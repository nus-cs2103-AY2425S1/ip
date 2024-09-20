# Eevee User Guide

![Ui](/Ui.png)

**Eevee** is a task manager to help make your life more convenient, optimized for use via commands. Eevee can help you track your tasks fast with a minimalistic GUI. 

### Notes about the command format:

- Commands can be typed in upper case or lower case or a mix of both.
- Words in `UPPER_CASE` are parameters to be supplied by the user. 



## Adding `Todo` task

Adds a task (with task name) to the task list.

Format: `todo TASK_NAME`

Examples:

- `todo exercise`

````
Added the following task to your list:
[T][] exercise
````

- `Todo Finish Homework`

````
Added the following task to your list:
[T][] Finish Homework
````

- `TODO complete project`

````
Added the following task to your list:
[T][] complete project
````



## Adding `Deadline` task

Adds a task with a deadline to the task list.

Format: `deadline TASK_NAME /by DEADLINE`

- For formatting of dates, they are expected to be in the format YYYY-MM-DD

Examples:

- `deadline  Submit ip /by tomorrow`

````
Added the following task to your list:
[D][] Submit ip (by tomorrow)
````

- `deadline buy birthday present /by 2024-09-30`

````
Added the following task to your list:
[D][] buy birthday present (by Sep 30 2024)
````




## Adding `Event` task

Adds a task with a timeline to the task list. 

Format: `event TASK_NAME /from START_TIME /to END_TIME`

- For formatting of dates, there are expected to be in the format YYYY-MM-DD

Examples:

- `event birthday party /from today /to tomorrow`

````
Added the following task to your list:
[E][] birthday party (from today to tomorrow)
````

- `event meeting /from 2024-09-22 /to 2024-09-23`

````
Added the following task to your list:
[E][] meeting (from Sep 22 2024 to Sep 23 2024)
````

- `event marathon /from today /to 2024-09-21`

```` 
Added the following task to your list:
[E][] marathon (from today to Sep 21 2024)
````



# `List` all tasks

Shows a list of all tasks in the task list.

Format: `list`



# `Mark` task as completed

Marks the selected task as completed.

Format: `mark TASK_NUMBER`

- Marks the task at the specified `TASK_NUMBER` as completed. 
- The task number refers to the number shown in the displayed task list. 
- The task number must be a positive integer and match up to the task list. 

Examples:

- `mark 2` marks the 2nd task in the task list as done as long as it exists and has not already been marked. 

Example outcome:

````
Congratulations! I've marked the following task as done:
[D][X] buy birthday present (by Sep 30 2024)
````



# `Unmark` task as incomplete

Unmarks a task such that it is incomplete. 

Format: `unmark TASK_NUMBER`

- Unmarks the task at the specified `TASK_NUMBER` as incomplete. 
- The task number refers to the number shown in the displayed task list. 
- The task number must be a positive integer and match up to the task list. 

Examples:

- `unmark 2` unmarks the 2nd task in the task list as long as it exists and is marked. 

Example outcome:

````
Ok! Task no longer marked as done:
[D][] buy birthday present (by Sep 30 2024)
````



# `Delete` task

Deletes a task from the task list. 

Format: `delete TASK_NUMBER`

- Deletes the task at the specified `TASK_NUMBER`. 
- The task number refers to the number shown in the displayed task list. 
- The task number must be a positive integer and match up to the task list. 

Examples:

- `delete 2` deletes the 2nd task in the task list as long as it exists.

Example outcome:

````
As you wish, this task has been removed:
[D][] buy birthday present (by Sep 30 2024)
````



# `Find` task

Finds a task whose name contains any of the given keywords. 

Format: `find KEYWORD`

- The search is case-insensitive. e.g. `read` will match `Read`
- The order of the keywords matter. e.g. `party birthday` will *not* match `birthday party`
- Only the task name is searched. 
- Partial words can be matched. e.g. `par` will match `party`
- Any task that has a name matching the full keyword(s) given will be returned. e.g. `ook` will match `buy a book`

Examples:

- `find meeting`

````
Here are the tasks containing keywrod meeting:
[E][] project meeting (from Sep 22 2024 to Sep 23 2024)
[E][] staff meeting (from today to Sep 21 2024)
````



# `Prioritize` task

Prioritizes a task as low, medium or high priority. 

Format: `prioritize TASK_NUMBER PRIORITY_LEVEL`

- Sets priority level for the task at the specified `TASK_NUMBER`. 
- The task number refers to the number shown in the displayed task list. 
- The task number must be a positive integer and match up to the task list.
- The priority level is case-insensitive. e.g. `high` will yield the same result as `HIGH`

- Priority level is set as `low` on default if not specified using `prioritize` command.
- `PRIORITY_LEVEL` must be `low`, `medium`, or `high`.
- `TASK_NUMBER` and `PRIORITY_LEVEL` must be separated by whitespace.

Examples:

- `prioritize 5 high`

````
Ok! I've set the priority of the following task to HIGH:
[E][] staff meeting (from today to Sep 21 2024)
````



# `List` tasks of a certain priority level

Lists all tasks that match the specified priority level. 

Format: `list PRIORITY_LEVEL`

- `PRIORITY_LEVEL` must be `low`, `medium`, or `high`.

Examples:

- `list high`

````
Here are your tasks of HIGH priority:
1. [T][] Drink water
5. [E][] staff meeting (from today to Sep 21 2024)
````



# Exit the program

Exits the program.

Format: `bye`



# Command Summary

| Command        | Format, Examples                                             |
| -------------- | ------------------------------------------------------------ |
| **Todo**       | `todo TASK_NAME` e.g. `todo exercise`                        |
| **Deadline**   | `deadline TASK_NAME /by DEADLINE`  e.g. `deadline buy birthday present /by 2024-09-30` |
| **Event**      | `event TASK_NAME /from START_TIME /to END_TIME` e.g. `event meeting /from 2024-09-22 /to 2024-09-23` |
| **List**       | `list` OR `list PRIORITY_LEVEL` e.g. `list high`             |
| **Mark**       | `mark TASK_NUMBER` e.g. `mark 2`                             |
| **Unmark**     | `unmark TASK_NUMBER` e.g. `unmark 2`                         |
| **Delete**     | `delete TASK_NUMBER` e.g. `delete 2`                         |
| **Find**       | `find KEYWORD` e.g. `find meeting`                           |
| **Prioritize** | `prioritize TASK_NUMBER PRIORITY_LEVEL` e.g. `prioritize 5 high` |
| **Bye**        | `bye`                                                        |

