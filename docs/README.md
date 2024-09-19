# Him User Guide

![A sample screenshot of Him in use.](Ui.png)

Him is a helpful chatbot that aims to help organise your day by keeping track of your tasks.

- [Quick Start](#quick-start)
- [Feature List](#feature-list)
    - [Adding Todos: `todo`](#adding-todos-todo)
    - [Adding Deadlines: `deadline`](#adding-deadlines-deadline)
    - [Adding Events: `event`](#adding-events-event)
    - [Marking Task as Completed: `mark`](#marking-task-as-completed-mark)
    - [Deleting a Task: `delete`](#deleting-a-task-delete)
    - [Show Task List: `list`](#show-task-list-list)
    - [Find a Task: `find`](#find-a-task-find)
    - [Archiving a Task: `archive`](#archive-a-task-archive)
    - [Displaying Archive: `archive`](#display-archive-archive)
    - [Exiting The App: `bye`](#exiting-the-app-bye)

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the .jar from [here](https://github.com/IsaacPangTH/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Him.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar him.jar` command to run
   the
   application.

## Feature List

> ### Note
>- Words in `UPPER_CASE` are parameters supplied by the user.<br>(e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a
   parameter which can be used as `todo Read Book`)
>- Items in square brackets are optional.<br>(e.g. `deadline DESCRIPTION /by DUE_DATE /at [DUE_TIME]` can be used as
   `deadline Return Book /by 2024-11-12` or
   `deadline Return Book /by 2024-11-12 /at 18:00`)
>- `DATE` arguments are to be in the format `yyyy-MM-dd`<br>(e.g. 1st September 2024 would be formatted as `2024-09-01`)
>- `TIME` arguments are to be in the format `HH:mm`<br> (e.g. 12.05 am is to be formatted as `00:05`)

### Adding Todos: `todo`

Adds a todo to the task list.

Format: `todo DESCRIPTION`

Examples:

- `todo Read Book` adds a todo with a description of *Read Book* to the task list.
- `todo Study` adds a todo with a description of *Study* to the task list.

### Adding Deadlines: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DUE_DATE /at [DUE_TIME]`

Examples:

- `deadline Return Book /by 2024-11-12` adds a deadline with a description of *Return Book* and a due date of
  *2024-11-12*
- `deadline Coding Assignment /by 2024-09-09 /at 23:59`adds a deadline with a description of *Coding Assignment* and a
  due date of *2024-09-09 at 23:59*

### Adding Events: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /start START_DATE /at START_TIME /end END_DATE /at END_TIME`

Examples:

- `event Team Meeting /start 2024-09-20 /at 15:00 /end 2024-09-20 /at 16:00` adds an event with a description of *Team
  Meeting* which starts on *2024-09-20 at 15:00* and ends at *2024-09-20 at 16:00*

### Marking Task as Completed: `Mark`

Marks a specified task as completed.

Format: `mark TASK_INDEX`

- Marks the task at the specified index as complete.
- The index refers to the index number shown in the task list.

Examples:

- `mark 3` marks the 3rd task in the task list as completed.

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

### Archive a Task: `archive`

Archives a specified task.

Format: `archive TASK_INDEX`

- Archives the task at the specified index.
- The index refers to the index number shown in the task list.

Examples:

- `archive 1` archives the 1st task in the task list.

### Display Archive: `archive`

Displays all archived tasks.

Format: `archive`

### Exiting The App: `bye`

Exits the app

Format: `bye`

Him will give one last message before closing the app.