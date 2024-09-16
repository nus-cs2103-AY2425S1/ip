# Waterfall User Guide

![Ui image](Ui.png)
Waterfall chatbot is your ultimate task management platform that helps you
efficiently manage tasks, set deadlines, and stay on top of your to-do list 
with ease.

## Getting Started
To interact with the Waterfall chatbot, simply type commands into the input 
field. Waterfall will respond with the appropriate information or take the 
necessary actions based on your input.


## Adding todos:
This feature allows you to add simple to-do tasks without any specific deadline.

### Command Format:

Example: `todo <title>`

Waterfall will add the todo task to your list.

```
Succcessfully added a task to the waterfallll:
[T][] <title>
```


## Adding deadlines
This feature allows you to add tasks with specific deadlines to help you track 
time-sensitive tasks.

### Command Format:

Example: `deadline <title> /by yyyy-MM-dd hhmm`

Waterfall will add the task to your list with the specified deadline.

```
Succcessfully added a task to the waterfallll:
[D][] <title> (by: MMM dd, yyyy hh:mm)
```

## Adding events
This feature allows you to add event tasks with a starting and end time.

### Command Format:

Example: `event <title> /from yyyy-MM-dd hhmm /to yyyy-MM-dd hhmm`

Waterfall will add the task to your list with the from and to time.

```
Succcessfully added a task to the waterfallll:
[E][] <title> (from: MMM dd, yyyy hh:mm to: MMM dd, yyyy hh:mm)
```

## Mark and unmark task status
This feature allows you to mark a task as done or not done.

### Command Format:

Example: `mark 4`
`unmark 4`

Waterfall will mark the task at position 4 as done or not done.

```
Huluhuluhulu, I've marked this task as done
[E][X] <title> (from: MMM dd, yyyy hh:mm to: MMM dd, yyyy hh:mm)
///////////////////////////////////////////////////////////////////////
Hohohohohoho, I've marked this task as not done
[E][] <title> (from: MMM dd, yyyy hh:mm to: MMM dd, yyyy hh:mm)
```

## Show task list
This feature allows you to see all the tasks in your list.

### Command Format:

Example: `list`

Waterfall will display all tasks.

```
Here's the task in your waterfall hualalala
  1.[T][X] <title1>
  2.[D][] <title2> (by: MMM dd, yyyy hh:mm) 
  3.[E][X] <title3> (from: MMM dd, yyyy hh:mm to: MMM dd, yyyy hh:mm)
```

## Delete task
This feature allows you to delete task in the list.

### Command Format:

Example: `delete 1`

Waterfall will delete task at specified index.

```
Hehehehehehe, I've removed this task from the waterfall:
[T][] <title>
```

## Undo command
This feature allows you to undo all previous commands.

### Command Format:

Example: `undo`

Waterfall will delete task at specified index.

```
Successfully undo the last command!
Here's the task in your waterfall hualalala
  1.[T][X] <title1>
  2.[D][] <title2> (by: MMM dd, yyyy hh:mm) 
  3.[E][X] <title3> (from: MMM dd, yyyy hh:mm to: MMM dd, yyyy hh:mm)
```