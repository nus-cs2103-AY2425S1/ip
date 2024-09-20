# Bob User Guide

![Screenshot of application](Ui.png)

Bob is a task tracker. 

It can manage different kinds of task, such as todos, 
deadlines or events.

Built with Java and Gradle.

## Adding Tasks

There are 3 types of tasks: Todos, Deadlines, Events

### Todos

For tasks that have no start or end dates.

Format: `todo DESCRIPTION`

Examples: 
- `todo borrow book`

Expected output:
```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the list.
```

### Deadlines

For tasks that have end dates.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Examples:
- `deadline return book /by 2019-12-02`

Expected output:
```
Got it. I've added this task:
[D][ ] return book (by: Dec 2 2019)
Now you have 1 task in the list.
```

### Events

For tasks that have start and end dates.

Format: `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Examples:
- `event project meeting /from 2019-12-02 /to 2019-12-08`

Expected output:
```
Got it. I've added this task:
[E][ ] project meeting (from: Dec 2 2019 to: to Dec 8 2019)
Now you have 1 task in the list.
```

## Other Features

### Listing all tasks: `list`

Shows a list of all the tasks that is being tracked.

Format: `list`

Expected output:
```
1. [T][ ] borrow book
2. [D][ ] return book (by: Dec 2 2019)
3. [E][ ] project meeting (from: Dec 2 2019 to: to Dec 8 2019)
```

###  Marking a task as done: `mark`
Marks a task as done by specifying its index.

Format: `mark TASK_INDEX`

Examples:
- `mark 1`

Expected output:
```
Nice! I've marked this task as done:
[T][X] borrow book
```

### Unmarking a task as done: `unmark`

Unmarks a task, indicating that it is not completed.

Format: `unmark TASK_INDEX`

Examples:
- `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] borrow book
```

### Deleting a task: `delete`

Deletes a task by specifying its index.

Format: `delete TASK_INDEX`

Examples:
- `delete 1`

Expected output:
```
Noted. I've removed this task:
[T][ ] borrow book
Now you have 0 tasks in the list.
```

### Finding tasks by name: `find`

Finds tasks that match a given keyword.

Format: `find KEYWORD`

Examples:
- `find book`

Expected output:
```
1. [T][ ] borrow book
2. [D][ ] return book (by: Dec 2 2019)
```

### Exiting the Program: `bye`

Saves all tasks to a file and exits the program.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```