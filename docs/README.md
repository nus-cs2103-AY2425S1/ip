# Bmo User Guide

[text](README.md)

Bmo is an intelligent chatbot designed to assist users in managing their tasks efficiently. 

Whether you need to keep track of deadlines, events, or simple to-dos, Bmo is here to help. With a user-friendly interface and intuitive commands, Bmo makes task management a breeze. Simply interact with Bmo through intuitive commands, and let it handle the rest. From adding new tasks to marking them as complete, Bmo ensures you stay organized and on top of your schedule.

## Quick Start
1. Ensure you have Java `17` installed.
2. Download the latest `.jar` file from [here](https://github.com/aldentantan/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar bmo.jar` command to run the application.
5. Type commands into the command box and press Enter to execute it. Some example commands you can try:
    - `list`: Lists all tasks in the to-do list.
    - `todo complete CS2100 assignment`: Adds a to-do task (with no deadline) to the to-do list.
    - `mark 1`: Marks the 1st task in the to-do list as complete.
    - `delete 1`: Deletes the 1st task in the to-do list.
    - `bye`: Terminates the chatbot session. Execute this command every time before closing the window.

## Features

> [!NOTE]
> Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in `todo TASK_NAME`,  `TASK_NAME` is a parameter which can be used as `todo complete assignment`.

### Listing all tasks: `list`

Lists all tasks currently in the to-do list.

Format: `list`

### Adding to-do task: `todo`

Adds a to-do task to the to-do list.

Format: `todo TASK_NAME`

Example: 
- `todo Go for a run`
- `todo Watch CS2102 Lecture Recording`

### Adding deadline task: `deadline`

Adds a deadline task to the to-do list.

Format: `deadline TASK_NAME /by DD/MM/YYYY`

Example: 
- `deadline Submit final version of iP /by 20/09/2024`
- `deadline Complete CS2100 Lab /by 18/09/2024`

### Adding event task: `event`

Adds an event task, which has a start and end date, to the to-do list.

Format: `event TASK_NAME /from DD/MM/YYYY /to DD/MM/YYYY`

Example: 
- `event Public Garden at Expo /from 28/09/2024 /to 29/09/2024`
- `event Bali Trip /from 22/09/2024 /to 27/09/2024`

### Mark a task as completed: `mark`

Marks a task in the to-do list as completed.

Format: `mark INDEX`

- Marks the task at the specified index in the to-do list.
- The index refers to the index number shown in the displayed list when `list` is executed.
- The index **must be a positive integer** 1, 2, 3...

### Unmark a task as incomplete: `unmark`

Unmark a task in the to-do list as incomplete.

Format: `unmark INDEX`

- Unmarks the task at the specified index in the to-do list.
- The index refers to the index number shown in the displayed list when `list` is executed.
- The index **must be a positive integer** 1, 2, 3...


### Delete a task: `delete`

Deletes the specified task from the to-do list.

Format: `delete INDEX`

- Deletes the task at the specified index.
- The index refers to the index number shown in the displayed list when `list` is executed.
- The index **must be a positive integer** 1, 2, 3...

### Find a specific task by name: `find`

Finds tasks whose descriptions contain the keyword provided.

Format: `find KEYWORD`

- The search is currently **case-sensitive**.
- Only the task description is searched.
- The keyword does not have to match the full word in the task description, it can just match as a substring.

### Exit the application: `bye`

Saves the task list to memory and allows safe termination of the chatbot session.

Format: `bye`
