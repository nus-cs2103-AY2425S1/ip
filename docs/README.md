# BingBong User Guide

<div style="text-align: -webkit-left;"><img src="Ui.png" alt="help-1" width="301"></div>

BingBong is the ultimate solution for managing your tasks, offering a user-friendly and intuitive experience designed 
for simplicity. It’s lightweight, providing just the essentials without any unnecessary frills, and operates with 
blazing efficiency, allowing you to focus on getting things done rather than navigating a complicated tool.

- [Quick Start](#quick-start)
- [Features and Commands](#features-and-commands)
    - [Help with commands: `help`](#help-with-commands-help)
    - [Adding todo tasks: `todo`](#adding-todo-tasks-todo)
    - [Adding deadlines: `deadline`](#adding-deadlines-deadline)
    - [Adding events: `event`](#adding-events-event)
    - [Adding fixed duration tasks: `fixed`](#adding-fixed-duration-tasks-fixed)
    - [View all tasks: `list`](#view-all-tasks-list)
    - [View tasks on specific dates: `list on`](#view-tasks-on-specific-dates-list-on)
    - [Marking tasks as completed: `mark`](#marking-tasks-as-completed-mark)
    - [Marking tasks as incomplete: `unmark`](#marking-tasks-as-incomplete-unmark)
    - [Deleting tasks: `delete`](#deleting-tasks-delete)
    - [Finding tasks: `find`](#finding-tasks-find)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

# Quick Start
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest `jar` file from [here](https://github.com/BingBong/ip/releases).
3. Copy the `jar` file to the folder you'd want to run Jackson on.
4. Run `java -jar bingbong_v1.jar` on the command prompt in the same directory.

- To input, type in the text box and either hit Enter or click on the send button.

> [!TIP]
> Try a few commands! You can view the list of supported commands with `help`

# Features and Commands

## Help with commands: `help`
This command displays the list of supported commands.

Format: `help`

## Adding todo tasks: `todo`

Adds a task with no deadline.

Format: `todo <task>`

Example:

`todo Buy groceries`

This command will create a task "Buy groceries":
```
Got it. I've added this task: 
[T][] Buy groceries
Now you have n tasks in the list
```

## Adding deadlines: `deadline`

Adds a task with a specific deadline.

Format: `deadline <task> /by <d/M/yyyy HH:mm>`

Example:
`deadline Submit assignment /by 10/09/2024 1300`

This command will create a task "Submit assignment" with a deadline of September 10, 2024, at 1:00 PM:
```
Got it. I've added this task: 
[D][] Submit assignment (by: 10 Sept 2024, 1:00pm)
Now you have n tasks in the list
```

## Adding events: `event`

Adds an event that starts and ends at specified times.

Format: `event <task> /from <d/M/yyyy HH:mm> /to <d/M/yyyy HH:mm>`

Example:
`event Team meeting /from 10/09/2024 1300 /to 15/09/2024 1200`

This command will create an event "Team meeting" starting on September 10, 2024, at 1:00 PM and ending on September 15, 2024, at 12:00 PM.

```
Got it. I've added this task: 
[E][] Team meeting (from: 10 Sept 2024, 1:00pm to: 15 Sept 2024, 12:00pm)
Now you have n tasks in the list
```

## Adding fixed duration tasks: `fixed`

Adds a task that has a fixed duration.

Format: `fixed <task> /period <timeInHours>`

Example:
`fixed Work on project /period 3`

This command will create a task "Work on project" with a duration of 3 hours.

```
Got it. I've added this task: 
[F][] Work on project (duration: 3 hours)
Now you have n tasks in the list
```

## View all tasks: `list`

Shows a list of all tasks managed by BingBong.

Format: `list`

## View tasks on specific dates: `list on`

Shows a list of tasks on a specified date.

Format: `list on <dd/M/yyyy>`

Example: `list on 10/10/2024`

## Marking tasks as completed: `mark`

Marks a task as complete.

Format: `mark <index>`

Example:
`mark 2`

This command will mark the second task on the list as complete:

```
Nice!. I've marked this task as done: 
[F][X] Work on project (duration: 3 hours)
```

## Marking tasks as incomplete: `unmark`

Unmarks a task.

Format: `unmark <index>`

Example:
`mark 2`

This command will unmark the second task on the list:

```
Okay. I've unmarked this task: 
[F][] Work on project (duration: 3 hours)
```

## Deleting tasks: `delete`

Removes a specified task from the list

Format: `delete <index>`

Example:
`delete 3`

This command will delete the third task on the list:
```
Noted. I've removed this task: 
[D][] Complete assignment (by: 14 August 2024, 7:00pm)
Now you have n tasks in the list
```

## Finding tasks: `find`

Searches for tasks that contain the specified keyword.

Format: `find <keyword>`

Example:
`find book`

This command will find all tasks in the list with the keyword 'book':
 ```
 Here are the tasks containing "book":
 1. [T][X] Read a book
 2. [D][] Return the book (by: 17 October 2024, 12:00pm)
 ```

## Exiting the program: `bye`

Exits the program.

Format: `bye`

# FAQ
**Q**: How do I transfer my data to another computer?\
**A**: Simply copy `main/resources/texts/data.txt` into your new computer and install BingBong
using the steps in [Quick Start](#quick-start).

# Command Summary
Click on each command to view the relevant section.

|                      Command                       |                     Usage                      |
|:--------------------------------------------------:|:----------------------------------------------:|
|         [`help`](#help-with-commands-help)         |                     `help`                     |
|         [`todo`](#adding-todo-tasks-todo)          |                 `todo [TASK]`                  |
|   [`deadline`](#adding-deadlines-deadline)         |        `deadline [TASK] /by [BY-DATE]`         |
|          [`event`](#adding-events-event)           | `event [TASK] /from [FROM-DATE] /to [TO-DATE]` |
|   [`fixed`](#adding-fixed-duration-tasks-fixed)    |      `fixed [TASK] /period [TIMEINHOURS]`      |
|           [`list`](#view-all-tasks-list)           |                     `list`                     |
| [`list on`](#view-tasks-on-specific-dates-list-on) |                   `list on`                    |
|     [`mark`](#marking-tasks-as-completed-mark)     |                 `mark [INDEX]`                 |
|  [`unmark`](#marking-tasks-as-incomplete-unmark)   |                `unmark [INDEX]`                |
|         [`delete`](#deleting-tasks-delete)         |                `delete [INDEX]`                |
|           [`find`](#finding-tasks-find)            |                `find [KEYWORD]`                |
|         [`bye`](#exiting-the-program-bye)          |                     `bye `                     |
