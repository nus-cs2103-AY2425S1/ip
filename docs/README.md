# Buddy User Guide

<img width="893" alt="Ui" src="https://github.com/user-attachments/assets/f0a47f56-0ccd-4d0d-974b-eb2eb4898973">


Buddy (aka. AbdulBuddy) is a chatbox that helps you keep track of your tasks using intuitive commands


## Adding Todo

You can add todos through this command: 

todo *input*

Buddy will create an uncompleted task with a description and add it to his list

Example: `Todo Read Book`

Buddy will take note of it and reply with: 

```
Gotcha! I've added this task:
     [T][] Read Book
Now, you have 1 tasks in the list!
```
The T in the first bracket refers to the type of task: T and the empty second bracket indicates it has not been completed


## Adding Deadlines

You can add deadlines containing (i) description (ii) deadline date/ timing through this command:

deadline *description* *date* *time*

The date and timing should be in the format: DD/MM/YYYY HHmm

Example: `deadline Coding Homework 12/12/2024 1500`

Buddy will take note and reply with:

```
Gotcha! I've added this task:
     [D][] Coding Homework (By: Dec 12 2024 1500)
Now, you have 1 tasks in the list!
```
The T in the first bracket refers to the type of task: T and the empty second bracket indicates it has not been completed

## Adding Events

You can create events, mainly used a task that lasts for a certain period of time. You can add events through the command:

event *description* /from *start date* /to *end date*

Both start and end date should be in the form: DD/MM/YYYY HHmm

Example: `event Orientation Camp /from 12/12/2024 1500 /to 13/12/2024 1500`

Buddy will reply with:

```
Gotcha! I've added this task:
     [E][] Orientation Camp (From: Dec 12 2024 1500, To: Dec 13 2024 1500)
Now, you have 1 tasks in the list!
```
## Marking Tasks

Tasks can be marked when they are completed using the command "mark":

mark *task index*

Task Index corresponds to the order in which the tasks were added

Example: `mark 1`

Buddy will reply with:

```
Nice one buddy! Marked this as done...
    [T][X] Read Book
```
The X in the second bracket indicates that the task is completed

## Unmarking Tasks
Tasks can be unmarked if they are not completed using the command "unmark":

unmark *task index*

Task Index corresponds to the order in which the tasks were added

Example: `unmark 1`

Buddy will reply with:

```
Alright buddy, let's give that task another shot!
    [T][] Read Book
```
## Deleting Tasks
Tasks can be removed if deemed fit using the command: 

delete *task index*

Task Index corresponds to the order in which the tasks were added

Example: `delete 1`

Buddy will reply with:

```
Noted. I've removed this task:
      [T][X] Read Book
Now you have 0 tasks in the list

```
## Listing Tasks

All the tasks created at the moment can be viewed using the command:

list

This displays all the tasks and their corresponding task index. 

Example: `list`

```
Here are the tasks in your list:
1. [T][X] Read Book

```

## Finding Tasks

The tasks currently in the list can be found by typing its prefix. 

This displays all the tasks that has the prefix specified. 

Example: `find re`

```
Here are the matching tasks in the list:
1. [T][X] Read Book
2. [T][] Release Pigeon

```

## Updating tasks

The tasks that are currently on the list can be updated through the command:

update *Task Index* *Task Field* *New Value*

the task index can be found through the list command. 

The task Field comprises of:
1. Todo: description
2. Deadline: description, deadline
3. Event: description, start, end

Using any of the task field lists for the tasks you want to edit. 

Example: `update 1 description "Return Book"`

Buddy will reply with:

```
Task is updated!
```

In the list:

```
Here are the taks in your list:
1. [T][X] Return Book
```

## Exit

You will be able to exit the application with the command: Exit
After which the input bar will be disabled and the application can be closed. 












