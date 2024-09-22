# Cloud User Guide
![Ui.png](Ui.png)

Cloud is a minimalistic desktop application for managing tasks, deadlines and events.

## Adding todos: `todo`

Create a todo task with a description.

Format: `todo DESCRIPTION`
- All fields are mandatory

Examples:
- `todo Wash the car`

## Adding deadlines: `deadline`

Create a deadline task with a description and a due date and time.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HH:mm`

- All fields are mandatory
- Time must be in 24-hour format

Examples:
- `deadline Submit assignment /by 24/09/2024 23:59`

## Adding events: `event`

Create an event task with a description, a start date-time and an end date-time.

Format: `event DESCRIPTION /from DD/MM/YYYY HH:mm /to DD/MM/YYYY HH:mm`

- All fields are mandatory
- Time must be in 24-hour format

Examples:
- `event CS2104 midterms /from 03/10/2024 12:00 /to 03/10/2024 14:00`
- `event Hackathon /from 13/11/2024 13:00 /to 14/11/2024 15:00`

## Listing all tasks: `list`

Shows a list of all tasks

Format: `list`

## Finding tasks by keyword: `find`

Find tasks with descriptions that contain the keyword

Format: `find KEYWORDS`

- Only the description will be searched
- Find is case-insensitive e.g. `LUNCH` will match `Lunch`
- Partial words can be matched e.g. `eepy` will match `Sleepy`

## Deleting tasks: `delete`

Delete task by index as displayed by `list`

Format: `delete INDEX`

- Index refers to the index number shown in the displayed task list 
- Index must be a positive integer

Examples:
- `delete 2` deletes the 2nd task in the list displayed by `list`

## Marking tasks: `mark`

Mark a task as done

Format: `mark INDEX`

- Index refers to the index number shown in the displayed task list
- Index must be a positive integer

Examples:
- `mark 2` marks the 2nd task in the list displayed by `list` as completed

## Unmarking tasks: `unmark`

Mark a task as not done

Format: `unmark INDEX`

- Index refers to the index number shown in the displayed task list
- Index must be a positive integer

Examples:
- `unmark 2` marks the 2nd task in the list displayed by `list` as not completed

## View command help: `help`

View command details

Format: `help [COMMAND]`

- `COMMAND` is optional
- Specifying `COMMAND` shows the details for that command only
- Omitting `COMMAND` shows a list of details for all commands

Examples:
- `help`
- `help deadline`

