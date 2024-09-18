# Bestie User Guide

![img.png](img.png)

## Meet Bestie, your personal task assistant chatbot!
Bestie is your personal task tracker assistant designed to help you keep organised and stay on top of your tasks. 
Effortlessly add, manage, and track your tasks with simple commands. Whether you're juggling work deadlines, 
todos or personal events, Bestie keeps everything in one place for you to have a peace of mind.
Bestie makes task management feel less like a chore and more like a breeze. Say goodbye to feeling overwhelmed and 
say hello to productivity!



## Key features
### Adding Todos: `todo`
To add a new todo, use the command 

`todo (name of task) /priority (high/medium/low)`

The `priority` tag indicates the priority of the task, and you can choose between high, medium and low.

Example command: `todo read book /priority low`

### Adding Deadlines: `deadline`

To add a new deadline task, use the command

`deadline (name of task) /by YYYY-MM-DD HHMM /priority (high/medium/low)`

Ensure that the date is of the correct format of Year-Month-Day and the time is in 24 hour time. 

Example command: `deadline submit work /by 2024-09-20 1800 /priority high` 


### Adding Events: `event`

To add a new event, use the command 

`event (name of event) /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM /priority (low/medium/high)`

Ensure that the date is of the correct format of Year-Month-Day and the time is in 24 hour time.

Example command: `event meeting /from 2024-09-18 1900 /to 2024-09-19 2000 /priority high`


### Marking tasks as completed: `mark`
To mark a task as completed, use the `mark (index of task)` command. Ensure to correctly input the task of the index 
you would like to mark as completed. 

### Marking tasks as incomplete: `unmark`
By default, all tasks added to the task list are unmarked. However, if you had previously marked 
a task as complete that you would now like to mark as incomplete, you can do so via the 
`unmark (index of task)` command. Ensure to correctly input the task of the index you would like to 
mark as incomplete. 

### Searching for tasks by keyword: `find` 
To search for tasks that contain specific keywords, use the command
`find (keyword)`.

This will generate a list of tasks in your current task list that contain the keyword.

### Searching for tasks by priority: `priority`
To obtain a list of all existing tasks of a certain priority, use the command 
`priority (high/medium/low)`. 

This will generate a list of tasks in your current task list that have the specified priority.


### Deleting tasks: `delete`
To delete a particular task, use the command `delete (index of task)`, ensuring that you key in 
the index of the task in your existing task list to be deleted.


### Closing the app and Saving your tasks: `bye` 
**This step is important to ensure your tasks are properly saved in your task list!**

Use the command `bye` before exiting the app to ensure your tasks are saved. 
