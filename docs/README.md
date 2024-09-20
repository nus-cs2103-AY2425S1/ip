# Mizz User Guide

## Introduction

Mizz is an intelligent task management chatbot designed to help users stay organized and productive. It empowers users to create and track multiple tasks with a beautiful GUI.

The 3 categories of tasks that can be managed are:

1. Todo - Tasks without any date / time
2. Deadline - Tasks that need to be done before a specific date / time
3. Event - Tasks that start and end at specific dates / times

## Mizz Demo

![Screenshot of Mizz](./Ui.png)

## Features

- Words in **UPPER_CASE** are the parameters to be supplied by the user.
- Items in **square brackets** are optional.
  E.g. `COMMAND [PARAMETERS]`

  E.g. `delete 1`

- Date formats are in `YYYY-MM-DD` format.  
  E.g. `2024-09-10` is equivalent to 10 September 2024.

---

> 💡 **NOTE: The example outputs below will be displayed in the GUI as seen above.**

## Adding todos: `todo`

Adds a todo task to the task list  
Usage: `todo TASK_DESCRIPTION`

Example: `todo new todo`

```
-------------
Got it I've added this task:
[T][ ] new todo
You now have 5 tasks in your list.
-------------
```

## Adding deadlines: `deadline`

Adds a deadline task to the task list  
Usage: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`

Example: `deadline finish iP /by 2024-09-10`

```
-------------
Got it I've added this task:
[D][ ] finish iP (by: Sept 10 2024)
You now have 6 tasks in your list.
-------------
```

## Adding events: `event`

Adds an event task to the task list  
Usage: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Example: `event new event /from 2024-09-10 /to 2025-09-10`

```
-------------
Got it I've added this task:
[E][ ] new event (from: Sept 10 2024 to: Sept 10 2025)
You now have 7 tasks in your list.
-------------
```

## Listing all tasks: `list`

Lists all tasks in the task list
Usage: `list`

```
-------------
Here are tasks in your list:
1. [T][X] todo task
2. [T][ ] todo task 2
3. [D][ ] deadline task (by: Sept 10 2024)
4. [T][ ] new todo
5. [D][ ] finish iP (by: Sept 10 2024)
6. [E][ ] new event (from: Sept 10 2024 to: Sept 10 2025)
-------------
```

## Marking tasks as complete: `mark`

Marks a task in the task list as complete  
Usage: `mark TASK_NUMBER/ NUMBERS`

Example 1: `mark 1`

```
-------------
Nice! I've marked this task as done:
[T][X] todo task

-------------
```

Example 2: `mark 1 2`

```
-------------
Nice! I've marked this task as done:
[T][X] todo task
[T][X] todo task 2

-------------
```

## Marking tasks as incomplete: `unmark`

Marks a task in the task list as incomplete  
Usage: `unmark TASK_NUMBER/ NUMBERS`

Example 1: `unmark 1`

```
-------------
Ok, I've marked this task as not done yet:
[T][ ] todo task

-------------
```

Example 2: `unmark 1 2`

```
-------------
Ok, I've marked this task as not done yet:
[T][ ] todo task
[T][ ] todo task 2

-------------
```

## Deleting tasks: `delete`

Deletes a task in the task list  
Usage: `delete TASK_NUMBER/ NUMBERS`

Example: `delete 3`

```
-------------
Ok! I've removed this task:
[D][ ] deadline task (by: Sept 10 2024)

You now have 5 tasks in your list.
-------------
```

Example `delete 1 2 4`

```
-------------
Ok! I've removed this task:
[T][X] todo task
[T][ ] todo task 2
[T][ ] new todo

You now have 2 tasks in your list.
-------------
```

## Finding tasks: `find`

Finds all tasks in the task list that matches the specified condition(s)

1. Finds tasks with task descriptions that **partially** matches the specified keyword.

Usage: `find KEYWORD`

Example 1: `find event`

```
-------------
Here are the tasks found:
[E][ ] new event (from: Sept 10 2024 to: Sept 10 2025)
-------------
```

## Exiting Mizz: `bye`

Exits the chatbot

Usage: `bye`
