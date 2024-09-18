# Alex User Guide

Alex is a chatbot that allows you to store and manage your tasks all in one place!
Tasks are separated into 3 categories: Todos, deadlines, and events.

Features:
- Viewing your list of tasks: `list`
- Adding a task : `todo` / `deadline` / `event`
- Marking a task: `mark`
- Unmarking a task: `unmark`
- Deleting a task: `delete`
- Finding tasks by keyword: `find`
- Viewing tasks that end on a certain date: `tasks on`
- Tagging a task: `tag`

## Installation
1. Ensure you have Java 17 or above installed in your computer.
2. Download the latest .jar file from [here](https://github.com/leroychiu20/ip/releases/).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Open a command terminal, cd into the folder you put the jar file in.
5. Use this command to run the application. A GUI should appear in a few seconds.
```
java -jar alex.jar
```
Some example commands you can try:
- `todo assignment` adds a todo task called assignment to the list
- `list` shows all tasks in the list
- `mark 1` marks the first task as done
- `delete 1` deletes the first task in the list

## Features

### Viewing your list of tasks: `list`

Shows a list of all tasks saved in storage.
Format: `list`

### Adding a todo task : `todo` / `deadline` / `event`

Adds a task to the list.

Todo tasks only consist of the description of the task.

Format: `todo DESCRIPTION`

Examples:
- `todo read a book`
- `todo submit assignment`

Deadline tasks consist of descriptin of task and the due date.

Format: `deadline DESCRIPTION /by DUEDATE`

Examples:
- `todo read a book /by 2024-09-01`
- `todo submit assignment /by 2024-09-09`

### Marking a task: `mark` /  Unmarking a task: `unmark`
Marks a task as completed / Unmarks a task as uncompleted based on its `INDEX` in the list.

Format: `mark INDEX` / `unmark INDEX`

Examples:
- `mark 1` marks the first task in the list as completed.
- `unmark 3` marks the third task in the list as uncompleted.

### Deleting a task: `delete`
Deletes a task from the list based on its `INDEX` in the list.

Format: `delete INDEX`

Examples:
- `delete 1` deletes the first task in the list.

### Finding tasks by keyword: `find`
Goes through the list and finds tasks that match the specified `KEYWORD`.

Format: `find KEYWORD`

Examples:
- `find submit` finds all tasks in the list that have the keyword 'submit'.
- `find book` finds all tasks in the list that have the keyword 'book'.

### Viewing tasks that end on a certain date: `tasks on`
Goes through the list and finds tasks that end on the specified `DUEDATE`.

Format: `tasks on DUEDATE` , where `DUEDATE` is in the form YYYY-MM-DD.

Examples:
- `tasks on 2024-09-09` returns all deadline or event tasks that end on the specified `DUEDATE`.

### Tagging a task: `tag`
Tags a certain task with the specified `LABEL` based on its `INDEX` in the list.

Format: `tag INDEX LABEL`

Examples: `tag 1 fun` tags the first task in the list with the label 'fun'.