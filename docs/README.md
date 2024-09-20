# Susan User Guide

> SusanLite is a desktop app for managing tasks, optimised for use via a Command Line Interface (CLI) while still having benefits of a Graphical User Interface (GUI).



## Adding to-dos

> Adds a non-urgent task to the task list. 

Format: `todo TASK`

Example: `todo read book` adds the task `read book` to the task list.



## Adding deadlines

> Adds a task with a deadline to the task list.

Format: `deadline TASK /by DATE`

* The date format must be `yyyy-mm-dd`

Example: `deadline return book /by 2024-09-30`

Expected output:

```
Got it. I've added this task:
   [D][] return book (by Sep 30 2024)
You have 2 task(s) in the list.
```



## Adding events

> Adds an event to the task list. 

Format: `todo EVENT /from START_DATETIME /to END_DATETIME`

* The datetime format must be `yyyy-mm-dd hhmm`

Example: `event project meeting /from 2024-09-30 1800 /to 2024-09-30 2000`

Expected output:

```
Got it. I've added this task:
   [E][] project meeting (from Sep 30 2024 6:00pm to 8:00pm)
You have 3 task(s) in the list.
```



## Listing all tasks: `list`

> Shows a list of all tasks in the task list.

Format: `list`



## Reminders: `remindme`

> Shows a list of all tasks within the next 3 days.

* to-dos will not be shown.

Format: `remindme`



## Locating tasks by keyword: `find`

> Find tasks which contains a given keyword.

Format: `find KEYWORD`

* The search is case-insensitive e.g. `essay` will match `Essay`
* Only full words will be matched exactly e.g. `return book` will not match `return books`



## Marking a task: `mark` / `unmark`

> Marks / unmarks the specified task from the task list as done / undone.

Format: `mark INDEX` / `unmark INDEX`

* Mark / unmark the task at the specified `INDEX` as done / undone.
* The index refers to the index number shown in the task list.
* The index must be a positive integer 1, 2, 3, ...

Example:

* `list` followed by `mark 2` marks the 2nd task in the task list as done.
* `unmark 2` unmarks the 2nd task in the task list as undone.



## Deleting a task: `delete`

> Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index must be a positive integer 1, 2, 3, ...

Example: `list` followed by `delete 2` deletes the 2nd task in the task list.



## Exiting the program: `bye`

Exits the program.

Format: `bye`



## Saving the data

Tasks data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.



## Editing the data file

Tasks data are saved automatically as a txt file [JAR file location]/data/SusanToDoList.txt. Advanced users are welcome to update data directly by editing that data file.'

Caution: If your changes to the data file makes its format invalid, SusanLite may discard all data and start with an empty data file at the next run. Therefore, edit the data file only if you are confident that you can update it correctly.