# Llama Project

This a chatbot named after the great land animal _Llama_. It is able to keep track of your tasks efficiently!
**It is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). If you can type fast, Llama can get your task management done faster than 
traditional GUI apps.**



Given below are instructions on how to use it.

// Product screenshot goes here

// Product intro goes here

## Quick Start

### Setting up
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest release [here](https://github.com/bmanara/ip/releases).
3. Copy the file to the folder you want to use as your _home folder_ for your Llama chatbot.
4. Open a command terminal, `cd` into the folder the `.jar`file is located at and use the command `java -jar Llama.jar`.
5. A GUI will appear after a few seconds.
6. Play and interact with the chatbot!
7. Refer to the Features below for details of all commands available.

---
## Features
> [!NOTE]
> Notes about the command format:<br>
> 1. Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo borrow book`.
> 2. `DATE` must always be in the format `YYYY-MM-DD HH:mm`.
      e.g. in `deadline DESCRIPTION /by DATE`, it should be `deadline return book /by 2024-11-23 12:00`.
> 3. Parameters must be in the specified order.<br>
      e.g. if the command specifies `DESCRIPTION from/DATE`, it needs to follow this format strictly.
> 4. If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


### Adding todos: `todo`
Adds a task of type `todo` to the task list.
Format: `todo DESCRIPTION`
Examples: `todo borrow book`

### Adding deadlines: `deadline`
Adds a deadline of type `deadline` to the task list.
Format: `deadline DESCRIPTION /by DATE`
Examples: `deadline return book /by 2024-11-23 12:00`

### Adding events: `event`
Adds an event of type `event` to the task list.
Format: `event DESCRIPTION /from DATE /to DATE`
Examples: `event project meeting /from 2024-11-23 12:00 /to 2024-11-23 14:00`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.
Format: `list`
Sample Output: <add screenshot here>

### Deleteing a task: `delete`
Deletes a task from the task list.
Format: `delete TASK_NUMBER`
Example: `delete 3` deletes the 3rd task in the task list. 

### Marking a task as done: `mark`
Marks a task as done in the task list.
Format: `mark TASK_NUMBER`
Example: `mark 3` marks the 3rd task in the task list as done.

### Unmarking a task as done: `unmark`
Unmarks a task as done in the task list.
Format: `unmark TASK_NUMBER`
Example: `unmark 3` unmarks the 3rd task in the task list as done.

### Locating tasks by name: `find`
Finds tasks whose descriptions contain the given keyword.
Format: `find KEYWORD`
Example: `find book` returns a list of tasks with the word `book` in their description.

### Create a tag: `create`
Creates a tag that can be used to tag a task.
Format: `create TAG_NAME`
Example: `create important`

### Tag a task: `tag`
Tags a task with an already existing tag.
Format: `tag TASK_NUMBER /with TAG_NAME`

### Untag a task: `untag`
Untags a task with an already existing tag.
Format: `untag TASK_NUMBER`

### Exiting the program: `bye`
Exits the program.
Format: `bye`

---
##  Data management

### Saving the data
Llama chatbot will automatically save data on tasks and tags after any command that changes data. There is no need to save manually.

### Editing the data file
Llama data files can be located in [JAR file location]/data/tagFile.txt and [JAR file location]/data/taskFile.txt. 
