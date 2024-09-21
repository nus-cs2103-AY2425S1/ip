# Sam User Guide

![](./Ui.png)

**Sam Bot** is a simple and efficient chatbot to help you manage tasks like ToDos, Deadlines, and Events. With Sam Bot, you can add, mark, delete, find and list tasks to stay organized.

## Features Overview

Sam Bot provides the following features:
- Adding tasks (ToDos, Deadlines, Events)
- Marking tasks as done or undone
- Deleting tasks
- Listing all tasks
- Finding tasks by keyword
- Exiting the application

## Adding Deadlines

The `deadline` command allows you to add a task with a specific due date. A deadline task requires a description and a due date, ensuring that you stay on top of time-sensitive tasks.

Example: `deadline <description> /by <due date>`

Example: `deadline submit assignment /by 2024-10-10`

This command adds a task with a deadline to your list. The task will appear with a `[D]` tag, indicating it’s a deadline task. It will also display the due date you specified.

```
Got it. I’ve added this task:
[D][ ] submit assignment (by: 2024-10-10)
```

## Adding ToDo Tasks

The `todo` command allows you to add a simple ToDo task, which only requires a description. This is for tasks without a specific deadline or timeframe.

Example: `todo <description>`

Example: `todo read book`

This command adds a ToDo task to your list. The task will appear with a `[T]` tag, indicating it’s a ToDo task.

```
Got it. I’ve added this task:
[T][ ] read book
```

## Adding Events

The `event` command allows you to add tasks that have a specific start and end date. This is useful for scheduling events that span a period of time.

Example: `event <description> /from <start date> /to <end date>`

Example: `event project meeting /from 2024-09-20 /to 2024-09-21`

This command adds an event to your list. The task will appear with an `[E]` tag, indicating it’s an event. The start and end dates will also be displayed.

```
Got it. I’ve added this task:
[E][ ] project meeting (from: 2024-09-20 to: 2024-09-21)
```

## Marking Tasks as Done

The `mark` command allows you to mark a task as completed by specifying its number from the list.

Example: `mark <task number>`

Example: `mark 2`

This command will update the specified task as done. A `[X]` will appear next to the task to indicate it has been completed.

```
Nice! I’ve marked this task as done:
[T][X] read book
```

## Unmarking Tasks as Not Done

The `unmark` command allows you to mark a task as not done by specifying its number from the list.

Example: `unmark <task number>`

Example: `unmark 2`

This command will update the specified task as not done. The `[X]` will be removed from the task.

```
OK, I’ve marked this task as not done yet:
[T][ ] read book
```

## Deleting Tasks

The `delete` command allows you to remove a task from your list by specifying its number.

Example: `delete <task number>`

Example: `delete 1`

This command will remove the specified task from the list.
```
Noted. I’ve removed this task:
[T][ ] read book
```

## Listing All Tasks

The `list` command shows all the tasks currently in your list.

Example: `list`

This command will display all tasks, along with their tags (e.g., `[T]`, `[D]`, `[E]`) and whether they are marked as done or not.
```
Here are the tasks in your list:

1.	[T][ ] read book
2.	[E][ ] project meeting (from: 2024-09-20 to: 2024-09-21)
3.	[D][X] submit assignment (by: 2024-10-10)
```

## Finding Tasks by Keyword

The `find` command allows you to search for tasks by keyword.

Example: `find <keyword>`

Example: `find book`

This command will list all tasks that contain the specified keyword in their description.
```
Here are the matching tasks in your list:

1.	[T][ ] read book
```

## Exiting the Application

The `bye` command allows you to exit the application.

Example: `bye`

This command will save your tasks to a file and close the program.


## Help

If you need help or want to view a list of commands, use the `help` command.

Example: `help`

This command will display a list of all available commands with brief descriptions.
```
Here are the available commands:

	1.	list - Displays all tasks in your list.
	2.	todo <description>  - Adds a ToDo task.
	3.	deadline <description>  /by  - Adds a Deadline task.
	4.	event <description>  /from  /to  - Adds an Event task.
	5.	mark <task number> - Marks a task as done.
	6.	unmark <task number> - Marks a task as not done.
	7.	delete <task number> - Deletes a task from the list.
	8.	find <keyword> - Finds tasks containing the keyword.
	9.	bye - Exits the application.
	10.	help - Displays this help message.
```

## Saving and Loading Tasks

Sam Bot automatically saves your tasks to a file (`sam.txt`) and reloads them the next time you start the bot. This ensures that your tasks are never lost between sessions.
