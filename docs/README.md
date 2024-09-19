# Buddy User Guide

***GitHub Copilot was used throughout the development as an auto-complete tool**

![Ui.png](Ui.png)

// Product intro goes here

## Adding todos

To add a todo, use the `todo` command followed by the task description.

Example: `todo Read book`

Expected outcome: Adds a todo task with the description "Read book".

```
Got it. I've added this task:
    [T][] Read book
Now you have 1 tasks in the list.
```

## Adding deadlines

To add a deadline, use the `deadline` command followed by the task description and the due date in "yyyy-MM-dd HHmm" format.

Example: `deadline Submit assignment /by 2021-09-30 1800`

Expected outcome: Adds a deadline task with the description "Submit assignment" and the due date "2021-09-30 1800".

```
Got it. I've added this task:
    [D][] Submit assignment (by: Sep 30 2021, 6:00 pm)
Now you have 2 tasks in the list.
```

## Adding events

To add an event, use the `event` command followed by the event description, start date, and end date in "yyyy-MM-dd HHmm" format.

Example: `event Project meeting /from 2021-09-30 1400 /to 2021-09-30 1600`

Expected outcome: Adds an event task with the description "Project meeting", start date "2021-09-30 1400", and end date "2021-09-30 1600".

```
Got it. I've added this task:
    [E][] Project meeting (from: Sep 30 2021, 2:00 pm to: Sep 30 2021, 4:00 pm)
Now you have 3 tasks in the list.
```

## Listing tasks

To list all tasks, use the `list` command.

Example: `list`

Expected outcome: Displays all tasks in the task list.

```
Here are the tasks in your list:
1. [T][] Read book
2. [D][] Submit assignment (by: Sep 30 2021, 6:00 pm)
3. [E][] Project meeting (from: Sep 30 2021, 2:00 pm to: Sep 30 2021, 4:00 pm)
```

## Marking tasks

To mark a task as done, use the `mark` command followed by the task number.

Example: `mark 1`

Expected outcome: Marks the first task in the list as done.

```
Nice! I've marked this task as done:
    [T][X] Read book
```

## Unmarking tasks

To unmark a task as not done, use the `unmark` command followed by the task number.

Example: `unmark 1`

Expected outcome: Marks the first task in the list as not done.

```
Nice! I've marked this task as not done:
    [T][] Read book
```

## Deleting tasks

To delete a task, use the `delete` command followed by the task number.

Example: `delete 1`

Expected outcome: Deletes the first task in the list.

```
Noted. I've removed this task:
    [T][] Read book
Now you have 2 tasks in the list.
```

## Archiving tasks

To archive the current state of the task list, use the `archive` command.

Example: `archive`

Expected outcome: Archives the current task list to a new file with a timestamp.

```
Task list has been archived successfully.
```

## Stopping the application

To stop the application, use the `bye` command.

Example: `bye`

Expected outcome: Stops the application.
