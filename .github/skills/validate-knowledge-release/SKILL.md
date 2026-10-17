---
name: validate-knowledge-release
description: Deterministically validates approved platform or application knowledge before immutable release creation. Use for release preparation and release validation.
---

Read the target configuration and run `scripts/validate_knowledge.py` from this
skill directory.

The agent runs the script. Do not ask the user to execute it during normal use.

Validate:

- approved domain, physical-schema, and semantic-mapping layers exist;
- required human approval and promotion files exist;
- records are valid JSON objects with unique IDs and approved status;
- platform IDs are consistent;
- semantic domain and physical references resolve;
- mapping classification and evidence state agree;
- nonverified mappings remain runtime denied;
- restricted physical objects remain runtime denied;
- a proposed immutable release version does not already exist.

Do not validate SHA/checksum chains. Existing SHA fields are legacy metadata.
Do not require every mapping to be runtime eligible. Never alter knowledge to
make validation pass.
