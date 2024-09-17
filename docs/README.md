# Bobby User Guide

![Product Screenshot](Ui.png)

> Introducing **Bobby**, a revolutionary (*okay, not really*) new way to track your tasks!

Bobby is structured as a chatbot that users can talk with. By typing in comments and
sending them to Bobby as a chat message, he will be able to execute these commands and return
the relevant information

Additionally, Bobby automatically saves the task list to a file with every command execution,
meaning that there is data persistence between different sessions!

## Adding todos

A todo task is a named task without any deadlines.
Bobby is able to add such tasks to the task list

Example: `todo Homework`

```
Got it. I've added this task:
[T][] Homework
Now you have x tasks in the list
```


## Adding deadlines

A deadline task is a named task with a set datetime (the deadline).
Bobby is also able to add such tasks to the task list

Example: `deadline Assignment /by 2019-10-10 15:10`

```
Got it. I've added this task:
[D][] Assignment (by: Oct 10 2019 10:10)
Now you have x tasks in the list
```

Note that it is necessary to provide the /by argument, followed by a datetime
with the YYYY-MM-DD HH:MM format

## Adding events

An event task is a named task with 2 set datetimes (from and to).
Bobby is able to add such tasks to the task list

Example: `event Concert /from 2020-10-05 16:30 /to 2020-10-05 19:00`

```
Got it. I've added this task:
[E][] Concert (from: Oct 05 2020 16:30 to: Oct 05 2020 19:00)
Now you have x tasks in the list
```

Note that it is necessary to provide the /from and /to arguments, followed by 2 datetimes
with the YYYY-MM-DD HH:MM format

## Listing tasks
Bobby lets you view the task list, with the `list` command

Example: `list`
```
Here are the tasks in your list:
1. [T][] Homework
2. [D][] Assignment (by: Oct 10 2019 10:10)
3. [E][] Concert (from: Oct 05 2020 16:30 to: Oct 05 2020 19:00) 
```

## Marking tasks

Bobby gives you the ability to mark tasks as done, and also unmark them to indicate that they
are not done. After the task is marked, Bobby returns the string representation of the task

Example: `mark 1`
```
Nice! I've marked this task as done:
[T][X] Homework
```

Example: `unmark 1`
```
Ok, I've marked this task as not done yet:
[T][] Homework
```

Note that the number argument after `mark` and `unmark` refers to the number of the task as shown by the `list` command

## Finding tasks
Bobby also lets users find specific tasks with a query
Any tasks with a name that includes the query will be returned.
Note that the search is *not* case-sensitive!

Example: `find assigment`
```
Here are the tasks matching your query:
2. [D][] Assignment (by: Oct 10 2019 10:10)
```

## Removing tasks
Of course, Bobby also lets users remove any tasks that no longer need to be tracked by the task list.
This command is similar in format to `mark`

Example: `delete 3`
```
Got it. I've removed this task:
[E][] Concert (from: Oct 05 2020 16:30 to: Oct 05 2020 19:00) 
Now you have 2 tasks in the list
```

Note that the number argument after `delete` refers to the number of the task as shown by the `list` command

## Sorting tasks
Sometimes, we want to be able to conveniently order the tasks by their dates and times, so that we can easily know
what to do next. Bobby allows users to do just that, with the `sort` command!

Right now, Bobby is able to sort the tasklist in two ways: ascending via the `asc` argument, and descending via the `desc`
argument. 

For sorting, todo tasks (that do not have datetimes) will be at the top of the list

Example: `sort asc`
```
List has been sorted in ascending order!
```

Example: `sort desc`
```
List has been sorted in descending order!
```

After this command is executed, the next time users `list` all the tasks out, it will be in the desired order
