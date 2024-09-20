# Bean User Guide
![Ui.png](..%2Fsrc%2Fmain%2Fresources%2Fimages%2FUi.png)
Bean is a command-line-based chatbot that supports a variety of commands such as add, list, mark, unmark, edit, delete, 
and find tasks. It handles different types of tasks, including todos, deadlines, and events. This guide will walk you through how to use Bean effectively.

---

## Adding Todos
The `todo` command allows you to add a simple task without a specific date or time. This is perfect for tasks that 
don't have a deadline but still need to be tracked.

**Example:** `todo Buy groceries`

When you successfully add a todo, Bean will confirm the task addition with the following output:

```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have X tasks in the list.
```

---

## Adding deadlines
The `deadline` command allows you to add tasks with a specific due date and time. This is useful for tracking tasks 
that need to be completed by a particular date and time.

Example: `deadline Project Submission /by 2019-10-10 1800`

Note that the time should be in this yyyy-MM-dd HHmm format

When you successfully add a deadline, Bean will confirm the task addition with the following output:

```
Got it. I've added this task:
  [D][ ] Project Submission (by: Oct 10 2019 18:00)
Now you have X tasks in the list.
```


---

## Adding Events
The `event` command helps you add tasks that occur at a specific date and time range. This is ideal for appointments or meetings.

**Example:** `event Team meeting /from 2024-09-21 0900 /to 2024-09-22 1100`

Note that the time should be in this yyyy-MM-dd HHmm format

When you successfully add an event, Bean will confirm the task addition with the following output:

```
Got it. I've added this task:
  [E][ ] Team meeting (from: Sep 21 2024, 9:00 am to: Sep 21 2024, 11:00 am)
Now you have X tasks in the list.
```

---

## Listing All Tasks
The `list` command displays all the tasks that are currently in your task list. This will help you review everything
you've added.

**Example:** `list`

When you issue this command, Bean will output:

```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Project Submission (by: Oct 10 2019 18:00)
3. [E][ ] Team meeting (from: Sep 21 2024, 9:00 am to: Sep 22 2024, 11:00 am)
```


---

## Marking Tasks as Done
The `mark` command allows you to mark a task as completed. This helps you track your progress.

**Example:** `mark 1`

When you successfully mark a task, Bean will acknowledge the action:

```
Nice! I've marked this task as done:
[T][X] Buy groceries
```

---

## Unmarking Tasks
The `unmark` command lets you undo the completion status of a task, in case you need to revise it.

**Example:** `unmark 1`

When you unmark a task, Bean will confirm the change:

```
Nice! I've marked this task as done:
[T][ ] Buy groceries
```

---

## Deleting Tasks
The `delete` command helps you remove a task from your list.

**Example:** `delete 1`

Upon successful deletion, Bean will show this message:
```
Noted. I've removed this task:
[T][ ] Buy groceries
Now you have X tasks in the list.
```

---

## Finding Tasks
The `find` command is a quick way to search for tasks that contain a specific keyword.

**Example:** `find meeting`

When tasks are found that match the keyword, Bean will display:
```
Here are the matching tasks in your list:
1. [E][ ] Team meeting (from: Sep 21 2024, 9:00 am to: Sep 22 2024, 11:00 am)
```

---

## Editing Tasks
The `edit` command allows you to modify the details of an existing task. You can only edit task to another task with 
the same type

**Example:** `edit 1 CS2103 Assignment /by 2024-10-10 1800` (modify a task using the same format as when it was initially added)

After editing, Bean will show the updated task:
```
Got it. I've updated this task:
[D][ ] CS2103 (by: Oct 10 2024 6:00 pm)

```

# Conclusion

Start using Bean today to simplify your task management!