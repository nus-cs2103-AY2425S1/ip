# Jeriel User Guide

Welcome to Jeriel, your personal task assistant! This guide will help you understand how to use Jeriel to manage your tasks efficiently.


![ChatBot UI](Ui.png)


## Commands

### 1. **todo**: Adds a todo task
- **Usage**: `todo <task_description>`
- **Description**: Adds a simple todo task to your task list.
- **Example**: `todo Read Book`
- **Expected output**: Got it. I've added this task: [T][ ] Read Book Now you have 1 task in the list.


### 2. **deadline**: Adds a deadline task
- **Usage**: `deadline <task_description> /by <date>`
- **Description**: Adds a task with a deadline, including the due date.
- **Example**: `deadline Submit report /by 2024-09-21`
- **Expected output**: `Got it. I've added this task: [D][ ] Submit report (by: Sep 21 2024)
 Now you have 2 tasks in the list.`


### 3. **event**: Adds an event task
- **Usage**: `event <task_description> /from <start_time> /to <end_time>`
- **Description**: Adds an event task with a start and end time.
- **Example**: `event Meeting /from 2024-09-21T14:00 /to 2024-09-21T16:00`
- **Expected output**: Got it. I've added this task: [E][ ] Meeting (from: Sep 21 2024T14:00 to: Sep 21 2024T16:00) Now you have 3 tasks in the list.

### 4. **list**: Lists all tasks
- **Description**: Displays all the tasks currently in the task list.
- **Example**: `list`
- **Expected output**:
Here are the tasks in your list:

[T][ ] Read Book
[D][ ] Submit report (by: Sep 21 2024)
[E][ ] Meeting (from: Sep 21 2024T14:00 to: Sep 21 2024T16:00)

### 5. **mark**: Marks a task as done
- **Usage**: `mark <task_number>`
- **Description**: Marks a specific task as completed.
- **Example**: `mark 1`
- **Expected output**: Nice! I've marked this task as done: [T][X] Read Book

### 6. **unmark**: Unmarks a task as undone
- **Usage**: `unmark <task_number>`
- **Description**: Unmarks a task, indicating it's not done yet.
- **Example**: `unmark 1`
- **Expected output**: OK, I've marked this task as not done yet: [T][ ] Read Book

### 7. **delete**: Deletes a task
- **Usage**: `delete <task_number>`
- **Description**: Removes a specific task from the list.
- **Example**: `delete 1`
- **Expected output**:
Noted. I've removed this task: [T][ ] Read Book Now you have 2 tasks in the list.

### 8. **find**: Finds tasks containing a keyword
- **Usage**: `find <keyword>`
- **Description**: Searches and displays tasks that match the given keyword.
- **Example**: `find report`
- **Expected output**:
Here are the matching tasks in your list:

[D][ ] Submit report (by: Sep 21 2024)

### 9. **bye**: Exits the chatbot
- **Description**: Terminates the chatbot session.
- **Example**: `bye`
- **Expected output**: Bye. Hope to see you again soon!

### 10. **help**: Displays available commands
- **Description**: Shows a list of all commands with a brief description of their usage.
- **Example**: `help`
- **Expected output**:



Running the Jeriel-1.1.jar file `java --module-path "C:/Users/jerie/Downloads/openjfx-17.0.12_windows-x64_bin-sdk/javafx-sdk-17.0.12/lib" --add-modules javafx.controls,javafx.fxml -jar "C:/Users/jerie/Desktop/Jeriel-1.0.jar"
`
