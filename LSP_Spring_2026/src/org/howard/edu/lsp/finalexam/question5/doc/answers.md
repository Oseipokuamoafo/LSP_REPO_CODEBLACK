Heuristic 1:
Name:
All data should be hidden within its class (Heuristic 2.1).

Explanation:
In lecture, this was illustrated by comparing a class that exposes its fields as public vs. one that hides them behind private fields with accessor methods. When data is hidden, the internal representation can change without breaking client code. This improves maintainability because changes are localized — only the class itself needs to be updated, not every caller. It also improves readability because the public interface communicates intent rather than implementation details.

Heuristic 2:
Name:
A class should capture one and only one key abstraction (Heuristic 2.8).

Explanation:
In lecture this was described as the "God class" anti-pattern — a class that does too many things, mixing data management, display logic, and business rules in one place. When a class has a single, clearly defined responsibility, it is easier to read (you know exactly what the class is for), easier to test in isolation, and easier to maintain because changes to one responsibility do not accidentally break another. In the RequestManager example, mixing ID generation and request storage in one class already pushes this boundary.

Heuristic 3:
Name:
Do not put implementation details in the public interface of a class (Heuristic 2.6).

Explanation:
In lecture this was applied to helper methods — methods that exist only to support other methods in the same class should be private, not public. As discussed with the getNextId() example, exposing an internal ID counter as a public method leaks the implementation and allows callers to advance the counter in ways the class never intended. Keeping implementation details private makes the class easier to understand (the public interface is smaller and cleaner) and easier to change (internal details can be refactored freely without breaking external code).
