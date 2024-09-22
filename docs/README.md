# Sammy User Guide

![img.png](img.png)

Welcome to **Sammy**! Sammy is a personal chatbot that helps you keep track of your daily tasks. You can add
different types of tasks, mark them as done, or tag them for easy reference. Sammy can also help you manage deadlines 
and events.

## Features

### Command Format
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
    - Example: In `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter to be provided by the user.
- Items in square brackets `[ ]` are optional.
    - Example: `deadline TASK_DESCRIPTION /by DATE` can be used with an optional date.
- Parameters can be in any order.
    - Example: `event TASK_DESCRIPTION /from DATE /to DATE` is valid even if `/to DATE` and `/from DATE` are switched.
- Extraneous parameters for commands that do not take any parameters (e.g., `list`, `bye`) will be ignored.

---

## 1. Add a Task: `todo`
Adds a simple task to your task list.

Format: todo `TASK_DESCRIPTION`

Example: 
```
todo read a book
```

---
## 2. Adding a Task with a Deadline: `deadline`

Adds a task with a deadline to your task list.

Format: `deadline TASK_DESCRIPTION /by DATE`

Example:
```
deadline submit assignment /by 2024-09-30
```
---
## 3. Adding an Event: `event`

Adds an event with a start and end date/time to your task list.


Format: `event TASK_DESCRIPTION /from DATE /to DATE`

Example:
```
event project meeting /from 2024-09-21 14:00 /to 2024-09-21 16:00
```
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
- `INDEX` refers to the position of the task in the list (starting from 1).

Example:
```
delete 3
```
This deletes the 3rd task in your list.

---
## 6. Marking a Task as Done: `mark`

Marks a task as done.

Format: `mark INDEX`
- `INDEX` refers to the position of the task in the list.

Example:
```
mark 2
```
This marks the 2nd task as done.

---
## 7. Unmarking a Task: `unmark`

Unmarks a task, making it incomplete again.

Format: `unmark INDEX`
- `INDEX` refers to the position of the task in the list.

Example:
```
unmark 2
```
This unmarks the 2nd task, making it incomplete.

---
## 8.  Tagging a Task: `tag`

Tags a task with one or more tags.

Format: `tag INDEX #TAG`
- `INDEX` refers to the position of the task in the list.
- `#TAG` is the tag you want to add to the task.

Example:
```
tag 1 #important
```
This tags the 1st task with `#important`.

---
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
| **Tag a Task**                 | `tag INDEX #TAG`<br/>Example: `tag 1 #important`                                                                              |
| **Exit**                       | `bye`<br/>Example: `bye`                                                                                                      |
