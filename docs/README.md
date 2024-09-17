# Meep User Guide

![Screenshot of Meep](Ui.png)

Meep is a friendly task management application designed to help you organize your daily activities, deadlines, and
events with ease. Its intuitive interface makes task management a breeze!

## Table of Contents

1. [Quick Start](#quick-start)
1. [Features](#features)
    1. [Adding a Todo](#adding-a-todo)
    1. [Adding a Deadline](#adding-a-deadline)
    1. [Adding an Event](#adding-an-event)
    1. [Listing Tasks](#listing-tasks)
    1. [Finding Tasks](#finding-tasks)
    1. [Marking Tasks as Done](#marking-tasks-as-done)
    1. [Unmarking Tasks](#unmarking-tasks)
    1. [Deleting Tasks](#deleting-tasks)
    1. [Archiving Tasks](#archiving-tasks)
    1. [Getting Help](#getting-help)
1. [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 17 or above installed on your computer.
1. Download the latest Meep.jar file from the [releases page](https://github.com/tata32000/ip/releases).
1. Double-click the downloaded Meep.jar file to launch the application. If that does not work, open a terminal and run
   `java -jar Meep.jar`.

## Features

### Adding a Todo

To add a simple todo task, use the `todo` command followed by the task description.

Example:

```
todo read a book
```

Meep will confirm the addition with a message like:

```
Got it! I've added this task:
  [T][ ] read a book
  
Now you have 1 tasks in the list.
```

### Adding a Deadline

To add a task with a deadline, use the `deadline` command followed by the task description and the `/by` keyword with
the due date and time.

Example:

```
deadline submit report /by 15/10/2024 1400
```

Meep will confirm:

```
Got it! I've added this task:
  [D][ ] submit report (by: 15th of October 2024, 2:00pm)
  
Now you have 2 tasks in the list.
```

### Adding an Event

To add an event, use the `event` command followed by the event description and the `/from` and `/to` keywords with start
and end dates and times.

Example:

```
event team meeting /from 16/10/2024 1000 /to 16/10/2024 1200
```

Meep will confirm:

```
Got it! I've added this task:
  [E][ ] team meeting (from: 16th of October 2024, 10:00am to: 16th of October 2024, 12:00pm)
  
Now you have 3 tasks in the list.
```

### Listing Tasks

To see all your tasks, use the `list` command.

Example:

```
list
```

Meep will display:

```
Here are the tasks in your list:
1.[T][ ] read a book
2.[D][ ] submit report (by: 15th of October 2024, 2:00pm)
3.[E][ ] team meeting (from: 16th of October 2024, 10:00am to: 16th of October 2024, 12:00pm)
```

### Finding Tasks

To find tasks containing specific keywords, use the `find` command followed by the keyword.

Example:

```
find report
```

Meep will show:

```
Here are the matching tasks in your list:
1.[D][ ] submit report (by: 15th of October 2024, 2:00pm)
```

### Marking Tasks as Done

To mark a task as done, use the `mark` command followed by the task number.

Example:

```
mark 1
```

Meep will confirm:

```
Nice! I've marked this task as done:
  [T][X] read a book
```

### Unmarking Tasks

To unmark a completed task, use the `unmark` command followed by the task number.

Example:

```
unmark 1
```

Meep will confirm:

```
Got it! I've unmarked this task:
  [T][ ] read a book
```

### Deleting Tasks

To delete a task, use the `delete` command followed by the task number.

Example:

```
delete 2
```

Meep will confirm:

```
Noted. I've removed this task:
  [D][ ] submit report (by: 15th of October 2024, 2:00pm)
  
Now you have 2 tasks in the list.
```

### Archiving Tasks

To archive a completed task, use the `archive` command followed by the task number.

Example:

```
archive 1
```

Meep will confirm:

```
Got it! I've archived this task:
  [T][X] read a book

Now you have 1 tasks in the list
```

Archived tasks will no longer be displayed in the task list. The archive can be accessed by opening the
`data.txt.archive` file

### Getting Help

To see a list of available commands and their usage, simply type `help`.

## Command Summary

| Command      | Format                                                                      | Example                                                        |
|--------------|-----------------------------------------------------------------------------|----------------------------------------------------------------|
| Add Todo     | `todo TASK_DESCRIPTION`                                                     | `todo read a book`                                             |
| Add Deadline | `deadline TASK_DESCRIPTION /by DATE TIME`                                   | `deadline submit report /by 15/10/2024 1400`                   |
| Add Event    | `event EVENT_DESCRIPTION /from START_DATE START_TIME /to END_DATE END_TIME` | `event team meeting /from 16/10/2024 1000 /to 16/10/2024 1200` |
| List Tasks   | `list`                                                                      | `list`                                                         |
| Find Tasks   | `find KEYWORD`                                                              | `find report`                                                  |
| Mark as Done | `mark TASK_NUMBER`                                                          | `mark 1`                                                       |
| Unmark Task  | `unmark TASK_NUMBER`                                                        | `unmark 1`                                                     |
| Delete Task  | `delete TASK_NUMBER`                                                        | `delete 2`                                                     |
| Archive Task | `archive TASK_NUMBER`                                                       | `archive 1`                                                    |
| Get Help     | `help`                                                                      | `help`                                                         |
| Exit Meep    | `bye`                                                                       | `bye`                                                          |

We hope you enjoy using Meep to organize your tasks efficiently!