---
layout: page
title: RizzBOT User Guide
---

# RizzBOT User Guide

![img.png](img.png)


> “CS students can't get Women. Let us help you!” – this guy probably [(source)](https://www.youtube.com/watch?v=2qBlE2-WL60)

RizzBOT frees your mind of having to remember your dates and things to do for yo girl. It’s,
- Text-based
- Easy to learn
- ~~**FAST**~~ _SUPER FAST_ to use

# Content Page

- [Quick Start](#Quick-start)
- [Commands](#Commands)
    * [ToDo](#Create-Todo)
    * [Deadline](#Create-Deadline)
    * [Event](#Create-Event)
    * [List](#Display-list)
    * [Mark](#Marking-and-Unmarking-tasks)
    * [Delete](#Delete-tasks)
    * [Find](#Finding-keywords-in-tasks)
    * [Undo](#Undo-tasks)
    * [Bye](#Bye - Bye!)
- [Features](#features)
    * [Saving & Loading of tasks](#Save-and-load-tasks)

# Quick-start
Ensure you have Java 17 or above installed in your Computer.
1. **Download** the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).


2. **Save** `rizz.jar` file to the folder you want to use as the _home folder_.
 

3. **Open** a command terminal, `cd` into the folder you put the jar file in.
   

4. **Run** the application using the following command: `java -jar addressbook.jar` 


5.  **Type** the command in the command box and press Enter to execute it. e.g. typing `bye` and pressing Enter will open the help window.
    <br> Some example commands you can try:

     * `todo Read book` : Adds a task to do.

     * `mark 1`: Marks the 1st task added

     * `list` : Lists all contacts.

     * `bye` : Exits application
       

6.  **Refer** to the [Features](#features) below for details of each command.

    

# Commands
> Note:
> Curly brackets `{}` refer to the input string for the arguments of the command. Do not type them in the command itself.
> The `/` is used as a marker to parse subsequent inputs, avoid using `/` in input.
------------------------------------------------------------------------------------------------------------------------


| **No.** |      **Command**      |                 **Description**                  |                    **Format**                    |                    **Example**                    |
|:-------:|:---------------------:|:------------------------------------------------:|:------------------------------------------------:|:-------------------------------------------------:|
|    1    |  **Add To-Do Task**   | Adds a task with no date or time specifications. |                `todo {task name}`                |                  `todo buy gift`                  |
|    2    | **Add Deadline Task** |           Adds a task with a deadline.           |    `deadline {task name} /by {due date} {by}`    |        `deadline homework 2025-02-14 2359`        |
|    3    |  **Add Event Task**   |      Adds a task with a start and end date.      | `event {task name} {date} /from {from} /to {to}` | `event date with girlfriend 2025-02-14 1000 1200` |
|    4    |    **List Tasks**     |         Displays all tasks in the list.          |                      `list`                      |                      `list`                       |
|    5    |   **Mark Task(s)**    |              Marks a task as done.               |              `mark {task index...}`              |                   `mark 1 2 3`                    |
|    6    |  **Unmark Task(s)**   |           Unmarks a task as not done.            |             `unmark {task index...}`             |                   `unmark 3 4`                    |
|    7    |  **Delete Task(s)**   |          Deletes a task from the list.           |             `delete {task index...}`             |                    `delete 2`                     |
|    8    |     **Find Task**     |      Searches for tasks containing keyword.      |                 `find {keyword}`                 |                  `find homework`                  |
|    9    |       **Undo**        |              Undo previous command.              |                      `undo`                      |                      `undo`                       |
|    9    |        **Bye**        |                Exits the program.                |                      `bye`                       |                       `bye`                       |

------------------------------------------------------------------------------------------------------------------------

### Create Todo
A ToDo is a subclass of the abstract class `Task`. It has no start date and no deadline.

Command format: `todo {task name}`

Command example: `todo buy gift` will create a ToDo task to buy a gift.

Expected output:
```
New ToDo added!!
    [T][] read book
```

### Create Deadline

A Deadline is a subclass of the abstract class `Task`. A Deadline is a task that needs to be completed by specific timing.

The timing specified follows a date `{due date}` written in the `yyyy-MM-dd` format, followed by 24HR daily time `{by}` of `HHmm` format.

Command format: `deadline {task name} /by {due date} {by}`

Command example: `deadline do homework 22-09-24 1800`

Expected Output:
```
New Deadline Added!!
    [D][] do her homework (by: Aug 08 2023)
```
### Create Event
A Deadline is a subclass of the abstract class `Task`.An Event is a task that needs to be completed during a certain period.

The timing specified follows a date `{date}` written in the `yyyy-MM-dd` format, followed by 24HR daily time `{from}` and `{to}`of `HHmm` format, to indicate the duration spent at this event.

Command format: `event {task name} {date} /from {from} /to {to}`

Command example: `event date with girlfriend 2025-02-14 1000 1200`

Expected Output:
```
New Event Added!!
    [E][] date with girlfriend  (from: Feb 12 2025 1000-1200)
```

Additional details: In hindsight, each event can be longer than a day so would be better to include end date too.


### Display list
To print all the tasks that have been created simply type the command `list`

Command format: `list`

Expected output:
```
Here's your list buddy!!
1. [T][ ] buy gift
2. [T][ ] visit yo girl @NUS
3. [E][ ] treat girlfriend dinner (at: Feb 12 2025 1800-2000)
4. [D][ ] do her homework and submit (by: Feb 12 2025)
5. [T][ ] sleep
```

### Marking and Unmarking tasks
Marking tasks allows users to mark tasks as done.

Command format: `mark {task index...}`

Command example: `mark 1`

Expected output:
```
Nice! I've marked these tasks as done:
[T][X] buy gift
```

Similarly, unmarking tasks allows users to unmark tasks.

Command format: `unmark {task index...}`

Command example: `unmark 1`

Expected output:
```
Nice! I've unmarked these tasks:
[T][ ] buy gift
```

### Delete tasks
Users can also delete tasks with the `delete` keyword.

Command format: `delete {task index...}`

Command example: `delete 1`

Expected output:
```
Noted. I've removed these task:
[T][ ] sleep

Now you have 3 tasks in the list.
1. [T][ ] visit yo girl @NUS
2. [E][ ] treat girlfriend dinner (at: Feb 12 2025 1800-2000)
3. [D][ ] do her homework and submit (by: Feb 12 2025)
```

###  Finding keywords in tasks
Users can quickly find tasks by searching for a word/letter in the task name. All the tasks that contain the search word/letter will be shown in the order in which they appear in the list along with the respective task number.

Command format: `find {keyword}`

Command example: `find homework`

Expected output:
```
I found some! Here are the matching tasks in your list:
1. [D][ ] do her homework and submit (by: Feb 12 2025)
```

# Undo-tasks
Users can revert the last action made, whether it is adding, deleting, marking, or editing a task. This is useful if you accidentally make changes to the task list and want to restore the previous state.

Whenever a task is added, deleted, marked, or unmarked, the current state of the task list is saved. Using the undo command will revert the task list to the previous saved state

Command format: `undo`

Expected output:
```
Alright! I have undone the previous command!
```

### Bye Bye!
Users can exit the chatbot with the `bye` command. After 3.5 seconds, the app will automatically close.

Command format: `bye`

Expected output:
```
Bye bye!
```

# Features

### Save and load tasks

The application automatically saves all tasks to the hard disk after every command that modifies the task list. This ensures that no data is lost, even when you close the application. 

The tasks are stored in a text file, which is automatically reloaded when you start the application, allowing you to seamlessly continue from where you left off.

**How saving works:**

After every command that adds, deletes, or modifies a task (such as add, delete, mark, unmark), the application updates the task list in the save file.
The save file ensures that when you reopen the application, all your previous tasks are loaded and available, along with their respective statuses (completed, deadlines, events, etc.).
Loading on startup:

When the application is launched, it automatically loads all saved tasks from the last session.
Tasks that were marked as done will remain marked, and all task deadlines or events will also be loaded correctly.

**Saving Previous State for Undo Command**

In addition to saving and loading tasks, the application also supports an Undo feature. This allows users to revert their most recent action, providing flexibility in case an incorrect command is entered.



The Undo feature works by saving the state of the task list before any modification is made. Each time you execute a command, the previous state is saved so that you can revert back if needed.

**How the Undo feature works:**

Every time you modify the task list (e.g., add, delete, mark, or unmark), the current state of the task list is saved before the change.
The previous state can be restored with the undo command, reverting the list to what it was before the last change.


This feature is especially useful for accidental deletions or incorrect modifications.
This combination of task-saving, autoloading, and state management for undo ensures that users have a seamless and forgiving experience, with no risk of losing data or accidental changes.



