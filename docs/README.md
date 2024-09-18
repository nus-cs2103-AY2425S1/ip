# Elliot User Guide
-- -
## Table of Content
- [Introduction](#introduction)
- [Quick Preview](#quick-preview)
- [Features](#features)
    - [List tasks](#list)
    - [Mark task](#mark)
    - [Unmark task](#unmark)
    - [Add task](#add-task)
        - [Add todo task](#todo-task)
        - [Add deadline task](#deadline-task)
        - [Add event task](#event-task)
    - [Delete task](#delete)
    - [Find task](#find)
    - [Tag task](#tag)
        - [Find task via tags](#find-tag)
- [Acknowledgements](#acknowledgements)
-- -
## Introduction
___Elliot___ is a task manager that can keep track of all your tasks.
-- -
## Quick Preview
![representative screenshot](./Ui.png)
-- -
## Features
Available Features:
- Display the entire list of tasks
- Mark the task as done
- Unmark the task as not done
- Add a todo task
- Add a event task
- Add a deadline task
- Delete a task in the tasklist
- Find a task in the tasklist
- Add tags to a Task
- Find task by tags
-- -
### List
Displays all the task you have currently.
Example use of the following command:
```
list
```
Expected Result:
![list](./list.png)
-- -
### Mark
Marks task as done.
Example use of the following command:
```
mark 1 2 3
```
Expected Result:
![mark](./mark.png)
-- -
### Unmark
Unmarks task as not done.
Example use of the following command:
```
unmark 1 2 3
```
Expected Result:
![unmark](./unmark.png)
-- -
### Add Task
Add different types task to the list of tasks.
#### Todo Task
Add a todo task to the task list.
Example use of the following command:
```
todo ride bike
```
Expected Result:
![todotask](./todotask.png)
#### Deadline Task
Add a task with a deadline to the task list.
Example use of the following command:
```
deadline homework /by 02-02-2001 1200
```
Expected Result:
![deadlinetask](./deadlinetask.png)
#### Event Task
Add a event to the task list.
Example use of the following command:
```
event party /from 02-02-2001 1200 /to 02-02-2001 1400
```
Expected Result:
![eventtask](./eventtask.png)
-- -
### Delete
Delete task from task list.
Example use of the following command:
```
delete 3 2 1
```
Expected Result:
![delete](./delete.png)
-- -
### Find
Find task with the same task description.
Example use of the following command:
```
find math
```
Expected Result:
![find](./find.png)
-- -
### Tag
Tags task.
Example use of the following command:
```
todo math assignment 3 #hw
```
Expected Result:
![tag](./tag.png)
-- -
#### Find Tag
Since task now can have tags, we can also search tasks with same tags.
Example use of the following command:
```
findtag #hw
```
Expected Result:
![findtag](./findtag.png)
-- -
## Acknowledgements
use of _ImList_ from CS2030
