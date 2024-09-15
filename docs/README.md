# Bob User Guide

![Screenshot of Bob Chatbot Ui](/Ui.png)

Welcome to **Bob**, your personal task management chatbot! 
Bob helps you keep track of tasks, mark them as done, and 
organize your day efficiently. Below is a guide to help you 
navigate Bob’s features and commands.

---

## Table of Contents

1. Getting Started
2. Features 
   - Adding Tasks 
   - Deleting Tasks
   - Marking Tasks
   - Finding Tasks 
   - Viewing All Tasks 
   - Exiting Bob

---

## Getting Started

To start using **Bob**, simply run the application in your command 
line. Once launched, Bob will greet you and you can start 
inputting commands.

---

## Features

❗ _Take note that all formatting for dates shown in this section
reflected by <date> is in <yyyy-MM-dd HHmm> format._

### Adding Tasks

Bob allows you to add different types of tasks: **To-Dos**, 
**Deadlines**, **Events**, and **Fixed** tasks.

- **To-Do**: A simple task without a specific date or time.
    ```
    Command: todo <description>
    Example: todo Read book
    ```
- **Deadline**: A task with a due date.
    ```
    Command: deadline <description> /by <date>
    Example: deadline Submit report /by 2024-09-20 1600
    ```
- **Event**: A task with a specific start and end time.
    ```
    Command: event <description> /from <date> /to <date>
    Example: event Team meeting /from 2024-09-19 2100 /to 2024-09-19 2300
    ```
- **Fixed**: A task with a fixed time taken for it to be completed.
    ```
    Command: fixed <description> /hours <duration in hours>
    Example: fixed Watch CS2103 briefing /hours 1
    ```

### Deleting Tasks

You can remove tasks you no longer need in your list.

```
Command: delete <task number>
Example: delete 3
```

### Marking Tasks

Once you complete a task, mark it as done so Bob can 
update your list.

```
Command: mark <task number>
Example: mark 2
```

You can also unmark a task.

```
Command: unmark <task number>
Example: unmark 2
```

### Finding Tasks

If your task list is long, you can search for tasks 
by keyword.

```
Command: find <keyword>
Example: find book
```

You can also search for tasks in relation to a specific date.

```
Command: on <date, leave out time>
Example: on 2024-09-20
```

### Viewing All Tasks

To see all the tasks you've added, just type the following:

`list`

### Exiting Bob

When you're done using Bob, exit the program with the 
following command:

`bye`

Bob will save your tasks and bid you farewell.



