# EchoBot User Guide

> "The key is not to prioritize what's on your schedule, but to schedule your priorities." -Stephen Covey ([source](https://www.brainyquote.com/authors/stephen-covey-quotes))

EchoBot is your ultimate task manager, designed to free up your mental space so you can focus on what truly matters. With EchoBot you can effortlessly keep track of your tasks without the hassle of remembering every detail. :wink: It’s:

* Easy to learn so you can get started right away. :dizzy:
* Blazing fast for quick and efficient task management. :star2:

<img src="Ui.png" width="40%">

## Features

### :blue_book: Create Tasks

- [`todo`](#todo): Create quick TODOs to help keep your tasks on track.
- [`deadline`](#deadline): Create tasks that need to be done before a specific date/time.
- [`event`](#event): Create tasks that start at a specific date/time and ends at a specific date/time.

### :books: View Tasks

- [`list`](#list): List all tasks in the task list
- [`find`](#find): Find tasks with specific keywords

### :memo: Manage Tasks

- [`mark`](#mark): Mark a task as done
- [`unmark`](#unmark): Unmark a task as incomplete
- [`tag`](#tag): Tag a task
- [`delete`](#delete): Delete a task from the task list

### :book: Get Help

- [`help`](#help): Get general help information
- [`help [command]`](#help): Get help information about a specific command

## Usage

### `todo`

Create quick TODOs to help keep your tasks on track.

**Format**: `todo<whitespace>[description]`

**Example**: `todo read the research paper`

**Expect Output**:

```
Got it. I've added this task:
    [T][ ] read the research paper
Now you have 1 tasks in the list.
```

### `deadline`

Create tasks that need to be done before a specific date/time.

**Format**: `deadline<whitespace>[description]<whitespace>/by<whitespace>[time]`

**Example**: `deadline CS2100 A1 /by 2024/09/10 23:59`

**Expect Output**:

```
Got it. I've added this task:
    [D][ ] CS2100 A1 (by: 10 Sep 2024 23:59)
Now you have 2 tasks in the list.
```

### `event`

Create tasks that start at a specific date/time and ends at a specific date/time.

**Format**: `event<whitespace>[description]<whitespace>/from<whitespace>[start time]<whitespace>/to<whitespace>[end time]`

**Example**: `event read book /from 2024/09/14 19:00 /to 2024/09/14 22:00`

**Expect Output**:

```
Got it. I've added this task:
    [E][ ] read book (from 14 Sep 2024 19:00 to 14 Sep 2024 22:00)
Now you have 3 tasks in the list.
```

### `list`

Lists all tasks with their details in the task list in order.

**Format**: `list`

**Expect Output**:

```
There are 4 tasks in the list:
1. [T][] read the research paper
2. [D][X] CS2100 A1 (by 10 Sep 2024 23:59)
3. [D][X] CS2109S Problem Set (by 13 Sep 2024 23:59）
4. [E][] go to lab (from 12 Sep 2024 08:00 to 12 Sep 2024 12:00）
```

### `find`

Find a task by searching for a keyword in the task description.

**Format**: `find<whitespace>[keywords]`

**Example**: `find CS2100`

**Expect Output**:

```
Here are the matching tasks in your list:
1. [D][X] CS2100 A1 (by 10 Sep 2024 23:59)
```

### `mark`

Mark a task in the task list as done.

**Format**: `mark<whitespace>[index]`

**Example**:

```
mark 1 // mark the first task in the list as completed
list
```

**Expect Output**:

```
There are 4 tasks in the list:
1. [T][X] read the research paper
2. [D][X] CS2100 A1 (by 10 Sep 2024 23:59)
3. [D][X] CS2109S Problem Set (by 13 Sep 2024 23:59）
4. [E][] go to lab (from 12 Sep 2024 08:00 to 12 Sep 2024 12:00）
```

### `unmark`

Unmark a task in the task list as incomplete.
**Format**: `unmark<whitespace>[index]`

**Example**:

```
mark 2 // mark the second task in the list as uncompleted
list
```

**Expect Output**:

```
There are 4 tasks in the list:
1. [T][X] read the research paper
2. [D][] CS2100 A1 (by 10 Sep 2024 23:59)
3. [D][X] CS2109S Problem Set (by 13 Sep 2024 23:59）
4. [E][] go to lab (from 12 Sep 2024 08:00 to 12 Sep 2024 12:00）
```

### `tag`

Tag a task in the task list.
**Format**: `unmark<whitespace>[index]`

**Example**: `tag 2 #important`, tag the second task in the list as #improtant

**Expect Output**:

```
OK, l've tag this task:
    [D][X] CS2100 A1 #improtant (by 10 Sep 2024 23:59）
```

### `delete`

Delete a task from task list.

**Format**: `delete<whitespace>[index]`

**Example**: `delete 3`, delete the third task from the list

**Expect Output**:

```
Noted. I've removed this task:
    [D][X] CS2109S Problem Set (by 13 Sep 2024 23:59）
Now you have 3 tasks in the list.
```

### `help`

- `help` Get general help information.
- `help<whitespace>[command]` Get help information about a specific command.
