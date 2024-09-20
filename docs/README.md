# Karen User Guide

<img src="Ui.png" width="200"/>

Need a personal assistant to keep track of your tasks?
Karen has got your back!

## Adding todos

Add a new Todo for Karen to track\
Todos are the simplest form of Task, with no date-time associated with it

Format: `todo <todo_name>`

Example: `todo buy groceries`

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

Format: `deadline <deadline_name> /by <date-time>`

Example: `deadline math assignment /by 2024-10-20 1200`

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

Format: `event <event_name> /from <date-time> /to <date-time>`

Example: `event birthday party /from 2024-10-12 1200 /to 2024-10-12 1800`

Output when event is added successfully:
```
_______________________
Got it! Added this task:
	[E][ ] <event_name> (from: <date-time> to: <date-time>)
Now you have _ tasks in the list.
_______________________
```

## Mark tasks

Mark a Task in your tasklist as complete

Format: `mark <task_index>`

Example: `mark 1`

Output when task is marked successfully:
```
_______________________
Nice! I've marked this task as done:
	[T][X] <task_name> <date-time>
_______________________
```

## Unmark tasks

Unmark Tasks in your list as incomplete

Format: `unmark <task_index>`

Example: `unmark 2`

Output when task is unmarked successfully:
```
_______________________
Ok! This task is now marked undone:
	[T][ ] <task_name> <date-time>
_______________________
```
## Delete events

Remove Tasks from your list after they are done

Format: `delete <task_index>`

Example: `delete 1`

Output when task is deleted successfully:
```
_______________________
Alright! I've removed this task from your list:
	[T][ ] <task_name> <date-time>
_______________________
```

## Sort events

Sort tasks in your list by alphabetical order or chronological order

Format: `sort <sort_criteria> /order <sort_order>`

Example: `sort alphabetical /order ascending`

Example: `sort chronological /order descending`

Output when tasks are sorted successfully:
```
List of tasks in sorted order
```

## date-time

Karen requires date-times to be entered in a certain syntax: `YYYY-MM-DD 24HR`

An invalid date-time will cause Karen to show an error message

Example: `2024-10-12 1200`
Example: `1996-01-02 2400`

## task_index

Task indexes are 1-indexed integers (i.e. the first task in the list has an index of 1)

An invalid `task_index` will cause Karen to show an error message