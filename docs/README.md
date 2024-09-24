# RapGod User Guide 🎤

> "You don't get another chance, life is no Nintendo game."  
> — Eminem

RapGod helps you manage both life and work seamlessly, ensuring you never miss a beat. It's:

- **Text-based**: Easy to use with a simple interface.
- **Easy to learn**: Get started quickly without a steep learning curve.
- **FAST**: Extremely efficient for managing tasks.

## Demonstration 📸
![img.png](Ui.png)

## Adding Tasks ⏳

To add a new task, use the following command:

```
ADD abc
```
This command adds a new task named **abc**.

### Example:
```
ADD Buy groceries
```
**Expected outcome:**
```
Task "Buy groceries" has been added. ✅
```

## Setting Deadlines and Events 📅

### Add a Deadline
To specify a deadline for a task, use:

```
/BY <time>
```
**Example:**
```
ADD Finish report /BY 01/10/2024
```
**Expected outcome:**
```
Task "Finish report" with deadline 01/10/2024 has been added.
```

### Set Up an Event
To create an event from x to y, use:

```
/FROM <time1> /TO <time2>
```
**Example:**
```
ADD Meeting /FROM 23/08/2024 1900 /TO 23/08/2024 2100
```
**Expected outcome:**
```
Event "Meeting" from Aug 23 2024 7:00pm to Aug 23 2024 9:00pm has been scheduled.
```

### Reschedule a Deadline
To reschedule the nth task's deadline, use:

```
RESCHEDULE n /BY <new time>
```
**Example:**
```
RESCHEDULE 1 /BY 23/08/2024 1900
```
**Expected outcome:**
```
Task Meeting has been rescheduled to be by Aug 23 2024 7:00pm
```

### Change Event Schedule
To change the schedule of the nth event task, use:

```
RESCHEDULE n /from <new time 1> /to <new time 2> 
```
**Example:**
```
RESCHEDULE 1 /FROM 23/08/2024 1900 /TO 23/08/2024 2200
```
**Expected outcome:**
```
Task Meeting has been rescheduled to be from Aug 23 2024 7:00pm to Aug 23 2024 10:00pm
```

## Time Format 🕒
Use the following format for deadlines and events:

- **Date:** dd/MM/yyyy
- **Date and Time:** dd/MM/yyyy HHmm

## Viewing Tasks 👀

### List All Tasks
To see the full list of tasks, use:

```
LIST
```
**Expected outcome:**
```
1. Buy groceries
2. Finish homework
```

### Find Specific Tasks
To filter tasks by keywords, use:

```
FIND abc, def
```
This command searches for tasks containing **abc** or **def**.

### Example:
```
FIND homework, groceries
```
**Expected outcome:**
```
1. Finish homework
2. Buy groceries
```

## Task Management ✅

### Mark a Task as Done
To mark the nth task as done, use:

```
MARK n
```
**Example:**
```
MARK 1
```
**Expected outcome:**
```
Task 1 has been marked as done. 🎉
```

### Unmark a Task
To mark the nth task as not done, use:

```
UNMARK n
```
**Example:**
```
UNMARK 1
```
**Expected outcome:**
```
Task 1 has been marked as not done. ❌
```

### Delete a Task
To delete the nth task, use:

```
DELETE n
```
**Example:**
```
DELETE 1
```
**Expected outcome:**
```
Task 1 has been deleted. 🗑️
```


