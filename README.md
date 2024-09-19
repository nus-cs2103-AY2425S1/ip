# SkD User Guide
![Ui.png](docs/Ui.png)

Welcome to *SKD* (Simple Keeptracker Duke).  
*SKD* is a personal task manager chatbot designed to help you track tasks easily.  
From simple to-dos to complex events and deadlines, *SKD* helps you organize your day effectively.

---

## Table of Contents:
* 1. [Features](#features)
* 2. [Commands Summary](#commands-summary)
* 3. [Detailed Features](#detailed-features)
    * - [Adding Tasks (ToDo, Event, Deadline)](#1-adding-tasks)
    * - [Marking and Unmarking Tasks](#2-marking-and-unmarking-tasks)
    * - [Snoozing Tasks](#3-snoozing-tasks)
    * - [Deleting Tasks](#4-deleting-tasks)
    * - [Finding Tasks](#5-finding-tasks)
    * - [Listing Tasks](#6-listing-tasks)
    * - [Exiting the Application](#7-exiting-the-application)
* 4. [Saving Tasks](#saving-tasks)

---

### Features:
* Add different types of tasks (ToDo, Event, Deadline).
* Mark or unmark tasks as complete.
* Snooze deadlines and events.
* Delete tasks.
* Find tasks by keyword.
* List all tasks.
* Automatic saving of tasks.
* User-friendly interface with snoozing and task search.
* Exiting the application via a simple command.

---

## Commands Summary:
* `todo [task]` : Adds a ToDo task.
* `deadline [task] /by [yyyy-mm-dd hh:mm]` : Adds a Deadline task.
* `event [task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]` : Adds an Event task.
* `mark [index]` : Marks a task as done.
* `unmark [index]` : Unmarks a task as undone.
* `snooze [index] [days]` : Snoozes a deadline or event by a certain number of days.
* `delete [index]` : Deletes a task.
* `find [keyword]` : Finds tasks containing a keyword.
* `list` : Lists all tasks.
* `bye` : Exits the application.

## Detailed Features:

### 1. Adding Tasks:
* `todo [task]` : Adds a ToDo task.
* Input: `todo Buy groceries`
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in the list.
```
* `deadline [task] /by [yyyy-mm-dd hh:mm]` : Adds a Deadline task.
* Input: `deadline Submit assignment /by 2024-09-20 23:59`
```
Got it. I’ve added this task:
  [D][ ] Submit assignment (by: Sep 20 2024, 23:59)
Now you have 2 tasks in the list.
```
* `event [task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]` : Adds an Event task.
* Input: `event Team meeting /from 2024-09-10 10:00 /to 2024-09-10 12:00`
```
Got it. I’ve added this task:
  [E][ ] Team meeting (from: Sep 10 2024, 10:00 to: Sep 10 2024, 12:00)
Now you have 3 tasks in the list.
```

### 2. Marking and Unmarking Tasks:
* `mark [index]` : Marks a task as complete.
* Input: `mark 2`
```
  Nice! I've marked this task as done:
   [D][X] Submit assignment (by: Sep 20 2024, 23:59) tasks in the list.
```
* `unmark [index]` : Unmarks a task as incomplete.
* Input: `unmark 2`
```
 OK, I've marked this task as not done yet:
   [D][ ] Submit assignment (by: Sep 20 2024, 23:59)
```

### 3. Snoozing Tasks:
* `snooze [index] [days]` : Snoozes a deadline or event by the specified number of days.
* Input: `snooze 2 3`
```
 Snoozed deadline task by 3 days: 
   [D][ ] Submit assignment (by: Sep 23 2024, 23:59)
```

### 4. Deleting Tasks:
* `delete [index]` : Deletes a task from the list.
* Input: `delete 1`
```
 Noted. I've removed this task:
   [T][ ] Buy groceries
 Now you have 2 tasks in the list.
```

### 5. Finding Tasks:
* `find [keyword]` : Searches for tasks that match the keyword.
* Input: `find meeting`
```
 Here are the matching tasks in your list:
   1. [E][ ] Team meeting (from: Sep 10 2024, 10:00 to: Sep 10 2024, 12:00)
```

### 6. Listing Tasks:
* `list` : Displays all tasks in the task list.
* Input: `list`
```
 Here are the tasks in your list:
   1. [D][ ] Submit assignment (by: Sep 23 2024, 23:59)
   2. [E][ ] Team meeting (from: Sep 10 2024, 10:00 to: Sep 10 2024, 12:00)
```

### 7. Exiting the Application:
* `bye` : Exits the application.
* Input: `bye`
```
 Bye. Hope to see you again soon!
```

## Saving Tasks:
* Tasks are automatically saved after every change. When you restart *SKD*, tasks are loaded from the saved file.

## FAQ:
* **Q**: How do I snooze a task?  
  **A**: Use the command `snooze [index] [days]` to delay the task.
* **Q**: Can I find tasks by keyword?  
  **A**: Yes, use the `find [keyword]` command to search for tasks.
* **Q**: Will my tasks be saved automatically?  
  **A**: Yes, tasks are automatically saved after every change.