# Gopher User Guide

<img src="Ui.png" alt="ScreenShot of Gopher's UI for showcase to the user" width="400" />

## Menu
- [Introduction](#introduction)
- [Feature List](#feature-list)
- [Set Up](#set-up)
- [Date Time Format](#datetime-formats-accepted-by-gopher)
- [Commands](#commands)
    - [Add Todo](#add-todo)
    - [Add Deadline](#add-deadline)
    - [Add Event](#add-event)
    - [List All Tasks](#list-all-tasks)
    - [Mark Task as Done](#mark-tasks-as-done)
    - [Mark Tasks as Not Done](#mark-tasks-as-not-done)
    - [Update Todo](#update-todo)
    - [Update Deadline](#update-deadline)
    - [Update Event](#update-event)
    - [Find Tasks](#find-tasks)
    - [Delete Tasks](#delete-tasks)
    - [Exit](#exit)

## Introduction
Having issue managing and tracking your tasks? Gopher is here to help you!\
Just tell Gopher what you want to track, and Gopher can remember everything for you!\
Store, Manage and Query the tasks just like how you talk to a person in real life!

## Feature List
1. Add Task(which can be todo, deadline or event)
    1. todo consists of only task description
    2. deadline contains due date of the task
    3. event specifies the start and end date & time of the task
2. Mark tasks as done/not done
3. Update task's details(name and any relevant dates)
4. Delete tasks
5. Find tasks based on keyword search
6. Auto-save/load tasks(YES! Gopher can remember everything for you!!!)

## Set Up
Setting up Gopher is easier than you think! Just follow these simple steps and you are ready to go!
1. Settle Dependencies: Ensure that `Java 17` is installed on your machine
2. Get the Jar File: Download `Gopher.jar` file from the latest release and put in a folder
3. Wake Gopher Up: Use command prompt to navigate to the folder that contains the jar file, and run
`java -jar Gopher.jar`
4. Start managing your tasks with Gopher!!!

## DateTime formats accepted by Gopher:

| Date Time Format  | Example            |
| ----------------- | ------------------ |
| YYYY-MM-DD        | 2024-09-11         |
| YYYY-MM-DD HH:mm  | 2024-09-11 10:00   |

## Commands
With a command:
- `text` is the basic part of the command for it to be interpreted correctly by Gopher
- `/text` is token that is needed by Gopher to manipulate relevant task
- `[text]` are parameters that are compulsory for Gopher to interpret the command
- `[text]...` are compulsory fields that accepts any number of parameters
- `{text}` are optional part of the command

### Add todo
Todo is a type of task that only contains description(or name) of the task, it can be created using:\
`todo [taskName]`

**Example**: `todo Wash Clothes`

### Add deadline
Deadline is a type of task that contains a due date, it can be created using:\
`deadline [taskName] /by [due date time]`

**Example**: `deadline iP Final Submission /by 2024-09-20 23:59`

### Add event
Event is a type of task that contains a start and end date, it can created using:\
`event [taskName] /from [start date time] /to [end date time]`

**Example**: `event Orbital Splashdown /from 2024-08-28 16:00 /to 2024-08-28 21:00`

### List all tasks
You can list out all the tasks that are current tracked by Gopher using:\
`list`

### Mark tasks as done
You can mark multiple tasks as done using:\
`mark [task number]...`

**Example**: `mark 1 2 3`

### Mark tasks as not done
You can mark multiple tasks as not done using:\
`unmark [task number]...`

**Example**: `unmark 1 2 3`

### Update todo
You can update a todo task using:\
`update [task number] [new task name]`

**Example**:\
If you want to update a todo task with task number 1 with new task name `Assignment 1`, input:\
`update 1 Assignment 1` 

### Update deadline
You can update a deadline task using:\
`deadline [task number] {new task name} {/by new due date time}`

**Example**:\
If you want to update a deadline task with task number 2 with the following information:
- New task name: `CS2101 OP1 Synopsis`
- New due date time: `2024-09-12`

You can input the update command like this:\
`update 2 CS2101 OP1 Synopsis /by 2024-09-12`

### Update event
You can update an event task using:\
`event [task number] {new task name} {/from new start date time} {/to new end date time}`

**Example**:\
If you want to update an event task with task number 3 with the following information:
- New task name: `CS2100 Midterm Exam`
- New start date time: `2024-10-10 10:00`
- New end date time: `2024-10-10 12:00`

You can input the update command like this:\
`update 3 CS2100 Midterm Exam /from 2024-10-10 10:00 /to 2024-10-10 12:00`

### Find tasks
You can find tasks that match a certain keyword using:\
`find [keyword]`

**Example**:\
If you want to find all tasks tracked by Gopher that contains the keyword `Orbital`, input:\
`find Orbital`

### Delete tasks
You can delete multiple tasks using:\
`delete [task number]...`

**Example**: `delete 1 2 3`

### Exit
You can say goodbye to Gopher using:\
`bye`\
OR simply close the application window
