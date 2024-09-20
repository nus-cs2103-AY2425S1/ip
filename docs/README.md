# Duck User Guide

![Ui.png](Ui.png)

Duck is a desktop chat application to track tasks on a Graphical User Interface (GUI). It is optimized for users familiar with the command line-like interface so that they can get their task management done faster than traditional GUI applications.

## Adding Tasks

There are four types of tasks: todo, deadline, event and doafter.

### Adding todos

Adds a todo to the task list.

Format: `todo <description>`

`<description>` can be any text.

Example: `todo read book`

Expected outcome: Adds a todo task with the description "read book".

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding deadlines

Adds a deadline to the task list.

Format: `deadline <description> /by <due_date>`

`<description>` can be any text. `<due_date>` is in "yyyy-MM-dd" format.

Example: `deadline turn in homework /by 2024-09-20`

Expected outcome: Adds a deadline task with the description "turn in homework".

```
Got it. I've added this task:
  [D][ ] turn in homework (by: 20 SEPTEMBER 2024)
Now you have 2 tasks in the list.
```

### Adding events

Adds an event to the task list.

Format: `event <description> /from <start_date> /to <end_date>`

`<description>` can be any text. `<start_date>` and `<end_date>` are in "yyyy-MM-dd" format.

Example: `event open house /from 2024-09-20 /to 2024-09-21`

Expected outcome: Adds an event task with the description "open house", start date "2024-09-20", and end date "2024-09-21".

```
Got it. I've added this task:
  [E][ ] open house (from: 20 SEPTEMBER 2024 to: 21 SEPTEMBER 2024)
Now you have 3 tasks in the list.
```

### Adding doafter tasks

Adds a doafter task to the task list. These are tasks that need to be done after a specific time.

Format: `doafter <description> /after <earliest_date>`

`<description>` can be any text. `<earliest_date>` is in "yyyy-MM-dd" format. The task can be marked as done on the earliest date, but not before.

Example: `doafter clean up /after 2024-09-21`

Expected outcome: Adds an doafter task with the description "clean up", and earliest date "2024-09-21".

```
Got it. I've added this task:
  [A][ ] clean up (after: 21 SEPTEMBER 2024)
Now you have 4 tasks in the list.
```

## Listing tasks

Lists all tasks.

Example: `list`

Expected outcome: Displays all tasks in the task list.

```
Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] turn in homework (by: 20 SEPTEMBER 2024)
    3. [E][ ] open house (from: 20 SEPTEMBER 2024 to: 21 SEPTEMBER 2024)
    4. [A][ ] clean up (after: 21 SEPTEMBER 2024)
```

## Marking tasks

Marks a task as done.

Example: `mark 1`

Expected outcome: Marks the first task in the task list as done.

```
Nice! I've marked this task as done:
  [T][X] read book
```

## Unmarking tasks

Marks a task as not done.

Example: `unmark 1`

Expected outcome: Marks the first task in the task list as not done.

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

## Deleting tasks

Deletes a task from the task list.

Example: `delete 1`

Expected outcome: Deletes the first task in the list.

```
Noted. I've removed this task:
  [T][ ] read book
Now you have 3 tasks in the list.
```

## Stopping the application

Terminates the application.

Example: `bye`

Expected outcome: The application is terminated.
