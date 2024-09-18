# Botty User Guide

![Ui.png](Ui.png)

Botty is your chatbot task manager that will help you store and retrieve your tasks.

## Adding todos

Todos are tasks with only a description. Botty allows you to add these to your list.

Example: `todo homework`

This adds a todo with description homework to your list

```
I have added the following task to the list!
[T] [ ] homework
You now have 1 task.
```

## Adding deadlines

Deadlines are tasks that have an end date. Botty allows you to add these to your list.

Example: `deadline homework /by 2024-09-15`

This adds a deadline with description homework and end date 15th September 2024 to your list
```
I have added the following task to the list!
[D] [ ] homework (by: 15 Sep 2024)
You now have 1 task.
```

## Adding events

Events are tasks with a start and end date. Botty allows you to add these to your list.

Example: `event career fair /from 2024-09-10 /to 2024-09-12`

This adds an event with description career fair, start date 10th September 2024 and end date 12th September 2024 to your list.

```
I have added the following task to the list!
[E] [ ] career fair (from: 10 Sep 2024 to: 12 Sep 2024)
You now have 1 task.
```

## Listing tasks

Botty allows you to list out your current tasks.

Example: `list`

This lists all tasks in the task list.

```
Here you go!
1. [T] [ ] homework
2. [D] [ ] homework (by: 15 Sep 2024)
2. [E] [ ] career fair (from: 10 Sep 2024 to: 12 Sep 2024)
```

## Marking and unmarking tasks

Botty allows you to mark and unmark your current tasks.

Example: `mark 1`

This marks the first task in the list
```
Congrats on completing that! Let me just mark that as done for you.
[T] [X] homework
```

Example: `unmark 1`

This unmarks the first task in the list
```
It's okay, we can get that done later. I'll mark that as undone for you.
[T] [ ] homework
```

## Finding tasks

Botty allows you to search for tasks that contain certain words or phrases

Example: `find homework`

This lists all the tasks that contain the word homework.

```
Here are the tasks that matched your search!
1. [T] [ ] homework
2. [D] [ ] homework (by: 15 Sep 2024)
```

## Updating tasks

Botty allows you to update your tasks.

Example: `update 3 /desc science fair /from 2024-09-11 /to 2024-09-13`

This updates the task with the given parameters.

```
Got it! I have updated the following task:
[E] [ ] science fair (from: 11 Sep 2024 to: 13 Sep 2024)
```

## Exiting

Use this command to get a goodbye message from botty

Example: `bye`

Botty will give you a goodbye message

```
Goodbye! Thanks for your continued patronage.
```