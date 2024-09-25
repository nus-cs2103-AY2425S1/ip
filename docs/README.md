# Bopes Task Manager User Guide

Bopes is a simple yet powerful task management application that allows you to keep track of your to-do items, deadlines, and events. It supports various commands to manage your tasks and helps you stay organized.

## Product Screenshot

![Bopes UI](./Ui.png)

## Product Introduction

Bopes Task Manager helps you organize your tasks with a simple, user-friendly interface. You can add to-do items, set deadlines, schedule events, and mark tasks as completed. Whether you need to list, find, or delete tasks, Bopes provides an intuitive command-line interface that makes task management easy.

---

## Features

### 1. Adding To-Do Tasks

Adds a simple to-do task to your task list.

**Usage**:  
`todo [description]`

**Example**:  
`todo Buy groceries`

**Expected Outcome**:
Added task: [T][ ] Buy groceries

---

### 2. Adding Deadlines

Add a task with a specific deadline to your list.

**Usage**:  
`deadline [description] /by [due date and time]`

**Example**:  
`deadline Finish report /by 20/09/2024 11:00 pm`

**Expected Outcome**:
Added task: [D][ ] Finish report (by: 2024 09 20 11:00 pm)

---

### 3. Adding Events

Schedule an event with a start and end time.

**Usage**:  
`event [description] /from [start time] /to [end time]`

**Example**:  
`event Team meeting /from 20/09/2024 10:00 am /to 20/09/2024 12:00 pm`

**Expected Outcome**:
Added task: [E][ ] Team meeting (from: 20/09/2024 10:00 am /to 20/09/2024 12:00 pm)

---

### 4. Listing All Tasks

Displays all tasks in your task list.

**Usage**:  
`list`

**Expected Outcome**:

1. [T][ ] Buy groceries
2. [D][ ] Finish report (by: 2024-09-20 18:00)
3. [E][ ] Team meeting (from: 2024-09-18 10:00 to: 2024-09-18 12:00)

---

### 5. Marking a Task as Done

Marks a task as completed in your task list.

**Usage**:  
`mark [task number]`

**Example**:  
`mark 1`

**Expected Outcome**:
Marked task: [T][X] Buy groceries

---

### 6. Unmarking a Task

Unmarks a completed task, marking it as not done.

**Usage**:  
`unmark [task number]`

**Example**:  
`unmark 1`

**Expected Outcome**:
Unmarked task: [T][ ] Buy groceries

---

### 7. Delete a Task

Delete a task

**Usage**:  
`unmark [task number]`

**Example**:  
`delete 1`

**Expected Outcome**:
Deleted task: [T][ ] Buy groceries

---

### 8. Finding Tasks by Keyword

Search for tasks that contain a specific keyword.

**Usage**:  
`find [keyword]`

**Example**:  
`find report`

**Expected Outcome**:
[D][ ] Finish report (by: 2024-09-20 18:00)

---

### 9. Exiting the Application

Exits the Bopes application.

**Usage**:  
`bye`

**Expected Outcome**:
Goodbye! The program will exit in 5 seconds...
