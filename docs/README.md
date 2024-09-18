# Tayoo User Guide

>"For every minute spent in organizing, an hour is earned." - Benjamin Franklin

Tayoo is a **chat bot for managing your tasks, deadlines and events** via a GUI. The bot is designed to be lightweight and can be downloaded run directly on your own laptop, making it a convenient and portable solution to managing all your day-to-day tasks!

---
## Quick start

1. Ensure you have Java 17 or above installed in your computer. You can install it from [here](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
2. Download the latest `.jar` file from [here](https://github.com/jtooya/ip/releases)
3. Copy the file to the folder you want to use as the *home folder* of your task list.
4. Open a command terminal, `cd` into the folder you put the jar file in, and type `java -jar tayoo.jar`  then press `enter` to run the application.
	1. For windows users, to open a command terminal, press `Windows + r` at the same time, type in cmd to the resulting prompt, then press "OK".
	
	![[cmd_prompt.png]]
	
	2. For Mac users, press `Command + Space` to open Spotlight, type "Terminal" and press `enter`.
5. Type your command in the text field at the bottom and press `Enter` or "Send" to execute it.

Some example command you can try:
- `List`: Lists out all your tasks
- `ToDo Return book`: Adds a new ToDo task to your task list
- `Delete 3`: Deletes the 3rd task in your task list
- `Delete all`: Clears all the tasks in your task list
6. Refer to the the [[#Features|features]] below for details of each command

---
## Features

>[!info] Info about command format:
>1. Words in `UPPER_CASE` are the parameters to be supplied by the user (That's you!)
>	e.g. `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as `todo Return book`
>
>2. Items with `...` after them can be used multiple times, but must be used at least once
>
>3. Parameters MUST be in the order specified
>
>4. All command names are case insensitive
>	e.g. `ToDO` is the same as `todo` 

### Listing out all the tasks: `List`
Lists out all the tasks that you currently have in your tasklist.

Format: `list`

## Creating a new ToDo: `Todo`

A "ToDo" is a task that does not have a deadline, or a start and end. So you can get to it at any time!

Format: `todo TASK_NAME`

>[!Info]- About task names
> All task names will be stored in a case-sensitive manner! So if you type `todo reTurN boOk`, it will be showed as `reTurN boOk`

## Creating a new Deadline: `Deadline`

A "Deadline" is a task with a deadline.

Format: `deadline TASK_NAME \by TASK_DEADLINE`

>[!Extra info]-
>The chat bot will be able to interpret most date and time formats and standardise them into the same format. e.g. `deadline Complete homework \by 01-01-2024` will be interpreted the same as `deadline Complete homework \by 01 Jan 2024`. 

Examples:
- `deadline Return book /by 01-01-2024`

## Creating a new Event: `Event`

An "Event" is a task with both a start date/time and an end date/time.

Format: `event TASK_NAME /from START_DATETIME /to END_DATETIME`

Examples:
- `Event Project Meeting /from 18/09/2024 18:30 /to 19:00`
- `Event Tuition lesson /from 1200 /to 1300`
## Marking your task as complete/incomplete: `Mark`/`Unmark`

Marks your task as completed or not yet completed. 

Format: `Mark TASK_NUMBER` OR `Unmark TASK_NUMBER`

- If a task was previously marked as completed and you run `mark`, there will be no change
- Similarly, if a task was previously marked as incomplete, running `unmark` will result in no change
- The number must refer to the number shown in the display task `list`
- The index **must be a positive integer** 1, 2, 3...

## Searching for a task by name: `Find`

Looks through all your tasks and looks for any tasks whose names matches the keyword.

Format: `Find KEYWORD

Examples:
- `Find book` will find all tasks that have the word "book" in their title.

## Deleting a task: `Delete` or `Remove`

Deletes a specific task from the task list

Format `delete TASK_NUMBER` OR `Remove TASK_NUMBER`

- Deletes the task at the specified `TASK_NUMBER`
- The number must refer to the number shown in the display task `list`
- The index **must be a positive integer** 1, 2, 3...

## Deleting all tasks: `Delete all` or `Remove all`

Deletes all the tasks from the task list.

<span style="color:#ff0000">**WARNING: THIS IS NOT REVERSIBLE, ALL YOUR DATA WILL BE LOST**</span>

Format: `Delete all` OR `Remove all`

## Exiting the chat bot: `Exit`

Exits the chat bot

>[!Tip]-
>You can also use "Goodbye", "Quit", "Bye", and "Close" to exit the chat bot

Format: `Exit`

## Saving your tasks!

Tayoo automatically saves all your tasklists in your hard disk after any command that changes the data. There is no need to save manually.

---
FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer, then copy the tasklist.txt file from where your current version of Tayoo is installed to where your Tayoo is downloaded in the other computer.

---

Command summary

| Action                | Format, Examples                                                                                                     |
| --------------------- | -------------------------------------------------------------------------------------------------------------------- |
| List                  | `list`                                                                                                               |
| ToDo                  | `todo TASK_NAME` e.g. `todo Read Book`                                                                               |
| Deadline              | `deadline TASK_NAME /by TASK_DEADLINE` e.g. `deadline Return book /by Sunday`                                        |
| Event                 | `event TASK_NAME /from START_DATETIME /to END_DATETIME` e.g. `event Project meeting /from 01/01/2024 1830 /to 19:30` |
| Mark                  | `mark TASK_NUMBER` e.g. `mark 1`                                                                                     |
| Unmark                | `unmark TASK_NUMBER` e.g. `unmark 2`                                                                                 |
| Find                  | `find KEYWORD` e.g. `find book`                                                                                      |
| Delete/Remove         | `Delete TASK_NUMBER` OR `Remove TASK_NUMBER`                                                                         |
| Delete all/Remove all | `Delete all` OR `Remove all`                                                                                         |
| Exit                  | `list`                                                                                                               |
