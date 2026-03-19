# Development Log - Question 1

## External Resources Used

**AI Tool: Claude (claude-sonnet-4-6)**

---

**Prompt:**
"What is the difference between a HashMap and an ArrayList in Java, and when would I choose one over the other?"

**Response:**
Claude explained that a HashMap stores key-value pairs and provides O(1) lookup by key, making it ideal when you need to retrieve objects by a unique identifier. An ArrayList stores elements in order and requires O(n) linear search to find a specific element. For TaskManager, a HashMap is the better choice because tasks need to be located by taskId quickly and duplicate IDs need to be prevented efficiently.

---

**Prompt:**
"How do I iterate over a collection in Java and filter elements based on a condition?"

**Response:**
Claude explained how to use a for-each loop to iterate over a collection and use an if statement to check a condition before adding elements to a result list. This helped me implement getTasksByStatus(), where I loop over the HashMap's values and collect only the tasks whose status matches the input string.

---

**Prompt:**
"How do I write a toString() method in Java that formats output a specific way?"

**Response:**
Claude explained that toString() is overridden from the Object class and should return a formatted String. It showed how to use string concatenation to produce output in a specific format. This helped me implement Task's toString() to produce output in the format: taskId description [status].

---

**Prompt:**
"What happens when you call a method on a null object in Java and how do I return null safely from a method?"

**Response:**
Claude explained that calling a method on a null reference throws a NullPointerException. To return null safely, you simply write "return null" when the desired object is not found. This helped me implement findTask() to return null when no task matches the given taskId, matching the expected Driver output "Task not found."

---

**Prompt:**
"How do I prevent duplicate entries in a Java data structure?"

**Response:**
Claude explained that a HashMap naturally prevents duplicate keys since each key maps to exactly one value. In addTask(), I use containsKey() to check if the taskId already exists before adding, and throw an IllegalArgumentException if it does. This satisfies the duplicate task ID rule from the spec.

---

**Prompt:**
"What is the List interface in Java and how does ArrayList implement it?"

**Response:**
Claude explained that List is a Java Collections Framework interface that defines ordered collection behavior, and ArrayList is a resizable array implementation of it. This helped me understand that getTasksByStatus() must declare its return type as List<Task> while internally building and returning an ArrayList<Task>.

---

**Prompt:**
"How do I write a Javadoc class-level comment vs a method-level comment in Java?"

**Response:**
Claude showed that class-level Javadoc goes directly above the class declaration and includes a description and @author tag, while method-level Javadoc goes above each method and includes a description plus @param and @return tags where applicable. This helped me add proper documentation to all classes across the exam.

---

**Prompt:**
"What is the difference between @param, @return, and @throws in Javadoc?"

**Response:**
Claude explained that @param documents each method parameter, @return describes what the method returns, and @throws documents any exceptions the method may throw. This helped me write complete Javadoc for addTask() which throws IllegalArgumentException, and findTask() which returns null.

---

**Prompt:**
"How do I organize Java files into packages and what does the package declaration look like?"

**Response:**
Claude explained that the package declaration goes on the first line of every Java file and must match the directory structure. For example, package org.howard.edu.lsp.midterm.crccards; must appear at the top of Task.java and TaskManager.java, and the files must live in the corresponding nested folders.

---

**Prompt:**
"What does it mean for a method to throw a checked vs unchecked exception in Java?"

**Response:**
Claude explained that checked exceptions must be declared with throws in the method signature and handled by callers, while unchecked exceptions (like IllegalArgumentException) extend RuntimeException and do not require explicit declaration. This confirmed that addTask() can throw IllegalArgumentException without adding a throws clause to the signature.

---

**Prompt:**
"What is a LinkedHashMap in Java and how does it differ from HashMap?"

**Response:**
Claude explained that LinkedHashMap extends HashMap but maintains insertion order — elements are iterated in the order they were added. A plain HashMap gives no ordering guarantee. This matters for getTasksByStatus() because the Driver expects T1 before T3 in the OPEN TASKS output, which is only guaranteed if insertion order is preserved. Switching from HashMap to LinkedHashMap ensures the output matches exactly.

---

**Prompt:**
"How do I throw an exception with a custom message in Java?"

**Response:**
Claude showed that you pass the message string directly to the exception constructor: throw new IllegalArgumentException("Task with ID " + taskId + " already exists.");. The message can be retrieved later with e.getMessage(). This helped me implement the duplicate-ID check in addTask() with a descriptive error message.

---

**Prompt:**
"What is the difference between equals() and == when comparing Strings in Java?"

**Response:**
Claude explained that == checks reference equality (whether two variables point to the same object in memory), while equals() checks value equality (whether the contents are the same). String literals can sometimes pass == by coincidence due to interning, but this is not guaranteed. For setStatus(), I must use equals() to compare the incoming status string against "OPEN", "IN_PROGRESS", and "COMPLETE" to ensure correct case-sensitive comparison regardless of how the string was created.

---

**Prompt:**
"How does a constructor work in Java and how do I set default field values?"

**Response:**
Claude explained that a constructor runs when a new object is created and is used to initialize fields. Default values are assigned by directly setting fields inside the constructor body. For Task, the constructor accepts taskId and description as parameters and sets status = "OPEN" as a hardcoded default, satisfying the spec rule that every new task starts with OPEN status.

---

**Prompt:**
"Can you run the drivers again to confirm the output matches exactly?"

**Response:**
Claude compiled all midterm Java files and ran both Driver classes. Q1 output:
```
OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.
```
Q3 output:
```
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
```
Both match the expected output in the assignment exactly.

---

**Prompt:**
"Can you add a null check to findTask() as an edge-case guard?"

**Response:**
Claude added a null check at the top of findTask(): if the taskId argument is null, the method returns null immediately rather than passing null into the HashMap lookup, which could cause unexpected behavior. The rest of the method is unchanged and the Driver output is unaffected.
