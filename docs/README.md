# Nayana User Guide

<img width="395" alt="image" src="https://github.com/user-attachments/assets/794a509e-f07c-43ba-b96a-58f21f68a1fd">

## Product Introduction

Nayana is a task manager application designed to help users keep track of their to-dos efficiently. With support for deadlines, events, and to-do lists, Nayana provides a seamless way to organize tasks via a console-based or graphical user interface. She also allows users to categorize tasks and receive reminders for upcoming deadlines, making her an essential tool for improving productivity.

## How can Nayana help me?
This readme serves as the documentation guide for how to talk to Nayana!

## Features

### 1. Add a Deadline Task
You can add a task with a deadline by specifying the task description and a due date

Command Format: `deadline <task description> /by <yyyy-mm-dd>`

Sample input: `deadline Submit report /by 2024-09-20`

Expected Output:
```
Got it! I've added this task:
[D][ ] Submit report (by: Sep 20 2024)
Now you have 1 tasks in the list.

```
### 2. Add an Event Task
You can add an event with a start and end date.

Command Format: `event <task description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

Sample input: `event Team meeting /from 2024-09-25 /to 2024-09-25`

Expected Output:
```
Got it! I've added this task:
[E][ ] Team meeting (from: Sep 25 2024 to: Sep 25 2024)
Now you have 2 tasks in the list.

```
### 3. Add a To-Do Task
You can add a simple to-do task without a deadline.

Command Format: `todo <task description>`

Sample input: `todo Read research paper`

Expected Output:
```
Got it! I've added this task:
[T][ ] Read research paper
Now you have 3 tasks in the list.

```

### 4. Mark a Task as Done
You can mark a task as done by specifying the task number from the list.

Command Format: `mark <task number>`

Sample input: `mark 2`

Expected Output:
```
Nice! I've marked this task as done:
[E][X] Team meeting (from: Sep 25 2024 to: Sep 25 2024)

```

### 5. Mark a Task as Not Done
You can unmark a task (mark it as not done) by specifying the task number from the list.

Command Format: `unmark <task number>`

Sample input: `unmark 2`

Expected Output:
```
OK, I've marked this task as not done yet:
[E][ ] Team meeting (from: Sep 25 2024 to: Sep 25 2024)

```
### 6. Delete a Task
You can delete a task by specifying the task number.

Command Format: `delete <task number>`

Sample input: `delete 1`

Expected Output:
```
Noted. I've removed this task:
[D][ ] Submit report (by: Sep 20 2024)
Now you have 2 tasks left in the list.

```
### 7. List All Tasks
You can view all the tasks in your list.

Command Format: `list`

Expected Output:
```
Here are the tasks in your list:
1. [E][ ] Team meeting (from: Sep 25 2024 to: Sep 25 2024)
2. [T][ ] Read research paper

```

### 8. Find Tasks by Keyword
You can search for tasks that match a keyword.

Command Format: `find <keyword>`

Sample input: `find research`

Expected Output:
```
Here are the matching tasks in your list:
1. [T][ ] Read research paper

```
### 9. Exit the Application
You can exit the application.

Command Format: `bye`

Expected Output:
```
Bye!!! Hope to help you again soon!

```
## Error Handling
Nayana gracefully handles errors and provides informative error messages, such as when a task is added without a description or with an invalid date format.

This README.md provides a comprehensive guide on how to use the Nayana task manager. 
If you find any errors please let the real Nayana know :)
