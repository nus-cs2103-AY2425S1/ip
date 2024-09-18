# Spiderman User Guide

![Spiderman Screenshot](Ui.png)

Spiderman helps you capture every task in your web of productivity, so you never have to remember everything on your own. Just like Spidey always stays on top of his game, you will too!

## Adding deadlines
Add deadline task to the tasks list.  
`deadline {description} /by {YYYY-MM-dd}`

Example: `deadline project meeting /by 2024-04-01`

Expected outcome:  
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```

## Adding todos
Add todo task to the tasks list.  
`todo {description}`

Example: `todo buy bread`

Expected outcome:
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```

## Adding events
Add event task to the tasks list.  
`event {description} /from {YYYY-MM-dd HH:mm} /to {YYYY-MM-dd HH:mm}`

Example: `event project meeting /from 2024-12-12 14:00 /to 2024-12-12 16:00`

Expected outcome:
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```

## Viewing entire task list
Shows current saved tasks in task list.  
`list`

Example: `list`

Expected outcome:
```
Alright! Here is your current tasks list:
1. [T][] buy bread
2. [D][] sell bread (by: Oct 1 2024)
3. [E][] project meeting (from: 1 Dec 2024, 12:30:00pm to: 1 Dec 2024, 6:30:00pm)
```

## Mark task as done
Mark task as completed.  
`mark {task index}`

Example: `mark 1`

Expected outcome:
```
Great! I've marked this task as done:
[T][X] buy bread
```
## Mark task as not done
Unmark a previously marked as completed task.  
`unmark {task index}`

Example: `unmark 1`

Expected outcome:
```
OK, this task will be marked as not done yet:
[T][] buy bread
```

## Update task
Update a task from the task list.  
`update {task index} /description {description} /by {YYYY-MM-dd} /from {YYYY-MM-dd HH:mm} /to {YYYY-MM-dd HH:mm}`

Example: `update 1 /description buy milk`

Expected outcome:
```
Updated Task: [T][] buy milk
```
## Delete task
Delete a task from the task list.  
`delete {task index}`

Example: `delete 1`

Expected outcome:
```
Alright! I will delete this task for you!
[T][] buy bread
```