# Duker User Guide

![Screenshot of UI](Ui.png)

Duker is a chatbot that helps you stay on top of your tasks.

## Features
- Words in `UPPER_CASE` are the parameters to be supplied by the user. (e.g. in `mark INDEX`, `INDEX` is a 
  parameter which can be used as `mark 2`.)
- `DATE_TIME` have to be supplied in the following format `yyyy-MM-dd hh:ss`.
- To terminate the chatbot, simply execute the command `bye`.

## List all tasks: `list`

Prints out all the tasks you currently have in your task list.

Format: `list`

## Add new tasks: `todo` or `deadline` or `event`

### 1. `todo` task: A task with no time associated with it.
Format: `todo TASK_DESCRIPTION`

Example:

- `todo CS2103 iP` Adds the `todo` task `CS2103 iP` to the task list.

### 2. `deadline` task: A task with a deadline associated with it.
Format: `deadline TASK_DESCRIPTION /by DATE_TIME`

> Tip: Make sure to use /by instead of \by.

Example:

- `deadline submit proposal /by 2024-09-16 16:00` Adds the `deadline` task `submit proposal` which has a 
  deadline `16 SEPTEMBER 2024 04:00pm` to the task list.

### 3. `event` task: A task with a start and end associated with it.
Format: `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME`

> Tip: Make sure to include both /from and /to.

Example:

- `event networking event /from 2024-09-16 16:00 /to 2024-09-16 18:00` Adds the `event` task `networking 
  event` that starts from `16 SEPTEMBER 2024 04:00pm` and ends at `16 SEPTEMBER 2024 06:00pm` to the task 
  list.

## Delete task: `delete`

Delete a specified task from the task list.

Format: `delete INDEX`

> Tip: Make sure that the index provided is valid. Execute `list` to see the correct index of the task you 
> want to delete.

Example:

- `delete 1` Deletes the task that is at serial number `1` in the task list.

## Mark task: `mark`

Marks a specified task as completed.

Format: `mark INDEX`

> Tip: Make sure that the index provided is valid. Execute `list` to see the correct index of the task you
> want to delete.

Example:

- `mark 1` Marks the task that is at serial number `1` in the task list as completed.

## Unmark task: `unmark`

Unmarks a specified task so that it is recorded as uncompleted.

Format: `unmark INDEX`

> Tip: Make sure that the index provided is valid. Execute `list` to see the correct index of the task you
> want to delete.

Example:

- `unmark 1` Unmarks the task that is at serial number `1` in the task list so that it is recorded as 
  uncompleted.

## Find tasks with keyword: `find`

Finds all tasks in the task list that contain the keyword provided

Format: `find KEYWORD`

> Tip: Type the entire `keyword` to ensure you get optimal and accurate search results that suit what you 
> are looking for.

Example:

- `find code` Finds and prints all tasks with a `TASK_DESCRIPTION` that contains the keyword `code`.

## Prioritise tasks as _**HIGH PRIORITY**_: `prioritise`

Marks a specified task as _**HIGH PRIORITY**_.

Format: `prioritise INDEX`

> Tip: Make sure that the index provided is valid. Execute `list` to see the correct index of the task you
> want to delete.

Example:

- `prioritise 1` Marks the task that is at serial number `1` in the task list as a task that has _**HIGH 
  PRIORITY**_.

## Deprioritise tasks back to default priority: `deprioritise`

Marks a specified task back to default priority.

Format: `deprioritise INDEX`

> Tip: Make sure that the index provided is valid. Execute `list` to see the correct index of the task you
> want to delete.

Example:

- `deprioritise 1` Marks the task that is at serial number `1` in the task list as a task that has default 
  priority.

## See all _**HIGH PRIORITY**_ tasks: `priority`

Prints out all the tasks you currently have in your task list that has been marked with _**HIGH PRIORITY**_.

Format: `priority`

## Exiting the chat bot: `bye`

Exits the chat bot.

Format: `bye`