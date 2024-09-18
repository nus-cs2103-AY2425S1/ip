# Tako User Guide

![](Ui.png)

Tako is a chat bot that helps you keep track of the tasks you have
using Graphical User Interface(GUI).  
It is able to track ToDo Task, Deadlines as well as Event!

[Adding Tasks](#adding-tasks)
- [Adding ToDo Task](#adding-todo-task-todo)
- [Adding Deadline Task](#adding-deadline-task-deadline)
- [Adding Event Task](#adding-event-task-event)

[Operations](#operations)
- [Find a task](#find-a-task-find)
- [Mark a task](#mark-a-task-mark)
- [Unmark a task](#unmark-a-task-unmark)
- [Delete a task](#delete-a-task-delete)
- [Set Priority](#set-priority-priority)

[Mass Commands](#mass-commands)
- [Listing all tasks](#listing-all-tasks-list)
- [Exiting the program](#exiting-the-program-bye)


# Commands

## Note

Words in brackets are the parameters to be supplied by the user.  
e.g. in todo (task name), 'task name' is to be given by the user, so
`todo Read Up on Maths` is adding a todo task with the task 
name 'Read Up on Maths'.

## Adding Tasks

### Adding ToDo Task: `todo`
Adds a ToDo task to the list
- A general task to be completed but does not have a specific due date
- Format: `todo (task name)` 
- Examples: `todo Read Up on Maths`  
            `todo Find Countries to go during the Holidays`

### Adding Deadline Task: `deadline`
Adds a Deadline task to the list
- A task that has to be completed by a certain date
- The date has to be a valid date
- If the MM/DD is 1-digit, a '0' at the front is required
- Format: `deadline (task name) /by (YYYY-MM-DD)`
- Examples: `deadline Coding Assignment 1 /by 2024-10-10`  
            `deadline Pay Water Bills /by 2024-10-01`

### Adding Event Task: `event`
Adds a Event task to the list.
- A task that is scheduled at a specific start to end date
- The date has to be a valid date
- If the MM/DD is 1-digit, a '0' at the front is required
- Format: `event (task name) /from 2022-10-10 /to 2023-11-11`
- Examples: `event Japan Trip /from 2024-10-10 /to 2024-10-20`  
            `event Dinner Date /from 2024-12-12 /to 2024-12-12`


## Operations

### Find a task `find`
Shows all the task with the specific keyword.
- The keyword is case-sensitive. `buy` will match with `buy` but not `BUY`
- Format: `find (keyword)`
- Examples: `find assignment`  
            `find bills`

### Mark a task `mark`
Mark the specified task with a 'X'.
- Format: `mark (number)`
- Mark the task at the specified number
- The number corresponds to the number as shown by command `list`
- The number must be positive
- Examples: `mark 1`  
            `mark 15`

### Unmark a task `unmark`
Unmark the specified task by removing the 'X' (if any).
- Format: `unmark (number)`
- Unmark the task at the specified number
- The number corresponds to the number as shown by command `list`
- The number must be positive
- Examples: `unmark 1`  
            `unmark 15`

### Delete a task `delete`
Delete the specified task by removing in from the list.
- Format: `delete (number)`
- Delete the task at the specified number
- The number corresponds to the number as shown by command `list`
- The number must be positive
- Examples: `delete 1`  
            `delete 15`

### Set Priority `priority`
Set the priority of the task in the list.
- Format: `priority (level) (number)`
- The level must only be 'low', 'medium' or 'high'
- The level input is case in-sensitive
- The number corresponds to the number as shown by command `list`
- The number must be positive
- Task with priority 'high' will be placed at the top of the list,
  followed by 'medium' then 'low'
- Examples: `priority high 2`  
            `priority MEDIUM 4`


## Mass Commands

### Listing all tasks `list`
Shows a list of all the tasks stored by the bot.
- Format: `list`

### Exiting the program `bye`
Exits the program
- Format: `bye`


# Credits

- Tako picture from Pinterest - https://mx.pinterest.com/pin/377387643792836973/
- User picture from Pinterest - https://mx.pinterest.com/pin/84724036731575360/