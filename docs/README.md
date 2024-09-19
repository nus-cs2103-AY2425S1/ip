# Revir User Guide
Revir is a personal chatbot that helps you manage your tasks effectively. From basic to-dos to deadlines and events, Revir handles all your tasks effortlessly. It responds to user commands via a straightforward, user-friendly interface. Simply type your commands, and Revir will take care of your tasks and deadlines!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

1. Download the latest `.jar` file.

1. Copy the file to the folder you want to use as the _home folder_ for installation.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar revir.jar` command to run the application.<br>
  A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
  ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will display the list of tasks.<br>
  Some example commands you can try:

  * `list` : Lists all tasks.

  * `todo task` : Adds a todo task with description `task` to the task list.

  * `delete 3` : Deletes the 3rd task in the task list.

  * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Any task description`.

## Adding todo tasks
You can add a simple todo task.

**Command:** `todo DESCRIPTION`

**Example:** `todo complete homework`

**Expected Outcome:**

Task added: [T][ ] complete homework

## Adding deadline tasks

You can add a task that has a deadline.

**Command:** `deadline DESCRIPTION /by DATE TIME`

**Example:** `deadline submit project /by 1/1/2024 1234`

**Expected Outcome:**

Task added: [D][ ] submit project (by: 1/1/2024 1234)

## Adding Event tasks

You can add an event that starts and ends at specific times.

**Command:** `event DESCRIPTION /by DATE TIME /to DATE TIME`

**Example:** `event team meeting /from 1/1/2024 1234 /to 1/1/2024 1235`

**Expected Outcome:**

Task added: [E][ ] team meeting (from: 1/1/2024 1234 to: 1/1/2024 1234)

### Listing all tasks : `list`

Shows a list of all tasks in the list.

Format: `list`

### Searching for tasks by description: `find`

Finds tasks whose description contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-sensitive.
* Only the task description is searched.
* Tasks matching at least one keyword will be returned (i.e. `OR` search).

Examples:
* `find homework` returns `homework submission task` and `homework revision task`

## Marking a Task as Done

You can mark a specific task as completed.

**Command:** mark INDEX

**Example:** mark 1

**Expected Outcome:**

Task marked as completed: [T][X] complete homework

## Unmarking a Task

You can mark a previously completed task as incomplete.

**Command:** unmark [task number]

**Example:** unmark 2

**Expected Outcome:**

Task marked as incomplete: [T][ ] complete homework

### Deleting a task : `delete`

Deletes a task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task shown in the list.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and copy the data from `./data/tasks.dat` to preserve your data.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo ​DESCRIPTION` <br> e.g., `todo thing`
**Deadline** | `deadline ​DESCRIPTION /by DATE` <br> e.g.`deadline ​DESCRIPTION /by DATE` 
**Event** | `event ​DESCRIPTION /from STARTDATE /to ENDDATE` <br> e.g.`event ​DESCRIPTION /from STARTDATE /to ENDDATE`
**Mark** | `mark INDEX`<br> e.g., `mark 3`
**Unmark** | `unmark INDEX`<br> e.g., `unmark 3`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD`<br> e.g., `find submission`
**List** | `list`