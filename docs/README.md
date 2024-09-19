# YNCH User Guide

![Screenshot of Ui](Ui.png)

YNCH is a silly c(h)atbot that manages your **todos, deadlines and upcoming events** in a graphical user interface.

## Table of contents
1. [Quick Start](#quick-start)
2. [Features](#features-and-their-examples)
    1. [Todo](#adding-todo-tasks)
    2. [Deadlines](#adding-deadlines)
    3. [Events](#adding-events)
    4. [List](#list)
    5. [Find](#finding-tasks)
    6. [Upcoming tasks](#upcoming-tasks)
    7. [Mark/Unmark](#marking-or-unmarking)
    8. [Deletion](#deletion-of-tasks)
    9. [Clearing all tasks](#clearing-all-tasks)
    10. [Reminders](#viewing-reminders)

## Adding deadlines
## Quick Start

// Describe the action and its outcome.
1. Ensure you have Java 17 or above installed in your Computer.
2. Download the .jar file from [YNCH.jar](https://github.com/yanqiyqh/ip/releases/download/A-Jar/ynch.jar).
3. Double click to open the app, it should immediately open the app for you to use.

## Features and their examples

### Adding Todo tasks
```
todo read book
```

A confirmation message should be output and the todo should be added to the list of tracked tasks. The confirmation message example is below:

```
Meow! I've added this task:
[T][] read book
Now you have [however many task(s)] in your list.
```
### Adding Deadlines
A deadline is a time-constrained task which must be done by a specific date and time. Make sure the date and time follows the exact format as shown below and that you have included the `/by` keyword. This should add a deadline to the list and output a message as shown in the examples below:

```
deadline return book /by 2024-09-19
```
A confirmation message should be output and the deadline should be added to the list of tracked tasks. The confirmation message example is below:
```
Meow! I've added this task:
[D][] return book (by: 19 Sep 2024)
Now you have [however many task(s)] in your list.
```

### Adding Events
An Event is a type of task which happens specifically within a constrained duration. Make sure that the date and time specified for the start and end follow exactly as seen in the Example Input:

```
event Career Fair /from 2024-09-16 /to 2024-09-30
```
A confirmation message should be output and the event should be added to the list of tracked tasks. The confirmation message example is below:
```
Meow! I've added this task:
[E][] Career Fair (from: 16 Sep 2024 to: 30 Sep 2024)
Now you have [however many task(s)] in your list.
```

### List
You can get a list of all tracked tasks by calling `list` to the program.

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: 19 Sep 2024)
```

### Finding tasks
You can quickly search for tasks using the find function one keyword at a time.

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
### Marking or Unmarking
You can mark or unmark items to show if they have been completed or not. Simply do the following with the item index shown in the list:


#### Mark

Marking Example Input:

```
mark 1
```

Marking Example Output:

```
Meow! I've marked this task as done:
[specified task based on the item number]
```

#### Unmark

Unmarking Example Input:

```
unmark 1
```

Unmarking Example Output:

```
Meow! I've marked this task as not done yet:
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
Meow! I've removed this task:
[deleted task]
Now, you have [X] task(s) left in your list.
```

### Viewing Reminders
You can view upcoming tasks on the startup of the chatbot:
```
Meow! How can I help you today?
Just so that you don't forget, here are some tasks you have to do soon:
1. [E][] Career Fair (from: 16 Sept 2024 to: 30 Sept 2024)
```
