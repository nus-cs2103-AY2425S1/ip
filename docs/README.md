# Orion User Guide

![Product Screenshot](https://github.com/pradyuprasad/ip/blob/master/docs/Ui.png)

**Orion** is a task management application that helps you organize and track various tasks such as TODOs, deadlines, and events. With simple commands, you can add, modify, and view tasks, as well as interact with them using a graphical user interface (GUI).

---

## Features

### 1. Adding TODOs

To add a TODO task, use the `todo` command followed by the task description.

**Usage:**

`todo <task description>`

**Example:**

`todo Read Chapter 5`

**Expected Outcome:**  
A new TODO task titled "Read Chapter 5" will be added to the task list.

---

### 2. Adding Deadlines

Use the `deadline` command to add a task that has a specific due date and time.

**Usage:**

`deadline <task description> /by <due date and time in format dd/MM/yyyy HHmm>`

**Example:**

`deadline Submit report /by 02/02/2024 2359`

**Expected Outcome:**  
A task titled "Submit report" with a deadline of 2nd February 2024 at 23:59 will be added to the task list.

---

### 3. Adding Events

To schedule an event with a specific start and end time, use the `event` command.

**Usage:**

`event <event description> /from <start date and time> /to <end date and time>`

**Example:**

`event Project meeting /from 03/03/2024 1400 /to 03/03/2024 1600`

**Expected Outcome:**  
An event titled "Project meeting" will be added, scheduled from 2:00 PM to 4:00 PM on 3rd March 2024.

---

### 4. Listing All Tasks

To see all the tasks in your list, use the `list` command. This will show all TODOs, deadlines, and events, along with their completion status.

**Usage:**

`list`

**Expected Outcome:**  
A list of all tasks will be displayed, showing their type (TODO, Deadline, Event), description, and completion status.

---

### 5. Marking a Task as Done

To mark a task as completed, use the `mark` command followed by the task number.

**Usage:**

`mark <task number>`

**Example:**

`mark 1`

**Expected Outcome:**  
The first task in the list will be marked as done, and its status will be updated.

---

### 6. Unmarking a Task

To change a completed task back to "not done," use the `unmark` command followed by the task number.

**Usage:**

`unmark <task number>`

**Example:**

`unmark 1`

**Expected Outcome:**  
The first task will be marked as "not done."

---

### 7. Deleting a Task

To remove a task from the list, use the `delete` command followed by the task number.

**Usage:**

`delete <task number>`

**Example:**

`delete 2`

**Expected Outcome:**  
The second task will be removed from the task list.

---

### 8. Finding Tasks by Keyword

To find tasks that contain a specific keyword, use the `find` command.

**Usage:**

`find <keyword>`

**Example:**

`find meeting`

**Expected Outcome:**  
All tasks with the word "meeting" in their description will be displayed.

---

### 9. Exiting the Program

To close the application, use the `bye` command.

**Usage:**

`bye`

**Expected Outcome:**  
The program will exit, saving any changes made to the task list.

---

### 10. Saving Data

Orion automatically saves all changes to the task list to a file. You don't need to manually save your tasks.

**Expected Outcome:**  
All tasks are saved automatically to the `tasks.csv` file in the `data` directory, ensuring your tasks persist between sessions.

---

### 11. Loading Data

When the application starts, it automatically loads tasks from the saved `tasks.csv` file.

**Expected Outcome:**  
Your task list will be restored, and any tasks you previously added will be displayed upon starting Orion.

---

### 12. Error Handling

Orion includes robust error handling. If you enter an invalid command or improperly formatted input, you'll receive a helpful error message describing what went wrong and how to fix it.

---

**Enjoy using Orion to manage your tasks efficiently!**
