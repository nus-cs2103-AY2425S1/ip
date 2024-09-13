
# TARS User Guide

**TARS** is an intuitive and efficient command-line-based chatbot designed to help you manage your tasks easily. This user guide will provide you with all the necessary information to effectively use TARS and its features.

---

## Quick Start

1. Ensure you have **Java 17** or above installed on your system.
2. Download the latest `tars.jar` file [here](#) or navigate to `src/main/java/tars/Launcher.java` to run the application directly.
3. To run the application via the JAR file:
   - Open a terminal.
   - Navigate to the folder where you saved the `tars.jar` file.
   - Execute the following command:
     ```bash
     java -jar tars.jar
     ```
   The app will greet you with:
   ```
   Hello! I'm TARS
   What can I do for you?
   ```

---

## Features

### 1. Viewing Help: `help`
Displays a message explaining how to access the help.

**Format**: 
```
help
```

### 2. Adding a Task

You can add three types of tasks to your list: **Todo**, **Deadline**, and **Event**.

#### a. Todo Task: `todo`
Adds a task that you need to do.

**Format**:
```
todo TASK_DESCRIPTION
```

**Example**:
```
todo Prepare the Endurance for launch
```

#### b. Deadline Task: `deadline`
Adds a task with a due date.

**Format**:
```
deadline TASK_DESCRIPTION /by DUE_DATE
```

**Example**:
```
deadline Solve the gravity equation /by 2067-01-01 2359
```

#### c. Event Task: `event`
Adds an event with a start and end time.

**Format**:
```
event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME
```

**Example**:
```
event Enter the wormhole /from 2067-01-12 1000 /to 2067-01-12 1200
```

---

### 3. Listing All Tasks: `list`
Displays a list of all tasks in your task list.

**Format**:
```
list
```

---

### 4. Marking a Task as Done: `mark`
Marks a specific task as completed.

**Format**:
```
mark TASK_NUMBER
```

**Example**:
```
mark 2
```

---

### 5. Unmarking a Task: `unmark`
Marks a task as not completed.

**Format**:
```
unmark TASK_NUMBER
```

**Example**:
```
unmark 2
```

---

### 6. Removing a Task: `remove`
Removes a task from your list.

**Format**:
```
remove TASK_NUMBER
```

**Example**:
```
remove 1
```

---

### 7. Finding a Task: `find`
Searches your task list for tasks that match a keyword.

**Format**:
```
find KEYWORD
```

**Example**:
```
find Cooper
```

---

### 8. Editing a Task: `edit`
Edits a task’s name, start time, or end time (for events), or a due date (for deadlines).

**Format**:
```
edit TASK_NUMBER /OPTION NEW_VALUE
```

**Options**:
- `/name`: To edit the task name.
- `/from`: To edit the start time of an event.
- `/to`: To edit the end time of an event.
- `/by`: To edit the due date of a deadline.

**Example**:
```
edit 3 /name Dock with the Ranger
edit 4 /from 2067-01-15 0900
```

---

### 9. Exiting the Program: `bye`
Exits the TARS application.

**Format**:
```
bye
```

Upon exiting, TARS will display:
```
See you on the other side, Coop
```

---

## Command Summary

| Action                  | Format                                              |
|-------------------------|-----------------------------------------------------|
| Help                    | `help`                                              |
| Add Todo                | `todo TASK_DESCRIPTION`                             |
| Add Deadline            | `deadline TASK_DESCRIPTION /by DUE_DATE`            |
| Add Event               | `event TASK_DESCRIPTION /from START_DATE /to END_DATE` |
| List Tasks              | `list`                                              |
| Mark Task               | `mark TASK_NUMBER`                                  |
| Unmark Task             | `unmark TASK_NUMBER`                                |
| Remove Task             | `remove TASK_NUMBER`                                |
| Find Task               | `find KEYWORD`                                      |
| Edit Task               | `edit TASK_NUMBER /OPTION NEW_VALUE`                |
| Exit                    | `bye`                                               |

---

This user guide ensures you have all the information you need to interact with **TARS** and efficiently manage your tasks.
