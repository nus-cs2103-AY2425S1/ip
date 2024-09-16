# Eli User Guide

<img src="/docs/Ui.png" alt="Eli chatbot screenshot" width="300"/>

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

### 1. Adding Tasks

- **Description**: Adds a task to your list.

- **Usage**:
1. todo <task description>
2. deadline <task description> /by <YYYY-MM-DD>
3. event <task description> /from <start time> /to <end time>

Example: `todo read a book`

**Expected Outcome**: A new ToDo task is added to your list.

```
Yay! \uD83C\uDF37 I’ve added '[T][] read a book' just for you! Keep up the great work! \uD83D\uDCAA✨ ";
```

### 2. Listing All Tasks
- **Description**: Displays all your tasks in the list.
- **Usage**: list

Example: list

**Expected Outcome**: A numbered list of all your tasks, including their types and statuses.

```
Here are the tasks in your list ✨:
[T][ ] read a book
[D][ ] submit assignment (by: Dec 1 2024)
```

### 3. Marking or Unmarking Tasks
- **Description**: Marks a task as done.
- **Usage**: 
1. mark <task number>
2. unmark <task number>

Example: mark 1

**Expected Outcome**: The specified task is marked as completed.

```
Woohoo! 🌟 You did it! So proud of you! 🎉 Keep going! 💪
```

### 4. Deleting Tasks
- **Description**: Deletes a task from your list.
- **Usage**: delete <task number>

Example: delete 1

**Expected Outcome**: The specified task is removed from the list.

```
Alright, now '[T][] read a book' is gone! ✨ You're doing amazing, keep it up! \uD83C\uDF08";
```

### 5. Finding Tasks
- **Description**: Finds tasks that match a keyword.
- **Usage**: find <keyword>

Example: find book

**Expected Outcome**: A list of tasks matching the keyword is displayed.

```
Here are the matching tasks in your list 🌸:
[T][ ] read a book
[D][ ] return book (Dec 2 2019)
```

### 6. Exiting Eli
- **Description**: Exits the application.
- **Usage**: bye

**Expected Outcome**: Eli says goodbye and the application closes.


```
Goodbye, sweet friend! Can’t wait to see you again soon! 💕✨
```











