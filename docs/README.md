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
```
Here are the available commands:

todo <task description> - Adds a todo task
deadline <task description> /by <date> - Adds a deadline task
event <task description> /from <start> /to <end> - Adds an event task
list - Lists all tasks
mark <task number> - Marks a task as done
unmark <task number> - Unmarks a task
delete <task number> - Deletes a task
find <keyword> - Finds tasks containing the keyword
help - Shows this help message
bye - Exits the program
```
<!-- ## Feature ABC

// Feature details


## Feature XYZ

// Feature details -->

```
Hello from
     _  ____  ___   _  ____  _      
    | || ___|| _ \ | || ___|| |     
    | |||___ ||_> || |||___ | |     
 _  | || ___||  _/ | || ___|| |     
| |_| |||___ ||\\  | |||___ | |____ 
 \___/ |____||| \\ |_||____||______|

____________________________________________________________
 Hello! I'm Jeriel
 What can I do for you?
____________________________________________________________
```


## Project Directory Structure
```
├── .gradle
├── app
├── build
│   ├── classes
│   ├── generated
│   ├── libs
│   ├── tmp
├── data
├── docs
│   └── README.md
├── gradle
│   └── wrapper
├── src
│   └── main
│       └── java
│           ├── data
│           └── jeriel
│               ├── command
│               │   ├── AddDeadlineCommand.java
│               │   ├── AddEventCommand.java
│               │   ├── AddTodoCommand.java
│               │   ├── Command.java
│               │   ├── DeleteCommand.java
│               │   ├── ExitCommand.java
│               │   ├── FindCommand.java
│               │   ├── ListCommand.java
│               │   ├── MarkCommand.java
│               │   └── UnmarkCommand.java
│               ├── task
│               │   ├── Deadline.java
│               │   ├── Event.java
│               │   ├── Task.java
│               │   └── Todo.java
│               ├── ui
│               │   ├── Main.java
│               │   ├── MainWindowController.java
│               └── util
│               │   ├── JerielException.java
│               │   ├── Parser.java
│               │   ├── Storage.java
│               │   ├── TaskList.java 
│               │   ├── Ui.java 
│               └── Jeriel.java
│   └── resources
│       └── view
│           └── MainWindow.fxml
├── test
│   └── java
│       └── jeriel
│           └── task
│               ├── EventTest.java
│               └── TodoTest.java
├── .gitignore
├── build.gradle
├── checkstyle.xml
├── CONTRIBUTORS.md
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle
