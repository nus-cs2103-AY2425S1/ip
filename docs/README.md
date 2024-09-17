# Peppa User Guide

![Ui.png](Ui.png)

Peppa is a versatile chatbot designed to help you manage tasks efficiently, whether they are simple to-dos, 
deadlines, or events. Peppa helps you stay organized by keeping track of tasks, marking their completion, 
and reminding you of upcoming deadlines. The user-friendly command system ensures quick interaction and 
task management with minimal effort.

## Features

### Adding Todos

Peppa allows you to add simple to-dos to your task list without any due date.

Action: Add a task with a description.

Example: `todo Submit report`

```
Got it! I've added this task:
[T][ ] Submit report
You now have 1 task in the list.
```

### Adding Deadlines

Peppa allows you to add deadlines for tasks, ensuring that you stay on top of important dates.
You can specify both the task and the deadline date.

Action: Add a task with a deadline. This task will be tracked and listed with its corresponding due date.

Example: `deadline Submit report /by 2024-09-30 16:00`

```
Got it! I've added this task:
[D][ ] Submit report (by: Sep 30 2024 4:00pm)
You now have 1 task in the list.
```

### Adding Events

Peppa allows you to add events for tasks, ensuring that you stay on top of important dates.

Action: Add a task as an event with a start and end time and date. This task will be tracked and listed with its corresponding event date.

Example: `event Submit report /from 2024-09-30 16:00 /to 2024-09-31 17:00` or `event Submit report /from Sep 30 2024 4:00pm /to 5:00pm`

```
Got it! I've added this task:
[E][ ] Submit report (from: Sep 30 2024 4:00pm to: Sep 31 2024 5:00pm)
You now have 1 task in the list.
```

### Marking and Unmarking Tasks

Peppa allows you to mark tasks as completed or revert them back to incomplete if necessary.

Action: Mark a task as completed or revert it back to incomplete.

Example: `mark 1` or `unmark 1`

```
Oh goody! I've marked this task as done:
[D][X] Submit report (by: Sep 30 2024 4:00pm)
You now have 0 tasks in the list.

Oh goody! I've marked this task as not done yet:
[D][ ] Submit report (by: Sep 30 2024 4:00pm)
You now have 1 task in the list.
```
### Deleting Tasks

Peppa allows you to delete tasks from the list.

Action: Delete a task from the list.

Example: `delete 1`

```
Oh goody! I've removed this task:
[D][X] Submit report (by: Sep 30 2024 4:00pm)
You now have 0 tasks in the list.
```

### Viewing Tasks

Peppa allows you to view all tasks in the list.

Action: View all tasks in the list.

Example: `list`

```
Here are the tasks in your list:
1.[D][X] Submit report (by: Sep 30 2024 4:00pm)
2.[E][ ] Birthday party (from: Sep 30 2024 4:00pm to: Sep 31 2024 5:00pm)
3.[T][ ] Go grocery shopping
```

### Exiting Peppa

Peppa allows you to exit the program.

Action: Exit the program.

Example: `bye`

```
Bye! Oink Oink. See you soon!
```
