# Brock User Guide
![Screenshot of Brock chatbot](Ui.png) 

Brock is a slightly unhinged chatbot, developed by a slightly unhinged individual. 
It allows the user to effectively manage their tasks! 

## Quick Start
1. Ensure your device has Java `17` or above installed.
2. Download the `.jar` file from [here](https://github.com/YangQF2002/ip/releases/tag/A-Release) 
3. Copy the `.jar` file to the folder you wish to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder that the `.jar` file is located in.
5. Type `java -jar brock.jar` to run the chatbot!

## Features 

### Add a todo task: `todo`
Adds a new todo task that the chatbot tracks.  
Format: `todo <description>`  
Example: `todo borrow book`  

### Add a deadline task: `deadline`
Adds a new deadline task that the chatbot tracks.
Format: `deadline <description> /by <due-date> <?due-time>`
- Due time is optional
- Due date must be in yyyy-mm-dd format
- Due date must not be before today
- Due time must be in 24hr format

Example: `deadline return book /by 2024-10-10 2300`

### Add an event task: `event`
Adds a new event task that the chatbot tracks.   
Format: `event <description> /from <start-date> <?start-time> /to <end-date> <?end-time>`
- Same general restrictions to date and time as `deadline` command

Example: `event Japan trip! /from 2024-10-10 /to 2024-10-20`

### Mark a task as complete: `mark`
Marks the specified task as being complete.  
Format: `mark <task-number>`
- The task number specified must exist 

Example: `mark 1`

### Un-mark a task as uncompleted: `unmark`
Unmarks the specified task as being uncomplete.   
Format: `unmark <task-number>`
- The task number specified must exist 

Example: `unmark 1`

### List all existing tasks: `list`
Lists all current tasks.   
Format: `list`
- No additional parameters

Example: `list`

### Find all matching tasks: `find`
Finds and shows all tasks whose description contains the specified keyword.  
Format: `find <keyword>`
- Currently, only a single keyword is supported
- Description must contain that exact keyword (instead of partial substrings) 

Example: `find borrow`

### Delete an existing task: `delete`
Deletes an existing task, meaning its no longer tracked by the chatbot.  
Format: `delete <task-number>`
- The task number specified must exist 

Example: `delete 1`

### Undo command: `undo`
Undoes the previous valid command, if possible.
As certain commands like `list` and `find` for example can't really be undone.   
Format: `undo`
- No additional parameters

Example: `undo`

### Exiting the chatbot: `bye`
Exits the chatbot. No worries, your existing tasks will be saved!   
Format: `bye`
- No additional parameters

Example: `bye`



