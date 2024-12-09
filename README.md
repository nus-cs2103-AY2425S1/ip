# Pikappi User Guide

## Installation

1. Ensure you have Java 17 or above installed in your computer.
2. Download the latest `pikappi.jar` from [here](https://github.com/xqtann/ip/releases/tag/A-Release).
3. Locate to the jar file using a command terminal, and execute `java -jar pikappi.jar` command to run the application.

## Features

> - Commands are case-insensitive.  
    > e.g. `help`, `HELP`, `Help` are all valid commands.
>
>
> - Date and time format is `yyyy-MM-dd HHmm` or `yyyy-MM-dd`.
>
>
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will be ignored.

### Viewing help: `help`

Shows a list of commands and their usage format.

### Adding a Task:

Add a task to the list of tasks.

Todo Task: `todo <task name>`

Deadline Task: `todo <task name> /by <date>`

Event Task: `todo <task name> /from <start date> /to <end date>`

Example of usage:

```
todo read book

deadline return book /by 2021-09-17 1800

event project /from 2021-09-17 1400 /to 2021-09-18 1600
```

### Listing all tasks: `list`

Shows a list of all tasks.

### Marking a task as done: `done <task number>`

Marks a task as done.

Example of usage:

```
done 1
```

### Unmarking a task as done: `undone <task number>`

Unmarks a task as done.

### Deleting a task: `delete <task number>`

Deletes a task from the list.

### Finding a task by keyword: `find <keyword>, <keyword>, ...`

Finds tasks that contain the keyword(s) in their description.

Example of usage:

```
find book, test, project
```

### Sorting tasks by date: `sort <sort type>`

Sorts tasks in list by description, deadline date, start date,
completion status, or type of task (Todo/Event/Deadline).

Sort types: `description`, `by`, `from`, `status`, `tasktype`

Example of usage:

```
sort description
```

### Exiting the program: `bye`

Exits the program.

### Saving the data

Pikappi data is saved in a file named `pikappi.txt`, and is automatically saved after any command that changes the data.
There is no need to manually save the data.