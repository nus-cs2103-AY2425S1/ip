# Rizzler User Guide

![](Ui.png)

Wanna keep track of your tasks?  
Want an easy way to manage task completion?  
Wanna get more rizz?  

Now you can with the Rizzler! He will rizz you up into Skibidi Ohio with
his functionality!


## Adding Tasks

Rizzler allows for 3 types of tasks to be added:
 - Todos
 - Deadlines
 - Events

The format to add one of the respective tasks is:
```
todo [task-name]
deadline [task-name] /by [yyyy-mm-dd]
event [task-name] /from [yyyy-mm-dd] /to [yyyy-mm-dd]
```
Just fill in the task name with what you wanna track and any dates
in the format of yyyy-mm-dd and Rizzler gyat your back!
Not to worry if you can't remember how to key in the commands, Rizzler
will remind you of the correct format if you get it wrong!

Example: `todo your mom`
```
Gotcha! I've added the new task for you:
[T][ ] your mom
Now you've gyat 1 tasks in the list.
```

## Marking and Unmarking Tasks

Rizzler can mark the tasks you add as done or as not done.  
Here's the format of these 2 commands:
```
mark [index-in-list]
unmark [index-in-list]
```
Index-in-list is the task number of the task as shown in the task list.
Rizzler will also catch if the command was entered wrongly. 
Let's see an example of us marking the task added from above.

Example: `mark 1`
```
Skibidi Ohio! You finished your task:
[T][X] your mom
```

## Listing Tasks

Rizzler will list all of the tasks you have added into it, 
including any tasks you have added in previous usages saved into memory.
Call this command with a simple `list`.

Here's an example of how this will look after calling `list` 
with the task above:
```
You've gyat these tasks in your list
1. [T][X] your mom
```

## Deleting Tasks

Rizzler also allows you to delete any tasks that you feel 
you do not need anymore. The command is as follows:
```
delete [index-in-list]
```
This command functions just like mark and unmark, 
together with the handling of any command misinputs.
As an example, let's add a `todo` named {read book} in the list 
and delete it.

Example: `delete 2`
```
I have fanum taxed this task for you:
[T][ ] read book
Now you've gyat 1 tasks in the list.
```

## Finding Tasks

Rizzler can allow you to find all tasks matching a certain keyword for 
easy access. Here's how the command looks:
```
find [keyword]
```
`find` supports spaces in your keywords which allow you to 
search for multi-word phrases. We will add a `todo` with name {read book}
to illustrate this example.

Example: `find read book`
```
I found these tasks matching your keyword
1. [T][ ] read book
```

## Sorting Your Tasks

Rizzler allows the sorting of the 3 different task types in the list. 
Tasks are sorted lexicographically by name. In the case of deadlines, 
they are sorted by their due date, and then their name. 
In the case of events, they are sorted by start date first, then end date,
then by their name. Let's see this in action by first adding the tasks below:
```
deadline read book /by 2024-08-15
deadline homework /by 2024-08-15
deadline homework /by 2024-08-14
```
The result after `sort` and `list` should be:
```
You've gyat these tasks in your list
1. [D][ ] homework (by: Aug 14 2024)
2. [D][ ] homework (by: Aug 15 2024)
3. [D][ ] read book (by: Aug 15 2024)
```