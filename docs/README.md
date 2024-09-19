# Friday Task Manager User Guide

![Ui.png](Ui.png)

Welcome to Friday, your personal task management assistant! Friday helps you organize your tasks efficiently by supporting various types of tasks, such as Todos, Deadlines, and Events. You can easily add, update, and track your tasks through simple commands.

## Adding Todos

Use the `todo` command to add tasks with a specific todo.

**Command Format**: `todo <description>`

**Example**: `deadline read book`

**Expected Outcome**: 
Friday will store the task and inform you that the todo has been added successfully.
```
Got it! I've added this task:
    [T][ ] read book
Now you have 3 tasks in your list.
```

## Adding Deadlines

Use the `deadline` command to add tasks with a specific deadline.

**Command Format**: `deadline <description> /by <yyyy-MM-dd HHmm>`

**Example**: `deadline Submit assignment /by 2024-09-15 2359`

**Expected Outcome**: 
Friday will store the task and inform you that the deadline has been added successfully.
```
Got it! I've added this task:
    [D][ ] Submit assignment (by: 15 Sept 2024, 11:59 pm)
Now you have 3 tasks in your list.
```

## Adding Events

Use the `event` command to add tasks with a specific event.

**Command Format**: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Example**: `event buy book /from 2024-09-13 1400 /to 2024-09-13 1800`

**Expected Outcome**: 
Friday will store the task and inform you that the event has been added successfully.
```
Got it! I've added this task:
    [E][ ] buy book (from: Sept 13 2024, 2:00 pm to: Sept 13 2024, 6:00 pm)
Now you have 3 tasks in your list.
```

## Updating Tasks

Use the `update` command to edit an existing task's description, deadline, or event times.

**Command Format**: `update <task number> <new details>`

**Example**: `update 3 return book /by 2024-09-16 1800`

**Expected Outcome**: 
Friday will update the task with the new details and notify you of the successful update.

```
Got it! I've updated this task:
    [D][ ] return book (by: 16 Sept 2024, 6:00 pm)
```

## Finding Tasks

Use the `find` command to find an existing task through its description.

**Command Format**: `find <description>`

**Example**: `find return`

**Expected Outcome**: 
Friday will find the task with the specified details and notify you of any matching entries.

```
Here are the matching tasks in your list:
1.[D][ ] return book (by: 16 Sept 2024, 6:00 pm)
```

## Listing All Tasks

To view your tasks, use the `list` command. This will display all tasks currently tracked by Friday, including their status (whether completed or not).

**Command Format**: `list`

**Expected Outcome**: 
All tasks will be displayed in a numbered list, with their respective status.

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sept 13 2024, 8:00 pm)
3.[E][ ] buy book (from: Sept 13 2024, 2:00 pm to: Sept 13 2024, 6:00 pm)
```

## Marking Tasks as Done

You can mark tasks as completed using the `mark` command.

**Command Format**: `mark <task number>`

**Example**: `mark 1`

**Expected Outcome**: 
The task will be marked as completed and Friday will notify you.

```
Nice! I've marked this task as done:
    [T][X] read book
```

## Unmarking Tasks as Done

You can mark tasks as completed using the `unmark` command.

**Command Format**: `unmark <task number>`

**Example**: `unmark 1`

**Expected Outcome**: 
The task will be marked as completed and Friday will notify you.

```
Nice! I've marked this task as done:
    [T][X] read book
```

## Deleting Tasks

Remove tasks from your list using the `delete` command.

**Command Format**: `delete <task number>`

**Example**: `delete 2`

**Expected Outcome**: 
The task will be removed, and Friday will confirm the deletion.

```
Noted. I've removed this task:
    [D][ ] return book (by: 2024-09-13 18:00)
Now you have 3 tasks in your list.
```

## Exit

Terminate the application using the `bye` command.

**Command Format**: `bye`

**Expected Outcome**:
The application should terminate itself immediately.

---

Feel free to explore the other commands and enjoy a more organized day with Friday!
