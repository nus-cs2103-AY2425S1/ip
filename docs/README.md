# YapBot User Guide


![Screenshot of YapBot GUI.](Ui.png)

YapBot is a task management tool that allows you to 
conveniently access and manage tasks quickly and efficiently.

## Features Overview

- Add Tasks
  + [General Tasks](#todo): `todo`
  + [Tasks with deadlines](#deadline): `deadline`
  + [Tasks with start and end times](#event): `event`
- [Mark Task as complete](#mark): `mark`
- [Mark Task as incomplete](#unmark): `unmark`
- [View all Tasks](#list): `list`
- [Delete a Task](#delete): `delete`
- [Search for a task](#find): `find`
- [Load Tasks from a file](#load): `load`
- [Save Tasks to file](#save): `save`

## Documentation

### todo

Adds a todo task.
A todo task is a task with only a description.

_Usage_: `todo <Task details>`

_Example_: `todo drink water`


```
Adding Task...
Success
Task added to database:
    [T][] drink water               
Total tasks: 1
```


### deadline

Adds a deadline task. A deadline task is a task with an ending date and/or time.

_Usage_: `deadline <Task details> /by <deadline>`

_Note_: Only the following formats are allowed for deadlines:

- Date and Time: `Hpm yyyy/mm/dd`
- Date Only (Defaults time to 8am): `yyyy/mm/dd`
- Time Only (Defaults date to today's date): `Hpm`

_Example_: `deadline attend lecture /by 9am 2024/09/03`


```
Adding Task...
Success
Task added to database:
    [D][] attend lecture (by: 9AM 03 Sep 2024)          
Total tasks: 1
```


### event

Adds an event task. 
An event task is a task with a starting and ending date and/or time.

_Usage_: `event <Task details> /from <startDate> /to <endDate>`

_Note_: Only the following formats are allowed for startDate & endDate:

- Date and Time: `Hpm yyyy/mm/dd`
- Date Only (Defaults time to 8am): `yyyy/mm/dd`
- Time Only (Defaults date to today's date): `Hpm`

_Example_: `event revise Java /from 9am 2024/09/03 /to 10am 2024/09/03`


```
Adding Task...
Success
Task added to database:
    [E][] revise Java (From: 9AM 03 Sep 2024 To: 10AM 03 Sep 2024)          
Total tasks: 1
```


### mark

Marks the Task as completed.

_Usage_: `mark <Task Number>`

_Example_: `mark 1`


```
Finding Task...Success
Task Completed:
    [T][X] drink water               
```


### unmark

Marks the Task as incomplete.

_Usage_: `unmark <Task Number>`

_Example_: `unmark 1`


```
Finding Task...Success
Task Incomplete:
    [T][] drink water                
```


### list

Lists all Tasks.

_Usage_: `list`

```
Retrieving Tasks...Success
Current Tasks:
    1.[T][] drink water 
    2.[D][] attend lecture (by: 9AM 03 Sep 2024) 
    3.[E][] revise Java (From: 9AM 03 Sep 2024 To: 10AM 03 Sep                 
```


### delete

Removes the Task.

_Usage_: `delete <Task Number>`

_Example_: `delete 1`


```
Finding Task...Success
Task deleted from database:
    [T][] drink water                
```


### find

Searches for a task by keyword.

_Usage_: `find <keyword>`

_Note_: 
- `find` returns all Tasks that contain the keyword in their task descriptions.
- Search is case-sensitive
- Querying by dates or times is not currently supported.

_Example_: `find drink`


```
Querying database for "drink"...Success
Matching Tasks:
    [T][] drink water                
```

### load

Loads tasks from specified file.

_Usage_: `load <filepath>`

_Note_: Tasks are stored in `data/tasks.txt` by default.

_Example_: `load data/importantTasks.txt`


```
Accessing data at <data/importantTasks.txt>...Success
Use Command "list" to view your tasks.         
```


### save

Saves tasks to the specified file.

_Usage_: `save`

_Note_: Tasks are stored in `data/tasks.txt` by default.


```
Saving Tasks...Success
Your tasks have been saved.       
```