# MiluTrock User Guide

MiluTrock is an chatbot assistant for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

![](./Ui.png)

## Features

### Viewing tasks: `list`

Lists all tasks currently stored.

Format:

```shell
list
```

Example:

> list

```
Here are the tasks in your list:
  1. [T][ ] Clean my room
  2. [D][ ] CS2103T Quiz (by: Sep 16 2024 2359)
```

### Marking tasks as done: `mark`

Marks a task as done.

Format:

```shell
mark <task number>
```

Example:

> mark 1

```
Nice! I've marked this task as done:
  [T][X] Clean my room
```

### Unmarking tasks as done: `unmark`

Marks the task as not done.

Format:

```shell
unmark <task number>
```

Example:

> unmark 1

```
OK, I've marked this task as not done yet:
  [T][ ] Clean my room
```

### Adding a ToDo: `todo`

Adds a task that needs to be done.

Format:

```shell
todo <task name>
```

Example:

> todo Clean my room

```
Got it. I've added this task:
  [T][X] Clean my room
Now you have 1 task(s) in the list.
```

### Adding a deadline: `deadline`

Adds a task that needs to be completed before a deadline.

Format:

```shell
deadline <task name> /by <yyyy-MM-dd HHmm>
```

Example:

> deadline CS2103T Quiz /by 2024-09-16 2359

```
Got it. I've added this task:
  [D][ ] CS2103T Quiz (by: Sep 16 2024 2359)
Now you have 2 task(s) in the list.
```

### Adding an event: `event`

Adds a task that has a start and end date.

Format:

```shell
event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```

Example:

> event Shopee sale /from 2024-09-15 0000 /to 2024-09-21 2359

```
Got it. I've added this task:
  [e][ ] Shopee sale (from: 2024-09-15 0000 to: 2024-09-21 2359)
Now you have 3 task(s) in the list.
```

### Deleting a task: `delete`

Deletes a task from the list

Format:

```shell
delete <task number> <task number> ...
```

Example:

> delete 1 2

```
Noted. I've removed this task:
  [T][X] Clean my room
  [D][ ] CS2103T Quiz (by: Sep 16 2024 2359)
Now you have 1 task(s) in the list.
```

### Searching for a task: `find`

Lists all tasks with a task name that matches the search query.

Format:

```shell
find <query>
```

Example:

> find CS2103T

```
Here are the matching tasks in your list:
  [D][ ] CS2103T Quiz (by: Sep 16 2024 2359)
```