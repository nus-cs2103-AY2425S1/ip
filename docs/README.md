# Fret User Guide

<img src='Ui.png'>

Fret, named after the player support AI Fret from [Halo Infinite](https://www.halopedia.org/FRET), is a chatbot that will take note of your tasks and allow you to mark, delete, save tasks and also add other tasks as needed.

The tone of the bot when you give it commands mimics its voice lines in-game and suits its personality well:
> "Fret AI constructs are able to remain eager to tackle the task ahead"

## Table of contents
 * [Quick Start Guide](#quick-start-guide)
 * [Adding Tasks](#adding-tasks)
    * [Todos](#todos)
    * [Deadlines](#deadlines)
    * [Events](#events)
 * [Removing Tasks](#removing-tasks)
 * [Updating Tasks (new)](#updating-tasks-new)
 * [Finding Tasks](#finding-tasks)
 * [Marking Tasks](#marking-tasks)
 * [Other commands](#other-commands)
 * [Help](#help)
 * [Datetimes](#datetimes)
 * [FAQs](#faqs)

## Quick Start Guide
1. Ensure you have Java 17 installed on your computer. You can check this by using the command `java --version`
2. Download the latest jar file from [here]().
3. Copy the file to the folder you want to use as the home folder.
4. Create a folder named `data` in the same folder as your home folder.
5. Open your terminal, navigate to the directory you put the jar file in, and use the following command: `java -jar fret.jar`

## Adding Tasks

There are 3 types of tasks that Fret can handle. Each of them requires a separate set of commands.

### Todos
These tasks just have a description and nothing else.

Command: `todo <description>`

Example: `todo read book`

### Deadlines
These tasks have a description and a deadline. The deadline **must** be a date _and_ time in the following format:

`d/m/yy h[:m]am/pm`

See the [datetime formatting section below](#datetimes) for more details.

Command: `deadline <description> /by <datetime>`

Example: `deadline return book /by 22-9-2025 5:30pm`

### Events
These tasks have a description, a start datetime and an end datetime. The datetimes are formatted as before.

Command: `event <description> /from <startDatetime> /to <endDatetime>`

Example: `event book club /from 20/9/24 6pm /to 20/9/24 9pm`

## Removing Tasks
It is possible to remove a task by refering to the task number: `delete <taskNumber>`

Example: `delete 3` (deletes the 3rd task in the list)

## Updating Tasks (new)
A task can be updated by refering to the task number and providing parameters to update its values.

Command: `update <taskNumber> [/desc <newDescription>] [/from <newFromDatetime>] [/to <newToDatetime>] [/by <newDateTime>]`

Example: `update 2 /from 20/9/24 5:30pm /to 20/9/24 8:30pm` (updates the 2nd task's from- and to-datetime)

The parameters can be in any order.

_Note: Trying to update an invalid paramter (ex: the start datetime of a `Todo`) will fail but not throw an error. It is not possible to add parameters to a task once created, only to change those parameters that already exist._

## Finding Tasks
It is possible to query the list of tasks for a particular keyword/phrase using the `find` command: `find <query>`

This returns a list of all tasks whose descriptions contains the given query.

## Marking Tasks
A task can be marked as completed by referencing the task number (similar to `delete` and `update`): `mark <taskNumber>`

An equivalent command `unmark` can be used to mark a completed task as incomplete: `unmark <taskNumber>`

## Other commands
 * `list`: This command lists out all the tasks that are currently in the task list, along with the task type, completion status, description, and any associated datetime(s).
 * `bye`: This command saves the current task list to the file `data/taskList.txt` and exits the chatbot.

## Help
The `help` command can be used to get the usage of a particular command: `help <commandName>`.

Example: `help event`

Output:
```
Usage: event <task> /from <datetime> /to <datetime>
Datetimes formatted as follows: dd/mm/yy h[:m]am/pm
See user guide for more details: https://dinoman44.github.io/ip
```

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


## FAQs
**Q**: Are tasks automatically saved to the text file?
**A**: No. **Tasks are only saved to the text file upon executing the `bye` command**.

**Q**: I get this message in the terminal when initialising the bot:
> Seems like the datetimes in the file are messed up. Please delete the task file and try again.

**A**: The easiest way to deal with this is to delete `taskList.txt` from the `data` folder and re-run. Your tasks can be saved manually elsewhere by you. You could also try reformatting the text file in an attempt to keep the tasks saved with the bot. Just make sure the content of `data/taskList.txt` matches the following:
```
----TASKS----
[T][ ] read book
[D][X] return book (by: 2025-09-22T17:30)
[E][ ] book club (from: 2024-09-20T18:00, to: 2024-09-20T21:00)
```
Note that there is no leading newline character after the last task, and note the different datetime format. [X] indicates a task marked as completed, [T] for a todo, [D] for a deadline and [E] for an event. Spaces and dashes should be exactly in the right places.

**Q**: I get this message in the terminal when initialising the bot:
> Error with task file. Please delete the task file and try again.

**A**: You may not have placed a `data` folder in the _same_ folder as the `.jar` file. Make sure that there is such a folder. The directory tree should look like this:
```
Root
 |
 |---some_folder
...
 |---data/[taskList.txt]
 |---fret.jar
...
```