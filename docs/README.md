# BuddyBot User Guide



// Product screenshot goes here

![screenshot](./docs/Ui.png)



Have you ever felt overwhelmed by the tasks you have in your daily life? 
Find yourself unable to keep track of them?
Fret not! BuddyBot is here to save the day!
BuddyBot is your all-in-one assistant to help you organise your tasks!

## Adding Todos

You can add a todo.

This adds a task without any time limit to your task list.

Example: `todo read book`


```
Got it.I've added this task:
[T] [] read book
Now you have 1 tasks in the list.
```

## Adding Deadlines

You can add a task and set the date it should be completed by.

This adds a task with a deadline to your task list.

Example: `deadline read book /by 2024-09-17`


```
Got it.I've added this task:
[D] [] read book by: Sep 17 2024
Now you have 1 tasks in the list.
```
## Adding Events

You can add an event.

This adds a task that falls within a time frame to your task list.

Example: `event read book /from 2024-09-17 /to 2024-09-18`


```
Got it.I've added this task:
[E] [] read book from Sep 17 2024 to: Sep 18 2024
Now you have 1 tasks in the list.
```
## View Task List

Type 'list' into the input box to view the tasks in your list.

Example: `list`

```
These are the tasks in your list:
1. [E] [] read book from Sep 17 2024 to: Sep 18 2024
2. [T] [] go to library
3. [D] [] read report by: Sep 18 2024
```


## Mark as Done

Type 'done <number>' into the input box to mark the number-specified task as done.

Example: `done 2`

```
I've marked this task as done!
[T] [X] go to library
```

## Delete

Type 'delete <number>' into the input box to remove the number-specified task.

Example: `delete 2`

```
Got it. I've removed this task:
[T] [X] go to library
```

## Find

Type 'find <characters>' into the input box to search for tasks in your task lists containing that combination of characters.

Example: `find read`

```
Here are the matching tasks in your list:
1. [E] [] read book from Sep 17 2024 to: Sep 18 2024
2. [D] [] read report by: Sep 18 2024
```

## Leave

Type 'bye' into the input box to exit BuddyBot and save the tasks in your task list.

Example: `bye`

```
Bye. Hope to see you again soon!
```