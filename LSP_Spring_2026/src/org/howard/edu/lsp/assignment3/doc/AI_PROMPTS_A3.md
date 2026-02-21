# AI Prompts – Assignment 3 ETL Redesign

**Course:** Large-Scale Programming (LSP) – Spring 2026
**Student:** Gerald Amoafo

This document is a full transcript of the prompts submitted to the AI assistant
(Claude, Anthropic) during the design and implementation of Assignment 3, along
with concise summaries of the AI's responses.

---

## Prompt 1

**User:**

> I need help redesigning my Assignment 2 Java ETL pipeline to be more
> object-oriented for Assignment 3. Here is my current Assignment 2 code:
> [paste your A2 code here]
>
> Please help me do the following:
>
> 1. Redesign the code into multiple classes under the package
>    `org.howard.edu.lsp.assignment3`. Suggest a clean OO decomposition with
>    clear class responsibilities. Use OO principles including encapsulation,
>    inheritance, and polymorphism where appropriate.
> 2. Generate the full Java source files, one public class per file, with
>    complete Javadocs for every class and public method.
> 3. Generate `REFLECTION_A3.md` saved to
>    `src/org/howard/edu/lsp/assignment3/doc/`.
> 4. Generate `AI_PROMPTS_A3.md` saved to
>    `src/org/howard/edu/lsp/assignment3/doc/`.

**AI Response Summary:**

The AI observed that the Assignment 2 source code had not been pasted — the
message still contained the literal placeholder text `[paste your A2 code here]`.
It explained that without the actual code it could not ensure the redesigned
pipeline would be behaviour-equivalent, and asked the user to share the
Assignment 2 Java source file(s) along with a sample of `data/products.csv` if
available.

---

## Prompt 2

**User:**

> I need you to read my Assignment 2 Java ETL pipeline from my codebase located
> at `src/org/howard/edu/lsp/assignment2/` and redesign it as Assignment 3 under
> `src/org/howard/edu/lsp/assignment3/`. Please do the following:
>
> 1. Read and analyze all existing files in `src/org/howard/edu/lsp/assignment2/`
>    before making any changes.
> 2. Redesign into multiple classes with clear OO responsibilities. Apply
>    encapsulation, inheritance, and polymorphism where appropriate. Suggest and
>    create at least 3 classes (e.g. a model class, an extractor, a transformer,
>    a loader, a pipeline runner, etc.).
> 3. Create all Java source files under `src/org/howard/edu/lsp/assignment3/`,
>    one public class per file, with full Javadocs on every class and public
>    method. The program must behave identically to Assignment 2 — same inputs,
>    outputs, transformations, error handling, and relative paths.
> 4. Create `src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md` comparing
>    Assignment 2 vs Assignment 3 (1–2 pages).
> 5. Create `src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md` as a
>    transcript of the prompts I gave you and summaries of your responses.
>
> Do not modify any existing Assignment 2 files. Produce everything completely
> with no placeholders or TODOs.

**AI Response Summary:**

The AI used file-system search tools to locate the project at
`/Users/geraldamoafo/git/LSP_REPO_CODEBLACK/LSP_Spring_2026/`. It read the
following files before writing a single line of new code:

- `src/org/howard/edu/lsp/assignment2/ETLPipeline.java` — the complete A2
  source (106 lines).
- `data/products.csv` — 12 rows (1 header + 11 data rows including blanks and
  intentionally malformed entries).
- `data/transformed_products.csv` — the expected output (header + 7 product
  rows).
- `src/module-info.java` — confirmed the module declaration requires no
  additional `exports` for the new package.

**Design analysis:** The AI identified that A2 was entirely procedural — one
class, one method, all concerns interleaved. It proposed a six-class
object-oriented architecture:

| Class | Role |
|---|---|
| `Product` | Encapsulated domain model (private fields, getters, setters, `toCSVRow()`) |
| `ETLStage` | Abstract base class declaring `execute() throws IOException` |
| `ProductExtractor` | Extends `ETLStage`; reads and parses the CSV |
| `ProductTransformer` | Extends `ETLStage`; applies the four business rules |
| `ProductLoader` | Extends `ETLStage`; writes the output CSV |
| `ETLPipeline` | Contains `main()`; sequences the three stages |

**Behaviour tracing:** Before writing any code the AI manually traced the A2
transformation logic for every row in `products.csv` to ensure the new classes
would produce byte-for-byte identical output:

- Electronics 10 % discount is applied using `BigDecimal("0.90")` before
  rounding.
- Price is rounded to 2 decimal places with `RoundingMode.HALF_UP`.
- The "Premium Electronics" upgrade checks the **post-discount, post-rounded**
  price against `$500.00` using the original category string (before any
  category mutation), exactly mirroring A2's `isElectronics` boolean.
- PriceRange thresholds: Low ≤ $10.00, Medium ≤ $100.00, High ≤ $500.00,
  Premium > $500.00.
- Invalid rows (non-integer ProductID, non-numeric Price, wrong field count)
  are skipped with the same counter increments as A2.

**OO principles applied:**

- *Encapsulation* — `Product` hides all fields behind private access; each
  stage hides its counters and paths.
- *Inheritance* — `ProductExtractor`, `ProductTransformer`, and `ProductLoader`
  all extend `ETLStage`, inheriting `stageName` and `getStageName()`.
- *Polymorphism* — `execute()` is abstract in `ETLStage` and overridden
  differently in each subclass; `ETLPipeline` invokes the correct behaviour
  through the concrete-type references.

**Files created (in order):**

1. `src/org/howard/edu/lsp/assignment3/Product.java`
2. `src/org/howard/edu/lsp/assignment3/ETLStage.java`
3. `src/org/howard/edu/lsp/assignment3/ProductExtractor.java`
4. `src/org/howard/edu/lsp/assignment3/ProductTransformer.java`
5. `src/org/howard/edu/lsp/assignment3/ProductLoader.java`
6. `src/org/howard/edu/lsp/assignment3/ETLPipeline.java`
7. `src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md`
8. `src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md`

No Assignment 2 files were modified. All paths are relative to the Eclipse
project root, preserving compatibility with the existing classpath and
`module-info.java`.
