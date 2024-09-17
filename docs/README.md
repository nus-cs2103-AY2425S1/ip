# Tick User Guide

> Introducing Tick, your friendly, capable task manager!
> 
> Just be careful of his head... it's a ticking time :bomb:

## Tick is designed to be:
* Fast and Efficient :dash:
* Easy to use :+1:
* Cute :space_invader:

---

<p align="center">
  <img src="Ui.png" />
</p>

---
## Quick Start
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `tick.jar` from [here](https://github.com/wrjgold/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Tick.
4. Open a command terminal, navigate to the folder where `tick.jar` is located. and use the command
   `java -jar tick.jar` to interact with Tick!
---

## Tick's features & usage

### Create Tasks
#### `todo`
Create a quick Todo to help track tasks with no specific date/time.

**Format**: `todo <description>`

**Example**: `todo Use hypercharge`

**Tick's response**:
```
Ding ding! I've added this task:
[T][ ] Use hypercharge
Now you have 1 tasks in the list.
```
---
#### `deadline`
Create tasks that need to be done before a specific date.

**Format**: `deadline <description> /by <yyyy-mm-dd>`

**Example**: `deadline Level up /by 2024-09-17`

**Tick's response**:
```
Ding ding! I've added this task:
[D][ ] Level up (by: 17 Sep 2024)
Now you have 2 tasks in the list.
```
---
#### `event`
Create tasks that start at a specific date and ends at a specific date.

**Format**: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

**Example**: `event Brawl Stars World Finals /from 2024-11-01 /to 2024-11-03`

**Tick's response**:
```
Ding ding! I've added this task:
[E][ ] Brawl Stars World Finals (from: 1 Nov 2024 to: 3 Nov 2024)
Now you have 3 tasks in the list.
```

---
### View Tasks
#### `list`
Lists all your tasks in a neat list.

**Format**: `list`

**Tick's Response**:
```
Here are the tasks in your list:
1.[T][ ] Use hypercharge
2.[D][ ] Level up (by: 17 Sep 2024)
3.[E][ ] Brawl Stars World Finals (from: 1 Nov 2024 to: 3 Nov 2024)
```
---
#### `find`
Find a task by searching for a keyword in the task description.

**Format**: `find <keyword>`

**Example**: `find Finals`

**Tick's Response**:
```
Ding ding! Here are the matching tasks in your list:
1.[E][ ] Brawl Stars World Finals (from: 1 Nov 2024 to: 3 Nov 2024)
```

---
### Manage Tasks
#### `mark`
Mark a task in the task list as completed.

**Format**: `mark <index>`

**Example**:
```
mark 1
```
**Tick's Response**:
```
Ding ding! I've marked this task as done:
[T][X] use hypercharge
```
---
#### `unmark`
Mark a task in the task list as incomplete.

**Format**: `unmark <index>`

**Example**:
```
unmark 1
```
**Tick's Response**:
```
OK, I've marked this task as undone:
[T][ ] use hypercharge
```
---
#### `delete`
Delete a task.

**Format**: `delete <index>`

**Example**:
```
delete 1
```

**Tick's Response**:
```
Womp womp. I've removed this task:
[T][ ] use hypercharge
Now you have 2 tasks in the list.
```
