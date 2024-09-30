
---

# KillJoy User Guide

![KillJoy UI](./Ui.png)

Meet **KillJoy**—your sassy, tech-savvy sidekick who's always three steps ahead. Need something done? Relax, KillJoy’s already on it. With a personality as sharp as its skills, this assistant doesn’t just follow orders—it owns them. Whether it’s organizing your tasks or throwing some banter your way, KillJoy is here to ensure life’s never boring.

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
    - [Adding a TODO](#adding-a-todo)
    - [Adding a Deadline](#adding-a-deadline)
    - [Adding an Event](#adding-an-event)
    - [Listing Tasks](#listing-tasks)
    - [Marking and Unmarking Tasks](#marking-and-unmarking-tasks)
    - [Deleting a Task](#deleting-a-task)
    - [Archiving Completed Tasks](#archiving-completed-tasks)
    - [Loading Tasks from Archive](#loading-tasks-from-archive)
    - [Finding Tasks](#finding-tasks)
- [Exiting KillJoy](#exiting-killjoy)

## Getting Started

## How to Test KillJoy JAR File

To test the `KillJoy.jar` file, follow the steps below:

### Prerequisites

- Ensure you have Java 17 installed on your system. You can verify your Java version by running the following command in your terminal or command prompt:

  ```bash
  java -version
  ```

  You should see output similar to:
  ```bash
  java version "17.0.x"
  ```

### Steps to Test

1. **Download the JAR File**:
- Navigate to the [Releases](https://github.com/thisisaditya17/ip/releases) section of this repository.
- Download the latest `killjoy.jar` file.

2. **Open Terminal or Command Prompt**:

3. **Navigate to the Directory**:
- Use the `cd` command to navigate to the directory where you downloaded the `killjoy.jar` file. For example:

  ```bash
  cd Downloads
  ```

4. **Run the JAR File**:
- To execute the JAR file, use the following command:

  ```bash
  java -jar killjoy.jar
  ```

5. **Interact with KillJoy**:
- Once the application starts, KillJoy will greet you, and you can begin using the various commands (e.g., adding tasks, marking tasks, deleting tasks).


---

## Features

### Adding a TODO

**Command**:  
`todo <description>`

**Example**:  
`todo CS2100 quiz`

**Expected Outcome**:  
Adds a new task with the description provided. The task is automatically marked as incomplete.

```
Yo Dawgg!! Added this task:
[T][ ] CS2100 Quiz
Now you have 1 task in the list.
```

---

### Adding a Deadline

**Command**:  
`deadline <description> /by <date/time>`

**Example**:  
`deadline CS2103T ip /by 2024-09-20 16:00`

**Expected Outcome**:  
Adds a deadline task that is due by the specified date and time.

```
Yo Dawgg!! Added this task:
[D][ ] CS2103T ip (by: 20 Sep 2024 16:00:00)
Now you have 2 tasks in the list.
```

---

### Adding an Event

**Command**:  
`event <description> /from <start time> /to <end time>`

**Example**:  
`event Team meeting /from 2024-11-10 14:00 /to 2024-11-10 16:00`

**Expected Outcome**:  
Adds an event task that occurs between the specified start and end times.

```
Yo Dawgg!! Added this task:
[E][ ] Team meeting (from: 10 Nov 2024 14:00:00 to: 10 Nov 2024, 16:00:00)
Now you have 3 tasks in the list.
```

---

### Listing Tasks

**Command**:  
`list`

**Expected Outcome**:  
Displays all tasks in your list, including TODOs, DEADLINES, and EVENTS. Each task shows its type, status (marked/unmarked), and relevant details (e.g., due dates or times).

```
Here are the tasks in your list:
1. [T][ ] CS2100 Quiz
2. [D][ ] Finish project (by: Dec 1 2024 18:00)
3. [E][ ] Team meeting (from: 10 Nov 2024 14:00:00 to: 10 Nov 2024, 16:00:00)
```

---

### Marking and Unmarking Tasks

**Commands**:  
`mark <task number>`  
`unmark <task number>`

**Example**:  
`mark 1`

**Expected Outcome**:  
Marks the specified task as complete or incomplete.

```
Ayee Yooo! I've marked this task okaayyyyy:
[T][X] CS2100 Quiz
```

---

### Deleting a Task

**Command**:  
`delete <task number>`

**Example**:  
`delete 2`

**Expected Outcome**:  
Removes the specified task from your list.

```
Okay! I'll remove this task:
[D][ ] Finish project (by: Dec 1 2024 18:00)
Now you have 2 tasks in the list.
```

---

### Archiving Completed Tasks

**Command**:  
`archive`

**Expected Outcome**:  
Completed tasks are moved to the "Archive" folder, saved in a uniquely named file, ensuring no overwriting.

```
Tasks have been archived!
```

---

### Loading Tasks from Archive

**Command**:  
`load <archive file name with extension>`

**Expected Outcome**:  
Restores tasks from the specified archive file, adding them to your task list.

```
Tasks have been loaded!
```


---

### Finding Tasks

**Command**:  
`find <keyword>`

**Example**:  
`find Quiz`

**Expected Outcome**:  
Displays all tasks that contain the specified keyword in their descriptions.

```
Here are the matching tasks in your list:
1. [T][ ] CS2100 Quiz
```

---

## Exiting KillJoy

**Command**:  
`bye`

**Expected Outcome**:  
Ends the session and saves the current task list. KillJoy bids you farewell with a sassy message.

```
Bubyyeee & Don't Stwesszz. Time to hide now!!
```
---
This is generated with help of ChatGPT