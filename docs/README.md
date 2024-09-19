# NotAGPT User Guide

[Ui.png](Ui.png)

Welcome to NotAGPT! It's a chatbot that helps you keep track of your tasks.

## Installation
Please run this in a folder where you have permissions to create a directory.
This will be used to store your tasks.

## Tasks

There are 3 types of tasks you can add. Todos, deadlines and events.
Todos have no date associated with them, deadlines have an ending date, while events have a start and end time.

## Viewing the tasklist

To view your tasklist, simply type in list!

Try it with `list`. This will list which test are you have added so far.

If there are no tasks in the list, you should see "No tasks in list".

Otherwise, you should see 
```
1.[T][ ] return book
```

## Exiting the program

To exit the program, simply type `bye`. This closes the program.

## Adding todos

Adding a todo will make it appear in your task list.

You can try `todo (name of task)`, which will add your todo task to the list.

Example: `todo return another book`
You should see the [T][ ] (name of todo)
```
Got it. I've added this task:
[D][ ] finish project
Now you have 2 tasks in the list.
```

## Deleting tasks

To delete a task, simply type `delete {index}`. The index of the first task, which is at the top of the list, is 1.

Example: `delete 1`
```
Noted. I've removed this task":
[T][ ] return book
Now you have 1 task in the list.
```

## Adding deadlines

Adding a todo will make it appear in your task list.

You can try `deadline (name) /by dd/MM/YYYY HHmm`, which will add a deadline task with the specified name and deadline.
Keep in mind that the deadline has to follow that format to work.

Example: `deadline finish project /by 2/12/2019 1800`
You should see the [D][ ] (name of deadline)
```
Got it. I've added this task:
[D][ ] finish project (by: by 2/12/2019 1800)
Now you have 2 tasks in the list.
```

## Adding event

Adding a todo will make it appear in your task list.

You can try `event (name) /from dd/MM/YYYY HHmm /to dd/MM/YYYY HHmm`, which will add an event with the start and end time and the specified name.
Keep in mind that the deadline has to follow that format to work.

Example: `event project meeting /from 2/11/2019 1200 /to 2/11/2019 1400`
You should see the [E][ ] (name of event)
```
Got it. I've added this task:
[E][ ] project meeting (from: 2/11/2019 1200 to: 2/11/2019 1400)
Now you have 3 tasks in the list.
```

## Marking task as done
Now, if you type `list`, you should see see all the tasks you've added.
If you want to mark a task as done, simply type `mark (index)`. 
Example: `mark 2`
You should see the [T][X] (name of task)
```
1. [T][ ] return another book
2. [D][X] finish project (by: 2/12/2019 1800)
3. [E][ ] project meeting (from: 2/11/2019 1200 to: 2/11/2019 1400)
```

## Unmarking task as done
If you want to unmark a task as done, simply type `unmark (index)`. 
Example: `unmark 2`
You should see the [T][ ] (name of task)
```
1. [T][ ] return another book
2. [D][ ] finish project (by: 2/12/2019 1800)
3. [E][ ] project meeting (from: 2/11/2019 1200 to: 2/11/2019 1400)
```

## Finding a task
If you want to find a task you've added, type `find (keyword)`. 
Example: `find return`
You should see the [T][1] (name of task)
```
1. [T][ ] return another book
```
