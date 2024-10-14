# Mahesh Dall-E User Guide

Mahesh Dall-E is a powerful task management tool designed to help you keep track of your todos, events, deadlines, and recurring tasks. With a simple and intuitive command-line interface, Mahesh Dall-E makes it easy to organize your tasks and stay on top of your schedule. All your data is stored locally in a human-readable text file, ensuring that your information is always accessible and easy to manage. Whether you're managing personal tasks or coordinating team activities, Mahesh Dall-E is here to help you stay organized and productive.

![User Interface](Ui.png)

## Adding todos

To add a todo, use the `todo` command followed by the task description.

**Command format:** `todo <task description>`

Example: `todo Buy groceries`

```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have X tasks in the list.
Mamta dum dum
```

## Adding events

To add an event, use the `event` command followed by the task description, start date, and end date.

**Command format:** `event <task description> /from <start date> /to <end date>`

**Date format:** `yyyy-MM-dd'T'HH:mm:ss`

Example: `event Team meeting /from 2023-10-15T10:00:00 /to 2023-10-15T12:00:00`

```
Got it. I've added this task:
  [E][ ] Team meeting (from: Oct 15 2023, 10:00:00 to: Oct 15 2023, 12:00:00)
Now you have X tasks in the list.
Mamta dum dum
```

## Adding deadlines

To add a deadline, use the `deadline` command followed by the task description and the due date.

**Command format:** `deadline <task description> /by <due date>`

**Date format:** `yyyy-MM-dd'T'HH:mm:ss`

Example: `deadline Submit report /by 2023-10-15T23:59:59`

```
Got it. I've added this task:
  [D][ ] Submit report (by: Oct 15 2023, 23:59:00)
Now you have X tasks in the list.
Mamta dum dum
```

## Adding recurring tasks

To add a recurring task, use the `recurring` command followed by the task description and the recurrence interval.

**Command format:** `recurring <task description> /every <recurrence interval>`

Example: `recurring Water plants /every Monday`

```
Got it. I've added this task:
  [R][ ] Water plants (every: Monday)
Now you have X tasks in the list.
Mamta dum dum
```

## Listing tasks

To list all tasks, use the `list` command.

**Command format:** `list`

Example: `list`

```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: Oct 15 2023, 23:59:00)
3. [E][ ] Team meeting (from: Oct 15 2023, 10:00:00 to: Oct 15 2023, 12:00:00)
4. [R][ ] Water plants (every: Monday)
Mamta dum dum
```

## Marking tasks as done

To mark a task as done, use the `mark` command followed by the task number.

**Command format:** `mark <task number>`

Example: `mark 1`

```
Nice! I've marked this task as done:
  [T][X] Buy groceries
Mamta dum dum
```

## Unmarking tasks as done

To unmark a task as done, use the `unmark` command followed by the task number.

**Command format:** `unmark <task number>`

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
Mamta dum dum
```

## Deleting tasks

To delete a task, use the `delete` command followed by the task number.

**Command format:** `delete <task number>`

Example: `delete 1`

```
Got it. I've removed this task:
  [T][ ] Buy groceries
Now you have X tasks in the list.
Mamta dum dum
```

## Local Storage

Mahesh Dall-E stores data locally in a text file in a human-readable format. This ensures that your tasks are easily accessible and can be manually edited if necessary.
There is no manual save or exit command needed. You can simple close out of the app and your tasks will be saved.
