# Hana User Guide

![Ui.png](Ui.png)

## Introduction
HanaBot is a chatbot that helps you organize your tasks efficiently. It's:
- Simple and intuitive to use
- Lightweight and fast SUPER FAST
- Command-line based for simplicity

## Print all commands
The `help` command prints all available commands.

### Usage:
```
//Format
help
```

### Expected Output:
```
Here are some examples of what you can do:
1. List all tasks: list
2. Mark a task as done: mark [task number]
3. Unmark a task: unmark [task number]
4. Add a todo: todo [description]
5. Add a deadline: deadline [description] /by [d/M/yyyy HHmm]
6. Add an event: event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]
7. Delete a task: delete [task number]
8. Find By Date: findByDate [d/M/yyyy]
9. Find By Keyword: findByKey [keyword]
10. Set Priority: priority [task number] [priority level]
11. List by Priority: listPriority
```

## Adding Tasks
The `todo` command allows you to add a basic task to your list. This feature is ideal for simple tasks without a 
specific deadline.

### Usage:

```
// Format
todo <task description>

// Example
todo Buy groceries
```

### Expected Output:
```
Got it! I've added this task:
    [T][3][ ] Buy groceries
You now have 1 tasks in the list.
```

## Adding Deadlines

To add a task with a specific deadline, use the `deadline` command. This feature helps you manage time-sensitive tasks 
by keeping track of due dates.

### Usage:

```
// Format
deadline <task description> /by <due date>

// Example
deadline Submit assignment /by 14/9/2024 2359
```

### Expected Output:
```
Got it! I've added this task:
    [D][3][ ] Submit assignment (by: 14/9/2024 2359)
You now have 2 tasks in the list.
```

## Adding Events

The `event` command allows you to add an event with a specified start and end time. 
This is useful for scheduling meetings, appointments, or other time-bound tasks.

### Usage:

```
// Format
event <event description> /from <start time> /to <end time>

// Example
event Project meeting /from 14/9/2024 1400 /to 14/9/2024 1800
```

### Expected Output:
```
Got it! I've added this task:
    [D][3][ ] Project meeting (from: 14/9/2024 1400 to: 14/9/2024 1800)
You now have 3 tasks in the list.
```

## Listing All Tasks

The `list` command displays all the tasks in your task list, whether they are to-dos, deadlines, or events. 
It helps you review what tasks are pending.

### Usage:

```
// Format
list
```

### Expected Output:
```
1. [T][3][ ] borrow book
2. [D][3][ ] return book (by: Dec 02 2019, 6:00 pm)
3. [E][3][ ] project meeting (from: Dec 02 2019, 2:00 pm to: Dec 02 2019, 2:00 pm)
```

## Marking a Task as Done

To mark a task as completed, use the `mark` command followed by the task number.
This will update the task to reflect its completion.

### Usage:

```
// Format
mark <task number>

// Example
mark 2
```

### Expected Output:
```
Nice! I've marked this task as done:
  [D][3][X] Buy groceries
```

## Unmarking a Task as Done

To unmark a task as completed, use the `mark` command followed by the task number.
This will update the task to reflect its completion.

### Usage:

```
// Format
Unmark <task number>

// Example
mark 2
```

### Expected Output:
```
OK, I've marked this task as not done yet:
  [D][3][ ] Buy groceries
```

## Deleting a Task

If you need to remove a task from your list, use the `delete` command followed by the task number. 
This feature helps you keep your task list clean and relevant.

### Usage:

```
// Format
delete <task number>

// Example
delete 1
```

### Expected Output:
```
Noted. I've removed this task:
    [T][3][X] Buy groceries
You now have 2 tasks in the list.
```

## Finding Tasks by Keyword

The `findByKey` command allows you to search for tasks that contain a specific keyword. 
This feature helps you quickly locate tasks when your list grows long.

### Usage:

```
// Format
findByKey <keyword>

// Example
findByKey assignment
```

### Expected Output:
```
Here are the matching tasks in your list:
[D][3][ ] Submit assignment (by: Sep 30, 2024)
```

## Finding Tasks by Date

The `findByDate` command allows you to search for tasks that is on a certain date.
This feature helps you quickly locate tasks when your list grows long.

### Usage:

```
// Format
Find By Date: findByDate [d/M/yyyy]

// Example
findByDate 30/9/2024
```

### Expected Output:
```
Here are the matching tasks in your list:
[D][3][ ] Submit assignment (by: Sep 30, 2024)
```

## Prioritizing Tasks

Hana allows you to prioritize tasks by marking important tasks with a high-priority flag. 
You can prioritize tasks when adding them using the `priority` keyword.

### Usage:

```
// Format
priority <task number>

// Example
priority 1 1
```

### Expected Output:
```
OK, I've set thie task to priority 1:
    [D][1][ ] Submit assignment (by: Sep 30, 2024)
```

## List by Priority

The `listPriority` command displays all the tasks in your task list, sorted by priority.

### Usage:

```
// Format
listPriority
```

### Expected Output:
```
1. [D][1][ ] Submit assignment (by: Sep 30, 2024)
2. [E][3][ ] project meeting (from: Dec 02 2019, 2:00 pm to: Dec 02 2019, 2:00 pm)
```

## Summary of Commands
- `todo <task description>` – Add a basic task.
- `deadline <task description>` /by <due date> – Add a task with a deadline.
- `event <event description> /from <start date> /to <end date>` – Add an event with start and end times.
- `list` – View all tasks.
- `mark <task number>` – Mark a task as done.
- `unmark <task number>` - Unmark a task as done.
- `delete <task number> `– Delete a task.
- `findByKey <keyword>` – Find tasks containing a specific keyword.
- `priority <task description>` – Add a high-priority task.
- `listPriority` - List by priority

Hana is your efficient task manager to help you stay on top of your tasks. 
Enjoy seamless task management with just a few commands!
