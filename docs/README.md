# Zero User Guide

Zero is a task management chatbot that helps you manage your to-do lists efficiently through a command-line interface or a GUI. It allows you to add, delete, mark tasks as done, snooze deadlines, and more.

<img width="399" alt="image" src="https://github.com/user-attachments/assets/6178ba5f-59c6-4d85-9940-872d8ac1affe">


## Table of Contents
1. [Introduction](#introduction)
2. [Commands Overview](#commands-overview)
   - [Adding Deadlines](#adding-deadlines)
   - [Adding Events](#adding-events)
   - [Adding Todos](#adding-todos)
   - [Marking Tasks as Done](#marking-tasks-as-done)
   - [Unmarking Tasks](#unmarking-tasks)
   - [Snoozing Deadlines](#snoozing-deadlines)
   - [Deleting Tasks](#deleting-tasks)
   - [Listing All Tasks](#listing-all-tasks)
   - [Finding Tasks by Keyword](#finding-tasks-by-keyword)
   - [Exiting the Application](#exiting-the-application)
3. [Command List Summary](#command-list-summary)

## Introduction

Welcome to Zero, your personal task management assistant! Zero helps you keep track of your tasks with ease. Whether you prefer using a command-line interface or a graphical user interface, Zero has got you covered.

## Commands Overview

### Adding Todos
Add a simple to-do task.
- **Command Structure:** `todo <description>`
- **Example Usage:** `todo read book`
- **Expected Output:** 
  - Adds a to-do task with the specified description.
  - Displays: `Got it. I've added this task: [T][ ] read book`
  
### Adding Deadlines
Add a task with a specific deadline.
- **Command Structure:** `deadline <description> /by <date and time>`
- **Example Usage:** `deadline return book /by 2024-09-01 1800`
- **Expected Output:** 
  - Adds a deadline task with the specified description and due date.
  - Displays: `Got it. I've added this task: [D][ ] return book (by: 1 SEPTEMBER 2024)`

### Adding Events
Add an event with a start and end time.
- **Command Structure:** `event <description> /from <start date and time> /to <end date and time>`
- **Example Usage:** `event project meeting /from 2024-09-01 1400 /to 2024-09-01 1600`
- **Expected Output:** 
  - Adds an event task with the specified description, start, and end times.
  - Displays: `Got it. I've added this task: [E][ ] project meeting (from: 1 SEPTEMBER 2024 to: 1 SEPTEMBER 2024)`

### Marking Tasks as Done
Mark a task as completed.
- **Command Structure:** `mark <task number>`
- **Example Usage:** `mark 1`
- **Expected Output:** 
  - Marks the specified task as done.
  - Displays: `Nice! I've marked this task as done: [T][X] study`

### Unmarking Tasks
Unmark a task to indicate it is not done.
- **Command Structure:** `unmark <task number>`
- **Example Usage:** `unmark 1`
- **Expected Output:** 
  - Unmarks the specified task.
  - Displays: `OK, I've marked this task as not done yet: [T][ ] study`

### Snoozing Deadlines
Postpone a deadline task.
- **Command Structure:** `snooze <task number>`
- **Example Usage:** `snooze 2`
- **Expected Output:** 
  - Snoozes the deadline of the specified task.
  - Displays: `Nice! I've postponed this task: [D][ ] return book (by: 3 NOVEMBER 2020)`

### Deleting Tasks
Remove a task from the list.
- **Command Structure:** `delete <task number>`
- **Example Usage:** `delete 3`
- **Expected Output:** 
  - Deletes the specified task from the list.
  - Displays: `Noted. I've removed this task: [E][ ] book fair (from: 24 NOVEMBER 2020 to: 24 DECEMBER 2020)`

### Listing All Tasks
View all tasks in your list.
- **Command Structure:** `list`
- **Example Usage:** `list`
- **Expected Output:** 

### Finding Tasks by Keyword
- **Command Structure:** `find <keyword>`
- **Example Usage:** `find book`
- **Expected Output:** Lists all tasks containing the specified keyword.

### Exiting the Application
- **Command Structure:** `bye`
- **Example Usage:** `bye`
- **Expected Output:** Exits the application.

## Command List Summary

| **Feature**                | **Command**                                                  | **Example Usage**                              |
|----------------------------|--------------------------------------------------------------|------------------------------------------------|
| **Adding Deadlines**        | `deadline <description> /by <date and time>`                 | `deadline return book /by 2024-09-01 1800`     |
| **Adding Events**           | `event <description> /from <start date and time> /to <end date and time>` | `event project meeting /from 2024-09-01 1400 /to 2024-09-01 1600` |
| **Adding Todos**            | `todo <description>`                                         | `todo read book`                               |
| **Marking Tasks as Done**   | `mark <task number>`                                         | `mark 1`                                       |
| **Unmarking Tasks**         | `unmark <task number>`                                       | `unmark 1`                                     |
| **Snoozing Deadlines**      | `snooze <task number>`                                       | `snooze 2`                                     |
| **Deleting Tasks**          | `delete <task number>`                                       | `delete 3`                                     |
| **Listing All Tasks**       | `list`                                                       | `list`                                         |
| **Finding Tasks by Keyword**| `find <keyword>`                                             | `find book`                                    |
| **Exiting the Application** | `bye`                                                        | `bye`                                          |

---

