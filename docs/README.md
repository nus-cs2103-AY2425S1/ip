# FRIDAY User Guide

<img width="959" alt="image" src="https://github.com/user-attachments/assets/7b29a540-975b-47cf-ac86-64d9d9793d8a">

Introducing FRIDAY, your personalized digital assistant inspired by Duke! FRIDAY maintains all the core functionalities you loved from Duke—managing tasks, to-dos, deadlines, and events—but with an exciting new twist: an archive feature. Now, you can neatly store away completed or past tasks, allowing you to focus on what's most important without losing track of your progress. FRIDAY’s intuitive interface, combined with its powerful organization tools, ensures you stay on top of everything while keeping your workspace clutter-free. Stay organized, stay efficient, and let FRIDAY handle the rest!

## Quick start
1. Ensure that you have Java `17` and above installed in your computer
2. Download the `.jar` file from [here](https://github.com/volleyballkickedme/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for your FRIDAY bot
4. Open a command terminal, `cd` into the folder you put the jar file in and use the `java -jar FRIDAY.jar` command to run the application. A GUI similar to the one below should pop up.
<img width="299" alt="image" src="https://github.com/user-attachments/assets/ce873c25-0c98-448b-80d0-87908c94b031">

5. Type the command in the text field and press Send to execute it.
Some sample commands you can try are:

 - `list`: displays the list of tasks that the bot is currently keeping track of.  
 - `todo TASK_DESCRIPTION`: adds a todo task to the list.  
 - `archive`: archives all current tasks.  
 - `bye` exits the program and saves all tasks to storage file.  
6. Refer below for the full list of commands.  

## Features

## Adding a todo task `todo`
Adds a todo task to your list of tasks.  
Format: `todo TASK_DESCRIPTION`

## Adding an event task
Adds an event task with a start and end time to your list of tasks.  
Format: `event TASK_DESCRIPTION/START_TIME to END_TIME`

## Adding a deadline task
Adds a deadline task with a due date to your list of tasks.  
Format: `deadline TASK_DESCRIPTION/DATE(YYYY-MM-DD)`

## Deleting a task
Deletes a task from the list, specified by the task number.  
Format: `delete TASK_NUMBER`

## Changing the completion status of a task
`mark`: marks a task as complete.  
`unmark`: marks a task as incomplete.  
Format: `mark TASK_NUMBER`, `unmark TASK_NUMBER`

## Displaying all current tasks
Displays a list of current tasks that is being tracked by the program.  
Format: `list`

## Searching for a task
Displays a list of tasks that have descriptions which contain the keyword that the user inputs
Format: `search KEYWORD`

## Archiving a task
Removes all tasks from the current list, and stores them locally in a text file named "archive"
Format: `archive`
