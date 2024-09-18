# Talker User Guide

![Product Screenshot](https://xinweichong.github.io/ip/Ui.png)

A friendly chatbot to help you keep track of your important tasks!

Make use of inbuilt completion trackers and priority 

## Command Summary

| Action          | Format                                                                |
|-----------------|-----------------------------------------------------------------------|
| list            | `list`                                                                |
| Add todo        | `todo <description>`                                                  |
| Add deadline    | `deadline <description> /by <dd-MM-yyyy HH:mm>`                       |
| Add event       | `event <description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>` |
| Delete          | `delete <task number>`                                                |
| Mark complete   | `mark <task number>`                                                  |
| Unmark complete | `unmark <task number>`                                                |
| Find date       | `date <target date in yyyy/MM/dd>`                                    |
| Find keywords   | `find <keyword(s)>`                                                   |
| Set priority    | `setPriority <task number> <h/m/l>`                                   |
| Find priority   | `findPriority <h/m/l>`                                                |
| Exit            | `bye`                                                                 |

## Features

> [!NOTE]
> Notes about command format:
> - Leading and trailing whitespaces will be removed!
> - Command types are not case-sensitive

### Listing all tasks: `list`

Lists all tasks in the task list

Command: `list`

```
This is what you need to accomplish!
1.[T][][L] Complete Assignment 1
2.[D][X][M] Submit Assignment 2 (by: 2024/09/25 2359)
3.[E][][H] Quiz 3 (from: 2024/09/18 0000 to: 2024/09/25 2359)
```

### Adding a todo: `todo`

Add a task with a description of what you need to do!

Command: `todo <description>`

Example: `todo Complete Assignment 1!`

```
Now you have to do this!
[T][][L] Complete Assignment 1
Now you have 1 things to do!
```

### Adding a deadline: `deadline`

Add a task with a deadline of when you need to complete it by!

Command: `deadline <description> /by <dd-MM-yyyy HH:mm>`

Example: `deadline Submit Assignment 2 /by 25-09-2024 23:59`

```
Now you have to do this!
[D][][L] Submit Assignment 2 (by: 2024/09/25 2359)
Now you have 1 things to do!
```

### Adding an event: `event`

Add a task with a definitive start and end date and time!

Command: `event <description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>`

> [!TIP]
> End date and time must come after start date and time!

Example: `event Quiz 3 /from 18-09-2024 00:00 /to 25-09-2024 23:59`

```
Now you have to do this!
[E][][L] Quiz 3 (from: 2024/09/18 0000 to: 2024/09/25 2359)
Now you have 1 things to do!
```

### Delete a task: `delete`

Delete a specified task from the list of tasks!

> [!NOTE]
> Deleting a non-existent task will throw an error!

Command: `delete <task number>`

Example: `delete 3`

```
You don't have to do this anymore:
[E][][H] Quiz 3 (from: 2024/09/18 0000 to: 2024/09/25 2359)
Now you have to do 2 things!
```

### Mark task as complete: `mark`

Mark a task from the list as complete!

Command: `mark <task number>`

Example: `mark 2`

```
Nice! I've marked this task as done:
[D][X][M] Submit Assignment 2 (by: 2024/09/25 2359)
```

### Unmark task as complete: `unmark`

Mark a task from the list as incomplete

Command: `unmark <task number>`

Example: `unmark 2`

```
OK, I've marked this task as not done yet:
[D][][M] Submit Assignment 2 (by: 2024/09/25 2359)
```

### Find tasks on date: `date`

Find all uncompleted deadlines and events that are occurring on target date

> [!TIP]
> If task has been marked as completed, it will not show up in the search!

Command: `date <target date in yyyy/MM/dd>`

Example: 'date 2024/09/20'

```
You've got to do these things on 20/09/2024:
3.[E][][H] Quiz 3 (from: 2024/09/18 0000 to: 2024/09/25 2359)
```

### Find tasks with keywords: `find`

Find all tasks with keyword(s) in description

Command: `find <keyword(s)>`

Example: `find Assignment`

```
Found these things you gotta do:
1.[T][][L] Complete Assignment 1
2.[D][X][M] Submit Assignment 2 (by: 2024/09/25 2359)
```

### Change priority of task: `setPriority`

Sets the priority of target task to target priority

> [!NOTE]
> Default priority of tasks is low!

Command: `setPriority <task number> <h/m/l>`

Example: `setPriority 2 m`

```
Okay, I've marked this task as MEDIUM priority for you:
[D][X][M] Submit Assignment 2 (by: 2024/09/25 2359)
```

### Find tasks of certain priority: `findPriority`

Sets the priority of target task to target priority

Command: `findPriority <h/m/l>`

Example: `findPriority m`

```
Here are the MEDIUM priority things you gotta do:
1.[D][X][M] Submit Assignment 2 (by: 2024/09/25 2359)
```

### Exit: `bye`

Exits and saves current state of task list to memory

Command: `bye`