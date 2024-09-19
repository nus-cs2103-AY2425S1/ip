# Shenhe User Guide
![Ui.png](Ui.png)

Shenhe is an intelligent chatbot designed to help users manage tasks efficiently. With simple text-based commands, 
users can add, delete, and list tasks, set deadlines, and more. Shenhe supports task management, deadlines, events, 
and searching for specific tasks.
## Adding todos
Shenhe allows you to add tasks to your list of tasks. 
Example:
```
//input
todo read book
```

``` 
//output
Got it! I've added this task:
[T][0] read book
Now you have 1 tasks in the list.
``` 

## Adding deadlines
Shenhe allows you to add tasks with deadlines, so you can keep track of when specific tasks need to be completed.
Example: 
```
//input
deadline return book /by 30/09/2024 1800
```

```
//output
Got it! I've added this task:
[D][0] return book (by: 2024-09-30 1800)
Now you have 2 tasks in the list.
```

## Adding events
Shenhe allows you to add events to your list of tasks, so you can keep track of upcoming events.
Example:
```
//input
event project meeting /from 1900 /to 2000
```

``` 
//output
Got it! I've added this task:
[E][0] project meeting (from 1900 to 2000)
Now you have 3 tasks in the list.
```

## Listing tasks
Shenhe allows you to list all the tasks you have added.
Example:
```
//input
list
```

```
//output
Here are the tasks in your list:
1. [T][0] read book
2. [D][0] return book (by: 2024-09-30 1800)
3. [E][0] project meeting (from 1900 to 2000)
``` 

## Marking tasks as done
Shenhe allows you to mark tasks as done when you have completed them.
Example:
```
//input
mark 1
```

```
//output
//output
Nice! I've marked this task as done:
[T][1] read book
```
## Marking tasks as undone
Shenhe allows you to mark tasks as undone when you have not completed them.
Example:
```
//input
unmark 1
```

```
//output
Nice! I've marked this task as not done yet:
[T][0] read book
```

## Deleting tasks
Shenhe allows you to delete tasks from your list of tasks.
Example:
```
//input
delete 1
```

```
//output
Task number: 1
Total number of tasks: 3
Noted. I've removed this task:
[T][0] read book
Now you have 2 tasks in the list.
```


## Finding tasks
Shenhe allows you to search for tasks that contain a specific keyword.
Example:
```
//input
find book
```

```
//output
Here are the matching tasks in your list:
1. [D][0] return book (by: 2024-09-30 1800)
```

## Exiting the program
Shenhe allows you to exit the program.
Example:
```
//input
bye
```

```
//output
Bye traveller. Hope to see you soon!
```
