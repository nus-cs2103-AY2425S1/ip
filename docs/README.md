# D++E User Guide

## Content Navigation
- [D++E User Guide](#de-user-guide)
  - [Content Navigation](#content-navigation)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Add](#add)
    - [List](#list)
    - [Mark](#mark)
    - [Unmark](#unmark)
    - [Delete](#delete)
    - [Find](#find)
    - [Exit](#exit)
    - [Saving Data](#saving-data)
  - [FAQ](#faq)
  - [Command Summary](#command-summary)



## Quick Start
    1. Ensure you have Java 17 or above installed on your computer.
    2. Download the latest version of D++E from [here](https://github.com/kaoxi998533/ip/releases).
    3. Navigate to the directory containing the downloaded JAR file with `cd` and run the command `java -jar d_plus_plus_e.jar` to start the application.

## Features

### Add
Use the command `todo` followed by the task description, or `deadline` followed by the task description and the deadline, or `event` followed by the task description and the event start date and end date.

### List
Use the command `list` to list all tasks.

### Mark
Use the command `mark` followed by the task number to mark the task as done.

### Unmark
Use the command `unmark` followed by the task number to mark the task as not done.

### Delete
Use the command `delete` followed by the task number to delete the task.

### Find
Use the command `find` followed by the keyword to find tasks containing the keyword.

### Exit
Use the command `bye` to exit the application.

### Saving Data
D++E data are saved in the `[Jar file location]/data/d_plus_plus_e.txt` file.

## FAQ
**Q**: Is it possible to add a task without using the `todo`, `deadline`, or `event` command?

**A**: No, the current version of D++E does not support adding tasks without using the `todo`, `deadline`, or `event` command and an error message will be shown.

## Command Summary

Feature | Command | Format | Description
--- | --- | --- | ---
Add | `todo` | `todo TASK_DESCRIPTION` | Adds a todo task
Add | `deadline` | `deadline TASK_DESCRIPTION /by DEADLINE` | Adds a deadline task
Add | `event` | `event TASK_DESCRIPTION /from START_DATE /to END_DATE` | Adds an event task
List | `list` | `list` | Lists all tasks
Mark | `mark` | `mark TASK_NUMBER` | Marks a task as done
Unmark | `unmark` | `unmark TASK_NUMBER` | Marks a task as not done
Delete | `delete` | `delete TASK_NUMBER` | Deletes a task
Find | `find` | `find KEYWORD` | Finds tasks containing the keyword
Exit | `bye` | `bye` | Exits the application



