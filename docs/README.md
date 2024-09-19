# DemureBot User Guide

![Ui.png](Ui.png)

DemureBot is a task management application designed to help you keep track of your to-dos, deadlines, and events with ease. With a simple and intuitive interface, DemureBot ensures that you stay organized and mindful of your tasks. Whether you're managing daily chores or planning significant events, DemureBot is your reliable assistant.

## Adding tasks

Add a todo to the task list.

Example: `todo return book`

```
Of course, I've added this task:
  [T][] return book
Now you have x tasks in the list.
```

Add a deadline to the task list.

Example: `deadline return book /by 2021-09-17 2359`

```
Of course, I've added this task:
  [D][] return book (by: Sep 17 2021 23:59)
Now you have x tasks in the list.
```

Add an event to the task list.

Example: `event party /from 2021-09-17 1800 /to 2021-09-17 2359`

```
Of course, I've added this task:
  [E][] party (from: Sep 17 2021 18:00 to: Sep 17 2021 23:59)
Now you have x tasks in the list.
```

## Marking/Unmarking tasks

Mark/Unmark task as done.

Example: `mark 1`

```
Nice! You have completed  
  task
Very demure, very mindful!
```

Example: `unmark 1`

```
Oh no! You haven't completed  
  task
Not very demure, not very mindful!
```

## Deleting tasks

Delete a task from the task list.

Example: `delete 1`

```
Ok dear, I've removed this task:
  task
Now you have x tasks in the list.
```

## Listing tasks

List all tasks in the task list.

Example: `list`

```
There are no tasks:DDD
```