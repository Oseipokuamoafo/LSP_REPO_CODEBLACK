# Development Log - Question 2

## External Resources Used

**AI Tool: Claude (claude-sonnet-4-6)**

---

**Prompt:**
"What is the Single Responsibility Principle and how do I identify when a class has too many responsibilities?"

**Response:**
Claude explained that the Single Responsibility Principle means a class should have only one reason to change. A class has too many responsibilities when it handles unrelated tasks — like calculating tax, printing receipts, writing to files, and sending emails all in one place. This helped me identify that OrderProcessor violates SRP by bundling five distinct concerns into a single processOrder() method.

---

**Prompt:**
"What does encapsulation mean in Java and what are the risks of making fields public?"

**Response:**
Claude explained that encapsulation means hiding internal state behind private fields and controlled access methods. Making fields public allows any outside class to read or modify them directly, bypassing validation and breaking the integrity of the object. This helped me identify that OrderProcessor's public fields (customerName, email, item, price) are a direct encapsulation violation.

---

**Prompt:**
"What is cohesion in OOP design and how does low cohesion hurt maintainability?"

**Response:**
Claude explained that cohesion refers to how closely related a class's responsibilities are. Low cohesion means a class does many unrelated things, making it harder to understand, test, and change without breaking other functionality. This helped me articulate in evaluation.md why OrderProcessor is low-cohesion — its responsibilities span tax logic, file I/O, email, discounts, and logging.

---

**Prompt:**
"What is coupling in OOP and why is tight coupling a problem?"

**Response:**
Claude explained that coupling measures how dependent one class is on another. Tight coupling means changes in one class force changes in others. OrderProcessor is tightly coupled to file I/O and email logic directly inside processOrder(), meaning swapping out any of those behaviors requires modifying the class itself rather than just replacing a dependency.

---

**Prompt:**
"How should file I/O, email sending, and tax calculation each be handled from a design perspective?"

**Response:**
Claude explained that each of these concerns should live in its own dedicated class — an OrderRepository for file I/O, an EmailService for sending confirmations, and a TaxCalculator or PriceCalculator for computing totals. This guided my CRC card redesign in design.md, where I split OrderProcessor into four focused classes.

---

**Prompt:**
"What is the Open/Closed Principle and how does it apply to a class that handles multiple unrelated tasks?"

**Response:**
Claude explained that the Open/Closed Principle means a class should be open for extension but closed for modification. OrderProcessor violates this because adding a new discount type or changing the logging behavior requires editing the class directly. Separating responsibilities into smaller classes makes each one easier to extend independently.

---

**Prompt:**
"How do I write CRC cards for a redesigned system — what format should they follow?"

**Response:**
Claude explained that CRC cards follow a Class / Responsibilities / Collaborators format. Each card names the class, lists what it is responsible for doing, and lists which other classes it depends on. This helped me structure design.md with properly formatted CRC cards for Order, TaxCalculator, OrderRepository, EmailService, and OrderProcessor.

---

**Prompt:**
"If I were to split OrderProcessor into multiple classes, what responsibilities would each class have?"

**Response:**
Claude walked through how to decompose OrderProcessor: Order holds customer and item data, TaxCalculator computes tax and discounts, OrderRepository handles file persistence, EmailService sends confirmation emails, and a slimmed-down OrderProcessor coordinates them. This directly informed my CRC card design.

---

**Prompt:**
"What is Arthur Riel's heuristic about God classes and how do I identify one?"

**Response:**
Claude explained that Riel's heuristics warn against "God classes" — classes that know too much or do too much, centralizing the intelligence of the system in one place. Signs of a God class include a large number of unrelated methods, direct manipulation of data that belongs to other objects, and a name so generic it could describe anything. OrderProcessor qualifies because it handles tax, discounts, file persistence, email, receipt printing, and logging all within a single method, making it a central point of failure and modification.

---

**Prompt:**
"What is the Law of Demeter and does OrderProcessor violate it?"

**Response:**
Claude explained that the Law of Demeter (also called the Principle of Least Knowledge) states that a method should only call methods on: itself, its parameters, objects it creates, or its direct fields. OrderProcessor doesn't violate it in the traditional chaining sense, but the spirit of the law is violated because processOrder() reaches directly into file system APIs, email concerns, and logging — all of which are outside its core domain. A well-designed class delegates those operations to collaborators rather than handling them directly.

---

**Prompt:**
"Why is it a problem to mix business logic with I/O operations in the same method?"

**Response:**
Claude explained that mixing business logic with I/O makes the code untestable in isolation — you can't unit test the tax calculation without triggering a file write or console output. It also violates the Separation of Concerns principle, making it impossible to swap out the file storage mechanism (e.g., switching from a flat file to a database) without touching the business logic. Keeping them in separate classes allows each to change independently.

---

**Prompt:**
"What is the difference between a collaborator and a dependency in OOP design?"

**Response:**
Claude explained that a collaborator is a class that another class works with to fulfill a responsibility — it appears in the Collaborators column of a CRC card because it is actively needed to complete a task. A dependency is a broader term for any class that another class relies on, including collaborators. In CRC card design, listing collaborators makes explicit which classes a given class delegates to, helping identify where responsibilities are properly distributed versus where they're being absorbed by one class.
