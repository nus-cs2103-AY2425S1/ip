# Dawn User Guide

![Ui](https://github.com/user-attachments/assets/7de331c1-2510-4c93-80c9-64fceb8cc688)

Dawn is a text-based chatbot that helps you to manage your todos, deadlines, and events! 

Below are some features supported by Dawn

## Adding todos, deadlines, and events

You can add todos (tasks without deadlines), deadlines (tasks with deadlines), and events easily by entering the following commands: 
- *todo [task]*
- *deadline [task] /by [date yyyy-mm-dd] [time]*
- *event [task] /from [date yyyy-mm-dd] [time] /to [time]*

On successful addition of the task or event, Dawn will return a success message and display the task / event added. 
If the addition was unsuccessful, Dawn will remind you to follow the correct format

**Example #1:** `todo Assignment 1` 

```
Gotcha! I've added this task:
1. [T][] Assignment 1
Now you have 1 task(s) in the list
```
**Example #2:** `deadline CS2103T Quiz /by 2024-09-20 1pm`

```
Gotcha! I've added this task:
2. [D][] CS2103T Quiz (by: Sept 20 2024 1pm)
Now you have 2 task(s) in the list
```

**Example #3:** `event CS2103T Lecture /from 2024-09-20 4pm /to 6pm`

```
Gotcha! I've added this task:
3. [E][] CS2103T Lecture (from: Sept 20 2024 4pm to: 6pm)
Now you have 3 task(s) in the list
```

**Example #4:** `event party /from 2pm`

```
Make sure you include both the task description and the time in this format:
deadline [task name] /by [date yyyy-mm-dd] [time]
For example: deadline submit assignment1 /by 2024-09-16 2pm
```

## Finding tasks / events

You can search for a specific task / event by using the ***find*** keyword followed by the ***name of the task or event***

**Example #1** `find CS2103T`
```
Finding the matching tasks in your list...
1. [D][] CS2103T Quiz (by: Sept 20 2024 1pm)
2. [E][] CS2103T Lecture (from: Sept 20 2024 4pm to: 6pm)
```

## List all the tasks and events

You can list all the tasks currently in the list by using the ***list*** keyword

**Example #1** `list`

```
listing all tasks...
1. [T][] Assignment 1
2. [D][] CS2103T Quiz (by: Sept 20 2024 1pm)
3. [E][] CS2103T Lecture (from: Sept 20 2024 4pm to: 6pm)
```

## Mark or unmark a task as done 

You can mark or unmark a task by using the ***mark*** or ***unmark*** keyword followed by the ***index of the task*** in the list. Tasks marked as done will have a 'X' shown.

**Example #1** `mark 1`
```
Ok, I've marked this task as done!
1. [T][X] Assignment 1
```

**Example #2** `unmark 1`
```
Ok, I've marked this task as not done :(
1. [T][] Assignment 1
```

## Deleting tasks and events

You can delete a task from the list by using the ***delete*** keyword followed by the ***index of the task*** in the list 

**Example #1** `delete 1`
```
Ok, I have removed this task for you:
[T][] Assignment 1
Now you have 2 task(s) in the list
```

## Getting a reminder on upcoming deadlines and events 

You can ask Dawn to remind you of upcoming deadlines and events by using the ***remind*** keyword. Dawn will list out all the events happening today, and deadlines that are due within this week. 

**Example #1** `remind`
```
Reminder for upcoming deadlines:
1. [D][] CS2103T Quiz (by: Sept 20 2024 1pm)
Events happening today:
1. [E][] CS2103T Lecture (from: Sept 20 2024 4pm to: 6pm)
```

## Exiting the chat

To exit the chat, just say ***bye*** to Dawn! Don't worry about losing the tasks and events that you've entered. Dawn will keep them in a safe place and load these tasks back for you when you next open the chat! 

**Example #1** `bye`
```
Bye! Nice chatting with you :)
See you next time, Dawn out
૮꒰ ˶• ༝ •˶꒱ა ♡
```
