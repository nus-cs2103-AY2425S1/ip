# Elara User Guide

Elara is a smart task manager designed to help you keep track of your to-dos, deadlines, and events effectively. This guide provides an overview of how to use Elara to organize and manage your tasks efficiently.
![Elara Screenshot](./Ui.png) 
*Screenshot of Elara interface here.*

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Adding Tasks](#adding-tasks)
    - [Adding To-Do Tasks](#adding-to-do-tasks)
    - [Adding Deadlines](#adding-deadlines)
    - [Adding Events](#adding-events)
- [Managing Tasks](#managing-tasks)
    - [Listing Tasks](#listing-tasks)
    - [Marking Tasks as Done](#marking-tasks-as-done)
    - [Deleting Tasks](#deleting-tasks)
- [Searching and Finding](#searching-and-finding)
    - [Finding Tasks by Keyword](#finding-tasks-by-keyword)
- [Duplicate Detection](#duplicate-detection)
- [Exiting the Application](#exiting-the-application)
- [Command Summary](#command-summary)

## Introduction

Elara is a personal task management tool that allows you to keep track of various tasks, set deadlines, and schedule events. It offers an intuitive command-line interface for quick and easy task management.

## Getting Started

To start using Elara, simply launch the application. You'll be greeted with a welcome message prompting you to enter your commands.

## Adding Tasks

Elara supports three types of tasks: to-dos, deadlines, and events.

### Adding To-Do Tasks

To add a simple to-do task, use the `todo` command followed by the task description.

**Command:**
`
todo <task description>
`

**Example:**
`
todo read book
`

**Expected Outcome:**
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

### Adding Deadlines

You can add a task with a specific deadline using the `deadline` command.

**Command:**
`
deadline <task description> /by <yyyy-MM-dd HHmm>
`

**Example:**
`
deadline return book /by 2024-08-06 1300
`

**Expected Outcome:**
```
Got it. I've added this task:
[D][ ] return book (by: Aug 6 2024 01:00 pm)
Now you have 2 tasks in the list.
```

### Adding Events

To add an event that has a start and end time, use the `event` command.

**Command:**
`
event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
`

**Example:**
`
event project meeting /from 2024-08-06 1400 /to 2024-08-06 1600
`

**Expected Outcome:**
```
Got it. I've added this task:
[E][ ] project meeting (from: Aug 6 2024, 02:00 pm to: Aug 6 2024, 04:00 pm)
Now you have 3 tasks in the list.
```

## Managing Tasks

### Listing Tasks

To view all tasks in your list, use the `list` command.

**Command:**
`
list
`

**Expected Outcome:**
`
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Aug 6 2024 01:00 pm)
3. [E][ ] project meeting (from: Aug 6 2024, 02:00 pm to: Aug 6 2024, 04:00 pm)
`

### Marking Tasks as Done

To mark a task as done, use the `mark` command followed by the task number.

**Command:**
`
mark <task index>
`

**Example:**
`
mark 1
`

**Expected Outcome:**
```
Nice! I've marked this task as done:
[T][X] read book
```

### Deleting Tasks

To delete a task from your list, use the `delete` command followed by the task number.

**Command:**
`
delete <task index>
`

**Example:**
`
delete 2
`

**Expected Outcome:**
```
Noted. I've removed this task:
[D][ ] return book (by: Aug 6 2024 01:00 pm)
Now you have 2 tasks in the list.
```

## Searching and Finding

### Finding Tasks by Keyword

You can search for tasks containing a specific keyword using the `find` command.

**Command:**
`
find <keyword>
`

**Example:**
`
find book
`

**Expected Outcome:**
```
Here are the matching tasks in your list:
1. [T][X] read book
```

## Duplicate Detection

Elara automatically checks for duplicate tasks to prevent adding the same task multiple times. This includes tasks with different dates but identical descriptions.

**Example of Duplicate Task Addition:**
`
todo read book
`

**Outcome:**
```
This task already exists in your list.
```

## Exiting the Application

To exit the Elara application, use the `bye` command.

**Command:**
```
bye
```

**Expected Outcome:**
```
Bye. Hope to see you again soon!
```

## Command Summary

- **Add To-Do Task:** `todo <task description>`
- **Add Deadline Task:** `deadline <task description> /by <yyyy-MM-dd HHmm>`
- **Add Event Task:** `event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
- **List Tasks:** `list`
- **Mark Task as Done:** `mark <task index>`
- **Delete Task:** `delete <task index>`
- **Find Task:** `find <keyword>`
- **Exit Application:** `bye`

---

This guide provides a comprehensive overview of Elara's functionality, including how to add and manage tasks, search for tasks, and handle duplicates.