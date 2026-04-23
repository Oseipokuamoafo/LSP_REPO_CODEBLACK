AI Tools Used:
Claude Code (claude-sonnet-4-6)

Prompts Used:
1. Explain the Template Method design pattern and how the abstract base class defines the workflow.
2. How should loadData() store values so that formatBody() can use them?
3. Why should generateReport() be declared final in the Template Method pattern?

How AI Helped:
AI helped clarify that the template method should be final to prevent subclasses from breaking the algorithm structure, and that data loaded in loadData() should be stored in instance fields so the formatting methods can access them.

Reflection:
I learned that the Template Method pattern cleanly separates what stays the same (the workflow) from what varies (the report-specific content), making the design open for extension but closed for modification.
