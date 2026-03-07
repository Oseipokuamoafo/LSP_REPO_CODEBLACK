# Assignment 4 – CRC Cards: ATC System Design

---

## CRC Card

**Class:** `Aircraft`

**Responsibilities:**
- Stores the plane's type and flight data
- Gives back flight data when another class asks for it

**Collaborators (if any):**
- `TransponderReceiver`
- `AircraftDatabase`

**Assumptions (if any):**
- Every plane has a unique ID like a tail number or flight number so the system can tell them apart

---

## CRC Card

**Class:** `TransponderReceiver`

**Responsibilities:**
- Picks up transponder signals sent by incoming planes
- Breaks down the packet data into something the system can work with
- Passes the data over to the database to be saved

**Collaborators (if any):**
- `Aircraft`
- `AircraftDatabase`

**Assumptions (if any):**
- Signals come in non-stop and get processed right away
- The packet format stays the same for all aircraft

---

## CRC Card

**Class:** `AircraftDatabase`

**Responsibilities:**
- Saves aircraft records and updates them as new data comes in
- Finds a plane's record when someone looks it up by ID
- Shares the list of active planes with the display and danger detector

**Collaborators (if any):**
- `Aircraft`
- `FlightDisplay`
- `DangerDetector`
- `ATCController`

**Assumptions (if any):**
- Only planes currently in range are kept in the database; a plane gets removed once it's out of range

---

## CRC Card

**Class:** `FlightDisplay`

**Responsibilities:**
- Grabs the latest plane data from the database every 10 seconds
- Shows each plane's position and flight info on screen
- Updates the screen so the controller always sees current info

**Collaborators (if any):**
- `AircraftDatabase`

**Assumptions (if any):**
- The 10-second refresh is fixed and doesn't change
- The display just shows data — it doesn't make any decisions on its own

---

## CRC Card

**Class:** `DangerDetector`

**Responsibilities:**
- Looks through the active plane list for anything risky, like planes flying too close or at conflicting altitudes
- Alerts the controller right away if something dangerous is spotted

**Collaborators (if any):**
- `AircraftDatabase`
- `ATCController`

**Assumptions (if any):**
- A dangerous situation means things like two planes on a collision path or a plane in restricted airspace
- The detector runs in the background at all times, not just when triggered

---

## CRC Card

**Class:** `ATCController`

**Responsibilities:**
- Looks up details on a specific plane from the database
- Gets notified when the danger detector finds a problem

**Collaborators (if any):**
- `AircraftDatabase`
- `DangerDetector`

**Assumptions (if any):**
- The controller can only look up planes that are currently showing on the display
- This class represents how the controller talks to the rest of the system
