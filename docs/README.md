# MoiMoi User Guide

![Screenshot of the user interface.](Ui.png)

MoiMoi is a **desktop app designed for managing tasks,
optimized for efficient use through a Command Line Interface (CLI)**
while offering the advantages of a Graphical User Interface (GUI).
If you're a fast typist, MoiMoi helps you manage tasks more quickly than traditional GUI-based apps.

## Getting started

1. Ensure that `Java 17` is installed in your computer.
2. Download the `.jar` file of the latest release, from [here](https://github.com/wkxcass/ip/releases/).
3. Move the `.jar` file into an empty folder.
4. Open a command window in that folder (right-click on the folder, then select `Open in Terminal`).
5. Run the command `java -jar "moimoi.jar"`.

## Adding a todo task: `todo`

_Adds a todo task to the task list._

**Format:** `todo <description>`

**Example:**

```
todo revise CS2103T
```

## Adding a deadline task: `deadline`

_Adds a deadline task to the task list._

**Format:** `deadline <description> /by <deadline>`

* `<deadline>` should be in the form of `yyyy-MM-dd HH:mm`.

**Example:**

```
deadline iP submission /by 2024-09-20 23:59
```

## Adding an event task: `event`

_Adds an event task to the task list._

**Format:** `event <description> /from <start> /to <end>`

* Both `<start>` and `<end>` should be in the form of `yyyy-MM-dd HH:mm`.
* `<start>` should not be later than `<end>`.

**Example:**

```
event Culture Night /from 2024-09-11 19:30 /to 2024-09-11 21:30
event system cutover /from 2024-09-17 00:00 /to 2024-09-17 00:00
```

## Adding a period task: `period`

_Adds a period task to the task list._

**Format:** `period <description> /for <period>`

* `<period>` should be a positive integer or decimal, representing the period in hours.

**Example:**

```
period practise Schubert /for 1
period exercise /for 1.5
```

## Deleting a task: `delete`

_Deletes a task from the task list._

**Format:** `delete <task index>`

**Example:**

```
delete 1
```

## Marking a task: `mark`

_Marks a task as done._

**Format:** `mark <task index>`

**Example:**

```
mark 2
```

## Unmarking a task: `unmark`

_Unmarks a task (i.e., marks a task as undone)._

**Format:** `unmark <task index>`

**Example:**

```
unmark 3
```

## Listing all tasks: `list`

_Lists all tasks from the task list._

**Format:** `list`

## Listing tasks for a date: `schedule`

_Lists all tasks from the task list, that occur on a specific date._

**Format:** `schedule <date>`

* `<date>` should be in the form of `yyyy-MM-dd`.

**Example:**

```
schedule 2024-09-30
```

## Finding tasks by keyword: `find`

_Lists all tasks from the task list, that contain a keyword in their descriptions._
_The search is **case-insensitive**._

**Format:** `find <keyword>`

**Example:**

```
find iP
```

## Exiting the program: `bye`

_Exits the program._

**Format:** `bye`

## Saving task list data
_Task list data is automatically saved to the hard disk, after any command that makes changes to it.
No manual saving is required._

## Remarks

* Extra parameters for commands that do not require them (e.g., `list` and `exit`) will be ignored.
For example, `list abc` will be interpreted as `list`.