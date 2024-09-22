# Knight2103 User Guide

## Overview
**Screenshot of the product:**<br>
![Screenshot of chatbot](https://github.com/cth06-Github/ip/blob/master/docs/Ui.png)

**Summary:** Knight2103 is a chatbot that manages a record of tasks you need.  <br><br>
**TextFile:** Users can load a text file "savedTaskList.txt" which must be located in the root directory to populate the tasks into the programme, or start from scratch (no text file). If there is no text file, a text file with the name `savedTaskList.txt` will be automatically be created which will save the tasks real-time as the programme executes its function.

## Features
Users can do the following via the commands available:
* [Add Todo Task (todo)](#add-todo-task)
* [Add Deadline Task (deadline)](#add-deadline-task)
* [Add Event Task (event)](#add-event-task)
* [Mark Task as done (mark)](#mark-task)
* [Mark Task as not done (unmark)](#unmark-task)
* [Delete Task (delete)](#delete-task)
* [View all the tasks recorded (list)](#view-all-tasks)
* [Find particular set of tasks (find)](#find-specific-tasks)
* [Sort tasks in order (sort)](#sort-the-list-of-tasks)
* [End the programme (bye)](#exiting-the-programme)

If the commands are typed in the wrong format or spelling, an error message will be shown by the bot.

### Add ToDo Task
Add Todo Task into the chatbot. The added task will be saved in the text file as well. <br>

**Command format:** `todo <task_description>` <br>
**Example:** `todo return book` <br>
**Expected output** (for example):
```
Task added:
[T][] return book
Total number of tasks in list: 1   # number of tasks depends on the tasklist currently
Type command "list" to see full list of tasks.
```
`todo` command will fail if the task description is missing. <br>

### Add Deadline Task
Add Deadline Task into the chatbot. The added task will be saved in the text file as well. <br>

**Command format:** `deadline <task_description> /by <deadline_in_yyyy:MM:dd>` <br>
**Example:** `deadline complete homework /by 2024-09-22` <br>
**Expected output** (for example):
```
Task added:
[D][] complete homework (by: 22 Sep 2024)
Total number of tasks in list: 2   # number of tasks depends on the tasklist currently
Type command "list" to see full list of tasks.
```
`deadline` command will fail if the task description and/or deadline is missing. Error will be shown if the deadline format is not in **yyyy:MM:dd** (year:month:day) format. <br>


### Add Event Task
Add Event Task into the chatbot. The added task will be saved in the text file as well.<br>

**Command format:** `event <task_description> /from <start_date&time_in_yyyy:MM:ddThh:mm> /to <end_date&time_in_yyyy:MM:ddThh:mm>` <br>
**Example:** `event book fair /from 2024-09-22T09:00 /to 2024-09-22T19:00` <br>
**Expected output:**
```
Task added:
[E][] book fair (from: 22 Sep (Sun) 09:00 to 22 Sep (Sun) 19:00)
Total number of tasks in list: 3   # number of tasks depends on the tasklist currently
Type command "list" to see full list of tasks.
```
`event` command will fail if the task description and/or start date&time and/or end date&time is missing. Error will be shown if the start and end date&time format is not in **yyyy:MM:ddThh:mm** (year:month:day T hours:minutes) format. The start date and time must also be before the end date and time as well.<br>


### Mark Task
Mark a task as done from the chatbot. The text file will be updated as well.<br>

**Command format:** `mark <task_Integer_index>` <br>
**Example:** `mark 1` <br>
**Expected output** (for example):
```
Mark this task as done!:
[T][X] return book     #the task with index 1 in the task list
Type command "list" to see full list of tasks.
```
`mark` command will fail if the task index is not found in the task list. The command will proceed even if the task is already marked as done.

### Unmark Task
Mark a task as not done from the chatbot. The text file will be updated as well.<br>

**Command format:** `unmark <task_Integer_index>` <br>
**Example:** `unmark 1` <br>
**Expected output** (for example):
```
Mark this task as not done yet!:
[T][] return book     #the task with index 1 in the task list
Type command "list" to see full list of tasks.
```
`unmark` command will fail if the task index is not found in the task list. The command will proceed even if the task is already marked as not done.

### Delete Task
Delete a task from the chatbot. The text file will be updated as well.<br>

**Command format:** `delete <task_Integer_index>` <br>
**Example:** `delete 1` <br>
**Expected output** (for example):
```
Task removed:
[T][] return book     #the task with index 1 in the task list
Total number of tasks in list: 2    # number of tasks in tasklist after deletion
Type command "list" to see full list of tasks.
```
`delete` command will fail if the task index is not found in the task list.

### View all Tasks
View all the tasks from the chatbot. <br>

**Command:** `list` <br>
**Expected output** (for example):
```
Here's the list of tasks:
1. [D][] complete homework (by: 22 Sep 2024)
2. [E][] book fair (from: 22 Sep (Sun) 09:00 to 22 Sep (Sun) 19:00)
```


### Find specific Tasks
Shows all the tasks from the chatbot that contain a SUBSTRING of the keyword searched. That means, if users find "car", words like "career" will also be selected as "car" is a substring of "career".<br>

**Command:** `find <keyword>` <br>
**Example:** `find car` <br>
**Possible output**:
```
Here are the matching tasks in the list:
1. [T][] carry books
2. [D][] prepare career interview (by: 22 Sep 2024)
3. [E][] car fair (from: 22 Sep (Sun) 09:00 to 22 Sep (Sun) 19:00)
```
`find` command will fail if the keyword to be searched is not provided.

### Sort the list of tasks
Sort the list of task from the chatbot. This sorted list will be saved into the text file as well. <br>

**Sorting order**<br>
Tasks will be sorted in the following considertion, in order:
* Whether tasks are mark as done or not done. Tasks that are not done (unmarked) will be placed above all tasks that are marked as done *
* Within the same done status, ToDo Task > Deadline Task > Event Task, i.e. Todo Task will be placed at the top. 
* Within the same TaskType:
  * ToDo task will be ranked in alphabetical order in terms of its task description
  * Deadline task will be ranked in terms of the deadline Date. The deadline that is earlier will be ranked higher.
  * Event task will be ranked in terms of Start date and time. The start date and time that is earlier will be ranked higher.
* For Deadline and Event task: if the deadline or start date and time is the same respectively, they will be ranked in alphabetical order in terms of its task description. 

**Command:** `sort` <br>
**Possible output** (for example):
```
Here's the sorted list of tasks:
1. [T][] call the boss
2. [T][] go for a run
3. [D][] report draft 1 (by: 21 Sep 2024)
4. [D][] book consultation (by: 23 Sep 2024)
5. [D][] presentation final (by: 23 Sep 2024)
6. [E][] book fair (from: 22 Sep (Sun) 09:00 to 22 Sep (Sun) 19:00)
7. [T][X] contact friend
8. [E][X] car fair (from: 19 Sep (Sun) 09:00 to 22 Sep (Sun) 19:00)
```

### Exiting the programme
Exit the programme by saying goodbye to the chatbot.<br>

**Command:** `bye` <br>
**Expected output**:
```
Bye. Hope to see you again soon!
```
The user text input will be locked and the send button will be disabled thereafter. 