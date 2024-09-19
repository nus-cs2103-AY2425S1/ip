# Jag User Guide

![Ui.png](Ui.png)

## Jag's description
Jag is a chat style bot to help you keep track of your tasks.
Tasks can come in 3 forms, todo's, deadlines and events.


## Adding deadlines

Type `deadline read book /by 2020-10-10 1900` to create a task
where you have to read a book by the given date and time format.
Keep in mind that the format used has to be exact.

Here are a few examples:
```
deadline read magazine /by 2020-10-10 1800
deadline read newspaper /by 2020-10-10 2100
deadline read journal /by 2020-10-10 1900
```

Try this out: `deadline read book /by 2020-10-10 1800`


Your expected output will be this, assuming you have 7 tasks saved:

```
----
Got it. I've added this task:
[D][] read book (by: Oct 10 2020, 06 00 00)
Now you have 7 tasks uin the list
----
```

## Adding todos

Type `todo read book` to create a task
of type todo, which has no time limit.

Try this out: `todo read book`


Your expected output will be this, assuming you have 7 tasks saved:

```
----
Got it. I've added this task:
[T][] read book
Now you have 7 tasks uin the list
----
```

## Adding events

Type `event read book /from 2020-10-10 1900 /to 2021-10-10 1800` to create a task
of type event, which has a start and an end date.

Try this out: `event read book /from 2020-10-10 1900 /to 2021-10-10 1800`


Your expected output will be this, assuming you have 10 tasks saved:

```
----------
Got it. I've added this task: 
[E][ ] read book (from: Oct 10 2020, 07 00 00, to: Oct 10 2021, 06 00 00)
Now you have 10 tasks in the list
----------
```

## Mark Feature

Type `mark 1` to mark a task as complete

Try this out: `mark 1`


Your expected output will be this:

```
----------
Nice! I've marked this task as done:
[T][X] read a book
----------
```

## Unmark Feature

Type `unmark 1` to mark a task as complete

Try this out: `unmark 1`


Your expected output will be this:

```
----------
OK, I've marked this task as not done yet:
[T][ ] read a book
----------
```

## List Feature

Type `list` to view your current saved list of tasks

Try this out: `list`


Your expected output will be this, assuming you have 4 tasks saved:

```
----------
1. [T][ ] read a book
2. [D][X] read a book (by: Oct 10 2020, 06 00 00)
3. [T][ ] read magazine1234
4. [D][ ] read (by: Oct 10 2020, 07 00 00)
----------
```

## Update feature

Type in the command `update 1 read another book` for example to update a task
that with the given index 1 in the list, which so happens to be a todo type task

Your expected output will be this:

```
----------
The following task has been updated: 
[T][ ] read another book
----------
```


## Finding a task

Type in the command `find read` to find  any tasks that contains the word read in its description

Try this out: `find book`

Your expected output will be this:

```
----------
Here are the matching tasks in your list: 
1. [T][ ] read another book
2. [D][X] read a book (by: Oct 10 2020, 06 00 00)
----------
```