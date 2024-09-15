# Nerf User Guide

![Product image](./Ui.png)

## Viewing all task: `list`

Display all tasks saved in order of insertion.

Format: `list`

## Adding Todo: `todo`

Adds the todo task to tasklist.

Format: todo <taskname>

Example: `todo view lecture video`

## Adding Deadlie: `deadline`

Adds the deadline task to tasklist.

Format: deadline <taskname> /by <yyyy-MM-dd>

Example: `deadline weekly quiz /by 2024-09-17`

## Adding Event: `event`

Adds the event task to tasklist.

Format: event <taskname> /from <yyyy-MM-dd> /to <yyyy-MM-dd>

Example: `event CS2 finals /from 2024-10-30 /to 2024-11-03`

## Marking a task: `mark`

Mark a task as completed.

Format: mark <taskIndex>

Example: `mark 1`

## Unmarking a task: `unmark`

Unmark a task as incompleted.

Format: unmark <taskIndex>

Example: `unmark 1`

## Deleting a task: `mark`

Mark a task as completed.

Format: mark <taskIndex>

Example: `mark 1`

## Locating a task: `mark`

Mark a task as completed.

Format: mark <taskIndex>

Example: `mark 1`

## Setting priority for a task: `mark`

Mark a task as completed.

Format: mark <taskIndex>

Example: `mark 1`

## Saving of data:

Tasklist data are saved to hard disk automatically after each command that changes the data. Thus, there is no need to save manually.

## Editing of the data file

Tasklist data are saved automatically as a text file `[JAR file location]/data/taskStorage.txt`. Advanced users are welcome to update data directly by editing that data file.

> ⚠️ **Caution:** Be careful when modifying manually as invalid data will ignored.
