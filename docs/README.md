# BobbyBot User Guide

![image info](./Ui.png)

**BobbyBot** is a task management chatbot designed for desktop use. It combines the speed and efficiency of a Command Line Interface (CLI) with the advantages of a Graphical User Interface (GUI).

## Features
1. [Adding a todo task](#adding-a-todo-task-todo)
2. [Adding a deadline task](#adding-a-deadline-task-deadline)
3. [Adding an event task](#adding-an-event-task-event)
4. [Listing all tasks](#listing-all-tasks-list)
5. [Marking a task as done](#marking-a-task-as-done-mark)
6. [Unmark a task as done](#unmark-a-task-as-done-unmark)
7. [Deleting a task](#deleting-a-task-delete)
8. [Finding tasks by keyword](#finding-tasks-by-keyword-find)
9. [Undo the last command](#undo-the-last-command-undo)
10. [Stop the program](#stop-the-program-bye)

> **_NOTE:_** Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Study`.

### Adding a _todo_ task: `todo`
Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Examples: 
- `todo Do Group Project`
- `todo Read Book`

### Adding a _deadline_ task: `deadline`
Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

- The `DATE` should be in the format `YYYY-MM-DD`.

Examples:
- `deadline Group Project /by 2021-09-17`
- `deadline Submit Assignment /by 2021-09-20`

### Adding an _event_ task: `event`
Adds an event task to the task list.

Format: `event DESCRIPTION /from DATE /to DATE`

- The `DATE` should be in the format `YYYY-MM-DD`.

Examples:
- `event Group Meeting /from 2021-09-17 /to 2021-09-18`
- `event Concert /from 2021-09-20 /to 2021-09-21`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `mark`
Marks a task as done in the task list.

Format: `mark INDEX`

- Marks a task as done at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

### Unmark a task as done: `unmark`
Unmarks a task as done in the task list.

Format: `unmark INDEX`

- Unmarks a task as done at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

### Deleting a task: `delete`
Deletes a task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

### Finding tasks by keyword: `find`
Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

- The search is case-sensitive. e.g. `CS2103T tp` will **not** match `CS2103T TP`
- Only the description is searched.

### Undo the last command: `undo`
Undoes the last command that modifies the task list.

Format: `undo`

### Stop the program: `bye`
Exits the program.

Format: `bye`