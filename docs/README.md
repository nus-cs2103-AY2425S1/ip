
# Weeny User Guide

Weeny is a desktop application that allows users to manage their tasks efficiently. 
[Product screenshot](/Ui.png)

## Key Features

- **Add Tasks:** Manage different types of tasks: ToDos, Deadlines, and Events.
- **Mark/Unmark Tasks:** Mark tasks as complete or incomplete with ease.
- **List Tasks:** View all tasks in a list format.
- **Delete Tasks:** Remove tasks from your list.
- **Search Tasks:** Find specific tasks using keywords.
- **Schedule View:** View all tasks with dates in chronological order.

## Quick Start

1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `weeny.jar` file from the [page](https://github.com/clarud/ip/).
3. Copy the file to a folder where you want to store your tasks.
4. Open a command terminal, navigate to the folder, and run the app using the command:
   ```
   java -jar weeny.jar
   ```
5. The GUI should load, and you're ready to start managing tasks!
6. You can type commands into the input box and press **Enter** to execute them. Example commands:
   - `todo Clean the room` to add a task.
   - `list` to view all tasks.
   - `bye` to exit the application.

## Features

### Adding a ToDo Task

Add a general task to your task list.

**Example:**
```
todo Clean the room
```
**Expected Output:**
```
Gotcha, I have added:
[T][ ] Clean the room
You have a total of X tasks in the list.
```

### Adding a Deadline

Add a task that has a specific deadline.

**Example:**
```
deadline Prepare Presentation /by 05/09/2024 0930
```
**Expected Output:**
```
Gotcha, I have added:
[D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
You have a total of X tasks in the list.
```

### Adding an Event

Add a task that has a start and end date/time.

**Example:**
```
event English Test /from 05/09/2024 0930 /to 05/09/2024 1030
```
**Expected Output:**
```
Gotcha, I have added:
[E][ ] English Test (from: Sep 5 2024 9:30 am to: Sep 5 2024 10:30 am)
You have a total of X tasks in the list.
```

### Deleting a Task

Remove a task from your list by its index number.

**Example:**
```
delete 2
```
**Expected Output:**
```
Spooof! The task magically disappeared:
[T][X] Clean the room
You have a total of X tasks in the list.
```

### Marking a Task as Done

Mark a task as completed.

**Example:**
```
mark 5
```
**Expected Output:**
```
Nice! I've marked this task as done:
[T][X] Clean the room
You have a total of X tasks in the list.
```

### Unmarking a Task

Unmark a completed task, indicating that it's not yet done.

**Example:**
```
unmark 5
```
**Expected Output:**
```
You're going back on your words? Tsk I have unmarked:
[T][ ] Clean the room
You have a total of X tasks in the list.
```

### Listing All Tasks

View all tasks in your task list.

**Example:**
```
list
```
**Expected Output:**
```
Here you go! All the tasks you have are here:
1. [T][ ] Clean the room
2. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
3. [E][ ] English Test (from: Sep 5 2024 9:30 am to: Sep 5 2024 10:30 am)
...
```

### Finding Tasks

Search for tasks by keywords.

**Example:**
```
find presentation
```
**Expected Output:**
```
Hmm.. these are the tasks that matches 
1. [D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)
```

### Viewing Schedule

View tasks that have start and end dates, sorted by time.

**Example:**
```
schedule 22/08/2024
```
**Expected Output:**
```
I have gathered all the tasks you have on 22/08/2024:
1. [E][ ] Team meeting (from: Aug 22 2024 10:00 am to: Aug 22 2024 11:30 am)
...
```

### Exiting the App

Exit the Weeny application.

**Example:**
```
bye
```
**Expected Output:**
```
I guess it's my meal time! Bye!
```

## Command Summary

| Action            | Format                                                                 |
|-------------------|------------------------------------------------------------------------|
| Add a ToDo        | `todo TASK`                                                            |
| Add a Deadline    | `deadline TASK /by DATE TIME`                                          |
| Add an Event      | `event TASK /from START_DATE TIME /to END_DATE TIME`                   |
| Delete a Task     | `delete INDEX`                                                         |
| Mark as Done      | `mark INDEX`                                                           |
| Unmark as Done    | `unmark INDEX`                                                         |
| List Tasks        | `list`                                                                 |
| Find Tasks        | `find KEYWORD`                                                         |
| View Schedule     | `schedule DATE`                                                        |
| Exit App          | `bye`                                                                  |



