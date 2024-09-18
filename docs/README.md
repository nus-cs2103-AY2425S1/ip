# Cook User Guide

![Screenshot of Cook](./Ui.png)

**THE** Cook cooks your tasks and serves them in a better format.
## Adding todos
Cook will help to add ToDo tasks to your list of tasks, as long as you provide the right ingredients!\
Command example: `todo <description>`\
e.g. `todo hors d'oeuvre`

If Cook was successful in adding the ToDo task, he'll let you know as seen below.
```
ToDo task has been added.
File saved.
```

## Adding deadlines
Cook will help to add Deadline tasks to your list of tasks when you give him a date and time to follow!\
Command example: `deadline <desc> /by <YYYY-MM-DD HH:mm>.`\
e.g. `deadline Cacio e pepe /by 2024-09-16 18:00`

If Cook was successful in adding the Deadline task, he'll let you know as seen below.
```
Deadline task has been added.
File saved.
```

## Adding events
Cook will help to add Event tasks to your list of tasks when you give him when the event is held!\
Command example: `event <desc> /from <YYYY-MM-DD HH:mm> /to <YYYY-MM-DD HH:mm>.`\
e.g. `event Gumbo /from 2024-09-16 18:00 /to 2024-09-16 20:00`

If Cook was successful in adding the Event task, he'll let you know as seen below.

```
Event task has been added.
File saved.
```

## Listing tasks
Cook will show you the tasks you have planned...\
The first letter indicates the type of task, whilst the second tell you if it has eaten (marked)!\
Command example: `list`\
e.g. `list`

If Cook was successful at list, he'll let you know as seen below. If there aren't any tasks in the list, he won't be kind enough to let you know!
```
1.[T][X] hors d'oeuvre
2.[D][ ] Cacio e pepe (by: 2024-09-16 18:00)
3.[E][ ] Gumbo (from: 2024-09-16 18:00 to: 2024-09-16 20:00)
```

## Delete task
In case you would no longer like to have that task in your list, Cook can also help you remove it!\
Command example: `delete <taskNumber>`
e.g. `delete 2`

If Cook was successful in removing the task, he'll let you know as seen below.
```
Task deleted.
File saved.
```

## Find task
Cook will also help you find tasks with your particular tastes (keyword)!
Command example: `find <keyword>`
e.g. 'find hors'

If Cook was successful in finding a task, he'll let you know as seen below. If what you're finding doesn't exist, he'll at least let you know this time...
```
1.[T][X] hors d'oeuvre
```

## Mark Task
Let him know once you're done with a task! He'll mark them down for you!
Command example: `mark <taskNumber>`\
e.g. `mark 2`

If Cook was successful in finding a task, he'll let you know as seen below.
```
Task marked.
File saved.
```

## Unmark Task
Let him know if you lied about doing the task! He'll keep the integrity of the task list for you!
Command example: `unmark <taskNumber>`\
e.g. `unmark 1`

If Cook was successful in finding a task, he'll let you know as seen below.
```dtd
Task unmarked.
File saved.
```

## Leaving the restaurant
Be polite and remember to say "bye" when you leave!\
Command example: `bye`
e.g. `bye`

If Cook was successful, you won't see him anymore...