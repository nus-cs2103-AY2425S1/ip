# Bottle Task Manager User Guide

## Overview

Bottle is a task management application designed to help users create, manage, and track tasks efficiently. It offers features to add various types of tasks, mark them as completed, and store them persistently.

---
## Table of Contents

1. **Getting Started**
    - Installation
    - Running the Application

2. **Basic Commands**
    - Adding Tasks
        - ToDo
        - Deadline
        - Event
    - Marking Tasks
    - Unmarking Tasks
    - Deleting Tasks
    - Finding Tasks
    - Listing Tasks
    - Exiting the Application

3. **Error Handling**
    - Common Errors
    - File Management Issues

4. **Advanced Features**
    - Task Filtering
    - File Storage Management

5. **FAQs**

---

## 1. Getting Started

### Installation

To use Bottle, ensure you have Java installed on your machine. Download the latest version of Bottle from the repository, and ensure you have the required libraries for JavaFX.

### Running the Application

Run the application by executing the following command in your terminal:

```bash
java -jar bottle.jar
```

Make sure to replace `bottle.jar` with the actual name of your jar file.
### Sample Product screenshot

![img.png](Ui.png)
---

## 2. Basic Commands

### Adding Tasks

#### ToDo

To add a simple task, use the following command format:

```
todo <task_description>
```

**Example:**

```
todo Buy groceries
```

#### Deadline

To add a task with a deadline, use:

```
deadline <task_description> /by <dd/MM/yyyy HHmm>
```

**Example:**

```
deadline Submit assignment /by 15/09/2024 1700
```

#### Event

To add an event, use:

```
event <event_description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>
```

**Example:**

```
event Team meeting /from 14/09/2024 0900 /to 14/09/2024 1000
```

---

### Marking Tasks

To mark a task as done, use:

```
mark <task_index>
```

**Example:**

```
mark 1
```

### Unmarking Tasks

To unmark a task, use:

```
unmark <task_index>
```

**Example:**

```
unmark 1
```

### Deleting Tasks

To delete a task, use:

```
delete <task_index>
```

**Example:**

```
delete 2
```

### Finding Tasks

To find tasks containing a specific keyword, use:

```
find <keyword>
```

**Example:**

```
find groceries
```

### Listing Tasks

To view all your tasks, simply use:

```
list
```

### Exiting the Application

To exit the application, type:

```
bye
```

### Check for clashes in events

To check if any events clash with one another, type:

```
check clash
```

---

## 3. Error Handling

### Common Errors

- **Invalid Command Format:** Ensure you follow the correct command structure.
- **Missing Parameters:** Commands like `deadline` and `event` require additional parameters; make sure all necessary information is provided.

### File Management Issues

- If the application cannot find the storage file, it will create a new one in the specified directory.
- Permissions errors may occur if the application is unable to access the specified file location.

---

## 4. Advanced Features

### Task Filtering

You can filter tasks based on specific criteria using the `find` command.

### File Storage Management

Tasks are automatically saved to a file, ensuring that your data persists across application restarts.

### Check for clashes in events

You can check if any of the events in the task list clash with each other in terms of timing.

---

## 5. FAQs

- **What if I enter an invalid date?**
  The application will notify you with an error message indicating the expected format.

- **Can I have multiple tasks with the same description?**
  Yes, but be cautious as it may make task management confusing.
