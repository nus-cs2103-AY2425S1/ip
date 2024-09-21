# Echo Chatbot User Guide

Welcome to **Echo**, your friendly chatbot that helps you manage your tasks efficiently! Echo allows you to create and manage various types of tasks, including to-dos, deadlines, and events, with simple commands. This guide will walk you through all the features and commands of Echo so you can use it with ease.

---

## Table of Contents
1. [Getting Started](#getting-started)
2. [Commands Overview](#commands-overview)
    - [Add a To-do](#add-a-to-do)
    - [Add a Deadline](#add-a-deadline)
    - [Add an Event](#add-an-event)
    - [Mark a Task as Completed](#mark-a-task-as-completed)
    - [Unmark a Task](#unmark-a-task)
    - [Delete a Task](#delete-a-task)
    - [List All Tasks](#list-all-tasks)
    - [Find a Task](#find-a-task)
    - [Exit Echo](#exit-echo)
3. [Duplicate Entry Handling](#duplicate-entry-handling)
4. [Command Summary](#command-summary)

---

## Getting Started

Once you start Echo, you can immediately begin managing your tasks using simple text commands. Each command follows a specific format, so be sure to follow the structure provided in this guide for the best experience.

---

## Commands Overview

### Add a To-do
The `todo` command allows you to add simple to-dos with a description.

**Command format:**

todo [description]


- Example: `todo Buy groceries`

This adds a to-do entry with the description "Buy groceries."

---

### Add a Deadline
The `deadline` command allows you to create tasks that are due by a specific date.

**Command format:**

deadline [description] /by [date]



- Example: `deadline Submit assignment /by 12/12/2024 2359`

The date can be entered in the `dd/mm/yyyy` format, with an optional 24-hour time (`hhhh`). Echo also supports more casual date entries like `"tmr"` or `"later"`.

- Example: `deadline Finish project /by tmr`

---

### Add an Event
The `event` command allows you to schedule events that occur between two dates or times.

**Command format:**

event [description] /from [start date/time] /to [end date/time]



- Example: `event Team meeting /from 11/11/2024 0900 /to 11/11/2024 1000`

This schedules an event called "Team meeting" from 9 AM to 10 AM on November 11, 2024.

---

### Mark a Task as Completed
The `mark` command allows you to mark a task (to-do, deadline, or event) as completed.

**Command format:**

mark [task number]



- Example: `mark 2`

This marks the second task in the list as completed.

---

### Unmark a Task
The `unmark` command allows you to unmark a previously completed task, changing its status back to "incomplete."

**Command format:**

unmark [task number]



- Example: `unmark 2`

This unmarks the second task in the list, changing its status to "incomplete."

---

### Delete a Task
The `delete` command allows you to delete a task from the list by specifying its number.

**Command format:**

delete [task number]



- Example: `delete 3`

This deletes the third task in the list.

---

### List All Tasks
The `list` command displays a list of all tasks that have been added, including their status (completed or incomplete).

**Command format:**

list


This shows a list of all tasks.

---

### Find a Task
The `find` command allows you to search for tasks by a specific keyword in the description.

**Command format:**

find [keyword]



- Example: `find groceries`

This searches for and displays all tasks with the keyword "groceries" in the description.

---

### Exit Echo
The `bye` command tells Echo to say bye back to you.

**Command format:**

bye


Echo will bid you farewell and end the session.

---

## Duplicate Entry Handling

Echo prevents you from adding duplicate entries to your task list. If you attempt to add a task with the same description and task type (e.g., another to-do with the description "Buy groceries"), Echo will notify you that a duplicate entry has been found, and the new entry will be ignored.

---

## Command Summary

| Command | Description                                       |
| --- |---------------------------------------------------|
| `todo [description]` | Adds a to-do task.                                |
| `deadline [description] /by [date]` | Adds a deadline task.                             |
| `event [description] /from [start date] /to [end date]` | Adds an event with a start and end date.          |
| `mark [task number]` | Marks the specified task as completed.            |
| `unmark [task number]` | Unmarks the specified task.                       |
| `delete [task number]` | Deletes the specified task.                       |
| `list` | Lists all tasks with their statuses.              |
| `find [keyword]` | Finds and lists tasks with the specified keyword. |
| `bye` | Say bye to the the chatbot.                       |

---

Thank you for using **Echo**! We hope this guide helps you get the most out of your task management experience.