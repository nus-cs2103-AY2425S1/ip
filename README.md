# RapGod User Guide 🎤

> "You don't get another chance, life is no Nintendo game."  
> — Eminem

RapGod helps you manage both life and work seamlessly, ensuring you never miss a beat. It's:

- **Text-based**: Easy to use with a simple interface.
- **Easy to learn**: Get started quickly without a steep learning curve.
- **FAST**: Extremely efficient for managing tasks.

## Demonstration 📸
![image](https://github.com/user-attachments/assets/361b769d-eebc-4a75-8b23-aacfa4ff96e9)

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
/BY z
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
/FROM x /TO y
```
**Example:**
```
ADD Meeting /FROM 09:00 /TO 10:00
```
**Expected outcome:**
```
Event "Meeting" from 09:00 to 10:00 has been scheduled.
```

### Snooze a Deadline
To snooze the nth task's deadline, use:

```
SNOOZE n /by x
```
**Example:**
```
SNOOZE 1 /by 2 days
```
**Expected outcome:**
```
Task 1 deadline has been snoozed by 2 days.
```

### Change Event Schedule
To change the schedule of the nth event task, use:

```
SNOOZE n /from x /to y
```
**Example:**
```
SNOOZE 1 /from 10:00 /to 11:00
```
**Expected outcome:**
```
Event 1 has been rescheduled from 10:00 to 11:00.
```

## Time Format 🕒
Use the following format for deadlines and events:

- **Date:** dd/MM/yyyy
- **Date and Time:** dd/MM/yyyy HH:mm

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


