# Friday User Guide

![A sample screenshot of Friday in use.](Ui.png)

Friday is a friendly chatbot that aims to help organise your day by keeping track of your tasks.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding Todos: `todo`](#adding-todos-todo)
    - [Adding Deadlines: `deadline`](#adding-deadlines-deadline)
    - [Adding Events: `event`](#adding-events-event)
    - [Marking Task as Completed: `mark`](#marking-task-as-completed-mark)
    - [Marking Task as not Completed: `unmark`](#marking-task-as-not-completed-unmark)
    - [Deleting a Task: `delete`](#deleting-a-task-delete)
    - [Show Task List: `list`](#show-task-list-list)
    - [Find a Task: `find`](#find-a-task-find)
    - [Exiting The App: `bye`](#exiting-the-app-bye)

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the .jar from [here](https://github.com/oneBoz/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Friday.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar Friday.jar` command to run
   the
   application.

## Features

> ### Note
>- Words in `UPPER_CASE` are parameters supplied by the user.<br>(e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a
   parameter which can be used as `todo Read Book`)
>- Items in square brackets are optional.<br>(e.g. `deadline DESCRIPTION /by DUE_DATE` can be used as
   `deadline Return Book /by 2024-11-12`)
>- `DATE` arguments are to be in the format `yyyy-MM-dd`<br>(e.g. 1st September 2024 would be formatted as `2024-09-01`)

### Adding Todos: `todo`

Adds a todo to the task list.

Format: `todo DESCRIPTION`

Examples:

- `todo Read Book` adds a todo with a description of *Read Book* to the task list.
- `todo Study` adds a todo with a description of *Study* to the task list.

### Adding Deadlines: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DUE_DATE`

Examples:

- `deadline CS2103T IP /by 2024-09-20` adds a deadline with a description of *CS2103T IP* and a due date of
  *Sep 20 2024*

### Adding Events: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

Examples:

- `event Team Meeting /from 2024-09-20 /to 2024-09-20` adds an event with a description of *Team
  Meeting* which starts on *2024-09-20 at 15:00* and ends at *2024-09-20 at 16:00*

### Marking Task as Completed: `Mark`

Marks a specified task as completed.

Format: `mark TASK_INDEX`

- Marks the task at the specified index as complete.
- The index refers to the index number shown in the task list.

Examples:

- `mark 3` marks the 3rd task in the task list as completed.

### Marking Task as not completed: `Unmark`

Marks a specified task as not completed.

Format: `unmark TASK_INDEX`

- Marks the task at the specified index as not complete.
- The index refers to the index number shown in the task list.

Examples:

- `unmark 3` marks the 3rd task in the task list as completed.

### Deleting a Task: `delete`

Deletes a specified task.

Format: `delete TASK_INDEX`

- Deletes the task at the specified index.
- The index refers to the index number shown in the task list.

Examples:

- `delete 1` deletes the 1st task in the task list.

### Show Task List: `list`

Displays the task list.

Format: `list`

### Find a Task: `find`

Displays all tasks with containing a specified keyword in their description.

Format: `find KEYWORD`

Examples:

- `find book` displays all tasks containing the word *book* in their description.

### Exiting The App: `bye`

Exits the app

Format: `bye`

Friday will give one last message before closing the app.