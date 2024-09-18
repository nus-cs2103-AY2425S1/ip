# Optimus User Guide

![Ui.png](Ui.png)


Optimus is a task management tool which allows you to track all your tasks with great flexibility.

## Feature List

* Adding To Do tasks
* Adding Deadline tasks
* Adding Events
* Viewing all tasks
* Searching tasks
* Updating task information
* Marking tasks as complete
* Unmarking tasks
* Deleting tasks

## General guidlines

For command formats, words in `UPPER_CASE` refer to user inputs

For commands requiring a `TASK_NUMBER` input, the `TASK_NUMBER` should be the targetted task's index in the list of tasks when the `list` command is called 

## Adding To Do tasks

Adds a basic task to the list of tasks

Format: `todo DESCRIPTION`

Example: `todo laundry`

## Adding Deadline tasks

Adds a task with a deadline to the list of tasks

Format: `deadline DESCRIPTION /by DEADLINE`

Note: `DEADLINE` must be in YYYY-MM-DD format

Example: `deadline submit iP /by 2024-09-20`

## Adding Events

Adds an event (a task with a start and end) to the list of tasks

Format: `event DESCRIPTION /from START /to END `

Example: `event career fair /from 12pm /to 5pm`

## Viewing tasks

Displays the current list of tasks

Format: `list`

Sample output:
```
1.[T][] laundry
2.[D][] submit iP (by: Sept 20 2024)
3.[E}[] career fair (from: 12pm to: 5pm)
```

## Searching tasks

Displays all tasks with descriptions containing the search input

Note: The search is case-sensitive

Format: `find SEARCH_INPUT`

Example: `find career`

Sample output:

```
Here are the matching tasks in your list:
[E][] career fair (from: 12pm to: 5pm)
```

## Updating task information

Changes a task's details in-place on the list

Format: `update TASK_NUMBER [*FLAGS*]`

Flags:
1. `/desc DESCRIPTION`
2. `/by DEADLINE`
3. `/from START`
4. `/to END`


Note: The appropriate flags should be chosen for the task type. E.g. do not update deadlines for To Do tasks.

Example: `update 3 /desc SoC career fair /to 4pm`

Sample output:

```
I have updated the task. It now reads as:
[E][] SoC career fair (from: 12pm to: 4pm)
```

## Marking tasks as complete

Marks a task as complete

Format: `mark TASK_NUMBER`

Example: `mark 1`

Sample output:

```
Nice! I've marked this task as complete:
[T][X] laundry
```

## Unmarking tasks

Removes the completed mark (if any) from the task

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

## Deleting tasks

Removes a task from the list of tasks

Format: `delete TASK_NUMBER`

Example: `delete 1`
