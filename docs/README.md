# Azir User Guide

![Ui.png](Ui.png)

Azir is a **task management chatbot that allows you 
to easily track your tasks**, optimized for use 
via a Command Line Interface. If you can type fast, 
Azir will be ***WAY FASTER*** than traditional task tracking apps!

:information_source: **Notes about the command format:**

- Words in `UPPERCASE` are compulsory parameters to be supplied by user
- Tasks with duplicate task descriptions will not be added

## How to start
1. Ensure you have Java 17 or above installed in your Computer. 
2. Download the latest .jar file from here. 
3. Open a terminal and run `java -jar Azir.jar` 
in the folder your jar file is in

## Features

### Viewing task list: `list`
Shows the list of tasks currently in the list

Format: `list`   

### Mark completed task: `mark`
Marks a task as completed in the list

Format: `mark TASKNUMBER`

### Mark incomplete task: `unmark`
Marks a task as incomplete in the list

Format: `unmark TASKNUMBER`

### Delete task: `delete`
Deletes a task from the list

Format: `delete TASKNUMBER`

### Add Todo task: `todo`
Adds a todo task to the list

Format: `todo TASKDESCRIPTION`

### Add deadline task: `deadline`
Adds a deadline task to the list

Format: `deadline TASKDESCRIPTION /by DATE`
- DATE should be of the format yyy-mm-dd

### Add event task: `event`
Adds an event task to the list

Format: `event TASKDESCRIPTION /from STARTDAY /by ENDDAY`
- No restrictions on format of STARTDAY or ENDDAY

### Locating tasks by description: `find`
Finds tasks that match the description keyword

Format: `find KEYWORD`
- DATE should be of the format yyy-mm-dd