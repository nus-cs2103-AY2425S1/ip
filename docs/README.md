# Oliver User Guide

![Oliver demo](./Ui.png)

Meet Oliver, your personal chatbot assistant designed to keep you organized and on top of your tasks. 
Whether it's simple to-dos, important deadlines, or scheduled events, Oliver helps you manage it all with ease. 
You can quickly add tasks, set reminders for upcoming deadlines, and even mark items as done or undone as your 
day progresses. Need to check what's on your plate? Oliver can list all your tasks or help you find specific ones 
in a snap. With Oliver, staying organized has never been easier—your smart, efficient task manager is just a chat away! 

## Adding todos

Adds a todo task to the list.

Example: `todo finish my homework`

```
Got it. I've added this task:
[T][] finish my homework
Now you have 1 task to keep you busy.
```

## Adding deadlines

Adds a task with a deadline to the list.

Example: `deadline submit assignment /by 2024-09-30`

```
Got it. I've added this task:
[D][] submit assignment (by: Sep 30 2024, 12.00 am)
Now you have 1 task to keep you busy.
```

## Adding events

Adds an event with a start time and end time to the list.

Example: `event party /from 2024-09-25 1800 /to 2024-09-25 2100`

```
Got it. I've added this task:
[E][] party (from: Sep 20 2024, 6.00 pm to: Sep 20 2024, 9.00 pm)
Now you have 1 task to keep you busy.
```

## Listing tasks

Shows a list of all tasks in the task list.

Format: `list`

## Marking as done

Marks a task as completed.

Example: `mark 1`

```
Pawsome work! I've marked this task as done:
[T][X] finish my homework
```

## Marking as undone

Marks a task as incomplete.

Example: `unmark 1`

```
Alright, I've marked this task as still pending:
[T][] finish my homework
```

## Deleting a task

Deletes the specified task from the task list.

Example: `delete 1`

```
All done. I've removed this task:
[T][] finish my homework
You are down to 2 tasks now.
```

## Finding tasks

Finds tasks that contain a specific keyword.

Example: `find homework`

```
Here are the matching tasks in your list I managed to dig up:
1. [T][] finish my homework
```

## Exiting the program

Exits the program.

Format: `bye`

