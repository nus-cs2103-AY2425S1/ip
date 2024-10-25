# Noisy User Guide


![img.png](img.png)

Introducing NoisyBot—your lively, task-managing companion! It not only helps you stay organized by adding, snoozing, and completing tasks but does so with a burst of humor and excitement.

## Adding deadlines

Action: Adds a task with a deadline to your task list, specifying a due date.

Outcome: NoisyBot will add the task and announce that it's been added to your list.

Example: `Deadline Submit_report true 2024-09-30`



```
expected output:
Got it. I've added this task: 
[D][X] dasd (by:Sep 30 2024)
Now you have 1 tasks in the list.
```

## Adding todos

Action: Adds a todo task to your task list.

Outcome: NoisyBot will add the task and announce that it's been added to your list.

Example: `todo test`

// A description of the expected outcome goes here

```
expected output:
Got it. I've added this task: 
[T][] test 
Now you have 1 tasks in the list.
```


## Adding events

Action: Adds a event task to your task list, specifying a start date and an end date.

Outcome: NoisyBot will add the task and announce that it's been added to your list.

Example: `Event Submit_report 2024-09-30 2024-10-01`

// A description of the expected outcome goes here

```
expected output:
Got it. I've added this task: 
[E][X] dasd (from: Sep 30 2024 to: Oct 1 2024
Now you have 1 tasks in the list.
```

## Listing tasks

Action: Lists all tasks in your task list.

Outcome: NoisyBot will list all tasks in your task list.

Example: `list`

// A description of the expected outcome goes here

```
expected output:
Here are the tasks in your list:
1. [D][X] dasd (by: Sep 30 2024)
```

## Completing tasks

Action: Marks a task as completed.

Outcome: NoisyBot will mark the task as completed and announce that it's been completed.

Example: `mark 1`

// A description of the expected outcome goes here

```
expected output:
Nice! I've marked this task as done:
[D][X] dasd (by: Sep 30 2024)
```

## Deleting tasks

Action: Deletes a task from your task list.

Outcome: NoisyBot will delete the task and announce that it's been deleted.

Example: `delete 1`

// A description of the expected outcome goes here

``` 
expected output:
Noted. I've removed this task:
[D][X] dasd (by: Sep 30 2024)
Now you have 0 tasks in the list.
```

## Snoozing tasks

Action: Snoozes a task to a later date.

Outcome: NoisyBot will snooze the task to the specified date and announce that it's been snoozed.

Example: `snooze 1 2024-10-01`

// A description of the expected outcome goes here

```
expected output:
Got it. I've snoozed this task:
[D][X] dasd (by: Oct 1 2024)
```

