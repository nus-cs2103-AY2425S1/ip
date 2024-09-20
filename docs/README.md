# Atreides User Guide

![Screenshot of Atreides Chatbot](/docs/Ui.png)


Atreides Chatbot is quality service that will keep track of your events, deadlines and todo tasks. 
Incorporating the flair of House Atreides, you can talk to Paul Muad'Dib Atreides to organise your daily tasks!

## Adding Deadlines

A deadline is a task that must be completed by a specific date time. Atreides chatbot keeps track of the deadline. 

For example, assignment deadlines or project submission due dates can be added. 

Format: `deadline {name} /by {Date Time}`

Example: `deadline finish iP /by 20 SEP 2024 23:59`

Valid Date formats: 
- DD MMM YYYY HH:MM
- YYYY-MM-DD (Time will automatically be set to 23:59)


Atreides chatbot will reply you with Task added and the position of the Deadline task in the list of tasks along with the number of current tasks. 

```
Task added
 [D][] finish iP (by: Sep 20 2024 23:59)
8 tasks in list
```

## Adding Events
An event is a task that has a start time and end time. 

For example, a 2h lecture from 10am to 12pm or a movie from 8pm to 11pm. 

Format: `event {name} /from {Start Date Time} /to {End Date Time}`

Example: `event project meeting /from Aug 6th 2pm /to 4pm`

All date times are valid.

Atreides chatbot will reply you with Task added and the position of the Event task in the list of tasks along with the number of current tasks.

```
Task added
 [E][] project meeting (from: Aug 6th 2pm to: 4pm)
8 tasks in list
```

## Adding Todos

A todo is a task that you would like to be done with no specific deadline.

For example, reading a book, watching a movie.

Format: `todo {name}`

Example: `todo read Dune`

Atreides chatbot will reply you with Task added and the position of the Todo task in the list of tasks along with the number of current tasks.

```
Task added
 [T][] read Dune
8 tasks in list
```

## Features

### 1. Handling Duplicates
The Atreides chatbot has a special feature to ensure that no duplicate events get added. 

For example, if there is a Event task, project meeting (from: Aug 6th 2pm to: 4pm) and another task with the same name, start and end time gets added, Atreides chatbot will prompt that it is a duplicate task.

Atreides chatbot will print the index and the duplicate task to the user.

```
Same Task identified 
8.[E][] project meeting (from Aug 2pm to 4pm)
```

### 2. Finding a task

The Atreides chatbot is able to find tasks and show the details of the task. 

For example, if there is an event task "project meeting (from: Aug 6th 2pm to: 4pm)" and a todo task "prep for project meeting".
If project meeting is searched, it will return the event task and todo task.

`find project meeting`
```
Here are the matching tasks in your list:
1.[E][] project meeting (from: Aug 6th 2pm to: 4pm)
2.[T][] prep for project meeting
```

### 3. Marking/Unmarking a task

You can use the Atreides chatbot to mark tasks that you have completed and unmark tasks that need further revision.
You can mark the task using its 1-based index.

For example, you have a todo task "read book"
```
1.[T][] read book
```
You can `mark 1` to mark the first task
```
Thank you, one task completed:
[T][X] read book
```
The X indicates that the task has been completed. 

Similarly, you can `unmark 1` to unmark the first task
```
Noted, this task has been unmarked
[T][] read book
```

## Summary of commands
| Command                                           | Description                                                                           |
|---------------------------------------------------|---------------------------------------------------------------------------------------|
| `todo <Name>`                                     | Add a todo task with the name of the task                                             |
| `event <Name> /from <Start time> /to <End Time>`  | Add a event task with start and end times.                                            |
| `deadline <Name> /from <Start time> /to <End Time>` | Add a event task with start and end times.                                            |
| `list`                                            | Print the list of tasks with its index numbers and details                            |
| `delete <index>`                                  | Delete a task using on its 1-based index                                              |
| `mark <index>`                                    | Mark a task as completed using its 1-based index                                      |
| `unmark <index>`                                  | Unmark a task as being incompleted using its 1-based index                            |
| `bye`                                             | Command to exit the program                                                           |





