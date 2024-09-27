# MGTOW User Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Command-Line Interface (CLI)](#command-line-interface-cli)
4. [Graphical User Interface (GUI)](#graphical-user-interface-gui)
5. [Task Management](#task-management)
6. [Advanced Features](#advanced-features)

## Introduction

Welcome to the MGTOW (Man Going Their Own Way) Task Management Application. This application helps you organize and manage your tasks efficiently, whether you prefer a command-line interface or a graphical user interface.

![Example Ui](/Ui.png)

## Getting Started

### Installation

1. Ensure you have Java Runtime Environment (JRE) 8 or later installed on your system.
2. Download the [MGTOW](https://github.com/GuanpengR/ip/releases) application JAR file.
3. Place the JAR file in a directory of your choice.

### Launching the Application

- To launch the GUI version:
  ```
  java -jar mgtow.jar
  ```
- To launch the CLI version:
  ```
  java -jar mgtow.jar --cli
  ```

## Command-Line Interface (CLI)

The CLI allows you to manage your tasks using text commands.

### Available Commands

- `todo <description>`: Add a new todo task
- `deadline <description> /by <yyyy-mm-dd HHmm>`: Add a new task with a deadline
- `event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>`: Add a new event
- `list`: Display all tasks
- `mark <task number>`: Mark a task as completed
- `unmark <task number>`: Unmark a task as not completed
- `delete <task number>`: Remove a task from the list
- `find <keyword>`: Search for tasks containing the keyword
- `sort`: Sort tasks by date
- `bye`: Exit the application

### Examples

```
todo Read a book
deadline Submit report /by 2023-09-30 1700
event Team meeting /from 2023-09-25 1400 /to 2023-09-25 1500
list
mark 1
unmark 1
delete 2
find report
sort
```

## Graphical User Interface (GUI)

The GUI provides a user-friendly interface for managing your tasks.

### Main Window

The main window consists of:
- A chat-like interface displaying your interactions with MGTOW
- An input field for entering commands
- A send button to submit commands

### Using the GUI

1. Type your command in the input field at the bottom of the window.
2. Click the send button or press Enter to submit the command.
3. The response will appear in the chat interface above.

## Task Management

### Adding Tasks

- Todo: Use the `todo` command followed by the task description.
- Deadline: Use the `deadline` command followed by the task description and the deadline date/time.
- Event: Use the `event` command followed by the event description, start date/time, and end date/time.

### Viewing Tasks

Use the `list` command to view all tasks. Each task will be displayed with its index number, which you can use for other commands like `done` or `delete`.

Example: `list`

```
Here are the tasks in your list:
1.[T][]print notes

```
### Completing Tasks

Use the `mark` command followed by the task number to mark a task as completed.

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] print notes

```
### Deleting Tasks

Use the `delete` command followed by the task number to remove a task from your list.

Example: `delete 1`

```
Noted. I've removed this task:
[T][X] print notes
Now you have 0 tasks in the list.

```
## Advanced Features

### Searching Tasks

Use the `find` command followed by a keyword to search for tasks containing that keyword in their description.

Example: `find notes`

```
Here are the matching tasks in your list:
1.[T][X]print notes
```

### Sorting Tasks

Use the `sort` command to sort your tasks by date. Tasks without dates will appear at the end of the list.

Example: sort

```
Here are the tasks in your list, sorted by date:
1.[D][X]return book(by Sep 01 2024, 11.59pm)
2.[T][X]print notes
```
---
