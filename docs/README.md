# MichaelBot

![Ui.png](Ui.png)

MichaelBot helps you stay on top of all your tasks! 

## Adding deadlines

Using the "deadline" command, a task with a valid deadline is added and saved to the list.

To add this task type, follow the format: "deadline [task name] /by [YYYY-MM-DD]".

Example: `deadline return book /by 2024-09-30`

MichaelBot returns your task as confirmation as follows:

```
Got it. I've added this task: 
[D][] return book (by: 30 Sep 2024)
Now you have X tasks in the list.
```

## Marking/Unmarking Tasks

Using the "mark/unmark" command, an existing task can be marked as done or unmarked.

To use these commands, follow the format: "mark X/ unmark X", where X is the position of the task on the list.

Example: `mark 1 / unmark 3`

MichaelBot shows your task as marked as follows:

```
Nice! I've marked this task as done: 
[D][X] return book (by: 30 Sep 2024) 
```

## Deleting tasks

Using the "delete" command, an existing task can be removed from the list.

To delete a task, follow the format: "delete X", where X is the position of the task on the list.

Example: `delete 1`

MichaelBot shows your deleted task as follows:

```
Noted. I've removed this task: 
[D][X] return book (by: 30 Sep 2024)
Now you have X tasks in the list.
```
## Other Commands

A simple task can be added as `todo [task]`

An event can be added as `event [task] /from [start] /to [end]`

All added tasks can be viewed using `list`

A specific task can be searched for using `find [keyword]`

The tasks can be sorted in alphabetical order using `sort`

The program can be exited using `bye`