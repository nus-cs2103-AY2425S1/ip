# Arona User Guide

Arona is a desktop app for managing tasks, it is stylised like a chat bot that you can talk to and receive replies from.

The design is inspired by [Arona from Blue Archive](https://bluearchive.fandom.com/wiki/Arona).
## Installation

1. Ensure you have Java 17 or above installed in your Computer.

2. Download the latest `.jar` file from [here](https://github.com/Jayden-Kim-NUS/ip/releases).

3. Copy the jar file to the folder you want to use as the home folder for Arona.

4. Open a command terminal, `cd` into the folder you put the jar file in

5. Use this command to run the application, a GUI should appear in a few seconds.
```
java -jar Arona.jar
``` 

6. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
- `todo homework` : Adds a todo called homework to the task list.
- `list` : List all tasks.
- `mark 1` : Marks the first task as done.
- `delete 1` : Deletes the first task.
- `bye` : Exits the app.

Refer to the Features below for details of each command.

## Features

### `list` : List all tasks

Lists all tasks in `data.txt` (the default storage file).

Format: `list`

### `todo` : Add a new todo

Adds a new task without any date attached to it.

Format: `todo DESCRIPTION`

Examples:
- `todo go to the park`
- `todo Shopping`

### `deadline `: Add a new deadline

Adds a new task that need to be done before a specific date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Examples:
- `deadline math assignment /by 2024-01-01`
- `deadline report /by 2024-06-06`

### `event` : Add a new event

Adds a new task that start at a specific date and ends at a specific date.

Format: `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Examples:
- `event orientation week /from 2024-03-14 /to 2024-03-21`
- `event Halloween /from 2024-10-31 /to 2024-10-31`

### `mark` : Mark a task as done

Mark the specified task as done [X].

Format: `mark INDEX`

Examples:

- `mark 3` marks the third task as done

### `unmark` : Unmark a task

Mark the specified task as not done [ ].

Format: `unmark INDEX`

Examples:

- `unmark 1` marks the first task as undone

### `delete` : Delete a task

Deletes the specified task from the list of tasks.

Format: `delete INDEX`

Examples:

- `delete 2` deletes the second task in the list

### `find` : Find a task

Finds a task which has a description that contains the specified keyword.

Note: the keyword is not case sensitive but it is whitespace sensitive.

Format: `find KEYWORD`

Examples:

- `find work` returns tasks with description `WORK`, `homework` and `work out` but not `w ork`
- `find buy toy` returns tasks with description `buy toy car` but not `buy a toy`

### `archive` : Archive current tasks

Saves current task list into specified file name and clears current task list.

Note: the archived file is in the same location as data.txt.

Format: `archive FILENAME`

Examples:

- `archive school work` saves tasks into `school work.txt`
- `archive archive.exe` saves tasks into `archive.exe.txt` (note this is still a text file)

### `bye` : Closes the app

Saves all tasks and closes the app.

Format: `bye`
