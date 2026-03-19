
# Design Evaluation: OrderProcessor

## Issues Identified

### 1. The Class Does Too Much
`OrderProcessor` tries to do everything in one method — it calculates tax, prints a receipt, saves to a file, sends an email, applies a discount, and logs activity. A class should really only have one job. When one class handles this many unrelated things, it becomes a nightmare to update or test without breaking something else.

### 2. The Fields Are All Public
Every field — `customerName`, `email`, `item`, and `price` — is public, which means any other part of the program can change them directly. That's a problem because there's no way to validate or control what values get set. Fields should be private with getters and setters so the class controls its own data.

### 3. Nothing in the Method Belongs Together
Tax calculation, file saving, email sending, and logging have nothing to do with each other. They just happen to all run when an order is placed. Cramming them into one method makes the code hard to read, hard to test, and risky to change since touching one thing could accidentally break another.

### 4. Magic Numbers Everywhere
The tax rate `0.07`, the discount threshold `500`, and the discount rate `0.9` are all just hardcoded into the method with no explanation. If the business changes any of those values, you have to dig into the code to find and change them manually, which is easy to mess up.

### 5. The Discount Is Applied Too Late
The discount gets calculated after the receipt is already printed and saved to the file, so the customer sees and the file stores the wrong total whenever a discount applies. This kind of bug is easy to miss when everything is jammed into one method.

### 6. No Separation Between Logic and Infrastructure
Business logic like tax and discounts is mixed in with infrastructure like file writing and console output. This makes it basically impossible to write unit tests — you can't test the tax calculation without also triggering a file write or console print as a side effect.