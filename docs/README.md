# Ruby User Guide

// Product screenshot goes here

Welcome to Ruby, your personal task management chatbot. Below is a guide on how to use Ruby’s available commands!

## Features 

### List of tasks: `list`

**Description**: Displays all the tasks currently stored in Ruby.

**Format**: `list`

- Ruby will show all your tasks with their statuses (done or not done).
- Tasks are listed in the order they were added.

### Tasks with no duration: `todo`

**Description**: Adds a task with no specific deadline.

**Format**: `todo TASK_DESCRIPTION`

- This adds a simple task.
- Tasks added with `todo` appear in the list with `[T]`.

**Example**: `todo Read a book`

### Tasks with a deadline: `deadline`

**Description**: Adds a task with a deadline.

**Format**: `deadline TASK_DESCRIPTION /by DATE`

- Dates should be provided in the format `YYYY-MM-DD HHmm`.
- Tasks added with `deadline` appear with `[D]` and the deadline date.

**Example**: deadline Submit assignment /by 2024-09-20 2359

### Tasks with a start and end date: `event`

**Description**: Adds a task that occurs during a specific time period.

**Format**: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

- Dates should be provided in the format `YYYY-MM-DD HHmm`.
- Tasks added with `event` appear with `[E]`, start date, and end date.

**Example**: event Project meeting /from 2024-09-22 2359 /to 2024-09-23 2359

### Remove task: `delete`

**Description**: Removes a task from the list.

**Format**: `delete TASK_NUMBER`

- Use the task number from the `list` command to specify which task to delete.

**Example**: delete 2

### Mark tasks: `mark`

**Description**: Marks a task as done.

**Format**: `mark TASK_NUMBER`

- Marks the specified task as done, displayed with `[X]`.

**Example**: mark 1

### Unmark tasks: `unmark`

**Description**: Marks a task as not done.

**Format**: `unmark TASK_NUMBER`

- Unmarks the specified task, displayed with `[ ]`.

**Example**: unmark 1

### Exiting Ruby: `bye`

**Description**: Exits Ruby.

**Format**: `bye`

- Use this command to exit the application. Ruby will automatically save your tasks.

### Sort tasks: `sort`

**Description**: Sorts tasks alphabetically.

**Format**: `sort`

- This will reorder the tasks alphabetically by their description.

### Search for specific tasks: `find`

**Description**: Finds tasks that match a specific keyword.

**Format**: `find KEYWORD`

- Returns a list of tasks containing the specified keyword.

**Example**: find book