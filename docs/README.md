# Peridot User Guide

![Product Screenshot](Ui.png)

## About

Peridot is a todo-list tracker that allows you to input 3 different tasks:
1. Todo (Task with Name only)
2. Deadline (Task with Name and Daedline Date)
3. Event (Task with Name, Start Date, and End Date)

## User Guide

After starting the program, you can input commands and the bot will respond accordingly!

To see the list of commands in the bot, type
```
help
```

*You can also see the full list of commands down below*

To close the bot, click the 'X' button on the top right corner of the program.

## Commands

Here is the list of possible commands:

1. List Out Tasks
   * Command: 'list'
   * e.g `list`
   * Lists out all the tasks
2. Add a new Todo
   * Command: 'todo {taskName}'
   * e.g `todo Work`
   * Adds a new Todo Task to the TaskList
3. Add a new Deadline
    * Command: 'deadline {taskName} /by {YYYY-MM-DD}'
    * e.g `deadline Homework /by 2024-10-27`
    * Adds a new Deadline Task to the TaskList
4. Add a new Event
    * Command: 'event {taskName} /from {YYYY-MM-DD} /to {YYYY-MM-DD}'
    * e.g `event Field Trip /from 2024-09-17 /to 2024-09-19`
    * Adds a new Event Task to the TaskList
5. Mark a Task
    * Command: 'mark {taskIndex}'
    * e.g `mark 3`
    * Marks the task as done
6. Unmark a Task
    * Command: 'unmark {taskIndex}'
    * e.g `unmark 2`
    * Marks the task as not done 
7. Delete a Task
    * Command: 'delete {taskIndex}'
    * e.g `delete 4`
    * Deletes the specified task
8. Find Tasks
    * Command: 'find {keyword}'
    * e.g `find work`
    * Returns a tasklist with only tasks that contain the keyword
9. Get List of Commands
    * Command: 'help'
    * e.g `help`
    * Returns list of commands