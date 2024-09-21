# <img src="/src/main/resources/images/DavidLogo.png" alt="David Logo" width="25"/> David User Guide

![Screenshot of David chatbot](/docs/Ui.png)


David is a task list tracker, enhanced and optimised for users who prefer
typing.

The features David provides include:
- [x] Add tasks (Todo tasks, Deadline tasks and event tasks)
- [x] Delete tasks
- [x] Local caching of tasks
- [x] Sort of tasks
- [x] Find tasks
- [x] Mark and unmark of tasks

# Installation
To download the latest version of David,
1. Go to the project's [releases](https://github.com/Incogdino/ip/releases/tag/v0.2).
2. Download the `.jar` file.
3. Run the `jar` file by double clicking on it.



## Command summary
> [!NOTE]
>
> The easiest way to see all available commands is simply calling the `help` command

| Command  | Arguments                                         | Action             |
| ----------- |---------------------------------------------------|--------------------|
| `todo`       | `TASK_NAME`                                       | Adds todo task     |
| `deadline`       | `/by YYYY-MM-DD HHHH`                             | Adds deadline task |
| `event`       | `/from YYYY-MM-DD HHHH` and  `/to YYYY-MM-DD HHHH` | Adds event task    |
| `mark` and `unmark`       | `INDEX`                                           | Marks and unmarks a task |
| `delete`       | `INDEX`                                           | Deletes a task |
| `list`       | -                                                 | Lists all task |
| `find`       | `TASK_NAME`                             | Finds all task corresponding to the specified string |
| `sort`       | `ORDER_BY`                             | Sorts tasks in the order specified |
| `help`       | -                              | Displays a list of available commands |


## Features
> [!NOTE]
> - Words in `UPPER_CASE` are required parameters supplied by the user.
> - Words in `lower_case` are action specifiers for commands


### Add todos
Adds a todo task to the list of tasks.

Format: `todo TASK_NAME`

Example: `todo eat`


### Add deadlines
Adds a deadline task to the list of tasks.

Format: `deadline TASK_NAME /by YYYY-MM-DD HHHH`

Deadlines should be appended with an input in the format of "YYYY-MM-DD HHHH"
where
- "Y" reprsents the year
- "M" represents the month
- "D" represents the day of the month
- "H" represents the 24-hour time

Example: `deadline cs2100 assignment /by 2024-12-12 1230`

### Add events
Adds an event task to the list of tasks.

Format: `event TASK_NAME /from YYYY-MM-DD HHHH /to YYYY-MM-DD HHHH`

`/from` and `/to` should be appended with an input in the format of "YYYY-MM-DD HHHH"
where
- "Y" reprsents the year
- "M" represents the month
- "D" represents the day of the month
- "H" represents the 24-hour time

Example: `event dance /from 2024-12-12 1230 /to 2024-12-12 1330`

### List tasks
Lists all tasks that have been added

Format: `list`

Example: `list`

### Mark and unmark tasks
Marks or unmarks tasks selected

Format: `mark INDEX` or `unmark INDEX`


Example:
```
mark 1
unmark 1
```

### Find tasks
Returns all tasks that matches the given event name.

Format: `find TASK_NAME`

Example: `find eat`

### Delete tasks
Deletes the specified task corresponding to the number in the list

Format: `delete INDEX`

Required arguments: `i` task number to delete

Example: `delete 1`

### Sort tasks
Sorts all tasks in the given order.

Format: `sort ORDER`

Parameters accepted:
- `asc` - ascending order
- `desc` - descending order

Example: `sort asc`

### Display all available commands
Displays a list of available commands

Format: `help`

Example: `help`