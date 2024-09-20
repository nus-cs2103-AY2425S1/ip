# Tissue User Guide

![alt](./Ui.png)

Tissue is a bot that helps you to store tasks for your viewing pleasure.

## Adding deadlines

Adds a task which can have its deadline stored.

Command: `deadline [task] [YYYY-MM-DD]`

Example: `deadline Buy food /by 2024-03-05`

```
Expected output:
Got it. I've added this task: 
  [D][] Buy food (by: Mar 05 2024)
Now you have 1 tasks in the list.
```

## Adding events

Adds an event task to the bot.


Command: `event [task] /from [time] /to [time]`\
`time` can be any format of your choice

Example: `event CS2030S exam /from 5p.m. /to 8p.m.`

```
Expected output:
Got it. I've added this task: 
  [E][] CS2030S exam (from 5p.m. to 8p.m.)
Now you have 1 tasks in the list.
```

## Adding ToDos

Adds a todo task.

Command: `todo [task]`

Example: `todo Buy food`

```
Expected output:
Got it. I've added this task: 
  [T][] Buy food
Now you have 1 tasks in the list.
```

## Mark item

Marks an item as done.

Command: `mark [number]`

Example: 
```
todo Buy food
mark 1
```

```
Expected output:
Nice! I've marked this task as done:
  [D][X] Buy food
```

## Unmark item

Unmarks an item.

Command: `unmark [number]`

Example:
```
todo Buy food
unmark 1
```

```
Expected output:
OK, I've marked this task as not done yet:
  [D][] Buy food
```

## Delete item

Deletes a task.

Command: `delete [number]`

Example:
```
todo Buy food
delete 1
```

```
Expected output:
Noted. I've removed this task:
  [D][X] Buy food
Now you have 0 tasks in the list.
```

## Find tasks
Finds all the matching tasks.

Command: `find [keyword]`

Example:
```
todo Buy food
todo Throw food
find food
```

```
Expected output:
Here are the matching tasks!
[T] buy food
[T] throw food
```