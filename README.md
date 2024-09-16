# Axel User Guide

**Axel** is a task management chat bot designed to help you organize your to-dos, deadlines, and events efficiently. Whether it's marking tasks, setting deadlines, or even finding specific tasks, **Axel** is ready to assist with simple commands. This guide will show you how to interact with Axel, including adding tasks, managing them, and accessing helpful features like searching and help.

![Ui.png](docs%2FUi.png)

# List of features
1. [Adding tasks to be done](#adding-tasks-to-be-done)
2. [Adding deadlines](#adding-deadlines)
3. [Adding events](#adding-events)
4. [Finding tasks](#finding-tasks)
5. [Marking tasks as done](#marking-tasks-as-done)
6. [Unmarking tasks](#unmarking-tasks)
7. [Deleting tasks](#deleting-tasks)
8. [Listing all tasks](#listing-all-tasks)
9. [Accessing help](#accessing-help)

## Adding Tasks to be Done

Use the `todo` command to add a simple task without a specific date or time.

### Command Format:
```
todo <task description>
```

- `<task description>`: Description of the task you want to add.

### Example of Usage:
```
todo read book
```
### Expected Outcome:

```
===================================
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
===================================
```

## Adding Deadlines

To add a task with a deadline, use the `deadline` command. This task includes a description and a due date.

### Command Format:
```
deadline <task description> /by <due date>
```

- `<task description>`: Description of the task you want to add.
- `<due date>`: Deadline for the task in `yyyy-MM-dd HHmm` format.

### Example of Usage:
```
deadline return book /by 2024-09-15 1800
```
### Expected Outcome:

```
===================================
Got it. I've added this task:
  [D][ ] return book (by: Sep 15 2024 06:00 PM)
Now you have 3 tasks in the list.
===================================
```

## Adding Events

Use the `event` command to add a task that happens during a specific time period.

### Command Format:
```
event <task description> /from <start time> /to <end time>
```

- `<task description>`: Description of the event you want to add.
- `<start time>`: Start time of the event in `yyyy-MM-dd HHmm` format.
- `<end time>`: End time of the event in `yyyy-MM-dd HHmm` format.

### Example of Usage:
```
event project meeting /from 2024-09-20 1400 /to 2024-09-20 1600
```
### Expected Outcome:

```
===================================
Got it. I've added this task:
  [E][ ] project meeting (from: Sep 20 2024 02:00 PM to: Sep 20 2024 04:00 PM)
Now you have 3 tasks in the list.
===================================
```

## Finding tasks

You can find tasks that contain a specific keyword in the description using the `find` command.

### Command Format:
```
find <keyword>
```

- `<keyword>`: Word or phrase you want to search for in task descriptions.

### Example of Usage:
```
find book
```
### Expected Outcome:

```
===================================
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 15 2024 06:00 PM)
===================================
```

## Marking Tasks as Done

Use the `mark` command to mark a task as completed.

### Command Format:
```
mark <task number>
```

- `<task number>`: The index number of the task in the list to mark as completed.

### Example of Usage:
```
mark 1
```
### Expected Outcome:

```
===================================
Nice! I've marked this task as done:
  [T][X] read book
===================================
```

## Unmarking Tasks

To mark a previously completed task as incomplete, use the `unmark` command.

### Command Format:
```
unmark <task number>
```

- `<task number>`: The index number of the task in the list to mark as incomplete.

### Example of Usage:
```
unmark 1
```
### Expected Outcome:

```
===================================
OK, I've marked this task as not done yet:
  [T][ ] read book
===================================
```

## Deleting Tasks

The `delete` command allows you to remove a task from the list.

### Command Format:
```
delete <task number>
```

- `<task number>`: The index number of the task in the list to delete.

### Example of Usage:
```
delete 2
```
### Expected Outcome:

```
===================================
Noted. I've removed this task:
  [D][ ] return book (by: Sep 15 2024 06:00 PM)
Now you have 2 tasks in the list.
===================================
```

## Listing All Tasks

To see a list of all your tasks, use the `list` command.

### Command Format:
```
list
```

### Example of Usage:
```
list
```
### Expected Outcome:

```
===================================
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 15 2024 06:00 PM)
===================================
```

## Accessing Help

Use the `help command to see a list of available commands and their usage.

### Command Format:
```
help
```

### Example of Usage:
```
help
```
### Expected Outcome:

```
===================================
Here are the available commands:
1. todo <task> - Adds a to-do task.
2. deadline <task> /by <date> - Adds a deadline task.
3. event <task> /from <start> /to <end> - Adds an event task.
4. mark <task number> - Marks a task as completed.
5. unmark <task number> - Marks a task as incomplete.
6. delete <task number> - Deletes a task.
7. find <keyword> - Finds tasks containing the keyword.
8. list - Lists all tasks.
9. bye - Exits the application.
===================================
```
Thank you for using **Axel**, your personal task management assistant. Whether you're organizing your daily to-dos, keeping track of deadlines, or managing events, **Axel** is here to help you stay on top of your tasks with ease.

If you have any feedback or encounter issues while using the app, feel free to reach out for assistance. We hope **Axel** enhances your productivity and makes task management a breeze!

Happy tasking! ✨
