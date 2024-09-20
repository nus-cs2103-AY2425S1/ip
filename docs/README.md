# Vuewee User Guide

Vuewee is a local application to keep track of tasks using a simple and intuitive chatbot interface! Your task data is _automatically saved_ and loaded whenever you start Vuewee again!

![UI example](./Ui.png)

## Adding Tasks

You can add 3 types of tasks: "todo", "deadline" and "event".

### Adding a TODO Task

Add a simple TODO task with no dates.

> Format: `todo <task name>`

### Adding a Deadline Task

Add a task with a single deadline.

> Format: `deadline <task name> /by <due date in yyyy-mm-dd format>`

### Adding an Event Task

Add an event task with a start and end date.

> Format: `event <task name> /start <start date in yyyy-mm-dd format> /end <end date in yyyy-mm-dd format>`

## Listing Tasks

Show all the tasks stored by Vuewee.

> Format: `list`

## List Today's Schedule

Show all tasks for today.

> Format: `schedule`

## Finding a Task

Search task by name (case-insensitive).

> Format: `find <task name>`

## Mark a Task as Done

Mark a task as done by task number index. It is recommended to use `list` before to get the task number.

> Format: `mark <task number>`

## Mark a Task as Not Done

Mark a task as not done by task number index. It is recommended to use `list` before to get the task number.

> Format: `unmark <task number>`

## Delete a Task

Delete a task by task number index. It is recommended to use `list` before deleting in order to prevent accidental incorrect deletions.

> Format: `delete <task number>`

## Exit Vuewee

> Format: `bye`

## Command Summary

| **Command**                    | **Example/Usage**                                                                                                          |
| ------------------------------ | -------------------------------------------------------------------------------------------------------------------------- |
| **Adding a TODO Task**         | `todo <task name>` <br> Example: `todo Finish report`                                                                      |
| **Adding a Deadline Task**     | `deadline <task name> /by <due date in yyyy-mm-dd format>` <br> Example: `deadline Submit assignment /by 2024-10-01`       |
| **Adding an Event Task**       | `event <task name> /start <start date> /end <end date>` <br> Example: `event Conference /start 2024-09-19 /end 2024-09-21` |
| **Listing All Tasks**          | `list`                                                                                                                     |
| **Listing Today’s Schedule**   | `schedule`                                                                                                                 |
| **Finding a Task**             | `find <task name>` <br> Example: `find meeting`                                                                            |
| **Marking a Task as Done**     | `mark <task number>` <br> Example: `mark 3`                                                                                |
| **Marking a Task as Not Done** | `unmark <task number>` <br> Example: `unmark 2`                                                                            |
| **Deleting a Task**            | `delete <task number>` <br> Example: `delete 4`                                                                            |
| **Exiting Vuewee**             | `bye`                                                                                                                      |
