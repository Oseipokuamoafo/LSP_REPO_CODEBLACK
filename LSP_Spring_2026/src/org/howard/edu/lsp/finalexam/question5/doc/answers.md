# Question 5 — Riel's OO Design Heuristics

---

## Heuristic 1:
**Name:** All data should be hidden within its class.

**Explanation:**
This heuristic states that instance variables should never be public, they should always be private and accessed only through methods. In lecture, this was illustrated by showing how exposing fields directly allows outside classes to change internal state in unpredictable ways, making the class hard to maintain. Keeping data hidden means only the class itself controls how its state changes, which reduces bugs and makes the code easier to reason about.

---

## Heuristic 2:
**Name:** A class should capture one and only one key abstraction.

**Explanation:**
This heuristic means each class should represent exactly one concept or responsibility. In lecture, this was discussed in the context of classes that try to do too much — for example, a class that handles both data storage and output formatting. When a class has a single, clear purpose, it is easier to understand, test, and modify without breaking unrelated functionality.

---

## Heuristic 3:
**Name:** Do not put implementation details in the public interface of a class.

**Explanation:**
This heuristic says that methods which exist only to support internal operations should not be exposed publicly. In lecture, this was explained using examples where helper methods were made public unnecessarily, which locks the class into an implementation and confuses users of the class about what they are supposed to call. Keeping internal methods private makes the public interface cleaner and gives the developer freedom to change the implementation later without affecting other classes.
