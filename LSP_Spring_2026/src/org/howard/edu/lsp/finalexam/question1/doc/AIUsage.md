AI Tools Used:
Claude Code (claude-sonnet-4-6)

Prompts Used:
1. Explain what shared resources and race conditions are in the context of Java concurrency.
2. What is the difference between synchronizing getNextId() vs addRequest() for thread safety?
3. How does AtomicInteger provide thread safety without the synchronized keyword?

How AI Helped:
AI helped clarify why synchronizing only one method at a time is insufficient when a compound operation spans multiple shared resources. It also helped me understand how AtomicInteger uses CAS (compare-and-swap) to achieve lock-free thread safety.

Reflection:
I learned that thread safety requires protecting the entire compound operation, not just individual steps. Choosing the right granularity of synchronization is critical to correctness.
