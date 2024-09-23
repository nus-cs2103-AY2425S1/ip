# Spike User Guide

> Introducing Spike, your quiet BUT friendly task manager! :blush:
>
> He may not speak, but don't make him angry as he doesn't hide his thorns :cactus:

## Why Choose Spike? Spike is:
* Efficient and Capable :dash:
* Easy to use :ballot_box_with_check:
* Cute and always ready to help :hugs:

---

<p align="center">
  <img src="Ui.png" />
</p>

---
## How to use Spike?
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `spike.jar` from [here](https://github.com/seantham21/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Spike.
4. Open a command terminal, navigate to the folder where `spike.jar` is located. and use the command
   `java -jar spike.jar` to interact with Spike!
---

## Spike's features & usage

### Add or Create Tasks
#### `todo`
Create a quick Todo to help track tasks with no specific date/time.

**Format**: `todo <description>`

**Example**: `todo Morning run`

**Spike's response**:
```
Got it. I've added this task:
[T][ ] Morning run
Now you have 1 tasks in the list.
```
---
#### `deadline`
Create tasks that need to be done before a specific date.

**Format**: `deadline <description> /by <yyyy-MM-dd'T'HH:mm:ss>`

**Example**: `deadline Submit CS2103T iP /by 2024-09-21T23:59`

**Spike's response**:
```
Got it. I've added this task:
[D][ ] Submit CS2103T iP (by: 21 Sep 2024 23:59)
Now you have 2 tasks in the list.
```
---
#### `event`
Create tasks that start at a specific date and ends at a specific date.

**Format**: `event <description> /from <yyyy-MM-dd'T'HH:mm:ss> /to <yyyy-MM-dd'T'HH:mm:ss>`

**Example**: `event Brawl Stars Championship /from 2024-09-20T00:00 /to 2024-09-30T16:00`

**Spike's response**:
```
Got it. I've added this task:
[E][ ] Brawl Stars Championship (from: 20 Sep 2024 00:00 to: 30 Sep 2024 16:00)
Now you have 3 tasks in the list.
```

---
### View Tasks
#### `list`
Lists all your tasks in a neat list.

**Format**: `list`

**Spike's Response**:
```
Here are the tasks in your list:
1.[T][ ] Morning run
2.[D][ ] Submit CS2103T iP (by: 21 Sep 2024 23:59)
3.[E][ ] Brawl Stars Championship (from: 20 Sep 2024 00:00 to: 30 Sep 2024 16:00)
```
---
#### `listbydate`
Lists all your deadlines and events in a neat list sorted by date.

**Format**: `listbydate`

**Spike's Response**:
```
Here are the tasks in your list:
1.[E][ ] Brawl Stars Championship (from: 20 Sep 2024 00:00 to: 30 Sep 2024 16:00)
2.[D][ ] Submit CS2103T iP (by: 21 Sep 2024 23:59)
```
---
#### `find`
Find a task by searching for a keyword in the task description.

**Format**: `find <keyword>`

**Example**: `find Brawl`

**Spike's Response**:
```
Here are the tasks in your list:
1.[E][ ] Brawl Stars Championship (from: 20 Sep 2024 00:00 to: 30 Sep 2024 16:00)
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
**Spike's Response**:
```
Nice! I've marked this task as done:
[T][X] Morning run
```
---
#### `unmark`
Mark a task in the task list as incomplete.

**Format**: `unmark <index>`

**Example**:
```
unmark 1
```
**Spike's Response**:
```
I've marked this task as not done yet:
[T][ ] Morning run
```
---
#### `delete`
Delete a task.

**Format**: `delete <index>`

**Example**:
```
delete 1
```

**Spike's Response**:
```
Noted. I've removed this task:
[T][ ] Morning run.
Now you have 2 tasks in the list.
```
---
#### `update`
Updates the description or date of a task through an interactive process.

**Format**: `update <index>`

**Example**:

**User's Input**:
```
update 2
```
**Spike's Response**:
```
What would you like to update? Enter '1', '2' or '3'.
1. Description     
2. Event start date and time
3. Event end date and time
```
**User's Input**:
```
1
```
**Spike's Response**:
```
Please enter the new description:
```
**User's Input**:
```
World Championship
```
**Spike's Response**:
```
Got it. I've updated this task:
[E][ ] World Championship (from: 20 Sep 2024 00:00 to: 30 Sep 2024 16:00)
```
---
## Hope you enjoy using Spike! :smile:

---
