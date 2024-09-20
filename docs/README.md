# XBot User Guide

XBot will help you to track your tasks and to cheer you on!!

---

## Overview of Available Commands

1. Add todo tasks - `todo <description>`
2. Add deadline tasks - `deadline <description> /by <date>`
3. Add event tasks - `event <description> /from <date> /to <date>`
4. List all tasks - `list`
5. Mark the task as done - `mark <task number>`
6. Mark the task as undone - `unmark <task number>`
7. Find a task - `find <prefix>`
8. Delete the task - `delete <task number>`
9. View the tasks on certain date - `view <date>`
10. Close the application - `bye`

---

## Add Tasks

### Todo tasks

Command: `todo <description>`

Examples: 

```
todo fold clothes
```

### Deadline tasks

Command: `deadline <description> /by <date>`

Examples: 

```
deadline CS2103T ip submission /by 20/9/2004 2359

deadline CS2100 assignment submission /by 25/09/2004
```

### Event  tasks

Command: `event <description> /from <date> /to <date>`

Examples:

```
event swim /from 20/9/2004 0900 /to 20/9/2004 1000

event career fair /from 16/09/2004 /to 19/09/2004 
```

---

## List Command

Description: List all the tasks recorded

Command: `list`

---

## Mark Commands

### Mark as Done Command

Command: `mark <task number>`

Examples: 
```
mark 1
```

Note: 
Refer to the output of command `list` for the task number of a task


### Mark as Undone Command

Command: `unmark <task number>`

Examples: 
```
unmark 1
```

Note: 
Refer to the output of command `list` for the task number of a task

---

## Find a Task

Find a task of a certain prefix or parts of a description or the entire description

Command: `find <prefix>`

Examples: 
```
find computing

find c
```

---

## Delete a Task

Delete a task based on its task number

Command: `delete <task number>`

Examples:
```
delete 1
```

---

## View Tasks

View the tasks for the certain date
This includes the entire todo list

Command: `view <date>`

Examples: 
```
view 24/9/2004
```

_**Note**: 
The `<date>` here only accepts `D/M/YYYY` instead of `D/M/YYYY HHMM`!_

---

## Close the Application

This closes the application.

Command: `bye`

_Note: Your data would be stored even after the application is closed._

---

## Appendix

### DateTime Formats
Generally, _unless specified_, the allowed date format are as follows: 
```
D/M/YYYY 

D/M/YYYY HHMM
```

For example: 
```
24/9/2024

24/9/2024 1500
```

### Task Number 
`<task number>` should be attained from the index of the task attained from list

For example: 
```
1. study CS2100
2. study CS2101
```
The `<task number>` for `study CS2101` should be 2. 

---