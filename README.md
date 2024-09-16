# Casper Chat User Guide

This is my individual project for CS2103T Software Engineering. 

## Introduction

Casper is an application designed to manage tasks efficiently through a Command Line Interface (CLI). It supports 
commands for task creation, modification, sorting, and listing tasks. The app is built to handle various types of 
tasks like `todo`, `deadline`, and `event`, providing comprehensive task management capabilities.

---
## Quick Start
#### 1. Ensure Java 17 or higher is installed on your computer.
#### 2. Download the latest `casper.jar` file from [here](#).
#### 3. Navigate to the folder where you downloaded the `.jar` file.
#### 4. Run the application using the following command:
```bash
java -jar casper.jar
```
#### 5. A command prompt will open, allowing you to enter commands such as `list`, `sort`, `find`, and more. 
Here are some example commands you can try:
- `list`: Lists all tasks.
- `todo buy milk`: Adds a new todo task with the description "buy milk".
- `mark 1`: Marks the second task as complete.
#### 6. Refer to the Features section below for more details about each command.

---

## Features

### Command Format
- Words surrounded by `[square brackets]` are the parameters to be supplied by the user.  
  For example: `todo [description]` requires a **description**.
- Commands like `list` do not require additional parameters.

### Available Commands:

#### 1. `help`
Displays a list of all available commands.
```bash
help
```

#### 2. `list`
Lists all tasks in the current task list.
```bash
list
```

#### 3. `sort /by [method]`
Sorts the task list by the specified method.

Available methods:
- `alphabetical`: Sorts tasks by alphabetical order.
- `type`: Sorts tasks by task type.
- `time`: Sorts tasks by their date or time.
- `status`: Sorts tasks by their completion status.

Example:
```bash
sort /by alphabetical
```

#### 4. `mark [task index]`
Marks a task as complete.

Example:
```bash
mark 2
```

#### 5. `unmark [task index]`
Unmarks a task, indicating it is incomplete.

Example:
```bash
unmark 2
```

#### 6. `delete [task index]`
Deletes a task from the task list.

Example:
```bash
delete 3
```

#### 7. `find [keyword]`
Finds tasks containing the specified keyword.

Example:
```bash
find homework
```

#### 8. `todo [description]`
Adds a new todo task.

Example:
```bash
todo Buy milk
```

#### 9. `deadline [description] /by [date]`
Adds a new deadline task with a specific date.

Example:
```bash 
deadline Submit assignment /by 2024-09-20
```

#### 10. `event [description] /from [start time] /to [end time]`
Adds a new event task with start and end times.

Example:
```bash 
event Attend conference /from 2024-09-20 10:00 /to 2024-09-20 12:00`
```

---

## Saving and Editing Data

- **Data is saved automatically** after every command that modifies the task list.
- Data is stored in a file `casper.txt` in the application's `/data` directory.
- You can manually edit the `casper.txt` file, but be cautious as incorrect formats can lead to errors or data loss.

---
