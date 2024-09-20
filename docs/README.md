# Avo User Guide

Avo is a chatbot for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Avo can get your task management done faster than traditional GUI apps.
![ui image](Ui.png)

## Quick start
1.  Ensure you have Java `17` or above installed in your Computer.
2. Download the latest .jar file from [here](https://github.com/ZweZeya/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for Avo.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar avo.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.

## Features
### Adding tasks
Avo supports 3 types of tasks: ToDo, Deadline and Event. 
#### ToDo
Basic task with a name.  

Format: `todo {NAME}`  

Examples:
- `todo read a book`

#### Deadline
Task with a deadline.  

Format: `deadline {NAME} /by {DATE}`
- `DATE` format in `yyyy-mm-dd HH:mm`

Examples:
- `todo return a book /by 2024-12-27 18:00`

#### Event
Task with a start and end time.  

Format: `event {NAME} /from {START_DATE} /to {END_DATE}`
- `START_DATE` and `END_DATE` format in `yyyy-mm-dd HH:mm`

Examples:
- `event project meeting /from 2024-12-05 18:00 /to 2024-12-27 18:00`

### Deleting tasks
Deletes the specified task.

Format: `delete {INDEX}`
- Deletes the task at the specified `INDEX`. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...  

Examples:  
- `list` followed by `delete 2` deletes the 2nd task.

### Viewing tasks
Shows a list of all tasks.  

Format: `list`
### Changing task status
#### Mark a task
Marks the specified task as completed.  

Format: `mark {INDEX}`

- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `mark 2` marks the 2nd task.

#### Unmark a task
Unmarks the specified task as uncompleted.

Format: `unmark {INDEX}`

- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `unmark 2` unmarks the 2nd task.

### Searching tasks
#### Search by task name
Searches all tasks that matches the prefix on `NAME`

Format: `find {NAME}`

Examples:
- `find book`

#### Search by date
Searches all tasks that occurs on `DATE`  

Format: `on {DATE}`
- `DATE` format in `yyyy-mm-dd`

Examples:
- `on 2024-12-05`

#### Viewing help
Shows a message explaining how to access the help page.  

Format: `help`

#### Closing the application
Closes the GUI

Format: `exit`
