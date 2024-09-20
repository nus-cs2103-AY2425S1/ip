# Ruby User Guide

![Product Image](/Ui.png.jpg)

Welcome to Ruby, your personal task management chatbot. Below is a guide on how to use Ruby’s available commands!

## Getting Started

Before using Ruby, follow these steps:

1. Install Java 17 on your computer. 
2. Under GitHub Releases, download the latest version of the jar file of Ruby (`ruby.jar`).
3. Open a terminal and navigate to the directory containing the jar file using the `cd` command.
4. Run the command java -jar ruby.jar. A GUI resembling the one above shouold appear.
5. You are now ready to use Ruby! Follow the rest of the guide to learn about Ruby's features.

## Features 

### List of tasks: `list`

**Description**: Displays all the tasks currently stored in Ruby.

**Format**: `list`

- Ruby will show all your tasks with their statuses (done or not done).
- Tasks are listed in the order they were added.

![List](/list.jpg)

---

### Tasks with no duration: `todo`

**Description**: Adds a task with no specific deadline.

**Format**: `todo TASK_DESCRIPTION`

- This adds a simple task.
- TASK_DESCRIPTION must not be empty
- Tasks added with `todo` appear in the list with `[T]`.

**Example**: `todo Read a book` Adds a task of type todo into your list of tasks.

![Todo](/todo.jpg)

---

### Tasks with a deadline: `deadline`

**Description**: Adds a task with a deadline.

**Format**: `deadline TASK_DESCRIPTION /by DATE`

- Dates should be provided in the format `YYYY-MM-DD HHmm`.
- DATE must not be empty.
- TASK_DESCRIPTION must not be empty.
- Tasks added with `deadline` appear with `[D]` and the deadline date.

**Example**: `deadline Submit assignment /by 2024-09-20 2359` Adds a task due on 2024-09-20 2359.

![Deadline](/deadline.jpg)

---

### Tasks with a start and end date: `event`

**Description**: Adds a task that occurs during a specific time period.

**Format**: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

- Dates should be provided in the format `YYYY-MM-DD HHmm`.
- DATE must not be empty.
- TASK_DESCRIPTION must not be empty.
- START_DATE and END_DATE must not be empty.
- START_DATE must be before END_DATE.
- Tasks added with `event` appear with `[E]`, start date, and end date.

**Example**: `event Project meeting /from 2024-09-22 2359 /to 2024-09-23 2359` Adds a task from the period 2024-09-22 2359 /to 2024-09-23 2359.

![Event](/event.jpg)

---

### Remove task: `delete`

**Description**: Removes a task from the list.

**Format**: `delete TASK_NUMBER`

- Use the task number from the `list` command to specify which task to delete.
- TASK_NUMBER must not be empty.
- TASK_NUMBER must be within the number of tasks in task list.

**Example**: `delete 2` deletes the second task in the task list.

![Delete](/delete.jpg)

---

### Mark tasks: `mark`

**Description**: Marks a task as done.

**Format**: `mark TASK_NUMBER`

- Marks the specified task as done, displayed with `[X]`.
- TASK_NUMBER must not be empty.
- TASK_NUMBER must be within the number of tasks in task list.

**Example**: `mark 1` marks task 1 in the list of tasks as done. 

![Mark](/mark.jpg)

---

### Unmark tasks: `unmark`

**Description**: Marks a task as not done.

**Format**: `unmark TASK_NUMBER`

- Unmarks the specified task, displayed with `[ ]`.
- TASK_NUMBER must not be empty.
- TASK_NUMBER must be within the number of tasks in task list.

**Example**: `unmark 1` marks task 1 in the list of tasks as not done.

![Unmark](/unmark.jpg)

---

### Exiting Ruby: `bye`

**Description**: Exits Ruby.

**Format**: `bye`

- Use this command to exit the application. Ruby will automatically save your tasks.

---

### Sort tasks: `sort`

**Description**: Sorts tasks alphabetically.

**Format**: `sort`

- This will reorder the tasks alphabetically by their description.

![Sort](/sort.jpg)

---

### Search for specific tasks: `find`

**Description**: Finds tasks that match a specific keyword.

**Format**: `find KEYWORD`

- Returns a list of tasks containing the specified keyword.
- When KEYWORD is empty, `find` performs the same function as `list`

**Example**: `find book` searches for tasks with book in their name.

![Find](/find.jpg)

---

Feel free to explore these commands and efficiently manage your tasks with Ruby!

NOTE: ChatGPT was used to generate JavaDoc comments 
NOTE: FXML files and JavaFX files were taken from JavaFX tutorial