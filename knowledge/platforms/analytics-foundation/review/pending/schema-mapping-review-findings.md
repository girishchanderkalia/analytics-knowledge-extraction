# Physical schema extraction findings

Scope: extraction self-check only. No semantic-mapping approval or independent review is claimed.

- Parser failures or unsupported constructs: 0.
- Blocking extraction defects: none detected.
- Restricted objects: 148; restrictions derive from conservative extraction policy.
- Missing schema-mapping review policy: deferred repository configuration prerequisite for combined policy-based review.
- Function bodies: procedural bodies are preserved but not compiled; internal dynamic dependencies are not certified.
- View type/nullability: computed types and all view nullability requiring server analysis are explicitly unknown.
- View projection lineage resolves source column IDs within every operand, including both UNION branches; full query SQL preserves join and filter dependencies.
- Full table metadata and source comments are declared physical evidence only, never business semantics.
- Index records describe explicit CREATE INDEX statements. Constraint-backed indexes are represented by their PK/unique constraints, not invented as extra CREATE INDEX records.
- Human schema-mapping approval is absent. Draft metadata does not authorize runtime access.
- All eight deferred domain findings remain for semantic mapping; none was silently resolved during extraction.
