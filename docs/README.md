# Jeff User Guide

![](Ui.png)

Jeff is your personal assistant for maintaining tasks and deadlines.

## Adding deadlines

Usage: deadline (task description) /by (deadline)

Example: `deadline Math Assignment /by 20th September 2024 5pm`

Expected Outcome: The Task will be added to your list of tasks


Expected Output:
```
Got it. I've added this task:
[D][] Math Assignment (by: 20th September 2024 5pm)
Now you have 1 task in the list.
```

## Adding todos

Usage: todo (task description)

Example: `todo Math Assignment`

Expected Outcome: The Task is added to your list of tasks

Expected Output:
```
Got it. I've added this task:
[T][] Math Assignment
Now you have 1 task in the list.
```

## Adding Events

Usage: Event (task description) /from (start time) /to (end time)

Example: `event Math Exam /from (September 20th 2024 2pm) /to (September 20th 2024 4pm)`

Expected Outcome: The Task is added to your list of tasks

Expected Output:
```
Got it. I've added this task:
[E][] Math Exam (from:(September 20th 2024 2pm) to:(September 20th 2024 4pm))
Now you have 1 task in the list.
```