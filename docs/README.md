# User Guide for King Chatbot

## Features
:information_source: Notes about the command format:

- Words in UPPER_CASE are the parameters to be supplied by the user.
  - e.g., in `deadline TASK_DESCRIPTION /by DATE_TIME`, `TASK_DESCRIPTION` is a parameter which can be used as `deadline submit_report /by 2024-09-30T10:00`.
- Items in square brackets are optional.
  - e.g., `event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME` can be used as `event meeting /from 2024-09-25 1400 /to 2024-09-25 1600`.
- Parameters can be in any order.
  - e.g., if the command specifies `delete INDEX`, `INDEX delete` is also acceptable.
- Extraneous parameters for commands that do not take in parameters (such as `bye`, `list`, and `remind`) will be ignored.
  - e.g., if the command specifies `list extra`, it will be interpreted as `list`.

## Viewing help : `help`
Displays a message explaining how to access the help page.

**Format:** `help`

## Adding a task: `todo`
Adds a todo item to the task list.

**Format:** `todo TASK_DESCRIPTION`

:bulb: Tip: A task can be added without any additional parameters.

**Examples:**
- `todo Buy groceries`
- `todo Complete assignment`

## Adding a deadline: `deadline`
Adds a deadline task to the task list.

**Format:** `deadline TASK_DESCRIPTION /by DATE_TIME`

**Examples:**
- `deadline Submit report /by 2024-09-30 1000`
- `deadline Finish project /by 2024-10-01 1500`

## Adding an event: `event`
Adds an event to the task list.

**Format:** `event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

- **START_DATE_TIME** and **END_DATE_TIME** must be in the format `yyyy-MM-dd HHmm`.

**Examples:**
- `event Meeting /from 2024-09-25 1400 /to 2024-09-25 1600`
- `event Conference /from 2024-09-28 0900 /to 2024-09-28 1700`

## Listing all tasks: `list`
Displays all tasks in the task list.

**Format:** `list`

## Marking a task as done: `mark`
Marks a task as completed.

**Format:** `mark INDEX`

**Examples:**
- `mark 1` marks the first task as done.

## Unmarking a task: `unmark`
Unmarks a task, indicating it is not completed.

**Format:** `unmark INDEX`

**Examples:**
- `unmark 1` unmarks the first task.

## Finding tasks: `find`
Finds tasks that contain the specified keywords.

**Format:** `find KEYWORD [MORE_KEYWORDS]`

**Examples:**
- `find report` returns all tasks with "report" in the description.
- `find assignment project` returns tasks matching either keyword.

## Reminding about tasks: `remind`
Lists tasks that are due soon.

**Format:** `remind [DAYS]`

**Examples:**
- `remind 2` shows tasks due in the next 2 days.
- `remind` shows tasks due soon without a specified time frame.

## Deleting a task: `delete`
Deletes the specified task from the task list.

**Format:** `delete INDEX`

**Examples:**
- `delete 2` deletes the second task.

## Clearing all tasks: `clear`
Removes all tasks from the task list.

**Format:** `clear`

## Exiting the program: `exit`
Exits the chatbot application.

**Format:** `exit`

## Saving the data
Task data is saved automatically after any command that modifies the data. No manual saving is required.

## Editing the data file
Task data is saved automatically as a JSON file at `[JAR file location]/data/tasks.json`. Advanced users can update data directly by editing this file.

:exclamation: **Caution:** If your changes to the data file make its format invalid, the application will discard all data and start with an empty data file on the next run. It's recommended to back up the file before editing it.
