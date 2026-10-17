# Apply the simplified release validation patch

This patch was built against the attached repository archive.

## What it changes

- Adds a non-user-invocable Knowledge Release Validator subagent.
- Adds a validation skill containing the deterministic validator script.
- Makes the AF Knowledge Coordinator invoke validation instead of asking users to run Python.
- Removes SHA/checksum-chain governance from release validation.
- Treats existing SHA fields as legacy metadata without rewriting approved knowledge.
- Updates the GitHub Actions workflow for the Analytics Foundation platform.
- Simplifies platform evaluation and invariant configuration.
- Preserves all approved knowledge, approvals, promotions, review history, and runtime restrictions.

## Apply from repository root

Git Bash:

```bash
unzip analytics-knowledge-simplified-validation-patch.zip -d /tmp/af-kb-patch
cp -R /tmp/af-kb-patch/. .
git status
```

PowerShell:

```powershell
Expand-Archive .\analytics-knowledge-simplified-validation-patch.zip -DestinationPath .\af-kb-patch
Copy-Item .\af-kb-patch\* . -Recurse -Force
Copy-Item .\af-kb-patch\.github . -Recurse -Force
git status
```

## Normal user workflow

In Copilot, select AF Knowledge Coordinator and run:

```text
Prepare platform release 1.0.0 for analytics-foundation.
```

The coordinator must delegate to Knowledge Release Validator. The validator
subagent runs the script inside the skill. Developers are not expected to run
the script manually during normal usage.

## Optional framework troubleshooting only

```bash
python -m pip install PyYAML
python .github/skills/validate-knowledge-release/scripts/validate_knowledge.py \
  --platform knowledge/platforms/analytics-foundation \
  --release-version 1.0.0
```

Expected result for the attached repository snapshot:

```text
PASSED: approved knowledge, references, runtime policy, and release prerequisites are valid
```
