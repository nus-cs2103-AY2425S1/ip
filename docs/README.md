# BingBong User Guide

![Screenshot of the BingBong app Ui](/Ui.png)

BingBong is a desktop app for managing your tasks. It is your go-to companion for staying on top of your to-dos, offering a seamless experience that's:

- **User-friendly**: Intuitive and designed with simplicity in mind.
- **Lightweight**: No unnecessary frills—just what you need to get things done.
- **Blazingly Efficient**: Lightning-fast, so you can focus on your tasks, not on using the tool.

## Features

### Adding to-do tasks: `todo`

Adds a task with no deadline.

Format: `todo <task>`

Example:

`todo Buy groceries`

This command will create a task "Buy groceries":
```
Got it. I've added this task: 
[T][] Buy groceries
Now you have n tasks in the list
```

### Adding deadlines: `deadline`

Adds a task with a specific deadline.

Format: `deadline <task> /by <d/M/yyyy HH:mm>`

Example:
`deadline Submit assignment /by 10/09/2024 1300`

This command will create a task "Submit assignment" with a deadline of September 10, 2024, at 1:00 PM:
```
Got it. I've added this task: 
[D][] Submit assignment (by: 10 Sept 2024, 1:00pm)
Now you have n tasks in the list
```

### Adding events: `event`

Adds an event that starts and ends at specified times.

Format: `event <task> /from <d/M/yyyy HH:mm> /to <d/M/yyyy HH:mm>`

Example:
`event Team meeting /from 10/09/2024 1300 /to 15/09/2024 1200`

This command will create an event "Team meeting" starting on September 10, 2024, at 1:00 PM and ending on September 15, 2024, at 12:00 PM.

```
Got it. I've added this task: 
[E][] Team meeting (from: 10 Sept 2024, 1:00pm to: 15 Sept 2024, 12:00pm)
Now you have n tasks in the list
```

### Adding fixed duration tasks: `fixed`

Adds a task that has a fixed duration.

Format: `fixed <task> /period <timeInHours>`

Example:
`fixed Work on project /period 3`

This command will create a task "Work on project" with a duration of 3 hours.

```
Got it. I've added this task: 
[F][] Work on project (duration: 3 hours)
Now you have n tasks in the list
```

### Viewing all tasks: `list`

Shows a list of all tasks managed by BingBong.

Format: `list`

### View tasks on specific dates: `list on`

Shows a list of tasks on a specified date.

Format: `list on <dd/M/yyyy>`

Example: `list on 10/10/2024`

### Mark a task: `mark`

Marks a task as complete.

Format: `mark <task number>`

Example:
`mark 2`

This command will mark the second task on the list as complete:

```
Nice!. I've marked this task as done: 
[F][X] Work on project (duration: 3 hours)
```

### Unmark a task: `mark`

Unmarks a task.

Format: `unmark <task number>`

Example:
`mark 2`

This command will unmark the second task on the list:

```
Okay. I've unmarked this task: 
[F][] Work on project (duration: 3 hours)
```

### Delete a task: `delete`

Removes a specified task from the list

Format: `delete <task number>`

Example:
`delete 3`

This command will delete the third task on the list:
```
Noted. I've removed this task: 
[D][] Complete assignment (by: 14 August 2024, 7:00pm)
Now you have n tasks in the list
```

### Find tasks: `find`

Searches for tasks that contain the specified keyword.

Format: `find <keyword>`

Example:
`find book`

This command will find all tasks in the list with the keyword 'book':
 ```
 Here are the tasks containing "book":
 1. [T][X] Read a book
 2. [D][] Return the book (by: 17 October 2024, 12:00pm)
 ```

### Exiting the program: `bye`

Exits the program.

Format: `bye`