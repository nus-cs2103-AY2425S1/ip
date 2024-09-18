# LevelHundred User Guide

## Introduction

LevelHundred is an intelligent task management chatbot designed to help users stay organized and productive. It allows users to create, track, and manage tasks effortlessly through simple conversational commands.

The 3 categories of tasks that can be managed are:

1. Todo - Tasks without any date / time
2. Deadline - Tasks that need to be done before a specific date / time
3. Event - Tasks that start and end at specific dates / times

## LevelHundred Demo

![Screenshot of LevelHundred](./Ui.png)

## Features

- Words in **UPPER_CASE** are the parameters to be supplied by the user.  
  E.g. In `mark TASK_NUMBER`, `TASK_NUMBER` is a parameter which can be used as `mark 1`

- Items in **square brackets** are optional.  
  E.g. `find TASK_DESCRIPTION [/byType TASK_TYPE]` can be used as `find foo` or as `find foo /byType T`

- Date formats are in `YYYY-MM-DD HHMM` format.  
  E.g. `2024-09-18 2359` is equivalent to 18 September 2024 11:59 p.m.

## Adding todos: `todo`

Adds a todo task to the task list  
Usage: `todo TASK_DESCRIPTION`

Example: `todo finish IP`

```
Got it. I've added this task:
[T][ ] finish IP
Now you have 1 tasks in the list.
```

## Adding deadlines: `deadline`

Adds a deadline task to the task list  
Usage: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`

Example: `deadline finish IP /by 2024-09-18 2359`

```
Got it. I've added this task:
[D][ ] finish IP (by: 18 Sep 2024 2359)
Now you have 2 tasks in the list.
```

## Adding events: `event`

Adds an event task to the task list  
Usage: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Example: `event finish IP /from 2024-09-18 0000 /to 2024-09-18 2359`

```
Got it. I've added this task:
[E][ ] finish IP (by: 18 Sep 2024 0000 to: 18 Sep 2024 2359)
Now you have 3 tasks in the list.
```

## Listing all tasks: `list`

Lists all tasks in the task list
Usage: `list`

```
1.[T][ ] finish IP
2.[D][ ] finish IP (by: 18 Sep 2024 2359)
3.[E][ ] finish IP (from: 18 Sep 2024 0000 to: 18 Sep 2024 2359)
```

## Marking tasks as complete: `mark`

Marks a task in the task list as complete  
Usage: `mark TASK_NUMBER`

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] Finish IP
```

## Marking tasks as incomplete: `unmark`

Marks a task in the task list as incomplete  
Usage: `unmark TASK_NUMBER`

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
[T][ ] Finish IP
```

## Deleting tasks: `delete`

Deletes a task in the task list  
Usage: `delete TASK_NUMBER`

Example: `delete 3`

```
Noted. I've removed this task:
[E][ ] finish IP (from: 18 Sep 2024 0000 to: 18 Sep 2024 2359)
Now your have 2 tasks in the list.
```

## Finding tasks: `find`

Finds all tasks in the task list that matches the specified condition(s)

1. Finds tasks with task descriptions that **partially** matches the specified task description
2. [**Optional**] Finds tasks by the specified task type (**T** for Todo, **D** for Deadline, **E** for Event)

Usage: `find TASK_DESCRIPTION [/byType TASK_TYPE]`

Example #1: `find finish`

```
Here are the matching tasks in your list
1.[T][ ] finish IP
2.[D][ ] finish IP (by: 18 Sep 2024 2359)
```

Example #2: `find finish /byType T`

```
Here are the matching tasks in your list
1.[T][ ] finish IP
```

## Exiting LevelHundred: `bye`

Exits the chatbot

Usage: `bye`
