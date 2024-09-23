# MySutong User Guide

![img.png](Ui.png)

Welcome to **MySutong**! MySutong is a personal chatbot that helps you keep track of your daily tasks. You can add
different types of tasks, mark them as done, or tag them for easy reference. Sammy can also help you manage deadlines
and events.
---
## Quick start

1. Ensure you have Java 17 or above installed in your computer. You can install it from [here](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
2. Download the latest `.jar` file from [here](https://github.com/yu-sutong/ip/releases)
3. Copy the file to the folder you want to use as the *home folder* of your task list.
4. Open a command terminal, `cd` into the folder you put the jar file in, and type `java -jar mysutong.jar`  then press `enter` to run the application.
5. Type your command in the text field at the bottom and press `Enter` or "Send" to execute it.
## Features

## 1. Add a Task: `todo`
Adds a simple task to your task list.

Format: todo `TASK_DESCRIPTION`

Example:
```
todo CS2103T IP
```

Parameter:
- TASKDESCRIPTION is compulsory and must be a non-empty string
---
## 2. Adding a Task with a Deadline: `deadline`

Adds a task with a deadline to your task list.

Format: `deadline TASK_DESCRIPTION /by DATE`

Example:
```
deadline submit CS2103T IP /by 23/9/2024 2359
```

Parameter:
- TASK_DESCRIPTION and DATE are compulsory and must be a non-empty string.
- DATE must be in the form of dd/mm/yyyy where dd and mm can be a single or double digits.
---
## 3. Adding an Event: `event`

Adds an event with a start and end date/time to your task list.

Format: `event TASK_DESCRIPTION /from DATE /to DATE`

Example:
```
event project meeting /from 23/9/2024 14:00 /to 23/9/2024 16:00
```

Parameter:
- TASK_DESCRIPTION and DATE are compulsory and must be a non-empty string.
- DATE must be in the form of dd/mm/yyyy where dd and mm can be a single or double digits.
---
## 4. Listing All Tasks: `list`

Displays all the tasks in your task list.

Format: `list`

Example:
```
list
```
---
## 5. Deleting a Task: `delete`

Deletes a task from your task list.

Format: `delete INDEX`
- `INDEX` refers to the position of the task in the list, starting from 1.

Example:
```
delete 2
```
This deletes the 2nd task in your list.

Parameter:
- INDEX must be a positive integer and less than or equal to the total number of tasks in your list.
- INDEX starts from 1 (the first task on the list).
---
## 6. Marking a Task as Done: `mark`

Marks a task as done.

Format: `mark INDEX`

Example:
```
mark 2
```
This marks the 2nd task in your list as done.

Parameter:
- INDEX must be a positive integer and less than or equal to the total number of tasks in your list.
- INDEX starts from 1 (the first task on the list).
---
## 7. Unmarking a Task: `unmark`

Unmarks a task, making it incomplete again.

Format: `unmark INDEX`

Example:
```
unmark 2
```
This unmarks the 2nd task, making it incomplete.

Parameter:
- INDEX must be a positive integer and less than or equal to the total number of tasks in your list.
- INDEX starts from 1 (the first task on the list).
---
## 8.  Find matching tasks: `find`

Find matching tasks in your list based on the keyword.

Format: `find KEYWORD`

Example:
```
find assignment
```

Parameter:
- KEYWORD must be a non-empty string.
---
## 9. Giving your task a priority: `priority`

Give your task (based on the INDEX) a priority tag (based on PRIORITY_NO.
When PRIORITY_NO = 1, the tag is "High".
When PRIORITY_NO = 2, the tag is "Medium".
When PRIORITY_NO = 3, the tag is "Low".
The default tag for each task if "Low".

Format: `priority INDEX PRIORITY_NO`

Example:
```
priority 2 1
```

Parameter:
- INDEX must be a positive integer and less than or equal to the total number of tasks in your list.
- INDEX of the task starts from 1.
- PRIORITY_NO must be a integer from 1 to 3.
## 10. Exiting the Program: `bye`

Exits the chatbot.

Format: `bye`

Example:
```
bye
```
---

## Command Summary
| Action                         | Format, Examples                                                                                                              |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------|
| **Add a Task**                 | `todo TASK_DESCRIPTION`<br/>Example: `todo read a book`                                                                       |
| **Add a Task with a Deadline** | `deadline TASK_DESCRIPTION /by DATE`<br/>Example: `deadline submit assignment /by 2024-09-30`                                 |
| **Add an Event**               | `event TASK_DESCRIPTION /from DATE /to DATE`<br/>Example: `event project meeting /from 2024-09-21 14:00 /to 2024-09-21 16:00` |
| **List Tasks**                 | `list`<br/>Example: `list`                                                                                                    |
| **Delete a Task**              | `delete INDEX`<br/>Example: `delete 3`                                                                                        |
| **Mark a Task**                | `mark INDEX`<br/>Example: `mark 2`                                                                                            |
| **Unmark a Task**              | `unmark INDEX`<br/>Example: `unmark 2`                                                                                        |
| **Finding matching tasks**     | `find KEYWORD`<br/>Example: `find assignment`                                                                                 |
| **Giving task a priority**     | `priority INDEX PRIORITY_NO`<br/>Example: `priority 2 1`                                                                      |
| **Exit**                       | `bye`<br/>Example: `bye`                                                                                                      |
