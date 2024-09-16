# Luna User Guide

![Product screenshot](/assets/images/Ui.png)

**Welcome to Luna!**

I'm here to help you keep track of your tasks effortlessly. 
Whether it's your daily to-do task, deadlines, or events, 
I can help you organize, store, and manage everything in one place. 
Just let me know what tasks you'd like me to save,
and easily show all your tasks so you won't forget to complete any tasks!

## Adding Todo Task
Add a to-do task with a description.

Example: `todo return book`

Response indicating the successful addition of the task 
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [T][ ] return book
Now you have 1 tasks in the list.
```

## Adding Deadline Task
Add a to-do task with a deadline.

Example: `deadline submit assignment 1 /by 16/09/2024 13:00`

Add Deadline after `/by` with date format `dd/mm/yyyy hh:mm`


Response indicating the successful addition of the task
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [D][ ] submit assignment 1 (Deadline: 1:00 pm, Mon 16 Sep 2024)
Now you have 1 tasks in the list.
```

## Adding Event Task
Add a to-do task with a description

Example: `event attend workshop /from 20/09/2024 12:00 /to 20/09/2024 15:00`

Add Start time after `/from` and End time after `/to` with date format `dd/mm/yyyy hh:mm`

Response indicating the successful addition of the task
and the new total number of tasks saved will be shown as follows.
```
Got it. I've added this task:
  [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
Now you have 1 tasks in the list.
```

## Mark a task: `mark` 
Marks specified task as completed.

Example: `mark 1`

Marks the indicated task with a "X" to show completion of the task.
```
Nice! I've marked this task as done: 
  [T][X] return book
```

## Unmark a task: `unmark`
Marks specified task as not completed.

Example: `mark 1`

Unmarks the indicated task if the task was previously marked with "X".
```
OK, I've marked this task as not done yet: 
  [T][ ] return book
```

## Search for a task: `find`
Finds tasks whose description contain the search description.

Example: `find book`

All tasks whose description match the query will be shown as follows.
```
Here are the tasks with the matching description:
  [T][ ] Book a seat in the library 
  [D][X] return book (Deadline: 1:00 pm, Mon 16 Sep 2024)
```

## Delete task: `delete`
Deletes specified task.

Example: `delete 2`

The task will be removed and the new total number of tasks saved will be shown as follows.
```
Got it. I've removed this task:
  [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
Now you have 3 tasks in the list.
```

## Show all tasks: `list`
Shows a list of all tasks.
```
Here are the tasks in your list:
1. [T][ ] Book a seat in the library 
2. [D][X] return book (Deadline: 1:00 pm, Mon 16 Sep 2024)
3. [E][ ] attend workshop (Start: 12:00 pm, Fri Sep 20 2024 ~ End: 5:00 pm, Fri Sep 20 2024
```

## Undo command: `undo`
Undoes the most recent command.
```
>>> undo 'mark' command
OK, I've marked this task as not done yet: 
  [T][ ] return book
```