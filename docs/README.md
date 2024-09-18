# Testament User Guide

![Screenshot of the Testament chatbot in use](Ui.png)

Testament is a desktop app for tracking and managing your tasks via a 
Command Line Interface (CLI), while retaining the benefits of a traditional 
Graphical User Interface (GUI). 

## Feature List
- Add To Do tasks
- Add Deadline tasks
- Add Event tasks
- View schedule
- Mark task as done
- Unmark task
- Search for task using keywords
- Delete tasks
- Archive all tasks
- Retrieve archived tasks
- Cute bye message!

## Note About Command Format
Words in `UPPER_CASE` are parameters that should be supplied by the user\
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that should be 
filled as `todo homework`

For commands that require a `TASK_NUMBER`, the `TASK_NUMBER` of the targeted 
task can be seen when the schedule command is called.

## Add To Do tasks
Adds a basic task to the schedule

Format: `todo DESCRIPTION`

Example: `todo 2103t ip`

Sample output

```
I've added the following task to your schedule:
[T][] 2103t ip
You have 1 tasks to complete
```

## Add Deadline tasks
Adds a task with a deadline to the schedule

Format: `deadline DESCRIPTION /by DEADLINE`

Note: `DEADLINE` should be in `YYYY-MM-DD` format

Example: `deadline 2100 assignment /by 2025-01-01`

Sample output

```
I've added the following task to your schedule:
[D][] 2100 assignment (by: 01 Jan 2025) 
You have 2 tasks to complete
```

## Add Event tasks
Adds a task with a start date and end date to the schedule

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

Note: `START_DATE` and `END_DATE` should be in `YYYY-MM-DD` format

Example: `event tournament /from 2024-09-14 /to 2024-09-15`

Sample output

```
I've added the following task to your schedule:
[E][X] tournament (from: 14 September 2024 to: 15 September 2024)
You have 3 tasks to complete
```

## View schedule
Shows a list of all tasks in the schedule

Format: `schedule`

Sample output

```
Your schedule is as follows:
1. [T][] 2103t ip
2. [D][] 2100 assignment (by: 01 Jan 2025) 
3. [E][] tournament (from: 14 September 2024 to: 15 September 2024)
```

## Mark task as done

Marks a task as done

Format: `mark TASK_NUMBER`

Example: `mark 1`

Sample output

```
Congratulations on completing your task:
[T][X] 2103t ip
```

## Unmark task

Unmarks a task

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

Sample output

```
This task has been unmarked:
[T][] 2103t ip
```

## Search for task using keywords

Searches for tasks with a specific string in their description

Format: `find KEYWORDS`

Example: `find 2103t`

Sample output

```
Here are the relevant tasks:
1. [T][] 2103t ip
```

## Delete tasks

Deletes a task

Format: `delete TASK_NUMBER`

Example: `delete 1`

Sample output

```
This task has been deleted:
1. [T][] 2103t ip
```

## Archive all tasks

Archives all tasks currently in the schedule, before clearing the schedule

Format: `archive`

Sample output

```
All tasks in the schedule have been archived.
```

## Retrieve archived tasks

Retrieves all tasks stored in the archive

Format: `restore`

Sample output

```
All tasks in the archive have been retrieved.
```

## Cute bye message!

Cheers up the user with a cute bye message!

Format: `bye`

Sample output

I'd say it's time for a tea break. Milk and sugar for you?
```