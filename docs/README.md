# Chicken the Chatbot
### Preview of Chicken the chatbot
![bot preview](Ui.png)
## Features 

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

### 2. Adding Todo

You can add a todo by using the following format:

- Action Format: `todo <description>`

#### Example Usage

```plaintext
todo chicken
```

#### Expected Outcome

```plaintext
[T][] chicken added
```

### 3. Adding Event

You can add a event by using the following format:

- Action Format: `deadline <description> /from <date> /to <date>`
- Date Format: `D/MM/YYYY HHMM` (e.g., 2/12/2019 1800) or `YYYY-MM-DD` (e.g 2024-04-03)

Make sure to use `/from` and `/to` to specify the deadline.

#### Example Usage

```plaintext
event task /from 2024-04-03 /to 2024-03-05
```

#### Expected Outcome

```plaintext
[E][] task (from: Apr 03 2024 to: Mar 05 2024) added
```

### 4. Finding Items

You can find items by their description using:

- **Command Format:** `find <description>`

This will list all items that match the given description.

### 5. Listing All Items

To list all your items, use the command:

- **Command Format:** `list`

This will display all items you currently have.

### 6. Marking Items

To mark an item, use the command:

- **Command Format:** `mark <item number>`

This will mark the specified item as completed.

### 7. Delete Items

To delete an item, use the command:

- **Command Format:** `delete <item number>`

This will delete the specified item.

### 8. Unmarking Items

To mark an item, use the command:

- **Command Format:** `unmark <item number>`

This will unmark the specified item.

## Saving
Data in every session is automatically saved and retrieved from "data.txt" file
