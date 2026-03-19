# Development Log - Question 3

## External Resources Used

**AI Tool: Claude (claude-sonnet-4-6)**

---

**Prompt:**
"Why is using a chain of if statements for behavior selection considered bad OOP design?"

**Response:**
Claude explained that if/else chains for behavior selection violate the Open/Closed Principle — every time a new customer type is added, the method must be modified. It also concentrates unrelated logic in one place, making the class harder to maintain and test. This helped me articulate the design problems in design_evaluation.md.

---

**Prompt:**
"What is the Open/Closed Principle and how does the Strategy pattern satisfy it?"

**Response:**
Claude explained that the Strategy pattern satisfies OCP by defining a common interface for behaviors and implementing each variation in a separate class. Adding a new customer type means adding a new class, not modifying existing ones. This framed my justification for why the Strategy pattern is the right refactoring approach.

---

**Prompt:**
"What is an interface in Java and how is it different from an abstract class?"

**Response:**
Claude explained that an interface defines a contract of method signatures with no implementation, while an abstract class can have both abstract and concrete methods. For the Strategy pattern, an interface is appropriate because all pricing strategies share the same method signature and none need shared implementation.

---

**Prompt:**
"How do I implement the Strategy pattern in Java — what are the interface, concrete classes, and context class?"

**Response:**
Claude described the three components: a PricingStrategy interface with a calculatePrice(double price) method, concrete classes like RegularPricing, MemberPricing, VIPPricing, and HolidayPricing each implementing it, and a PriceCalculator context class that accepts a PricingStrategy and delegates to it. This gave me the structure to build my refactored implementation.

---

**Prompt:**
"How do I call a strategy dynamically in Java based on a string input like 'VIP' or 'MEMBER'?"

**Response:**
Claude showed how to use a HashMap<String, PricingStrategy> to map string keys to strategy instances, then retrieve and call the appropriate one based on input. This helped me implement the lookup mechanism in PriceCalculator without using if statements.

---

**Prompt:**
"How do I write a Java Driver class that tests multiple strategy implementations and prints formatted output?"

**Response:**
Claude explained how to instantiate a context class with different strategies and call the method for each, printing results with System.out.println(). This helped me write Driver.java to produce the required output format: REGULAR: 100.0, MEMBER: 90.0, VIP: 80.0, HOLIDAY: 85.0.

---

**Prompt:**
"How do I apply a percentage discount in Java without hardcoding the result?"

**Response:**
Claude showed that a percentage discount is applied as price * multiplier, where the multiplier is stored as a field or constant in each strategy class. For example, MemberPricing uses price * 0.90. This kept the discount logic encapsulated inside each strategy rather than hardcoded in a central method.

---

**Prompt:**
"What should a development log for a Java assignment include when AI tools were used?"

**Response:**
Claude explained that a development log for AI tool usage should include each prompt sent, the response received, and how it influenced the solution. It should be honest and complete — partial transcripts are not acceptable. This helped me structure all three development_log files across the exam questions.

---

**Prompt:**
"What are the three roles in the Strategy pattern — context, strategy, and concrete strategy?"

**Response:**
Claude explained the three roles: the Strategy interface defines the common method signature (calculatePrice); the Concrete Strategy classes each implement that interface with a specific algorithm (RegularPricingStrategy, MemberPricingStrategy, etc.); and the Context class (PriceCalculator) holds a reference to a Strategy and delegates the work to it. The Context doesn't know or care which concrete strategy it's using — it just calls the interface method. This gave me a clear mental model for structuring the refactored design.

---

**Prompt:**
"How do I use constructor injection vs setter injection for a strategy in Java?"

**Response:**
Claude explained that constructor injection passes the strategy when the object is first created (new PriceCalculator(new RegularPricingStrategy())), while setter injection allows the strategy to be swapped after construction via a setStrategy() method. I implemented both in PriceCalculator — the constructor sets the initial strategy, and setStrategy() allows the Driver to switch strategies at runtime without creating a new PriceCalculator instance for each customer type.

---

**Prompt:**
"What is the difference between composition and inheritance and why does the Strategy pattern prefer composition?"

**Response:**
Claude explained that inheritance creates an is-a relationship and bakes behavior into the class hierarchy, making it rigid — changing behavior requires subclassing or modifying a parent. Composition creates a has-a relationship where an object delegates behavior to another object it holds a reference to. The Strategy pattern uses composition because PriceCalculator has a PricingStrategy rather than extending it. This means pricing behavior can be swapped at runtime without changing the class hierarchy, making the design far more flexible.

---

**Prompt:**
"How does the Strategy pattern relate to polymorphism in Java?"

**Response:**
Claude explained that the Strategy pattern relies directly on polymorphism — PriceCalculator holds a reference typed as PricingStrategy (the interface), and at runtime the JVM dispatches the calculatePrice() call to whichever concrete class is currently assigned. This is dynamic dispatch: the same method call produces different behavior depending on which strategy is in use. Without polymorphism, the Strategy pattern wouldn't work — you'd be forced back to if/else chains to distinguish between types.
