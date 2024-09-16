# Eli User Guide

<img src="docs/Ui.png" alt="Eli chatbot screenshot" width="300"/>

Welcome to **Eli**, your lovely personal assistant designed to help you manage tasks with ease! Eli is a user-friendly chatbot that keeps track of your to-dos, deadlines, and events while communicating in a friendly and charming way.

---

## Quick Start Guide

1. **Add your tasks** by using the `todo`, `deadline`, and `event` commands.
2. **Manage your tasks** by marking them as done, unmarking, or deleting them.
3. **Find specific tasks** with the `find` command.
4. **List all your tasks** anytime with the `list` command.
5. **Exit** the chatbot by typing `bye`.

---

## Getting Started

To get started, simply clone the repository and run Eli via the terminal. Eli will greet you and await your commands to help manage your tasks.

For further help or issues, visit [our GitHub repository](https://github.com/munhuikim/your-repo).

---

## Features

### 1. Adding ToDos

- **Description**: Adds a new ToDo task to your list.

- **Usage**: todo <task description>

Example: `todo read a book`

**Expected Outcome**: A new ToDo task is added to your list.

```
Got it! I've added this task: [T][ ] read a book
```

### 2. Adding Deadlines
- **Description**: Adds a new task that needs to be completed by a specific date.
- **Usage**: deadline <task description> /by <YYYY-MM-DD>

Example: deadline submit assignment /by 2024-12-01

**Expected Outcome**: A new deadline task is added to your list with the specified due date.

```
Got it! I've added this task: [D][ ] submit assignment (by: Dec 1 2024)
```

### 3. Adding Events
- **Description**: Adds a new event that starts and ends at specific times.
- **Usage**: event <task description> /from <start time> /to <end time>

Example: event project meeting /from 2024-09-18 2pm /to 4pm

**Expected Outcome**: A new event task is added to your list with specified start and end times.

```
Got it! I've added this task: [E][ ] project meeting (from: Sep 18 2pm to: 4pm)
```

### 4. Listing All Tasks
- **Description**: Displays all your tasks in the list.
- **Usage**: list

Example: list

**Expected Outcome**: A numbered list of all your tasks, including their types and statuses.

Here are the tasks in your list:

```
[T][ ] read a book
[D][ ] submit assignment (by: Dec 1 2024)
```

### 5. Marking Tasks as Done
- **Description**: Marks a task as done.
- **Usage**: mark <task number>

Example: mark 1

**Expected Outcome**: The specified task is marked as completed.

```
Nice! I've marked this task as done: [T][X] read a book
```

### 6. Unmarking Tasks
- **Description**: Marks a completed task as not done yet.
- **Usage**: unmark <task number>

Example: unmark 1

**Expected Outcome**: The specified task is marked as not done.

```
OK, I've marked this task as not done yet: [T][ ] read a book
```

### 7. Deleting Tasks
- **Description**: Deletes a task from your list.
- **Usage**: delete <task number>

Example: delete 1

**Expected Outcome**: The specified task is removed from the list.

```
Noted. I've removed this task: [T][ ] read a book
```

### 8. Finding Tasks
- **Description**: Finds tasks that match a keyword.
- **Usage**: find <keyword>

Example: find book

**Expected Outcome**: A list of tasks matching the keyword is displayed.

```
Here are the matching tasks in your list:
[T][ ] read a book
```

### 9. Exiting Eli
- **Description**: Exits the application.
- **Usage**: bye

**Expected Outcome**: Eli says goodbye and the application closes.


```
Bye. Come back soon!
```











