# Dook User Guide

![Product Screenshot, creating an Event.](Ui.png)

Dook is a chatbot that is able to keep track of all your tasks.
Dook is able to track todos, deadlines, and events. 

# Commands

## Help: `help`
Displays the user guide.

Format: `help`

## Adding Todos: `todo`
Creates a Todo.

Format: `todo <description>`

## Adding deadlines: `deadline`
Creates a Deadline.

Format: `deadline <description> /by <due date>`

💡 `due date` should be in (dd/MM/yyyy hh:mm) format.

## Adding Events: `event`
Creates an Event. 

Format: `event <description> /from <start> /to <end>`

💡 `start` and `end` should be in (dd/MM/yyyy hh:mm) format. 

## Listing all Tasks: `list`
Shows a list of all tasks, completed or not.

Format: `list`

## Delete a Task: `delete`
Deletes the specified task from the list of tasks.

Format: `delete <task number>` 

💡 `task number` refers to the index number shown in the displayed task list.

## Search for a Task: `find`
Searches for tasks that contain the specified keyword.

Format: `find <keyword>`

## Marking Tasks as Done: `mark`
Marks a task as done. Can be undone with `unmark`

Format: `mark <task number>` / `unmark <task number>`

💡 `task number` refers to the index number shown in the displayed task list.

## Exit: `bye`
Leave the app. Automatically closes after a few seconds.

Format: `bye`