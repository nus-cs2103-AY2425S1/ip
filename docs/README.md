# Qwerty User Guide

Qwerty is a **desktop app for managing tasks,
optimised for use via a Command Line Interface (CLI)**.
Qwerty works as fast as you can type.

## Quick Start

1. Ensure you have Java `17` or above installed in your computer.
2. Download the latest `.jar` file.
3. Copy the file you want to use as your *home folder* for your Qwerty.
4. Open a command terminal, cd into the folder you put the jar in,
and use the `java -jar qwerty.jar` command to run Qwerty.
A GUI similar to the below should appear soon. The app should not
contain any initial data.

![Screenshot 2024-09-19 232313.png](..%2F..%2F..%2F..%2F..%2FPictures%2FScreenshots%2FScreenshot%202024-09-19%20232313.png)

6. Refer to the Features below for details of each command.

## Features

### Adding a Todo task: `todo`

Adds a Todo type task to the task list.

Format: `todo <name>`

Example: `todo submit cs2103t iP`

### Adding a Deadline task: `deadline`

Adds a Deadline type task to the task list.

Format: `deadline <name> /by <DD/MM/YYYY HHmm>`

Example: `deadline submit cs2103t iP /by 20/09/2024 2359`

### Adding an Event task: `event`

Adds an Event type task to the task list.

Format: `event <name> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm>`

Example: `event cs2103t lecture /from 20/09/2024 1600 /to 20/09/2024 1700`

### Listing all tasks: `list`

Shows a list of all the tasks currently in the task list.

Format: `list`

### Finding a task: `find`

Finds tasks whose names contain the given string.

Format: `find <string>`

Example: `find cs2103t`

### Sorting tasks by date: `sort`

Sorts tasks by their date. The date of each type of task is as follows:
1. Todo - No date, is sorted to the back of the list
2. Deadline - the date of the deadline
3. Event - the date when the event begins

Format: `sort`

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark <index>`

Example: `mark 1`

### Marking a task as not done: `unmark`

Marks a task as not done.

Format: `unmark <index>`

Example: `unmark 1`

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <index>`

Example: `delete 1`

### Closing the application: `bye`

Closes the application.

Format: `bye`

### Saving the data

Qwerty is smart enough to save your task list everytime a modification is made.
There is no need for manual saving.