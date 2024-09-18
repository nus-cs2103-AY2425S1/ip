# GavinChatBot User Guide

> GavinChatBot is a task management chatbot that helps you manage your tasks efficiently. 
> You can create todos, deadlines, events, and track them through commands. 
> Here are the features and how to use them:

## Adding Todos

>To add a todo task, use the `todo` keyword followed by the task description.

Example: `todo Buy groceries`


```
OKAY Got it. I've added this task to your list:
[T][]Buy groceries
Now you have an astonishing 1 task(s) in the list.
```

## Adding Deadlines

>To add a deadline task, use the `deadline` keyword followed by the task description and the due date.

Example: `deadline Submit assignment /by 2024-12-12`

Expected Output:
```
OKAY Got it. I've added this task to your list:
[D][]Submit assingment (by:Dec 12 2024)
Now you have an astonishing 2 task(s) in the list.
```

## Adding Events

>To add an event task, use the `event` keyword followed by the task description, start time, and end time.

Example: `event Meeting /from 2pm /to 3pm`

Expected Output:
```
OKAY Got it. I've added this task to your list:
[E][]Meeting (from:2pm to:3pm)
Now you have an astonishing 3 task(s) in the list.
```

## Marking Tasks

>To mark a task as done, use the `mark` keyword followed by the index of the task.

Example: `mark 2`

Expected Output:

```
Wow great job! This task is now marked as done:
[D][X]Submit assingment (by:Dec 12 2024)
```

## Unmarking Tasks

>To unmark a task as not done, use the `unmark` keyword followed by the index of the task.

Example: `unmark 2`

Expected output:
```
Oh no... What happened... I've unmarked this task:
[D][]Submit assingment (by:Dec 12 2024)
```

## Deleting Tasks

>To delete a task, use the `delete` keyword followed by the index of the task.

Example: `delete 3`

Expected output:
```
Alright I gotchu. I've removed this task:
[E][]Meeting(from:2pm to:3pm)
Now you have a total of 2 task(s) in the list.
```

## Finding Tasks

>To find tasks that contain a specific keyword, use the `find` keyword followed by the search keyword.

Example: `find Buy`

Expected output:
```
After some hard work, I have found the matching
tasks in your list:
1.[T][] Buy groceries
```

## Counting Completed Tasks

>To count the number of tasks that are marked as done, use the `count` keyword.

Example: `count`

Expected output:
```
Number of tasks marked as done: 0
```

## Tagging Tasks

>To tag a task with a custom label, use the `tag` keyword followed by the index of the task and the tag.

Example: `tag 1 urgent`

Expected output:
```
Okay, I have now tagged this task as 'urgent':
1.[T][] Buy groceries Tags:[urgent]
```

## Display List of Tasks

>To display the list of tasks, use the `list` keyword.

Example: `list`

Expected output:
```
Alright, here's all the tasks in your list:
1.[T][] Buy groceries Tags:[urgent]
2.[D][]Submit assingment (by:Dec 12 2024)
```

## Exit

>To exit the chatbot, use the `bye` keyword.

Example: `bye`

Expected output:
```
Oh, that was quick.
Bye. Hope to see you again soon!
```