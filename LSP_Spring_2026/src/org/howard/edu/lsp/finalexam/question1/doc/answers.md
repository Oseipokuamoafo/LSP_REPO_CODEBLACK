Part 1:
Shared Resource #1: nextId (the integer counter used to generate unique request IDs)
Shared Resource #2: requests (the ArrayList that stores all request strings)

Concurrency Problem: Race condition. Two threads can read the same value of nextId simultaneously, producing duplicate IDs. They can also interleave calls to requests.add(), causing lost updates or even an ArrayIndexOutOfBoundsException because ArrayList is not thread-safe.

Why addRequest() is unsafe: addRequest() performs a compound read-modify-write operation across two separate shared resources (nextId via getNextId(), and requests via requests.add()). Neither operation is atomic. A thread can be preempted between any two steps, allowing another thread to observe or modify the shared state in between, producing duplicate IDs or corrupting the list.

Part 2:
Fix A: Explanation
NOT correct. Synchronizing only getNextId() ensures that the read-increment-return of nextId is atomic, but it does not protect requests.add() in addRequest(). Two threads can each obtain their unique ID correctly but then call requests.add() concurrently on a non-thread-safe ArrayList, causing data corruption or lost entries.

Fix B: Explanation
CORRECT. Synchronizing addRequest() on the same object lock ensures that the entire compound action — getting the next ID and adding the request to the list — is executed as a single atomic unit. No other thread can enter addRequest() while one thread is inside it, so both nextId and requests are always accessed under the same lock. This eliminates the race condition completely.

Fix C: Explanation
NOT correct. Synchronizing getRequests() only protects the return of the list reference; it does nothing to protect nextId or the add operations in addRequest(). The race condition on ID generation and list mutation is entirely unaffected.

Part 3:
Answer + Explanation
No, getNextId() should NOT be public. According to Arthur Riel's heuristics, a class should minimize its public interface and expose only what external clients truly need. getNextId() is an internal implementation detail used solely by addRequest(). Making it public violates encapsulation — it exposes internal state-management logic, allows external callers to advance the counter without adding a request (breaking invariants), and makes the class harder to maintain because the internal counter becomes part of the public API contract. It should be private or package-private.

Part 4:
Description:
The alternative approach is using java.util.concurrent atomic classes, specifically AtomicInteger for the ID counter and CopyOnWriteArrayList (or Collections.synchronizedList) for the request list. AtomicInteger provides lock-free, thread-safe increment-and-get operations using hardware-level compare-and-swap (CAS) instructions, eliminating the need for the synchronized keyword. For the list, replacing ArrayList with CopyOnWriteArrayList makes add() thread-safe without explicit locking.

Code Snippet:
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private List<String> requests = new CopyOnWriteArrayList<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        requests.add("Request-" + id + " from " + studentName);
    }
}
