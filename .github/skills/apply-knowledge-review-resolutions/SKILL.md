---
name: apply-knowledge-review-resolutions
description: Applies configured review resolutions to draft platform or application knowledge without performing human approval.
---

Read the target configuration and resolve:

- the review stage;
- the review policy;
- draft path;
- review inputs;
- resolution set;
- required outputs.

## Process

1. Validate that every resolution references an existing finding.
2. Validate that each category exists in the review policy.
3. Validate that the requested disposition is allowed.
4. Calculate blocking status and runtime treatment from policy.
5. Apply explicit decisions only.
6. Apply only mutations allowed by policy.
7. Do not rewrite source facts merely to remove a review comment.
8. Preserve source references.
9. Update configured review artifacts.
10. Produce a change report with before, after, and reason.
11. Derive the recommendation from remaining findings.
12. Stop before human approval.

## Safety

Never:

- invent a domain resolution;
- modify source documents or schema files;
- verify a database mapping during domain review;
- write to approved knowledge;
- create a release.