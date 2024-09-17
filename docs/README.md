# Bob User Guide

![Screenshot of Bob's GUI.](./Ui.png)

Bob is a chatbot that manages your todos, events and deadlines,
allowing you to mark, unmark, add, delete and list these tasks. 
In addition, he can help to summarise the tasks you have completed in the
past week, or any range you specify.

For information on each function, refer to the sections below:

Note: A **task** refers to a **deadline**, **event**, or **todo**.


## Listing Tasks: ```list```
Shows a list of all tasks.

Format: ```list```

Expected output: 
```
These are your tasks:
1:[T][X] Buy groceries
2:[E][X] Interview for job position (from: Sep 17 2024 0200 to: Sep 17 2024 0500)
3:[D][ ] CS2103T ip (by: Sep 17 2024 0500)
4:[T][X] Buy pencils
5:[T][ ] Buy food
```

## Adding To-Dos: ```todo```
Adds a to-do.

Format: `todo <task description>`

Example: `todo Buy groceries`

Expected output:
```
Here's the added task:
	[T][ ] Buy groceries
Now you have 5 tasks in the list.
```

## Adding Deadlines: ```deadline```
Adds a deadline. Deadlines have a field to store the date and time the task should be completed by.

Format: `deadline <task description> /by <dd/MM/yy HHmm>`

Example: `deadline Big project /by 17/09/24 0200`

Expected output:
```
Here's the added task:
	[D][ ] Big project (by: Sep 17 2024 0200)
Now you have 6 tasks in the list.
```

## Adding Events: ```event```
Adds an event. Events have a field to store the start and end dates of the event.

Format: `event <task description> /from <dd/MM/yy HHmm> /to <dd/MM/yy HHmm>`

Example: `event Concert /from 17/09/24 0200 /to 17/09/24 0500`

Expected output:
```
Here's the added task:
	[E][ ] Concert (from: Sep 17 2024 0200 to: Sep 17 2024 0500)
Now you have 7 tasks in the list.
```

## Deleting tasks: ```delete```
Deletes the deadline / event / todo.

Format: `delete <task number>`

Example: `delete 1`

Expected output:
```
Oof. I have removed the requested task:
	[E][X] Interview for job position (from: Sep 17 2024 0200 to: Sep 17 2024 0500)
Now you have 6 tasks in the list
```

Note: You need to have a task in your task list before being able to perform an operation on the task.
<br>
Tip: List all tasks with `list` to know the number of the task you wish to perform an operation on.

## Marking tasks as done: ```mark```
Marks the deadline / event / todo as completed.

Format: `mark <task number>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
	[D][X] CS2103T ip (by: Sep 17 2024 0500)
```

## Unmarking tasks: ```unmark```
Unmarks the deadline / event / todo.

Format: `unmark <task number>`

Example: `unmark 1`

Expected output:
```
Oh well, this task has been marked undone:
	[D][ ] CS2103T ip (by: Sep 17 2024 0500)
```

## Finding tasks by keyword: ```find```
Finds all tasks with descriptions containing the keyword.

Format: `find <keyword>`

Example: `find Buy`

Expected output:
```
Here are the matching tasks in your list:
1:[T][X] Buy pencils
2:[T][ ] Buy food
3:[T][ ] Buy groceries
```

## Summarising the number of tasks completed: ```summarise```
Returns the number of tasks completed.

### Summary of tasks completed in the past week:
Format: `summarise week`

Expected output:
```
This week, in the past 7 days, you completed a total of 2 tasks, listed below:
1:[T][X] Buy pencils
2:[T][X] Buy food
 Great Job!
```

### Summary of tasks completed in a specified date range:
Format: `summarise /from <dd/MM/yy HHmm> /to <dd/MM/yy HHmm>`

Example: `summarise /from 17/09/24 1000 /to 17/09/24 2359`

Expected output:
```
From 2024-09-17 1000 to 2024-09-17 2359, you completed a total of 2 tasks, listed below:
1:[T][X] Buy pencils
2:[T][X] Buy food
 Great Job!
```

## Seeking help: ```I need help.```
Shows available commands, with each entry containing the format
of the command and a brief description of what the command does.

Format: `I need help.`

Expected output:
```
Here are the list of commands:
1. list
	- lists tasks
2. mark <task number>
	- marks the task as done
3. unmark <task number>
	- unmarks the task
4. deadline <task description> /by <by>
	- Creates a deadline
5. todo <task description>
	- Creates a todo
6. event <task description> /from <from> /to <to>
	- Creates an event
7. delete <task number>
	- deletes the task
8. find <keyword>
	- finds all tasks whose descriptions contain the keyword
9. summarise {(week) OR (/from <date> /to <date>)}
	- returns the number of tasks completed in the past week OR date range when /from and /to are specified)
10. (CLI only) bye
	- exits the program
```