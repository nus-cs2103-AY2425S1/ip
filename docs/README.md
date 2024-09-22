# Elysia User Guide

<img src="Ui.png" alt="Screenshot of Elysia's UI" width="1272" />

### Notes about the command format:
- Words in ``UPPER_CASE`` are the parameters to be supplied by the user.

  e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.

## Features
- [Add Todos](https://github.com/Elinengu/ip/edit/master/docs/README.md#add-todos)
- [Add Deadlines](https://github.com/Elinengu/ip/edit/master/docs/README.md#add-deadlines)
- [Add Events](https://github.com/Elinengu/ip/edit/master/docs/README.md#add-events)
- [List all](https://github.com/Elinengu/ip/edit/master/docs/README.md#list-all)
- [Clear all](https://github.com/Elinengu/ip/edit/master/docs/README.md#clear-all)
- [Mark task](https://github.com/Elinengu/ip/edit/master/docs/README.md#mark-task)
- [Unmark task](https://github.com/Elinengu/ip/edit/master/docs/README.md#unmark-task)
- [Delete task](https://github.com/Elinengu/ip/edit/master/docs/README.md#delete-task)
- [Find task](https://github.com/Elinengu/ip/edit/master/docs/README.md#find-task)
- [Call Elysia by her name](https://github.com/Elinengu/ip/edit/master/docs/README.md#elysia)
- [Exit](https://github.com/Elinengu/ip/edit/master/docs/README.md#exit)
---
### Add Todos
Adds a todo to the task list.

Format: ``todo TASK``

Examples:
- todo read book
---
### Add Deadlines 
Adds a deadline to the task list.

Format: ``deadline TASK /by DATE``
> The month and "by" are case-sensitive.

Examples:
- deadline return book /by 23 Sep
- deadline return book /by mon
- deadline return book /by Mon

---
### Add Events
Adds an event to the task list.

Format: ``event TASK /from DATE\TIME /to TIME``
> "from" and "to" are case-sensitive.

Examples:
- event project meeting /from mon\2pm /to 4pm
- event project meeting /from 23 Sep\2pm /to 4pm
- event project meeting /from 23 Sep 2024\1400 /to 1600

---
### List all
Shows a list of all tasks in the task list.

Format: ``list``

Examples:
- list

---
### Clear all
Clears all the tasks in the task list.

Format: ``clear``

Examples:
- clear

---
### Mark task
Marks a task in the task list as done.

Format: ``mark INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``mark 2`` marks the 2nd task in the task list as done.

---
### Unmark task
Unmarks a task in the task list.

Format: ``unmark INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``unmark 2`` unmarks the 2nd task in the task list.

---
### Delete task
Deletes a task in the task list.

Format: ``delete INDEX``
> The index refers to the index number shown in the displayed list using ``list``.
> The index must be a positive integer 1,2,3 ...

Examples:
- ``list`` followed by ``delete 2`` deletes the 2nd task in the task list.

---
### Find task
Finds a task in the task list.

Format: ``find TASK_DESCRIPTION``
> The keyword for task description needs not to match the whole word in the task's description
> The keyword for task description is case-insensitive.

Examples:
- ``find book`` displays all tasks containing the keyword "book" in the description.
  
Example: `keyword (optional arguments)`

---
### Elysia
Call Elysia by her name and she will respond to you.

Format: ``Elysia`` or ``elysia``
She will respond to you with the randomised voice lines taken from Honkai Impact 3rd.

---
### Exit 
Exit the application.

Format: ``bye``
> Elysia helps you save and load the tasks automatically, you can skip the bye command and close the application directly or use this command safely. 
