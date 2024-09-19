# Bocchi User Guide

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Commands](#commands)
    - [Create a todo](#create-a-todo)
    - [Create a deadline](#create-a-deadline)
    - [Create an event](#create-an-event)
    - [List all tasks](#list-all-tasks)
    - [Mark a task as done/undone](#mark-a-task-as-doneundone)
    - [Delete a task](#delete-a-task)
    - [Add/delete tag(s) to a task](#adddelete-tags-to-a-task)
    - [Exit the program](#exit-the-program)
- [Datetime formats supported](#datetime-formats-supported)
    - [Date Format](#date-format)
    - [Time Format](#time-format)
    - [Note on default values](#note-on-default-values)


<img src="Ui.png" alt="Product Screenshot" width="500">

## Introduction
Bocchi is here to help you keep track of your tasks!  
Simply type in your tasks and Bocchi will remember them for you.  
Wechat-like GUI makes you feel like you are chatting with Bocchi! 

### Features
1. Add events, deadlines and todos
2. Mark tasks as done/undone
3. Delete tasks
4. Find tasks by tags and/or type
5. List all tasks, optionally filtered by tags and/or type
6. Auto-save tasks to disk
7. Load tasks from disk on startup


## Commands

#### Note on command format:
- `<>` denotes a required field.
- `[]` denotes an optional field.
- `+` denotes one or more of the preceding element.
- all other text should be typed in verbatim.
- parameter order is not important.
- datetime format is specified in the [Datetime formats supported](#datetime-formats-supported) section.
- index is the number of the task shown in the list command.

### Create a todo
```
todo <description> [/tag <tag>+]
```
A todo is a task without a deadline.
It can be assigned with multiple tags during creation, or using the tag command.

### Create a deadline
```
deadline/ddl <description> /by <dueDateTime> [/tag <tag>+]
```
A deadline is a task with a due date.
It can be assigned with multiple tags during creation, or using the tag command.

### Create an event
```
event <description> /from <fromDateTime> /to <toDateTime> [/tag <tag>+]
```
An event is a task with a start and end time.
It can be assigned with multiple tags during creation, or using the tag command.

#### Example
```
event CA1 meeting /from 9/18 12:00 /to 9/18 14:00 /tag CS2101
```

### List all tasks
```
list [/type <type>+] [/tag <tag>+]
```
Lists all tasks, optionally filtered by type and/or tag.

#### Example
```
list /type todo /tag exam CS2101
```

### Mark a task as done/undone
```
mark <index>
```
```
unmark <index>
```

### Delete a task
```
delete/del <index>
```

### Add/delete tag(s) to a task
```
tag <index> /tag <tag>+
```
```
ntag <index> /tag <tag>+
```

### Exit the program
```
bye
```
Ends the conversation.


## Datetime formats supported

| Date Format | Example     |
|-------------|-------------|
| yyyy-M-d    | 2024-9-1    |
| yyyy-MM-dd  | 2024-09-18  |
| yyyy-MMM-dd | 2024-Sep-18 |
| yyyy-dd-MMM | 2024-18-Sep |
| M-d         | 9-1         |
| MM-dd       | 09-18       |
| yyyy/M/d    | 2024/9/1    |
| yyyy/MM/dd  | 2024/09/18  |
| yyyy/MMM/dd | 2024/Sep/18 |
| yyyy/dd/MMM | 2024/18/Sep |
| M/d         | 9/1         |
| MM/dd       | 09/18       |
| MMM dd      | Sep 18      |
| dd MMM      | 18 Sep      |

| Time Format | Example       |
|-------------|---------------|
| HH:mm:ss    | 14:30:00      |
| HH:mm       | 14:30         |
| H:mm:ss     | 4:30:00       |
| H:mm        | 4:30          |

### Note on default values
The datetime input can be date only, time only, or both.

- If the year is not specified, the current year is assumed.
- If the date is not specified at all, the current date is assumed.
- If the second is not specified, 00 is assumed.
- If the time is not specified at all, 23:59:59 is assumed.