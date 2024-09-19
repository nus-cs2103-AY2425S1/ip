# Will User Guide

![Ui.png](Ui.png)

Introducing Will – your personal task management assistant designed to help you stay on top of your schedule effortlessly. Will specializes in managing ToDos, Events, and Deadlines, ensuring that your daily tasks are organized and manageable. With intuitive commands, Will makes it easy to add tasks, track deadlines and events, so you can stay focused and productive. Whether it’s keeping track of tasks or planning ahead for important dates, Will is here to help you manage your day with ease and confidence. Let Will take care of the details, so you can focus on achieving your goals!

## Feature

### Add Todo: `todo`

Adds a simple todo task.

Format: `todo [TASK]`

Example: 
* `todo do assignment`
* `todo buy lunch`

### Add Deadline: `deadline`

Adds a task with a deadline that must be completed by a specific date and time.

Format: `deadline [TASK] /by [DATETIME]`

* The datetime must be in **d/M/yyyy HHmm** format

Example:
* `deadline return book /by 12/12/2024 2359`
* `deadline submit assignment /by 20/9/2024 2359`

### Add Event: `event`

Adds an event task that spans a specific period of time.

Format: `event [TASK] /from [DATETIME] /to [DATETIME]`

* The datetime must be in **d/M/yyyy HHmm** format

Example:
* `event project meeting /from 18/10/2024 1500 /to 18/10/2024 1800`
* `event midterm exam /from 19/9/2024 0800 /to 19/9/2024 1000`


### Mark task: `mark`

Mark task as done

Format: `mark [INDEX]`

* Mark the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `mark 1` mark the 1st task in the task list.

### Unmark task: `unmark`

Mark task as done

Format: `unmark [INDEX]`

* Unmark the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `unmark 1` unmark the 1st task in the task list.

### List tasks: `list`

Shows a list of all tasks.

Format: `list`

### Find task: `find`

Finds task whose names contain any of the given keywords.

Format: `find [KEYWORD]`

* The search is case-sensitive.
* Only the task name is searched.

Examples:
* `find lunch` return `buy lunch` and `eat lunch`
* `find buy` return `buy lunch` and `buy iphone16`

### Delete task: `delete`

Deletes the specified task.

Format: `delete [INDEX]`

* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 1` deletes the 1st task in the task list.

### Exiting the program: `bye`

Exits the program.

Format:`bye`

### Saving the data

Tasks are saved in `./data/db.txt` automatically after any command that changes the data. There is no need to save manually.

### Duplication

Duplicated task will be flagged and cannot be saved. 




