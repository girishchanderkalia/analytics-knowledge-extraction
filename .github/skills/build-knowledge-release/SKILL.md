---
name: build-knowledge-release
description: Builds an immutable release from approved platform or application knowledge after deterministic validation passes.
---

Read the target configuration. Require all configured human approvals and
promotions and a PASS from the validate-knowledge-release skill for the proposed
version.

Package approved knowledge without changing semantic fields or runtime policy.
A release may contain partially verified, unresolved, conflicting, inferred, or
deprecated knowledge when those records remain runtime denied.

Create the configured manifest, retrieval index or bundle, changelog, and
validation report. Refuse to overwrite an existing release version.

Do not calculate or validate SHA/checksum chains. Existing checksum fields are
legacy metadata. Do not approve, promote, or runtime-authorize knowledge.
