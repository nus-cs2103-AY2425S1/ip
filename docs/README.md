# Rizzler User Guide

![](https://github.com/Andrew22Teoh/ip/blob/master/docs/Ui.png)

Say hello to **Rizzler**! Your best friend(?) who'll help you keep track of everything you have to do!
Read on below for the User Guide and instructions on how to use it properly!

## ToDo

The most obvious kind of task is something that we have to do. **Rizzler** calls this a ToDo!

To create a ToDo, just type in `todo`, followed by a task description! See below for the format and examples of using this command.
```
todo {task description}
todo homework
todo play golf with friends
```

## Deadline

Another common type of task is one with a deadline. Fortunately for us, **Rizzler** supports these too!

To create a Deadline task, just type in `deadline`, followed by the task description, then tell **Rizzler** when you need to finish this task `/by`!
```
deadline {deadline description} /by {deadline}
deadline send email to prof /by tomorrow!!
deadline apply for SEP /by 2024-10-01
```
If you have any dates, you may want to consider entering them in `YYYY-MM-DD` format! **Rizzler** will format it into a more readable format for you 😄

## Event

The last type of task that **Rizzler** supports is events. These come with a start and end time, which you have to let **Rizzler** know!

To create an Event task, just type in `event`, followed by the task description, then tell **Rizzler** the duration of the event, `/from` when to `/to` when!
```
event {event description} /from {event start} /to {event end}
event Conference with Insurance Agents /from 2024-11-01 /to 2024-11-03
event lunch with parents! /from tomorrow 12pm /to tomorrow 2pm
```
Just like Deadlines, Events can also be created with dates in `YYYY-MM-DD` format if you'd like **Rizzler** to format it for you!

## List

Once you've added all your tasks into **Rizzler**'s memory bank, you want to access it at times.

This is where the `list` command comes in handy! Just type in `list`, and you'll get a list of all tasks that you have told **Rizzler** about!

## Mark & Unmark

Of course, once we've populated our list with tasks, we want a way to update our list when any task is completed!

**Rizzler** can help you to keep track of which tasks are done, and which are still pending. Just use the `mark` and `unmark` commands to update the status of any task!

Take note that the task IDs referenced below are based on the task ID when you use the `list` command, so if you want to mark a task as done, refer to it by the number that shows up to the left of the task when you use the `list` command!
```
mark {task ID}
unmark {task ID}
mark 1
mark 3
unmark 3
```
## Delete

We all make mistakes, and that's ok! **Rizzler** ~~loves~~ appreciates you for who you are, even if you are fallible. If you do key in a task wrongly, or you realise you made a Deadline when you should have made it an Event instead, **Rizzler** allows you to delete any tasks you no longer want to see.

This is done with the `delete` command, which uses the same task ID used in the `mark` and `unmark` command. This is also the same task ID shown by the `list` command, so you can use that as a reference!
```
delete {task ID}
delete 1
delete 3
```

## Find

If you do get really busy and your task list starts to fill up, **Rizzler** understands that it can be a tedious process searching through your task list just to check on the status of a specific task.

And so, **Rizzler** has a search function too! Just use the `find` command, together with what you remember of the task description, and **Rizzler** will show you all tasks with task descriptions that match your input! Don't worry if you don't remember the whole task description, any part of it that you remember will be sufficient for **Rizzler** to narrow down the tasks, even if all you remember is a single letter!
```
find {string to match to task description}
find dinner with parents
find work
find a
```

## Help

Last of all, if you do need help at any point while using **Rizzler** and you wish to avoid having to check this user guide, **Rizzler** has a handy help function!

All you have to do is key in `help` and a list of all commands will show up!

If you want more information about the format of any other command, you can simply type in the command you want more information about, and **Rizzler** will let you know what the command format is like, and show you examples.
