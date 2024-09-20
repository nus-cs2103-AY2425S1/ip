# Samson Chatbot User Guide

Welcome to **Samson Chatbot**! Samson is a simple CLI (Command Line Interface) task manager that helps you keep track of your tasks in an organized way. You can add various types of tasks (ToDos, Deadlines, Events), mark them as completed, delete them, and even search for specific tasks.

## Table of Contents
- [Installation](#installation)
- [Features](#features)
    - [1. Adding a ToDo](#1-adding-a-todo)
    - [2. Adding a Deadline](#2-adding-a-deadline)
    - [3. Adding an Event](#3-adding-an-event)
    - [4. Listing Tasks](#4-listing-tasks)
    - [5. Marking a Task as Completed](#5-marking-a-task-as-completed)
    - [6. Unmarking a Task as Not Completed](#6-unmarking-a-task-as-not-completed)
    - [7. Deleting a Task](#7-deleting-a-task)
    - [8. Finding Tasks](#8-finding-tasks)
    - [9. Exiting the Chatbot](#9-exiting-the-chatbot)
- [Storage](#storage)
- [Command Summary](#command-summary)

---

## Installation

1. Ensure you have Java installed on your system (Java 17).
2. Download the latest release of the Samson chatbot JAR file from the repository.
3. Place the JAR file in a directory of your choice.
4. Open a terminal or command prompt in that directory.
5. Run the following command to start Samson:
    ```
    java -jar samson.jar
    ```

---

## Features

### 1. Adding a ToDo
Add a simple ToDo task with no date attached.

- **Format**:
  ```
  todo [task description]
  ```

- **Example**:
  ```
  todo Read a book
  ```

---

### 2. Adding a Deadline
Add a task with a specific deadline.

- **Format**:
  ```
  deadline [task description] /by [YYYY-MM-DD HHmm]
  ```

- **Example**:
  ```
  deadline Submit assignment /by 2024-09-15 1800
  ```

---

### 3. Adding an Event
Add a task that has a specific start and end time.

- **Format**:
  ```
  event [task description] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]
  ```

- **Example**:
  ```
  event Project meeting /from 2024-09-10 1400 /to 2024-09-10 1600
  ```

---

### 4. Listing Tasks
View all the tasks you have added.

- **Format**:
  ```
  list
  ```

- **Example Output**:
  ```
  Here are the tasks in your list:
  1. [T][ ] Read a book
  2. [D][X] Submit assignment (by: Sep 15 2024 18:00)
  3. [E][ ] Project meeting (from: Sep 10 2024 14:00 to: Sep 10 2024 16:00)
  ```

---

### 5. Marking a Task as Completed
Mark a task as done.

- **Format**:
  ```
  mark [task number]
  ```

- **Example**:
  ```
  mark 1
  ```

---

### 6. Unmarking a Task as Not Completed
Unmark a task to mark it as not done.

- **Format**:
  ```
  unmark [task number]
  ```

- **Example**:
  ```
  unmark 1
  ```

---

### 7. Deleting a Task
Remove a task from your list.

- **Format**:
  ```
  delete [task number]
  ```

- **Example**:
  ```
  delete 3
  ```

---

### 8. Finding Tasks
Find tasks that contain a specific keyword in their description. The search is case-insensitive and allows partial matches.

- **Format**:
  ```
  find [keyword]
  ```

- **Example**:
  ```
  find book
  ```

- **Example Output**:
  ```
  Here are the matching tasks in your list:
  1. [T][ ] Read a book
  ```

---

### 9. Exiting the Chatbot
Exit the Samson chatbot.

- **Format**:
  ```
  bye
  ```

---

## Storage

Samson saves all your tasks automatically to a local file (e.g., `samson.txt`). When you start Samson again, it will automatically load your previous tasks from this file.

The file is located in the `data` directory of the same folder where the JAR file is run. You do not need to manually manage this file; Samson handles it for you.

---

## Command Summary

| Command                  | Description                                   | Example                                      |
|--------------------------|-----------------------------------------------|----------------------------------------------|
| `todo [task]`             | Adds a ToDo task                             | `todo Buy groceries`                         |
| `deadline [task] /by [date-time]` | Adds a Deadline task with a due date    | `deadline Submit report /by 2024-09-15 1800` |
| `event [task] /from [date-time] /to [date-time]` | Adds an Event with start and end times | `event Conference /from 2024-10-10 0900 /to 2024-10-10 1700` |
| `list`                    | Lists all tasks                              | `list`                                       |
| `mark [task number]`       | Marks a task as done                         | `mark 2`                                     |
| `unmark [task number]`     | Marks a task as not done                     | `unmark 2`                                   |
| `delete [task number]`     | Deletes a task                               | `delete 3`                                   |
| `find [keyword]`           | Finds tasks containing the keyword           | `find book`                                  |
| `bye`                     | Exits the chatbot                            | `bye`                                        |

---

With Samson, keeping track of your tasks has never been easier. Start managing your tasks efficiently today!