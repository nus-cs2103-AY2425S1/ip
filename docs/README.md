# Bob User Guide

![Ui.png](Ui.png)

**Bob** is a chatbot designed to help users **_stay organised_**
and **_manage their tasks efficiently_**.

Whether you need help managing deadlines, planning your day, or simply track minor tasks,
Bob can make your life easier!

**_Let Bob handle your tasks so you can focus on what truly matters._**

+ [Quick Start](#quick-start)
+ [Features](#features)
  + [Add ToDo task](#add-todo-task-todo): `todo`
  + [Add Deadline task](#add-deadline-task-deadline): `deadline`
  + [Add Event task](#add-event-task-event): `event`
  + [Display all tasks](#display-all-tasks-list): `list`
  + [Mark a task](#mark-a-task-mark): `mark`
  + [Unmark a task](#unmark-a-task-unmark): `unmark`
  + [Get relevant tasks by date](#get-relevant-tasks-by-date-relevant): `relevant`
  + [Find relevant tasks by keyword](#find-relevant-tasks-by-keyword-find): `find`
  + [Sort tasks](#sort-tasks-sort): `sort`
  + [Delete a task](#delete-a-task-delete): `delete`
  + [Exit the application](#exit-the-application-bye): `bye`

## Quick Start
+ Have Java 17 installed on your computer / laptop
+ Download the latest `.jar` file.
+ Open terminal and `cd` to the directory where the `.jar` file is located.
+ Run the command `java -jar Bob.jar` to open Bob application.

> [!IMPORTANT]
> Mac users will require the specific Azul JDK 17 distribution.

## Features
> [!NOTE]
> Words in UPPER_CASE are the parameters to be specified by the user.

### Add ToDo task: `todo`
Adds a ToDo task to the task list.

Format: `todo DESCRIPTION`

**Example**
+ `todo laundry`

Expected output:
```
Adding ToDo task:
 [T][] laundry
Total number of tasks in your list: 1
```

### Add Deadline task: `deadline`
Adds a Deadline task to the task list.

Format: `deadline DESCRIPTION /by DUE_DATE`
+ `DUE_DATE` has format *yyyy-MM-dd HHmm*

**Example**
+ `deadline return book /by 2024-10-25 1800`

Expected output:
```
Adding Deadline task:
 [D][] return book (by: Oct 25 2024 06:00 pm)
Total number of tasks in your list: 2
```

### Add Event task: `event`
Adds an Event task to the task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`
+ `START_DATE` and `END_DATE` has format *yyyy-MM-dd HHmm*

**Example**
+ `event christmas eve party /from 2024-12-24 1900 /to 2024-12-25 0100`

Expected output:
```
Adding Event task:
 [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
Total number of tasks in your list: 3
```

### Display all tasks: `list`
Shows all tasks in the task list.

Format: `list`

**Example**

Expected output:
```
Your list of tasks:
1. [T][] laundry
2. [D][] return book (by: Oct 25 2024 06:00 pm)
3. [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
Total number of tasks in your list: 3
```

### Mark a task: `mark`
Marks a task as done.

Format: `mark TASK_NUMBER`
+ The `TASK_NUMBER` refers to the task number shown in the displayed task list.
+ The task number must be a **positive integer** 1, 2, 3, ...

**Example**
+ `mark 1`

Expected output:
```
Good Job! Marking this task as done:
 [T][X] laundry
```

### Unmark a task: `unmark`
Marks a task as not done yet.

Format: `unmark TASK_NUMBER`
+ The `TASK_NUMBER` refers to the task number shown in the displayed task list.
+ The task number must be a **positive integer** 1, 2, 3, ...

**Example**
+ `unmark 1`

Expected output:
```
Good Job! Marking this task as done:
 [T][] laundry
```

### Get relevant tasks by date: `relevant`
Shows tasks occurring on a specific date.

Format: `relevent SPECIFIC_DATE`
+ `SPECIFIC_DATE` has format *YYYY-MM-DD HHMM*

**Example**
+ `relevant 2024-12-24`

Expected output:
```
1. [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
Total number of relevant tasks for Dec 24 2024: 1
```

### Find relevant tasks by keyword: `find`
Shows tasks with description containing the specified keyword or phrase.

Format: `find KEYWORD`
+ `KEYWORD` can be a word or a phrase
+ Only full words will be matched e.g. `book` will not match `books`

**Example**
+ `find christmas eve party`

Expected output:
```
1. [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
Total number of tasks containing "christmas eve party": 1
```

### Sort tasks: `sort`
**Sort By Description**

Shows tasks by description in alphabetical order.

Format: `sort description`

**Example**

Expected output:
```
Here is your list of tasks sorted by description:
1. [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
2. [T][] laundry
3. [D][] return book (by: Oct 25 2024 06:00 pm)
```
**Sort By Date**

Shows tasks by the earliest date.
+ ToDo tasks do not have time constraints, hence they are ordered last.

Format: `sort date`

**Example**

Expected output:
```
Here is your list of tasks sorted by date:
1. [D][] return book (by: Oct 25 2024 06:00 pm)
2. [E][] christmas eve party (from: Dec 24 2024 07:00 pm to: Dec 25 2024 01:00 am)
3. [T][] laundry
```

### Delete a task: `delete`
Removes a specific task from the task list.

Format: `delete TASK_NUMBER`
+ The `TASK_NUMBER` refers to the task number shown in the displayed task list.
+ The task number must be a **positive integer** 1, 2, 3, ...

**Example**
+ `delete 1`

Expected output:
```
Noted, removing this task:
 [T][] laundry
Total number of tasks in your list: 2
```

### Exit the application: `bye`
Closes Bob application in 3 seconds.

Format: `bye`

**Example**

Expected output:
```
Bye! Hope to see you again :)
```
