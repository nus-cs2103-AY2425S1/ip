# Dude User Guide

https://MingYiAw.github.io/ip/Ui.png

**CATCHPHRASE!!!**

Do you have lots of deadlines to work on and having multiple events marked on your calendar? 
Are you frustrated keeping tracks of all these while having to deal with new tasks coming to you?

Dude is here to help!! Dude is a chatbot designed to help you to keep tracks of your tasks and make your life easier.

With Dude, you can easily keep track of all your daily task, deadlines and events.

# Features

## Overview
Dude is able to handle three types of task, which are ToDo, Deadline and Event. 
ToDo is a task with no date and time requirement, whereas Deadline is a task with a date and time to be complete and 
Event is a task with start and end date and time. You are able to add and delete any task with the commands.

Dude also offers function to list out all your task for a good overview or just list out tasks with specific keyword 
to help you to locate your task easily. 
There is also commands to mark your task as done or the other way round to keep track your task's condition.
To further enhance your experience, you can speed up the process by define your own shortcuts for any command that 
provided by Dude.

## Commands

### List Task: `list`

List out all your added tasks in your list.

Format: `list`

Example: 
```
list
```

Expected output:
```
Here's your task list! Get going and tackle those tasks right away:
1.[T][ ] smt
2.[D][X] cs2103t (by: Oct 08 2024 11:11)
3.[T][ ] bye
```

### Add ToDo Task: `todo`

Add a ToDo task with no date into your list.

Format: `todo <task description>`

Example:
```
todo cs2103t
```

Expected output:
```
Got it. Task added:
[T][ ] cs2103t
Now you have 5 tasks in the list.
```

### Add Deadline Task: `deadline`

Add a Deadline task to be done by specific date and time into your list.

Format: `deadline <task description> /by <date and time>`

> [!IMPORTANT]
> `<date and time>` must follow the format: `yyyy-MM-dd HH:mm` e.g. `2024-01-01 01:01`

Example:
```
deadline cs2101 /by 2024-09-20 23:59
```

Expected output:
```
Got it. Task added:
[D][ ] cs2101 (by: Sep 20 2024 23:59)
Now you have 5 tasks in the list.
```

### Add Event Task: `event`

Add a Event task with start and end date and time into your list.

Format: `event <task description> /from <date and time> /to <date and time>`

> [!IMPORTANT]
> `<date and time>` must follow the format: `yyyy-MM-dd HH:mm` e.g. `2024-01-01 01:01`

Example:
```
event cs3241 /from 2024-09-20 00:00 /to 2024-09-30 23:59
```

Expected output:
```
Got it. Task added:
[E][ ] cs3241 (from: Sep 20 2024 00:00 to: Sep 30 2024 23:59)
Now you have 5 tasks in the list.
```

### Delete Task: `delete`

Delete a task with specific index in your list.

Format: `delete <task index>`

Example:
```
delete 5
```

Expected output:
```
All right! Task eliminated:
[T][ ] cs2103t
Now you have 4 tasks in the list.
```

### Mark Task as Done: `mark`

Mark a task with specific index in your list as done.

Format: `mark <task index>`

Example:
```
mark 4
```

Expected output:
```
Finally you have done something! I've marked this as done:
[D][X] cs2103t (by: Oct 08 2024 11:11)
```

### Mark Task as Not Done: `unmark`

Mark a task with specific index in your list as not done.

Format: `unmark <task index>`

Example:
```
unmark 4
```

Expected output:
```
OK, Looks like you have more works to do. I've marked this as not done yet:
[D][ ] cs2103t (by: Oct 08 2024 11:11)
```

### Find Task: `find`

Find tasks in your list which contains specific keyword.

Format: `find <keyword>`

Example:
```
find cs
```

Expected output:
```
Here's what you looking for:
1.[D][ ] cs2103t (by: Oct 08 2024 11:11)
2.[T][ ] cs2101
```

### Define New Shortcut: `define`

Define a new shortcut for any of the commands.

> [!NOTE]
> If the same shortcut is define for different commands, only the latest define will work.

Format: `define <shortcut> <command>`

Example:
```
define cari find
```

Expected output:
```
Did you just said that "cari" means "FIND"?
```

### Delete Shortcut: `undefine`

Delete a previously defined shortcut.

Format: `undefine <shortcut>`

Example:
```
undefine cari
```

Expected output:
```
I got you. What does "cari" even means?
```

### Say Hi to Dude: `hi`

Dude will "greet" you again.

Format: `hi`

### Exit the Application: `bye`

Save your list and quit the application after a few seconds.

Format: `bye`

## Commands Format Summary
- **List:** `list`
- **ToDo:** `todo <task description>`
- **Deadline:** `deadline <task description> /by <date and time>`
- **Event:** `event <task description> /from <date and time> /to <date and time>`
- **Delete:** `delete <task index>`
- **Mark:** `mark <task index>`
- **Unmark:** `unmark <task index>`
- **Find:**`find <keyword>`
- **Define:** `define <shortcut> <command>`
- **Undefine:** `undefine <shortcut>`
- **Hi:** `hi`
- **Bye:** `bye`