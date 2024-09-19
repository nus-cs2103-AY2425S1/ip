# Tars User Guide

![](Ui.png)

Welcome to **Tars** chatbot application. Your one-stop Task manager.

TARS handles 3 types of Tasks  
 - ToDo (simple tasks without any time requirement)
 - Deadline (a task that should be completed withing a time)
 - Event (an event occuring from a particular time to another time)

These tasks are stored in a list, which can be accessed by entering `list`

Besides adding these 3 task types you can also conduct other operations 
 - Mark the task as done, stating its index, eg: `m 2`
 - Unmark a task as not done,stating its index, eg: `um 2`
 - Delete a task from the list of tasks, stating its index, eg: `delete 2`
 - Find a task based on the name of the task, eg: `find read book`


## Adding ToDos
Adding a ToDos Task, eg: `d finish assignment`

This is how TARS would reply after adding the task!
```
Got it. I've added this task: finish assignment
Now you have 4 tasks in the list.
```

## Adding Deadline

Add a Deadline Task, stating the date and time in (YYYY-MM-DD HH:TT)

Example: `d read book /by 2019-10-15 18:30`

## Adding Events

Add an Event Task, stating start and end of the event. 
Format for stating the date and time is (YYYY-MM-DD HH:TT)

Example: `e group meeting /from 2019-10-15 18:30 /to 2019-10-15 20:00`