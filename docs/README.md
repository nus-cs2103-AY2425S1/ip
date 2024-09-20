# Colby User Guide

![](/Users/amithav/Desktop/2103 iP/docs/Ui.png)

Ever felt like all your tasks and plans were catching up to you too fast? 
Why not try out Colby, a task manager right at your fingertips! It can help 
you track your tasks, deadlines and events all in one place!

## Features
Here are all the commands you can use with Colby!
- `todo`
- `deadline`
- `event`
- `list`
- `mark`
- `unmark`
- `delete`
- `find`

## Adding tasks

Add a task to your list by entering a command starting with either todo, deadline or event!

With a todo task, you can add a fixed duration of time required for the task if applicable.

With a deadline task, you need to add minimally a date, when you task will be due, and additionally 
you can add a time as well! 

With an event task, you need to add a start and end date/time of your event.

Each task added to you list will be marked with either [T], [D] or [E], along with the status of
whether the task has been completed, represented by a [X].


## ToDo tasks: `todo`

Using the todo command to add your tasks helps you take note of any tasks 
with no deadline or simply have a fixed duration to be completed in and will be represented 
by [T]. 

### Examples:

Command: `todo math quiz`
```
Alright, I have added this task to the list:
[T][] math quiz
```

Command: `todo math quiz /needs 1 hour`
```
Alright, I have added this task to the list:
[T][] math quiz (needs 1 hour)
```

## Deadline tasks: `deadline`

Using the deadline command to add your tasks helps you take note of any tasks
with deadlines, be it just a date or time as well. They will be represented
by [D], and the deadline date/time needs to be entered after the task name following 
a "/by", with the date in the format of YYYY/MM/DD.

### Examples:

Command: `deadline math quiz /by 2024/10/15`
```
Alright, I have added this task to the list:
[D][] math quiz (by: Oct 15 2024 23:59)
```

Command: `deadline math quiz /by 1 2024/10/15 13-02`
```
Alright, I have added this task to the list:
[D][] math quiz (by: Oct 15 2024 13:02)
```

## Event tasks: `event`

Using the event command to add your tasks helps you take note of any events
with start and end dates or time. They will be represented
by [E], and the event dates/times needs to be entered after the task name following
a "/from" for the start and "/to" for end.

### Examples:

Command: `event orientation /from 2024/10/15 /to 2024/10/18` 
```
Alright, I have added this task to the list:
[E][] orientation (from: 2024/10/15 to: 2024/10/18)
```

Command: `event meeting /from 2pm /to 4pm`
```
Alright, I have added this task to the list:
[E][] meeting (from: 2pm to: 4pm)
```

## Viewing all tasks: `list`

With the list command, Colby will list out all the tasks you have added so far!

### Example:

Command: `list`
```
Here's all the tasks you have to do:
1. [T][] hw1
2. [D][] math quiz (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
```

## Marking status of tasks: `mark` and `unamrk`
The mark and unmark commands help you indicate whether you are finished with a task in your list.
They can be used by entering mark/unmark followed by the number of the task in the entire list.

### Example:

Command: `mark 2`
```
1. [T][] hw1
2. [D][X] math quiz (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
```
## Deleting a task from the list: `delete`
With the delete command, you can delete a task from your list, based on the task's number in the list.

### Example: 
Command: `delete 3`
The list will change from: 
```
1. [T][] hw1
2. [D][X] math quiz (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
```
to:
```
1. [T][] hw1
2. [D][X] math quiz (by: Oct 15 2024 13:02)
```
and show `"Okay, I have removed this task from your list: meeting (from: 2pm to: 4pm)"`

## Searching for tasks in the list: `list`
You can use the find command to search our list for tasks matching the keyword you enter!

### Example:
Command: `find quiz`

In a list of 
```
1. [T][] english quiz
2. [D][X] math hw (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
4. [D][X] math quiz (by: Oct 15 2024 13:02)
```

Colby will return to you:
```
1. [T][] english quiz
2. [D][X] math quiz (by: Oct 15 2024 13:02)
```