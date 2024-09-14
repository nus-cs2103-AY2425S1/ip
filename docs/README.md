# Rob User Guide

![Screenshot of product showing chatbot in action.](https://linette-g.github.io/ip/Ui.png)


Rob is here to streamline your task management and make your life easier!

## Adding todo tasks
Adds a task to the task list.

Format: todo TASK

Example: `todo buy eggs`

Returns a message confirming that the task has been added.
```
____________________________________
added: [T][ ] buy eggs
____________________________________
Now you have 1 task(s) in the list.
```

## Adding deadlines

Adds a deadline task that needs to be done before a specific date/time to the task list. 
Deadline can be in the format YYYY-MM-DD.

Format: deadline TASK /by DEADLINE

Example: `deadline buy milk /by 2024-10-10`

Returns a message confirming that the deadline task has been added.

```
____________________________________
added: [D][ ] buy milk (by: 10 Oct 
2024)
____________________________________
Now you have 2 task(s) in the list.
```

## Adding events 

Adds an event task that starts at a specific date/time and ends at a specific date/time to the task list.

Format: event TASK /from START /to END

Example: `event project meeting /from Mon 2pm /to 4pm`

Returns a message confirming that the event task has been added.

```
____________________________________
added: [E][ ] project meeting (from: 
Mon 2pm to: 4pm)
____________________________________
Now you have 3 task(s) in the list.
```


## Delete tasks

Deletes a task with a specified task number.

Format: delete NUMBER

Example: `delete 1`

Returns a message confirming that the task has been deleted.

```
____________________________________
deleted: [T][ ] buy eggs
____________________________________
Now you have 2 task(s) in the list.
```

## Mark tasks

Marks a task with a specified task number as done.

Format: mark NUMBER

Example: `mark 1`

Returns a message confirming that the task has been marked as done.

```
____________________________________
Nice! I've marked this task as done:
[D][X] buy milk (by: 10 Oct 2024)
____________________________________
```

## Unmark tasks

Unmarks a task with a specified task number.

Format: unmark NUMBER

Example: `unmark 1`

Returns a message confirming that the task has been marked as done.

```
____________________________________
OK, I've marked this task as not done yet: 
[D][ ] buy milk (by: 10 Oct 2024)
____________________________________
```

## See task list

Shows the task list.

Format: list

Returns the saved task list.

```
____________________________________
[D][ ] buy milk (by: 10 Oct 2024)
[E][ ] project meeting (from: Mon 2pm 
to: 4pm)
____________________________________
```

## Search tasks by description
Finds tasks whose descriptions contain any of the given keywords.

Format: find KEYWORDS

Example: `find buy m`

Returns a list of tasks containing the keyword.

```
____________________________________
[D][ ] buy milk (by: 10 Oct 2024)
[E][ ] buy mushrooms (from: Thu 4pm 
to: 6pm)
[T][X] buy magnolias
____________________________________
```


## Force add duplicates

Force add duplicate tasks with the same task description. 
Task list will contain multiple task with the same description.

Format: force TASK_COMMAND

Example: `force deadline buy milk /by 2024-10-10`

Returns a message confirming that the duplicate task has been added.

```
____________________________________
added: [D][ ] buy milk (by: 10 Oct 
2024)
____________________________________
Now you have 3 task(s) in the list.
```

## Exit
Exit the chatbot and closes window after exit message shown.

Format: bye

Returns the exit message.

```
Bye. Hope to see you again soon!
```


## Acknowledgements
<a href="https://www.freepik.com/icon/kitty_763789">Rob Icon by Freepik</a>

<a href="https://www.freepik.com/icon/little-red-riding-hood_3530945">User Icon by Freepik</a>

Main, MainWindow, DialogBox, Launcher classes and DialogBox.fxml, MainWindow.fxml files have code obtained /modified from 
<a href="https://se-education.org/guides/tutorials/javaFx.html">JavaFX tutorial @SE-EDU/guides</a>