# VoidCat User Guide

![VoidCat Ui](Ui.png)

## Introduction
VoidCat is a chat bot designed to help you manage tasks efficiently. With VoidCat, you can add, delete, mark tasks, and more. This guide will walk you through using VoidCat's key features.

## Quick start
1. Ensure Java `17` is installed on your machine.
2. Download the latest version of `VoidCat.jar` from [here](https://github.com/charlenetcy/ip/releases/download/A-Release/void.jar).
3. Open a terminal in the folder containing `VoidCat.jar`.
4. Run the command `java -jar VoidCat.jar`.
5. The VoidCat window should appear, and you're ready to begin managing your tasks!

## Features

## 1. Add a Task
You can add various types of tasks (ToDo, Deadline, Event).

### ToDo:
Task without a deadline

Format: `todo <task_description>`

Example: `todo read book`

Adds a ToDo task.

```
/ᐠ > ˕ <マ Got it. I've added this task:

[T][ ] read book

Now you have 1 tasks in the list
```

### Deadline:
Task with a specific due date and time

Format: `deadline <task_description> /by <yyyy-mm-dd hhmm>`

Example: `deadline finish project /by 2024-12-31 2359`

Adds a Deadline task.
```
/ᐠ > ˕ <マ Got it. I've added this task:

[D][ ] finish project (by: Dec 31 2024, 11:59pm) 

Now you have 2 tasks in the list
```

### Event:
Task with a start and end time

Format: `event <task_description> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm>`

Example: `event conference /from 2024-10-10 1000 /to 2024-10-10 1200`

Adds an Event task.
```
/ᐠ > ˕ <マ Got it. I've added this task:

[E][ ] event conference (from: Oct 10 2024, 10:00am to: Oct 10 2024, 12pm) 

Now you have 3 tasks in the list
```
## 2. Mark and unmark tasks as done
### Mark
Mark a task as done using the `mark` command.
Format: `mark <task_number>`

Example: `mark 3`
```
ฅ^._.^ฅ OK, I've marked this task as not done yet:

[E][X] event conference (from: Oct 10 2024, 10:00am to: Oct 10 2024, 12pm) 
```
### Unmark

Undo marking a task as done using the `unmark` command.
Format: `unmark <task_number>`

Example: `unmark 3`
```
/ᐠ > ˕ <マ ₊˚⊹♡ Good job! I've marked this task as done:

[E][ ] event conference (from: Oct 10 2024, 10:00am to: Oct 10 2024, 12pm) 
```


## 3. Delete a task
Format: `delete <task_number>`

Example: `delete 3`
```
ฅ^._.^ฅ Noted. I've removed this task:

[E][ ] event conference (from: Oct 10 2024, 10:00am to: Oct 10 2024, 12pm) 
```

## 4. List tasks
You can view all tasks using the `list` command.

Example: `list`
```
Here are the tasks from your list ^•ﻌ•^ฅ♡ :

1. [T][ ] read book
2. [D][ ] finish project (by: Dec 31 2024, 11:59pm) 
```

## 5. Find tasks
You can find a task by its description using the `find` command.

Format: `find <keyword>`

Example: `find book`
```
Here are the tasks from your list ^•ﻌ•^ฅ♡ :

1. [T][ ] read book
```

## 6. Getting Help
Contains all the commands and formats of VoidCat

Example: `help`
```
Here are the available commands ^•ﻌ•^ฅ♡ :

1. list - Shows all tasks
2. todo <description> - Adds a ToDo task
3. deadline <description> /by <yyyy-mm-dd hhmm> - Adds a Deadline task
4. event <description> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> - Adds an Event task
5. mark <task_number> - Marks a task as done
6. unmark <task_number> - Unmarks a task
7. delete <task_number> - Deletes a task
8. find <keyword> - Finds tasks with the keyword
9. help - Displays this help page
10. bye - Exits the application
```

## 7. Exit the app
You can exit the app using the `bye` command.

Example: `bye`

A random goodbye message will be displayed.
```
/ᐠ˵- ⩊ -˵マ Purr... Until next time, friend.
```