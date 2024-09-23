# Elysia User Guide

<img src="Ui.png" alt="Screenshot of Elysia's UI" width="1272" />

### Notes about the command format:
- Words in ``UPPER_CASE`` are the parameters to be supplied by the user.
   - e.g. in todo TASK, TASK is a parameter which can be used as todo read book.
- For deadline command and event command, the input date will be assumed to be in the current year.
    - For example, "23 Sep" will be interpreted as "23 Sep 2024," since it is currently 2024.
- Enter the year explicitly if the date is not in the current year, such as "23 Sep 2025."
- Elysia does not allow tasks with deadlines in the past or events that start in the past.

> [!IMPORTANT]

> Use "23 Sep" as the input date instead of "23Sep" for deadline and event commands.


## Features
- [Add Todos](#add-todos)
- [Add Deadlines](#add-deadlines)
- [Add Events](#add-events)
- [List all](#list-all)
- [Clear all](#clear-all)
- [Mark task](#mark-task)
- [Unmark task](#unmark-task)
- [Delete task](#delete-task)
- [Find task](#find-task)
- [Call Elysia by her name](#elysia)
- [Exit](#exit-1)

---
### Add Todos

<a name="add-todos"></a>

Adds a todo to the task list.

Format: ``todo TASK``

Examples:
- todo read book

---

### Add Deadlines

<a name="add-deadlines"></a>

Adds a deadline to the task list.

Format: ``deadline TASK /by DATE``

> [!NOTE]

> The month and "by" are case-sensitive.
> - The input date will be assumed to be in the current year.
>   - For example, "23 Sep" will be interpreted as "23 Sep 2024," since it is currently 2024.
> - Enter the year explicitly if the date is not in the current year, such as "23 Sep 2025."

> [!TIP]

> - mon, tue, wed, ... will be interpreted as the next occuring mon, tue, wed, ...
>   - For example, if today is Monday (23 Sep),
>   - then mon will be interpreted as 30 Sep.
>   - then tue will be interpreted as 24 Sep. 

Examples:
- deadline return book /by 23 Sep
- deadline return book /by mon
- deadline return book /by Mon

---

### Add Events

<a name="add-events"></a>

Adds an event to the task list.

Format: ``event TASK /from DATE\TIME /to TIME``

> [!NOTE]

> "from" and "to" are case-sensitive.
> - The input date will be assumed to be in the current year.
> - For example, "23 Sep" will be interpreted as "23 Sep 2024," since it is currently 2024.
> - Enter the year explicitly if the date is not in the current year, such as "23 Sep 2025."

> [!NOTE]

> The start time and end time are assumed to be within the same day.
> The end time must not be earlier than the start time.

> [!TIP]

> - mon, tue, wed, ... will be interpreted as the next occuring mon, tue, wed, ...
>   - For example, if today is Monday (23 Sep),
>   - then mon will be interpreted as 30 Sep.
>   - then tue will be interpreted as 24 Sep. 

Examples:
- event project meeting /from mon\2pm /to 4pm
- event project meeting /from 23 Sep\2pm /to 4pm
- event project meeting /from 23 Sep 2024\1400 /to 1600

---

### List all
<a name="list-all"></a>
Shows a list of all tasks in the task list.

Format: ``list``

---
### Clear all
<a name="clear-all"></a>
Clears all the tasks in the task list.

Format: ``clear``

---
### Mark task
<a name="mark-task"></a>
Marks a task in the task list as done.

Format: ``mark INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``mark 2`` marks the 2nd task in the task list as done.

---
### Unmark task
<a name="unmark-task"></a>
Unmarks a task in the task list.

Format: ``unmark INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``unmark 2`` unmarks the 2nd task in the task list.

---
### Delete task
<a name="delete task"></a>
Deletes a task in the task list.

Format: ``delete INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``delete 2`` deletes the 2nd task in the task list.

---
### Find task
<a name="find task"></a>
Finds a task in the task list.

Format: ``find TASK_DESCRIPTION``
> The keyword for task descriptions does not need to match the entire word in the task's description.
> The keyword is case-insensitive.

Examples:
- ``find book`` displays all tasks containing the keyword "book" in the description.

---
### Elysia
<a name="elysia"></a>
Call Elysia by her name and she will respond to you.

Format: ``Elysia`` or ``elysia``
She will respond to you with the randomised voice lines taken from Honkai Impact 3rd.

---
### Exit
<a name="exit"></a>
Exit the application.

Format: ``bye``
> Elysia helps you save and load the tasks automatically, you can skip the bye command and close the application directly or use this command safely. 
