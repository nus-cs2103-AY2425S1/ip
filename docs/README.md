# Weeny User Guide

[Product screenshot](/Ui.png)

## Adding todos

Add general task to your task list.

Example: `todo Clean the room`

The task gets added to the list for you to check off later

```
Gotcha, I have added:
[T][ ] Clean the room
You have a total of 16 tasks in the list.
```

## Adding deadlines

Add task which has deadlines to your task list.

Example: `deadline Prepare Presentation /by 05/09/2024 0930`

The deadline gets added to the list for you to check off later

```
Gotcha, I have added:
[D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
You have a total of 17 tasks in the list.
```

## Adding events

Add task which has start and end date to your task list.

Example: `event English Test /from 05/09/2024 0930 /to 05/09/2024 1030`

The event gets added to the list for you to check off later

```
Gotcha, I have added:
[E][ ] English Test (from: Sep 5 2024 9:30 am to: Sep 5 2024 10:30 am)
You have a total of 18 tasks in the list.
```

## Deleting tasks

Remove task from the task list.

Example: `delete 15`

Task is deleted from the list.

```
Spooof! The task magically disappeared:
[T][X] plan vacation
You have a total of 15 tasks in the list.
```

## Marking tasks as done
 
Mark a task as completed in your task list.

Example: 'mark 5'

The task is marked as completed.

```
Nice! I've marked this task as done:
[T][X] Clean the room
You have a total of 16 tasks in the list.
```

## Unmarking tasks as done

Unmark a previously completed task, indicating that it's not yet done.

Example: 'unmark 5'

// A description of the expected outcome goes here 
The task is marked as incomplete.

```
You're going back on your words? Tsk I have unmarked:
[T][ ] Clean the room
You have a total of 16 tasks in the list.
```

## Listing all tasks

View all tasks currently in your task list.

Example: 'list'

// A description of the expected outcome goes here 
All tasks in your task list will be displayed.

```
Here you go! All the tasks you have are here:
1. [T][ ] Clean the room
2. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
3. [E][ ] English Test (from: Sep 5 2024 9:30 am to: Sep 5 2024 10:30 am)
...
You have a total of 16 tasks in the list.
```

## Finding tasks by keyword

Search for tasks in your list that match a keyword.

Example: 'find presentation'

Tasks that contain the keyword are displayed.

```
Hmm.. these are the tasks that matches 
1. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
```

## Viewing schedule

View all tasks that have start and end dates, sorted chronologically.


Example: 'schedule 22/08/2024'

All scheduled tasks are shown in chronological order.

```
I have gathered all the tasks you have on 22/08/2024:
1. [T][ ] Read Book
...
4. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
...
8. [E][ ] Team meeting (from: Aug 22 2024 10:00 am to: Aug 22 2024 11:30 am)
...
```

## Exiting the application

End the session and close the application.

Example: 'bye'

The application saves your data and exits.

```
I guess it's my meal time! Bye!
```