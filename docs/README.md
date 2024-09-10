# Matcha User Guide

![Screenshot of Matcha app](Ui.png)

**Matcha** is a chatbot created to help users to **manage their tasks**. 
It is a CLI app with a GUI interface that allows users to **add**, **delete**, **mark** and **view** their tasks.

## View all tasks

You can view all your events, todos and deadlines via the `list` command.

Format: `list` 

Usage: `list`

Expected Output:
```
Here are your tasks:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: Dec 12 2024 12:12 pm)
    3. [E][ ] project meeting (from: Dec 12 2024 12:12 pm to: Dec 12 2024 12:12 pm)
```

## Add New Tasks

You can add new tasks to the list by using the `todo`, `event` and `deadline` commands.
*Note: The date and time must be in the format `YYYY-MM-DD HHMM`.*

Format:
1. `todo <description>`
2. `event <description> /from <START DATE> <START TIME> /to <END DATE> <END TIME>`
3. `deadline <description> /by <DEADLINE DATE> <DEADLINE TIME>`

Usage:
1. `todo borrow book`
2. `event project meeting /from 2024-12-12 1212 /to 2024-12-12 1212`
3. `deadline return book /by 2024-12-12 12:12`

Expected Output:
```
Alright, I have added this Todo:
	[T][ ] borrow book
You have 1 tasks in the list.
```
```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the list.
```


## Mark/Unmark Task

You can mark tasks as done or unmark them by using the `mark` or `unmark` command.

Format:
1. `mark <task number>`
2. `unmark <task number>`

Usage:
1. `mark 1`
2. `unmark 1`