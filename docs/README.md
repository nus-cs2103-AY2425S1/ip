# Bigdog User Guide

// Product screenshot goes here

Bigdog is an app designed to manage three types of tasks: To-Do tasks, 
Deadline tasks with specific completion dates, and Event tasks scheduled within a defined timeframe. 
It features functionalities that allow users to view their schedule on a specific day, delete tasks, mark them as done, 
unmark them for re-evaluation, and view all tasks at once. This structure enhances productivity and organization, 
providing an efficient way for users to manage their tasks and stay on top of their responsibilities.

## Creating To-do Tasks

Adds a Task of type To-do to the list with the provided description.

Format: `taskType taskDescription`

Example: `todo buy groceries`

The task should be saved into your list and the following confirmation message should appear:

```
Got it I've added this task:
[T][ ] buy groceries
Now you have 1 tasks in the list.
```

## Creating Deadlines Tasks

Adds a Task of type Deadline to the list with the provided description.

Format: `taskType taskDescription /by DD/MM/YYYY HH:mm`

Example: `deadline study for midterms /by 23/09/2024 23:00`

The task should be saved into your list and the following confirmation message should appear:

```
Got it I've added this task:
[D][ ] study for midterms (by: 23 Sep 2024 23:00)
Now you have 2 tasks in the list.
```

## Creating Events Tasks

Adds a Task of type Event to the list with the provided description.

Format: `taskType taskDescription /from DD/MM/YYYY HH:mm /to DD/MM/YYYY HH:mm`

Example: `event meeting with Elango /from 27/09/2024 09:00 /to 27/09/2024 21:00`

The task should be saved into your list and the following confirmation message should appear:

```
Got it I've added this task:
[E][ ] event meeting with Elango (from: 27 Sep 2024 09:00 to: 27/09/2024 21:00)
Now you have 3 tasks in the list.
```

## Marking Tasks

Marks the task at the specified index as done.

Format: `mark taskIndex`

Example: `mark 1`

The task should be saved into your list and the following confirmation message should appear:

```
Nice! I've marked this task as done:
[D][X] study for midterms (by: 23 Sep 2024 23:00)
```

## Unmarking Tasks

Unmarks the task at the specified index as done.

Format: `unmark taskIndex`

Example: `unmark 1`

The task should be saved into your list and the following confirmation message should appear:

```
OK, I've marked this task as not done yet:
[D][ ] study for midterms (by: 23 Sep 2024 23:00)
```

## Deleting individual tasks

Deletes the task at the specified index.

Format: `delete taskIndex`

Example: `delete 1`

The task should be removed from your list and the following confirmation message should appear:

```
Noted. I've removed this task:
[T][ ] buy groceries
Now you have 2 tasks in the list.
```

## Listing stored Tasks

Displays all the current tasks on the list, whether marked or unmarked.

Example: `list`

The tasks on your list should be listed with an indication if they are marked or not.

```
Here are the tasks in your list:
1. [D][ ] study for midterms (by: 23 Sep 2024 23:00)
2. [E][ ] meeting with Elango (from: 27 Sep 2024 09:00 to: 27 Sep 2024 21:00) 
```

## Finding similar tasks

Displays any tasks that contain a specified keyword or phrase

Format: `find keyword(s)`

Example: `find Elango`

The tasks that contain the specified keyword(s) should be listed:
```
Here are the tasks in you're list:
2. [E][ ] meeting with Elango (from: 27 Sep 2024 09:00 to: 27 Sep 2024 21:00)
```

## Viewing the schedule for a specific date

Displays all the current unmarked tasks on the list on a specified date.

Format: `view DD/MM/YYYY`

Example: `view 27/09/2024`

```
Here is your schedule for 27/09/2024
1. [E][ ] meeting with Elango (from: 27 Sep 2024 09:00 to: 27 Sep 2024 21:00)
```

## Exiting the program

Exits the program.

Format: `bye`

```
Bye. Hope to see you again soon!
```