# BotManager User Guide

![Ui.png](Ui.png)

BotManager is a chatbot that will help you track and manage your tasks.

## Features

Notes about the command format:<br>

* Words in `<>` are the parameters to be supplied by the user.<br>
e.g. in `todo <description>`, `<description>` is a parameter.

* The command format must be followed exactly, and all arguments must be in the order given.
* All dates have to be entered in the format `yyyy-mm-dd`.
e.g. `2024-01-01`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

## Adding todos: `todo`
The `todo` command allows you to add basic tasks with only a description to the list.

<b>Format</b>: `todo <description>`

<b>Example</b>: `todo Do homework`

<b>Expected Outcome</b>: 
```
Added task: [T][ ] Do homework
You have 1 task currently.
```

## Adding deadlines: `deadline`
The `deadline` command allows you to add tasks which need to be completed by a set date to the list.

<b>Format</b>: `deadline <description> /by <end date>`

<b>Example</b>: `deadline Submit project /by 2024-10-01`

<b>Expected Outcome</b>:
```
Added task: [D][ ] Submit project (by: Oct 1 2024)
You have 2 tasks currently.
```

## Adding events: `event`
The `event` command allows you to add tasks which have a start and end date to the list.

<b>Format</b>: `event <description> /from <start date> /to <end date>`

<b>Example</b>: `event Holiday /from 2024-10-01 /to 2024-10-08`

<b>Expected Outcome</b>:
```
Added task: [E][ ] Holiday (from: Oct 1 2024, to: Oct 8 2024)
You have 3 tasks currently.
```

## Listing tasks: `list`
The `list` command lists all the tasks currently stored by BotManager.

<b>Format</b>: `list`

<b>Expected Outcome</b>:
```
You have 3 tasks currently.
1. [T][ ] Do homework
2. [D][ ] Submit project (by: Oct 1 2024)
3. [E][ ] Holiday (from: Oct 1 2024, to: Oct 8 2024)
```
## Marking tasks as done: `mark`
The `mark` command allows you to mark tasks as done.

<b>Format</b>: `mark <task index>`

<b>Example</b>: `mark 1`

<b>Expected Outcome</b>:
```
Marked task as done: [T][X] Do homework
```

## Marking tasks as undone: `unmark`
The `unmark` command allows you to mark tasks as undone.

<b>Format</b>: `unmark <task index>`

<b>Example</b>: `unmark 1`

<b>Expected Outcome</b>:
```
Marked task as undone: [T][ ] Do homework
```

## Deleting tasks: `delete`
The `delete` command allows you to delete a task from the list.

<b>Format</b>: `delete <task index>`

<b>Example</b>: `delete 1`

<b>Expected Outcome</b>:
```
Deleted task: [T][ ] Do homework
```

## Finding tasks: `find`
The `find` command allows you to find tasks with certain words/phrases.

<b>Format</b>: `find <search string>`

<b>Example</b>: `find project`

<b>Expected Outcome</b>:
```
1 matching task found!
1. [D][ ] Submit project (by: Oct 1 2024)
```

## Viewing schedule: `schedule`
The `schedule` command allows you to view all tasks due/occurring on a particular date

<b>Format</b>: `schedule <date>`

<b>Example</b>: `schedule 2024-10-01`

<b>Expected Outcome</b>:
```
2 tasks on Oct 1 2024!
1. [D][ ] Submit project (by: Oct 1 2024)
3. [E][ ] Holiday (from: Oct 1 2024, to: Oct 8 2024)
```

## Viewing help: `help`

The `help` command provides a list of all available commands and their usages.

<b>Format</b>: `help`

<b>Expected outcome</b>:
```
list                                              List all tasks
todo <name>                                       Add a todo with name
deadline <name> /by <end date>                    Add a deadline with name and end date 
event <name> /from <start date> /to <end date>    Add an event with name, start date and end date
mark <task index>                                 Mark task with corresponding task index as done
unmark <task index>                               Mark task with corresponding task index as undone
delete <task index>                               Delete task with corresponding task index
find <search string>                              Find tasks that match the given search string
schedule <date>                                   List the schedule for a particular date
help                                              View all available commands
bye                                               Exit BotManager
```
## Exiting BotManager: `bye`
The `bye` command will exit BotManager.

<b>Format</b>: `bye`

<b>Expected outcome</b>:
```
Goodbye! Hope to see you again soon!
```

BotManager will automatically exit shortly after displaying this message

## Saving data
Task data is automatically written to the data file after each command. There is no need to manually save data.

## Editing the data file
Task data is saved in a text file at `[JAR file location]/data/tasks.txt`.<br>
Advanced users are welcome to update data directly by editing the text file. <br>
<b>Note: BotManager will only load data in the valid format. All invalid data will be discarded in the next autosave. (i.e. after any command is ran)