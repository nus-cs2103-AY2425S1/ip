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

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer. Check by running the command `java -version` in your terminal.
2. Download the latest calebyyy.jar from [the application's release page](https://github.com/Calebyyy/ip/releases/).
3. Copy the file to the folder you want to use as the home folder for Calebyyy.
4. Run the command `java -jar calebyyy.jar` in your terminal to start the application.


## Command Summary

| Index |   Command to Use    |                   Format                    |                        examples                        |
|:-----:|:-------------------:|:-------------------------------------------:|:------------------------------------------------------:|
|   1   |   Add a Todo task   |            `todo <description>`             |                    `todo sleep`                    |
|   2   | Add a Deadline task |   `deadline <description> /by <d/M/yyyy HHmm>`   |           `deadline exam /by 11/11/2024 1111`            |
|   3   |  Add an Event task  | `event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>` | `event birthday /from 11/11/2024 00:00 /to 11/11/2024 23:59` |
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
Ayy, task added to the grindset! Check it out:
  [T][ ] Plan holiday trip
You are now dripping with rizz: 1 tasks in your epic collection.
```

### Add Deadline task
Adds a Deadline task to your task list.

**Format:** `deadline <description> /by DD/MM/YY HH:MM`

**Example:** `deadline Submit CS2100 Assignment /by 16/09/24 13:00`

**Expected Outcome:**
```
Ayy, task added to the grindset! Check it out:
  [D][ ] Submit CS2100 Assignment (by: 16 Sep 24 13:00)
You are now dripping with rizz: 2 tasks in your epic collection.
```

### Add Event task
Adds an Event task to your task list.

**Format:** `event <description> /from DD/MM/YY HH:MM /to DD/MM/YY HH:MM`

**Example:** `event CS2103T Finals /from 26/11/24 17:00 /to 26/11/24 18:30`

**Expected Outcome:**
```
Ayy, task added to the grindset! Check it out:
  [E][ ] CS2103T Finals (from: 26 Nov 24 17:00 to: 26 Nov 24 18:30)
You are now dripping with rizz: 3 tasks in your epic collection.
```

### List tasks
Displays all tasks currently in your task list, including their status.

**Format:** `list`

**Expected Output:**
```
Yo, here's the list of your goon cave missions, king:
1. [T][ ] Plan holiday trip
2. [D][ ] Submit CS2100 Assignment (by: 16 Sep 24 13:00)
3. [E][ ] CS2103T Finals (from: 26 Nov 24 17:00 to: 26 Nov 24 18:30)
Get that grind on, fam!
```

### Mark task
Marks a task as done in your task list.

**Format:** `mark <task number>`

**Example:** `mark 1`

**Expected Output:**
```
Rizzed up and ready to go! Task has been marked as done like a true sigma:
  [T][X] Plan holiday trip
```

### Unmark task
Unmarks a task that you previously marked as done.

**Format:** `unmark <task number>`

**Example:** `unmark 1`

**Expected Output:**
```
L, blud. I've marked this task as not done yet:
  [T][ ] Plan holiday trip
```

### Delete task
Deletes a task in your task list.

**Format:** `delete <task number>`

**Example:** `delete 1`

**Expected Output:**
```
Alright, I have yeeted this task out of your list:
  [T][ ] Plan holiday trip
You are down to 2 tasks now. Keep hustling, champ!
```

### Find task
Finds all tasks in your task list whose description contains the keyword provided.

**Format:** `find <keyword>`

**Example:** `find assignment`

**Expected Output:**
```
Boom! Check out these epic tasks that match your search quest:
1. [D][ ] Submit CS2100 Assignment (by: 16 Sep 24 13:00)
```

### Help
Displays a list of all available commands with brief descriptions.

**Format:** `help`

**Expected Output:**
```
List of Commands:
1. bye: Exits the program. Use this command when you want to end your session.

2. list: Displays all tasks currently in your task list, including their status (completed or not completed).

3. mark <task number>: Marks the specified task as completed. Replace <task number> with the number of the task you wish to mark.

4. unmark <task number>: Unmarks the specified task, setting it back to not completed. Replace <task number> with the number of the task you wish to unmark.

5. todo <description>: Creates a new todo task with the given description. This command is used to add simple tasks without deadlines.

6. deadline <description> /by <date>: Creates a deadline task with the specified description and a due date. Use /by followed by the date to set when the task should be completed (e.g., deadline Finish report /by 2024-08-31).

7. event <description> /from <date> /to <date>: Creates an event task with the given description and a time frame. Use /from to indicate the start date and /to to indicate the end date (e.g., event Team meeting /from 2024-09-01 /to 2024-09-02).

8. delete <task number>: Removes the specified task from your list. Replace <task number> with the number of the task you want to delete.

9. find <keyword>: Searches your task list for tasks that contain the specified keyword and displays matching tasks.

10. help: Displays a list of all available commands with brief descriptions. Use this command whenever you need a quick reminder of what commands are available.
```

### Bye
Terminates the application and saves all tasks in the task list to a specified text file.

**Format:** `bye`

**Expected Output:**
```
Skedaddling outta here, my dude! See you in the Matrix or when baby Gronk rizzes up Livvy Dunne!
Exiting in 3...2...1...
```