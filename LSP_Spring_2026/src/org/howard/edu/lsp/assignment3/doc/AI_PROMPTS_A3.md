AI Prompts – Assignment 3 ETL Redesign

Course: Large-Scale Programming (LSP) – Spring 2026
Student: Gerald Amoafo

This document contains the actual prompts I used while redesigning my Assignment 2 ETL pipeline into a more object-oriented structure for Assignment 3. I used AI mainly to help me think through class design, improve structure, and generate draft documentation that I later reviewed and edited.

---

Prompt 1

User:

I built my ETL pipeline for Assignment 2 in one main Java class. For Assignment 3, I need to redesign it to be more object-oriented while keeping the exact same behavior.

What would be a clean way to break this into multiple classes?

I want to apply encapsulation, inheritance, and polymorphism where it makes sense, but I also don't want to overcomplicate the design.

AI Response Summary:

The AI suggested separating the ETL stages into different classes instead of keeping everything in one file. It recommended creating a Product model class, separate classes for extraction, transformation, and loading, and possibly an abstract base class for shared behavior. This helped me think more clearly about responsibility separation instead of mixing all logic in main().

---

Prompt 2

User:

Here is my full Assignment 2 code.

Please analyze it carefully and redesign it into multiple classes under the package org.howard.edu.lsp.assignment3.

Important: The program must produce the exact same output file and handle errors the same way as Assignment 2. Do not change the logic — only improve the structure.

AI Response Summary:

The AI reviewed the structure and confirmed that my original implementation was mostly procedural. It proposed a redesign where:
	•	A Product class encapsulates product attributes.
	•	An Extractor class reads and parses the CSV file.
	•	A Transformer class applies business rules (discounts, rounding, category updates, price range classification).
	•	A Loader class writes the transformed results to the output file.
	•	A pipeline runner class coordinates everything.

I reviewed the suggestions and adjusted the class structure slightly to ensure it matched the assignment requirements and stayed clean.

---

Prompt 3

User:

Can you help me generate proper Javadocs for each class and public method?

I want them to follow standard Java documentation style and clearly explain what each class is responsible for.

AI Response Summary:

The AI generated draft Javadoc comments. I carefully reviewed and edited them to make sure they accurately described my implementation and did not include anything misleading or unnecessary.

---

Prompt 4

User:

How can I test my Assignment 3 version to confirm it behaves exactly like Assignment 2?

I want to make sure the output file is identical and that invalid rows are handled the same way.

AI Response Summary:

The AI recommended running both versions using the same input file and comparing the resulting transformed_products.csv files. It also suggested testing edge cases such as:
	•	Missing input file
	•	Empty input file
	•	Rows with invalid data types

I followed this approach to verify that my Assignment 3 implementation produced identical behavior.

---

Prompt 5

User:

Does my redesigned solution clearly demonstrate encapsulation, inheritance, and polymorphism? If not, how can I improve it without overengineering?

AI Response Summary:

The AI confirmed that encapsulation was demonstrated through private fields in the Product class, inheritance was shown through a shared abstract ETL stage class, and polymorphism was applied via overridden execute() methods. It also emphasized keeping the design simple and avoiding unnecessary complexity.

---

Final Note

I used AI as a design assistant and documentation helper, not as a replacement for understanding the assignment. I reviewed all generated code and explanations, ensured correctness, and made adjustments where necessary to meet the project requirements and coding standards discussed in class.
