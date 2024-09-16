# Applemazer User Guide

<p align="center">
   <img src="Ui.png">
</p>

Applemazer is an **intelligent chatbot application designed to optimize your task management workflow.**
* [Quick start](#quick-start)
* [Features](#features)
  * [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
  * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  * [Adding an Event task: `event`](#adding-an-event-task-event)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
  * [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Finding a specific task: `find`](#finding-a-specific-task-find)
  * [Saving the program](#saving-the-program)

## Quick start
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest release from [here](https://github.com/kaajinn/ip/releases).
3. Within the folder you placed the `.jar` file in, open a command terminal and call `java -jar Applemazer.jar`.
4. A GUI similar to the above picture should appear.
5. Refer to the [Features](#features) section for more details of the commands to use.

## Features

### Adding a Todo task: `todo`
Adds a todo task to keep track of.

Format: `todo DESCRIPTION`

Examples:
* `todo lab assignment`

Expected outcome:
```
Got it. I've added this task:
   [T][] lab assignment
Now you have 1 tasks in the list.
```


### Adding a Deadline task: `deadline`
Adds a deadline task to keep track of.

Format: `deadline DESCRIPTION /by DEADLINE`
* DEADLINE should follow the format `<yyyy-mm-dd> <HHmm>` or `<dd/MM/yyyy> <HHmm>`.
* `<HHmm>` is optional.

Examples:
* `deadline CS2103T iP /by 20/09/2024 2359`

Expected outcome:
```
Got it. I've added this task:
   [D][] CS2103T iP (by: 20 Sep 2024 11:59pm)
Now you have 2 tasks in the list.
```


### Adding an Event task: `event`
Adds an event task to keep track of.

Format: `event DESCRIPTION /from DATE1 /to DATE2`
* DATE1 and DATE2 should follow the format `<yyyy-mm-dd> <HHmm>` or `<dd/MM/yyyy> <HHmm>`.
* `<HHmm>` is optional.

Examples:
* `event recess week /from 2024-09-21 /to 29/09/2024`

Expected outcome:
```
Got it. I've added this task:
   [E][] recess week (from: 21 Sep 2024 to: 29 Sep 2024)
Now you have 3 tasks in the list.
```


### Exiting the program: `bye`
Exits the program after displaying a farewell message.

Format: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```


### Listing all tasks: `list`
Lists down all tasks that are currently being tracked.

Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] homework
2. [D][] CS2103T iP (by: 20 Sep 2024 11:59pm)
3. [E][] recess week (from: 21 Sep 2024 to: 29 Sep 2024)
```


### Marking a task as done: `mark`
Marks a task as done.

Format: `mark TASK_NUMBER`

Examples:
* `mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
   [D][X] CS2103T iP (by: 20 Sep 2024 11:59pm)
```


### Marking a task as not done: `unmark`
Marks a task as not done.

Format: `unmark TASK_NUMBER`

Examples:
* `unmark 2`

Expected outcome:
```
OK, I've marked this task as not done yet:
   [D][] CS2103T iP (by: 20 Sep 2024 11:59pm)
```


### Deleting a task: `delete`
Deletes a task that is currently being tracked.

Format: `delete TASK_NUMBER`

Examples:
* `delete 1`

Expected outcome:
```
Noted. I've removed this task:
   [T][] homework
Now you have 2 tasks in the list.
```


### Finding a specific task: `find`
Lists down all tasks that match or contain the given description.

Format: `find DESCRIPTION`
* The search is case-sensitive. For example, `Week` does not match `week`.

Examples:
* `find week`

Expected outcome:
```
Here are the matching tasks in your list:
2. [E][] recess week (from: 21 Sep 2024 to: 29 Sep 2024)
```


### Saving the program
The program saves all data automatically when any changes are made.
