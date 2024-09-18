# Muke User Guide
![Screenshot of Product](./Ui.png)

Muke helps you stay organized by keeping track of tasks so you can focus on what truly matters. It’s:

	•	User-friendly and text-based: Muke is easy to interact with using simple text commands.
	•	Simple and quick to master: Get started with minimal learning effort.
	•	Exceptionally FAST: Handles tasks efficiently, catering to all your organizational needs.

## Adding Deadlines

Adding deadlines allows you to manage tasks that need to be completed by a specific date or time.

	•	Action and Outcome: Use the deadline command followed by the task description and /by followed by the deadline date or time.

### Example of Usage:

Example: `deadline return book /by 2019-10-15`

### Expected Outcome:

```
Got it. I've added this task:
  [D][ ] submit report (by: Oct 15 2019)
Now you have X tasks in the list.
```

## Adding Events

Adding events allows you to schedule tasks with a start and end time, helping you manage appointments or meetings.

	•	Action and Outcome: Use the event command followed by the task description, /from followed by the start time, and /to followed by the end time.

### Example of Usage:

Example: `event team meeting /from 2pm /to 4pm`

### Expected Outcome:

```
Got it. I've added this event:
  [E][ ] team meeting (from: 2pm to: 4pm)
Now you have X tasks in the list.
```

## Adding Todos

Adding todos helps you keep track of tasks without a specific deadline, allowing you to list things you need to do.

	•	Action and Outcome: Use the todo command followed by the task description to add a basic task.

### Example of Usage:

Example: `todo buy groceries`

### Expected Outcome:

```
Got it. I've added this task:
  [T][ ] buy groceries
Now you have X tasks in the list.
```

## Listing Tasks

Listing tasks lets you view all the tasks you have added, including todos, deadlines, and events.

	•	Action and Outcome: Use the list command to display all tasks currently in your task list.

### Example of Usage:

Example: `list`

### Expected Outcome:

```
Here are the tasks in your list:
  1. [T][ ] buy groceries
  2. [E][ ] team meeting (from: 2pm to: 4pm)
```

## Marking Tasks as Done

Marking tasks as done helps you keep track of completed tasks, providing a sense of accomplishment.

	•	Action and Outcome: Use the mark command followed by the task number to mark a task as completed.

### Example of Usage:

Example: `mark 2`

### Expected Outcome:

```
Nice! I've marked this task as done:
  [T][X] buy groceries
```

## Unmarking Tasks as Undone

Unmarking tasks allows you to revert tasks that were previously marked as done.

	•	Action and Outcome: Use the unmark command followed by the task number to unmark a completed task.

### Example of Usage:

Example: `unmark 3`

### Expected Outcome:

```
OK, I've marked this task as not done yet:
  [E][ ] team meeting (from: 2pm to: 4pm)
```

## Finding Tasks

Finding tasks allows you to search for specific tasks using keywords, helping you quickly locate what you need.

	•	Action and Outcome: Use the find command followed by a keyword to search for tasks containing that keyword.

### Example of Usage:

Example: `find cook`

### Expected Outcome:

```
Here are the matching tasks in your list:
  1. [D][ ] cook lunch (by: Oct 15 2019)
```
