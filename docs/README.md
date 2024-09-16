
# Dynamike Guide

> Dynamike is your friendly task manager, designed to help you prioritise your tasks and win your battles.
> 
> Now you can easily keep track of your tasks and blow your troubles away. :bomb: :bomb: :bomb:

## Why Dynamike?
* He's cute and friendly, always ready to help you destroy your tasks.
* So simple you can pick it up in a minute. 
* Blazing fast for quick and efficient task management.
---
<p align="center" width="100%">
    <img src="Ui.png" width="50%">
</p>

## Dynamike's features & usage

---
## Create Tasks
1. #### `todo`
Create a quick Todo to help track tasks with no specific date/time.

**Format**: `todo <description>`

**Example**: `todo play Brawl Stars`

**Dynamike's response**:
```
Got it. I've added this task:
[T][ ] play Brawl Stars
Now you have 3 tasks in the list.
```
---

2. #### `deadline`
Create tasks that need to be done before a specific date/time.

**Format**: `deadline <description> /by <time>`

**Example**: `deadline CS2103T Quiz /by 2024-09-11 23:59`

**Dynamike's response**:
```
Got it. I've added this task:
[D][ ] CS2103T Quiz (by: Sep 11 2024 23:59)
Now you have 4 tasks in the list.
```
---
3. #### `event`
Create tasks that start at a specific date/time and ends at a specific date/time.

**Format**: `event <description> /from <start time> /to <end time>`

**Example**: `event Brawl Stars /from 2024-09-12 19:00 /to 2024-09-14 22:00`

**Dynamike's response**:
```
Got it. I've added this task:
[E][ ] Brawl Stars (from: Sep 12 2024 19:00 to: Sep 14 2024 22:00)
Now you have 5 tasks in the list.
```

---
## View Tasks
- #### `list`
Lists all your tasks in a neat list.

**Format**: `list`

**Dynamike's Response**:
```
Here are the tasks in your list:
1.[D][X] return book (by: Feb 12 2024 12:00)
2.[T][ ] read
3.[T][ ] play Brawl Stars
4.[D][ ] CS2103T Quiz (by: Sep 11 2024 23:59)
5.[E][ ] Brawl Stars (from: Sep 12 2024 19:00 to: Sep 14 2024 22:00)
```
---
- #### `find`
Find a task by searching for a keyword in the task description.

**Format**: `find <keyword>`

**Example**: `find Brawl`

**Dynamike's Response**:
```
Here are the matching tasks in your list:
1.[T][ ] play Brawl Stars
2.[E][ ] Brawl Stars (from: Sep 12 2024 19:00 to: Sep 14 2024 22:00)
```
---
## Manage Tasks
- #### `mark`
Mark a task in the task list as completed.

**Format**: `mark <index>`

**Example**:
```
mark 2
```
**Dynamike's Response**:
```
Nice! I've marked this task as done:
[T][X] read
```
- #### `unmark`
Mark a task in the task list as incomplete.

**Format**: `unmark <index>`

**Example**:
```
unmark 2
```
**Dynamike's Response**:
```
OK, I've marked this task as not done yet:
[T][ ] read
```
---
- #### `delete`
Delete a task.

**Format**: `delete <index>`

**Example**: 
```
delete 2
```

**Dynamike's Response**:
```
Noted. I've removed this task:
[T][ ] read
Now you have 4 tasks in the list.
```
---

## Have fun with Dynamike and good luck! :bomb: :bomb: :bomb: