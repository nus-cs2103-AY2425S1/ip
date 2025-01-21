# MorganaPro User Guide

<img src="Ui.png" alt="Ui" width="500px">

MorganaPro frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ _SUPER_ FAST to use

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
   - [Adding a todo: `todo`](#adding-a-todo-todo)
   - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
   - [Adding an event: `event`](#adding-an-event-event)
   - [Listing all tasks: `list`](#listing-all-tasks-list)
   - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
   - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
   - [Deleting a task: `delete`](#deleting-a-task-delete)
   - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
   - [Exiting the application: `bye`](#exiting-the-application-bye)
   - [Saving the data](#saving-the-data)
   - [Editing the data file](#editing-the-data-file)
3. [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 17 or later installed.
2. Download the latest `.jar` file from [here](https://github.com/Gra7ityIC3/ip/releases).
3. Open a command line and navigate to the folder containing the JAR file.
4. Run `java -jar morgana.jar` to start the application.

## Features

> Notes about the command format:
> - Mandatory arguments are denoted by `<>`.
> - All dates are in `yyyy-MM-dd HHmm` format.
> - Extraneous arguments for commands that do not take in arguments (such as `list` and `bye`) will be ignored.<br>
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

- The search is **case-sensitive**. E.g., `Book` will not match `book`.

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

### Saving the data

MorganaPro automatically saves your data to the hard disk after every command that modifies the data. There's no need to save manually.

### Editing the data file

MorganaPro stores your data in a text file at `data/morgana.txt` within the same folder as the JAR file.

Editing this file is **discouraged**, as invalid changes can cause the application to crash, so always back up the file before making any modifications.

## Command Summary

| Command      | Format                                        |
|:-------------|:----------------------------------------------|
| **todo**     | `todo <description>`                          |
| **deadline** | `deadline <description> /by <date>`           |
| **event**    | `event <description> /from <date> /to <date>` |
| **list**     | `list`                                        |
| **mark**     | `mark <index>`                                |
| **unmark**   | `unmark <index>`                              |
| **delete**   | `delete <index>`                              |
| **find**     | `find <keyword>`                              |
| **bye**      | `bye`                                         |
