# Yapper User Guide

![Screenshot 2024-09-18 at 12 55 21 AM](https://github.com/user-attachments/assets/1361b92c-bfdb-47d6-8e17-672f8de1175d)

**Yapper is a task manager, to keep track of all your tasks, and even their time of occurrence!**

## Adding todos

Add a todo task into your list of tasks.
Enter "todo " followed by the name of the todo.

Example: todo buy bread

Yapper will give the following response:

```
" Got it. I've added this task:
  [T][ ] buy bread
  Now you have 1 tasks in the list "
```

## Adding deadlines

Add a deadline task into your list of tasks.
Enter "deadline " followed by the name of the deadline, followed by the ending time.
Format for time: YYYY/MM/DD HHMM
If no deadline time is specified, default is set to 23:59.

Example: deadline make bread /by 2024/12/31 1800

Yapper will give the following response:

```
" Got it. I've added this task:
  [D][ ] make bread (BY: Dec 31 2024 18:00)
  Now you have 1 tasks in the list "
```

## Adding events

Add a event task into your list of tasks.
Enter "event " followed by the name of the deadline, followed by the starting time, followed by the ending time.
Format for time: YYYY/MM/DD HHMM
If no event start time is specified, default is set to 00:00.
If no event end time is specified, default is set to 23:59.

Example: event sell bread /from 2024/12/30 1800 /to 2024/12/31 2100

Yapper will give the following response:

```
" Got it. I've added this task:
  [E][ ] sell bread (FROM: Dec 30 2024 18:00 TO: DEC 31 2024 21:00)
  Now you have 1 tasks in the list "
```

## Returning the list of tasks

Returns the current list of tasks.
Enter "list".

Example: list

Yapper will give the following response:

```
" Here are the tasks in your list:
  1. [T][ ] buy bread
  2. [D][ ] make bread (BY: Dec 31 2024 18:00)
  3. [E][ ] sell bread (FROM: Dec 30 2024 18:00 TO: DEC 31 2024 21:00) "
```

## Marking a task as Done

Marks the specified task as done.
Enter "mark " followed by the task number you wish to mark.

Example: mark 1

Yapper will give the following response:

```
" Nice ! I've marked this task as done:
  1. [T][X] buy bread "
```

## Marking a task as Undone

Marks the specified task as undone.
Enter "unmark " followed by the task number you wish to unmark.

Example: unmark 1

Yapper will give the following response:

```
" OK, I've marked this task as not done yet:
  1. [T][ ] buy bread "
```

## Delete a task

Deletes the specified task from the list of tasks.
Enter "delete " followed by the task number you wish to delete.

Example: delete 1

Yapper will give the following response:

```
" Noted. I've removed this task:
  [T][ ] buy bread
  Now you have 2 task in the list "
```

## Find a task

Finds a task containing the specified String.
Enter "find " followed by the string you wish to search for.

Example: find sell

Yapper will give the following response:

```
" Here are the tasks in your list:
  1. [E][ ] sell bread (FROM: Dec 30 2024 18:00 TO: DEC 31 2024 21:00) "
```

## Postponing a task

Postpone a task to the specified time
Enter "postpone " followed by the task number you wish to postpone, followed by 0 for start time, and 1 for end time.
Entering 0 required the task to have a start time, ie. an event.
Entering 1 required the task to have an end time, ie. a deadline or an event.

Example: postpone 1 1 2024/12/31 2100

Yapper will give the following response:

```
" Changed make bread end time to Dec 31 2024 21:00 "
```
