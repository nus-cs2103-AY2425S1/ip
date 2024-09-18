# Genji User Guide

![](Ui.png)

Genji frees your mind of having to remember things you need to do.

## Help manual

Show the format of all commands

Example: `help`

## Adding todos

Add todo tasks to task list

todo xxx

Example: `todo read book`

```
Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list
```

## Adding deadlines

Add deadline tasks to task list

deadline xxx /by yyyy-mm-ddThh:mm

Example: `deadline homework /by 2024-09-20T16:00`

```
Got it. I've added this task:
[D][] homework (by: Sep 20 2024 16:00)
Now you have 2 tasks in the list
```

## Adding events

Add event tasks to task list

event xxx /from yyyy-mm-ddThh:mm /to yyyy-mm-ddThh:mm

Example: `event watch movie /from 2024-09-22T17:30 /to 2024-09-22T19:30`

```
Got it. I've added this task:
[E][] watch movie (from: Sep 22 2024 17:30 to:
Sep 22 2024 19:30)
Now you have 3 tasks in the list
```

## Marking

Mark task as done

mark i

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] read book
```

## Unmarking

Mark task as undone

unmark i

Example: `unmark 1`

```
Nice! I've marked this task as done:
[T][] read book
```
## Bye

End the program


Example: `bye`

```
Bye. Hope to see you again soon!
```

## Listing

List out tasks

Example: `list`

```
1.[T][] read book
2.[D][] homework (by: Sep 20 2024 16:00)
3.[E][] watch movie (from: Sep 22 2024 17:30 to:
Sep 22 2024 19:30)
```

## Deleting

Delete task

delete i

Example: `delete 3`

```
Noted. I've removed this task:
[E][] watch movie (from: Sep 22 2024 17:30 to:
Sep 20 2024 19:30)
Now you have 2 tasks in the list
```

## Checking date

Check tasks on a specific date

date yyyy-mm-dd

Example: `date 2024-09-20`

```
Here are the matching tasks in your list:
[D][] homework (by: Sep 20 2024 16:00)
```

## Finding task

Find tasks containing specific word

find xxx

Example: `find read`

```
Here are the matching tasks in your list:
[T][] read book
```