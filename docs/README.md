# Karen User Guide

<img src="Ui.png" width="200"/>

Need a personal assistant to keep track of your tasks?
Karen has got your back!

## Adding todos

Add a new Todo for Karen to track\
Todos are the simplest form of Task, with no date-time associated with it

Example: `todo <todo_name>`

Output when todo is added successfully:
```
_______________________
Got it! Added this task:
	[T][ ] <todo_name>
Now you have _ tasks in the list.
_______________________
```

## Adding deadlines

Adds a new Deadline for Karen to track\
Deadlines are a Task to be finished by a certain date-time

Example: `deadline <deadline_name> /by <date-time>`

Output when deadline is added successfully:

```
_______________________
Got it! Added this task:
	[D][ ] <deadline_name> (by: <date-time>)
Now you have _ tasks in the list.
_______________________
```

## Adding events

Add a new event for Karen to track\
Events are Tasks with a start date-time and an end date-time

Example: `event <event_name> /from <date-time> /to <date-time>`

Output when event is added successfully:
```
_______________________
Got it! Added this task:
	[E][ ] <event_name> (from: <date-time> to: <date-time>)
Now you have _ tasks in the list.
_______________________
```

## Mark tasks

Mark Tasks in your list as complete

Example: `mark <task_index>` 

Output when task is marked successfully:
```
_______________________
Nice! I've marked this task as done:
	[T][X] <task_name> <date-time>
_______________________
```

## Unmark tasks

Unmark Tasks in your list as incomplete

Example: `unmark <task_index>`

Output when task is unmarked successfully:
```
_______________________
Ok! This task is now marked undone:
	[T][ ] <task_name> <date-time>
_______________________
```
## Delete events

Remove Tasks from your list after they are done

Example: `delete <task_index>`

Output when task is deleted successfully:
```
_______________________
Alright! I've removed this task from your list:
	[T][ ] <task_name> <date-time>
_______________________
```

## Sort events

Sort tasks in your list by alphabetical order or chronological order

Example: `sort alphabetical /order ascending`

Example: `sort chronological /order descending`

Output when tasks are sorted successfully:
```
List of tasks in sorted order
```

## Date-times

Karen requires date-times to be entered in a certain syntax: `YYYY-MM-DD 24HR`

Example: `2024-10-12 1200`
Example: `1996-01-02 2400`