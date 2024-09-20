# Snipe User Guide

Welcome to the Snipe User Guide! This guide will help you understand how to use Snipe, a task management chatbot that helps you organize tasks, set deadlines, and manage your to-do list efficiently.

## Product Screenshot

![Snipe GUI](Ui.png)

*Figure: The main interface of the Snipe chatbot.*

## Introduction

Snipe is a chatbot designed to assist users in managing tasks, setting deadlines, and keeping their workspace organized. Whether you need to add a new task, mark tasks as complete, or archive them for future reference, Snipe provides a user-friendly way to handle all your task management needs.

---

## Adding Todos

You can add todo tasks to help you keep track of undone tasks.

**Action**: Add a todo by specifying the task description.

**Example Usage**:
```plaintext
todo Buy groceries
```
Expected Output
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 3 tasks in the list.
```

## Adding Deadlines

You can add deadlines to tasks to ensure you keep track of important dates.

**Action**: Add a deadline to a task by specifying the task description and the deadline date.

**Example Usage**:
```plaintext
deadline Submit assignment /by 2024-09-30 2359
```
Expected Output
```
Got it. I've added this task:
  [D][ ] Submit assignment (by: Sep 30 2024, 11.59pm)
Now you have 4 tasks in the list.
```

## Adding Events

Add events to your task list with specific dates and times to keep track of important meetings, appointments, or any scheduled activities.

**Action**: Add an event by specifying the event description and the event start and end date and time.

**Example Usage**:
```plaintext
event team meeting /from 2024-08-23 1400 /to 2024-08-23 1600
```
Expected Output
```
Got it. I've added this task:
  [E][ ] team meeting (from: 2024-08-23 2.00pm to: 2024-08-23 4.00pm)
Now you have 5 tasks in the list.
```

## Marking Tasks as Done

Mark tasks as done when you have completed them to keep your list updated.

**Action**: Mark a task as done by specifying its task number.

**Example Usage**:
```plaintext
mark 4
```
Expected Output
```
Nice! I've marked this task as done:
  [D][X] Submit assignment (by: Sep 30 2024, 11.59pm)
```

## Unmarking Tasks as Not Done

Unmark tasks as not done when you have wrongly marked them as done.

**Action**: Unmark a task as not done by specifying its task number.

**Example Usage**:
```plaintext
unmark 4
```
Expected Output
```
OK, I've marked this task as not done yet:
  [D][X] Submit assignment (by: Sep 30 2024, 11.59pm)
```

## Deleting Tasks

Delete tasks that you no longer need on your list.

**Action**: Remove a task from the list by specifying its task number.

**Example Usage**:
```plaintext
delete 3
```
Expected Output
```
Noted. I've removed this task:
  [T][ ] Buy groceries
Now you have 4 tasks in the list.
```

## Viewing Your Current Task List

See all tasks currently on your list.

**Action**: Display the current list of tasks.

**Example Usage**:
```plaintext
list
```
Expected Output
```
Here are the tasks in your list:
1. [T][ ] Complete book report
2. [T][ ] Read book
3. [T][ ] [D][X] Submit assignment (by: Sep 30 2024, 11.59pm)
4. [E][ ] team meeting (from: 2024-08-23 2.00pm to: 2024-08-23 4.00pm)
```

## Finding Tasks by Keyword

Search for tasks that contain a specific keyword.

**Action**: Find tasks that match the given keyword in the list.

**Example Usage**:
```plaintext
find book
```
Expected Output
```
Here are the matching tasks in your list:
1. [T][ ] Complete book report
2. [T][ ] Read book
```

## Archiving a Task

Archive a task from your current list by specifying its task number.

**Action**: Archive the task at the specified number in your list.

**Example Usage**:
```plaintext
archive 2
```
Expected Output
```
Noted. I've archived this task:
  [T][ ] Read book
```

## Viewing Archived Tasks

View all tasks that have been archived.

**Action**: Display the current list of archived tasks.

**Example Usage**:
```plaintext
archives
```
Expected Output
```
Here are the tasks in your archived list:
1. [T][ ] Read book
```

## Help Command

View a list of useful commands to manage your tasks.

**Action**: Display a list of available commands and their descriptions.

**Example Usage**:
```plaintext
help
```
Expected Output
```
Hello! Here are your list of helpful commands:

'list' - shows you your current list of tasks
'mark n' - marks the task at number n on your list as done
'unmark n' - unmarks the task at number n on your list as not done
'todo task_name' - adds a todo task with task_name to your list
'deadline task_name /by due_date' - adds a deadline task with task_name to your list with a deadline of due_date
'event task_name /from start_date /to end_date' - adds an event task with task_name with a start_date to end_date
'delete n' - deletes the task at number n on your list
'find _keyword_' - searches for all items in your list that has the _keyword_ inside
'archive n' - archives the task at number n on your list
'archives' = shows you your current archived list

'help' - shows the list of useful commands
```
## Exiting the program

Exits the program.

**Example Usage**:
```plaintext
bye
```

## Known Issues:
None reported at this time. Please report any bugs or issues under the Issues section of the repository.