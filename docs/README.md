# Weeny User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
[Product screenshot](/Ui.png)

// Product intro goes here

## Adding todos

// Describe the action and its outcome.
Add general task to your task list.

// Give examples of usage

Example: `todo Clean the room`

// A description of the expected outcome goes here
The task gets added to the list for you to check off later

```
Gotcha, I have added:
[T][ ] Clean the room
You have a total of 16 tasks in the list.
```

## Adding deadlines

// Describe the action and its outcome.
Add task which has deadlines to your task list.

// Give examples of usage

Example: `deadline Prepare Presentation /by 05/09/2024 0930`

// A description of the expected outcome goes here
The deadline gets added to the list for you to check off later

```
Gotcha, I have added:
[D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
You have a total of 17 tasks in the list.
```

## Adding events

// Describe the action and its outcome.
Add task which has start and end date to your task list.

// Give examples of usage

Example: `event English Test /from 05/09/2024 0930 /to 05/09/2024 1030`

// A description of the expected outcome goes here
The event gets added to the list for you to check off later

```
Gotcha, I have added:
[E][ ] English Test (from: Sep 5 2024 9:30 am to: Sep 5 2024 10:30 am)
You have a total of 18 tasks in the list.
```

## Deleting tasks

// Describe the action and its outcome.
Remove task from the task list.

// Give examples of usage

Example: `delete 15`

// A description of the expected outcome goes here
Task is deleted from the list.

```
Spooof! The task magically disappeared:
[T][X] plan vacation
You have a total of 15 tasks in the list.
```

## Marking tasks as done

// Describe the action and its outcome. 
Mark a task as completed in your task list.

// Give examples of usage

Example: 'mark 5'

// A description of the expected outcome goes here 
The task is marked as completed.

```
Nice! I've marked this task as done:
[T][X] Clean the room
You have a total of 16 tasks in the list.
```

## Unmarking tasks as done

// Describe the action and its outcome. 
Unmark a previously completed task, indicating that it's not yet done.

// Give examples of usage

Example: 'unmark 5'

// A description of the expected outcome goes here 
The task is marked as incomplete.

```
You're going back on your words? Tsk I have unmarked:
[T][ ] Clean the room
You have a total of 16 tasks in the list.
```

## Listing all tasks
// Describe the action and its outcome. 
View all tasks currently in your task list.

// Give examples of usage

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
// Describe the action and its outcome. 
Search for tasks in your list that match a keyword.

// Give examples of usage

Example: 'find presentation'

// A description of the expected outcome goes here 
Tasks that contain the keyword are displayed.

```
Hmm.. these are the tasks that matches 
1. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
```

## Viewing schedule
// Describe the action and its outcome. 
View all tasks that have start and end dates, sorted chronologically.

// Give examples of usage

Example: 'schedule 22/08/2024'

// A description of the expected outcome goes here 
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
// Describe the action and its outcome. 
End the session and close the application.

// Give examples of usage

Example: 'bye'

// A description of the expected outcome goes here 
The application saves your data and exits.

```
I guess it's my meal time! Bye!
```