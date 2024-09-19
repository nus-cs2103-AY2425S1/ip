# R2D2 User Guide

**R2D2** is your go-to chatbot to **keep track of all your tasks**, optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface** (GUI).

  * [Features](#features)
    + [Viewing help: `help`](#viewing-help)
    + [Adding Tasks to your list](#adding-tasks-to-your-list)
    + [1. Todo tasks](#1-todo-tasks)
    + [2. Deadline tasks](#2-deadline-tasks)
    + [3. Event tasks](#3-event-tasks)
    + [Viewing your list: `list`](#viewing-your-list)
    + [Finding matching tasks: `find`](#finding-matching-tasks)
    + [Marking a task as done: `mark`](#marking-a-task-as-done)
    + [Unmarking a task as incomplete: `unmark`](#unmarking-a-task-as-incomplete)
    + [Deleting a task: `delete`](#deleting-a-task)
  * [Command Summary](#command-summary)


## Features

### Viewing help

Upon typing **help**, displays all the available commands in R2D2 the user can utilise to keep track of the to-do list.

Format: `help`

### Adding Tasks to your list

### 1. Todo tasks

Format: `todo TASK_NAME`

Adds a task of type todo to the list with the specified TASK_NAME

Examples:
- `todo finish my CS2100 homework`
- `todo clear the trash`

### 2. Deadline tasks

Format: `deadline TASK_NAME /by DEADLINE_DATE`

Adds a task of type deadline to the list with the specified TASK_NAME and the deadline date as specified. Take note that the deadline date should be in the format **"dd/mm/yyyy HHmm"**.

Examples:
- `deadline submit application /by 29/09/2024 1300`
- `deadline finish assignment 1 /by 12/10/2024 2359`

### 3. Event tasks

Format: `event TASK_NAME /from START_DATETIME /to END_DATETIME`

Adds a task of type event to the list with the specified TASK_NAME and the starting datetime to the ending datetime as specified by the user. The format for the datetime should be **"dd/mm/yyyy HHmm"**.

Examples:
- `event Supernova /from 11/09/2024 1300 /to 11/09/2024 2200`
- `event Bruno Mars Concert /from 03/03/2024 1800 /to 03/03/2024 2200`

### Viewing your list

Format: `list`

To view your list so far, use the command `list` to display the current list with all the tasks added before.

<p align="center">
<img width="416" alt="Screenshot 2024-09-19 at 11 32 07" src="https://github.com/user-attachments/assets/e2501925-8a01-45a9-93cb-34737c3824f1">
</p>

### Finding matching tasks

Format: `find KEYWORD`

To find a task which contains the KEYWORD specified by the user, type in the command `find KEYWORD` where KEYWORD is the user input.

<p align="center">
<img width="413" alt="Screenshot 2024-09-19 at 11 38 00" src="https://github.com/user-attachments/assets/d6ab9005-f4ed-41d9-a4a4-74ecb6f4e204">
</p>

### Marking a task as done

Format: `mark INDEX`

Type `mark INDEX` with the number index of the task you want to complete.

Examples:
- `mark 2`
- `mark 1`
  
<p align="center">
<img width="415" alt="Screenshot 2024-09-19 at 11 40 16" src="https://github.com/user-attachments/assets/d717fb4b-3cc2-44b8-bbde-5e3596aa35bc">
</p>

### Unmarking a task as incomplete

Format: `unmark INDEX`

Type `unmark INDEX` with the number index of the task you want to unmark.

Examples:
- `unmark 1`
- `unmark 2`
  
<p align="center">
<img width="416" alt="Screenshot 2024-09-19 at 11 41 49" src="https://github.com/user-attachments/assets/0211c3bd-8f00-4284-9a07-737001e863e5">
</p>

### Deleting a task

Format: `delete INDEX`

Type `delete INDEX` with the number index of the task you want to delete.

Examples:
- `delete 1`
- `delete 2`
  
<p align="center">
<img width="481" alt="Screenshot 2024-09-19 at 11 44 03" src="https://github.com/user-attachments/assets/daabf3ed-8912-45eb-9733-b6dbc1fbd9ce">
</p>


## Command Summary

| Command | Format | Example |
| :---: | :---: | :---: |
| `help` | `help` | `help` will display a help page with all the commands available |
| `todo` | `todo TASK_NAME` | `todo watch CS2103T lecture videos` adds "watch CS2103T lecture videos" to the list |
| `deadline` | `deadline TASK_NAME /by DEADLINE_DATETIME` | `deadline submit application /by 29/09/2024 1300` |
| `event` | `event TASK_NAME /from START_DATETIME /to END_DATETIME` | `event Supernova /from 11/09/2024 1300 /to 11/09/2024 2200` |
| `list` | `list` | `list` displays the current todo list |
| `find` | `find KEYWORD` | `find assignment` displays all the tasks with "assignment" in it |
| `mark` | `mark INDEX` | `mark 2` marks the 2nd item in the list as done |
| `unmark` | `unmark INDEX` | `unmark 2` marks the 2nd item in the list as incomplete |
| `delete` | `delete 2` | `delete 2` deletes the 2nd item in the list |
