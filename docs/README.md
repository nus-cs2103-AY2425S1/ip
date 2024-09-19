# Kira User Guide

![Screenshot 2024-09-19 at 1.05.10 PM.png](..%2F..%2F..%2FDesktop%2FScreenshot%202024-09-19%20at%201.05.10%E2%80%AFPM.png)

Kira is your personal secretary that is not just cute, but keeps track of all the overwhelming things you need to do!

## Adding Todo Tasks

You can add a todo task.

Example: `todo read book`

```
Got it. I've added this task:
[ ][T][ ] read book
Now you have 1 task in your list.
```

## Adding Deadline Tasks

You can add a deadline task. This includes a due date.

Example: `deadline assignment /by 20/9/2024 2359`

```
Got it. I've added this task:
[ ][D][ ] assignment (by: Sep 20 2024 23:59)
Now you have 2 tasks in your list.
```

## Adding Event Tasks

You can add an event task. This includes a start and an end date.

Example: `event career fair /from 10/10/2024 1400 /to 10/10/2024 1600`

```
Got it. I've added this task:
[ ][E][ ] career fair (from: Oct 10 2024 14:00 to: Oct 10 2024 16:00)
Now you have 3 tasks in your list.
```

## Viewing List of Tasks

You can view the current list of all your tasks.

Example: `list`

```
Here are the tasks in your list:
1.[ ][T][ ] read book
2.[ ][D][ ] assignment (by: Sep 20 2024 23:59)
3.[ ][E][ ] career fair (from: Oct 10 2024 14:00 to: Oct 10 2024 16:00)
```

## Deleting Tasks

You can delete tasks from the list by referring to the task index.

Example: `delete 3`

```
Noted. I've removed this task:
[ ][E][ ] career fair (from: Oct 10 2024 14:00 to: Oct 10 2024 16:00)
Now you have 2 tasks in your list.
```

## Marking and Unmarking Tasks as Done

You can mark tasks as done. These tasks will appear in the list with a cross. You can undo this by unmark the tasks.

Example: `mark 2`

```
Nice! I've marked this task as done:
[ ][D][X] assignment (by: Sep 20 2024 23:59)
```

Example: `unmark 2`

```
OK, I've marked this task as not done yet:
[ ][D][ ] assignment (by: Sep 20 2024 23:59)
```

## Finding Tasks by Keyword

You can search for tasks whose names contain a specific keyword.

Example: `find book`

```
Here are the matching tasks in your list:
1.[ ][T][ ] read book
```

## Prioritising and Unprioritising Tasks

You can mark tasks to be a priority. These tasks will appear in the list with an exclamation mark. You can undo this by unprioritising the tasks.

Example: `prioritise 2`

```
Gotchu, this is now a priority:
[!][D][ ] assignment (by: Sep 20 2024 23:59)
```

Example: `unprioritise 2`

```
OK, this is no longer a priority:
[ ][D][ ] assignment (by: Sep 20 2024 23:59)
```