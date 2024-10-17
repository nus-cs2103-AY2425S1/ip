# FriendlyBot User Guide

![Screenshot of the GUI of FriendlyBot.](./Ui.png)

Hello! Welcome to FriendlyBot. FriendlyBot is a friendly chatbot tool used to help you keep track of your tasks!
FriendlyBot frees your mind of having to remember things that you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ _SUPER FAST_ to use!

## Getting Started

Use `help` to get started! FriendlyBot will reply you with its latest commands. Use `help <command>` to learn more
about each command. :D

Example: 

- `help`
- `help delete`
- `help event`

## Listing all tasks

Use `list` to list all your saved tasks!

Example: `list`

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Jun 24 2024)
```

## Adding todos

Use `todo <task_description>` to add a new todo task!

Example: `todo read book`

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task in the list.
```

## Adding deadlines

Use `deadline <task_description> /by <date>` to add a new deadline task! 
Make sure your date is in the YYYY-MM-DD format.

Example: `deadline return book /by 2024-09-25`

```
Got it. I've added this task:
    [D][ ] return book (by: Sept 25 2024)
Now you have 2 tasks in the list.
```

## Adding events

Use `event <task_description> /from <date> /to <date>` to add a new event task!
Make sure your dates are in the YYYY-MM-DD format.

Example: `event midterm exams /from 2024-09-01 /to 2024-09-08`

```
Got it. I've added this task:
    [E][ ] midterm exams (from: Sept 1 2024 to: Sept 8 2024)
Now you have 3 tasks in the list.
```

## Marking tasks as completed

Use `mark <task_number>` to mark your task as completed!

Example: `mark 1`

```
Nice! I've marked this task as done:
    [T][X] read book
```

## Unmarking tasks as completed

Use `unmark <task_number>` to mark your task as completed!

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
    [T][ ] read book
```

## Deleting tasks

Use `delete <task_number>` to delete your task!

Example: `delete 1`

```
Noted. I've removed this task:
    [T][ ] read book
Now you have 2 tasks in the list.
```

## Finding tasks using a keyword

Use `find <keyword>` to find tasks whose task description matches the keyword!

Example: `find exams`

```
Here are the matching tasks in your list:
1.[E][ ] midterm exams (from: Sept 1 2024 to: Sept 8 2024)
```

## Finding tasks that happen on a date

Use `date <date>` to find tasks that happen on that date!
This command returns:
- Deadline tasks whose deadline lies on that date
- Event tasks that happen on that date, i.e. the date is between the start and end date of the event

Example: `date 2024-09-03`

```
Here are the matching tasks in your list:
1.[E][ ] midterm exams (from: Sept 1 2024 to: Sept 8 2024)
```

## Exiting FriendlyBot

Use `bye` to exit from the program! 
You will see a goodbye message and the program will automatically close in 3 seconds.

Example: `bye`

```
Goodbye. Hope to see you again soon!
```

Thank you for using FriendlyBot! Feel free to leave me some comments on my [GitHub](https://github.com/limyixiang/ip).
