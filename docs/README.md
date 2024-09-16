# Nixy User Guide

![Nixy](./Ui.png)

Nixy is a desktop task manager, optimized for use via a Command Line Interface (CLI).
It also has a text based Graphical User Interface (GUI) for users who prefer to use a GUI!

## Adding deadlines

Add a task with a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example: `deadline wish rachael happy birthday /by 2024-09-30`

A short alert would show task has been added to the task list with the deadline.

```
Got it. I've added this task:
[D][ ] wish rachael happy birthday (by: 30 Sep 2024)
Now you have X tasks in the list.
```

## Adding events

Add a task with a specific start event date and end date to the task list.

Format: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Example: `event attend conference /from 2024-09-30 /to 2024-10-01`

A short alert would show task has been added to the task list with the start and end date.

```
Got it. I've added this task:
[E][ ] attend conference (from: 30 Sep 2024 to: 01 Oct 2024)
Now you have X tasks in the list.
```

## Adding todos

Add a task without a deadline to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo watch Stranger Things`

A short alert would show task has been added to the task list.

```
Got it. I've added this task:
[T][ ] watch Stranger Things
Now you have X tasks in the list.
```

## Listing tasks

List all tasks in the task list.

Example: `list`

A list of tasks would be shown.

```
Here are the tasks in your list:
1. [D][ ] wish rachael happy birthday (by: 30 Sep 2024)
2. [E][ ] attend conference (from: 30 Sep 2024 to: 01 Oct 2024)
3. [T][ ] watch Stranger Things
```

## Deleting tasks

Delete a task from the task list.
Task number can be found by using the `list` command.

Format: `delete TASK_NUMBER`

Example: `delete 3`

A short alert would show task has been deleted from the task list.

```
Noted. I've removed this task:
[T][ ] watch Stranger Things
Now you have X tasks in the list.
```

## Marking tasks as done/undone

Mark a task as done/undone in the task list.
Task number can be found by using the `list` command.

Format: `mark TASK_NUMBER`

Example: `mark 1`, `unmark 1`

A short alert would show task has been marked as done/undone in the task list.

```
(mark 1)
Nice! I've marked this task as done:
[D][X] wish rachael happy birthday (by: 30 Sep 2024)
```

## Undoing the last command

Undo the last command that changed the task list.

Example: `undo`

A short alert would show the last command has been undone and the action took to undo the last command.

Note: The undo command can undo up to the last 3 commands.

## Finding tasks

Find tasks that contain a specific keyword.
Tasks that contain the keyword would be shown.

Format: `find KEYWORD`

Example: `find conference`

A list of tasks that contain the keyword would be shown.

```
Here are the matching tasks in your list:
1. [E][ ] attend conference (from: 30 Sep 2024 to: 01 Oct 2024)
```

Note: The list numbering return is not consistent with the actual `list` numbering.

## Exiting the program

Exit the program.

Example: `bye`

Program would exit.

## Saving the data

Task list data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

Task list data is saved as a text file `data/tasks.txt`. Advanced users are welcome to update data directly by editing that data file.
