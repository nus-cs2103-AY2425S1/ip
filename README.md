# JBot User Guide

Welcome to **JBot**, your personal task management chatbot. This guide will help you get started and understand how to use JBot effectively.

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
   - [Running the Application](#running-the-application)
   - [User Interface Overview](#user-interface-overview)
- [Commands](#commands)
   - [1. `list`](#1-list)
   - [2. `todo`](#2-todo)
   - [3. `event`](#3-event)
   - [4. `deadline`](#4-deadline)
   - [5. `mark`](#5-mark)
   - [6. `unmark`](#6-unmark)
   - [7. `delete`](#7-delete)
   - [8. `find`](#8-find)
   - [9. `bye`](#9-bye)
   - [10. `help`](#10-help)

---

## Introduction

JBot is a task management chatbot designed to help you organize your tasks efficiently. You can create to-do lists, set deadlines, mark tasks as done, and much more using simple commands.

---

## Getting Started

### Running the Application

1. **Launch the Application**: To start the JBot application, run the `JBot.jar` file, which will start the JavaFX interface.
   ```bash
   java -jar JBot.jar
   ```

2. **Graphical Interface**: Once the application starts, a graphical user interface (GUI) will appear where you can type your commands.

### User Interface Overview

- **Input Box**: Enter your commands here.
- **Output Area**: JBot will display responses and task updates in this section.
- **Task List**: View all your tasks in a scrollable list.

---

## Commands

Here are the main commands you can use to interact with JBot.

### 1. `list`
Displays all tasks currently in the list.

```
list
```

- **Example**:
  ```
  1. [T][ ] Buy groceries
  2. [D][X] Submit assignment (by: Sep 21 2024)
  3. [E][ ] Team meeting (from: 2pm to 4pm)
  ```

### 2. `todo`
Adds a new to-do task to the list.

```
todo <task description>
```

- **Example**:
  ```
  todo Buy groceries
  ```

   - Output:
     ```
     Added: [T][ ] Buy groceries
     ```

### 3. `event`
Adds a new event task to the list with a specified start and end time.

```
event <event description> /from <start time> /to <end time>
```

- **Example**:
  ```
  event Team meeting /from 2pm /to 4pm
  ```

   - Output:
     ```
     Added: [E][ ] Team meeting (from: 2pm to 4pm)
     ```

### 4. `deadline`
Adds a new task with a deadline.

```
deadline <task description> /by <deadline in d/M/yyyy HHmm>
```

- **Example**:
  ```
  deadline return book /by 2/12/2024 1800
  ```

   - Output:
     ```
     Added: [D][ ] Submit assignment (by: Sep 21 2024)
     ```

### 5. `mark`
Marks a specific task as completed. The task index is based on the current list.

```
mark <task index>
```

- **Example**:
  ```
  mark 2
  ```

   - Output:
     ```
     Nice! I've marked this task as done:
     [D][X] Submit assignment (by: Sep 21 2024)
     ```

### 6. `unmark`
Unmarks a completed task.

```
unmark <task index>
```

- **Example**:
  ```
  unmark 2
  ```

   - Output:
     ```
     OK, I've marked this task as not done yet:
     [D][ ] Submit assignment (by: Sep 21 2024)
     ```

### 7. `delete`
Deletes a task from the list.

```
delete <task index>
```

- **Example**:
  ```
  delete 1
  ```

   - Output:
     ```
     Noted. I've removed this task:
     [T][ ] Buy groceries
     ```

### 8. `find`
Finds tasks that match a keyword.

```
find <keyword>
```

- **Example**:
  ```
  find assignment
  ```

   - Output:
     ```
     1. [D][X] Submit assignment (by: Sep 21 2024)
     ```

### 9. `bye`
Exits the application.

```
bye
```

- **Example**:
  ```
  bye
  ```

   - Output:
     ```
     Bye. Hope to see you again soon!
     ```

  The application will close after displaying this message.

### 10. `help`
Displays a help message with available commands and usage instructions.

```
help
```

- **Output**:
    ```
    Here are the available commands:
    - list: Display all tasks.
    - todo <task>: Add a to-do task.
    - event <task> /from <time> /to <time>: Add an event.
    - deadline <task> /by <deadline>: Add a task with a deadline.
    - mark <index>: Mark a task as completed.
    - unmark <index>: Unmark a task.
    - delete <index>: Delete a task.
    - find <keyword>: Find tasks by keyword.
    - bye: Exit the application.
    ```

---

## Additional Information

- JBot stores tasks in a JSON file (`JBotStorage.json`) in the `data` directory.
- All tasks and their statuses will be saved automatically and loaded the next time you run the application.

Feel free to reach out for any questions or feedback!