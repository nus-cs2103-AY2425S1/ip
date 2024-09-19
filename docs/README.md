# Cloudy User Guide

![Screenshot of Cloudy bot]
(/Ui.png)

Cloudy is a bot that helps you to keep track of your tasks! Whether they are tasks to do, tasks with a deadline, or an 
event, let Cloudy take care of it for you.

## Adding todos

Add a todo task by typing:

**todo [YOUR TASK]**

Example: `todo eat ice cream`

Your task will be displayed as:

```
[T][] eat ice cream
```

## Adding deadlines

Add a task with a deadline by typing:

**deadline [YOUR TASK] /by [DD/MM/YYYY]**

Example: `deadline finish my assignment /by 10/10/2024`

Your task will be displayed as:

```
[D][] finish my assignment (by: 10 October 2024)
```

## Adding events

Add an event task by typing:

**event [YOUR TASK] /from [DD/MM/YYYY] /to [DD/MM/YYYY]**

Example: `event vacation /from 01/01/2024 /to 06/01/2024`

Your task will be displayed as:

```
[E][] vacation (from: 1 January 2024 to: 6 January 2024)
```


## Viewing your list

View your list by typing:

**list**

Example: `list`

Your tasks will be displayed as:

```
Here are the tasks in your list:
1. [T][] eat ice cream
2. [D][] finish my assignment (by: 10 October 2024)
3. [E][] vacation (from: 1 January 2024 to: 6 January 2024)
```

## Marking a task as done

Mark a task as done by typing:

**mark [TASK NUMBER]**

Example: `mark 1`

This will be displayed as:

```
[T][X] eat ice cream
```

## Deleting a task

Delete a task by typing:

**delete [TASK NUMBER]**

Example: `delete 1`

This will be displayed as:

```
Noted. I've removed this task:
[T][X] eat ice cream
Now you have 2 tasks in the list. 
```