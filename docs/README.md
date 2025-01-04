# Friday User Guide

Welcome to Friday, your personal task management assistant! This chatbot helps you stay organized by keeping track of your tasks, deadlines and events.


<img width="398" alt="Ui" src="https://github.com/user-attachments/assets/9844030b-1075-45ce-a993-3f9fb4c8a989">



## Adding To-Do Tasks

The `todo` command allows you to add a to-do task to the task list. A to-do task is a simple task that does not have any specific deadline or event time.

Format: `todo DESCRIPTION`

Example: `todo read book`

This command will add a task with the description "read book" to the task list.

```
Got it. I've added this task:
  [T][ ] read book 
Now you have N tasks in the list.
```

## Adding Deadlines

The `deadline` command allows you to add a task with a specific deadline to the task list. You must provide a task description and a date for deadline. This will create a task that is marked with a deadline.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline return book /by 2024-01-23`

This command will add a task with the description "return book" and a deadline of January 23, 2024 to the task list.

```
Got it. I've added this task:
  [D][ ] return book (by: Sep 30 2024)
Now you have N tasks in the list.
```

## Adding Events

The `event` command allows you to add a task for a scheduled event to the task list. You must provide a task description and specify the start and end dates and times of the event. This will create a task that is marked with a specific date and times.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event project meeting /from 2024-01-23 1400 /to 2024-01-23 1600`

This command will add a task with the description "project meeting" and schedule it from 2 PM to 5 PM on January 23, 2024.

```
Got it. I've added this task:
  [E][ ] project meeting (from: Jan 23 2024 2pm  to: Jan 23 2024 4pm)
Now you have N tasks in the list.
```


## Commands
| Command       | Description                                  | Example                                                           |
|---------------|----------------------------------------------|-------------------------------------------------------------------|
| `todo`        | Adds a todo task with a description.         | `todo read book`                                                  |
| `deadline`    | Adds a task with a deadline.                 | `deadline return book /by 2024-12-30`                             |
| `event`       | Adds an event with date, start and end times.| `event project meeting /from 2024-01-23 1400 /to 2024-01-23 1600` |
| `list`        | Lists all tasks.                             | `list`                                                            |
| `mark`        | Marks a task as done.                        | `mark 1`                                                          |
| `unmark`      | Marks a task as not done.                    | `unmark 1`                                                        |
| `delete`      | Deletes a task from the list.                | `delete 2`                                                        |
| `find`        | Finds tasks by keyword(s).                   | `find book`                                                       |
| `view`        | Lists all [D] tasks scheduled on a date.     | `view 2024-01-23`                                                 |
| `bye`         | Exits the chatbot.                           | `bye`                                                             |

