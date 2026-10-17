#!/usr/bin/env python3
"""Simple deterministic validator used by the Knowledge Release Validator agent."""
from __future__ import annotations
import argparse, json, re, sys
from collections import Counter
from pathlib import Path
try:
    import yaml
except ImportError:
    print("ERROR: PyYAML is required (python -m pip install PyYAML)", file=sys.stderr)
    raise SystemExit(2)

ALLOWED_STATES={"verified","partially-verified","conflict","inferred","unresolved","deprecated"}
DENIED_STATES=ALLOWED_STATES-{"verified"}

def load_yaml(path):
    data=yaml.safe_load(path.read_text(encoding="utf-8"))
    if not isinstance(data,dict): raise ValueError(f"YAML root is not an object: {path}")
    return data

def get(data,path,default=None):
    cur=data
    for part in path.split('.'):
        if not isinstance(cur,dict) or part not in cur:return default
        cur=cur[part]
    return cur

def read_layer(root,rel,name,errors,ids):
    folder=root/rel; records=[]; index={}
    if not folder.is_dir(): errors.append(f"Missing approved {name} directory: {folder}"); return records,index
    files=sorted(folder.glob('*.jsonl'))
    if not files: errors.append(f"Approved {name} layer is empty: {folder}")
    for file in files:
        for line_no,line in enumerate(file.read_text(encoding='utf-8').splitlines(),1):
            if not line.strip(): continue
            try:r=json.loads(line)
            except Exception as exc: errors.append(f"{file}:{line_no}: invalid JSON: {exc}"); continue
            if not isinstance(r,dict): errors.append(f"{file}:{line_no}: record is not an object"); continue
            rid=r.get('id')
            if not isinstance(rid,str) or not rid: errors.append(f"{file}:{line_no}: missing id")
            elif rid in ids: errors.append(f"{file}:{line_no}: duplicate id {rid}")
            else: ids.add(rid); index[rid]=r
            records.append(r)
    return records,index

