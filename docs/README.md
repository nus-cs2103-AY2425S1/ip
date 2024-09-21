# Muffin User Guide

![Screenshot of product Ui.](docs/Ui.png)

Muffin is a simple and intuitive task list manager that helps you keep track of your to-dos, deadlines, and events with 
ease. The app supports a variety of task types and allows you to perform several useful operations, including listing, 
marking, unmarking, and deleting tasks. You can also undo actions and search for specific tasks using a keyword.

## Features

### Todo Tasks: Create simple tasks 
Format: `todo [task]`

Example:
```
todo buy apples
```

### Deadline Tasks: Create tasks with a deadline
Format: `deadline [task] /by [date]`

Example:
```
deadline submit assignment /by 2024-09-24
```

### Event Tasks: Create tasks with start and end dates
Format: `event [task] /from [start date] /to [end date]`

Example:
```
event school fair /from 2024-09-24 /to 2024-09-26
```

### List Tasks: View all tasks currently in the list 
Format: `list`

### Mark Tasks: Update completed tasks
by specifying its index using the format: `mark [index]`

Example:
```
mark 1
```

### Unmark Tasks: Unmark previously marked task
by specifying its index using the format: `unmark [index]`

Example:
```
unmark 1
```

### Delete Tasks: Remove a task from the list
by specifying its index using the format: `delete [index]`

Example:
```
delete 1
```

### Find Tasks: Search for tasks containing a specific keyword
Format: `find [keyword]`

Example:
```
find assignment
```

### Undo Actions: Undo the most recent operations
Format: `undo`

### Exit: End the application 
Format: `bye`

## Installation
1. Clone this repository to your local machine.
2. Ensure you have the necessary dependencies installed. 
3. Run the app!