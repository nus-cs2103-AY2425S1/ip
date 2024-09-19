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



Try this out: `deadline read book /by 2020-12-12 1800`


Your expected output will be this, assuming you have 7 tasks saved:

```
----
Got it. I've added this task:
[D][] read book (by: Dec 12 2020, 06 00 00)
Now you have 7 tasks uin the list
----
```

## Update feature

Type in the command `update 1 read book /by 2021-10-10 1800` for example to update a task
that with the given index 1 in the list, which so happens to be a deadline type task


## Finding a task

Type in the command `find read` to find  any tasks that contains the word read in it's description