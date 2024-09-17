# Kita User Guide

![](Ui.png)

## Features

### 1. Listing Task `list`

```
list
```

Lists all tasks that you have added

Examples:

```
list
```

Example Output:

```
Here are the tasks in your list:
1. [E][ ] hello world (from: Aug 02 2021 to: Aug 02 2022)
2. [T][X] hello
```

### 2. Adding Tasks `deadline`, `todo`, `event`

Adds a task to your list.

There are 3 types of tasks to choose from: `todo`, `event` and `deadline`, you can find the relevant info for them below.

**<u>Note:</u>** All mutation actions (such as this) are automatically written to disk and will persist between different sessions

#### 2.1 `todo`

```
todo <description>
```

Adds a `todo` type task.

Examples:

```
todo Do CS2103T iP by midnight
```

Example Output:

```
Got it. I've added this task:
  [T][ ] Do CS2103T iP by midnight
Now you have 10 tasks in the list.
```

#### 2.2 `deadline`

```
deadline <description> /by <date>
```

Adds a `deadline` type task which has a "by when" time to complete the task

**<u>Note:</u>** All `<date>`s should be in `yyyy-MM-dd`

Examples:

```
deadline Do CS2103T iP by midnight /by 2024-09-25
deadline Do CS2100 by today!!! /by 2024-09-18
```

Example Output:

```
Got it. I've added this task:
  [D][ ] Do CS2103T iP by midnight (by: Sep 25 2024)
Now you have 11 tasks in the list.
```

#### 2.3 `event`

```
event <description> /from <date> /to <date>
```

Adds a `event` type task which has a "from" time to a "to" time

**<u>Note:</u>** All `<date>`s should be in `yyyy-MM-dd`

Examples:

```
event CCA party on recess week /from 2024-09-25 /to 2024-09-26
event CCA Chalet /from 2024-12-25 /to 2024-12-26
```

Example Output:

```
Got it. I've added this task:
  [E][ ] CCA party on recess week (from: Sep 25 2024 to: Sep 26 2024)
Now you have 12 tasks in the list.
```

### 3. Marking Task(s) `mark`

```
mark <task_id> <task_id?> <task_id?>
```

Mark any number of tasks as completed

Task IDs range from `1-n` (`n` is the number of tasks you have)

**<u>Note:</u>** All mutation actions (such as this) are automatically written to disk and will persist between different sessions

Examples:

```
mark 1
mark 1 2 3
mark 4 6 8 9 10
```

Example Output:

```
Nice! I've marked these tasks as done: 
  [E][X] hello world (from: Aug 02 2021 to: Aug 02 2022)
  [T][X] hello
  [T][X] hello 2
```

### 4. Un-Marking Task(s) `unmark`

```
unmark <task_id> <task_id?> <task_id?>
```

Mark any number of tasks as uncompleted

Task IDs range from `1-n` (`n` is the number of tasks you have)

**<u>Note:</u>** All mutation actions (such as this) are automatically written to disk and will persist between different sessions

Examples:

```
unmark 1
unmark 1 2 3
unmark 4 6 8 9 10
```

Example Output:

```
I've marked theses tasks as not done yet: 
  [E][ ] hello world (from: Aug 02 2021 to: Aug 02 2022)
  [T][ ] hello
  [T][ ] hello 2
```

### 5. Deleting Task(s) `delete`

```
delete <task_id> <task_id?> <task_id?>
```

Delete any number of tasks

Task IDs range from `1-n` (`n` is the number of tasks you have)

**<u>Note:</u>** All mutation actions (such as this) are automatically written to disk and will persist between different sessions

Examples:

```
delete 1
delete 1 2 3
delete 4 6 8 9 10
```

Example Output:

```
Noted. I've removed these tasks:
  [E][ ] hello world (from: Aug 02 2021 to: Aug 02 2022)
  [T][ ] hello 2
  [T][ ] hello 4
Now you have 9 tasks in the list.
```

### 6. Find Tasks `find`

```
find <search_query>
```

Search tasks via their `description`.

**<u>Note:</u>** The search is **<u>case insensitive</u>**

Examples:

```
find ccA
```

Example Output:

```
Here are the matching tasks in your list:
1. [E][ ] CCA party on recess week (from: Sep 25 2024 to: Sep 26 2024)
```

### 7. Exit Program `bye`

```
bye
```

Exits the program and closes the GUI

Examples:

```
bye
```

Example Output: (only visible in the Command Line version)

```
Bye. Hope to see you again soon!
```