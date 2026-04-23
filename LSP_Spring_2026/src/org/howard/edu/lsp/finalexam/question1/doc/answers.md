# Question 1 Answers

## Part 1: Shared Resources and Risk

**Shared Resource #1:** `nextId` — the integer counter used to generate unique request IDs

**Shared Resource #2:** `requests` — the shared ArrayList that stores all request strings

**Concurrency Problem:**
A race condition may occur. Multiple threads can read and modify `nextId` and `requests` simultaneously without coordination, leading to duplicate IDs or corrupted list state.

**Why addRequest() is unsafe:**
`addRequest()` performs multiple non-atomic operations: it calls `getNextId()` to read and increment `nextId`, then constructs a request string, then adds it to `requests`. If two threads execute these steps concurrently, they may read the same value of `nextId` before either increments it, resulting in duplicate request IDs. Additionally, `ArrayList` is not thread-safe, so concurrent `add()` calls can corrupt the list.

---

## Part 2: Evaluate Fixes

**Fix A: `public synchronized int getNextId() { ... }`**
✗ Incorrect. Synchronizing only `getNextId()` protects the ID counter in isolation, but `addRequest()` is still unsynchronized. A thread can call `getNextId()` and get a unique ID, but then be interrupted before adding to the list. Another thread can do the same. The compound action of getting an ID and adding the request is still not atomic, so race conditions on `requests` remain.

**Fix B: `public synchronized void addRequest(String studentName) { ... }`**
✓ Correct. Synchronizing `addRequest()` makes the entire compound operation atomic — getting the ID, constructing the request string, and adding to the list all happen as one locked block. No two threads can execute this method at the same time, eliminating both the duplicate ID problem and the unsafe ArrayList access.

**Fix C: `public synchronized List<String> getRequests() { ... }`**
✗ Incorrect. This only synchronizes the getter that returns the list. It does nothing to protect `nextId` or the `add()` operations inside `addRequest()`. The race condition during request creation is completely unaddressed.

---

## Part 3: Object-Oriented Design

**Answer:** No, `getNextId()` should not be public.

**Explanation:** According to Arthur Riel's heuristics, a class should hide its implementation details and only expose what is necessary for clients to use it. `getNextId()` is an internal mechanism used by `addRequest()` to manage ID generation — it is an implementation detail, not part of the intended public interface. Making it public violates encapsulation by allowing external classes to call it directly, which could increment the counter and cause IDs to be skipped or misused. It should be private or package-private.

---

## Part 4: Alternative Synchronization Approach

**Description:**
The alternative approach discussed in lecture is using `ReentrantLock` from `java.util.concurrent.locks`. Instead of using the `synchronized` keyword, a `ReentrantLock` is explicitly acquired before the critical section and released in a `finally` block to guarantee it is always unlocked. This gives more fine-grained control over locking compared to `synchronized` and makes the locking behavior more visible and explicit in the code.

**Code Snippet:**
```java
import java.util.concurrent.locks.ReentrantLock;

private final ReentrantLock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}
```
