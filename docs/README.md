# RoTodo User Guide

<img src="Ui.png" alt="Representative screenshot of RoTodo's UI" width="400"/>

RoTodo is a simple task managing bot, optimized for use via a Command Line Interface while still having the benefits of a simple Graphical User Interface (GUI). If you can type fast, RoTodo can help you manage your tasks faster than traditional GUI apps.

- [Quick Start](#quick-start)
- [Features](#features)
  1. [Viewing help](#viewing-help-help): `help`
  2. Adding tasks
     - [Add Todo task](#adding-todo-task-todo): `todo`
     - [Add Deadline task](#add-deadline-task-deadline): `deadline`
     - [Add Event](#add-event-event): `event`
  3. [Listing all tasks](#listing-all-tasks-list): `list`
  4. [Deleting tasks](#deleting-tasks-delete): `delete`
  5. Change task status
     - [Mark task as done](#mark-tasks-as-done-mark): `mark`
     - [Mark task as undone](#mark-tasks-as-undone-unmark): `unmark`
  6. [Find task by keyword](#find-tasks-by-keyword-find): `find`
  7. [Exit the application](#exits-the-application-bye): `bye`
  8. [Saving the tasklist]()
- [FAQ](#faq)
- [Command Summary](#command-summary)
---
## Quick Start
1. Ensure you have `Java 17` or above installed in your Computer.
2. Download the latest `.jar` file [here](https://github.com/k-hian/ip/releases)
3. Open a command terminal, `cd` to the file's directory, and use `java -jar RoTodo.jar` command to run the application.<br>
Use `java -jar RoTodo.jar <name>` to customize the username.
4. Type the commands in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will print the help menu.
5. Refer to the [Features](#features) below for details of each command.
---
## Features
> [!NOTE] Notes about command format:
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
e.g. in `todo DESCRIPTION is a parameter which can be used as add n/John Doe.
>
> - Extraneous parameters for commands that do not take in parameters (such as help, list and bye) will be ignored.<br>
e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### **Viewing help: `help`**
Shows a help message explaining the commands described in this guide.

<img src="help.png" alt="Sample help message output" width="400"/>

**Format:** `help`

### **Adding Todo task: `todo`**
Adds a Todo task to the tasklist. A Todo task consist of only a description.

**Format:** `todo DESCRIPTION`

Examples:
- `todo Collect my laundry from the dry cleaner`

### **Adding Todo task: `todo`**
Adds a Todo task to the tasklist. A Todo task consist of only a description.

**Format:** `todo DESCRIPTION`

**Output:** RoTodo will add the task to the tasklist, and also print the number of task in the tasklist.

Examples:
- `todo Collect my laundry from the dry cleaner`

### **Add Deadline task: `deadline`**
Adds a Deadline task to the tasklist. A Deadline task consist of a description and a due datetime to complete by.

**Format:** `deadline DESCRIPTION /by DD/MM/YYYY HHMM`

**Output:** RoTodo will add the task to the tasklist, and also print the number of task in the tasklist.

Examples:
- `deadline Complete Written Assignment 3 /by 17/10/2024 2359`
- `deadline Finish writing script for CS2101 /by 4/9/2024 1900`

### **Add Event: `event`**
Adds a Event to the tasklist. An Event consist of a description, a start and end datetime.

**Format:** `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`

**Output:** RoTodo will add the task to the tasklist, and also print the number of task in the tasklist.

Examples:
- `event JB trip with friends /from 25/09/2024 0800 /to 25/09/2024 2300`
- `event CS3230 midterms /from 5/10/2024 1400 /to 5/10/2024 1530`

### **Listing all tasks: `list`**
Shows a list of all tasks, with the type and  (done) status of each task.

**Format:** `list`

### **Deleting tasks: `delete`**
Delete the specified task from the tasklist.

**Format:** `delete INDEX`
- deletes the task at the specified `INDEX`.
- The index refers to the index number as shown in the displayed tasklist from `list`.
- The index **must be a positive integer** 1, 2, 3, ...

**Output:** RoTodo will delete the task from the tasklist, and also display information of the task deleted.

Examples:
- `delete 1` deletes the 1st task in the list.

### **Mark tasks as done: `mark`**
Mark the specified task in the tasklist as done.

**Format:** `mark INDEX`
- marks the task at the specified `INDEX` as done.
- The index refers to the index number as shown in the displayed tasklist from `list`.
- The index **must be a positive integer** 1, 2, 3, ...

**Output:** RoTodo will mark the task from the tasklist as done, and also display information of the task.

Examples:
- `mark 2` marks the 2nd task in the list as done.

### **Mark tasks as undone: `unmark`**
Mark the specified task in the tasklist as undone.

**Format:** `unmark INDEX`
- marks the task at the specified `INDEX` as undone.
- The index refers to the index number as shown in the displayed tasklist from `list`.
- The index **must be a positive integer** 1, 2, 3, ...

**Output:** RoTodo will mark the task from the tasklist as undone, and also display information of the task.

Examples:
- `unmark 2` marks the 2nd task in the list as undone.

### **Find tasks by keyword: `find`**
Find and display the list of tasks that contains the keyword.

**Format:** `find KEYWORD`

**Output:** RoTodo will display a list of task that contains the keyword. Tasks that contain the exact word will be displayed before tasks that only contain the keyword as a substring.<br>
E.g. `keyword = "task"`, then `"do task" > "dotask"`

Examples:
- `find feed` will find tasks that contain the word "feed" or contains the substring "feed".

### **Exits the application: `bye`**
Exits the application.

**Format:** `bye`

### **Saving the tasklist**
Tasklist is automatically saved to a data file after any modification command (add, delete, mark, unmark)

---
## FAQ
**Q:** How do I transfer my tasklist to another computer?
**A:** Download the application in the other computer and overwrite the data file it creates with the file that contains the data of your previous tasklist.

---
## Command Summary
|Action|Format, Example|
|---|---|
|Add Todo|`todo DESCRIPTION`<br>e.g. `todo go to supermarket`|
|Add Deadline|`deadline DESCRIPTION /by DD/MM/YYYY HHMM`<br>e.g. `deadline homework 1 /by 19/09/2024 2359`|
|Add Event|`event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`<br>e.g. `event slumber party /from 18/9/2024 1900 /to 19/9/2024 0100`|
|List tasks|`list`|
|Delete tasks|`delete INDEX`<br>e.g. `delete 2`|
|Mark as done|`mark INDEX`<br>e.g. `mark 2`|
|Mark as undone|`unmark INDEX`<br>e.g. `unmark 2`|
|Find task|`find KEYWORD`<br>e.g. `find cats`|
|Exit application|`bye`|