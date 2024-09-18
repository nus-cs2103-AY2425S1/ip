# Snowy User Guide


![Screenshot of a standard usage of Snowy](/Ui.png)

Snowy is a chat bot that is able to help you keep track of all of your tasks.


## Adding deadlines

You are able to add a task that have a deadline and Snowy will display it whenever it is required.

The command should be in the format of : 

`deadline {task name} /by {due date}`

where due date is in the format of yyyy-mm-dd.

Example: `deadline Return book /by 2024-09-24`

Snowy should return a text message that says that the task has been added.

```
New Deadline task added:
[D][] Return book (by September 24, 2024)
```

## Adding todos

You are able to add a simple task and Snowy will display it whenever it is required.

The command should be in the format of :

`todo {task name}`

Example: `todo Read book`

Snowy should return a text message that says that the task has been added.

```
New todo task added:
[T][] Read book
```


## Adding events

You are able to add a task that has a start and end date, Snowy will display it whenever it is required.

The command should be in the format of :

`event {task name} /from {start date} /to {end date}`

where `start date` and `end date` is in the format of yyyy-mm-dd.

Example: `event Holiday /from 2024-09-24 /to 2024-10-01`

Snowy should return a text message that says that the task has been added.

```
New Event task added:
[E][] Holiday (from September 24, 2024 to: October 1, 2024
```

## Change task status
You are able to keep track of what task have you completed.

The command should be in the format of:

`mark {index}` if you want to mark the task as complete or

`unmark {index}` if you want to mark the task as incomplete.

Example: `mark 1`

Snowy should return a text message that says the task status has been changed.
```
Ok, I've marked this task as completed:
[T][X] Read book
```

## List tasks
You are able to show the tasks that you currently have.

The command should be: `list`

Snowy should return a text message with your current task and their completion status.

```
1.[T][X] Read Book
2.[D][] Return Book (by September 24, 2024)
```

## Find tasks
Snowy is able to show your tasks that contains a specific keyword.

The command should be in the format of:

`find {keyword}`

where `keyword` is contained inside the task name.

Example: `find book`

Snowy should return a list of tasks that contain the keyword in the name.

``` 
Here are the matching tasks in your list:
1.[T][X] Read Book
2.[D][] Return Book (by September 24, 2024)
```

## Change tasks date
Snowy is able to change the date stored by different tasks.

The command should be in the format of:

`snooze {index} /by {date}` for deadline tasks or

`snooze {index} /from {start date} /to {end date}` for event tasks

Example: 

`snooze 2 /by 2024-09-25`

or

`snooze 3 /from 2024-09-30 /to 2024-10-05`

Snowy should return the task with its date changed.
```
 Ok, I've changed the date of this task:
 [D][] Return Book (by September 25, 2024)
```

## Delete task
Snowy is able to delete tasks that you do not wish to keep track anymore

The command should be in the format of:

`delete {index}`

Example:

`delete 1`

Snowy should return the task that has been deleted and remove it from the list.

```
Ok, I've deleted this task:
[T][X] Read book
```

## Acknowledgement

The section that creates the GUI, such as `DialogBox.java`, `Launcher.java`, `Main.java`, `MainWindow.java`, 
as well as the relevant css and fxml files are modified from:

https://se-education.org/guides/tutorials/javaFx.html.
