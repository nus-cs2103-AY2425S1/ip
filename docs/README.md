# DGPT User Guide

![Sample image of how the DGPT chatbot looks like in action](Ui.png)

**DGPT** is here to help you stay organized and on top of your tasks with ease. Whether you need to set deadlines,
track events, or even remember recurring tasks, **DGPT** is equipped to handle it all. Simply give me the command, and
I'll help you create, update, delete and monitor them. Let's get your to-dos under control and make productivity a 
breeze!

## Features

### Show existing tasks: `list`

Shows a list of all existing tasks.

Format: `list`

Example:
```
Here are the following items in your list:
1.[T][] clean my room
```

### Marking and Unmarking tasks: `mark`, `unmark`

Marks and unmarks a task in the list respectively.

Format: `mark/unmark INDEX`
- Marks or unmarks the task at the specified `INDEX`. The index refers to the index number shown in the displayed
task list. The index **must be a positive integer** 1,2,3...
- the task will then be marked with [x] or [] when displayed depending on whether is it marked or not.

Examples:
- `mark 5` marks the 5th task in the list with a cross
- `unmark 3` unmarks the 3rd task in the list by removing the cross


### Searching for tasks by keyword: `find`
Finds tasks whose description contain any of the given keywords.

Format:`find KEYWORD`
- The search is case-sensitive. e.g`coffee` will not match `Coffee`

Example:
- `find coffee` returns
```
Here are the matching tasks in your list:
1.[T][] make coffee
```

### Create a ToDo task: `todo`
Creates a Task with a description.
- a ToDo task starts off unmarked.

Format: `todo DESCRIPTION`

Example: `todo make coffee` creates an unmarked ToDo task with `make coffee` as its description.

### Create a Deadline task: `deadline`
Creates a Task with a description and a deadline.
- a Deadline task starts off unmarked.
- `DEADLINE` expects a date in the format of `dd/MM/yyyy`

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline make coffee /by 19/09/2024` creates
```
[D][] make coffee (by: 19 Sep 2024)
```

### Create an Event task: `event`
Creates a Task with a description, a start time and an end time.
- an Event task starts off unmarked.
- `Event` expects a start time and an end time in the format of `dd/MM/yyyy HHmm`

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event attend coffee conference /from 19/09/2024 1200 /to 19/09/2024 1800` creates
```
[E][] attend coffee conference (from: 19 Sep 2024, 12:00PM to: 19 Sep 2024, 6:00PM)
```

### Create a Recurring task: `recurring`
Creates a Task with a description and a frequency.
- a Recurring task starts off unmarked.

Format: `recurring DESCRIPTION /every FREQUENCY`

Example: `recurring drink coffee /every morning` creates
```
[R][] drink coffee (every: morning)
```

### Delete a task: `delete`
Deletes a task at the specified index.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed
  task list. The index **must be a positive integer** 1,2,3...

Example: `delete 3` deletes the task at the 3rd index in the list.

### Save existing tasks: `save`
Saves the list of tasks you currently have locally, which will be loaded in the next time you start the application.

Format: `save`

If successful, the following will be shown:
```
successfully saved!
```


