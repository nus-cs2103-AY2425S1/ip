# Luna User Guide

![Product screenshot](./Ui.png)

**Welcome to Luna!**

I'm here to help you keep track of your tasks effortlessly. 
Whether it's your daily to-do task, deadlines, or events, 
I can help you organize, store, and manage everything in one place. 
Just let me know what tasks you'd like me to save,
and easily show all your tasks so you won't forget to complete any tasks!

## Features
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `deadline TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `deadline return book`.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `undo`) will be ignored.<br> 
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.
* Commands are case-insensitive

</div>

### Adding Todo Task: `todo`
Add a to-do task with a description.

Format: `todo TASK_DESCRIPTION`

Example: `todo return book`

Response indicating the successful addition of the task 
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [T][ ] return book
Now you have 1 tasks in the list.
```

### Adding Deadline Task: `deadline`
Add a to-do task with a deadline.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`
* Add `DEADLINE` with date format `dd/mm/yyyy hh:mm`

Example: `deadline submit assignment 1 /by 16/09/2024 13:00`

Response indicating the successful addition of the task
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [D][ ] submit assignment 1 (Deadline: 1:00 pm, Mon 16 Sep 2024)
Now you have 1 tasks in the list.
```

### Adding Event Task: `event`
Add a to-do task with a description.

Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME`
* Add `START_TIME` and `END_TIME` with date format `dd/mm/yyyy hh:mm`

Example: `event attend workshop /from 20/09/2024 12:00 /to 20/09/2024 15:00`

Add Start time after `/from` and End time after `/to` with date format `dd/mm/yyyy hh:mm`

Response indicating the successful addition of the task
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
Now you have 1 tasks in the list.
```

### Mark a task: `mark` 
Marks specified task as completed.

Format: `mark INDEX`
* Marks the task at the specified `INDEX`.
* Index refers to the number shown in the task list
* Index must be a positive integer 1, 2, 3...

Example: `mark 1`

Marks the indicated task with a "X" to show completion of the task.
```
Nice! I've marked this task as done: 
  [T][X] return book
```

### Unmark a task: `unmark`
Marks specified task as not completed.

Format: `unmark INDEX`
* Marks the task at the specified `INDEX`.
* Index refers to the number shown in the task list
* Index must be a positive integer 1, 2, 3...

Example: `unmark 1`

Unmarks the indicated task if the task was previously marked with "X".
```
OK, I've marked this task as not done yet: 
  [T][ ] return book
```

### Search for a task: `find`
Finds tasks whose description contain the search description.

Format: `find QUERY`
* Search for all tasks whose description match the specified `QUERY`.
* Search is case-insensitive.
* Only the exact description will be matched.

Example: `find book`

All tasks whose description match the query will be shown as follows.
```
Here are the tasks with the matching description:
  [T][ ] Book a seat in the library 
  [D][X] return book (Deadline: 1:00 pm, Mon 16 Sep 2024)
```

### Delete task: `delete`
Deletes specified task.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* Index refers to the number shown in the task list
* Index must be a positive integer 1, 2, 3...

Example: `delete 2`

The task will be removed and the new total number of tasks saved will be shown as follows.
```
Got it. I've removed this task:
  [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
Now you have 3 tasks in the list.
```

### Show all tasks: `list`
Shows a list of all tasks.

Format: `list`
```
Here are the tasks in your list:
1. [T][ ] Book a seat in the library 
2. [D][X] return book (Deadline: 1:00 pm, Mon 16 Sep 2024)
3. [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
```

### Undo command: `undo`
Undoes the most recent command.

Format: `undo`

Example: undo most recent `mark` command
```
>>> undo 'mark' command
OK, I've marked this task as not done yet: 
  [T][ ] return book
```

### Saving the data
Data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command summary

Action | Format, Examples
--------|------------------
**todo** | `todo TASK_DESCRIPTION` <br> e.g. `todo return book`
**deadline** | `deadline TASK_DESCRIPTION /by DEADLINE` <br> e.g. `deadline submit assignment 1 /by 16/09/2024 13:00`
**event** | `event TASK_DESCRIPTION /from START_TIME /to END_TIME` <br> e.g. `event attend workshop /from 20/09/2024 12:00 /to 20/09/2024 15:00`
**mark** | `mark INDEX` <br> e.g. `mark 1`
**unmark** | `unmark INDEX` <br> e.g. `unmark 1`
**delete** | `delete INDEX`<br> e.g. `delete 2`
**find** | `find QUERY` <br> e.g. `find book`
**list** | `list`
**undo** | `undo`