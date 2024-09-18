# Rasputin User Guide


![Example of Rasputin in use](Ui.png)
Rasputin is the only reminder app you'll need from now on. 
Rasputin helps you remember all the tasks you need to do, including the deadlines and time. 
It's intuitive, easy to learn, ~~fast~~ SUPER fast to use and best of all, it's FREE!

## Features

### Legend

<...> indicates a parameter to be provided by the user.

[...] indicates an optional parameter to be provided by the user.

### Adding Todos

Adds a task that does not need to be done by any date or time.

Expected usage: `todo <task description>`

Example: `todo read book`

Expected output:
```
Added Todo task:
[T][ ] read book
You currently have 1 task/s in your list.
```

### Adding Deadlines

Adds a task that has a deadline to be done by. 

Deadlines must have a date in the format YYYY-MM-DD. 
The time of the deadline is optional and must be in the 24-hour format HHmm.

Expected usage: `deadline <task description> /by <date> [time]`

Example: `deadline return book /by 2024-09-30` or `deadline submit quiz /by 2024-09-28 2200`

Expected output: 
```
Added Deadline task:
[D][ ] return book (by: 30 Sep 2024)
You currently have 2 task/s in your list.

Added Deadline task:
[D][ ] submit quiz (by: 28 Sep 2024 2200)
You currently have 3 task/s in your list.
```

### Adding Events
Adds an event that has a start time and end time.

Events must have both a date and time for the event start and event end in the format YYYY-MM-DD HHmm.

Expected usage: `event <task description> /from <event start> /to <event end>`

Example: `event math lecture /from 2024-09-19 1400 /to 2024-09-19 1600`

Expected output:
```
Added Event task:
[E][ ] math lecture (from: 19 Sep 2024 1400 to: 19 Sep 2024 1600)
You currently have 4 task/s in your list.
```

### Marking Tasks as done
Marks the task at the given index of the list as done.
The specified task must not be already been marked as done.

Expected usage: `mark <task index>`

Example: `mark 1`

Expected output:
```
Good job! Marked that as done for you.
[T][X] read book
```

### Marking Tasks as not done
Marks the task at the given index of the list as not done.
The specified task must not be already been marked as not done.

Expected usage: `unmark <task index>`

Example: `unmark 1`

Expected output:
```
Task has been unmarked.
[T][ ] read book
```

### Undoing the most recent command

Undoes the most recent command that can be undone.
Can only be applied to todo, deadline, event, delete, mark and unmark commands.
One of these commands must have been executed to be able to use the undo command.

Expected usage: `undo`

Expected output:
```
Most recent command has been undone!
```

### Find task

Finds tasks in the list that contains the search keyword.

Expected usage: `find <keyword>`

Example: `find book`

Expected output:
```
Here are the matching tasks in your list.
1.[T][ ] read book
2.[D][ ] return book (by: 30 Sep 2024)
```

### View all tasks

View all the current tasks in your list.

Expected usage: `list`

Expected output:
```
1.[T][ ] read book
2.[D][ ] return book (by: 30 Sep 2024)
3.[D][ ] submit quiz (by: 28 Sep 2024 2200)
4.[E][ ] math lecture (from: 19 Sep 2024 1400 to: 19 Sep 2024 1600)
```


### Exit

To exit the application, simply enter `bye` and Rasputin will exit, saving all changes to your list of tasks.
