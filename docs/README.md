# Mediell User Guide

![Ui](Ui.png)

Mediell is a helpful tool to help manage your many tasks!

## Featues

### List

list

lists all tasks currently stored

Example: `list`

```
1. [T][X] Create a Repo
2. [T][] Add teammates to the Repo
```

### Mark

mark [number]

marks a task as completed

Example: `mark 1`

```
Nice! I've marled this task as done:
[T][X] Create a Repo
```

### Unmark

unmark [number]

marks a task as uncompleted

Example: `unmark 1`

```
Ok, I've marked this task as not done yet:
[T][ ] Create a Repo
```

### Delete

delete [number]

Deletes a task

Example: `delete 1`

```
Ok, I will remove this task:
[T][ ] Create a Repo
```

### Todo

todo [task name]

Creates a todo task with the provided task name

Example: `todo Create a Repo`

```
Got it! I've added this task:
[T][ ] Create a Repo
```

### Event

event [task name] /from [date in YYYY-MM-DD] /to [date in YYYY-MM-DD]

Creates an event task with the provided task name from provided date to provided date

Example: `event party /from 2024-09-28 /to 2024-09-29`

```
Got it! I've added this task:
[E][ ] party (from: 2024-09-28 to: 2024-09-29)
```

### Deadline

deadline [task name] /by [date in YYYY-MM-DD]

Creates a deadline task with the provided task name by the provided date

Example: `deadline homework /by 2024-09-29`

```
Got it! I've added this task:
[D][ ] homework (by: 2024-09-29)
```

### find

find [task name]

Finds all matching task names

Example: `find homework`

```
1. [D][ ] homework (by: 2024-09-29)
```

### sort

sort

sorts all tasks by date  
todo tasks will be at the top (no date)  
deadline tasks will be sorted by their by date  
event tasks will be sorted by their from date  

Example: `find homework`

```
1. [T][X] Create a Repo
2. [T][] Add teammates to the Repo
3. [D][ ] homework (by: 2024-09-29)
4. [D][ ] homework (by: 2024-09-30)
```