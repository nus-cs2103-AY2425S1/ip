# Molly User Guide

Prerequisites: JDK 17.
- **For Windows Users:** [Oracle Version](https://www.oracle.com/java/technologies/downloads/#java17)
- **For Mac Users:** [JDK 17 (Azul)](https://se-education.org/guides/tutorials/javaInstallationMac.html)

Molly frees your mind from remembering tasks and lets you focus on what truly matters. It's:

- **text-based**
- **easy to use**
- **FAST** 🚀

## Table of Contents
1. [Getting Started](#getting-started)
2. [Features](#features)
    - [Viewing Help : `/help`](#viewing-help--help)
    - [Add a Todo : `todo`](#add-a-todo--todo)
    - [Add a Deadline : `deadline`](#add-a-deadline--deadline)
    - [Add an Event : `event`](#add-an-event--event)
    - [List All Tasks : `list`](#list-all-tasks--list)
    - [Mark a Task as Done : `mark`](#mark-a-task-as-done--mark)
    - [Unmark a Task as Done : `unmark`](#unmark-a-task-as-done--unmark)
    - [Delete Tasks : `delete`](#delete-tasks--delete)
    - [Find Tasks by Keyword : `find`](#find-tasks-by-keyword--find)
    - [View Reminders : `reminders`](#view-reminders--reminders)
    - [Exiting Molly : `bye`](#exiting-molly--bye)

### Getting Started

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest Molly.jar file from [here](https://github.com/adipanda2002/ip/releases).
3. Place the file into the folder you want to use as the _home folder_ for Molly.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Molly.jar` command to run the application.
   A GUI similar to the below should appear in a few seconds.
   ![Initial GUI upon running Molly](./initialUI.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
6. Refer to the [Features](#features) below for details of each command

# Features:

## Viewing help : `/help`
Shows a help message with instructions on command formats.

Format: `/help`

![Molly help command interaction](./help.PNG)

## Add a todo : `todo`
Adds a new task with the given description to your list.

Format: `todo <description>`

Examples:
- `todo plan presentation`
- `todo wash the dishes`

![Molly todo command interaction](./todo.PNG)

### Add a deadline : `deadline`
Adds a new task with the given description and deadline to your list.

Format: `deadline <description> /by <DD-MM-YYYY HHmm>`

Examples:
- `deadline math homework /by 20-09-2024 2359`

![Molly deadline command interaction](./deadline.PNG)

## Add an event : `event`
Adds a new event with the given description, start and end dates/times to your list.

Format: `event <task description> /from <DD-MM-YYYY HHmm> /to <DD-MM-YYYY HHmm>`

Note that the end date/time must be after the start date/time

Examples:
- `event attend the f1 race /from 22-09-2024 1600 /to 22-09-2024 2200`

![Molly event command interaction](./event.PNG)

## List all tasks : `list`
Lists all the tasks currently in your list.

Format: `list`

![Molly list command interaction](./list.PNG)

## Mark a task as done : `mark`
Marks the selected task as done with an 'X'

Format: `mark <task number>`

Examples:
- `mark 2`
- `mark 1`

![Molly mark command interaction](./mark.PNG)

Note that the task number input must be smaller than or equal to the number of tasks in your list.

## Unmark a task as done : `unmark`
Unmarks the selected task on the list.

Format: `unmark <task number>`

Examples:
- `unmark 2`
- `unmark 1`

![Molly unmark command interaction](./unmark.PNG)

Note that the task number input must be smaller than or equal to the number of tasks in your list.

## Delete tasks : `delete`
Deletes the selected task from the list.

Format: `delete <task number>`

Examples:
- `delete 2`
- `delete 1`

![Molly delete command interaction](./delete.PNG)

Note that the task number input must be smaller than or equal to the number of tasks in your list.

## Find tasks by keyword : `find`
Find tasks containing the text following the command `find`.

Format: `find <keyword>`

Examples:
- `find f1`

![Molly find command interaction](./find.PNG)

## View Reminders : `reminders`
View events coming up in the next 3 days.

Format: `reminders`

![Molly reminders command interaction](./reminders.PNG)

## Exiting Molly : `bye`
Saves your tasks and exits Molly.

Format: `bye`

![Molly bye command interaction](./bye.PNG)

This user guide aims to be as concise as possible, while still providing enough detail for you to use all the key features of Molly.

For more complex operations, refer to specific sections of this user guide.
