# Bibi User Guide
<img src="http://domokunx.github.io/ip/Ui.png" alt="Image of Bibi at work" width="300">

Bibi is a **desktop app** for managing tasks, optimized for use via a Command Line Interface (CLI).
A simple Graphical User Interface (GUI) is implemented to enhance user experience.

## Setting Up
1. Ensure you have Java 17 installed in your Computer.
2. Download any of the jar files from [one the releases](https://github.com/Domokunx/ip/releases/tag/A-Jar). Recommended to download the latest.
3. Place the jar into a folder you want to use as the home directory for Bibi.
4. Double-click the jar to execute the app. You should see something like this:

<img src="http://domokunx.github.io/ip/Launch.png" alt="Image of Bibi at Launch" width="300">

## Features
> [!TIP]
> Type the command if you unsure of the correct syntax.  

### Get list of commands: `help`
Shows a list of commands and their behaviours in the chat box.

<img src="http://domokunx.github.io/ip/Help.png" alt="Image of Help command" width="300">

Syntax: `help`

### Get list of tasks: `list`
Shows all tasks and their completion status.

<img src="http://domokunx.github.io/ip/List.png" alt="Image of List Command" width="300">


Syntax: `list`

### Mark task as done: `mark`
Marks the specified task in the task list as done using an 'X'.

<img src="http://domokunx.github.io/ip/MarkBefore.png" alt="Image of unmarked task" height="200">
<img src="http://domokunx.github.io/ip/MarkAfter.png" alt="Image of marked task" height="200">

Syntax: `mark <index>`. Where index is the number in the task list as in seen in `list`.

### Mark task as not done: `unmark`
Marks the specified task in the task list as not done.

<img src="http://domokunx.github.io/ip/MarkBefore.png" alt="Image of marked task" height="200">
<img src="http://domokunx.github.io/ip/UnmarkAfter.png" alt="Image of unmarked task" height="200">

Syntax: `mark <index>`. Where index is the number in the task list as in seen in `list`.

### Adding a ToDo task: `todo`
A ToDo task is a task with description of a task, with no specified deadline.

Command adds a ToDo task, denoted with a **[T]** to the bottom of the list. 
New task count is also echoed for user convenience.

<img src="http://domokunx.github.io/ip/MarkBefore.png" alt="Image of initial task list" height="200">
<img src="http://domokunx.github.io/ip/ToDoAfter.png" alt="Image of task list with a new ToDo task" height="200">

>[!TIP]
> Description of task cannot be blank.

Syntax: `todo <description>`

### Adding a Deadline task: `deadline`
A Deadline task is a task with description of a task, with the specified deadline.

Command adds a Deadline task, denoted with a **[D]** to the bottom of the list.
New task count is also echoed for user convenience.

<img src="http://domokunx.github.io/ip/DeadlineBefore.png" alt="Image of initial task list" height="200">
<img src="http://domokunx.github.io/ip/DeadlineAfter.png" alt="Image of task list with a new Deadline task" height="200">

>[!TIP]
> Deadlines in the format yyyy-mm-dd are auto transcribed to DD-MONTH-YYYY.

Syntax: `deadline <description> /by <deadline>`

### Adding an Event task: `event`
A event task is a task with description of a task, with a specified interval.

Command adds an Event task, denoted with a **[E]** to the bottom of the list.
New task count is also echoed for user convenience.

<img src="http://domokunx.github.io/ip/EventBefore.png" alt="Image of initial task list" height="200">
<img src="http://domokunx.github.io/ip/EventAfter.png" alt="Image of task list with a new ToDo task" height="200">

Syntax: `event <description> /from <time> /to <time>`

### Remove a task: `remove`
Removes a task with specified index from the task list. New task count is echoed for user convenience.

<img src="http://domokunx.github.io/ip/RemoveBefore.png" alt="Image of initial task list" height="200">
<img src="http://domokunx.github.io/ip/RemoveAfter.png" alt="Image of task list after removing 1 task" height="200">

Syntax: `remove <index>`. Where index is the number in the task list as in seen in `list`.

### Find tasks: `find`
Finds all tasks that match the keywords or patterns.

<img src="http://domokunx.github.io/ip/Find.png" alt="Image of find command" width="300">

>[!TIP]
> You can input incomplete keywords, or multiple keywords as well! 

Syntax: `find <keywords>`. 
