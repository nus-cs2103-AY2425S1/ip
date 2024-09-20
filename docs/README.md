# ChadGPT User Guide

![Ui.png](Ui.png)

##### Don't be a lowly minion, be a chad! Let ChadGPT help you track all your minion tasks.

- ### [Quick Start](#quick-start)
- ### [Commands](#commands)
  - [Listing all tasks: `list`](#1-listing-all-tasks)
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

### 1. Listing all tasks
You can list all the tasks using the command:
```
list
```
This will display a list of tasks in the following format:
```
[type][status] <description> (timeframe)
```
- **type**: `T` for todo, `D` for deadline, `E` for event
- **status**: `X` for completed task and empty otherwise

**Example output:**
```
1. [T][] eat banana
2. [D][X] eat 10 bananas (by:2024-09-25)
3. [E][] banana eating competition (from: 2024-09-26 to: 2024-09-27)
```

---

### 2. Adding todo
Adds a new todo to the task list
```
todo <task>
```
**Example Usage:**
```
todo eat banana
```
This adds a new todo `eat banana` to the task list

---

### 3. Adding deadlines
```
deadline <task> /by <date>
```
**Date format:** `YYYY-MM-DD` e.g. `2024-09-25`
(We follow the ISO-8601 calendar system)

**Example Usage:**
```
deadline eat 10 bananas /by 2024-09-25
```
This adds a new task `eat 10 bananas` with the deadline `2024-09-25`

---

### 4. Adding events
```
event <task> /from <date> /to <date>
```
**Date format:** `YYYY-MM-DD` e.g. `2024-09-25`
(We follow the ISO-8601 calendar system)

**Example Usage:**
```
event eat banana /from 2024-01-01 /to 2024-12-31
```
This adds a new event `eat banana` that lasts from `2024-01-01` to `2024-12-31`

Note that date input should follow the ISO-8601 calendar system.

---

### 5. Task marking
To mark a task as completed:
```
mark <task index>
```

To unmark a task: 
```
unmark <task index>
```
**Example Usage:**
```
mark 1 
```
This marks the first task in the list as completed.

---

### 6. Deleting tasks
```
delete <task index>
```
**Example Usage:**
```
delete 5
```
This deletes the 5th task in the list

---

### 7. Searching for tasks
You can find all tasks that contain a specific word/phrase by using
```
find <query>
```
**Example Usage:**
```
find eat banana
```
**Example Output:**
```
[T][] eat banana
[D][] eat banana contest (by: 2024-09-25)
```

---

### 8. Undo command
Reverts the last executed command.
```
undo
```

---

### 9. Help command
See a list of all available commands and their usage.
```
help
```

---

### 10. Bye command
Exits the chat and closes the app interface.
```
bye
```