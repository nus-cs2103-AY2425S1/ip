![Luke Skywalker](src/main/resources/images/skywalker.png)

# hey, i'm luke, your glorified task manager :)

Luke is a chatbot that helps you keep track of your daily tasks. Think of Luke as an advanced to-do list that can help you track basic to-dos, events, as well as deadlines.

![Luke in action](docs/Ui.png)

# Table of Contents

- [Quick start](#quick-start)
- [Features](#features)
  - [Adding a task: `todo`, `deadline`, and `event`](#adding-a-task)
  - [Deleting a task: `delete`](#deleting-a-task)
  - [Finding a task: `find`](#finding-a-task)
  - [Listing all tasks: `list`](#listing-all-tasks)
  - [Marking a task as complete or incomplete: `mark`, `unmark`](#marking-a-task-as-complete-or-incomplete)
  - [Exiting Luke: `bye`](#exiting-luke)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [Command summary](#command-summary)

---

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

2. Download the latest .jar file from [here](github-release).

3. Copy the file to the folder you want to use as the home folder for Luke.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar luke.jar` command to run Luke. 

<br>

## Features

### Adding a task

Luke has built-in support for 3 task types:
* `todo` The Todo task is a simple task; it has a task name and no additional data.
* `deadline` 
* `event` The Event class represents the Event task type. The Event task contains a task name, an event start time `from`, and an event end time `to`.

You can add these tasks to Luke in 2 ways:
1. Sending them to Luke.
2. Writing them inside the task list save file in `/data/Luke.txt`.


When sending tasks to Luke, each task type needs to adhere to the following formats:
* For `todo` tasks: `todo <TODO_TASK_NAME>`
* For `deadline` tasks: `deadline /by <DEADLINE_DATE>`
* For `event` tasks: `event <TASK_NAME> /from <START_TIME> /to <END_TIME>`

  

The formats each task type has to be stored in within `Luke.txt` are as follows: 
* For `todo` tasks: `<COMPLETION_STATUS> | todo | <TODO_TASK_NAME>`
* For `deadline` tasks: `<COMPLETION_STATUS> | deadline <TASK_NAME> by <DEADLINE_DATE>`
* For `event` tasks: `<COMPLETION_STATUS> | event | <TASK_NAME> | from: <START_TIME> to: <END_TIME>`

<br>

### Finding a task

Filters a task list for tasks that match the provided search criteria.

Format: `find <SEARCH_CRITERIA>`
Example: `find morning run`

<br>

### Listing all tasks

Shows a list of all the tasks in the task list.

Format: `list`

<br>

### Marking a task as complete or incomplete

Toggles the completion status of a task identified by its number.

If the task was unmarked, it will be marked as complete.
If the task was already marked as complete, it will be unmarked.

Format: `mark <TASK_NUMBER>`
Example: `mark 2` OR `ummark 2`

<br>

### Deleting a task

Removes a task from the list using its index number.

Format: `delete <TASK_NUMBER>`
Example: `delete 2` (removes the second task in the list)

<br>

### Exiting Luke

To exit Luke, all you need to do is to enter `bye`. Bye, Luke!

Format: `bye`

<br>

### Saving the data

Any tasks you give Luke will be saved in the hard disk automatically after any command that modifies the list. 

You will not need to save the task list manually. 

All saved tasks will be accessible the next time you run Luke.

<br>

### Editing the data file

Luke's task list is automatically saved as a `.txt` file at `[JAR file location]/data/Luke.txt`. You may modify the task list directly if you like, but tasks need to follow a specific format to be processed by Luke.

Please edit the data only if you are sure you can update it correctly, otherwise Luke may not work as designed.

Format tasks have to be stored in within `Luke.txt`: 
* For `todo` tasks: `<completion status> | todo | <todo task name>`
* For `deadline` tasks: `<completion status> | deadline <task name> by <deadline date>`
* For `event` tasks: `<completion status> | event | <task name> | from: <start time> to: <end time>`

<br>

## Command summary


| Action | Format, Examples |
|--------|------------------|
| Add task | `todo <TASK_NAME>` <br> e.g., `todo travel to coruscant` <br><br> `deadline <TASK_NAME> /by <DEADLINE_DATE>` <br> e.g. `deadline lightsaber presentation slides /by 28/07/2024 18:00` <br><br> `event <TASK_NAME> /from <START_TIME> /to <END_TIME>` <br> e.g. `event senate meeting /from Friday 2pm /to 3pm`|
| Delete task | `delete TASK_NUMBER` <br> e.g., `delete 2` |
| Find task | `find SEARCH_CRITERIA` <br> e.g., `find padawan` |
| List all tasks | `list` |
| Mark/Unmark tasks | `find SEARCH_CRITERIA` <br> e.g., `mark 2` or `unmark 2` |
| Exit Luke | `bye` |
