# Reflection: Assignment 3 – Object-Oriented ETL Pipeline Redesign

**Course:** Large-Scale Programming (LSP) – Spring 2026
**Student:** Gerald Amoafo
**Package:** `org.howard.edu.lsp.assignment3`

---

## 1. What Changed in the Design

### Assignment 2 Architecture

Assignment 2 delivered the entire ETL pipeline as a single class,
`ETLPipeline`, containing one method: `main()`. All responsibilities —
opening the file, reading lines, parsing fields, validating data, applying
business rules, and writing the output — were mixed together in roughly
90 lines of sequential, procedural code. Products had no representation of
their own: each record existed only as a handful of local variables
(`productId`, `name`, `price`, `category`, `priceRange`) that were declared
inside a `try` block and discarded at the end of each loop iteration.

This approach works for a small script, but it has several drawbacks:

- **Untestable in isolation** — there is no way to unit-test the discount
  logic or the CSV reader without running the entire pipeline.
- **Tightly coupled** — changing the input format, the business rules, or the
  output destination all require touching the same method.
- **Not extensible** — adding a new transformation or a second output target
  means editing code that already works.

### Assignment 3 Architecture

Assignment 3 decomposes the pipeline into **six classes**, each with a single,
well-defined responsibility:

| Class | Responsibility |
|---|---|
| `Product` | Domain model: encapsulates the five fields of one product record |
| `ETLStage` | Abstract base class defining the common pipeline-stage contract |
| `ProductExtractor` | Extract phase: opens, reads, validates, and parses the CSV |
| `ProductTransformer` | Transform phase: applies the four business rules |
| `ProductLoader` | Load phase: writes the header and product rows to the output CSV |
| `ETLPipeline` | Orchestrator: sequences the stages and prints the run summary |

The same `data/products.csv` input path and `data/transformed_products.csv`
output path are used, and the console summary format is unchanged, so the
observable behavior of Assignment 3 is identical to Assignment 2.

---

## 2. How the Design Is More Object-Oriented

### Objects and Classes

In Assignment 2 a "product" had no identity outside a single loop iteration.
In Assignment 3 every valid CSV row becomes a `Product` **object** with
persistent state. The pipeline passes a `List<Product>` between stages, so the
data flows through the system as named, typed objects rather than ephemeral
strings.

### Separation of Concerns

Each stage class has one reason to change:

- If the input format changes, only `ProductExtractor` is edited.
- If a new discount rule is added, only `ProductTransformer` is edited.
- If the output format changes (e.g. JSON instead of CSV), only `ProductLoader`
  is replaced.

This is the Single Responsibility Principle in practice.

---

## 3. OO Concepts Applied

### Encapsulation

`Product` stores all five fields (`productId`, `name`, `price`, `category`,
`priceRange`) as **private** instance variables. External code cannot read or
modify them directly; it must use the provided getters and setters. This means
the internal representation (e.g. using `BigDecimal` for price) is hidden from
the rest of the pipeline.

Each stage class likewise keeps its own state private: `ProductExtractor` hides
its `List<Product>`, `rowsRead`, and `rowsSkipped` counters; `ProductLoader`
hides its `rowsWritten` counter. Callers see only the results they need through
narrow, well-named accessor methods.

### Inheritance

`ETLStage` is an **abstract base class**. The three stage classes extend it:

```
ETLStage  (abstract)
├── ProductExtractor
├── ProductTransformer
└── ProductLoader
```

Each subclass inherits the `stageName` field and the `getStageName()` method
without repeating that code. The "is-a" relationships are natural:
a `ProductExtractor` **is an** `ETLStage`; so are the transformer and loader.

### Polymorphism

`ETLStage` declares `execute()` as **abstract**. Each subclass provides a
completely different implementation:

- `ProductExtractor.execute()` performs file I/O and CSV parsing.
- `ProductTransformer.execute()` mutates product objects in memory.
- `ProductLoader.execute()` serialises products and writes to disk.

`ETLPipeline.main()` invokes `execute()` on each concrete stage through its
specific reference. Because all three share the common `ETLStage` type, new
stages (e.g. a validation stage or a second loader) can be added by subclassing
`ETLStage` without changing the existing classes — this is the Open/Closed
Principle as a benefit of polymorphism.

### Summary Table

| Concept | Where Applied |
|---|---|
| **Object** | Each product row is a `Product` object; each stage is an `ETLStage` object |
| **Class** | `Product`, `ETLStage`, `ProductExtractor`, `ProductTransformer`, `ProductLoader`, `ETLPipeline` |
| **Encapsulation** | Private fields in `Product` and all stage classes; access only through getters/setters |
| **Inheritance** | Three concrete stage classes extend the abstract `ETLStage` |
| **Polymorphism** | `execute()` overridden in each stage; correct implementation selected at runtime |

---

## 4. Verifying Identical Output

### Step-by-Step Test Procedure

1. **Run Assignment 2** by executing `org.howard.edu.lsp.assignment2.ETLPipeline`
   with the existing `data/products.csv`. Note the console output and save or
   record the contents of `data/transformed_products.csv`.

2. **Run Assignment 3** by executing `org.howard.edu.lsp.assignment3.ETLPipeline`
   against the same `data/products.csv`.

3. **Compare the output files** using a diff tool:
   ```
   diff data/transformed_products.csv <saved-a2-output>
   ```
   The diff should report no differences.

4. **Compare the console summaries** — both programs must print:
   ```
   ETL Run Summary
   ----------------
   Rows read: 11
   Rows transformed: 7
   Rows skipped: 4
   Output written to: data/transformed_products.csv
   ```

### Edge Cases Verified by Manual Trace

| Input row | A2 behaviour | A3 behaviour |
|---|---|---|
| `badid,Pen,1.00,Stationery` | `parseInt` throws → skipped | Same: `parseInt` in `ProductExtractor.execute()` throws → skipped |
| `14,Notebook,abc,Stationery` | `BigDecimal` throws → skipped | Same: `new BigDecimal(...)` in `ProductExtractor.execute()` throws → skipped |
| `15,TooFewFields,9.99` | 3 fields ≠ 4 → skipped | Same: `parts.length != 4` check → skipped |
| `16,Too,Many,Fields,Here` | 5 fields ≠ 4 → skipped | Same |
| `7,USB Cable,9.99,Electronics` | 9.99 × 0.90 = 8.991 → 8.99, Low | Same in `applyElectronicsDiscount` + `roundPrice` |
| `11,Mouse,25.555,Electronics` | 25.555 × 0.90 = 22.9995 → 23.00, Medium | Same |
| `9,4K TV,1200.00,Electronics` | 1200 × 0.90 = 1080.00 > 500 → Premium Electronics, Premium | Same in `upgradePremiumCategory` |
| `13,Camera,556.00,Electronics` | 556 × 0.90 = 500.40 > 500 → Premium Electronics, Premium | Same |
| `8,  Office Chair  ,150.00, Furniture ` | trimmed, uppercased, Furniture, High | Same |
| `10,Gift Card,10.00,Other` | 10.00 ≤ 10 → Low | Same |
| `12,Table,500.00,Furniture` | 500.00 ≤ 500 → High | Same |

All seven written rows and the skip count of four match the expected output
file. Both assignments use the same relative paths, so no classpath or working
directory changes are needed when switching between them.
