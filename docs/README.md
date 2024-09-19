# Colby User Guide

// Product screenshot goes here

Ever felt like all your tasks and plans were catching up to you too fast? 
Why not try out Colby, a task manager right at your fingertips! It can help 
you track your tasks, deadlines and events all in one place!

## Adding tasks

Add a task to your list by entering a command starting with either todo, deadline or event!

With a todo task, you can add a fixed duration of time required for the task if applicable.

With a deadline task, you need to add minimally a date, when you task will be due, and additionally 
you can add a time as well! 

With an event task, you need to add a start and end date/time of your event.

Each task added to you list will be marked with either [T], [D] or [E], along with the status of
whether the task has been completed, represented by a [X].

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## ToDo tasks

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

## Deadline tasks

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

## Event tasks

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

## List

With the list command, Colby will list out all the tasks you have added so far!

### Example:

Command: `list`
```
Here's all the tasks you have to do:
1. [T][] hw1
2. [D][] math quiz (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
```

## Mark and Unmark
The mark and unmark commands help you indicate whether you are finished with a task in your list.
They can be used by entering mark/unmark followed by the number of the task in the entire list.

### Example:

Command: mark 2
```
1. [T][] hw1
2. [D][X] math quiz (by: Oct 15 2024 13:02)
3. [E][] meeting (from: 2pm to: 4pm)
```

