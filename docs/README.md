# John Cena Task Manager User Guide

Meet John Cena Task Manager, your dedicated assistant for managing daily tasks, deadlines, and events. Designed to streamline your schedule and keep you on top of your commitments, John Cena Task Manager makes organizing your life simpler and more efficient.

- [Quick Start](#quick-start)
- [Features and Commands](#john-cena-task-manager-supports-the-following-features)
    - [Adding Todo tasks: `todo`](#1-adding-a-to-do-task)
    - [Adding Deadline tasks: `deadline`](#2-adding-a-deadline)
    - [Adding Event tasks: `event`](#3-adding-an-event)
    - [Adding tasks after a certain date: `after`](#4-adding-a-task-to-be-done-after-a-certain-date)
    - [Finding tasks on a certain date: `on`](#5-find-tasks-on-a-certain-date)
    - [Finding matching tasks: `find`](#6-find-matching-tasks)
    - [Marking task as done: `mark`](#7-mark-a-task-as-done)
    - [Marking task as not done: `unmark`](#8-unmark-a-task-mark-a-task-as-not-done)
    - [Deleting a task: `delete`](#9-delete-a-task)
    - [Viewing the task list: `list`](#10-view-all-tasks)
    - [Hello Interaction: `hello`](#11-hello-interaction)
    - [List all commands: `help`](#12-list-all-commands)
    - [Exit the program: `bye`](#13-exit-the-program)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

# Quick Start

1. Ensure you have Java `17` or above installed on your computer.

1. Download the latest `.jar` file from [here](https://github.com/ishan-agarwal-05/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your John Cena Task Manager.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar JohnCena.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will show you a list of commands.<br>
   Some example commands you can try:

    * `list` : Lists all tasks.

    * `Todo swim` : Add a new `Todo` task to swim in the current list.

    * `delete 3` : Deletes the 3rd task shown in the current list.

    * `bye` : Exits the app.

1. Refer to the [Features](#john-cena-task-manager-supports-the-following-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## John Cena Task Manager Supports the Following Features:

> [!NOTE]
> Notes about the syntax format:
> - Words in single quotation mark `''` are the parameters to be supplied by the user.<br>
    The parameters should follow the guide in the single quotation mark.<br>
    e.g. in `Todo 'Description'`, `'Description'` is a parameter which can be used as `Todo Sleep`.
> - Extra arguments in most of the command types will be interpreted as a wrong format error.
> - The date and time format should be in `yyyy-mm-dd HHmm` format.
> - The commands are case-sensitive. e.g. `todo` will work, but `Todo` will not work.

### 1. Adding a To-do task

To add a To-do to the storage, use the following format:

`todo 'Description'`

Example: `todo eat dinner`

```
 Alright, Champ! I've added this task: 
   [T] [ ] eat dinner
 Now you have 1 tasks in the list. Keep hustling!
```

### 2. Adding a Deadline

To add a task with a deadline to the list, use the following format:

`deadline 'Description' /by 'yyyy-mm-dd HHmm'`

Example: `deadline CS2100 /by 2024-09-25 1600`

Response:
```
 Got it. I've added this task: 
   [D] [ ] CS2100 (by: 2024-09-25 1600)
 Now you have 2 tasks in the list.
```

### 3. Adding an Event

To add an Event to the list, use the following format:

`event 'Description' /from 'yyyy-mm-dd HHmm' /to 'yyyy-mm-dd HHmm'`

Example: `event birthday /from 2024-09-22 0001 /to 2024-09-22 2359`

Response:
```
 Got it. I've added this task: 
   [E] [ ] birthday (from: 2024-09-22 0001 to: 2024-09-22 2359)
 Now you have 3 tasks in the list. Keep hustling!
```

### 4. Adding a task to be done after a certain date

To add a task to be done after a certain date, use the following format:

`after 'Description' /after 'yyyy-mm-dd HHmm'`

Example: `after party /after 2024-09-25 1600`

Response:
```
 Got it. I've added this task: 
   [A] [ ] party (after: 2024-09-25 1600)
 Now you have 4 tasks in the list.
```

### 5. Find tasks on a certain date

To find tasks on a certain date, use the following format:

`on 'yyyy-mm-dd'`

Example:
`on 2024-09-22`

Response:
```
 Alright, Champ! Here are the tasks on Sep 22 2024:
  1. [E] [ ] birthday (from: 2024-09-22 0001 to: 2024-09-22 2359)
  5. [D] [ ] submit report (by: 2024-09-22 1600)
```

### 6. Find matching tasks

To find matching tasks for some keywords, use the following format:

`find 'one or more keywords'`

* The search is case-sensitive. e.g `Eat` will match `eat`
* Non-space words will be used to search. e.g `eat lunch` will be divided into `eat` and `lunch` to search
* Partial words will be matched e.g. `lun` will match `lunch`

Example:
`find eat`

Response:
```
  Here are the matching tasks in your list:
  1. [T] [ ] eat dinner
  5. [T] [ ] eat lunch
```

Example:
`find eat lun`

Response:
```
  Here are the matching tasks in your list:
  5. [T] [ ] eat lunch
```

The response can be interpreted as follows:
- Event type (`T` is a Todo, `E` is an Event, `D` is a Deadline, 'A' is an After)
- Status (`[X]` for completed, `[ ]` otherwise)
- Description
- Start and end dates (if applicable) in `yyyy-mm-dd HHmm` format

### 7. Mark a task as done

To mark a task as done, use the following format:

`mark 'ordinal number of the task'`

* Mark at the specified `index`.
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer** 1, 2, 3, …
* The index must be valid, i.e., **within the range of the current task list**.
* The task will be marked as done with `[X]`.

Example:
`mark 2`

Response:
```
 You did it, Champ! I've marked this task as done:
   [D] [X] CS2100 (by: 2024-09-25 1600)
```

### 8. Unmark a task/ Mark a task as not done

To unmark a task as done, use the following format:

`unmark 'ordinal number of the task'`

* Unmark at the specified `index`.
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer** 1, 2, 3, …
* The index must be valid, i.e., **within the range of the current task list**.
* The task will be marked as not done with `[ ]`.

Example:
`unmark 2`

Response:
```
 Alright, Champ! I've marked this task as not done yet:
   [D] [ ] CS2100 (by: 2024-09-25 1600)
```

### 9. Delete a task

To delete a task, use the following format:

`delete 'index'`

* Deletes the task at the specified `index`.
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer** 1, 2, 3, …
* The index must be valid, i.e., **within the range of the current task list**.
* The task will be removed from the list.

Example:
`delete 2`

Response:
```
 Noted. I've removed this task. Now, you can't see me and neither can you see that task:
   [D] [ ] CS2100 (by: 2024-09-25 1600)
 Now you have 4 tasks in the list.
```
> [!NOTE]
> After a task is deleted, the remaining tasks will be automatically renumbered.<br>
> E.g: task list has 3 tasks with index 1, 2, 3, after deleting task 2, old task 3 will become new task 2.

### 10. View all tasks

To view all tasks, use the following command:

`list`

Example:
`list`

Response:
```
  Alright, Champ! Here are the tasks in your list:
  1. [T] [ ] eat dinner
  2. [E] [ ] birthday (from: 2024-09-22 0001 to: 2024-09-22 2359)
  3. [A] [ ] party (after: 2024-09-25 1600)
  4. [T] [ ] eat lunch
```

### 11. Hello Interaction

To say hi to  the chatbot, use anyone of the following command:

`hello`, `hi`, `hey`, `yo`, `sup`

The chatbot will respond with a greeting message.

Example:
`hi`

Response:
```
Hello from
John Cena
You can't see me! But I'm here to help. What can I do for you today?
```
### 12. List all commands

To view all commands, use anyone of the following command:

`help`, `commands`, `command`, `cmds`, `cmd`

The chatbot will list all the commands available with their formats and examples.

Example:
`help`

Response:
```
Here are the commands you can use:
    bye – Exits the program
    list – Lists all tasks
    mark [task number] – Marks a task as done
    unmark [task number] – Marks a task as not done
    delete [task number] – Deletes a task
    todo [description] – Adds a todo task
    deadline [description] /by [due date] – Adds a deadline task
    event [description] /from [start date] /to [end date] – Adds an event task
    find [keyword] – Finds tasks with a specific keyword
    after [description] /after [date] – Adds a task that you will do after a specific date
    on [date] – Lists all tasks on a specific date
    hello – Displays the welcome message
    help – Displays the list of commands
```
### 13. Exit the program

To exit the program, use the following command:

`bye`

The program will close.

--------------------------------------------------------------------------------------------------------------------
## Known Issues

1. **Command Overwriting**: If users attempt to add a task with the same description as an existing one, the app does not prevent duplicates. Users should manage their tasks accordingly.
2. **Not Case-Sensitive**: The app is not case-sensitive, so users should ensure they input commands in lowercase.
3. **Date and Time Format**: The app only accepts dates and times in the `yyyy-mm-dd HHmm` format. Users should ensure they input dates and times correctly.

--------------------------------------------------------------------------------------------------------------------
## Command Summary

| Action                        | Format, Examples                                                                                                                        |
|-------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| **Add To-do**                 | `todo 'Description'`<br> e.g., `todo eat dinner`                                                                                        |
| **Add Deadline**              | `deadline 'Description' /by 'yyyy-mm-dd HHmm'`<br> e.g., `deadline CS2100 /by 2024-09-25 1600`                                          |
| **Add Event**                 | `event 'Description' /from 'yyyy-mm-dd HHmm' /to 'yyyy-mm-dd HHmm'`<br> e.g., `event birthday /from 2024-09-22 0001 /to 2024-09-22 2359` |
| **Add Task After a Date**     | `after 'Description' /after 'yyyy-mm-dd HHmm'`<br> e.g., `after party /after 2024-09-25 1600`                                           |
| **Find Tasks on a Date**      | `on 'yyyy-mm-dd'`<br> e.g., `on 2024-09-22`                                                                                             |
| **Find Matching Tasks**       | `find 'one or more keywords'`<br> e.g., `find eat lun`                                                                                  |
| **Mark Task as Done**         | `mark 'ordinal number of the task'`<br> e.g., `mark 2`                                                                                  |
| **Unmark Task (Mark as Not Done)** | `unmark 'ordinal number of the task'`<br> e.g., `unmark 2`                                                                              |
| **Delete Task**               | `delete 'ordinal number of the task'`<br> e.g., `delete 2`                                                                              |
| **View All Tasks**            | `list`                                                                                                                                  |
| **Hello Interaction**         | `hello`, `hi`, `hey`, `yo`, `sup`                                                                                                       |
| **List All Commands**         | `help`, `commands`, `command`, `cmds`, `cmd`                                                                                                                                  |
| **Exit the Program**          | `bye`                                                                                                                                   |