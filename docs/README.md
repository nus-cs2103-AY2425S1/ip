# Jay User Guide

![Jay](Ui.png)

Jay is a tasks managing assistant bot, optimized for use via a Command Line Interface (CLI). 
If you can type fast, Jay can help you manage your tasks faster than traditional GUI apps.

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
    1. [Adding a todo task: `todo`](#1-adding-a-todo-task-todo)
    2. [Adding a deadline task: `deadline`](#2-adding-a-deadline-task-deadline)
    3. [Adding an event task: `event`](#3-adding-an-event-task-event)
    4. [Listing all tasks: `list`](#4-listing-all-tasks-list)
    5. [Marking a task as done: `mark`](#5-marking-a-task-as-done-mark)
    6. [Marking a task as undone: `unmark`](#6-marking-a-task-as-undone-unmark)
    7. [Deleting a task: `delete`](#7-deleting-a-task-delete)
    8. [Finding tasks by keyword: `find`](#8-finding-tasks-by-keyword-find)
    9. [Setting a priority to a task: `set`](#9-setting-a-priority-to-a-task-set)
    10. [Viewing help: `help`](#10-viewing-help-help)
    11. [Exiting the program: `bye`](#11-exiting-the-program-bye)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `jay.jar` from [here](https://github.com/tckeong/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for Jay.
4. Use the command `java -jar jay.jar` to start the app. The GUI similar to the above should appear in a few seconds.

## Features
### 1. Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo [DESCRIPTION]`

Example: 
```
todo read book
```

Jay will show:
```
Got it. I've added this task:
[T][ ] read book { Priority: Low }
Now you have 1 tasks in the list.
```

### 2. Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline [DESCRIPTION] /by [DATE]`

Example:
```
deadline return book /by 30-09-2024
```

Jay will show:
```
Got it. I've added this task:
[D][ ] return book { Priority: Low } (by: 30 Sep 2024)
Now you have 2 tasks in the list.
```

### 3. Adding an event task: `event`
Adds an event task to the task list.

Format: `event [DESCRIPTION] /from [DATE] [TIME] /to [TIME]`

Example:
```
event project meeting /from 30-09-2024 1400 /to 1600
```

Jay will show:
```
Got it. I've added this task:
[E][ ] project meeting { Priority: Low } (from: 30 Sep 2024 02:00 PM to: 04:00 PM)
Now you have 3 tasks in the list.
```

### 4. Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

Example:
```
list
```

Jay will show:
```
Here are the tasks in your list:
1. [T][ ] read book { Priority: Low }
2. [D][ ] return book { Priority: Low } (by: 30 Sep 2024)
3. [E][ ] project meeting { Priority: Low } (from: 30 Sep 2024 02:00 PM to: 04:00 PM)
```

### 5. Marking a task as done: `mark`
Marks a task as done in the task list.

Format: `mark [TASK INDEX]`

Example:
```
mark 2
```

Jay will show:
```
Nice! I've marked this task as done:
[D][X] return book { Priority: Low } (by: 30 Sep 2024)
```

### 6. Marking a task as undone: `unmark`
Marks a task as undone in the task list.

Format: `unmark [TASK INDEX]`

Example:
```
unmark 2
```

Jay will show:
```
Ok, I've marked this task as not done yet:
[D][ ] return book { Priority: Low } (by: 30 Sep 2024)
```

### 7. Deleting a task: `delete`
Deletes a task from the task list.

Format: `delete [TASK INDEX]`

Example:
```
delete 2
```

Jay will show:
```
Noted. I've removed this task:
[D][ ] return book { Priority: Low } (by: 30 Sep 2024)
Now you have 2 tasks in the list.
```

### 8. Finding tasks by keyword: `find`
Finds tasks in the task list by keyword.

Format: `find [KEYWORD]`

Example:
```
find book
```

Jay will show:
```
Here are the matching tasks in your list:
1. [T][ ] read book { Priority: Low }
```

### 9. Setting a priority to a task: `set`
Sets a priority to a task in the task list.

Format: `set [TASK INDEX] [PRIORITY]`

Example:
```
set 2 high
```

Jay will show:
```
Got it. I've set the priority of this task to high:
[E][ ] project meeting { Priority: High } (from: 30 Sep 2024 02:00 PM to: 04:00 PM)
```

### 10. Viewing help: `help`
Shows the help message.

Format: `help`

### 11. Exiting the program: `bye`
Exits the program.
Format: `bye`

## FAQ
Q: How do I transfer my data to another Computer?

A: You can just replace the data folder in the new computer with the data folder in the old computer.

## Command Summary
| Command  | Format                                               | Example                                                |
|----------|------------------------------------------------------|--------------------------------------------------------|
| todo     | `todo [DESCRIPTION]`                                 | `todo read book`                                       |
| deadline | `deadline [DESCRIPTION] /by [DATE]`                  | `deadline return book /by 30-09-2024`                  |
| event    | `event [DESCRIPTION] /from [DATE] [TIME] /to [TIME]` | `event project meeting /from 30-09-2024 1400 /to 1600` |
| list     | `list`                                               | `list`                                                 |
| mark     | `mark [TASK INDEX]`                                  | `mark 2`                                               |
| unmark   | `unmark [TASK INDEX]`                                | `unmark 2`                                             |
| delete   | `delete [TASK INDEX]`                                | `delete 2`                                             |
| find     | `find [KEYWORD]`                                     | `find book`                                            |
| set      | `set [TASK INDEX] [PRIORITY]`                        | `set 2 high`                                           |
| help     | `help`                                               | `help`                                                 |
| bye      | `bye`                                                | `bye`                                                  |


#### Have fun managing your tasks with Jay! 🎉

