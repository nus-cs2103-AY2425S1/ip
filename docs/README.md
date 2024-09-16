# WansBot User Guide

![UI](https://github.com/user-attachments/assets/ece60c9f-0449-430d-9b9a-74d7683dc570)

WansBot is a simple **chat bot app for managing your daily tasks**, complete with a Graphical User Interface (GUI)! Other than that it can also **memorise answers to questions that you have that you may want remembered**. Since we all get forgetful sometimes it's nice to have a backup plan in the reliable WansBot!

LINKS TO BE ADDED HERE

***

## Quick Start

1. Ensure you have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed in your computer.
2. Download the latest .jar file [here](https://github.com/bigismols/ip/releases).
3. Open your terminal, `cd` into the folder the jar file is in and use `java -jar wansbot.jar` command to run the application
A GUI similar to the one you see at the top of this page should appear.

***

## Features

> [!NOTE]
Everything in brackets() are necessary but should be replcaed with a name. E.g. todos (task_name) should be something like todos Do homework.
All commands are case insensitive.

## Adding ToDos: `todos`

Adds a todo task to the user's task list.

Format: `todos (task_name)`

Examples:
- `todos read`
- `todos study for exams`

***

## Adding Tasks with Deadlines: `deadline`

Adds a task with a deadline

Format: `deadline (task_name) /by (date)`

> [!IMPORTANT]
Your (date) here must be in the format YYYY-MM-DD. WansBot will tell you that your deadline is formatted wrongly otherwise!

Examples:
- `deadline read /by 2024-01-01
- `deadline study bio /by 2020-11-15

***

## Adding Tasks with a start and end date: `event`

Adds a task to the user's task list with a start date and an end date.

Format: `event  (task_name) /from (start_date) /to (end_date)`

Examples:
- `event recess week /from 2024-09-20 /to 2024-09-27`
- `event exam period /from 2024-11-15 /to 2024-11-30`

***

## Listing all tasks: `list`

Lists all the current tasks in user's task list currently. All tasks are listed with 2 square brackets before the task name and additional information. The first square bracket contains the type of task e.g. todos, deadline, or event and the second square bracket indicates whether the task has been completed or not. X means complete. Marking will be discussed later in this guide. Below is an example of how todos, deadlines, and events would be shown when `list` is used.

![image](https://github.com/user-attachments/assets/c281b6a9-b9fa-4484-9e4d-81e84f2547e0)

Format: `list`

***

## Marking tasks complete: `mark`

Marks a task as complete.

Format: `mark (task_number)

Examples:
- `mark 1`
- `mark 10`

> [!NOTE]
> Task number will correspond to the number on the left of the list when `list` is used.

***

## Unmarking tasks: `unmark`

Unmarks a task and label it as incomplete ie. no X.

Format: `unmark (task_number)`

Examples:
- `unmark 2`
- `unmark 11`

***

## Removing tasks: `remove`

Removes task from the user's task list.

Format: `remove (task_number)`

Examples:
- `remove 3`
- `remove 13`

> [!NOTE]
> This is not to be confused with marking and unmarking. This COMPLETELY removes the task from the task list and **will also shift task numbers after the removed task**.
> Ensure that you do a `list` command to see the updated task numbers.

***

## Finding tasks by date: `findtask`

Finds a task that falls on or within a particular date. Todos will **NOT** be displayed here. Display format is similar to [list](#listing-all-tasks-list).



