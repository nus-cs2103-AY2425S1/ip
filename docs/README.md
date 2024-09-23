# Loafy User Guide

![Screenshot of the Loafy UI](/Ui.png)

Loafy is a desktop app for keeping track of all the things you need to do. 
From simple todos to important event dates, 
you never have to search anywhere else for your task lists again. 


## Features
- [Adding a todo: `todo`] (#adding-a-todo-todo)
- [Adding a deadline: `deadline`] (#adding-a-deadline-deadline)
- [Adding an event: `event`] (#adding-an-event-event)
- [Listing all tasks : `list`] (#listing-all-tasks-list)
- [Locating task by name: `find`] (#locating-task-by-name-find)
- [Deleting a task : `delete`] (#deleting-a-task-delete)
- [Exiting the program : `bye`] (#exiting-the-program-bye)
- [Saving the data] (#saving-the-data)

## Adding a todo: `todo`

Adds a todo to the task list. 

Format: `todo TASK_NAME` 

Example: `todo drink water`

```
Got it. I've added this task:
[T][ ] drink water
Now you have 1 tasks in the list.
```

## Adding a deadline: `deadline`
Adds a deadline with the specified date to the task list.

Format: 
- `deadline TASK_NAME /by DATE`
- date: `DAY OF WEEK[ HHmm]` or `d/M/yyyy[ HHmm]`

Examples: 
* `deadline wash dishes /by 23/9/2024 2100`
* `deadline finish assignment /by 1/10/2024`
* `deadline sleep /by monday 2300` 
* `deadline submit assignment /by tuesday`
  (the date will be read as this coming tuesday 2359)

```
Got it. I've added this task:
[D][ ] submit assignment (by: 24/9/2024 2359)
Now you have 2 tasks in the list.
```

## Adding an event: `event`
Adds an event with the specified start date and end date to the task list.

Format:
- `event TASK_NAME /from DATE /to DATE`
- date: `DAY OF WEEK[ HHmm]` or `d/M/yyyy[ HHmm]`

Examples:
* `event eat breakfast /from tuesday 0900 /to tuesday 1000`
* `event attend lecture /from 1/10/2024 1400 /to 1/10/2024 1600`

```
Got it. I've added this task:
[E][ ] attend lecture (from: 1/10/2024 1400 to: 1/10/2024 1600)
Now you have 3 tasks in the list.
```

## Listing all tasks: `list`
Lists all the tasks in the current list in the order they were added

Format:
- `list`

```
Here are the tasks in your list:
1. [T][ ] drink water
2. [D][ ] submit assignment (by: 24/9/2024 2359)
3. [E][ ] attend lecture (from: 1/10/2024 1400 to: 1/10/2024 1600)
```

## Locating task by name: `find`
Finds all tasks which have names that match the specified start keyword and returns them in a list.

Format:
- `find KEYWORD`

Examples:
* `find assignment`

```
Here are the matching tasks in your list:
2. [D][ ] submit assignment (by: 24/9/2024 2359)
```

## Deleting a task: `delete`
Deletes the task by its taskId as displayed in `list` or `find`

Format:
- `delete TASKID`

Examples:
* `delete 2`

```
Got it. I've removed this task:
[D][ ] submit assignment (by: 24/9/2024 2359)
Now you have 2 tasks in the list.
```

## Exiting the program: `bye`
Loafy responds and exits the program. 
```
byeee see you soon! ;)
```

## Saving the data
Loafy data are saved in the hard disk automatically after any command that changes the data. 
The data will reload automatically the next time you open the program on the same desktop. 
There is no need to save manually.