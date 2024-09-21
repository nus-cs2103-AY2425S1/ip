# Tuesday User Guide

![Tuesday Sample Image](/Ui.png)

Ever want to keep track of your tasks through a chatbot? Look no further! 
Tuesday will solve all your tracking problems.  

## Adding todos

If you want to add a simple task to your list,
you can do it with a simple command.

Example: `todo {description}`

A simple task will be added to the list, and you can view it.
```
[T][][] {description}
```

## Adding deadlines

If you want to add a deadline to your list, 
you can do it with a simple command.

Example: `deadline {description} /by {time}`

A deadline task will be added to the list, and you can view it.
```
[D][][] {description} (by: {time})
```

## Adding events

If you want to add an event task to your list,
you can do it with a simple command.

Example: `event {description} /from {time 1} /to {time 2}`

An event task will be added to the list, and you can view it.
```
[E][][] {description} (from: {time 1} to: {time 2})
```

## Listing tasks

If you want to view your task list,
you can do it with a simple command.

Example: `list`

A list will be displayed.
```
Here are the task(s) in your list:

1. {task 1}
2. {task 2}
...
```

## Mark tasks

If you have completed your task and you want to mark it as done, 
you can do it with a simple command.

Example: `mark {index}`

Mark your task as done.
```
Before: [T][ ][H] {description}
After:  [T][X][H] {description}
```

## Unmark tasks

If you want to change your task to incomplete and you want to mark 
it as undone, you can do it with a simple command.

Example: `unmark {index}`

Mark your task as undone.
```
Before: [T][X][H] {description}
After:  [T][ ][H] {description}
```

## Delete tasks

If you want to delete a task, you can do it with a simple command.

Example: `delete {index}`

Delete your task from the tasklist.
```
Got it. I've deleted this task:
[T][X][H] {description}
Now you have {number} task(s) in the list.
```

## Find tasks

If you want to find a task, you can do it with a simple command.

Example: `find {keyword}`

Shows all the task from the tasklist with the keyword 
```
Here are the matching tasks in your tasks in your list
1. {task 1}
2. {task 2}
...
```

## Change priority

If you want to change the priority for your tasks, 
you can do it with a simple command.

Example: `edit {task number} /priority {H/M/L}`

Changes the priority for the specific task
```
Before: [T][ ][L] {description}
After:  [T][ ][H] {description}
```

## Exit the bot

If you want to exit the bot, you can do it with a simple command.

Example: `bye`

Exits the bot
```
Bye bye. Hope to see you again soon!
```

