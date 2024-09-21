# bellroy User Guide

![Example of bellroy in use](/ip/docs/Ui.png)

Having trouble managing your deadlines? Fret not, as bellroy is your new companion.
bellroy will be your new to-do list, helping you keep track of deadlines!
It's Clean, Simple, and ~~Lightweight~~ Featherweight :D

## Features

### Add Task

Users are able to add a task to the tasklist. 

There are 3 types of tasks: todo, deadline, and event

Expected Inputs: 
```
1. todo (task name)
2. deadline (task name) /by (due date of the task, in the format "yyyy-MM-dd HHmm")
3. event (task name) /from (event start) /to (event end)
```

Example Usages:
```
- todo swimming
- deadline homework /by 2024-09-27 1800
- event piano lesson /from Thursday 4pm /to Thursday 6pm
```

Expected Outcome:

```
Hoot Hoot! I've added this task:
[T][ ] swim
Now you have 3 tasks in the list
```

## Delete Task

Users are able to delete a task in the tasklist.

Expected Input:
```
delete (task index)
```

Example Usages:

```
delete 3
```
Expected Outcome:

```
Hoot Hoot! I've removed this task:
[T][ ] run
Now you have 4 tasks in the list.
```


## View list of Tasks

Users are able to see the tasks in the tasklist.

Expected Input:
```
list
```

Example Usages:

```
list
```
Expected Outcome:

```
1. [T][ ] run
2. [T][X] swim
```

## Mark/Unmark tasks

Users are able to mark and unmark a task as done.

Expected Input:
```
mark (index)
unmark (index)
```

Example Usages:

```
mark 4
unmark 3
```
Expected Outcome:

```
Nice! I've marked this task as done:
[T][ ] swim
Hoot Hoot!
```

## Find task

Users are able to find a specific task.

Expected Input:
```
find (input string)
```

Example Usages:

```
find run
```
Expected Outcome:

```
Hoot Hoot! Here are the matching tasks in your list:
1. [T][ ] run
2. [T][ ] run errands
```

## Tagging of tasks

Users are able to tag a task with an association.

Expected Input:
```
tag (index) (association)
```

Example Usages:

```
tag 1 urgent
```
Expected Outcome:

```
Hoot Hoot! I've tagged this task:
1. [T][ ] run
with the association: urgent
```

## Filter by association

Users are able to filter tasks by their association.

Expected Input:
```
filter (association)
```

Example Usages:

```
filter urgent
```
Expected Outcome:

```
Hoot Hoot! Here are the filtered tasks in your list:
1. [T][X] run
2. [T][ ] shower
```

## Filter by association
Users can close and exit the program by using the `bye` command.

## Acknowledgements
bellroy image: Duolingo

User image: Cryptozombies
