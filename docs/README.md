# Itadel User Guide

![Ui.png](Ui.png)
Welcome to Itadel! The number one task management chatbot
to handle your daily tasks, deadlines and events!

## Features
The features of Itadel includes:
1. list
2. add todo
3. add deadline
4. add event
5. mark/unmark as complete
6. search with keywords
7. manage tags in tasks

## List
The feature allows you to see existing tasks (Todo, Deadline, Event) in your
list.

### Command format

`list`

> `list`
> 
>       1. [T][ ] sleep
>       2. [T][ ] Work on CS2103T ip

## Adding Todo

The feature allows you to add Todo tasks for you
to handle normal tasks.

### Command format

`todo <task name>`

> `todo Work on CS2103T ip`
> 
>       Got it! I have added: [T][ ] Work on CS2103T ip | []
>
>       Now you have 2 tasks in the list

## Adding Deadlines

The feature allows you to add deadlines for you 
to handle tasks that are time sensitive. 

### Command format

`deadline <task name> /by <time>`

`<time>` should be of format `dd/MM/yyyy` `HH:mm`
where `dd` = date, `MM` = month, `yyyy` = year,
`HH` = hour, `mm` = minute 

> `deadline assignment 1 /by 18/09/2024 23:59`
> 
>       Got it! I have added: [D][ ] assignment 1 (by: 18/09/2024 23:59) | []
>
>       Now you have 3 tasks in the list

## Adding Events

The feature allows you to add events for you
to handle upcoming events.

### Command format

`event <task name> /from <start time> /to <end time>`

`<start time>` and `<end time>` should be of format `dd/MM/yyyy` `HH:mm`
where `dd` = date, `MM` = month, `yyyy` = year,
`HH` = hour, `mm` = minute

## Deleting tasks

The feature allows you to delete tasks (Deadline, Event, Todo)
from the existing list.

### Command format

`delete <Task Index>`

`<Task Index>` refers to the index of the 
task in the `TaskList` array.

>`list`
>
>       1. [T][ ] sleep
>       2. [T][ ] sleep twice
>       3. [D][ ] assignment 1 (by: 18 09 2024 23:59)
>
>`delete 1`
>
>       Noted. I've removed this task: [T][ ] sleep | []
>
>       Now you have 2 tasks in the list
## Mark/Unmark tasks

The feature allows you to mark a task (Deadline, Event, Todo)
as complete/ unmark a task.

### Command format

`mark <Task Index>`

Marks the task as completed.

`<Task Index>` refers to the index of the
task in the `TaskList` array.

`unmark <Task Index>`

Unmarks the task from being completed.

`<Task Index>` refers to the index of the
task in the `TaskList` array.

>`list`
>
>       1. [T][ ] sleep twice
>       2. [D][ ] assignment 1 (by: 18 09 2024 23:59)
>
>`mark 1`
>
>       Nice! I've marked this task as done:
>
>       [T][X] sleep twice | []
> 
> `list`
>
>       1. [T][X] sleep twice
>       2. [D][ ] assignment 1 (by: 18 09 2024 23:59)
>
>`unmark 1`
> 
>       Nice! I've marked this task as not done:
> 
>       [T][ ] sleep twice | []


## Search with keywords

The feature allows you to search tasks (Deadline, Event, Todo) that contain
the keywords.

### Command format

`find <keyword>`

>`find sleep`
> 
>       1. [T][ ] sleep twice
>       
>       Here are the matching tasks in your list:1. [T][ ] sleep twice

## Managing Tags
The feature allows you to manage tags in your tasks (Deadline,
Event, Todo), including adding, removing and getting all
tags.

### Command Format

`addtag <Task Index> <Tag Name>`

Adds the tag to the task.

`<Task Index>` refers to the index of the
task in the `TaskList` array.

`<Tag Name>` refers to the name of the tag
added.


`removetag <Task Index> <Tag Index>`

Removes the tag in the tag index from the task.

`<Task Index>` refers to the index of the
task in the `TaskList` array.

`<Tag Index>` refers to the index of the
tag in the tag list associated with the task.


`gettag <Task Index>`

Gets all the tags associated with the task.

`<Task Index>` refers to the index of the
task in the `TaskList` array.

>`list`
>
>     1. [T][ ] sleep twice
>     2. [D][ ] assignment 1 (by: 18 09 2024 23:59)

>`addtag 1 sleep 8 hours`
>
>     Tag: sleep 8 hours has been added to task:
>
>     [T][ ] sleep twice | [sleep 8 hours]
>
>`addtag 1 sleep on comfortable bed`
>
>     Tag: sleep on comfortable bed has been added to task:
>
>     [T][ ] sleep twice | [sleep 8 hours, sleep on comfortable bed]
>
>`gettag 1`
>
>       Below are the list of tags related to task:
>
>       [T][ ] sleep twice | [sleep 8 hours, sleep on comfortable bed]
>       1. sleep 8 hours
>       2. sleep on comfortable bed
>
>`removetag 1 2`
>
>       Tag: sleep on comfortable bed has been removed from the task:
>
>       [T][ ] sleep twice | [sleep 8 hours]
>
>`gettag 1`
>
>       Below are the list of tags related to task:
>
>       [T][ ] sleep twice | [sleep 8 hours]
>       1. sleep 8 hours

## Bye
The feature allows you to exit the program.

### Command Format

`bye`

>`bye`
> 
>       Bye. Hope to see you again soon!
