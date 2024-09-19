# Atlas User Guide

![Screenshot of the Atlas Chatbot.](./Ui.png)

Atlas is a state-of-the-art chatbot that has various functionalities that enable you to manage and keep track of your 
todos, events and deadlines. Your lists of tasks will be saved when you exit the application, and it will be restored
whenever you come back. Try it now!

## Getting Help :`help`

The help command shows all the commands that are supported by Atlas. It also shows what parameters are expected for
each command.

Format: `help` or `h`

Example: `help` or `h`

The Atlas help page will be displayed.

```
--------------------------------------- Atlas Help Page ---------------------------------------

Here are all the commands you can try with Atlas!

bye, b - exits the Atlas chatbot application

list, l - lists all tasks in your task list

mark, m <taskNumber> - marks a task in your task list as done

unmark, u <taskNumber> - unmarks a task in your task list as not done

delete, r <taskNumber> - removes a task from your task list

find, f <pattern> - displays tasks whose names match this pattern

todo, t <name> - adds a new todo to the task list

deadline, d <name> /by <date (YYYY-MM-DD HHMM)> - adds a new deadline to the task list

event, e <name> /from <date (YYYY-MM-DD HHMM)> /to <date (YYYY-MM-DD HHMM)> - adds a new event
```

## Exiting Atlas :`bye`

The bye command helps you to quit the application.

Format: `bye` or `b`

Example: `bye` or `b`

The Atlas application will be closed.

## Listing All Tasks :`list`

The list command helps you to list all the tasks in your current task list. It also shows the description of the tasks.

Format: `list` or `l`

Example: `list` or `l`

All the tasks in the task list will be displayed.

```
1. [D][ ] return book (by: May 30 2024 5:00 pm)
2. [E][X] play football (from: Nov 1 2024 5:00 pm to: Nov 1 2024 7:00 pm)
3. [D][ ] submit assignment (by: Oct 30 2024 10:00 am)
4. [E][X] join marathon (from: Jun 12 2024 5:00 am to: Jun 12 2024 12:00 pm)
5. [T][X] buy groceries
6. [D][ ] buy gift (by: Sep 30 2024 12:00 pm)
```

## Marking Tasks :`mark`

The mark command helps you to mark a task in your current task list as done. You have to pass in the task number as 
an argument after the 'mark' command.

Format: `mark INDEX` or `m INDEX`

- The index must be a positive integer 1, 2, 3, ... that is within the total number of tasks in the list.

Example: `mark 6` or `m 6`

The sixth task will be marked as done.

```
Nice! I've marked this task as done:
[D][X] buy gift (by: Sep 30 2024 12:00 pm)
```

## Unmarking Tasks :`unmark`

The unmark command helps you to unmark a task in your current task list as not done. You have to pass in the task 
number as an argument after the 'unmark' command.

Format: `unmark INDEX` or `u INDEX`

- The index must be a positive integer 1, 2, 3, ... that is within the total number of tasks in the list.

Example: `unmark 6` or `u 6`

The sixth task will be unmarked as not done.

```
OK, I've marked this task as not done yet:
[D][ ] buy gift (by: Sep 30 2024 12:00 pm)
```

## Deleting Tasks :`delete`

The delete command helps you to delete a task from your current task list. You have to pass in the task number as an 
argument after the 'delete' command.

Format: `delete INDEX` or `r INDEX`

- The index must be a positive integer 1, 2, 3, ... that is within the total number of tasks in the list.

Example: `delete 6` or `r 6`

The sixth task will be deleted from the current task list.

```
Noted. I've removed this task:
[D][ ] buy gift (by: Sep 30 2024 12:00 pm)
Now you have 5 tasks in the list
```

## Finding Tasks :`find`

The find command helps you find all the tasks from your current task list that match the search pattern you have 
specified. You have to pass in the pattern as an argument after the 'find' command.

Format: `find PATTERN` or `f PATTERN`

- The pattern must be a valid string. It is case-sensitive: 'B' and 'b' are not the same.

Example: `find sub` or `f sub`

All the tasks from the current task list that contain the pattern 'sub' in their name, will be displayed.

```
Here are the matching tasks in your list:
[D][ ] submit assignment (by: Oct 30 2024 10:00 am)
```

## Adding Todo :`todo`

The todo command helps you add a todo into the current task list. You have to pass in the todo name as an argument 
after the 'todo' command.

Format: `todo NAME` or `t NAME`

- The name must be a valid string.

Example: `todo exercise` or `t exercise`

The todo you have added will be displayed. The total number of tasks in the current task list will also be displayed
at the end.

```
Got it. I've added this task:
[T][ ] exercise
Now you have 7 tasks in the list.
```

## Adding Deadline :`deadline`

The deadline command helps you add a deadline into the current task list. You have to pass in the deadline name as an 
argument after the 'deadline' command, together with the deadline date.

Format: `deadline NAME /by DEADLINE_DATETIME` or `d NAME /by DEADLINE_DATETIME`

- The name must be a valid string. The deadline datetime should be in the valid format: YYYY-MM-DD HHMM.

Example: `deadline submit report /by 2024-06-12 1500` or `d submit report /by 2024-06-12 1500`

The deadline you have added will be displayed. The total number of tasks in the current task list will also be displayed
at the end.

```
Got it. I've added this task:
[D][ ] submit report (by: Jun 12 2024 3:00 pm)
Now you have 8 tasks in the list.
```


## Adding Event :`event`

The event command helps you add a event into the current task list. You have to pass in the event name as an argument
after the 'event' command, followed by the start and end times.

Format: `event NAME /from START_TIME /to END_TIME` or `e NAME /from START_TIME /to END_TIME`

- The name must be a valid string. The start time and end time should be in the valid format: YYYY-MM-DD HHMM.

Example: `event join workshop /from 2024-07-22 1600 /to 2024-07-22 1800` or
`e join workshop /from 2024-07-22 1600 /to 2024-07-22 1800`

The event you have added will be displayed. The total number of tasks in the current task list will also be displayed 
at the end.

```
Got it. I've added this task:
[E][ ] join workshop (from: Jul 22 2024 4:00 pm to: Jul 22 2024 6:00 pm)
Now you have 9 tasks in the list.
```
