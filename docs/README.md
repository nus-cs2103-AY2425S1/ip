# Velma User Guide

![Screenshot of Velma chatbot](Ui.png)

Velma is a simple-to-use chatbot that helps you manage yours tasks easily and efficiently. Each tasks are categorised into 
four types: `todo`, `deadline`, `event` and `after`. Velma automatically saves the task list to your local storage,
so don't be afraid of losing your tasks!


## Adding Todos
A to-do task is just a simple task with description. To add a todo:

Example: `todo read book`

Velma will process this command and add your todo into the task list. 

```
Got it. I've added this task:
[T][] read book
```

## Adding Events
An event is a task with a description, a starting time and an ending time. To add an event, follow the following format:

event {task description} /from {hhmm} /to {hhmm}

Example: `event project meeting /from 1400 /to 1600`'

Velma will process this command and add your event into the task list. 

```
Got it. I've added this task:
[E][] project meeting (from: 1400 to: 1600)
```

## Adding Deadlines
A deadline is a task with a description, and a deadline. To add an event, follow the following format:

event {task description} /by {dd/MM/yyyy hhmm}

Example: `deadline CS2103 Ip /by 20/9/2024 2359`

Velma will process this command and add your deadline task into the task list.

```
Got it. I've added this task:
[D][] CS2103 Ip (by: Sep 29 2024 2359)
```

## Adding After
An after task is specified to start after something. To add an after task:

Example: Submit peer review after class

Velma will process this command and add your after task into the task list.

```
Got it. I've added this task:
[A][] Submit peer review(after: class)
```

## List Feature
You can list all your tasks by typing `list` (case-sensitive). Velma will display all your tasks in the task list.

Example: 'list'

```
Here are the tasks in your list:
1. [T][] read book
2. [E][] project meeting (from: 1400 to: 1600)
3. [D][] CS2103 Ip (by: Sep 29 2024 2359)
```

## Done/Undone Feature
Once you finish your task, you can mark it as done by typing `mark` followed by the task number in the list.
Likewise, you can unmark the task by typing `unmark` followed by the task number in the list.

Example: 'mark 2'

```
Nice! I have marked this task as done:
2. [E][X] project meeting (from: 1400 to: 1600)
```

Example: 'unmark 2'

```
Nice! I have marked this task as undone:
2. [E][] project meeting (from: 1400 to: 1600)    
```

## Delete Feature
You can delete a task by typing `delete` followed by the task number in the list.

Example: 'delete 2'

```
Noted! I have removed this task.
```