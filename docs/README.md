# TheOrangeRatchetCat User Guide

![Image of GUI](https://github.com/OrangeCatLoves/ip/blob/master/docs/Ui.png?raw=true)

TheRatchetCat is here to organise tasks for you! 
> [!IMPORTANT]
> Spacing should be observed between each command entity in a single command

## Commands
**"list"** list command lists all the for you
> list

**"cp"** cp command changes the priority of specified task for you
> cp "Task number" "Task priority"

**"deadline"** deadline command adds a new Task with a deadline for you  
> deadline "Task Description" /by "YYYY-MM-DD"

**"delete"** delete command a task for you
> delete "Task index"

**"event"** event command adds a new Task with a starting and ending date
> event "Task Description" /from "YYYY-MM-DD" /to "YYYY-MM-DD"

**"find"** find command displays relevant tasks that correspond to the string input
> find "input"

**"mark"** mark command marks a specific task as done
> mark "Task index"

**"unmark"** unmark command unmark a specific task as done
> unmark "Task Index"

**"on"** on command displays relevant tasks that correspond to the date input
> on "YYYY-MM-DD"

**"todo"** todo command adds a new ToDo Task
> todo "TaskDescription"

**"bye"** For you to find out!
> bye


Example: `list`

```
RatchetCat says:
Here are the tasks in your list:
1.[E][ ] CS2100 (from: Sep 11 2024 to: Sep 12 2024)
2.[E][ ] CS2101 (from: Sep 21 2024 to: Sep 30 2024)
3.[D][X] CS2103T (by: Nov 28 2024)
4.[E][X] CS2102 (from: Jan 15 2025 to: Apr 29 2025)
```

## Task adding feature

Users can add new tasks into the their list of tasks


## Finding task feature

Users can search for tasks with matching keywords

## Task prioritisation

Users can change the priority of different tasks
