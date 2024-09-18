# GLaDOS Chatbot User Guide

GLaDOS is a versatile chatbot for managing tasks, based off of the video game character in _Portal_. If you can type fast, GLaDOS can assist with your task management quickly and efficiently (albeit a little reluctantly).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Adding a ToDo: `todo`](#adding-a-todo-todo)
  - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an Event: `event`](#adding-an-event-event)
  - [Deleting a Task: `delete`](#deleting-a-task-delete)
  - []
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `.jar` file from the [releases page](https://github.com/jayjay19630/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, navigate to the folder you placed the jar file in, and run the following command:
   `java -jar GLaDOS.jar`
   A GUI similar to the one below should appear in a few seconds.
5. Type a command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command

![Glados Image](./Ui.png)

---

## Features

Note: Words in UPPER_CASE are parameters to be supplied by the user.

Items in square brackets are optional.
Items with …​ after them can be used multiple times.
Parameters can be in any order.
Extraneous parameters for commands that do not take in parameters will be ignored.
Viewing Help: help
Shows a message explaining how to access the help page.

# Listing all tasks: `list`

Shows list of all tasks in the system

Format: `list`

# Adding a ToDo: `todo`

Adds a ToDo to the task list, which is a task with a description.

Format: `todo TASK_DESCRIPTION`

Examples:

- `todo eat some cake`
- `todo buy some ice cream`

# Adding a Deadline: `deadline`

Adds a deadline to the task list, which is an task with a description and deadline date.

Format: `deadline TASK_DESCRIPTION /by DEADLINE_DATE`

- All parameters must be filled in order or else invalid argument error will be shown
- `DEADLINE_DATE` must be in the form YYYY-MM-DD

# Adding an Event: `event`

Adds an Event to the task list, which is an task with a description, start date and end date.

Format: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

- All parameters must be filled in order or else invalid argument error will be shown
- `START_DATE` and `END_DATE` must be in the form YYYY-MM-DD

# Deleting a Task: `delete`

Deletes a task from the task list based on list index

Format: delete INDEX

- `INDEX` is the index of the task to delete. Must be a positive integer (e.g., 1, 2, 3, …​).
- If `INDEX` does not exist within range of task list, error will be shown.

Example:

- `delete 2`

# Marking a Task as Done: `mark`

Marks a task as completed.

Format: mark TASK_DESCRIPTION

TASK_DESCRIPTION: The description of the task to mark as done.
Example:

mark Finish report
Unmarking a Task: unmark
Marks a task as not completed.

Format: unmark TASK_DESCRIPTION

TASK_DESCRIPTION: The description of the task to unmark.
Example:

unmark Finish report
Updating a Task: update
Updates the description of an existing task.

Format: update NEW_TASK_DESCRIPTION

NEW_TASK_DESCRIPTION: The updated description of the task.
Example:

update Finish the final report

Command Summary
Action Format, Examples
Add add TASK_DESCRIPTION INDEX
e.g., add Finish report 3
Delete delete INDEX
e.g., delete 2
List list
Mark mark TASK_DESCRIPTION
e.g., mark Finish report
Unmark unmark TASK_DESCRIPTION
e.g., unmark Finish report
Update update NEW_TASK_DESCRIPTION
e.g., update Finish the final report
Print Error printError EXCEPTION
Exit exit
FAQ
Q: How do I transfer my data to another computer?
A: Install the app on the other computer and copy the data file from the previous GLaDOS home folder to the new one.

---

## Known Issues

- UI Issues: If you expand the window vertically, you may see some empty space. GLaDOS is currently not expandable-friendly.

---

## Command Summary
