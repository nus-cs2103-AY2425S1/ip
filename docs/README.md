# Cow User Guide

![Product screenshot](Ui.png)

Cow is a task management application designed to help you keep track of your to-dos, deadlines, and recurring tasks efficiently. With an intuitive interface and powerful features, Cow ensures that you stay organized and productive.

## Adding todos

Add a todo.

Example: `todo buy groceries`
```
Added task: [T][ ] buy groceries
```

## Adding Deadlines

Add a Deadline item to the list.

Example: `deadline return book /by 2/12/2019 1800`
```
[D][ ] return book (by: Dec 2 2019 1800)
```

## Adding Events

Add an Event item to the list.

Example: `event project meeting /from Mon 2pm /to 4pm`
```
[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

## Recurring Tasks

Schedule tasks to recur daily or weekly.

Example: `recurring task /start 1/1/2023 10:00 /freq daily /times 5`
```
Added recurring task: [R][ ] task (starts at 1/1/2023 10:00, repeats daily for 5 times)
```

## Searching Tasks

Find tasks using keywords.

Example: `find keyword`
```
1. [T][ ] task containing keyword
```

## List Tasks Due By Date

Show all due items on specified date.

Example: `due 16/9/2024`
```
1.[D][ ] CS2100 Assignment 1 (by: Sep 16 2024 2359)
```

## Deleting Tasks

Remove a task from the list.

Example: `delete 1`
```
Deleted task: [T][ ] task description
```

## View All Tasks

View all tasks in the list.

Example: `list`
```
1. [T][ ] task 1
2. [D][ ] task 2 (by: date)
3. [R][ ] task 3 (starts at date, repeats daily for 5 times)
```

## Marking Tasks

Mark task as done.

Example: `mark 1`
```
1. [T][x] task 1
```

## Unmarking Tasks

Unmark task as done.

Example: `unmark 1`
```
1. [T][ ] task 1
```

## Help Command

Get a list of available commands and their usage.

Example: `help`
```
list: Lists all tasks in todo list.
Example: list
bye: Exits the program.
Example: bye
todo: Adds a Todo item to the list.
Example: todo buy groceries
deadline: Adds a Deadline item to the list.
Example: deadline return book /by 2/12/2019 1800
event: Adds an Event item to the list.
Example: event project meeting /from Mon 2pm /to 4pm
mark: Marks an item in the todo as done.
Example: mark 1
unmark: Unmarks an item in the todo as done.
Example: unmark 1
delete: Delete an item from the todo list.
Example: delete 1
due: Show all due items on specified date
Example: due d/M/yyyy
help: Shows program usage instructions.
Example: help
recurring: Creates recurring deadlines.
Example: recurring return book /start 7/9/2019 1800 /freq day /times 5-Mooooo
```