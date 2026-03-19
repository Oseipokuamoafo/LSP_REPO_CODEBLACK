# Proposed Improved Design: CRC Cards



**Class:** Order

**Responsibilities:**
- Store customer name, email, item, and price
- Provide access to order data via getters

**Collaborators:**
- None



**Class:** TaxCalculator

**Responsibilities:**
- Calculate tax amount for a given price
- Calculate total price including tax

**Collaborators:**
- None


**Class:** DiscountCalculator

**Responsibilities:**
- Determine whether a discount applies based on price
- Calculate the discounted total

**Collaborators:**
- None



**Class:** ReceiptPrinter

**Responsibilities:**
- Format and print an order receipt to the console

**Collaborators:**
- Order



**Class:** OrderRepository

**Responsibilities:**
- Save order data to persistent storage (e.g., a file)

**Collaborators:**
- Order



**Class:** EmailService

**Responsibilities:**
- Send a confirmation email to the customer

**Collaborators:**
- Order


**Class:** OrderLogger

**Responsibilities:**
- Log order activity with a timestamp

**Collaborators:**
- Order


**Class:** OrderProcessor

**Responsibilities:**
- Coordinate the order processing workflow by delegating to TaxCalculator, DiscountCalculator, ReceiptPrinter, OrderRepository, EmailService, and OrderLogger

**Collaborators:**
- Order, TaxCalculator, DiscountCalculator, ReceiptPrinter, OrderRepository, EmailService, OrderLogger
