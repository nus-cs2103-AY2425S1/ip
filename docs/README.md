# Gumball User Guide


![Ui of the chatbot](Ui.png)
This is the Gumball chatbot it can add and delete 4
different types of tasks to a list. You can also mark
the tasks.

## Adding deadlines

To add deadlines 
Type 'deadline', followed by 'description' /by 'date' in the format yyyy-mm-dd

Example: `deadline return book /by 2019-10-15 `

A deadline will then be added to your task list.

```
Got it. I've added this task:
[D][] return book(by:Oct 15 2019)
Now you have x tasks in the list.
```

## Adding todos

To add a todo task.
Type 'todos' into the input bar followed by the description of the todo

Example: `todo assignment`

A todo will then be added to your task list.

```
Got it. I've added this task:
[T][] assignment
Now you have 'x' tasks in the list.
```

## Adding Event

To add an event.
Type 'event' into the input bar followed by the description of the todo
then '/from x '/to' y where x and y are times.


Example: `event project meeting /from Mon 2pm /to 4pm
`

A event will then be added to your task list.

```
Got it. I've added this task:
[E][] project meeting (from: Mon 2pm to: 4pm)
Now you have 'x' tasks in the list.
```

## Adding FixedDurationTasks

To add a FixedDurationTasks.
Type 'fixed' into the input bar followed by the description of the fixedDurationTask
the duration of the task in the format 'x /hrs ymins' where x is a positive integer and y is an integer between 0 and 60


Example: `fixed work /for 2 /hrs 30mins
`

A fixedDurationEvent will then be added to your task list.

```
Got it. I've added this task:
[F][] work for:2hrs 30mins
Now you have 'x' tasks in the list.
```

## Adding FixedDurationTasks

To add a FixedDurationTasks.
Type 'fixed' into the input bar followed by the description of the fixedDurationTask
the duration of the task in the format 'x /hrs ymins' where x is a positive integer and y is an integer between 0 and 60


Example: `fixed work /for 2 /hrs 30mins
`

A fixedDurationEvent will then be added to your task list.

```
Got it. I've added this task:
[F][] work for:2hrs 30mins
Now you have 'x' tasks in the list.
```

## List tasks

To get the current list of tasks.
Type 'list' into the input bar.


Example: `list
`

The list will then be shown to you.

```
Your list will be printed here.
```

## Mark task

To mark a task in your list.
Type mark into the input followed 
by the number of the task you want to mark.

Example: `mark 1
` 

The task will then be marked when it is listed

```
Nice! I've marked this task as done:
...
```

## Delete task

To delete a task in your list.
Type delete into the input followed
by the number of the task you want to delete.

Example: `delete 1
`

The task will then be deleted from the list.

```
Nice! I've deleted this task:
...
Now you have x tasks in the list.
```

## Delete task

To delete a task in your list.
Type delete into the input followed
by the number of the task you want to delete.

Example: `delete 1
`

The task will then be deleted from the list.

```
Nice! I've deleted this task:
...
Now you have x tasks in the list.
```

## Find tasks

To find task in your list which 
contain the keyword you enter.
Type find followed by the keyword.

Example: `find book
`

The list containing the tasks containing
the keyword.

```
Your list will be printed here.
```