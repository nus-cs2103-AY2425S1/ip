# SadCat User Guide

SadCat is a personal task management application that helps you keep track of your todos, deadlines, and events. With SadCat, you can easily add, update, and manage your tasks through a simple command-line interface. Manage your tasks without removing your hands from the keyboard!

![SadCat UI](./Ui.png)

## Additional features
As programmers, we probably hate looking at bright things. The "Toggle Dark Mode" button allows users to toggle between a screen brighter than their future and a comfy dark interface.

## Adding tasks

SadCat supports three types of tasks: todos, deadlines, and events.

### Adding a todo

To add a todo, use the following command:

`todo <task description>`

Example: `todo read a book`

Expected output:
```
Got it. I've added this task:
[T][ ] read a book
Now you have 1 tasks in the list.
```

### Adding a deadline

To add a deadline, use the following command:

`deadline <task description> /by <date time>`

Example: `deadline submit report /by 2023-12-31 2359`

Expected output:
```
Got it. I've added this task:
[D][ ] submit report (by: Dec 31 2023 23:59)
Now you have 2 tasks in the list.
```

### Adding an event

To add an event, use the following command:

`event <task description> /from <start date time> /to <end date time>`

Example: `event team meeting /from 2023-06-15 1400 /to 2023-06-15 1600`

Expected output:
```
Got it. I've added this task:
[E][ ] team meeting (from: Jun 15 2023 14:00 to: Jun 15 2023 16:00)
Now you have 3 tasks in the list.
```

## Listing tasks

To view all your tasks, use the following command:

`list`

Expected output:
```
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] submit report (by: Dec 31 2023 23:59)
3. [E][ ] team meeting (from: Jun 15 2023 14:00 to: Jun 15 2023 16:00)
```

## Marking tasks as done

To mark a task as done, use the following command:

`mark <task number>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
[T][X] read a book
```

## Unmarking tasks

To mark a task as not done, use the following command:

`unmark <task number>`

Example: `unmark 1`

Expected output:
```
Ok, I've marked this task as not done yet:
[T][ ] read a book
```

## Deleting tasks

To delete a task, use the following command:

`delete <task number>`

Example: `delete 2`

Expected output:
```
Noted. I've removed this task:
[D][ ] submit report (by: Dec 31 2023 23:59)
Now you have 2 tasks in the list.
```

## Finding tasks

To find tasks containing a specific keyword, use the following command:

`find <keyword>`

Example: `find book`

Expected output:
```
Here are the matching tasks in your list:
1. [T][ ] read a book
```

## Updating tasks

To update an existing task, use the following command:

`update <task number> <new task type> <new task description>`

Example: `update 1 deadline finish book /by 2023-07-31 2359`

Expected output:
```
Got it. I've updated the task:
[D][ ] finish book (by: Jul 31 2023 23:59)
```

## Switching save files

To switch to a different save file, use the following command:

`savefile <filename>`

Example: `savefile work`

Expected output:
```
Tasks will now be saved to and loaded from: work.txt
```

## Archiving tasks

To archive all current tasks and start with a clean slate, use the following command:

`archive`

Expected output:
```
Tasks archived to: current_archive.txt
Current task list has been cleared.
```

## Getting help

To view a list of all available commands, use the following command:

`help`

This will display a comprehensive list of all commands and their usage.

## Exiting the application

To exit SadCat, use the following command:

`bye`

Expected output:
```
Bye. Hope to see you again soon!
```