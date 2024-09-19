# Katheryne User Guide

![Ui.png](https://github.com/YANYAN-w/ip/blob/master/docs/Ui.png)

**Ad astra abyssosque!**

Meet Katheryne, your trusted receptionist from the Adventurers' Guild, now here to help you navigate through the daily quests of life! 
Whether it's managing tasks, setting deadlines, or organizing events, Katheryne is always ready to assist you with utmost efficiency and a smile.

## Getting Started
1. Ensure that you have Java 17 or above in your computer.
2. Download the latest Katheryne.jar to run the programme.
3. Copy the file to the folder you want to use as the home folder under `/.data` folder and rename it to `Katheryne.txt` for loading of past tasks.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar Katheryne.jar` command to run the application.
5. Refer to the features below for details


## Features

### ToDo
Adds a ToDo task to your task list.  

Format: `todo [desription]`  
Example: `todo read`  

### Deadline
Adds a Deadline task to your task list.  

Format: `deadline [description] /by [date]`  
Example: `deadline homework /by 2024-09-17`  

Remark: the date should be in `yyyy-MM-dd` format.  

### Event
Adds an Event task to your task list.  

Format: `event [description] /from [start_time] /to [end_time]`  
Example: `event camp /from Sept 21 /to Sept 25`  

### List
Lists down all tasks in your task list.  

Format: `list`  
Example: `list`

### Mark
Marks a task as done.  
Format: `mark [index]`  
Example: `mark 2`

### Unmark
Unmarks a task as not done yet.
Format: `unmark [index]`  
Example: `unmark 2`  

### Delete
Deletes a task from your task list.  

Format: `delete [index]`  
Example: `delete 2`

### Find
Finds tasks whose descriptions containing the given keyword.  

Format: `find [description]`  
Example: `find read`

### Bye
Exits the application in 5 seconds.

Format: `bye`  
Example: `bye`


