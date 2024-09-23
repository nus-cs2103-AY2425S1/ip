# Pacman User Guide

<img src="Ui.png" alt="">

Pacman bot is an application that remember and track all your works.

## Adding todos

Add a task that will be done.

Usage: <code>todo \<task name></code>, <code>T \<task name></code>

Example: `todo commit code`

Result:
```
Got it. I've added this task:
  [T][ ] commit code
Now you have <number of task> tasks in the list.
```

## Adding deadlines

Add a task with deadline.

Usage: <code>deadline \<task name> /by \<deadline date with format YYYY-MM-DD></code>, <code>D \<task name> /by \<deadline date with format YYYY-MM-DD></code>

Example: `D commit code /by 2024-09-17`

Result:
```
Got it. I've added this task:
  [D][ ] commit code (by: Sep 17 2024)
Now you have <number of task> tasks in the list.
```

## Adding events

Add an event with start date and end date.

Usage: <code>event \<task name> /from \<event end date with format YYYY-MM-DD> /to \<event start date with format YYYY-MM-DD></code>, <code>E \<task name> /from \<event start date with format YYYY-MM-DD> /to \<event end date with format YYYY-MM-DD></code>

Example: `E vacation /from 2024-09-17 /to 2024-09-29`

Result:
```
Got it. I've added this task:
  [E][ ] vacation (from: Sep 17 2024 to: Sep 29 2024)
Now you have <number of task> tasks in the list.
```

## Marking tasks

Mark a task.

Usage: <code>mark \<task index></code>, <code>m \<task index></code>

Example: `mark 1`

Result:
```
Got it. I've marked this task done:
  [T][X] commit code
```


## Unmarking tasks

Unmark a task.

Usage: <code>unmark \<task index></code>, <code>u \<task index></code>

Example: `u 1`

Result:
```
Got it. I've marked this task as not done yet:
  [T][ ] commit code
```

## Delete tasks

Delete a task.

Usage: <code>delete \<task index></code>, <code>d \<task index></code>

Example: `d 3`

Result:
```
Noted. I've removed this task:
  [E][ ] vacation (from: Sep 17 2024 to: Sep 29 2024)
Now you have <number of task> tasks in the list.
```

## Find tasks

Find tasks that have a string as task name substring.

Usage: <code>find \<string to be find></code>, <code>f \<string to be find></code>

Example: `find co`

Result:
```
Here are the matching tasks in your list:
1. [T][ ] commit code
2. [D][ ] commit code (by: Sep 17 2024)
```

## List added tasks

Display all tasks.

Usage: <code>list</code>, <code>l</code>

Example: `list`

Result:
```
Here are the tasks in your list:
1. [T][ ] commit code
2. [D][X] commit code (by: Sep 17 2024)
```