# Miku User Guide

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
1. [Features](#features)
    - [View the task list: `list`](#view-the-task-list-list)
    - [Adding todos: `todo`](#adding-todos-todo)
    - [Adding events: `event`](#adding-events-event)
    - [Adding deadlines: `deadline`](#adding-deadlines-deadline)
    - [Mark a task as complete: `mark`](#mark-a-task-as-complete-mark)
    - [Mark a task as incomplete: `unmark`](#mark-a-task-as-incomplete-unmark)
    - [Delete a task: `delete`](#delete-a-task-delete)
    - [Find a task by a input string: `find`](#find-a-task-by-a-input-string-find)
    - [Set the priority of a task: `set priority`](#set-the-priority-of-a-task-set-priority)
1.  [FAQ](#faq)
2.  [Command Summary](#command-summary)


## Introduction

This is a project done by Xingye for a greenfield Java project. It's named after the famous japnese Idol Hatsune Miku. Given below are instructions on how to use it.


## Quick Start

1. Ensure you have Java `17` (preferred) or above installed.
2. Download the `Miku.jar` file from here
3. Double click to run the file **OR** Open a command terminal, `cd` to the folder where the jar file is located, run the jar file using `java -jar Miku.jar`.
   A GUI similar to the below should appear, the example shows what happens when `list` command is entered.
   
<img width="446" alt="image" src="https://zhou-colla.github.io/ip/Ui.png">
5. Type the command in the command box and press the send button to chat with Miku. e.g. typing `list` will prompt Miku to show you what events that you have told Miku to memorise, check the [Features](##Features) below for more commands.

## Features

**Notes about command format:**
For simplicity, all command sections should be entered in lower cases. 
E.g. The command sections are highlighted:
- `**list**`, `**todo** I want to read a book.`

**Notes about the format of Miku's responses:**
Miku's responses will be wrapped in a dialog box. E.g. Miku's response to `list` command is shown below.

<img width="390" alt="image" src="https://github.com/user-attachments/assets/ae055792-fc46-43b6-825a-ad5a839c4bfb">

### View the task list: `list`
Ask Miku to show the items in the task list. 

Format: `list`

Example:
`list`

Expected outcome of the example:

<img width="378" alt="image" src="https://github.com/user-attachments/assets/a333600c-5bc0-4e55-b73e-991e1ef71e38">



### Adding todos: `todo`
Ask Miku to add a todo task to the task list. 

Format: `todo DESCRIPTION`

Example:
`todo I want to read a book`

Expected outcome of the example:

<img width="359" alt="image" src="https://github.com/user-attachments/assets/553f208d-c1fc-429e-88b3-012130550620">

### Adding events: `event`
Ask Miku to add a event task to the task list. 

Format: `event DESCRIPTION /from FROMDATETIME /to TODATETIME`
FROMDATETIME and TODATETIME has to be in format YYYY-MM-DDTHH:MM:SS (ISO 8601 standard)

Example: `event test4 /from 2024-08-29T14:30:00 /to 2024-08-29T14:30:11`

Expected outcome of the example:

<img width="367" alt="image" src="https://github.com/user-attachments/assets/c9ec86da-855d-402d-b3b0-42185c970f1d">


### Adding deadlines: `deadline`
Ask Miku to add a deadline task to the task list. 

Format: `deadline DESCRIPTION /by DEADLINE`
DEADLINE has to be in format YYYY-MM-DD

Example: `deadline test2 /by 1972-05-24`

Expected outcome of the example:

<img width="348" alt="image" src="https://github.com/user-attachments/assets/d7edefdb-2523-496d-acc7-37e2762a6f76">


### Mark a task as complete: `mark`
Mark a task as complete

Format: mark INDEX
INDEX: the index of the task as shown when running `list`

Example: `mark 2`

Expected outcome of the example:

<img width="358" alt="image" src="https://github.com/user-attachments/assets/b07a845e-c71b-4123-a0e5-8545015415af">

where test2 task has a index value of 2:

### Mark a task as incomplete: `unmark`
Mark a task as incomplete

Format: unmark INDEX
INDEX: the index of the task as shown when running `list`

Example: `unmark 2`

Expected outcome of the example:

<img width="377" alt="image" src="https://github.com/user-attachments/assets/1a971686-2c00-46c4-9261-6aa4264c1518">

where test2 task has a index value of 2:

### Delete a task: `delete`
Delete a task from the task list

Format: delete INDEX
INDEX: the index of the task as shown when running `list`

Example: `delete 2`

Expected outcome of the example:

<img width="380" alt="image" src="https://github.com/user-attachments/assets/f2d72a66-9790-421e-b93f-24ca51689c8d">

where test2 task has a index value of 2:

### Find a task by a input string: `find`
Find a task with a given string from the task list

Format: find STRING
STRING: the string value that the description of the task contains

Example: `find Read a book`

Expected outcome of the example:

<img width="374" alt="image" src="https://github.com/user-attachments/assets/cf63059a-c98c-496a-a21a-d4f42bde915c">



### Set the priority of a task: `set priority`
Set the priority of a task


Format: set priority INDEX PRIORITY
PRIORITY: enum, The new priority of the task, valid values: LOW, MEDIUM, HIGH
INDEX: the index of the task as shown when running `list`

Example: `find Read a book`

Expected outcome of the example:

<img width="373" alt="image" src="https://github.com/user-attachments/assets/14cbd763-8676-4b68-9e71-df5fc05cdb79">


## FAQ
**Q:** How do I exit the program on MacOS?

**A:** Simply click the exit button at the top left corner of the chat window. 

## Command Summary
| Command | Format |
| ------- | ------ |
| `list`| `list` |
| `todo`| `todo DESCRIPTION` |
| `event`| `event DESCRIPTION /from FROMDATETIME /to TODATETIME` |
| `deadline`| `deadline DESCRIPTION /by DEADLINE` |
| `mark`| `mark INDEX` |
| `unmark`| `unmark INDEX` |
| `delete`| `delete INDEX` |
| `find`| `find STRING` |
| `set priority`| `set priority INDEX PRIORITY` |

