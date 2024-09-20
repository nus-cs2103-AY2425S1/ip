## Carine User Guide

<!-- Product screenshot goes here -->

## Product Introduction

**Carine** is a task management chatbot designed to help users keep track of their tasks efficiently. With Carine, 
you can add, delete, and mark tasks as complete. Carine also provides reminders for tasks that are overdue or nearing 
their deadlines. 

![Alt text](./Ui.png)
*Example conversation with Carine*
---
## Table of Contents

- [Adding Tasks](#adding-tasks)
    - [Add Todo](#add-todo)
    - [Add Deadline](#add-deadline)
    - [Add Event](#add-event)
- [View Tasks](#view-tasks)
- [Mark and Un-mark Task](#mark-and-un-mark-task)
- [Delete Task](#delete-task)
- [Find Task](#find-task)
- [Exiting Program](#exiting-program)
- [Reset Data File](#reset-data-file)
- [Command Summary](#command-summary)

---
## Adding Tasks

There are three types of tasks you can add: **todo**, **deadline**, and **event**.
A todo is a task without due date, a deadline is a task with a due date and an event is a task with starting 
and end dates.

### Add Todo

**Format:**
todo [Name of Task]

**Examples:**
```
todo Read Book
todo Writing
```
### Add Deadline

**Format:**
deadline [Name of Task] /by [Date/Date and Time]
> **Tip:** Dates are formatted as `dd/MM/yyyy` or `dd/MM/yyyy HH:mm`.
> 
**Examples:**
```
deadline Assignment 1 /by 01/01/2024
deadline Assignment 1 /by 01/01/2024 11:59
```

### Add Event

**Format:**
event [Name of Task] /from [Date/Date and Time] /to [Date/Date and Time]
> **Tip:** Dates are formatted as `dd/MM/yyyy` or `dd/MM/yyyy HH:mm`.
>
**Examples:**
```
event Meeting /from 01/01/2024 14:00 /to 01/01/2024 15:00
event Orientation Camp /from 01/01/2024 /to 03/01/2024
```
---
## View Tasks
Displays all the tasks stored, along with their index, completion status and other details.

**Format:**
list
---
## Mark and Un-mark Task

When tasks are first created, they are set to **not done** as default. This function allows change in the
completion status of tasks, as done or not done.

**Format:**

mark [Index of Task]

unmark [Index of Task]

- Marks or un-marks a task by the specified index.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

**Examples:**
```
mark 1
unmark 2
```
---
## Delete Task

Deletes a task from the tasklist.

**Format:**
delete [Index of Task]

- Deletes the task at the specified index.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

**Examples:**
```
delete 1
```
---
## Find Task
Find a task by searching for a keyword in the task description.

**Format:**
find [Keyword]

> **Tip:** Carine will return all tasks that has name that matches the keyword.
>
**Examples:**
```
find Assignment
find Assignment 1
```
---
## Exit Program
Exits the program.

**Format:**
bye
---
## Reset Data File
If there is an error in data file that stores all the tasks information. Carine will alert the user of the error 
and demands a **RESET** of data file. 

User will not be able to proceed without a reset. After the reset, all of user's previous data will be gone. 

**To reset:**

**Format:**
reset

> **Tip:** After resetting, the program will exit by itself. When user starts the program again, 
> this error should not appear. 
---
## Command Summary
| Action             | Command Format                                                             |
|--------------------|----------------------------------------------------------------------------|
| **Add Todo**       | `todo [Name of Task]`                                                      | 
| **Add Deadline**   | `deadline [Name of Task] /by [Date/Date and Time]`                         | 
| **Add Event**      | `event [Name of Task] /from [Date/Date and Time] /to [Date/Date and Time]` |
| **Mark Task**      | `mark [Index of Task]`                                                     |
| **Un-mark Task**   | `unmark [Index of Task]`                                                   |
| **Delete Task**    | `delete [Index of Task]`                                                   |
| **Find Task**      | `find [Keyword]`                                                           |
| **View All Tasks** | `list`                                                                     |