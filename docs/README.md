# Downy User Guide

![Product Screenshot](./Ui.png)

Downy is a **chatbot** application for **managing tasks** and **notes**, optimised for use
via a Command Line Interface (CLI) whilst still having the benefits of a Graphical User
Interface (GUI).

## Features

> ℹ️ **Notes about the command format:**

- Words in **UPPER_CASE** are the parameters to be supplied by the user.
    - e.g. in `todo t/TASK`, `TASK` is a parameter which can be used as `todo t/Wash Clothes`.

- Items in square brackets are optional.
    - e.g. `d/DATE [t/TIME]` can be used as `d/2024/01/01 t/1800` or as `d/2024/01/01`.
    - 
- If an invalid command/format was entered, Downy will alert the user with a response
    - e.g. `Error: Invalid Command Entered`


### Viewing help : `help`

Displays a help message listing all valid commands.

Format: `help`

### List tasks: `list`

Displays a list of tasks.

Format: `list`

### Add a task todo: `todo`

Adds a todo task to the task list.

Format: `todo t/TASK`

Example: `todo return book`

### Add a deadline: `deadline`

Adds a deadline task with a due date to the task list.

Format: `deadline t/TASK /by d/DATE [t/TIME]`

- Date must be specified in YYYY/MM/DD format.
- Time must be specified in HHmm format. If no time is provided, the default time will be set to midnight.

Example: `deadline buy groceries /by 2024/10/10 2000`


### Add an event: `event`

Adds an event task with a start and ending time to the task list.

Format: `event t/TASK /from s/STARTDATE [s/STARTTIME] /to e/ENDDATE [e/ENDTIME]`

- Dates must be specified in YYYY/MM/DD format.
- Times must be specified in HHmm format. If no time is provided, the default time will be set to midnight.
- Start time must be before end time (chronologically)

Example: `event birthday party /from 2024/10/10 1800 /to 2024/10/11 0000`

### Mark a task complete: `mark`

Marks a task as complete in the task list.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` complete. The index refers to the index number shown in the list. The index
**must be a positive integer** 1, 2, 3... and the task **must exist**.

### Mark a task incomplete: `unmark`

Marks a task as incomplete in the task list.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX` incomplete. The index refers to the index number shown in the list. The index
  **must be a positive integer** 1, 2, 3... and the task **must exist**.

### Delete a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3...

### Find a task: `find`

Finds a task that contains the specified keyword.

Format: `find k/KEYWORD`

- The search is case-insensitive. e.g. `Read` will match `read`
- Only the task name is searched

### View/Edit notes: `note`

The `note` command handles three actions related to notes.

Formats:
1. `note list`
   - Displays a list of notes.
2. `note entry c/CONTENT`
   - Add a note with `c/CONTENT` to the note list.
3. `note delete INDEX`
   - Deletes the note with the specified `INDEX`
   - `INDEX` **must be a positive integer** 1,2,3....

### Exiting the program: `bye`
Exits the program.

Format: `bye`

## Command Summary Table

| **Command**   | **Action**                              | **Format**                                                               |
|---------------|-----------------------------------------|--------------------------------------------------------------------------|
| `help`        | Displays help message with all commands | `help`                                                                   |
| `list`        | Lists all tasks                         | `list`                                                                   |
| `todo`        | Adds a todo task                        | `todo t/TASK`                                                            |
| `deadline`    | Adds a deadline task                    | `deadline t/TASK /by d/DATE [t/TIME]`                                    |
| `event`       | Adds an event task                      | `event t/TASK /from s/STARTDATE [s/STARTTIME] /to e/ENDDATE [e/ENDTIME]` |
| `mark`        | Marks a task as complete                | `mark INDEX`                                                             |
| `unmark`      | Marks a task as incomplete              | `unmark INDEX`                                                           |
| `delete`      | Deletes a task                          | `delete INDEX`                                                           |
| `find`        | Finds a task by keyword                 | `find k/KEYWORD`                                                         |
| `note list`   | Lists all notes                         | `note list`                                                              |
| `note entry`  | Adds a new note                         | `note entry c/CONTENT`                                                   |
| `note delete` | Deletes a note                          | `note delete INDEX`                                                      |
| `bye`         | Exits the program                       | `bye`                                                                    |
