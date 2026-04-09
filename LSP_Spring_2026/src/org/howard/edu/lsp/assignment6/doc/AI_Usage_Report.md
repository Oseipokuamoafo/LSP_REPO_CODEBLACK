# AI Usage Report – Assignment 6: IntegerSet JUnit Testing

## AI Conversations Used

**Interaction 1 – Understanding JUnit 5 Test Structure**
Prompt: "What is the basic structure of a JUnit 5 test class? What annotations do I need and how do I import them?"
How it helped: I was familiar with JUnit 4 but not JUnit 5. This clarified that JUnit 5 uses @Test and @DisplayName from org.junit.jupiter.api, and that assertions come from org.junit.jupiter.api.Assertions. I then wrote all test methods myself.

**Interaction 2 – Using assertThrows Correctly**
Prompt: "How do I use assertThrows in JUnit 5 to verify that a method throws an exception?"
How it helped: I needed to test that largest() and smallest() throw an IllegalStateException when called on an empty set. Claude explained the assertThrows(ExceptionType.class, executable) syntax, which I then applied to both exception test cases.

**Interaction 3 – Edge Case Identification**
Prompt: "What edge cases should I test for a custom IntegerSet class that supports union, intersect, diff, and complement?"
How it helped: I had the basic test cases written but wanted to confirm I wasn't missing anything. Claude suggested testing empty sets, disjoint sets, identical sets, and verifying that original sets are not modified after set operations. I used these to complete my edge case coverage.

**Interaction 4 – assertDoesNotThrow Usage**
Prompt: "In JUnit 5, how do I assert that a method does NOT throw an exception?"
How it helped: I wanted to verify that remove() on a missing value and clear() on an already-empty set do not throw. Claude pointed me to assertDoesNotThrow(), which I applied in those two edge case tests.

## External Websites / References Used

Java SE 21 API Documentation – https://docs.oracle.com/en/java/docs/api/
Used to verify method signatures for ArrayList and Collections.

JUnit 5 User Guide – https://junit.org/junit5/docs/current/user-guide/
Referenced to confirm annotation names and assertion method signatures.
