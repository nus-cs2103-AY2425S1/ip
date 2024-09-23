# Vinegar Task Manager User Guide

<img width="812" alt="Vinegar Task Manager" src="https://github.com/user-attachments/assets/d62b6526-b6dc-447c-9efd-32c6e58e7976">

Welcome to **Vinegar**, your personal task manager! Vinegar helps you manage tasks such as todos, deadlines, and events directly from the command line interface. It also includes additional features like help documentation and loading sample data for first-time users.

## Getting Started

To get started with Vinegar, follow these steps:

1. **Install Java (JDK 17)**.
2. **Download the JAR File**: Obtain the latest version of the Vinegar JAR file from the release page.
3. **Open a Command Window in the folder containing the JAR file**: Navigate to the folder where the downloaded JAR file is stored.
4. **Execute the JAR File** using the following command:
   ```bash
   java -jar vinegar.jar
   ```

## Features

### 1. Add a Task
Vinegar supports three types of tasks: `todo`, `deadline`, and `event`.

- **Todo**: Adds a basic task without any time constraints.
  - **Command**:
    ```
    todo <description>
    ```
  - **Example**:
    ```
    todo Read documentation
    ```

- **Deadline**: Adds a task with a specific due date.
  - **Command**:
    ```
    deadline <description> /by <date>
    ```
  - **Example**:
    ```
    deadline Submit project proposal /by 2023-09-25
    ```

- **Event**: Adds a task with a specific time range.
  - **Command**:
    ```
    event <description> /at <start> /to <end>
    ```
  - **Example**:
    ```
    event Team meeting /at 2023-09-22 /to 2023-09-23
    ```

### 2. Mark a Task as Done
Mark tasks as completed using the `mark` command.

- **Command**:
  ```
  mark <task_number>
  ```
- **Example**:
  ```
  mark 1
  ```

### 3. Delete a Task or All Tasks
You can delete a specific task or delete all tasks from your list using the `delete` command.

- **Delete a Single Task**:
  - **Command**:
    ```
    delete <task_number>
    ```
  - **Example**:
    ```
    delete 3
    ```

- **Delete All Tasks**:
  - **Command**:
    ```
    delete all
    ```
  - This will remove all tasks from the task list. **Be careful**, as this action cannot be undone.

### 4. View All Tasks
List all tasks currently in the task list.

- **Command**:
  ```
  list
  ```

### 5. Find Tasks
Search for tasks by keyword using the `find` command.

- **Command**:
  ```
  find <keyword>
  ```
- **Example**:
  ```
  find documentation
  ```

### 6. Help
To view the help page and see a list of all available commands, use the `help` command.

- **Command**:
  ```
  help
  ```

### 7. Exit the Chatbot
Exit the Vinegar chatbot using the `bye` command.

- **Command**:
  ```
  bye
  ```

## First-Time Setup

If you're running Vinegar for the first time, the app will automatically load sample data, including:
- A `Todo`: "Read documentation"
- A `Deadline`: "Submit project proposal"
- An `Event`: "Team meeting"

You can view these tasks using the `list` command.

---

## Summary of Commands

| Command                        | Description                                               |
|---------------------------------|-----------------------------------------------------------|
| `todo <description>`            | Adds a new todo task.                                     |
| `deadline <description> /by <date>` | Adds a new deadline task with a specific date.            |
| `event <description> /at <start> /to <end>` | Adds a new event with a time range.                   |
| `mark <task_number>`            | Marks a task as done.                                     |
| `delete <task_number>`          | Deletes the task at the specified position.               |
| `delete all`                    | Deletes all tasks in the task list.                       |
| `list`                          | Displays all tasks in the task list.                      |
| `find <keyword>`                | Finds tasks that match the given keyword.                 |
| `help`                          | Displays the help documentation.                         |
| `bye`                           | Exits the Vinegar application.                            |

---

With these commands, you can manage your tasks efficiently using Vinegar. The `delete all` feature makes it easy to reset your task list entirely if needed.
