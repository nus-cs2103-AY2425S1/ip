# Nugget Chatbot User Guide

![Ui.png](..%2F..%2F..%2F..%2FDocuments%2FUi.png)

## Introduction
Nugget is a task tracker chatbot designed to help users organize and plan their day effectively. It supports three types of tasks:
- **Todo**: Tasks without a deadline.
- **Deadline**: Tasks with a specific due date.
- **Event**: Tasks that span a duration.

## Basic Functions
The chatbot provides the following functionalities:
1. **Add Tasks**: Create todo, deadline, or event tasks.
2. **Delete Tasks**: Remove existing tasks.
3. **Find Tasks**: Search for tasks by name (case-insensitive).
4. **Mark/Unmark Tasks**: Change the status of tasks.
5. **View Tasks**: Display the full list of tasks.
6. **Sort Tasks**: Organize tasks based on their end date and time.

## Listing all tasks: `list`
Shows a list of all tasks in the task list.
Format: `list`

## Adding Todos
To add a todo task to the list of tasks, use the following format:
todo {description}
Example: `todo Buy dinner`
Outcome:
```
Got it. I've added this task:
[T][] Buy dinner
Now you have 1 task in the list.
```
## Adding Deadlines
To add a deadline task to the list of tasks, use the following format:
deadline {description} /by yyyy-MM-dd HHmm
Example: `deadline Complete assignment 1 /by 2024-09-24 1900`

// A description of the expected outcome goes here

Outcome:
```
Got it. I've added this task:
[D][] Complete assignment 1 (by: Sep 24 2024, 07:00pm)
Now you have 1 task in the list.
```

## Adding Events
To add an event task to the list of tasks, use the following format:
event {description} /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
Example: `event Client meeting /from 2024-09-24 0900 /to 2024-09-24 1100`

// A description of the expected outcome goes here

Outcome:
```
Got it. I've added this task:
[E][] Client meeting (from: Sep 24 2024, 07:00am to: Sep 24 2024, 11:00am)
Now you have 1 task in the list.
```
## Deleting Tasks: `delete`
To delete a task, use the following format:
delete {task number}, where the task number can be
referenced from the list command.

Example: `delete 1`

Outcome:
```
Noted. I've removed this task:
[D][] Complete assignment 1 (by: Sep 24 2024, 07:00pm)
Now you have 2 tasks in the list.
```

## Marking Tasks: `mark`
To mark a task, use the following format:
mark {task number}, where the task number can be
referenced from the list command.

Example: `mark 1`

Outcome:
```
Nice! I've marked this task as done:
[D][X] Complete assignment 1 (by: Sep 24 2024, 07:00pm)
```

## Unmarking Tasks: `unmark`
To mark a task, use the following format:
mark {task number}, where the task number can be
referenced from the list command.

Example: `unmark 1`

Outcome:
```
OK, I've marked this task as not done yet:
[D][] Complete assignment 1 (by: Sep 24 2024, 07:00pm)
```

## Find Tasks: `find`
To filter a task that contains with a certain characters,
use the following format: find {keyword}

Example 'find meeting'

Outcome:
```
Here are the matching tasks in your list
1. [E][] Client meeting (from: Sep 24 2024, 07:00am to: Sep 24 2024, 11:00am)
2. [E][] Team meeting (from: Sep 24 2024, 12:00pm to: Sep 24 2024, 02:00pm)
```

## Sorting Tasks: `sort`
To sort the tasks based on the end dates. More recent dates would appear at the top.
For todo tasks, they are pushed to the end of the list as it does not contain
any dates. Use the following format: `sort`

Outcome:
```
You have the following tasks sorted by dates:
1.[E][] Client meeting (from: Sep 24 2024, 09:00 am to:
Sep 24 2024, 11:00 am)
2.[E][] Team meeting (from: Sep 24 2024, 12:00 pm to:
Sep 24 2024, 02:00 pm)
3.[T][] sleep
```