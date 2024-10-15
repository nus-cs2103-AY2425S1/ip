# LunaBot User Guide

![Ui.png](Ui.png)

Use LunaBot to track and manage your tasks easily and effectively!

### Adding Tasks

The following types of tasks can be added with the commands in the format below:

ToDo -> t [description]

Deadline -> d [description] /by [due date]

Event -> e [description] /from [start] /to [end]

eg. t buy groceries for dinner

A ToDo task will be added to you task list with the input as the description 
```
Got it! I've added this task:
[T][ ] buy groceries for dinner
Now you have 1 tasks in the list
```

### View all tasks in your list

Type "ls" to display all tasks currently in your list

```
Here are the tasks in your list:
1. [T][ ] buy groceries for dinner
```

### Mark / Unmark / Delete tasks

Type mark followed by the corresponding number of the task in your list to mark it as done

eg. mark 1 

```
YAY! I've marked this task as done! :
[T][X] buy groceries for dinner
```

Type unmark followed by corresponding number of the task in your list to mark it as not done

eg. unmark 1

```
OK, I've marked this task as not done yet:
[T][ ] buy groceries for dinner
```

Type del followed by the corresponding number of the task in your list to delete it

eg. del 1

```
Okayyy I've deleted this task:
[T][ ] buy groceries for dinner
Now you have 0 tasks in the list.
```

### Find tasks 

Type find followed by a keyword to find the matching tasks in your list

eg. find buy

```
Here are the matching tasks in your list:
1. [T][ ] buy groceries for dinner
```