# Elon User Guide

![Screenshot of User Interface](Ui.png)

The Elon chatbot is a tool designed to help users manage their tasks efficiently. 
It allows users to create, track, and organise tasks in various categories: todos, deadlines, and events. 
Key features include adding, deleting, finding, snoozing and marking tasks as complete.

## Quick Start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `elon.jar` file.
3. Copy the file to the folder you wish to use as the home folder for Elon.
4. Open a command terminal, navigate (`cd`) to the folder containing the jar file, 
and run the following command: (`java -jar elon.jar`)
5. A terminal interface will appear, ready to accept your commands.

Some example commands you can try:
- `list` : Lists all tasks.
- `todo buy groceries`: Adds a ToDo task with the description "buy groceries" to the list.
- `delete 3` : Deletes the 3rd task shown in the list.
- `bye` : Exits the app.

## Features

### Adding a Task: `todo`,`deadline` or `event`
Adds a new task to the system. You can add three types of tasks: `todo`, `deadline`, and `event`.

- **Format of ToDo**: `todo [Task Description]`
- **Format of Deadline**: `deadline [Task Description] /by [Date in yyyy-MM-dd HH-mm format]`
- **Format of Event**: `event [Task Description] /from [Date in yyyy-MM-dd HH-mm format] /to 
[Date in yyyy-MM-dd HH-mm format]`
- **Example**: `event Complete homework /from 2024-09-25 16:00 /to 2024-09-26 12:00`: Adds an Event task with the 
name "Complete homework" to be done between September 25, 2024 1600 hrs and September 26, 2024 1200 hrs.

### Listing All Tasks: `list`
Shows a list of all tasks currently in the system.

- **Format**: `list`

### Deleting a Task: `delete`
Deletes a task based on its index in the list.

- **Format**: `delete [Index of Task]`
- **Example**: `delete 1`: Deletes the 1st task from the list.

### Marking a Task as Completed: `mark`
Marks the specified task as completed based on its index in the list.

- **Format**: `mark [Index of Task]`
- **Example**: `mark 1`: Marks the 1st task in the list as completed.

### Unmarking a Task as Completed: `unmark`
Unmarks the specified task as not completed based on its index in the list.

- **Format**: `unmark [Index of Task]`
- **Example**: `unmark 2`: Unmarks the 2nd task in the list as not completed.

### Snoozing a Task: `snooze`
Snoozes (postpones) the end date of a Deadline or Event task to a new date.

- **Format**: `snooze [Index of Task] [Date in yyyy-MM-dd HH-mm format]`
- **Example**: `snooze 3 2024-09-25 13:00`: Snoozes the end date of the 3rd task 
to September 25, 2024 1300 hrs.

### Exiting the Program: `bye`
Exits the Elon application.

- **Format**: `bye`

### Saving Data
Elon automatically saves your tasks after any command that changes the data. There is no need to manually save.
