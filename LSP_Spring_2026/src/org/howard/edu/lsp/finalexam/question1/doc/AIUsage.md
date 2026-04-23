# AI Usage — Question 1

**AI Tools Used:** Claude (Anthropic)

**Prompts Used:**
1. What are the shared resources in a multithreaded Java class with a counter and ArrayList?
2. Why is a method that calls getNextId() and then adds to a list considered unsafe in a concurrent environment?
3. How does synchronizing only one method in a compound operation still leave a race condition?
4. How can ReentrantLock be used as an alternative to synchronized in Java?

**How AI Helped:**
AI helped me think through which resources are actually shared and why compound operations create race conditions even when individual methods are synchronized. It also helped me understand the ReentrantLock approach and how to structure the finally block properly.

**Reflection:**
I learned that synchronizing individual methods is not always enough — the atomicity of the full operation matters. Understanding this made the fix evaluation section much clearer.
