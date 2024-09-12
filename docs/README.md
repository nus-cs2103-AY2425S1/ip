# Joe User Guide :rocket:

<img src="./Ui.png" height="700px"/>

Joe is a desktop app for managing tasks using a CLI. If you are a fast typer, Joe is the perfect task manager for you. :rocket:

## Install

1. Download the zip file of this repository. [Click here for more info on how to download on GitHub](https://docs.github.com/en/get-started/start-your-journey/downloading-files-from-github#downloading-a-repositorys-files)
2. Extract the zip file.
3. Enjoy using Joe by double clicking the jar file!

## Features

-   [Joe User Guide :rocket:](#joe-user-guide-rocket)
-   [Install](#install)
-   [Features](#features)
-   [Help Menu](#help-menu)
-   [Adding Todos](#adding-todos)
-   [Adding Deadlines](#adding-deadlines)
-   [Adding Events](#adding-events)
-   [Marking/Unmarking Tasks](#markingunmarking-tasks)
-   [Deleting Tasks](#deleting-tasks)
-   [Listing Tasks](#listing-tasks)
-   [Finding Tasks](#finding-tasks)
-   [Postponing Deadlines and Events](#postponing-deadlines-and-events)

### Help Menu

-   Command: `help`
-   Use: Lists all available commands and their usage.

### Adding Todos

-   Command: `todo <description>`
-   Use: Adds a todo task to the task list.
-   Example: `todo read book`

### Adding Deadlines

-   Command: `deadline <description> /by <yyyy-mm-dd>`
-   Use: Adds a task with a deadline to the task list.
-   Example: `deadline return book /by 2021-09-30`

### Adding Events

-   Command: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
-   Use: Adds an event with a start and end date to the task list.
-   Example: `event LeetCode Grind Session /from 2024-09-30 /to 2024-10-01`

### Marking/Unmarking Tasks

-   Command: `[mark/unmark] <task number>`
-   Use: Marks or unmarks a task as done.
-   Example: `mark 1`

### Deleting Tasks

-   Command: `delete <task number>`
-   Use: Deletes a task from the task list.
-   Example: `delete 1`

### Listing Tasks

-   Command: `list`
-   Use: Lists all tasks in the task list.

### Finding Tasks

-   Command: `find <keyword>`
-   Use: Finds all tasks with the keyword in the description.

### Postponing Deadlines and Events

-   Command: `postpone <task number> <days>`
-   Use: Postpones the deadline or event by the specified number of days.
