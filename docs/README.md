# Naega Task Manager User Guide

![Ui.png](Ui.png) <!-- Add an actual path to the product screenshot here -->

Naega is a powerful and easy-to-use task manager for keeping track of todos, deadlines, and events. With its simple syntax and user-friendly interface, Naega helps you stay organized and productive.

## Adding Todos

The `todo` command adds a basic todo task to your task list. It doesn't have any date or time associated with it—just the task description.

### Example usage
```
todo read book
```

### Expected outcome
````
Got it. I've added this task:
[T][ ] read book
Now you have X tasks in the list.
````
## Adding Deadlines

To add a deadline to your task list, use the `deadline` command followed by the task description and the deadline date/time in the format `yyyy-MM-dd HHmm`.

### Example usage
```
deadline submit report /by 2024-09-20 1800
```
### Expected outcome
````
Got it. I've added this task:
[D][ ] submit report (by: Sep 20 2024 06:00PM)
Now you have X tasks in the list.
````
## Adding Events

To add an event to your task list, use the `event` command followed by the task description, the start time with /from, and the end time with /to. The format for the date and time is yyyy-MM-dd HHmm.
### Example usage
```
event attend conference /from 2024-09-20 0900 /to 2024-09-20 1700
```
### Expected outcome
````
Got it. I've added this task:
[E][ ] attend conference (from: Sep 20 2024 09:00AM to: Sep 20 2024 05:00PM)
Now you have X tasks in the list.
````
## Marking a Task as Done

To mark a task as done, use the `mark` command followed by the task number in the list.
### Example usage
```
mark 2
```
### Expected outcome
````
Nice! I've marked this task as done:
[D][X] submit report (by: Sep 20 2024 06:00PM)
````
## Unmarking a Task (Mark as Not Done)

To unmark a task (mark it as not done), use the `unmark` command followed by the task number in the list.
### Example usage
```
unmark 2
```
### Expected outcome
````
OK, I've marked this task as not done yet:
[D][ ] submit report (by: Sep 20 2024 06:00PM)
````

## Deleting a Task

To delete a task, use the `delete` command followed by the task number in the list.
### Example usage
```
delete 3
```
### Expected outcome
````
Noted. I've removed this task:
[T][ ] read book
Now you have X tasks in the list.
````
## Listing All Tasks

To display all tasks in your task list, use the `list` command. This will show the list of tasks along with their status (done or not), type, and any relevant dates or times.
### Example usage
```
list
```
### Expected outcome
````
Here are the tasks in your list:
1. [T][ ] read book
2. [D][X] submit report (by: Sep 20 2024 06:00PM)
3. [E][ ] attend conference (from: Sep 20 2024 09:00AM to: Sep 20 2024 05:00PM)
````

## Finding Tasks by Keyword

You can find tasks that contain a certain keyword in their description by using the `find` command. It searches all tasks for matching keywords.
### Example usage
```
find report
```
### Expected outcome
````
Here are the matching tasks in your list:
1. [D][ ] submit report (by: Sep 20 2024 06:00PM)
````
## Exiting the Application

To exit the Naega application, use the `bye` command. This will save your tasks and close the app.
### Example usage
```
bye
```
### Expected outcome
````
Bye. Hope to see you again soon!
````
