# Screwllum User Guide

Screwllum is a HSR-themed task management application that helps users keep track of their tasks. The UI can be seen 
[here](#sample-output)!

It can add tasks, delete them, toggle the status of the task, list all tasks as well as find tasks that match certain keywords.
There is also an additional feature to archive all your existing tasks!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that Java `17` or above is installed.

1. Download [the latest jar file from here](https://github.com/Quasant/ip/releases/).

1. Using a command terminal, `cd` into the folder the jar file is in, and type `java -jar screwllum.jar`.

1. Utilise the commands listed below.
--------------------------------------------------------------------------------------------------------------------
## Command summary (click on command to jump directly)

| Command                                                     | Format, Examples                                                                                         |
|-------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| **[Todo](#add-a-task-todo)**                                | `todo DESC` <br> e.g., `todo dishes`                                                                     |
| **[Deadline](#add-a-task-with-a-deadline-deadline)**        | `deadline DESC /by DEADLINE` <br> e.g., `deadline IP /by 2024-09-23`                                     |
| **[Event](#add-a-task-that-spans-between-two-dates-event)** | `event DESC /from STARTDATE /to ENDDATE` <br> e.g., `event National Day /from 2024-08-01 /to 2024-08-20` |
| **[Delete](#delete-a-task--delete)**                        | `delete INDEX`<br> e.g., `delete 3`                                                                      |
| **[Toggle](#toggle-the-status-of-a-task--toggle)**          | `toggle INDEX`<br> e.g., `toggle 3`                                                                      |
| **[Find](#find-tasks-by-description-find)**                 | `find KEYWORD` <br> e.g., `find assignment`                                                              |
| **[List](#list-command-list)**                              | `list`                                                                                                   |
| **[Archive](#archive-recorded-tasks--archive)**             | `archive`                                                                                                |
| **[Bye](#exiting-the-program--bye)**                        | `bye`                                                                                                    |

--------------------------------------------------------------------------------------------------------------------

## Commands

### Add a task: `todo`

Creates a task with the specified `DESC`.

Format: `todo DESC`

### Add a task with a deadline: `deadline`

Creates a task with the specified `DESC` and `DEADLINE`.

Format: `deadline DESC /by DEADLINE`

* `DEADLINE` has to be formatted as shown [here](#date-format).

### Add a task that spans between two dates: `event`

Creates a task with the specified `DESC` that occurs between 
`STARTDATE` and `ENDDATE`

Format: `event DESC /from STARTDATE /to ENDDATE`

* `STARTDATE` and `ENDDATE` has to be formatted as shown [here](#date-format).

### List command: `list`

Provides a view of all the recorded tasks.

Format: `list`

### Find tasks by description: `find`

Provides a view of the recorded tasks whose description contains the keyword. 

Format: `find KEYWORD`

### Delete a task : `delete`

Deletes the task at the specified `INDEX`.

Format: `delete INDEX`


* The index refers to the index number shown when using the command `list`.


### Toggle the status of a task : `toggle`

Changes the status of the task at the specified `INDEX`

Format: `toggle INDEX`

* If a task is done, `toggle` will mark the task as undone.
* If a task is not done, `toggle` will mark the task as done.
* The index refers to the index number shown when using the command `list`.

### Archive recorded tasks : `archive`

Saves recorded task into an archival file and resets the list of recorded tasks.

Format: `archive`

### Exiting the program : `bye`

Exits the program. 

Format: `bye`

* Alternatively, you can click on the close button on the application window.
* Your recorded tasks will be saved upon exit in the following path `"/data/Save.txt"`.

--------------------------------------------------------------------------------------------------------------------
## Extra information

### Date Format
Dates have to be in the format YYYY-M-D. 

Valid examples: `2024-10-5`, `2024-12-12`, `2024-5-2`, `2024-5-20`

### Commands are case-insensitive

### Certain commands ignore extra input
For instance, if you enter `bye SOMETHINGEXTRA`, it would be interpreted as just `bye` and not return an error.

--------------------------------------------------------------------------------------------------------------------
## Sample Output

[Back to top](#quick-start)

![screenshot](Ui.png "Logo Title Text 1")

