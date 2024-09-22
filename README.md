# Sigma
## User Guide

Sigma is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Sigma can help you manage your tasks faster than traditional GUI apps. Interested? Jump to the [Quick Start](#quick-start) to get started!

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help) 
  - [Adding a task: `todo`, `deadline`, `event`](#adding-a-task-todo-deadline-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `mark`, `unmark`](#marking-a-task-as-done-mark-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding a task: `find`](#finding-a-task-find)
  - [Sorting tasks: `sort`](#sorting-tasks-sort)
  - [Exiting the program: `bye`](#exiting-the-program-bye)

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.

2. Download the latest `.jar` file from [here](https://github.com/anselmlong/ip/releases/tag/v1.0).

3. Copy the file to the folder you want to use as the home folder for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sigma.jar` command to run the application.
5. You should see something like the following when Sigma starts:
![Welcome Screen](src/main/resources/images/welcome.png)
6. Type a command in the text box and press Enter to execute it. Some example commands you can try:
   - `help`: Shows all available commands.
   - `hello`: Greets you.
7. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help: `help`
Shows all available commands.

Format: `help`

![Help](src/main/resources/images/help.png)

### Adding a task: `todo`, `deadline`, `event`
Adds a task to the task list.
There are 3 different types of tasks you can add:
1. Todo: A task without any date/time attached to it.
   - Format: `todo DESCRIPTION`
2. Deadline: A task that needs to be done before a specific date/time.
   - Format: `deadline DESCRIPTION /by DATE TIME`
3. Event: A task that starts at a specific time and ends at a specific time.
   - Format: `event DESCRIPTION /from DATE TIME /to DATE TIME`
> [!NOTE]
> Date and time should be in the format `yyyy-MM-dd HH:mm`.

Examples:
- `todo read book`
- `deadline return book /by 2021-09-30 18:00`
- `event project meeting /from 2021-09-30 14:00 /to 2021-09-30 16:00`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `mark`, `unmark`
Marks a task as done, and undone respectively.

Format: `mark <index>`, `unmark <index>`


![Mark](src/main/resources/images/mark.png)

### Deleting a task: `delete`
Deletes a task from the task list.

Format: `delete <index>`
* Deletes the person at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3...

### Finding a task: `find`
Finds tasks whose descriptions contain any of the given keywords.

Format: `find <keyword>`'

### Sorting tasks: `sort`
Returns a new list of tasks sorted by the given criteria. This command can also be used to filter by a certain type of task.

Format: `sort <criteria>`

Criteria:
   - `desc`/`description`: Sort by task description
   - `date`: Sort by task date
   - `todo`/`t`: Sort by todo tasks
   - `deadline`/`d`: Sort by deadline tasks
   - `event`/`e`: Sort by event tasks

### Exiting the program: `bye`
Exits the program.

Format: `bye`

## Command Summary
- **Help**: `help`
- **Add Todo**: `todo DESCRIPTION`
- **Add Deadline**: `deadline DESCRIPTION /by DATE TIME`
- **Add Event**: `event DESCRIPTION /from DATE TIME /to DATE TIME`
- **List**: `list`
- **Mark**: `mark <index>`
- **Unmark**: `unmark <index>`
- **Delete**: `delete <index>`
- **Find**: `find <keyword>`
- **Sort**: `sort <criteria>`
- **Exit**: `bye`

You can enter `sigma` or `skibidi` for a surprise!
