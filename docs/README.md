<h1 align="center">Calebyyy User Guide</h1>

## Product Overview
Calebyyy is a task management application that helps you keep track of your daily tasks. It is designed to be simple and easy to use, allowing you to add, delete, mark and find tasks with ease. Calebyyy is a CLI (Command Line Interface) application, which means you interact with it using text commands. This user guide will provide you with a detailed overview of the various commands and features available in Calebyyy.

<p align="center">
<img width="520" src="./Ui.png" alt="main view of application">
</p>

- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
    * [Creating Tasks](#creating-tasks)
        * [Add Todo task](#add-todo-task)
        * [Add Deadline task](#add-deadline-task)
        * [Add Event task](#add-event-task)
    * [List Tasks](#list-tasks)
    * [Mark Task](#mark-task)
    * [Unmark Task](#unmark-task)
    * [Delete Task](#delete-task)
    * [Find Task](#find-task)
    * [Bye](#bye)
    * [Detect Duplicate Tasks](#detect-duplicate-tasks)

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer. Check by running the command `java -version` in your terminal.
2. Download the latest calebyyy.jar from [the application's release page](https://github.com/Calebyyy/ip/releases/).
3. Copy the file to the folder you want to use as the home folder for Calebyyy.
4. Run the command `java -jar calebyyy.jar` in your terminal to start the application.


## Command Summary

| Index |   Command to Use    |                   Format                    |                        examples                        |
|:-----:|:-------------------:|:-------------------------------------------:|:------------------------------------------------------:|
|   1   |   Add a Todo task   |            `todo <description>`             |                    `todo sleep`                    |
|   2   | Add a Deadline task |   `deadline <description> /by d/M/yyyy HHmm`   |           `deadline exam /by 11/11/2024 1111`            |
|   3   |  Add an Event task  | `event <description> /from d/M/yyyy HHmm /to d/M/yyyy HHmm` | `event birthday /from 11/11/2024 0000 /to 11/11/2024 2359` |
|   4   |    Delete a task by number (1-indexed)   |               `delete <task>`               |                       `delete 1`                       |
|   5   |    Find task(s) by keywords     |              `find <keyword>`               |                       `find exam`                        |
|   6   |     List tasks      |                   `list`                    |                         `list`                         |
|  10   |     Mark a task as done by number(1-indexed)     |                `mark <task>`                |                        `mark 1`                        |
|  11   |    Unmark a task as undone by number(1-indexed)  |               `unmark <task>`               |                       `unmark 1`                       |
|  14   |  End session  |                    `bye`                    |                         `bye`                          |


## Features

### Creating Tasks

### Add Todo task
Adds a Todo task to your task list.

**Format:** `todo <description>`

**Example:** `todo sleep`

**Expected Outcome:**
```
Got it. I've added this task:
   [T][] sleep
Now you have 1 tasks in the list.
```

### Add Deadline task
Adds a Deadline task to your task list.

**Format:** `deadline <description> /by d/M/yyyy HHmm`

**Example:** `deadline exam /by 11/11/2024 1111`

**Expected Outcome:**
```
Got it. I've added this task:
   [D][] exam (by: Nov 11 2024, 11:11am)
Now you have 2 tasks in the list.
```

### Add Event task
Adds an Event task to your task list.

**Format:** `event <description> /from DD/MM/YY HH:MM /to DD/MM/YY HH:MM`

**Example:** `event birthday /from 11/11/2024 0000 /to 11/11/2024 2359`

**Expected Outcome:**
```
Got it. I've added this task:
   [E][] birthday (from: Nov 11 2024, 12:00am to: Nov 11 2024, 11:59pm)
Now you have 3 tasks in the list.
```

### List tasks
Displays all tasks currently in your task list, including their status.

**Format:** `list`

**Expected Output:**
```
Here are the tasks in your list:
1. [T][ ] sleep
2. [D][ ] exam (by: Nov 11 2024, 11:11am)
3. [E][ ] birthday (from: Nov 11 2024, 12:00am to: Nov 11 2024, 11:59pm)
```

### Mark task
Marks a task as done in your task list.

**Format:** `mark <task number>`

**Example:** `mark 1`

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] sleep
```

### Unmark task
Unmarks a task that you previously marked as done.

**Format:** `unmark <task number>`

**Example:** `unmark 1`

**Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] sleep
```

### Delete task
Deletes a task in your task list.

**Format:** `delete <task number>`

**Example:** `delete 1`

**Expected Output:**
```
Noted. I've removed this task:
  [T][ ] sleep
Now you have 2 tasks in the list.
```

### Find task
Finds all tasks in your task list whose description contains the keyword provided.

**Format:** `find <keyword>`

**Example:** `find sleep`

**Expected Output:**
```
Here are the matching tasks in your list:
1. [T][ ] sleep
```

### Bye
Terminates the application and saves all tasks in the task list to a specified text file.

**Format:** `bye`

**Expected Output:**
```
Bye. Hope to see you again soon!
```

### Detect duplicate tasks
Detects if you are trying to add a task that is already in the task list.

**Example:** `todo sleep`

**Expected Output:** 
```
OOPS!!! Brother you already have this task!!!
```
