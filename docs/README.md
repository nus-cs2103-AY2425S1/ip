# Nerf User Guide

![Product image](./Ui.png)

## Viewing all task: `list`

Display all tasks saved in order of insertion.

Format: `list`

## Adding Todo: `todo`

Adds the todo task to tasklist.

Format: todo TASKNAME

Example: `todo view lecture video`

## Adding Deadlie: `deadline`

Adds the deadline task to tasklist.

Format: deadline TASKNAME /by YYYY-MM-DD

Example: `deadline weekly quiz /by 2024-09-17`

## Adding Event: `event`

Adds the event task to tasklist.

Format: event TASKNAME /from YYYY-MM-DD /to YYYY-MM-DD

Example: `event CS2 finals /from 2024-10-30 /to 2024-11-03`

## Marking a task: `mark`

Marks a task as completed.

Format: mark INDEX

Example: `mark 1`

## Unmarking a task: `unmark`

Unmarks a task as incompleted.

Format: unmark INDEX

Example: `unmark 1`

## Deleting a task: `mark`

Deletes the specified task from the tasklist.

Format: delete INDEX

Example: `delete 1`

## Locating a task: `find`

Finds tasks whose task description contain the given keyword.

Format: find KEYWORD

Example: `find quiz`

- The search is case-sensitive
- Index of find does not reflect the true index of task used for other methods.
- Only task description is searched
- Partial work can be matched

## Setting priority for a task: `priority`

Sets a priority to a task at three levels: `HIGH(1), MEDIUM(2), LOW(3)`

Format: priority INDEX /priority \[1-3\]

Example: `priority 1 /priority 1`
This command sets the task in index 1 to HIGH.

## Saving of data:

Tasklist data are saved to hard disk automatically after each command that changes the data. Thus, there is no need to save manually.

## Editing of the data file

Tasklist data are saved automatically as a text file `[JAR file location]/data/taskStorage.txt`. Advanced users are welcome to update data directly by editing that data file.

> ⚠️ **Caution:** Be careful when modifying manually as invalid data will ignored.
