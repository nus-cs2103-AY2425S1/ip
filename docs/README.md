# Tira User Guide

A very friendly cat residing in a local cafe that is somehow
as smart as normal humans.

![Ui.png](Ui.png)

Tira will remember, modify and edit your tasks as long as you treat her
with good ol' cat food. Here are the following things you can do:

## Show Task List: `list`
Tira will display the current tasks that you have and your progress with each of them.

Example: `list`
```
HERE ARE THE CURRENT TASKS:
1. [D][] borrow book (by: 12 Sep 2020)
```

## Add Deadlines:`deadline`
Adds a task with deadline. 
Format: `deadline DESCRIPTION by/ yyyy-MM-dd`

Example: `deadline borrow book by/ 2020-09-12`

```
Miao! Got it. I've added this task to my cat brain:
[D][] borrow book (by: 12 September 2020)
Now you have 1 task(s) in the list!
```

## Add ToDo: `todo`
Adds todos that have no dates/timings.

Format: `todo DESCRIPTION`
Example: `todo cook`
```
Miao! Got it. I've added this task to my cat brain:
[T][] cook
Now you have 1 task(s) in the list!
```
## Add Event: `event`
Adds event that has a start and end date.

Format: `event DESCRIPTION /from yyyy-MM-dd /to yyyy-MM-dd`

Example: `event wedding /from 2020-01-01 /to 2020-02-02`

```
Miao! Got it. I've added this task to my cat brain:
[E][] wedding (from: 01 Jan 2020 to: 02 Feb 2020 )
Now you have 1 task(s) in the list!
```

## Delete Task: `delete`
Delete a specific task number from the list.

Format: `delete TASKNUMBER`

Example: `delete 1`

Previous list:
```
HERE ARE THE CURRENT TASKS:
1. [D][] borrow book (by: 12 Sep 2020)
```
After deletion:
```angular2html
HERE ARE THE CURRENT TASKS:
```

## Mark Task: `mark`
Marks task that you have done by specifying its number.

Format: `mark TASKNUMBER`

Example: `mark 1`

Before marking:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [D][] borrow book (by: 12 Sep 2020)
```
After marking:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [D][X] borrow book (by: 12 Sep 2020)
```

## Unmark Task: `unmark`
Unmarks task by specifying its number.

Format: `unmark TASKNUMBER`

Example: `unmark 1`

Before marking:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [D][X] borrow book (by: 12 Sep 2020)
```
After marking:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [D][] borrow book (by: 12 Sep 2020)
```

## Find Tasks: 'find'
Finds task(s) with matching description names. 

Format: `find DESCRIPTION`

Example: `find borrow book`

If the list looks like this:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [D][] borrow book (by: 12 Sep 2020)
2. [T][] borrow book
```
Output would be:
```angular2html
Miao!!!! I found the tasks in my cat brain! They are:
[D][] borrow book (by: 12 Sep 2020)
[T][] borrow book
```
## Show Task Statistics: `statistics`
Calculates the statistics of tasks in the list.
Format: `statistics`

xample: `statistics`

Given the list is:
```angular2html
HERE ARE THE CURRENT TASKS:
1. [T][X] borrow book
2. [E][] wedding (from: 01 Jan 2020 to: 01 Jan 2021)
3. [D][X] cooking (by: 03 Jan 2020)
```
The statistics would be:
```angular2html
OK MIAO! Here's your TaskList statistics:
Number of tasks in the list:3

TODOS
ToDos: 1
Marked ToDos: 1
Unmarked ToDos: 0

DEADLINES
Deadlines: 1
Marked Deadlines: 1
Unmarked Deadlines:0

EVENTS
Events: 1
Marked Events: 0
Unmarked Events: 1

Total number of Marked tasks: 2
Total number of Unmarked tasks: 1
% of Marked Tasks: 66
```
