# Fret User Guide

<img src=''>

Fret, named after the player support AI Fret from [Halo Infinite](https://www.halopedia.org/FRET), is a chatbot that will take note of your tasks and allow you to mark, delete, save tasks and also add other tasks as needed.

The tone of the bot when you give it commands mimics its voice lines in-game and suits its personality well:
> "Fret AI constructs are able to remain eager to tackle the task ahead"


## Adding Tasks

There are 3 types of tasks that Fret can handle. Each of them requires a separate set of commands.

### Todos
These tasks just have a description and nothing else.

Command: `todo <description>`

### Deadlines
These tasks have a description and a deadline. The deadline **must** be a date _and_ time in the following format:

`d/m/yy h[:m]am/pm`

See the datetime formatting section below for more details.

Command: `deadline <description> /by <datetime>`

### Events
These tasks have a description, a start datetime and an end datetime. The datetimes are formatted as before.

Command: `event <description> /from <startDatetime> /to <endDatetime>`

## Removing Tasks
It is possible to remove a task by refering to the task number.

Command: `delete <taskNumber>`

## Updating Tasks (new)
A task can be updated by refering to the task number and providing parameters to update its values.

Command: `update <taskNumber> [/desc <newDescription>] [/from <newFromDatetime>] [/to <newToDatetime>] [/by <newDateTime>]`

The parameters can be in any order.

Note: Trying to update an invalid paramter (ex: the start datetime of a `Todo`) will fail but not throw an error. It is not possible to add parameters to a task once created, only to change those parameters that already exist.

## Finding Tasks
It is possible to query the list of tasks for a particular keyword/phrase using the `find` command:

`find <query>`

This returns a list of all tasks whose descriptions contains the given query.

## Marking Tasks
A task can be marked as completed by referencing the task number (similar to `delete` and `update`).

Command: `mark <taskNumber>`

An equivalent command `unmark` can be used to mark a completed task as incomplete: `unmark <taskNumber>`

## Other commands
 * `list`: This command lists out all the tasks that are currently in the task list, along with the task type, completion status, description, and any associated datetime(s).
 * `bye`: This command saves the current task list to the file `data/taskList.txt` and exits the chatbot.

## Datetimes
All datetimes need to be entered in the following format:
`d/m/y h[:m]am/pm`

1. The day, month, year, hours and minutes must be numerical and in the given order above.
2. The year can either be a 4-digit number (in which case it is taken as-is) or a 2-digit number (in which case it is taken as 20XX)
3. The meridiem (am/pm) are compulsory to add.
4. The minutes are optional, but if added need to be separated from the hours by a colon (:)
5. Instead of `/`, the following separators are also allowed (for the date): `|`, `-`, `_`, `\`, ` `
6. The date has to come first before the time, and has to be separated by a space OR a `T`

Below are some valid datetimes:
 * `5-10-2021 10pm` - 10pm on 5th October 2021
 * `4-11-24 11:30am` - 11:30am on 4th November 2024
 * `09|01|2054 12pm` - 12pm on 9th January 2054
 * `31\8\2024 08:00pm` - 8pm on 31st August 2024
 * `9_10_11T7 am` - 7am on 9th October 2011