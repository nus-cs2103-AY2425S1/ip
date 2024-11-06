# Bunbun User Guide

![Bunbun GUI](Ui.png)

Bunbun is a chatbot assistant to help you plan out your tasks! With Bunbun, you can keep track of your various tasks.

## Features

- Add tasks to keep track of, split into todos, deadlines, events, and time-boxed tasks.
- Mark these tasks as complete with an X.
- Delete tasks once you're done.
- View all tasks.
- Search for all tasks containing a certain keyword.

## Commands

> **Note**
> - All [DATE]s must be specified in YYYY-MM-DD format.
> - [HOURS] must be specified with 1 postive integer.
> - [TASK NUMBER] must be specifed with 1 positive integer 
> - [TASK] descriptions can be as long as you want.

### Adding deadlines : `deadline`

Adds a deadline task.

Format: `deadline [TASK] /by [DATE]`

Example: `deadline Calculus Assignment /by 2024-09-20`

```
[D][ ] Calculus Assignment ( by: Sep 20 2024)
```

### Adding events : `event` 

Adds an event task.

Format: `event [TASK] /from [DATE] /to [DATE]`

Example: `event Bali trip /from 2024-12-12 /to 2025-01-03`

```
[E][ ] Bali trip ( from: Dec 12 2024  to: Jan 3 2025 )
```

### Adding todos: `todo`

Adds a todo task.

Format: `todo [TASK]`

Example: `todo Groceries`

```
[T][ ] Groceries
```

### Adding time-boxed tasks: `timebox`

Adds a time-boxed task, which is a time that must be completed within a fixed duration.

Format:`timebox [TASK] /in [HOURS]`

Example: `timebox Read /in 2`

```
[B][ ] Read ( needs: 2 hours )
```

### Listing out all tasks: `list`

Lists out all tasks to see.

Format: `list`

### Mark tasks as complete: `mark`

Mark a task as complete, which will be shown with an X.

Format: `mark [TASK NUMBER]`

Example: `mark 2`

```
[B][X] Read ( needs: 2 hours )
```

### Delete tasks: `delete`

Delete a task from the task list

Format: `delete [TASK NUMBER]`

Example: `delete 2`

```
Bunbun: Oki, I've deleted [TASK 2] task!
```

### Find tasks: `find`

Finds and displays all tasks that contain a specified keyword.

Format: `find [KEYWORD]`

Example: `find homework`

```
1. [T][ ] Do reading homework
2. [D][ ] Calculus homework ( by: Dec 12 2024 )
3. [B][X] Linear Algebra homework ( needs: 20 hours )
```

### Close Bunbun: `bye`

Closes Bunbun.

Format: `bye`