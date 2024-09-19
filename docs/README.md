# Echo User Guide

![Screenshot of Echo GUI](Ui.png)
Echo is a chat bot who will help you manage your tasks!

## Adding todos: `Todo`

Adds a task with a description.

Format: `Todo` `[Description]`

Example: `Todo` `Go for a run`

```
Got it. I've added this task:
[T] [] Go for a run
Now you have 1 task in the list.
```

## Adding deadlines: `Deadline`

Adds a task with a deadline.

Format: `Deadline` `[Description]` `/by [date]`
- Date can be in any date format including date, month & year.
e.g. 24-9-2024, 4 Jun 2024

Example: `Deadline` `Assignment` `/by 24/9/2024`

```
Got it. I've added this task:
[D] [] Assignment (by: Sep 24 2024)
Now you have 1 task in the list.
```

## Adding events: `Event`

Adds a task with a start and end date/time.

Format: `Event` `Description` `/from [start date/time]` `/to [start date/time]`
- Start date/time can be anything.

Example: `Event` `My birthday` `/from 2pm` `/to 3pm`

```
Got it. I've added this task:
[E] [] My birthday (from: 2pm to: 3pm)
Now you have 1 task in the list.
```