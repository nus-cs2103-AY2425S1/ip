# Atlas User Guide
![Atlas](Ui.png)
Atlas is a simple and intuitive task management chatbot designed to help users manage their tasks, deadlines, and events efficiently. It allows users to add, mark, and delete for tasks through a conversational interface. This guide will walk you through the main features of Atlas and how to interact with it.

## Quick Start
To get started with Atlas:
1. Ensure you have **Java 17** [installed](https://blog.hubspot.com/website/check-java-verison#:~:text=You%20can%20also%20check%20your,the%20version%20of%20Java%20listed.) on your system.
2. Download the latest `atlas.jar` from [here](https://github.com/chongtzezhao/ip/releases/).
3. Navigate to the directory where `atlas.jar` is located and run the following command:

```bash
java -jar atlas.jar
```

## Features

### Adding Todos
Adds a Todo task to the list.
* **Format**: `todo <description>`
* **Example(s)**:
    * `todo Read "The Odyssey"`
* **Output**:

```
Got it. Task saved:
  [T][ ] Read "The Odyssey"
1 tasks in the list.
```

### Adding Deadlines
Adds a Deadline task to the list.
* **Format**: `deadline <description> /by <deadline>`
* **Example(s)**:
  * `deadline Submit thesis /by 2024-12-15`
* **Output**:

```
Got it. Task saved:
  [D][ ] Submit thesis (by: 15 Dec 2024)
2 tasks in the list.
```

### Adding Events
Adds an Event task to the list.
* **Format**: `event <description> /from <start time> /to <end time>`
* **Example(s)**:
  * `event World History Conference /from 2024-11-10 /to 2024-11-12`
* **Output**:

```
Got it. Task saved:
  [E][ ] World History Conference (from: 10 Nov 2024 to: 12 Nov 2024)
3 tasks in the list.
```

### Listing Tasks
Displays all tasks in the list.
Optionally sorts the task by date in ascending order.
* **Format**: `list [sort]`
* **Example(s)**:
  * `list`
* **Output**:

```
Here are your tasks:

[T][ ] Read "The Odyssey"
  [D][ ] Submit thesis (by: 15 Dec 2024)
[E][ ] World History Conference (from: 10 Nov 2024 to: 12 Nov 2024)
```

### Marking Tasks as Done
Marks a task as completed.
* **Format**: `mark <task number>`
* **Example(s)**:
  * `mark 1`
* **Output**:

```
Nice! Marked as done:
  [T][X] Read "The Odyssey"
```

### Unmarking Tasks
Marks a completed task as not done.
* **Format**: `unmark <task number>`
* **Example(s)**:
  * `unmark 1`
* **Output**:

```
Ah! Unmarked as not done:
  [T][ ] Read "The Odyssey"
```

### Deleting Tasks
Removes a task from the list.
* **Format**: `delete <task number>`
* **Example(s)**:
  * `delete 2`
* **Output**:

```
Sure. Task deleted:
  [D][ ] Submit thesis (by: 15 Dec 2024)
2 tasks in the list.
```
## Known Issues

- Logic issue: adding multiple task numbers when marking or delete is not supported. Only the first task number will be processed.

---