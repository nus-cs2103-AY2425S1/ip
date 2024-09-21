# Gary User Guide

Welcome to **Gary**, your personal task management chatbot! Gary helps you keep track of your to-do lists, deadlines, and events, ensuring you stay on top of your tasks effortlessly. This user guide will walk you through all the essential features of Gary and how to use them effectively.

## Getting started 
1. To get started with Gary, ensure that you have Java 17 installed.
2. Download Gary.jar from [here](https://github.com/kuiktzecheng/ip/releases).
4. Open a command window in that folder.
5. Run the command java -jar Gary.jar.

## Features

***NOTE:***

***1. Words in UPPER_CASE for the various commands below are the parameters to be supplied by the user.***

***2. All parameters are mandatory. Commands with parameters that are in the wrong format (e.g. wrong order, empty parameters) will cause an error.***

***3. Date formats must be in yyyy-mm-dd e.g. 20th September 2024 should be formatted as "2024-09-20".***

### Adding ToDo task : 
Adds a task with no form of time restriction to your list of tasks

Command Format : todo NAME_OF_TASK

Example : todo read book

### Adding Deadline task : 
Adds a task with a deadline to your list of tasks

Command Format : deadline NAME_OF_TASK /by DEADLINE

Example : deadline return book /by 2024-09-20

### Adding Event task : 
Adds a task with a time duration to your list of tasks

Command Format : event NAME_OF_TASK /from START_DATE /to /END_DATE

+ Multiple START_DATE and END_DATE not allowed!!

Example : event holiday /from 2024-07-15 /to 2024-07-25

### Display list of tasks : 
Show the current list of tasks

Command Format : list

### Marking/Unmarking tasks : 
Mark/Unmark specified task from the list as done/not done

Command Format : mark/unmark INDEX

+ Marks the task at the specified INDEX as done / Unmark the task at the specified INDEX as not done.
+ The index refers to the index number shown in the displayed task list.
+ The index must be a positive integer 1, 2, 3, …​
+ The index must be a valid integer pointing to a task in the list.

Examples : 
+ mark 2 marks the second task in the list as done.
+ mark 6 in a list with 5 tasks will produce an error message.
+ unmark 2 marks the second task in the list as not done.
+ unmark 6 in a list with 5 tasks will produce an error message.

### Deleting task from list : 
Delete the specified task from the list

Command Format : delete INDEX

+ Deletes the task at the specified INDEX.
+ The index refers to the index number shown in the displayed task list.
+ The index must be a positive integer 1, 2, 3, …​
+ The index must be a valid integer pointing to a task in the list.

Examples:
+ delete 2 deletes the second task from the list.
+ delete 6 in a list with 5 tasks will produce an error message.

### Find task from list : 
Find tasks with specified keyword

Command Format : find KEYWORD

+ Search is case-insensitive. e.g Book will match book
+ Only the task name is searched.
+ Partial words can be matched e.g. work will match homework
+ Supports the search of one keyword only

### Exiting the program : 
Exit the program 

Command Format : bye
