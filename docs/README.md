# Bimo User Guide

![Screenshot of product Ui](./Ui.png)

Bimo is a *task management* chat bot that helps you remember 
your tasks in a **simple** and **fuss free manner**. All you need is a Command Line Interface to open 
our chatbot GUI! 

## Initialise chat bot

1. Download the jar file from [Github](https://github.com/CJianzhi/ip).
2. Copy the file to an empty folder.
2. Using a command terminal, `cd` into the directory containing the file. 
3. Run `java -jar bimo.jar`.

## Available commands
- todo
- deadline
- event
- mark
- unmark
- delete
- list
- find
- set
- help
- bye

## Adding todo tasks

Add a task without any date attached to it. 

Format: `todo DESCRIPTION`

Example usage:

`todo Buy bread`

Expected output:
```
Roger that. I've added this task for you
  [T][] Buy bread
Now you have 1 task in the list.
```

## Adding tasks with deadlines

Add a task that needs to be done before a specific date.

Format: `deadline DESCRIPTION /by yyyy-mm-dd`
- Date specified must be in year-month-day order
- Year must be specified using 4 numbers, e.g. 2023
- Month must be specified using 2 numbers, e.g. 03
- Day must be specified using 2 numbers, e.g. 01
- `/by` indicates the due date

Example usage:

`deadline Complete tutorial /by 2024-09-22`

Expected output:
```
Roger that. I've added this task for you
  [D][] Complete tutorial (by: 22 Sept 2024)
Now you have 1 task in the list.
```
## Adding tasks with start and end date

Add a task that starts at a specific date and ends at a specific date.

Format: `event DESCRIPTION /from yyyy-mm-dd /to yyyy-mm-dd`
- Date specified must be in year-month-day order
- Year must be specified using 4 numbers, e.g. 2023
- Month must be specified using 2 numbers, e.g. 03
- Day must be specified using 2 numbers, e.g. 01
- `/from` indicates the start date
- `/to` indicates the end date

Example usage:

`event Attend workshop /from 2024-09-22 /to 2024-09-23`

Expected output:
```
Roger that. I've added this task for you
  [E][] Attend workshop (from: 22 Sept 2024 to: 23 Sept 2024)
Now you have 1 task in the list.
```

## Set task as completed

Mark a task inside the list of tasks as completed.

Format: `mark TASK_NUMBER`
- `TASK_NUMBER` is the position of the task in the list

Example usage:

`mark 1`

Expected output:
```
Wow! Good job! I've crossed out this task for you!
 [T][X] Buy bread
```

## Set task as uncompleted

Mark a task inside the list of tasks as uncompleted.

Format: `unmark TASK_NUMBER`
- `TASK_NUMBER` is the position of the task in the list

Example usage:

`unmark 1`

Expected output:
```
OOPS! I've unticked this task for you :(
 [T][] Buy bread
```

## Delete task

Remove a task from the list using the task number.

Format: `delete TASK_NUMBER`
- `TASK_NUMBER` is the position of the task in the list

Example usage:

`delete 1`

Expected output:
```
Roger that. I've removed this task for you
  [T][] Buy bread
Now you have 0 tasks in the list.
```

## List all tasks

Display all tasks added. 

Format: `list`

Example usage:

`list`

Expected output:
```
Have a look at the list of tasks:
  1.[T][] Buy bread
  2.[D][] Complete tutorial (by: 22 Sept 2024)
  3.[E][] Attend workshop (from: 22 Sept 2024 to: 23 Sept 2024)
```

## Find tasks by word

Display list of tasks containing at least one of the words specified by users.

Format: `find WORD_1 WORD_2 WORD_3`
- Users can provide one or more words that are separated by a space
- Only the description of each task is searched

Example usage:

`find bread tutorial`

Expected output:
```
Here are the matching tasks you requested:
  1.[T][] Buy bread
  2.[D][] Complete tutorial (by: 22 Sept 2024)
```

## Add priority to task

Add priority levels `HIGH`, `MEDIUM` or `LOW` to the tasks to indicate
the urgency for each task.

Format: `set TASK_NUMBER PRIORITY_LEVEL`
- `PRIORiTY_LEVEL` must be either `HIGH`, `MEDIUM` or `LOW` 
- `TASK_NUMBER` is the position of the task in the list

Example usage:

`set 1 high`

Expected output:
```
OKAYY I have set the priority level for this task!
  <HIGH> [T][] Buy bread
```

## View all available commands

Display a list of available commands. 

Format: `help`

Example usage:

`help`

Expected output:
```
Take a look at what I can do!

Available commands:

1. todo <task>

2. deadline <task> /by yyyy-mm-dd

3. event <task> /from yyyy-mm-dd /to yyyy-mm-dd

4. mark <task number>

5. unmark <task number>

6. delete <task number>

7. find <keyword keyword keyword>

8. list

9. set <task number> <high, medium, low>

10. help

11. bye
```

## Exit chat bot

Exits program and closes the window automatically 
after a 1.5 seconds delay. 

Format: `bye`

Example usage:

`bye`

Expected output:
```
Bye Bye!!! Have a good day!
```