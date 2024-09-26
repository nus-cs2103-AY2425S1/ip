# Mylo User Guide

**Mylo is a chatbot that helps you declutter your mind by keeping track of everything you need to do. It is:**

- text-based
- intuitive and easy to use
- ~~FAST~~ LIGHTNING FAST — get things done in no time!

![Screenshot of Mylo's UI](./Ui.png)

Getting started is simple:

1. Download it from [here](https://github.com/cweijin/ip/releases).
2. Double-click to launch.
3. Add your tasks.
4. Let Mylo handle the rest! 😉

# Features

Mylo's features include (click to be redirected):
- [Adding tasks](#adding-tasks)
- [Deleting tasks](#deleting-tasks)
- [Updating task's completion status](#updating-completion-status)
- [Viewing tasks (full/filtered)](#viewing-tasks)
- [Searching tasks by keywords](#searching-tasks)
- [Exiting program](#exiting-mylo)

## Adding Tasks

Mylo recognizes three types of tasks: [Todo](#todo), [Event](#event) and [Deadline](#deadline).

### Todo

A Todo is a simple task that includes only a task title.

When a task does not have a specific date or deadline, you can add it as a Todo.

To add a Todo:

Command: `todo <task title>`

Example: `todo Update UserGuide`

Expected output:

```
Got it. I've added this task:
 [T][ ] Update UserGuide
Now you have 1 tasks in the list.
```

### Event

An Event is a task that includes both a start date and an end date.

To add an Event:

Command: `event <task title> /from <dd-mm-yyyy hhmm> /to <dd-mm-yyyy hhmm>`

Example: `event CS2109S Midterm /from 07-10-2024 1610 /to 07-10-2024 1740`

Expected output:

```
Got it. I've added this task:
 [E][ ] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
Now you have 2 tasks in the list.
```

### Deadline

A Deadline is a task with a due date.

To add a Deadline:

Command: `deadline <task title> /by <dd-mm-yyyy hhmm>`

Example: `deadline iP Submission /by 26-09-2024 2359`

Expected output:

```
Got it. I've added this task: 
 [D][ ] iP Submission (by: Thu 26 Sep 2024 23:59)
Now you have 4 tasks in the list.
```

## Deleting Tasks

Mylo supports deleting tasks if you added the wrong task or no longer need to track a task.

To delete a task:

Command: `delete <index of task to be deleted>`

Example: `delete 1`

Expected output:

```
Noted. I've removed this task:
 [T][ ] Update UserGuide
Now you have 2 tasks in the list.
```

## Updating Completion Status

Each task in Mylo tracks its completion status:

\[ ] indicates an incomplete task.

\[X] indicates a completed task.

Here's how to mark a task as done or undone.

### Mark As Done

Command: `mark <index of task to be marked as done>`

Example: `delete 1`

Expected output:

```
Nice! I've marked this task as done: 
[E][X] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
```

### Mark As Undone

Command: `mark <index of task to be marked as undone>`

Example: `unmark 1`

Expected output:

```
OK, I've marked this task as not done yet: 
[E][ ] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
```

## Viewing Tasks

With a list of tasks recorded, you may want to view them.

Mylo supports viewing the task list, either filtered or unfiltered:
- [Viewing All Tasks](#all-tasks)
- [Viewing Tasks for Current Date](#current-date)
- [Viewing Tasks for a Specific Date](#specific-date)

### All Tasks

Mylo can list all tasks being tracked.

To view all tasks:

Command: `list`

Expected output:

```
1. [E][ ] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
2. [D][ ] iP Submission (by: Thu 26 Sep 2024 23:59)
```

### Current Date

Mylo provides a shorthand for viewing tasks ongoing or due on the current date.

To view tasks on the current date:

Command: `list today`

Expected output:

```
1. [D][ ] iP Submission (by: Thu 26 Sep 2024 23:59)
```

### Specific Date

Mylo also supports viewing tasks ongoing or due on a specific date.

To view tasks on a specific date:

Command: `list on <dd-mm-yyyy>`

Example: `list on 07-10-2024`

Expected output:

```
1. [E][ ] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
```

## Searching Tasks

As your task list grows larger, searching for tasks by keyword can be helpful.

Mylo supports task searches by keyword.

To search for a task:

Command: `find <keyword>`

Example: `find mid`

Expected output:

```
1. [E][ ] CS2109S Midterm (from: Mon 07 Oct 2024 16:10 to: Mon 07 Oct 2024 17:40)
```

## Exiting Mylo

Mylo also provides a way to exit the program gracefully.

To exit the program, simply type:

Command: `bye`

## Acknowledgements

### Usage of AI Tools

ChatGPT was used to refine the JavaDoc draft and this README.