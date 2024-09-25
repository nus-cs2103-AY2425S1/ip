# BeeBot User Guide

![Ui.png](Ui.png)

> "Plans are nothing; planning is everything." — Dwight D. Eisenhower

## BeeBot takes the load off your mind by managing your tasks efficiently
**It’s:**

Text-based ✅

User-friendly ✅

~~Thunder~~ LIGHTNING fast ⚡

## Add a Todo

**Add a simple to-do task to your list**

Format: "todo [TASK_NAME]"

_Example: `todo Buy Groceries`_

_Expected UI Output:_

```
Growl... Honeyboo added 'Buy Groceries' to the list!"
```

## Add a Deadline

**Add a task with a Deadline to your list**


Format: "deadline [TASK_NAME] /by [YYYY-MM-DD]"

_Example: `deadline Buy new Calculator /by 2024-09-18`_

_Expected UI Output:_

```
BZZZZZ... Honeyboo added 'Buy new Calculator' to the list!"
```


## Add an Event

**Add a task with a start and end date to your list**

Format: "event [TASK_NAME] /from [YYYY-MM-DD] /to [YYYY-MM-DD]"

_Example: `event Attend Football Camp /from 2024-09-18 /to 2024-09-21`_

*Expected UI Output:*

```
Grrrr... Honeyboo added 'Attend Football Camp' to the list!
```

## Mark a Task

**Mark a task as complete**

Format: "mark [TASK_NUMBER]"

*Example: `mark 1`*

*Expected UI Output:*

```
🐝-utiful! Honeyboo marked this task as done:
[T][X] Buy Groceries
```

## Unmark a Task

**Unmark a task as complete**

Format: "unmark [TASK_NUMBER]"

*Example: `unmark 1`*

*Expected UI Output:*

```
🐝-utiful! Honeyboo marked this task as not done yet:
[T][] Buy Groceries
```

## List out all Tasks

**View all tasks in the list**

Format: "list"

*Example: `list`*

*Expected UI Output:*

```
1. [T][ ] Buy Groceries
2. [D][ ] Buy new Calculator (by: 18 September 2024)
3. [E][ ] Attend Football Camp (from: 18 September 2024 to: 21 September 2024)
```

## Find a Task by Name

**Find a task by part of its name**

Format: "find [PART_OF_TASK_NAME]"

*Example: `find Buy`*

*Expected UI Output:*

```
1. [T][ ] Buy Groceries
2. [D][ ] Buy new Calculator (by: 18 September 2024)
```

## Update a Task Name

**Update a Task Name**

Format: "update [TASK NUMBER] /to [NEW_TASK_NAME]"

*Example: `update 1 /to Buy Milk`*

*Expected UI Output:*

```
Task updated!
```

## Delete a Task

**Delete a Task**

Format: "delete [TASK_NUMBER]"

*Example: `delete 1`*

*Expected UI Output:*

```
Yum yum in my tum tum! Task eaten!
```

## Exit Application

**Exit the Application**

_Format: "bye"_

_Example: `bye`_
