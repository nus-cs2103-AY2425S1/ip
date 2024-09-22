# Friday User Guide

![Ui.png](Ui.png)

This is Friday, a to-do list chatbot that ensures you get things done by **Friday**! Available in CLI or GUI format.

You can:
* Add/remove different types of tasks (Todos, deadlines, events)
* Track their completion statuses
* Find keywords quickly

``help`` displays all possible commands that you can use.

## Adding Tasks

There are 3 kinds of tasks supported by Friday.
1. Todo - Task without any dates
2. Deadline - Task with a deadline
3. Event - Task with a start date and end date

Friday recognises different task types based on how many date fields you input in the ``add`` command.

To add different task types, use the pipe `|` character to separate each date.

**Ensure all dates are denoted in ``dd mm yyyy`` format.**

### Add Todo
``add <task name>``

Example: ``add Drink water``
```
Okay, I've added a todo: Drink water
```

### Add Deadline
``add <task name> | <date>``

Example: ``add Drink water | 26 05 2024``
```
Okay, I've added a deadline: Drink water
```

### Add Event
``add <task name> | <start date> | <end date>``

Example: ``add Drink water | 26 05 2024 | 27 05 2024``
```
Okay, I've added an event: Drink water
```

Friday detects if the start date is after the end date, and will not add the task if this happens.


Example: ``add Drink water | 27 05 2024 | 26 05 2024``
```
The start date is after the end date! Did you key in incorrectly?
```

### Task Duplication
Friday detects if you are adding a task that has the same name (but type-agnostic) as one currently in the list.

If so, it will warn you but continue to add the task for you.

## Deleting Tasks

``remove <index>``

Friday uses 1-based indexing for task removal. Not sure what index the task you want to remove is? Do ``list`` to check!

## Finding Tasks

``find <keyword>``

Friday finds any tasks that partially match your given keyword. If none found, it will let you know too.

## Marking and Unmarking Tasks

``mark <index>``

``unmark <index>``

Mark any task for completion or unmark for non-completion.

Friday uses 1-based indexing for marking. Not sure what index the task you want to mark is? Do ``list`` to check!
