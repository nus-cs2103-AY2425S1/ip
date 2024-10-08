# Bob User Guide

![Alt text](Ui.png)

## Adding deadlines

Add tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

Example: `deadline submit report /by 15/10/2019 1700`

Creates a deadline with the specified due-date.

```
Got it. I've added this task:
[D][] submit report (by: Oct 15 2019 17:00)
```

## Adding todos

Add tasks without any date/time attached to it e.g., visit new theme park

Example: `todo visit new theme park`

Creates a todo task with the specified description.

```
Got it. I've added this task:
[T][] visit new theme park
```

## Adding events

Add tasks tasks that start at a specific date/time and ends at a specific date/time
e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

```
Got it. I've added this task:
[E][] team project meeting (at: Oct 2 2019 14:00 - 16:00)
```

## Marking tasks as done

Mark tasks as done when they are completed

Example: `done 1`

Marks the task at index 1 as done.

```
Nice! I've marked this task as done:
[D][X] submit report (by: Oct 15 2019 17:00)
```

## Listing all tasks

List all tasks that have been added to Bob

Example: `list`

Lists all tasks that have been added to Bob.

```
Here are the tasks in your list:
1. [D][X] submit report (by: Oct 15 2019 17:00)
2. [T][] visit new theme park
3. [E][] team project meeting (at: Oct 2 2019 14:00 - 16:00)
```



