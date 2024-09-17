# EchoBot User Guide

![Screenshot of the main program.](Ui.png)
Echobot is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Echobot can get your task management done faster than traditional GUI apps.

## Adding a todo task: `add todo`
Adds a todo task to the list.

Format: `add todo DESCRIPTION`

Example: `add todo assignment1`

```
Got it. I've added this task:
        [T][] assignment 1
    Now you have 9 task(s) in the list.
```

## Adding a deadline task: `add deadline`
Adds a deadline task to the list.

Format: `add deadline DESCRIPTION /by DEADLINE`
* `DEADLINE`: Follows `dd-MM-yyyy HH:ss` datetime format.

Example: `add deadline assignment 2 /by 17-09-2024 17:00`

```
Got it. I've added this task:
        [D][] assignment 2 (by:Sep 17 2025 17:00)
    Now you have 10 task(s) in the list.
```

## Adding an event task: `add event`
Adds a deadline task to the list.

Format: `add event DESCRIPTION /from FROM /to TO`
* `FROM`: Follows `dd-MM-yyyy HH:ss` datetime format.
* `TO`: Follows `dd-MM-yyyy HH:ss` datetime format.

Example: `add event chess competition /from 15-09-2024 08:30 /to 15-09-2024 17:30`

```
Got it. I've added this task:
        [E][] chess competition (from:Sep 15 2024 08:30 to:Sep 15 2024 17:30)
    Now you have 11 task(s) in the list.
```

## Deleting an event task: `delete`
Deletes a task from the list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index `must be in the displayed task list`.

Example: `DELETE 2`

```
Got it. I've added this task:
        [D][] chess competition (from:Sep 15 2024 08:30 to:Sep 15 2024 17:30)
    Now you have 10 task(s) in the list.
```

## Listing all tasks: `list`
Displays all tasks in the list.

Format: `list`

Example: `list`

```
Here are the tasks in your list:
	1.[T][X] buy books
	2.[D][] assignment1 (by:Sep 17 2024 17:00)
	3.[D][X] assignment2 (by:Nov 11 2024 11:00)
	4.[E][] coffee talk (from:Sep 15 2024 08:30 to:Sep 15 2024 17:30)
```

## Listing all tasks that occur on a date: `list`
Displays all **undone** tasks that occur on a specific date in the list.

Format: `list /on DATE`
* `DATE`: Follows `dd-MM-yyyy` date format.

Example: `list /on 17-09-2024`

```
Here are the tasks in your list:
	1.[D][] go to gym (by:Nov 11 2024 11:00)
	2.[D][] assignment2 (by:Sep 17 2024 17:00)
```

## Finding tasks by keyword: `find`
Finds all tasks that contain the keyword.

Format: `find KEYWORD`
* `KEYWORD` is case-sensitive.

Example: `find assignment`

```
Here are the tasks in your list:
	1.[T][] complete assignment 1
	2.[T][X] complete assignment 2
	3.[D][] assignment 3 for Calculus (by:Sep 17 2024 17:00)
```

## Marking a task done: `mark`
Marks a task done by index.

Format: `mark INDEX`
* Marks the task done at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index `must be in the displayed task list`.

Example: `mark 1`

```
Nice! I've marked this task as done:
    [T][X] complete assignment 1
```

## Marking a task undone: `unmark`
Marks a task undone by index.

Format: `unmark INDEX`
* Marks the task undone at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index `must be in the displayed task list`.

Example: `unmark 1`

```
Nice! I've marked this task as not done yet:
    [T][] complete assignment 1
```

## Undoing a command: `undo`
Undoes the lastly executed command.

Format: `undo`

Example: `undo`

## Exiting the program: `bye`
Exits the program.

Format: `bye`

Example: `bye`

## Saving the data
Echobot data are saved in the hard disk automatically after any command that changes the data.
There is not need to save manually.