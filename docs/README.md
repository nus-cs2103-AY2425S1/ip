# ChadGPT User Guide

![Ui.png](Ui.png)

##### Don't be a lowly minion, be a chad! Let ChadGPT help you track all your minion tasks.

- ### [Quick Start](#quick-start)
- ### [Commands](#commands)
  - [Listing all tasks: `list`](#listing-all-tasks)
  - [Adding a todo: `todo`](#adding-todo)
  - [Adding a deadline: `deadline`](#adding-deadlines)
  - [Adding an event: `event`](#adding-events)
  - [Task Marking: `mark`/`unmark`](#task-marking)
  - [Deleting a task: `delete`](#deleting-tasks)
  - [Searching for a task: `search`](#searching-for-tasks)
  - [Undo: `undo`](#undo-command)
  - [Viewing help: `help`](#help-command)
  - [Exit the app: `bye`](#bye-command)

## Quick Start
1. Ensure you have Java 17 installed locally on your computer.
   2. For Mac users, please use the [Java 17 JDK+FX Azul distribution](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest `.jar` file [here](https://github.com/mongj/ip/releases)
3. Copy the file to the desired home directory, and run
```bash
java -jar bot.jar
```
You should see a GUI similar to the one above.
4. Type the command in the command box and press Enter to execute it. You can also type `help` to see a list of available commands and their usages.
Some example commands you can try:
- `todo read book` Adds a todo item `read book` to the task list.
- `list` : Lists all tasks. 
- `mark 1`: Mark the first task as completed.
- `delete 1` : Deletes the first task in the list.
- `bye`: Ends the chat and exits the app.
5. Refer to the section below for details of each command.

## Commands

### Listing all tasks
See a list of all tasks
```
list
```

### Adding todo
Adds a new todo to the task list
```
todo <task>
```
Example:
```
todo eat banana
```
This adds a new todo `eat banana` to the task list

### Adding deadlines
```
deadline <task> /by <date>
```
Example:
```
deadline eat banana /by 2024-11-11
```
This adds a new task `eat banana` with the deadline `2024-11-11`

Note that date input should follow the ISO-8601 calendar system.

### Adding events
```
event <task> /from <date> /to <date>
```
Example:
```
event eat banana /from 2024-01-01 /to 2024-12-31
```
This adds a new event `eat banana` that lasts from `2024-01-01` to `2024-12-31`

Note that date input should follow the ISO-8601 calendar system.

### Task marking
To mark a task as completed:
```
mark <task index>
```

To unmark a task: 
```
unmark <task index>
```
Example:
```
mark 1 
```
This marks the first task in the list as completed.

### Deleting tasks
```
delete <task index>
```
Example:
```
delete 5
```
This deletes the 5th task in the list

### Searching for tasks
```
find <query>
```
Example:
```
find eat banana
```
This will return a list of all tasks that contain the term `eat banana`.

### Undo command
Reverts the last executed command.
```
undo
```

### Help command
See a list of all available commands and their usage.
```
help
```

### Bye command
Exits the chat and closes the app interface.
```
bye
```