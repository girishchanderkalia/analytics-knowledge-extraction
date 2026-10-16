# Physical schema extraction correction report

Platform: analytics-foundation. Status: corrections verified; awaiting human approval.
Recommendation: **ACCEPT_WITH_COMMENTS**. Three blockers resolved; six nonblocking comments retained.

## Exact record changes

| Routine | Field | Before | After |
|---|---|---|---|
| `check_repack_privileges` | `hasDynamicSql` | `true` | `false` |
| `grant_repack_privileges` | `hasDynamicSql` | `true` | `false` |
| `pg_repack_reinstall` | `hasDynamicSql` | `true` | `false` |

Stable IDs, source references, function bodies/body hashes, restrictions, statuses, runtime authorization, and all other fields are unchanged. The two real dynamic-SQL routines, get_data_directory_disk_usage and get_fragmentation_stat, retain hasDynamicSql=true.

## Root-cause correction

Replaced the extraction script's word-regex classification with offline pglast.parse_plpgsql. Only actual procedural dynamic execution nodes count: PLpgSQL_stmt_dynexecute, PLpgSQL_stmt_dynfors, and dynamic query fields on RETURN QUERY/OPEN. Invalid bodies fail explicitly; no regex fallback is used. Classification does not execute SQL or resolve business semantics. The corrected extraction script is session tooling; the reproducible classifier and regression inputs are retained below.

## Checksums and evidence

- Source SHA-256 (unchanged): `032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad`
- functions.jsonl before: `76a17b7bd9e0eee880e8d19be18ba7eb75d58e8abf96608fe25acdc2420d1e61`
- functions.jsonl after: `839fbadc1fb8d0670b85ab960cd4c1ccf664caf306c95524aa69a64e36bd5221`
- Review policy SHA-256 (unchanged): `76d196c1db1180bdd94ef49f4789cbe71302e1f53ee0aa7b0b6ecb6e700c6610`
- Full offline extraction regenerated 31,341 records; exact equality with persisted drafts verified.
- Delta assertion permits only the three true -> false fields listed above.
- All 13 output hashes, IDs, references, inventories, constraints, comments, routine bodies, view definitions and lineage validated against 16,197 source statements.
- Seven existing/new test methods pass, including 13 classification cases and fail-closed invalid-body coverage.
- Before the correction, seven false-positive subcases and the invalid-body test failed.
- Source DDL, domain drafts, approved records, approval files and release directories were not modified.
- Configuration nesting and absent repository-level validation remain nonblocking release/automation comments; no unrelated configuration change was made.

## Reproducible classification regression

Uses the extraction environment's existing pglast 8.6 dependency; no database is required. Run as Python to assert the corrected classification and rejection of malformed procedural syntax.

```python
from pglast import parse_plpgsql
from pglast.parser import ParseError

def contains_dynamic_sql(value):
    if isinstance(value, dict):
        if "PLpgSQL_stmt_dynexecute" in value or "PLpgSQL_stmt_dynfors" in value:
            return True
        for key in ("PLpgSQL_stmt_return_query", "PLpgSQL_stmt_open"):
            if value.get(key, {}).get("dynquery") is not None:
                return True
        return any(contains_dynamic_sql(child) for child in value.values())
    if isinstance(value, list):
        return any(contains_dynamic_sql(child) for child in value)
    return False

cases = [('line-comment', "BEGIN -- EXECUTE 'SELECT 1';\n RETURN; END;", False), ('nested-comment', 'BEGIN /* outer /* EXECUTE */ comment */ RETURN; END;', False), ('quoted-text', "BEGIN RAISE NOTICE 'EXECUTE'; RETURN; END;", False), ('dollar-text', "BEGIN RAISE NOTICE $msg$EXECUTE 'SELECT 1'$msg$; RETURN; END;", False), ('quoted-identifier', 'DECLARE "EXECUTE" text; BEGIN "EXECUTE" := \'x\'; RETURN; END;', False), ('grant', 'BEGIN GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA lisa TO PUBLIC; RETURN; END;', False), ('revoke', 'BEGIN REVOKE EXECUTE ON ALL FUNCTIONS IN SCHEMA lisa FROM PUBLIC; RETURN; END;', False), ('execute', "BEGIN EXECUTE 'SELECT 1'; RETURN; END;", True), ('nested-execute', "BEGIN IF true THEN EXECUTE 'SELECT 1'; END IF; RETURN; END;", True), ('return-query', "BEGIN RETURN QUERY EXECUTE 'SELECT 1'; END;", True), ('static-query', 'BEGIN RETURN QUERY SELECT 1; END;', False), ('dynamic-loop', "DECLARE r record; BEGIN FOR r IN EXECUTE 'SELECT 1' LOOP NULL; END LOOP; RETURN; END;", True), ('dynamic-cursor', "DECLARE c refcursor; BEGIN OPEN c FOR EXECUTE 'SELECT 1'; RETURN; END;", True)]

for name, body, expected in cases:
    returns = 'SETOF int' if name in ('return-query', 'static-query') else 'void'
    statement = f'CREATE FUNCTION lisa.test() RETURNS {returns} LANGUAGE plpgsql AS $body${body}$body$;'
    actual = contains_dynamic_sql(parse_plpgsql(statement))
    assert actual is expected, (name, actual, expected)
try:
    parse_plpgsql('CREATE FUNCTION lisa.bad() RETURNS void LANGUAGE plpgsql AS $$BEGIN EXECUTE; END;$$;')
except ParseError:
    pass
else:
    raise AssertionError('Malformed procedural body was accepted')
print('13 classification cases and invalid-body check passed')
```

## Approval boundary

This is a deterministic parser correction and review recommendation, not human approval. Draft functions remain restricted and runtimeAuthorized=false. No knowledge was promoted or released.
