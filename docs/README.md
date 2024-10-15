<h1 align="center">SeanBot User Guide</h1>

## Product Overview
SeanBot is a task management application that helps you keep track of your daily tasks. Designed to be used for anyone, SeanBot allows you to add, delete, mark, and find tasks efficiently. SeanBot has both a Command Line Interface (CLI) application and a Graphical User Interface (GUI) as well. 

<p align="center">
<img width="520" src="./Ui.png" alt="mapplication demo">
</p>

- [Quick Start](#quick-start)
- [Features](#features)
    * [Creating Tasks](#creating-tasks)
        * [Add Todo Task](#add-todo-task)
        * [Add Deadline Task](#add-deadline-task)
        * [Add Event Task](#add-event-task)
    * [List Tasks](#list-tasks)
    * [Mark Task](#mark-task)
    * [Unmark Task](#unmark-task)
    * [Delete Task](#delete-task)
    * [Find Task](#find-task)
    * [Bye](#bye)
- [Command Summary](#command-summary)


## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest SeanBot.jar from [the application's release page](https://github.com/seanwong2/ip/releases/).
3. Copy the file to the folder you want to use as the home folder for SeanBot.
4. Run the command `java -jar SeanBot.jar` in your terminal to start the application.

## Features

### Creating Tasks

#### Add Todo Task
Adds a Todo task to your task list.

**Format:** `t <description>`

**Example:** `t borrow book`

**Expected Outcome:**
```
Got it. I've added this task: 
 [T][ ] borrow book 
Now you have 1 task in the list.
```

#### Add Deadline Task
Adds a Deadline task to your task list.

**Format:** `dl <description> /by yyyy-MM-dd`

**Example:** `dl return book /by 2024-08-25`

**Expected Outcome:**
```
Got it. I've added this task: 
 [D][ ] exam (by: Aug 25 2024) 
 Now you have 2 tasks in the list.
```

#### Add Event Task
Adds an Event task to your task list.

**Format:** `e <description> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`

**Example:** `e project meeting /from 2024-08-26 14:00 /to 2024-08-26 16:00`

**Expected Outcome:**
```
Got it. I've added this task: 
 [E][ ] project meeting (from: Aug 26 2024, 14:00 to: Aug 26 2024, 16:00) 
Now you have 3 tasks in the list.
```

### List Tasks
Displays all tasks currently in your task list, including their status.

**Format:** `l`

**Expected Outcome:**
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] exam (by: Aug 25 2024) 
3. [E][ ] project meeting (from: Aug 26 2024, 14:00 to: Aug 26 2024, 16:00) 
```

### Mark Task
Marks a task as done in your task list.

**Format:** `m <task number>`

**Example:** `m 1`

**Expected Outcome:**
```
Nice! I've marked this task as done: 
 [T][X] borrow book
```

### Unmark Task
Unmarks a task that you previously marked as done.

**Format:** `um <task number>`

**Example:** `um 1`

**Expected Outcome:**
```
OK, I've marked this task as not done yet: 
 [T][ ] borrow book
```

### Delete Task
Deletes a task in your task list.

**Format:** `del <task number>`

**Example:** `del 1`

**Expected Outcome:**
```
Noted. I've removed this task: 
 [T][ ] borrow book
Now you have 2 tasks in the list.
```

### Find Task
Finds all tasks in your task list whose description contains the keyword provided.

**Format:** `f <keyword>`

**Example:** `f project meeting`

**Expected Outcome:**
```
Here are the matching tasks in your list:
1. [E][ ] project meeting (from: Aug 26 2024, 14:00 to: Aug 26 2024, 16:00) 
```

### Bye
Replies with a goodbye message.

**Format:** `b`

**Expected Outcome:**
```
Bye. Hope to see you again soon!
```

## Command Summary

| Index |   Command to Use    |                   Format                    |                        examples                        |
|:-----:|:-------------------:|:-------------------------------------------:|:------------------------------------------------------:|
|   1   |   Add a Todo task   |            `y <description>`             |                    `t borrow book`                    |
|   2   | Add a Deadline task |   `dl <description> /by yyyy/mm/dd `   |           `dl return book /by 2024-08-25 `            |
|   3   |  Add an Event task  | `e <description> /from yyyy/mm/dd HHmm /to yyyy/mm/dd HHmm` | `e project meeting /from 2024-08-26 14:00 /to 2024-08-26 16:00` |
|   4   |    Delete a task    |               `del <task>`               |                       `del 1`                       |
|   5   |    Find task        |              `f <keyword>`               |                       `f exam`                        |
|   6   |     List tasks      |                   `l`                    |                         `l`                         |
|  10   |     Mark a task as done |                `m <task>`                |                        `m 1`                        |
|  11   |    Unmark a task as undone |               `um <task>`               |                       `um 1`                       |
|  14   |  End   |                    `b`                    |                         `b`                          |
