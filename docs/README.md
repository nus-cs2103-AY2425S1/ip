# YihuiBot User Guide

![Product screenshot](Ui.png)

YihuiBot is friendly todo list app for managing your every day tasks. Supported tasks include a `Todo`, which is a basic task, a `Deadline`, which is a task with a deadline, or an `Event`, which is a task with a start and end time.

## Table of Contents
1. [Quick start](#quick-start)
2. [Features](#Features)

## Quick start
1. Download the latest `.jar` file [here](https://github.com/yihuididi/ip/releases).
2. Move the `.jar` file to a suitable folder location to run YihuiBot.
3. Run `java -jar yihuibot.jar` in your command line. This will start the bot, creating a default file at `data/task.txt`. If you would like to specify another filepath, run `java -jar yihuibot.jar <filepath>` instead.

## Features

### Create Todo task: `todo`

Creates a new Todo task. A Todo task is simply a task with a description.

Format: `todo <task description>`
- where `<task description>` can be replaced by the description of the task. The description can contain multiple words (do not have to be only one word).

Examples:
- `todo assignment`
- `todo read book`

### Create Deadline task: `deadline`

Creates a new Deadline task. A Deadline task is a task with a description and a deadline.

Format: `deadline <task description> /by <date and time>`
- where `<task description>` can be replaced by the description of the task. The description can contain multiple words (do not have to be only one word),
- and `<date and time>` can be replaced by the deadline of the task specified using yyyy-MM-dd HH:mm.

Examples:
- `deadline return book /by 2024-09-17 16:30`
- `deadline report /by 2024-12-26 06:30`

### Create Event task: `event`

Creates a new Event task. An Event task is a task with a description, start time and end time.

Format: `event <task description> /from <start date and time> /to <end date and time>`
- where `<task description>` can be replaced by the description of the task. The description can containe multiple words (do not have to be only one word),
- `<start date and time>` can be replaced by the start time of the task specified using yyyy-MM-dd HH:mm,
- and `end date and time>` can be replaced by the end time of the task specified using yyyy-MM-dd HH:mm.

Examples:
- `event project meeting /from 2024-10-19 20:00 /to 2024-10-19 22:00`
- `event exam /from 2024-09-12 07:00 /to 2024-09-13 07:00`

### List out all tasks: `list`

List out all the tasks in user's tasklist.

Format: `list`

### Mark complete: `mark`

Mark the specified task as completed.

Format: `mark <index>`
- where `<index>` can be replaced by a positive number from 1, 2, 3, ... n, where n is the number of tasks created.

Examples:
- `mark 2`

### Mark incomplete: `unmark`

Mark the specified task as incomplete.

Format: `unmark <index>`
- where `<index>` can be replaced by a positive number from 1, 2, 3, ... n, where n is the number of tasks created.

Examples:
- `unmark 2`

### Delete task: `delete`

Delete the task at the specified index.

Format: `delete <index>`
- where `<index>` can be replaced by a positive number from 1, 2, 3, ... n, where n is the number of tasks created.

Examples:
- `delete 2`

### Filter task: `find`

Filter the tasks by task description. Listing out all tasks with matching task descriptions.

Format: `find <filter>`
- where `<filter>` can be replaced by any query.

Examples:
- `find book`
- `find CS2013 assignment`

### Save tasks: `bye`

Greet the bot farewell, and write the tasks to file in a human-readable format.

Format: `bye`
