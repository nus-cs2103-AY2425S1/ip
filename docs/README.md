# Joe User Guide :rocket:

<img src="./Ui.png" height="700px"/>

Joe is a desktop app for managing tasks using a CLI. If you are a fast typer, Joe is the perfect task manager for you. :rocket:

## Install

> **Prerequisites**: Java 17 with JavaFX installed on your computer.

1. Download the jar file for Joe. [Click here to download](https://github.com/GabrielCWT/ip/releases/download/A-Release/joe.jar)
2. With a terminal, navigate to the directory where the jar file is located.
3. Run the jar file by typing `java -jar joe.jar` in the terminal.
4. Enjoy using Joe!

## Features

-   [Help Menu](#help-menu)
-   [Adding Todos](#adding-todos)
-   [Adding Deadlines](#adding-deadlines)
-   [Adding Events](#adding-events)
-   [Marking/Unmarking Tasks](#markingunmarking-tasks)
-   [Deleting Tasks](#deleting-tasks)
-   [Listing Tasks](#listing-tasks)
-   [Finding Tasks](#finding-tasks)
-   [Postponing Deadlines and Events](#postponing-deadlines-and-events)
-   [Exiting the App](#exiting-the-app)

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

### Exiting the App

-  Command: `bye`
-  Use: Exits the app.