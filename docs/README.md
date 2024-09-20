ChatGPT is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ChatGPT can get your task list management done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

2. Download the latest `.jar` file from [here]().

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar chatgpt-1.0.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](docs/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will display the dummy data.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `todo Read a book /note Preferably Fantasy` : Adds a task to `Read a book` to the task list.

    * `delete 3` : Deletes the 3rd task shown in the current list.

    * `mark 2` : Marks the 2nd task as completed indicated by the [x].

    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo Grocery Shopping`.

* Items in square brackets are optional.<br>
  e.g `/by DEADLINE [/note NOTE]` can be used as `/by 2024-09-20 2359 /note Urgent` or as `/by 2024-09-20 2359`.

* Parameters must follow in the order given.<br>
  e.g. if the command specifies `/by DEADLINE /note NOTE`, `/note NOTE /by DEADLINE` is not acceptable.

</div>

### Adding a To Do Task: `todo`

Adds a Task to do without deadline or time periods to the list.

Format: `todo TASK [/note NOTE]`


Examples:
* `todo Watch the new Netflix show /note Watch with Jane`
* `todo Learn a new skill`

### Adding a Task with a deadline: `deadline`

Adds a Task to do with a deadline to the list.

Format: `deadline TASK /by DEADLINE [/note NOTE]`

<div markdown="span" class="alert alert-primary">:exclamation: **To note:**
Deadline should be in the format of YYYY-MM-DD HHmm, e.g 2024-09-20 2359
</div>

Examples:
* `deadline Submit iP /by 2024-09-20 2359 /note Extremely Urgent`
* `deadline Buy a gift /by 2024-10-21 1400`

### Adding an event: `event`

Adds an event with a time period to the list.

Format: `event TASK /from StartDate /to EndDate [/note NOTE]`

<div markdown="span" class="alert alert-primary">:exclamation: **To note:**
Dates should be in the format of YYYY-MM-DD HHmm, e.g 2024-09-20 2359
</div>

Examples:
* `event Ivan's Party /from 2024-10-20 1700 /to 2024-10-20 2200 /note Buy a gift`
* `event Hall BBQ /from 2024-11-02 1800 /to 2024-11-03 0100`

### Listing all tasks : `list`

Shows a list of all tasks added.

Format: `list`

### Showing details of a tasks : `show`

Shows the detail of a specified task from the list.

Format: `show INDEX`

### Marking a task as complete : `mark`

Marks a specified task from the list as complete.

Format: `mark INDEX`

* If the task has already been marked as complete, a message will be shown instead

### Marking a task as not complete : `unmark`

Marks a specified task from the list as not complete.

Format: `unmark INDEX`

* If the task has already been marked as not complete, a message will be shown instead

### Locating tasks by keywords: `find`

Finds Task whose name/description contains the given keywords.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `ip` will not match `iP`
* The order of the keywords does matter. e.g. `Submit iP` will not match `iP Submit`
* Dates can also be searched.

Examples:
* `find iP` returns `Deadline task to Submit iP`<br>
  ![result for 'find iP'](docs/findiPResults.png)

### Deleting a task : `delete`

Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the overall task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd task in the task list.
* `find Read` followed by `delete 1` deletes the 1st task in the overall task list.


### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

ChatGPT data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ChatGPT data is saved automatically as a text file `[JAR file location]/data/saveData.txt`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Certain edits can cause ChatGPT to behave in unexpected ways (e.g., if a value entered is outside of the expected range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ChatGPT home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When editing the save file directly**, if you change the number of arguments separated by `|` the program will crash

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**ToDo** | `todo TASK [/note NOTE]` <br> e.g., `todo Watch the new Netflix show /note Watch with Jane`
**Deadline** | `deadline TASK /by DEADLINE [/note NOTE]` <br> e.g., `deadline Submit iP /by 2024-09-20 2359 /note Extremely Urgent`
**Event** | `event TASK /from StartDate /to EndDate [/note NOTE]` <br> e.g., `event Ivan's Party /from 2024-10-20 1700 /to 2024-10-20 2200 /note Buy a gift`
**List** | `list`
**Show** | `show INDEX`<br> e.g., `show 1`
**Mark** | `mark INDEX`<br> e.g., `mark 2`
**Unmark** | `unmark INDEX`<br> e.g., `unmark 2`
**Find** | `find KEYWORD`<br> e.g., `find iP`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Bye** | `bye`
