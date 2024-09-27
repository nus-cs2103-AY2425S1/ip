# Alexer User Guide

![Alexer Screenshot](Ui.png)

Alexer is a powerful companion that allows you to:
- Create different types of tasks for all your important activites, such as deadlines and events
- Show and view all your created tasks in a neat and easy-to-read manner
- Mark and unmark tasks that have been completed (or yet to be done)
- Find and search tasks that you don't remember the exact details for
- Delete tasks created by mistake or completed tasks that are unneeded anymore

## Adding to-dos

Create something to-do for later.

Example: `todo <description>`

- `todo Eat food`

Alexer will add your to-do to your task list, and you should see something as follows:

```
Sure! I've added the todo to your list.

    [T] [ ] Eat food
    
You have 1 tasks now.
```

## Adding deadlines

Add a task with a certain deadline to your task list.

Example: `deadline <description> /by <YYYY-MM-DD HHmm>`  
- `deadline Something important /by 2024-09-23 2359`

Alexer will add the task to the task list, and you should see something like the following:

```
No problems! I’ve added the task to your list:

    [D] [ ] Something important (by: 23 Sep 2024 11:59 PM)
    
You have 2 tasks now.
```


## Adding events

Need to remember an important event? Just add it to the task list

Example: `event <description> /from <start> /to <end>`
- `event School outing /from Monday of Recess Week /to 29 Sep 2024`

Alexer will add the event to your task list, with the given response:

```
Noted! I’ve added a new event to your tasks:

    [E] [ ] School outing (from: Monday of Recess Week, to: 29 Sep 2024)
    
You have 3 tasks now.
```


## Listing all the tasks

After adding all your tasks, you may use this feature to list all the tasks.

Example: `list`

All your completed and not completed tasks will be shown in a list, with numbered prefix to indicate the task index.

```
Sure thing! Here is your task list:

    1. [T] [ ] Eat food
    2. [D] [ ] Something important (by: 23 Sep 2024 11:59 PM)
    3. [E] [X] School outing (from: Monday of Recess Week, to: 29 Sep 2024)
```


## Marking/Un-marking tasks

Once you have completed the task, you can mark it as done. (Or mark as not done with `unmark`)

Example: `mark <index>` or `unmark <index>`
- `mark 3`
- `unmark 1`

Alexer will mark/un-mark the task in at the index accordingly.

```
Great job completing the task! Keep up the great work!

    [E] [X] School outing (from: Monday of Recess Week, to: 29 Sep 2024)
```

## Find a task

No idea if your task has been added already? Fret not, you can search all tasks with the given keyword easily!

Example: `find <keyword>`
- `find food`


Alexer will let you know a list of tasks with the given keyword. (case sensitive)

```
I got you! Here is what I found:

    [T] [ ] Eat food
```


## Show me all the commands

If you want a quick overview of all the commands, you may use this feature.

Example: `help`

Alexer will gladly tell you all its features and abilities.
