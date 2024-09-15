# DailyTasks User Guide

DailyTasks is a simple desktop application that allows you to manage and track your daily tasks efficiently. The app provides a variety of commands to add, manage, and organize your tasks such as to-dos, deadlines, and events. This guide will walk you through how to use DailyTasks.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a To-Do](#adding-a-to-do)
    - [Adding a Deadline](#adding-a-deadline)
    - [Adding an Event](#adding-an-event)
    - [Listing All Tasks](#listing-all-tasks)
    - [Searching for Tasks](#searching-for-tasks)
    - [Filtering Tasks by DateTime](#filtering-tasks-by-datetime)
    - [Deleting a Task](#deleting-a-task)
    - [Marking a Task as Completed](#marking-a-task-as-completed)
    - [Setting Task Priority](#setting-task-priority)
- [Saving Data](#saving-data)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest `.jar` file from the releases page.
3. Move the `.jar` file to a folder where you want to store your tasks.
4. Open a command terminal and navigate to the folder containing the `.jar` file.
5. Run the following command to start the application:
   ```bash
   java -jar dailytasks.jar
   ```
6. Start entering tasks by using the available commands (see the list below for details).

## Features

### Adding a To-Do
Add a simple to-do task to your task list.

**Format:**
```bash
todo <xyz>
```

- `<xyz>`: Description of the to-do task.

**Example:**
```bash
todo Buy groceries
```

### Adding a Deadline
Add a task with a deadline.

**Format:**
```bash
deadline <xyz> /by <datetime>
```

- `<xyz>`: Description of the task.
- `<datetime>`: Deadline for the task in the format `DD/MM/YYYY HHMM`.

**Example:**
```bash
deadline Submit project report /by 20/09/2024 1800
```

### Adding an Event
Add an event with a specific start and end time.

**Format:**
```bash
event <xyz> /from <datetime> /to <datetime>
```

- `<xyz>`: Description of the event.
- `<datetime>`: Start and end time in the format `DD/MM/YYYY HHMM`.

**Example:**
```bash
event Team meeting /from 18/09/2024 1000 /to 18/09/2024 1200
```

### Listing All Tasks
Display all tasks (to-dos, deadlines, and events) in the task list.

**Format:**
```bash
list
```

**Example:**
```bash
list
```

### Searching for Tasks
Find tasks that contain the specified keyword in their description.

**Format:**
```bash
find <description>
```

- `<description>`: Keyword to search for in task descriptions.

**Example:**
```bash
find groceries
```

### Filtering Tasks by DateTime
Filter all tasks that are active during the specified date and time.

**Format:**
```bash
filter <datetime>
```

- `<datetime>`: Date and time in the format `DD/MM/YYYY HHMM` to filter tasks that are active during that time.

**Example:**
```bash
filter 20/09/2024 1200
```

### Deleting a Task
Delete a task by its task index.

**Format:**
```bash
delete <taskIndex>
```

- `<taskIndex>`: Index number of the task to be deleted (as shown in the list command).

**Example:**
```bash
delete 2
```

### Marking a Task as Completed
Mark a task as completed by its task index.

**Format:**
```bash
mark <taskIndex>
```

- `<taskIndex>`: Index number of the task to mark as completed.

**Example:**
```bash
mark 1
```

### Setting Task Priority
Set the priority level of a task by its task index.

**Format:**
```bash
priority <taskIndex> <taskPriority>
```

- `<taskIndex>`: Index number of the task to set priority for.
- `<taskPriority>`: Priority level of the task (integer values e.g., 1, 2, 3).

**Example:**
```bash
priority 3 1
```

## Saving Data
- The tasks are saved automatically to a text file (`data/save.txt`) **only** when you close the application successfully.
- The data is saved in plain text, allowing you to back it up or transfer it to another device.

## Command Summary
| Action                         | Format                                                                  | Example                                                    |
|---------------------------------|-------------------------------------------------------------------------|------------------------------------------------------------|
| **Add a To-Do**                 | `todo <xyz>`                                                            | `todo Buy groceries`                                        |
| **Add a Deadline**              | `deadline <xyz> /by <datetime>`                                          | `deadline Submit project report /by 20/09/2024 1800`        |
| **Add an Event**                | `event <xyz> /from <datetime> /to <datetime>`                            | `event Team meeting /from 18/09/2024 1000 /to 18/09/2024 1200` |
| **List All Tasks**              | `list`                                                                  | `list`                                                     |
| **Search for Tasks**            | `find <description>`                                                    | `find groceries`                                           |
| **Filter Tasks by DateTime**    | `filter <datetime>`                                                     | `filter 20/09/2024 1200`                                    |
| **Delete a Task**               | `delete <taskIndex>`                                                    | `delete 2`                                                 |
| **Mark a Task as Completed**    | `mark <taskIndex>`                                                      | `mark 1`                                                   |
| **Set Task Priority**           | `priority <taskIndex> <taskPriority>`                                   | `priority 3 high`                                          |