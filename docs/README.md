# README
# Chicken the Chatbot
## Preview of Chicken the chatbot
![bot preview](Ui.png)
## Features (Only some)

### 1. Adding Deadlines

You can add a deadline by using the following format:

- Action Format: `deadline <description> /by <date>`
- Date Format: `D/MM/YYYY HHMM` (e.g., 2/12/2019 1800) or `YYYY-MM-DD` (e.g 2024-04-03)

Make sure to use `/by` to specify the deadline.

#### Example Usage

```plaintext
deadline return book /by 2/12/2019 1800
```

#### Expected Outcome

```plaintext
[D][] return book (by: 2nd of December 2019, 6:00 pm) added
```

### 2. Finding Items

You can find items by their description using:

- **Command Format:** `find <description>`

This will list all items that match the given description.

### 3. Listing All Items

To list all your items, use the command:

- **Command Format:** `list`

This will display all items you currently have.

### 4. Marking Items

To mark an item, use the command:

- **Command Format:** `mark <item number>`

This will mark the specified item as completed.
