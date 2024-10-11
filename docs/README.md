# Mortal Reminder User Guide

![Screenshot of Ui](Ui.png)

Mortal Reminder is a funny little app used to manage your **todos, deadlines and upcoming events** in a pretty graphical user interface which makes everything easier to see.

## Table of contents
1. [Quick Start](#quick-start)
2. [Features](#features-and-their-examples)
   1. [Todo](#adding-todo-tasks)
   2. [Deadlines](#adding-tasks-with-deadline-deadlines)
   3. [Events](#adding-events)
   4. [List](#list)
   5. [Find](#finding-tasks)
   6. [Upcoming tasks](#upcoming-tasks)
   7. [Mark/Unmark](#marking-or-unmarking)
   8. [Deletion](#deletion-of-tasks)
   9. [Clearing all tasks](#clearing-all-tasks)
   10. [Adding alternative command words](#adding-alternative-command-words)
   11. [Clearing alternative command words](#clearing-all-alternative-commands)
3. [Acknowledgements](#acknowledgements)

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer. 
2. Download the latest .jar file from [MortalReminder.jar](https://github.com/RezwanAhmed123/ip/releases/tag/A-Release).
3. Double click to open the app, it should immediately open the app for you to use.
4. If you are on a Mac, use terminal and navigate to the folder containing the app and use `java -jar MortalReminder.jar` to open the app.

## Features and their examples

### Adding Todo tasks
Example:
```html
todo read book
```

A confirmation message should be output and the todo should be added to the list of tracked tasks. The confirmation message example is below:

```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[T][] read book
Now you have [however many task(s)] in your list.
```
### Adding tasks with deadline (Deadlines)
A deadline is a time-constrained task which must be done by a specific date and time. Make sure the date and time follows the exact format as shown below and that you have included the `/by` keyword. This should add a deadline to the list and output a message as shown in the examples below:

Example Input:
```
deadline return book /by 19-09-2024 1800
```
Example output:
```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[D][] return book (by: 19 Sep 2024 6:00pm)
Now you have [however many task(s)] in your list.
```

### Adding Events
An Event is a type of task which happens specifically within a constrained duration. Make sure that the date and time specified for the start and end follow exactly as seen in the Example Input:

Example Input:
```
event project meeting /from 19-09-2024 1400 /to 19-09-2024 1500
```
Example Output:
```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[E][] project meeting (from: 19 Sep 2024 2:00pm to: 19 Sep 2024 3:00pm)
Now you have [however many task(s)] in your list.
```

### List
You can get a list of all tracked tasks by calling `list` to the program.

Example output:
```
Here are the tasks in your list:
1. [T][X] Read Book
2. [D][ ] Return Book (by: 19 Sep 2024 4pm)
```

### Finding tasks
You can quickly search for tasks using the find function. You can search for different tasks using either, one keyword at a time, or many keywords at once using a comma to separate the search terms. An example is shown below:

#### One keyword only

Input Example:
```
find book
```
Output example:
```
Here are the tasks matching your search terms:
1. [similar 1st task would be printed here]
2. [similar 2nd task would be printed here]
```

#### Multiple search terms

Input Example:
```
find book, project
```
Output example:
```
Here are the tasks matching your search terms:
1. [similar 1st task would be printed here]
2. [similar 2nd task would be printed here]
```

### Upcoming tasks
You can search for all upcoming tasks that have not been marked by calling the `upcoming_tasks` command. This will give you a list of all upcoming tasks as the output.

### Marking or Unmarking
You can mark or unmark items to show if they have been completed or not. Simply do the following with the item index shown in the list:


#### Mark

Marking Example Input:

```
mark 1
```

Marking Example Output:

```
I've marked this task as done:
[specified task based on the item number]
But are you really Ok?
```

#### Unmark

Unmarking Example Input:

```
unmark 1
```

Unmarking Example Output:

```
Seems like your effort was in Vayne. This task has been unmarked:
[specified task here]
```


### Deletion of tasks
You can delete tasks you no longer want to track using the following:
Example input:
```
delete 1
```
Example output:
```
"A futile act. Why do you persist? I've deleted this task:
[deleted task]
Now, you have [X] task(s) left in your list.
```

### Clearing all tasks
You can delete all tracked tasks using the `clear_tasks` command. This should clear everything that is currently being tracked.

### Adding alternative command words
You can use this command to add alternative commands to the commands in the program. For example, you can change `todo` to just `t`.
How to use this command:
```
add_command_alternative [new alternative you want] [a version of the command already recognised by the program]
```
Example input:
```
add_command_alternative aca add_command_alternative
```
Example output:
```
aca has been added to the command alternatives for add_command_alternative
```
And you can even use `aca` to map other forms of alternatives now as well such as:
```
aca a aca
```
which will output:
```
a has been added to the command alternatives for add_command_alternative
```

### Clearing all alternative commands
Using `clear_alternatives` will clear all alternative commands from memory.

## Acknowledgements
I would like to credit [@Solomon0519](https://github.com/Solomon0519) as I referenced his idea of using an Error Class to flag all my errors in the program instead of dealing with many error types at once.