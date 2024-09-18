# Bruno User Guide

![Screenshot of the Bruno app in action](Ui.png)

> Will store your tasks in my trusty zippers with my *Stickyyyy Fingaaaz* (Sticky Fingers)!

Bruno will unzip open your brain, find and store any task you have in mind, and make you **headache-free** :100:

## Adding todo tasks

Adding a todo task makes Bruno store a task without any deadline.

Command: todo `task description`

Example: `todo read book`

Bruno will store a todo task with the description "read book"

```
I've unzipped open my brain and added this task to it:
T | [ ] | read book
Now you have 1 tasks stored in my brain.
```

## Adding deadline tasks

Adding a deadline task makes Bruno store a task with a deadline.

Command: deadline `task description` /by `due date`

Example: `deadline finish iP increments /by Fri 23:59`

Bruno will store a deadline task with the description "finish iP increments"
and deadline will be the next Friday on the calendar at 23:59

```
I've unzipped open my brain and added this task to it:
D | [ ] | finish iP increments | by: Sep 20 2024 23:59
Now you have 1 tasks stored in my brain.
```

## Adding event tasks

Adding a event task makes Bruno store a task with a start and end date.

Command: event `task description` /from `start date` /to `end date`

Example: `event PGPR climbing event /from Sat 11am /to Sat 1pm`

Bruno will store an event task with the description "PGPR climbing event"
and start date will be the next Saturday on the calendar at 11am
and end date be will be the next Saturday on the calendar at 1pm

```
I've unzipped open my brain and added this task to it:
E | [ ] | PGPR climbing event | from: Sep 21 2024 11:00 to Sep 21 2024 13:00
Now you have 1 tasks stored in my brain.
```

## Deleting tasks

By deleting a task Bruno no longer stores it. It takes in any number of
task indices to delete.

Command: delete `task indices`

Example: `delete 1 2`

Bruno will delete task 1 and task 2 from the list.

```
I've unzipped open my brain and removed these tasks:
D | [X] | finish iP increments | by: Sep 20 2024 23:59
T | [X] | read book
Now you have 3 tasks stored in my brain.
```

## Marking tasks

By marking a task, Bruno will update the list by marking the task as done.
It takes any number of task indices to mark.

Command: mark `task indices`

Example: `mark 1 2`

Bruno will mark task 1 and task 2 as done.

```
I've marked these tasks as done:
E | [X] | PGPR climbing event | from: Sep 21 2024 11:00 to Sep 21 2024 13:00
T | [X] | read book
```

## Unmarking tasks

By unmarking a task, Bruno will update the list by marking the task as NOT done.
It takes any number of task indices to unmark.

Command: unmark `task indices`

Example: `unmark 1 2`

Bruno will mark task 1 and task 2 as NOT done.

```
I've marked these tasks as done:
E | [ ] | PGPR climbing event | from: Sep 21 2024 11:00 to Sep 21 2024 13:00
T | [ ] | read book
```

## Listing tasks

Bruno will list the tasks stored.

Command: list

Example: `list`

Bruno will list all the tasks that are currently stored.
```
Here are the tasks stored in my brain:
E | [ ] | PGPR climbing event | from: Sep 21 2024 11:00 to Sep 21 2024 13:00
T | [ ] | read book
D | [ ] | finish iP increments | by: Sep 20 2024 23:59
```

## Finding tasks

Bruno will search for tasks with a specific keywords and list them.

Command: find `keyword`

Example: `find read`

Bruno will look for tasks that have the keyword "read" in their description.

```
Here are the matching tasks I found stored in my brain:
T | [ ] | read book
```