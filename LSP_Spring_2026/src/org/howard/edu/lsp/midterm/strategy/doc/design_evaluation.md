# Design Evaluation: PriceCalculator

## Issues with the Current Design

### 1. You Have to Modify the Class Every Time You Add Something New
The `calculatePrice` method uses a chain of `if` statements to pick a price based on customer type. Every time a new customer type needs to be added like "STUDENT" or "EMPLOYEE" you have to go in and edit this method. That's risky because changing existing code can break things that already work. Good design means you should be able to add new behavior without touching old code.

### 2. Using Raw Strings Is Fragile
The method relies on strings like `"REGULAR"`, `"MEMBER"`, and `"VIP"` to decide what to do. If there's a typo or a capitalization difference, the method won't catch it it'll just return the original price with no warning. Nothing in the code stops someone from passing in a bad value.

### 3. Everything Is Crammed Into One Method
All the pricing logic lives in one place. As more customer types get added, this method just keeps growing. It becomes harder to read and harder to test each pricing rule on its own without running the whole thing.

### 4. Each Pricing Rule Should Have Its Own Home
Every customer type has its own distinct pricing behavior. Those behaviors should each live in their own class instead of being stacked on top of each other in one method. Mixing them all together means the class is doing more than one job.

### 5. Every If Block Looks the Same But Can't Be Reused
Each `if` block follows the exact same pattern  check the type, apply a multiplier. But because they're all hardcoded in one method, none of that logic can be swapped out or reused anywhere else. The Strategy Pattern fixes this by giving each behavior its own class that can be plugged in as needed.
