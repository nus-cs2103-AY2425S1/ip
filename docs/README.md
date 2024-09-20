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

