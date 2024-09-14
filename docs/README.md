<style> g {color: greenyellow} </style>
<style> gr {color: green} </style>
# <g>Mel</g> User Guide

![Screenshot of Mel's GUI](Ui.png)

Mel is a simple chatbot helper to assist you with
remembering and managing your tasks.

## <g>Command Summary</g>
| Action                  | Command                                        |
|-------------------------|------------------------------------------------|
| Add to-do task          | `todo TASK`                                    |
| Add deadline task       | `deadline TASK /by DATE [TIME]`                |
| Add event task          | `event TASK /from DATE [TIME] /to DATE [TIME]` |
| Delete task             | `delete INDEX [MORE_INDICES]`                  |
| List all tasks          | `list`                                         |
| Mark task as complete   | `mark INDEX [MORE_INDICES]`                    |
| Mark task as incomplete | `unmark INDEX [MORE_INDICES]`                  |
| Find all matching tasks | `find KEYPHRASE`                               |
| End session             | `bye`                                          |

## <g>Adding Tasks</g>

Mel categorises tasks into 3 types:
* To-Do tasks
  * Simple task to be completed
* Deadline tasks
  * Tasks with a deadline
* Event tasks
  * Tasks that have a start and end date/time

Mel finishes all task creation by concluding with:
```
Mel counts NUMBER stuffs memorized XD
```
For total `NUMBER` of tasks in Mel's task list.

### <gr>Adding To-do Tasks</gr>

Adds a to-do task to the task list.
Format: `todo TASK`
* Creates to-do task with description `TASK`.

Example:
* `todo homework` adds task `homework`

Mel confirms task creation by responding:
```
Mel remembers...
  [T][ ] TASK
```

### <gr>Adding Deadline Tasks</gr>

Adds a deadline task to the task list.
Format: `deadline TASK /by DATE [TIME]`
* Creates deadline task with description `TASK`.
* Deadline is represented by date/time field which must at least include `DATE`
* `DATE` must be a valid date of format `yy[yy]-M[M]-d[d]`,
e.g. `2024-12-20` for `20 Dec 2024`
* `TIME` must be a valid time of format `hhmm` in 24h format,
e.g. `1845` for `6.45pm`
* If `TIME` is not provided, it is assumed to be at 12.00am of given `DATE`

Example:
* `deadline homework /by 25-2-2` adds task `homework`
with deadline by `2 Feb 2025 12.00am`
* `deadline more work /by 25-3-2 1830` adds task `more work`
with deadline by `2 March 2025 6.30pm`

Mel confirms task creation by responding:
```
Mel remembers...
  [D][ ] TASK (by: DATE TIME)
```

### <gr>Adding Event Tasks</gr>

Adds an event task to the task list.
Format: `event TASK /from DATE [TIME] /to DATE [TIME]`
* Creates event task with description `TASK`.
* Event is represented by date/time field which must at least include `DATE`
* `DATE` must be a valid date of format `yy[yy]-M[M]-d[d]`,
e.g. `2024-12-20` for `20 Dec 2024`
* `TIME` must be a valid time of format `hhmm` in 24h format,
e.g. `1845` for `6.45pm`
* If `TIME` is not provided, it is assumed to be at 12.00am of given `DATE`
* `/from` must be a valid date/time before `/to`

Example:
* `event work /from 25-2-2 /to 25-3-2 1830` adds
task `work` starting from `2 Feb 2025 12.00am` to `2 March 2025 6.30pm`

When a task is added, Mel confirms it by responding:
```
Mel remembers...
  [E][ ] TASK (from: DATE TIME to: DATE TIME)
```

## <g>Deleting Tasks</g>

Deletes a task from the task list.
Format: `delete INDEX [MORE_INDICES]`
* Deletes a task from at specified `INDEX`
* `INDEX` follows the indexing of tasks in task list, thus `INDEX` must be a
positive integer within task list, from index 1 onward
* The order of indices provided does not matter, e.g. `1 2 3` is equivalent to `2 3 1`

Example:
* `delete 1 2 3` deletes tasks of index 1, 2 and 3 from task list

When a task is deleted, Mel confirms it by responding:
```
Mel helps your forget...
  [TASK_TYPE][MARK] TASK [MORE_DESCRIPTION]
```

## <g>List All Tasks</g>

Lists all tasks in the task list with respective indexing.
Format: `list`
* Any additional extraneous details will render command invalid

Mel outputs:
```
Mel remembers all your stuff~
  [INDEX]. [TASK_TYPE][MARK] TASK [MORE_DESCRIPTION]
  [ANY_ADDITIONAL_TASKS]
```

## <g>Mark Task as Complete</g>

Marks a task from the task list as completed.
Format: `mark INDEX [MORE_INDICES]`
* Marks a task from at specified `INDEX`
* `INDEX` follows the indexing of tasks in task list, thus `INDEX` must be a
  positive integer within task list, from index 1 onward
* The order of indices provided does not matter, e.g. `1 2 3` is equivalent to `2 3 1`

Example:
* `mark 1 2 3` marks tasks of index 1, 2 and 3 from task list as completed

When a task is marked, Mel confirms it by responding:
```
Mel sees you completed your task!
  [TASK_TYPE][X] TASK [MORE_DESCRIPTION]
```

## <g>Mark Task as Incomplete</g>

Marks a task from the task list as not completed.
Format: `unmark INDEX [MORE_INDICES]`
* Marks a task from at specified `INDEX`
* `INDEX` follows the indexing of tasks in task list, thus `INDEX` must be a
  positive integer within task list, from index 1 onward
* The order of indices provided does not matter, e.g. `1 2 3` is equivalent to `2 3 1`

Example:
* `mark 1 2 3` marks tasks of index 1, 2 and 3 from task list as not completed

When a task is marked, Mel confirms it by responding:
```
Mel wonders how you undid your task...
  [TASK_TYPE][ ] TASK [MORE_DESCRIPTION]
```

## <g>Find All Matching Tasks</g>

Finds all tasks from task list with descriptions that contain the given keywords.
Format: `find KEYPHRASE`
* The search function is case-sensitive, e.g. `task` does not match `Task`
* The spacing and order of `KEYPHRASE` matters,
e.g. `more task` does not match `task   more`

Example:
* `find some task` will find and return all tasks that contain `some task`, such as
`create some task` and `delete some task`

## <g>End Session</g>

Ends the active session with Mel and closes the application.
Format: `bye`
* Any additional extraneous details will render command invalid

Before closing the application, Mel ends with:
```
Buh-bye :)
```

## <g>Save File</g>

Mel automatically creates and updates a save file on all task
list commands, storing them in a text file on local hard disk.
* File is stored as `tasks.txt` in `data/` directory
* Mel should have permission to write/read to file, otherwise Mel will return
the error `Mel is stunned! Mel couldn't access your file`

Before closing the application, Mel ends with:
```
Buh-bye :)
```