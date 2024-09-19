# MorganaPro User Guide

![Ui](Ui.png)

MorganaPro frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ _SUPER_ FAST to use

## Features

> Notes about the command format:
> * Mandatory arguments are denoted by `<>`.
> * All dates are in `yyyy-MM-dd HHmm` format.
> * Extraneous arguments for commands that do not take in arguments (such as `list` and `bye`) will be ignored.<br>
    E.g., `list 123` will be interpreted as `list`.

### Adding a todo: `todo`

Adds a todo with the specified description.

Format: `todo <description>`

Example: `todo read book`

Expected output:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

### Adding a deadline: `deadline`

Adds a deadline with the specified due date.

Format: `deadline <description> /by <date>`

Example: `deadline return book /by 2019-10-15 1800`

Expected output:

```
Got it. I've added this task:
[D][ ] return book (by: Oct 15 2019, 6:00 PM)
Now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds an event with the specified start and end dates.

Format: `event <description> /from <date> /to <date>`

Example: `event project meeting /from 2019-10-15 1400 /to 2019-10-15 1600`

Expected output:

```
Got it. I've added this task:
[E][ ] project meeting (from: Oct 15 2019, 2:00 PM to: Oct 15 2019, 4:00 PM)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Lists all tasks in the task list.

Format: `list`

Example: `list`

Expected output:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Oct 15 2019, 6:00 PM)
3. [E][ ] project meeting (from: Oct 15 2019, 2:00 PM to: Oct 15 2019, 4:00 PM)
```

### Marking a task as done: `mark`

Marks the task at the specified index as done.

Format: `mark <index>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
1. [T][X] read book
```

### Marking a task as not done: `unmark`

Marks the task at the specified index as not done.

Format: `unmark <index>`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
1. [T][ ] read book
```

### Deleting a task: `delete`

Removes the task at the specified index from the task list.

Format: `delete <index>`

Example: delete 3

Expected output:

```
Noted. I've removed this task:
3. [E][ ] project meeting (from: Oct 15 2019, 2:00 PM to: Oct 15 2019, 4:00 PM)
Now you have 2 tasks in the list.
```

### Finding tasks by keyword: `Find`

Find tasks that contain a specific keyword in their description.

* The search is **case-sensitive**. E.g., `Book` will not match `book`.

Format: `find <keyword>`

Example: `find book`

Expected output:

```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Oct 15 2019, 6:00 PM)
```

### Exiting the application: `bye`

Exits the application.

Format: `bye`

Example: `bye`

Expected output:

```
Bye! Hope to see you again soon!
```

## Command Summary

| Command      | Format                                        |
|--------------|-----------------------------------------------|
| **todo**     | `todo <description>`                          |
| **deadline** | `deadline <description> /by <date>`           |
| **event**    | `event <description> /from <date> /to <date>` |
| **list**     | `list`                                        |
| **mark**     | `mark <index>`                                |
| **unmark**   | `unmark <index>`                              |
| **delete**   | `delete <index>`                              |
| **find**     | `find <keyword>`                              |
| **bye**      | `bye`                                         |
