# Murphy User Guide

![Product screenshot](Ui.png)

A simple chatbot to help improve your workflow by keeping a list of your tasks.

## Features

Note that all [date]s should be in the format 'yyyy-mm-dd'

[task number] takes in the number of a task based on the order it is shown in the list.

Any parameters in [] which are not described here require any form of non-empty text.

## Adding todos

Adds a new todo to your list.

Usage: `todo [description]`

Murphy should successfully add the todo to your list.

```
Got it. I've added this task:
[T][ ] test
```

## Adding deadlines

Adds a new deadline to your list.

Usage: `deadline [description] /by [date]`

Murphy should successfully add the deadline to your list.

```
Got it. I've added this task:
[D][ ] test (by: Sep 22 2024)
```

## Adding events

Adds a new event to your list.

Usage: `event [description] /from [date] /to [date]`

Murphy should successfully add the event to your list.

```
Got it. I've added this task:
[E][ ] event (from: Sep 22 2024 to: Sep 24 2024)
```

## Displaying the list

Views the current contents of your list.

Usage: `list`

Murphy should display the contents of the list.

```
[T][ ] test
[D][ ] test (by: Sep 22 2024)
[E][ ] event (from: Sep 22 2024 to: Sep 24 2024)
```

## Marking tasks

Marks a task in the list.

Usage: `mark [task number]`

Murphy should successfully mark the task.

```
Nice! I've marked this task as done:
[T][X] test
```

## Unmarking tasks

Unmarks a task in the list.

Usage: `unmark [task number]`

Murphy should successfully unmark the task.

```
Ok, I've unmarked this task. Guess Murphy struck?
[T][ ] test
```


## Deleting tasks
Deletes a task from the list.

Usage: `delete [task number]`

Murphy should show what task was deleted from the list.

```
Got it. I've deleted this task:
[T][ ] test
```

## Finding tasks
Search for tasks by their description.

Usage: `find [search term]`

Murphy will show tasks where the search term can be found in the description.

```
Example: find test

Output:
[T][ ] test
[D][ ] test (by: Sep 22 2024)
```

## Getting tasks by date

Get a list of what events/deadlines will be active on a specific date.

Usage: `schedule [date]`

Murphy will show tasks which either have the specified date falling within their event period,
or the specified date falling before their deadline.

```
Example: schedule 2024-09-23

Output:
[D][ ] test (by: Sep 22 2024)
[E][ ] event (from: Sep 22 2024 to: Sep 24 2024)
```