def main():
    ap=argparse.ArgumentParser()
    ap.add_argument('--platform',required=True,type=Path)
    ap.add_argument('--release-version')
    ap.add_argument('--report',type=Path)
    a=ap.parse_args(); root=a.platform.resolve(); errors=[]; warnings=[]; counts=Counter()
    cfg_path=root/'platform.yaml'
    if not cfg_path.is_file(): print(f"ERROR: missing {cfg_path}",file=sys.stderr); return 2
    cfg=load_yaml(cfg_path); pid=get(cfg,'metadata.id')
    if not pid: errors.append('platform.yaml metadata.id is missing')
    paths={
      'domain':Path(get(cfg,'spec.knowledge.domain.approvedPath','knowledge/approved/domain')),
      'physicalSchema':Path(get(cfg,'spec.knowledge.schema.approvedPath','knowledge/approved/schema')),
      'semanticMappings':Path(get(cfg,'spec.knowledge.mappings.approvedPath','knowledge/approved/mappings'))}
    ids=set()
    domain,domain_idx=read_layer(root,paths['domain'],'domain',errors,ids)
    phys,phys_idx=read_layer(root,paths['physicalSchema'],'physicalSchema',errors,ids)
    maps,map_idx=read_layer(root,paths['semanticMappings'],'semanticMappings',errors,ids)
    approval_paths=set()
    for name,records in [('domain',domain),('physicalSchema',phys),('semanticMappings',maps)]:
        counts[f'{name}.records']=len(records)
        for r in records:
            rid=r.get('id','<missing>')
            if r.get('platformId')!=pid: errors.append(f"{name}:{rid}: platformId must be {pid}")
            if r.get('status')!='approved': errors.append(f"{name}:{rid}: status must be approved")
            if not isinstance(r.get('sourceRefs'),list) or not r.get('sourceRefs'): errors.append(f"{name}:{rid}: sourceRefs must be non-empty")
            ar=r.get('approvalRef')
            if not isinstance(ar,dict) or not ar.get('approvalId') or not ar.get('path'):
                errors.append(f"{name}:{rid}: approvalRef approvalId/path missing")
            else: approval_paths.add(ar['path'])
    for rel in sorted(approval_paths):
        if not (root/rel).is_file(): errors.append(f"Referenced approval file missing: {rel}")
    approval_files=['review/approved/domain-approval.yaml','review/approved/schema-extraction-approval.yaml','review/approved/semantic-mapping-approval.yaml']
    promotion_files=['review/approved/domain-promotion.yaml','review/approved/schema-extraction-promotion.yaml','review/approved/semantic-mapping-promotion.yaml']
    for rel in approval_files+promotion_files:
        if not (root/rel).is_file(): errors.append(f"Required artifact missing: {rel}")
    for r in domain+phys:
        if r.get('runtimeAuthorized') is not False: errors.append(f"{r.get('id')}: runtimeAuthorized must be false")
    for r in phys:
        if r.get('runtimePolicy')=='exclude-from-runtime' and r.get('runtimeAuthorized') is not False:
            errors.append(f"{r.get('id')}: restricted object is runtime authorized")
        if r.get('runtimePolicy')=='exclude-from-runtime':
            oid=r.get('objectId')
            if not oid or oid not in phys_idx: errors.append(f"{r.get('id')}: restricted objectId does not resolve: {oid}")
    state_counts=Counter()
    for r in maps:
        rid=r.get('id'); state=r.get('classification'); ev=r.get('evidenceState'); state_counts[state]+=1
        if state not in ALLOWED_STATES: errors.append(f"{rid}: invalid classification {state}")
        if ev not in ALLOWED_STATES: errors.append(f"{rid}: invalid evidenceState {ev}")
        if state!=ev: errors.append(f"{rid}: classification != evidenceState")
        eligible=r.get('runtimeEligible'); authorized=r.get('runtimeAuthorized')
        if not isinstance(eligible,bool) or not isinstance(authorized,bool): errors.append(f"{rid}: runtime flags must be boolean"); continue
        if state in DENIED_STATES and (eligible or authorized): errors.append(f"{rid}: nonverified mapping is runtime enabled")
        if authorized and not eligible: errors.append(f"{rid}: authorization requires eligibility")
        if eligible and state!='verified': errors.append(f"{rid}: eligibility requires verified classification")
        if r.get('identifierCategoriesAreInterchangeable') is True: errors.append(f"{rid}: identifier categories cannot be interchangeable")
        if r.get('keyConstraintVerified') is False and (eligible or authorized): errors.append(f"{rid}: unverified key is runtime enabled")
        if r.get('traversalVerified') is False and (eligible or authorized): errors.append(f"{rid}: unverified traversal is runtime enabled")
        cps=r.get('canonicalPathSegmentAssessment')
        if isinstance(cps,dict) and cps.get('state')=='unresolved' and (eligible or authorized): errors.append(f"{rid}: unresolved path is runtime enabled")
        for ref in r.get('domainRecordRefs',[]):
            if ref not in domain_idx: errors.append(f"{rid}: unresolved domainRecordRef {ref}")
        for ref in r.get('physicalRecordRefs',[]):
            if ref not in phys_idx: errors.append(f"{rid}: unresolved physicalRecordRef {ref}")
            elif phys_idx[ref].get('runtimePolicy')=='exclude-from-runtime' and (eligible or authorized): errors.append(f"{rid}: restricted physical reference is runtime enabled")
    counts['all.unique_ids']=len(ids)
    for state,n in state_counts.items(): counts[f'semanticMappings.{state}']=n
    counts['semanticMappings.runtime_eligible']=sum(r.get('runtimeEligible') is True for r in maps)
    if a.release_version:
        if not re.fullmatch(r'(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)',a.release_version): errors.append('release version must be x.y.z')
        if (root/'knowledge'/'releases'/a.release_version).exists(): errors.append(f"release already exists: {a.release_version}")
    report={'platformId':pid,'releaseVersion':a.release_version,'passed':not errors,'counts':dict(counts),'errors':errors,'warnings':warnings}
    if a.report:
        a.report.parent.mkdir(parents=True,exist_ok=True); a.report.write_text(json.dumps(report,indent=2)+'\n',encoding='utf-8')
    print(f"Platform: {pid}")
    if a.release_version: print(f"Candidate release: {a.release_version}")
    print('Counts:')
    for k,v in sorted(counts.items()): print(f"  {k}: {v}")
    if errors:
        print('Validation errors:')
        for e in errors: print(f"  ERROR: {e}")
        print(f"FAILED: {len(errors)} error(s)"); return 1
    print('PASSED: approved knowledge, references, runtime policy, and release prerequisites are valid'); return 0
if __name__=='__main__': raise SystemExit(main())
