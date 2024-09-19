## Meow User Guide

![Product Screenshot](./Ui.png)

Whether you need to manage your daily to-do list, set important deadlines, or organize events with start
and end times, Meow provides a seamless experience for handling your task management needs. With support
for natural language commands and a clean, minimalistic UI, the app makes task tracking straightforward
and enjoyable.

Download the latest version [HERE](https://github.com/Al-ez/ip/releases)
## Adding deadlines

To add a task with a specific deadline, you can use the deadline command.

Example: deadline submit assignment /by 2024-12-01

A deadline task 'submit assignment' with the deadline 'Dec 01 2024' will be added to the list.

```
Got it, I've added this task:
[D][] submit assignment (by: Dec 01 2024)
Now you have 5 tasks in the list.
```

## Adding ToDos

To add a task without a specific date, use the todo command.

Example: todo read book

ToDo event read book will be added to the list.

```
Got it, I've added this task:
[T][] read book
Now you have 5 tasks in the list.
```



## Adding Events

To add a task with both a start and end time, use the event command.

Example: event project meeting /from 2024-10-01 /to 2024-10-02

Project meeting event will be added to the list.

```
Got it, I've added this task:
[E][] project meeting (from: Oct 01 2024 to: Oct 02 2024)
Now you have 5 tasks in the list.
```

## Find tasks by keyword

To find all tasks with the specific keyword or letters

Example: find eat

Returns all the tasks with the keyword 'eat' anywhere in its description

```
1. [T][] Eat lunch
```

## Delete task

Delete the specified task based on its position in the list.

Example: delete 1

Deletes the first task in the list, and shift all other tasks behind it up by 1.

```
Noted. I've removed this task:
[T][] Eat lunch
Now you have 4 tasks in the list
```

## Mark task as done

Mark the task based on the task position in the list.

Example: mark 1

Mark the first task in the list.

```
Nice, I've marked this task as done:
[T][X] Eat lunch
```

## Unmark task as not done

Unmark the task based on the task position in the list.

Example: unmark 1

Unmark the first task in the list

```
OK, I've marked this task as not done yet:
[T][] Eat lunch
```

## View Task List

View a visual representation of our list of tasks

Example: list

```
1. [T][] Eat lunch
2. [T][] Clean the toilet
```

## Sort list by chronological order

Sort out the list from earliest date to latest date

Example: sort

```
1. [E][] Camp (from: Dec 12 2020 to: Dec 13 2020)
2. [D][] Submit Assignment (by: Dec 01 2024)
3. [T][] Eat lunch
```

## Saving data

Task data in the list are saved in the hard disk automatically after any command that
changes the data. There is no need to save manually.