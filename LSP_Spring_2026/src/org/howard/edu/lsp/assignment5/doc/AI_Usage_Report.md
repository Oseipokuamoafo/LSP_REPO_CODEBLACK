# AI Usage Report – Assignment 5: IntegerSet Implementation

## AI Conversations Used

**Interaction 1 – Understanding JCF Methods for Set Operations**
Prompt: "What is the difference between addAll, retainAll, and removeAll in Java's ArrayList? Can you give me a simple example of each?"
How it helped: I wasn't sure which built-in ArrayList methods mapped to which set operations. This clarified that retainAll keeps only common elements (intersection), removeAll eliminates matching elements (difference), and addAll merges two lists (union). I then wrote the methods myself using this understanding.

**Interaction 2 – Clarifying the complement Definition**
Prompt: "In set theory, what is the complement of Set A with respect to Set B? How is that different from the difference A - B?"
How it helped: The assignment defines complement(IntegerSet b) as elements in b but not in this, which is the reverse of diff. I wanted to confirm my understanding of the math before coding it to make sure I wasn't mixing up the two operations.

**Interaction 3 – Exception Handling for largest() and smallest()**
Prompt: "If I have a method that returns the largest integer in an ArrayList, what should it do if the list is empty? Should I throw an exception or return a sentinel value?"
How it helped: I decided to throw an IntegerSetException (a custom exception) when largest() or smallest() is called on an empty set. Claude confirmed this is the cleaner, more professional approach compared to returning something like -1, which could be a valid set member.

**Interaction 4 – toString() Formatting**
Prompt: "I need my toString() method to return integers in ascending order, comma-separated, inside brackets — like [1, 2, 3]. Is Collections.sort() the right approach here, and how do I join the list into a string?"
How it helped: I had the sorting part figured out but wasn't sure about the cleanest way to format the output string. Claude suggested using String.join(", ", ...) after converting integers to strings, which I then implemented myself.

**Interaction 5 – JUnit 5 Test Case Ideas**
Prompt: "I'm writing JUnit 5 tests for a custom IntegerSet class. What edge cases should I consider when testing methods like union, intersect, diff, and complement?"
How it helped: I had already written basic tests but wanted to make sure I wasn't missing anything. Claude suggested testing: empty sets on both sides, sets with no overlap, sets that are identical, single-element sets, and making sure the original sets are not modified after operations. I used these ideas to expand my test suite.

**Interaction 6 – Verifying equals() Logic**
Prompt: "If I want to check that two ArrayLists contain the same integers regardless of order, what's a reliable way to do that in Java?"
How it helped: I knew order shouldn't matter for set equality but wasn't sure of the cleanest implementation. Claude suggested sorting both lists and comparing them, or using containsAll in both directions. I went with the containsAll approach since it felt more semantically aligned with set equality.

## External Websites / References Used

Java SE 21 API Documentation – https://docs.oracle.com/en/java/docs/api/
Used to look up method signatures for ArrayList, Collections.sort(), and String.join().
