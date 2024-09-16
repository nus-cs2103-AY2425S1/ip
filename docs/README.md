# Patrick The Star User Guide

![User Interface of Patrick The Star](Ui.png)

## Patrick The Star, Spongebob's Bestie, is the _best_ at **remembering your tasks for you!**

## What can you do with Patrick The Star you may ask

### Adding todo tasks
Add tasks that you are required to do and be arranged nicely in a list.

"I have CS2103 iP to be done"

Example: `todo CS2103 iP`

This is placed into the task list.

```
Got it! I've added this task:
T | O | CS2103 iP
Now you have 1 task in the list.
```

### Adding deadline tasks

Add tasks with a deadline.

"I have CS2103 iP to be done by September 20th 2024 2359"

Example: `deadline CS2103 iP /by Sep 20 2024 2359`

This is placed into the task list.

```
Got it! I've added this task:
D | O | CS2103 iP | Sep 20 2024 2359
Now you have 1 task in the list.
```

### Adding event tasks

Add tasks with a start and end time.

"I have CS2103 Briefing on September 20 2024 1600 - 1800"

Example: `event CS2103 Briefing /from Sep 20 2024 1600 /to 1800`

This is placed into the task list.

```
Got it! I've added this task:
E | O | CS2103 Briefing | Sep 20 2024 1600-1800
Now you have 1 task in the list.
```

### List tasks

List out all the tasks

"I want to check what tasks do I have left"

Example: `list`

A list showing all the various tasks will be displayed.

```
Here are the tasks in your list:
T | O | CS2103 iP
D | O | CS2103 iP | Sep 20 2024 2359
E | O | CS2103 Briefing | Sep 20 2024 1600-1800

```

### Mark tasks

Mark a specific task as done

"I am done with CS2103 iP"

Example: `mark 1`

The indexed task will be marked

```
Nice! I've marked this task as done:
T | X | CS2103 iP
```

### Unmark tasks

Mark a specific task as not done

"I thought I am done with CS2103 iP"

Example: `unmark 1`

The indexed task will be unmarked

```
Nice! I've marked this task as not done yet:
T | O | CS2103 iP
```

### Delete tasks

Deletes a specific task

"I thought I need to do CS2103 iP but welps, guess not"

Example: `delete 1`

The indexed task will be deleted

```
Noted! I've removed this task:
T | O | CS2103 iP
Now you have 0 task in the list
```

### Date Formats

Display the list of possible date formats to input

"Damn! I'm not sure what date format Patrick accepts"

Example: `formats`

The list of possible date formats will be displayed

```
Here are the different formats available:
yyyy-MM-dd HHmm
...
...
```

### Find Tasks

Looks through the tasks and displays tasks with the specified keyword

"Damn! There is so many tasks, when is the CS2103 briefing?"

Example: `find CS2103 Briefing`

The list of possible tasks will be displayed

```
Here are the matching tasks in your list:
E | O | CS2103 Briefing | Sep 20 2024 1600-1800
```