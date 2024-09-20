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

## Marking Events

Usage: mark (task number)

Example: `mark 1`

Expected Outcome: Task 1 is marked

Expected Output:
```
Nice! I've marked this task as done:
[D][X] Math Assignment (by: 20th September 2024 5pm)
```

## Unmarking Events

Usage: unmark (task number)

Example: `mark 1`

Expected Outcome: Task 1 is marked

Expected Output:
```
OK, I've marked this task as not done yet:
[D][] Math Assignment (by: 20th September 2024 5pm)
```

## Listing Tasks

Usage: list

Example: `list`

Expected Outcome: All lists along with their ranks are listed

Expected Output:
```
1.[D][] Math Assignment (by: 20th September 2024 5pm)
2.[E][] Math Exam (from:(September 20th 2024 2pm) to:(September 20th 2024 4pm))
```

## Deleting Tasks
Usage: delete (task number)

Example: `delete 1`

Expected Outcome: the first task is deleted from the task list

Expected Output:
```
Noted. I've removed this task:
[D][] Math Assignment (by: 20th September 2024 5pm)
Now you have 1 task in the list.
```

## Finding Tasks
Usage: find (search query)

Example: `find Math`

Expected Outcome: All tasks containing the word "Math" (NOTE: This is case-sensitive) will be listed

Expected Output:
```
1.[T][] Math Assignment
2.[E][] Math Exam (from:(September 20th 2024 2pm) to:(September 20th 2024 4pm))
```

## Exiting Chat
Usage: bye!

Expected Outcome: The chat is terminated (but all data is saved)