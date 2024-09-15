# Tecna User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## Quick Start
- There is already some tasks inside stored inside the application

## Notes
- 3 types of tasks, explain each type
- Type of datetime input

## Adding a task
Adds a new task to the list of tasks.

Command format:

- ToDo task: `todo [task name]`
- Deadline task: `deadline [task name] /by [deadline of task in yyyy-MM-dd HHmm]`
- Event task: `event [task name]`

Example: 
- `todo prepare for the magic meeting`
- `deadline submit a magic formula to Professor Winx /by 2024-09-20 2359`
- `event attend flying training /from 2024-09-16 1600 /to 2024-09-16 1900`

## Listing all tasks
Lists all the tasks in the list in the order of adding into the list.

Command format: `list`

## Searching
Searches any tasks that contain the specified keyword in the task name.
Command format: `find [keyword]`
> [!NOTE]
> Only the first word is considered as keyword. The subsequent ones will be ignored.

Example: both `find magic` and `find magic training` return the same list
[SCREENSHOT]()

## Mark a task as Done
Marks a task with the corresponding index number as done.
Command format: `mark [index number]`

## Unmark a task
Unmarks the done-status of the task with the corresponding index number.
Command format: `unmark [index number]`


## Delete a task
Deletes a task by the index number in the application task list.

Command format: `delete [index number]`

Example: `delete 1` will delete the first task in the list and shift the subsequent tasks up.
> [!TIP]
> You should use the `list` command to see the index of the task you want to delete.

## Exit Tecna
Closes the Tecna chatbot's window and quits the program.

Command format: `bye`


## Saving the data
Your input data will be automatically saved in the program's disk.

## Command summary

| Action                    | Format, Examples                                                                                                                                                |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| __Add a *ToDo* task__     | `todo [task name]`<br/>e.g., `todo go to the Tec-magic school`                                                                                                  |
| __Add a *Deadline* task__ | `deadline [task name] /by [deadline yyyy-MM-dd HHmm]`<br/>e.g., `deadline submit the Tec-gic assignment /by 2024-09-16 1300`                                    |
| __Add an *Event* task__   | `event [task name] /from [start yyyy-MM-dd HHmm] /to [end yyyy-DD-mm HHmm]`<br/>e.g., `event join a casual Tec-dance /from 2024-09-20 1300 /to 2024-09-20 1500` |
| __List__                  | `list`                                                                                                                                                          |
| __Find__                  | `find [keyword]`br/>e.g., `find school`                                                                                                                         |
| __Mark__                  | `mark [index number]`<br/>e.g., `mark 2`                                                                                                                        |
| __Unmark__                | `unmark [index number]`<br/>e.g., `unmark 2`                                                                                                                    |
| __Delete__                | `delete [index number]`<br/>e.g., `delete 2`                                                                                                                    |
| __Bye__                   | `bye`                                                                                                                                                           |
