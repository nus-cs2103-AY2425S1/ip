# DailyTasks User Guide

DailyTasks is a simple desktop application that allows you to manage and track your daily tasks efficiently. The app provides a variety of commands to add, manage, and organize your tasks such as to-dos, deadlines, and events. This guide will walk you through how to use DailyTasks.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
   - [Notes about Command Format](#notes-about-command-format)
   - [Adding a To-Do](#adding-a-to-do)
   - [Adding a Deadline](#adding-a-deadline)
   - [Adding an Event](#adding-an-event)
   - [Listing All Tasks](#listing-all-tasks)
   - [Searching for Tasks](#searching-for-tasks)
   - [Filtering For Ongoing Events by DateTime](#filtering-for-ongoing-events-by-datetime)
   - [Deleting a Task](#deleting-a-task)
   - [Marking a Task as Completed](#marking-a-task-as-completed)
   - [Unmark a Task as Completed](#unmark-a-task-as-completed)
   - [Setting Task Priority](#setting-task-priority)
   - [Save Data and Close the GUI](#save-data-and-close-the-gui)
- [Command Summary](#command-summary)
- [Credits](#credits)
   - [JavaFX Tutorial](#javafx-tutorial)

## Quick Start
1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest `.jar` file from the [latest release page](https://github.com/yongkheehou/ip/releases/tag/v0.3) or by directly clicking [this link](https://github.com/yongkheehou/ip/releases/download/v0.3/DailyTasks-all.jar).
3. Move the `.jar` file to a folder where you want to store your tasks.
4. Open a command terminal and navigate to the folder containing the `.jar` file.
5. Run the following command to start the application:
   ```bash
   java -jar Dailytasks-all.jar
   ```
6. Start entering tasks by using the available commands (see the list below for details).

## Features

### Notes about Command Format:
- Items in **angle brackets** `< >` indicate user inputs and are **mandatory**.
   - e.g. `<xyz>` in `todo <xyz>` or `<datetime>` in `deadline <xyz> /by <datetime>`.
   - For example, in the command `todo <xyz>`, `xyz` is a parameter that should be replaced with the user's task description.
   - e.g. `todo Buy groceries`.

- Parameters for all the commands must be entered in the order specified in this user guide.

- Extra parameters that do not belong to the command are not allowed.
   - e.g. `list extra` will not be interpreted as `list`.

---

### Adding a To-Do
Add a simple to-do task to your task list.

**Format:**
```bash
todo <xyz>
```

- `<xyz>`: Description of the to-do task.

**Example:**
```
todo Buy groceries
```
**Expected Output:**
```
Got it. I've added this task:
[T] [ ] [Priority: 0] Buy groceries
Now you have 1 tasks in the list.
```

### Adding a Deadline
Add a task with a deadline.

**Format:**
```
deadline <xyz> /by <datetime>
```

- `<xyz>`: Description of the task.
- `<datetime>`: Deadline for the task in the format `DD/MM/YYYY HHMM`.

**Example:**
```
deadline Submit project report /by 20/09/2024 1800
```

**Expected Output:**
```
Got it. I've added this task:
[D] [ ] [Priority: 0] Submit project report (by: Sep 20 2024, 6:00 pm)
Now you have 2 tasks in the list.
```


### Adding an Event
Add an event with a specific start and end time.

**Format:**
```
event <xyz> /from <datetime> /to <datetime>
```

- `<xyz>`: Description of the event.
- `<datetime>`: Start and end time in the format `DD/MM/YYYY HHMM`.

**Example:**
```
event Team meeting /from 18/09/2024 1000 /to 18/09/2024 1200
```

**Expected Output:**
```
Got it. I've added this task:
[E] [ ] [Priority: 0] Team meeting (from: Sep 18 2024, 10:00 am to: Sep 18 2024, 12:00 pm)
Now you have 3 tasks in the list.
```

### Listing All Tasks
Display all tasks (to-dos, deadlines, and events) in the task list.

**Format:**
```
list
```

**Example:**
```
list
```

**Expected Output:**
```
Here are the tasks in your list:
1. [T] [ ] [Priority: 0] Buy groceries
2. [D] [ ] [Priority: 0] Submit project report (by: Sep 20 2024, 6:00 pm)
3. [E] [ ] [Priority: 0] Team meeting (from: Sep 18 2024, 10:00 am to: Sep 18 2024, 12:00 pm)
```

### Searching for Tasks
Find tasks that contain the specified keyword in their description.

**Format:**
```
find <description>
```

- `<description>`: Keyword to search for in task descriptions.

**Example:**
```
find groceries
```

**Expected Output:**
```
Here are the tasks in your list:
1. [T] [ ] [Priority: 0] Buy groceries
```

### Filtering For Ongoing Events by DateTime
Filter for all ongoing events that are active during the specified date and time.

**Format:**
```
filter <datetime>
```

- `<datetime>`: Date and time in the format `DD/MM/YYYY HHMM` to filter events that are active during that time.

**Example:**
```
filter 18/09/2024 1100
```

**Expected Output:**
```
Here are the filtered tasks:
1. [E] [ ] [Priority: 0] Team meeting (from: Sep 18 2024, 10:00 am to: Sep 18 2024, 12:00 pm)
```

### Deleting a Task
Delete a task by its task index.

**Format:**
```
delete <taskIndex>
```

- `<taskIndex>`: Index number of the task to be deleted (as shown in the list command).

**Example:**
```
delete 2
```

**Expected Output:**
```
Noted. I've removed this task:
[D] [ ] [Priority: 0] Submit project report (by: Sep 20 2024, 6:00 pm)
Now you have 2 tasks in the list.
```

### Marking a Task as Completed
Mark a task as completed by its task index.

**Format:**
```
mark <taskIndex>
```

- `<taskIndex>`: Index number of the task to mark as completed.

**Example:**
```
mark 1
```

**Expected Output:**
```
Nice! I've marked this task as done:
[T] [X] [Priority: 0] Buy groceries
```

### Unmark a Task as Completed
Unmark a task as completed by its task index.

**Format:**
```
unmark <taskIndex>
```

- `<taskIndex>`: Index number of the task to mark as completed.

**Example:**
```
unmark 1
```

**Expected Output:**
```
OK, I've marked this task as not done yet:
[T] [ ] [Priority: 0] Buy groceries
```

### Setting Task Priority
Set the priority level of a task by its task index. The user can decide how they want to determine priority. One suggestion is to let the value 1 be the highest priority, and let the priorities decrease as the value increases.

**Format:**
```
priority <taskIndex> <taskPriority>
```

- `<taskIndex>`: Index number of the task to set priority for.
- `<taskPriority>`: Priority level of the task (integer values e.g., 1, 2, 3).

**Example:**
```
priority 2 1
```

**Expected Output:**
```
Noted. I've added the priority 1 to this task:
[E] [ ] [Priority: 1] Team meeting (from: Sep 18 2024, 10:00 am to: Sep 18 2024, 12:00 pm)
```

### Save Data and Close the GUI
The tasks are saved automatically to a text file (`data/save.txt`) **only** when you close the application successfully. The data is saved in plain text, allowing you to back it up or transfer it to another device.

**Format:**
```
bye
```

**Example:**
```
bye
```

**Expected Output:**
GUI closes immediately and the data is saved automatically to a text file (`data/save.txt`)

## Command Summary

| Action                          | Format                                        | Example                                                        |
|---------------------------------|-----------------------------------------------|----------------------------------------------------------------|
| **Add a To-Do**                 | `todo <xyz>`                                  | `todo Buy groceries`                                           |
| **Add a Deadline**              | `deadline <xyz> /by <datetime>`               | `deadline Submit project report /by 20/09/2024 1800`           |
| **Add an Event**                | `event <xyz> /from <datetime> /to <datetime>` | `event Team meeting /from 18/09/2024 1000 /to 18/09/2024 1200` |
| **List All Tasks**              | `list`                                        | `list`                                                         |
| **Search for Tasks**            | `find <description>`                          | `find groceries`                                               |
| **Filter Tasks by DateTime**    | `filter <datetime>`                           | `filter 20/09/2024 1200`                                       |
| **Delete a Task**               | `delete <taskIndex>`                          | `delete 2`                                                     |
| **Mark a Task as Completed**    | `mark <taskIndex>`                            | `mark 1`                                                       |
| **Unmark a Task as Completed**  | `unmark <taskIndex>`                          | `unmark 1`                                                     |
| **Set Task Priority**           | `priority <taskIndex> <taskPriority>`         | `priority 3 high`                                              |
| **Save Data and Close the GUI** | `bye`                                         | `bye`                                                          |
## Credits
### JavaFX Tutorial
The GUI code was largely referenced and inspired by [this JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html)
