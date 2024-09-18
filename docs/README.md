# EchoBot User Guide

_EchoBot helps you stay organized by managing your tasks effortlessly._

**Features**
1. Text-based commands for simplicity
2. Easy to learn with minimal setup
3. Lightning-fast task management

## Getting Started: 

1. Download the latest release [here](https://github.com/JJtan2002/ip/releases)
2. Run ```java -jar echobot.jar```
3. Let EchoBot manage your tasks efficiently

## Help
To view all commands

Command: ```help```

## Adding ToDos
For tasks without any deadline

Command: ```todo {description}```

Sample input: ```todo graduate```

Expected output:
```
Added:[T][] graduate
Now you have 2 tasks in list.
```

## Adding Deadlines

For tasks with a specific deadline

Command: ```deadline {description} /by {d/mm/yyyy}```

Sample input: ```deadline cs2103t ip /by 20/9/2024```

Expected output:
```
Added:[D][] cs2103t (by: Sep 20 2024)
Now you have 2 tasks in list.
```

## Adding Events

For tasks with a specific duration 

Command: ```event {description} /from {dd/mm/yyyy} /to {dd/mm/yyyy}```

Sample input: ``` event university /from 1/1/2023 /to 1/1/2027```

Expected output:
```
Added: [E][ ] university (from: Jan 01 2023 to: Jan 01 2027)
Now you have 3 tasks in the list.
```
## List Tasks

List all tasks

Command/sample input: ```list```

Expected output:
```
Here are the tasks in your list:
1. [T][] graduate
2. [D][] cs2103t (by: Sep 20 2024)
```

## Delete

Remove a task by its number

Command: ```delete {task number}```

Sample input: ```delete 1```

Expected output:
```
I've removed this task:
1. [T][] graduate
Now you have 1 tasks in the list.
```

## Find

Search for tasks by their description

Command: ```find {description}```

Sample input: ```find grad```

Expected output:
```
Here are the matching tasks in your list:
1. [T][] graduate
```

## Mark

Mark a task as completed

Command: ```mark {task number}```

Sample input: ```mark 1```

Expected output:
```
Nice! I've marked this task as done:
[T][X] graduate
```

## Unmark

Unmark a task

Command: ```unmark {task number}```

Sample input: ```unmark 1```

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] graduate
```