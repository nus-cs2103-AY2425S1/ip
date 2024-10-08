# Lolo Chatbot 🤖

Welcome to **Lolo**, your friendly Java-based chatbot! Lolo helps you manage tasks with ease, offering functionalities to add, list, mark, unmark, and delete tasks. With a sleek JavaFX interface, Lolo is designed to make task management efficient and enjoyable.

## Features 🌟

- **Add Tasks**: Easily add ToDos, Deadlines, and Events.
- **Mark Tasks**: Track progress by marking tasks as done or not done.
- **Delete Tasks**: Remove tasks that are no longer needed.
- **View Tasks**: List tasks by type and view them by date.
- **Tag Tasks**: Tag tasks by customisable attributes.
- **Graphical User Interface**: Enjoy a modern GUI with JavaFX.

## Getting Started 🚀

Follow these steps to get Lolo up and running on your local machine:

### 1. Download the executable JAR file (`lolo.jar`) from [latest release](https://github.com/chenle228/ip/releases/tag/v0.2) 🛠️

### 2. Double-click (`lolo.jar`) to launch 🌐

## User Guide 🛠️

### 1. **Add a ToDo Task** 📝
To add a new ToDo task, use the following format:
```bash
todo [task description]
```
**Example:**
```bash
todo Read Chapter 5
```
This command adds a task with the description "Read Chapter 5."

**Expected** Output: 
```
Got it. I've added this task:
[T][] Read Chapter 5 Tags:[]
Now you have 1 task(s) in the list.
```

### 2. **Add a Deadline Task** ⏰
To add a new Deadline task, use the following format:
```bash
deadline [task description] /by [yyyy-MM-dd HHmm]
```
**Example:**
```bash
deadline Submit Assignment /by 2024-09-10 2359
```
This command adds a deadline task to "Submit Assignment" by September 10, 2024, 23:59.

**Expected** Output: 
```
Got it. I've added this task:
[D][] Submit Assignment Tags:[] (by:Sep 10 2024, 11:59 PM)
Now you have 2 task(s) in the list.
```

### 3. **Add an Event Task** 📅
To add a new Event task, use the following format:
```bash
event [event description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]
```
**Example:**
```bash
event Team Meeting /from 2024-09-15 1400 /to 2024-09-15 1600
```
This command schedules an event "Team Meeting" from September 15, 2024, 14:00 to September 15, 2024, 16:00.

**Expected** Output: 
```
Got it. I've added this task:
[E][] Team Meeting Tags:[] (from: Sep 15 2024, 2:00 PM to Sep 15 2024, 4:00 PM)
Now you have 3 task(s) in the list.
```

### 4. **List All Tasks** 📋
To view all tasks, simply use the command:
```bash
list
```
This will display all your current tasks with their details.

**Expected** Output: 
```
Here are the tasks in your list:
1. [T][] todo Read Chapter 5 Tags:[]
2. [D][] Submit Assignment Tags:[] (by:Sep 10 2024, 11:59 PM)
3. [E][] Team Meeting Tags:[] (from: Sep 15 2024, 2:00 PM to Sep 15 2024, 4:00 PM)
```

### 5. **Mark a Task as Done** ✅
To mark a task as done, use the following format:
```bash
mark [task number]
```
**Example:**
```bash
mark 1
```
This command marks the task with number 1 as done.

**Expected** Output:
```
Nice! I've marked this task as done:
[T][X] todo Read Chapter 5 Tags:[]
```

### 6. **Unmark a Task** ❌
To unmark a task, use the following format:
```bash
unmark [task number]
```
**Example:**
```bash
unmark 1
```
This command changes the status of the task with number 1 to not done.

**Expected** Output:
```
OK, I've marked this task as as not done yet:
[T][] todo Read Chapter 5 Tags:[]
```

### 7. **Delete a Task** 🗑️
To delete a task, use the following format:
```bash
delete [task number]
```
**Example:**
```bash
delete 1
```
This command deletes the task with number 1 from your list.

**Expected** Output:
```
Noted. I've removed this task:
[T][] todo Read Chapter 5 Tags:[]
Now you have 2 task(s) in the list.
```

### 8. **Tag a Task (Extension Feature) ** 
To tag a task, use the following format:
```bash
tag [task_number] <tag_name>
```
**Example:**
```bash
tag 1 important
```
This command tags task 1 with the tag "important".

**Expected** Output:
```
OK, I've added the tag: #important to this this task:
[D][] Submit Assignment Tags:[important] (by:Sep 10 2024, 11:59 PM)
```

### 9. **Exit the Application** 👋
To exit Lolo, use the command:
```bash
bye
```
This will close the application.

Have fun trying out Lolo! 🙌
