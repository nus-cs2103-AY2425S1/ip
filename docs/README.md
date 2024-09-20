# Mummy

**Mummy** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having
the
benefits of a Graphical User Interface (GUI). If you can type fast, Mummy can get your task management done faster than
traditional GUI apps.

## User guide

- Quick start
- Features
    - View list of tasks: `list`
    - Create a todo task: `todo [description]`
    - Create a deadline task: `deadline [description] /by <deadline>`
    - Create an event task: `event [description] /from <from> /to <to>`
    - Mark a task: `mark <task_number>`
    - Unmark a task: `unmark <task_number>`
    - Delete a task: `delete <task_number>`
    - Find a task: `find <keyword>`
    - Undo a command: `undo`
    - Redo a command: `redo`
    - Exit the program: `bye`
- FAQ
- Known issues
- Command summary

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest .jar from here.
3. Copy the file to the folder you want to use as the home folder for your Mummy
4. Open a command terminal, `cd` into the folder you put the jar file in, and
   use the `java -jar mummy.jar` command to run the application. A GUI similar
   to the below should appear in a few seconds. ![Ui](./Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g.
   typing `list` and pressing Enter will show the list of tasks.\
   Some example commands you can try:
    - `list`: Lists all tasks
    - `todo submit homework`: Adds a todo task with description `submit homework`
    - `delete 1`: Deletes the 1st task shown in the current list.
    - `bye`: Exits the app.
6. Refer to the Features below for details for each command.

## Features

> [!NOTE]
> **Notes about the command format:**
> - Words encapsulated with `<` and `>` are the parameters to be supplied by the user.
> - Words encapsulated with `[` and `]` are optional parameters.
> - e.g. `deadline [description] /by <deadline>` can be either used as `deadline foobar /by 20-12-2024`
    or `deadline /by 20-12-2024`.

### Listing all tasks: `list`

Shows a list of all tasks in your Mummy.

Format: `list`

### Creating a todo task: `todo`

Creates a todo task with a description.

Format: `todo [description]`

### Creating a deadline task: `deadline`

Creates a deadline task with a description and a deadline.

Format: `deadline [description] /by <deadline>`

### Creating an event task: `event`

Creates an event task with a description, `from` field and `to` field.

Format: `event [description] /from <from> /to <to>

### Marking a task: `mark`

Marks a task in the tasklist as completed. Uses the task's current number in the tasklist to locate the task.

Format: `mark <task_number>`

### Unmarking a task: `unmark`

Unmarks a marked task in the tasklist. Uses the task's current number in the tasklist to locate the task.

Format: `unmark <task_number>`

### Deleting a task: `delete`

Deletes a task in the tasklist. Uses the task's current number in the tasklist to locate the task.

Format: `delete <task_number>`

### Finding tasks: `find`

Finds all tasks whose description matches the keyword.

Format: `find <keyword>`

### Undoing recent commands: `undo`

Reverses recent command executions.

Format: `undo`

### Redoing recently undone commands: `redo`

Reverses recently undone commands.

Format: `redo`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## FAQ

**Q:** Why is the app called Mummy?\
**A:** I heard someone shouting "Mummy" whilst I was coming up with the app's name.

**Q:** How can I transfer my data to another computer?\
**A:** App data is found under `./data/mummy.txt`. You may copy the file over to the other device under `data`
directory.

## Known issues

1. Date format containing forward slashes `/` do not work when adding tasks, e.g. `11/12/2024`❌.\
   As a workaround, you may choose to use dashes instead, e.g.
   `11-12-2024`✅.

## Command summary

| Action   | Format, Examples                                                                                                  |
|----------|-------------------------------------------------------------------------------------------------------------------|
| List     | `list`                                                                                                            |
| Todo     | `todo [description]`, e.g. `todo submit homework`                                                                 |
| Deadline | `deadline [description] /by <deadline>`, e.g. `deadline homework /by today 2359`                                  |
| Event    | `event [description] /from <from> /to <to>`, e.g. `event F1 night race /from 200924 1800 hrs /to 200924 2200 hrs` |
| Mark     | `mark <task_number>`, e.g. `mark 3`                                                                               |
| Unmark   | `mark <task_number>`, e.g. `unmark 3`                                                                             |
| Delete   | `delete <task_number>`, e.g. `delete 1`                                                                           | 
| Find     | `find <keyword>`, e.g. `find work`                                                                                |
| Undo     | `undo`                                                                                                            |
| Redo     | `redo`                                                                                                            |
| Bye      | `bye`                                                                                                             |

