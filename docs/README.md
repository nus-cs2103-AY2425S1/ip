# DrBrown Application - User Guide

![The DrBrown Application Screenshot](Ui.png)

## Setup

1. **Installation**: Ensure Java is installed on your system. Download the DrBrown application jar file and place it in a folder.
2. **Running the Application**:
    - **CLI Version**: Open a terminal or command prompt, navigate to the folder containing the jar file, and run `java -jar DrBrown.jar`.
    - **GUI Version**: Launch the jar file directly or run it through a terminal with the `java -jar DrBrown.jar` command.

## Core Components

### DrBrown Main Class

- Initializes the application with a `Ui`, `Storage`, and `TaskList`.
- Loads tasks from a file and handles errors in loading.
- Processes user commands and outputs results or error messages.

### Commands

Commands allow users to interact with the application to perform various operations. All commands extend the abstract `Command` class and implement the `executeCommand()` method:

- **AddCommand**: Adds a task (`Todo`, `Deadline`, `Event`) to the task list.
- **DeleteCommand**: Deletes a task by its index.
- **ExitCommand**: Exits the application.
- **FindCommand**: Finds tasks containing a specific keyword.
- **ListCommand**: Lists all tasks.
- **MarkCommand**: Marks a task as completed.
- **UnmarkCommand**: Unmarks a task as not completed.

### Tasks

Tasks represent the different types of activities users can manage:

- **Task**: The abstract base class for all tasks with common attributes like description, status, and priority.
- **Todo**: A simple task with a description and priority.
- **Deadline**: A task with a description, a deadline date/time, and priority.
- **Event**: A task with a description, start and end date/time, and priority.

### Priorities

There are three types of priorities that can be assigned to tasks:

- **LOW**: Represents tasks with low urgency.
- **MEDIUM**: Represents tasks with moderate urgency.
- **HIGH**: Represents tasks with high urgency and importance.

### Utilities

Utility classes provide supporting functionality:

- **Ui**: Handles all user interface interactions, displaying messages and reading input. It provides themed messages for various operations and errors.
- **Storage**: Handles loading and saving tasks to a file.
- **TaskList**: Manages the list of tasks, providing methods to add, remove, mark, unmark, find, and list tasks.
- **DrBrownException**: A custom exception class for handling application-specific errors.

## Usage Instructions

### Adding Tasks

- **Todo**: `todo {description} /priority {priority}`
- **Deadline**: `deadline {description} /by {date} /priority {priority}`
- **Event**: `event {description} /from {start date} /to {end date} /priority {priority}`

### Deleting Tasks

- **Delete a Task**: `delete {index}`

### Listing Tasks

- **List All Tasks**: `list`

### Marking and Unmarking Tasks

- **Mark a Task**: `mark {index}`
- **Unmark a Task**: `unmark {index}`

### Finding Tasks

- **Find Tasks by Keyword**: `find {keyword}`

### Exiting the Application

- **Exit**: `bye`

## Error Handling

The application provides detailed, themed error messages when incorrect input is provided, a file is corrupted, or other errors occur. Examples include:

- **Missing or Incorrect Input**: "Great Scott! You can't add a to-do without a description!"
- **File Errors**: "Oops! It seems like this is your first time. No worries, I've created a brand new file to get you started."
