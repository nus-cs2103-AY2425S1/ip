
---

# MentalHealth User Guide

## Product Image

![Ui](./Ui.png)

## Product Introduction

**MentalHealth** is a chatbot designed to help users manage their tasks through effective organization. It supports commands for managing todos, deadlines, events, marking tasks as completed, updating existing tasks, and searching tasks. This guide provides an overview of all the supported commands and how to use them.

---

## Get Started

### Requirements
- Ensure you have **Java 17** or above installed on your system.

### Download the JAR File
- Download the latest version of the **MentalHealth.jar** file [here](https://github.com/thony-ui/ip/releases/tag/A-Release) or navigate to `src/main/java/health/Launcher.java` to run the application directly.

### Running the Application

To run the application via the JAR file, follow these steps:

1. **Open a terminal**.
2. Navigate to the folder where you saved the `MentalHealth.jar` file.
3. Execute the following command:

    ```bash
    java -jar MentalHealth.jar
    ```

4. The app will greet you with the following message:

    ```bash
    Hello! I'm your friendly ChatBot assistant called MentalHealth :)
    
    What can I do for you?
    ```

---

# Commands

---
### Adding Deadlines

Use the `deadline` command to add a deadline to your list. The format is:

```bash
deadline <task description> /by <date in dd/mm/yyyy> <time in 24-hour format>
```

#### Example:

```bash
deadline return book /by 2/12/2019 1800
```

#### Expected Output:

```bash
MentalHealth bot replies:

Okays! I've added this task:
[D][ ] return book (by: Dec 2 2019 18:00)

Now you have 1 tasks in the list.
```

### Todo Tasks

Use the `todo` command to add a simple task to your list.

```bash
todo <task description>
```

#### Example:

```bash
todo borrow book
```

#### Expected Output:

```bash
MentalHealth bot replies:

Okays! I've added this task:
[T][ ] todo borrow book

Now you have 2 tasks in the list.
```

### Event Scheduling

You can add events with specified start and end times using the `event` command.

```bash
event <event description> /from <day> <start time> /to <end time>
```

#### Example:

```bash
event project meeting /from Mon 2pm /to 4pm
```

#### Expected Output:

```bash
MentalHealth bot replies:

Okays! I've added this task:
[E][ ] project meeting (from: Mon 14:00 to 16:00)

Now you have 3 tasks in the list.
```

### Marking Tasks as Completed

To mark a task as completed, use the `mark` command followed by the task index.

```bash
mark <index>
```

#### Example:

```bash
mark 2
```

#### Expected Output:

```bash
MentalHealth bot replies:

Okays! I've marked this task as done:
[T][X] borrow book

Now you have 3 tasks in the list.
```

### Unmarking Completed Tasks

To unmark a task (i.e., mark it as incomplete), use the `unmark` command followed by the task index.

```bash
unmark <index>
```

#### Example:

```bash
unmark 2
```

#### Expected Output:

```bash
MentalHealth bot replies:

Okays! I've marked this task as not done
[T][ ] borrow book

Now you have 3 tasks in the list.
```

### Deleting Tasks

To remove a task from your list, use the `delete` command followed by the task index.

```bash
delete <index>
```

#### Example:

```bash
delete 2
```

#### Expected Output:

```bash
MentalHealth bot replies:

Alrighty! I will remove this task:
[T][ ] borrow book

Now you have 2 tasks in the list.
```

### Listing All Tasks

Use the `list` command to see all your todos, deadlines, and events.

```bash
list
```

#### Expected Output:

```bash
MentalHealth bot replies:

your TODO's
1. [D][ ] return book (by: Dec 2 2019 18:00)  
2. [E][ ] project meeting (from: Mon 14:00 to 16:00)
```

### Updating Task Descriptions

To update the description of a task, use the `update` command, followed by the task index and the new description.

```bash
update <index> /description <new description>
```

#### Example:

```bash
update 1 /description play
```

#### Expected Output:

```bash
MentalHealth bot replies:

Alrighty! I have updated this task:
[D][ ] play (by: Dec 2 2019 18:00)  

Now you have 2 tasks in the list.
```

### Finding Tasks

You can search for tasks by a keyword using the `find` command. It will return all tasks that match the search term.

```bash
find <keyword>
```

#### Example:

```bash
find play
```

#### Expected Output:

```bash
Here are your matching tasks!

Mentalhealth bot replies:

your TODO's
1. [D][ ] play (by: Dec 2 2019 18:00)
```

---

## Command Summary Table

| Command          | Description                                          | Example Usage                            | Expected Output                                                   |
|------------------|------------------------------------------------------|------------------------------------------|-------------------------------------------------------------------|
| `todo`           | Adds a todo task.                                    | `todo borrow book`                       | `[T][ ] borrow book`                                              |
| `deadline`       | Adds a task with a deadline.                         | `deadline return book /by 2/12/2019 1800`| `[D][ ] return book (by: Dec 2 2019 18:00)`                        |
| `event`          | Adds an event with start and end times.              | `event project meeting /from Mon 2pm /to 4pm`| `[E][ ] project meeting (from: Mon 14:00 to 16:00)`                |
| `mark`           | Marks a task as completed.                           | `mark 1`                                | `[T][X] borrow book`                                              |
| `unmark`         | Marks a completed task as incomplete.                | `unmark 1`                              | `[T][ ] borrow book`                                              |
| `delete`         | Deletes a task at the specified index.               | `delete 1`                              | `Deleted: [T][ ] borrow book`                                      |
| `list`           | Lists all tasks.                                     | `list`                                  | Displays all tasks in the list.                                    |
| `update`         | Updates the description of a task.                   | `update 1 /description play`             | `Updated: [T][ ] play`                                             |
| `find`           | Finds and lists tasks that match the search keyword. | `find book`                             | Lists tasks matching the keyword "book."                           |

--- 

This table summarizes all the available commands for easy reference.