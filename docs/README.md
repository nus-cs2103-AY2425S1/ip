# Gopher User Guide

![ScreenShot of Gopher's UI for showcase to the user]("/assets/images/Ui.png")

## Introduction
Having issue managing and tracking your tasks? Gopher is here to help you!
Just tell Gopher what you want to track, and Gopher can remember everything for you!
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

## Commands
With a command:
- `text` is the basic part of the command for it to be interpreted correctly by Gopher
- `/text` is token that is needed by Gopher to manipulate relevant task
- `[text]` are parameters that are compulsory for Gopher to interpret the command
- `[text]...` are compulsory fields that accepts any number of parameters
- `{text}` are optional part of the command

DateTime formats accepted by Gopher:
1. YYYY-MM-DD (For example: 2024-09-11)
2. YYYY-MM-DD HH:mm (For example: 2024-09-11 10:00)

### Add todo
Todo is a type of task that only contains description/name of the task, it can be created using:
`todo [taskName]`

Example: `todo Wash Clothes`

### Add deadline
Deadline is a type of task that contains a due date, it can be created using:
`deadline [taskName] /by [due date time]`

Example: `deadline iP Final Submission /by 2024-09-20 23:59`

### Add event
Event is a type of task that contains a start and end date, it can created using:
`event [taskName] /from [start date time] /to [end date time]`

Example: `event Orbital Splashdown /from 2024-08-28 16:00 /to 2024-08-28 21:00`

### List all tasks
You can list out all the tasks that are current tracked by Gopher using:
`list`

### Mark tasks as done
You can mark multiple tasks as done using:
`mark [task number]...`

For example: `mark 1 2 3`

### Mark as not done
You can mark multiple tasks as not done using:
`unmark [task number]...`

For example: `unmark 1 2 3`

### Update todo
You can update a todo task using:
`update [task number] [new task name]`

For example:
If you want to update a todo task with task number 1 with new task name `Assignment 1`, input:
`update 1 Assignment 1` 

### Update deadline
You can update a deadline task using:
`deadline [task number] {new task name} {/by new due date time}`
For example:
If you want to update a deadline task with task number 2 with the following information:
- New task name: `CS2101 OP1 Synopsis`
- New due date time: `2024-09-12`
You can input the update command like this:
`update 2 CS2101 OP1 Synopsis /by 2024-09-12`

### Update event
You can update an event task using:
`event [task number] {new task name} {/from new start date time} {/to new end date time}`

For example: 
If you want to update an event task with task number 3 with the following information:
- New task name: `CS2100 Midterm Exam`
- New start date time: `2024-10-10 10:00`
- New end date time: `2024-10-10 12:00`
You can input the update command like this:
`update 3 CS2100 Midterm Exam /from 2024-10-10 10:00 /to 2024-10-10 12:00`

### Find tasks
You can find tasks that match a certain keyword using:
`find [keyword]`

For example:
If you want to find all tasks tracked by Gopher that contains the keyword `Orbital`, input:
`find Orbital`

### Delete task
You can delete multiple tasks using:
`delete [task number]...`

For example: `delete 1 2 3`

### Exit
You can say goodbye to Gopher using:
`bye` OR simply close the application window
