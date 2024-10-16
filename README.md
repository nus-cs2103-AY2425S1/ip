# SigmaBot User Guide
![Ui](./Ui.png)
SigmaBot is a smart task management chatbot designed to help you organize your tasks, events, and deadlines efficiently. It supports a range of commands that allow you to create, manage, and track your tasks, all while keeping things light-hearted with its built-in joke-telling feature!

## Features

### 1. Telling Jokes
Feeling a bit stressed? Lighten up your day by asking SigmaBot to tell you a joke.

**Command:**
```
joke
```

**Example:**
```
Why do Java developers wear glasses? Because they don’t C#.
```

---

### 2. Viewing the Task List
You can view all tasks, including todos, deadlines, and events, with a single command.

**Command:**
```
list
```

**Example:**
```
[T] [ ] 2103 iP task
   Description: finish this week's task
[T] [ ] Laundary
   Description: do laundry and set a timer
[E] [ ] 3231 midterm
   Description: midterm exam, on regular language
   Start Time: 18/SEP/2024
   End Time: 18/SEP/2024
   Location: COM1 SR1
```

---

### 3. Adding Tasks
SigmaBot allows you to add different types of tasks: todos, deadlines, and events. You will be prompted to enter the necessary information.

#### Adding a Todo
**Command:**
```
add 
todo
```

You will be prompted for:
- **Name**: The name of the todo.
- **Description**: A description of the todo task.

**Example:**
```
   add 
   todo
Enter task name: 
   Buy groceries
Enter description: 
   Buy fruits and vegetables.
```

#### Adding a Deadline (DDL)
**Command:**
```
add 
deadline
```

You will be prompted for:
- **Name**: The name of the deadline.
- **Description**: A description of the task.
- **Deadline (ddl)**: The due date in the format `yyyy-mm-dd`.

**Example:**
```
   add 
   deadline
Enter task name: 
   Submit assignment
Enter description: 
   Final CS project
Enter deadline (yyyy-mm-dd): 
   2024-12-01
```

#### Adding an Event
**Command:**
```
add 
event
```

You will be prompted for:
- **Name**: The event name.
- **Description**: A description of the event.
- **Start time**: The start date in the format `yyyy-mm-dd`.
- **End time**: The end date in the format `yyyy-mm-dd`.
- **Location**: The location of the event.

**Example:**
```
   add 
   event
Enter event name: 
   Conference
Enter description: 
   AI Conference 2024
Enter start date (yyyy-mm-dd): 
   2024-11-30
Enter end date (yyyy-mm-dd): 
   2024-12-02
Enter location: 
   Marina Bay Sands
```

---

### 4. Removing Tasks
If you want to remove a task from the list, simply use the delete command.

**Command:**
```
delete <task name>
```

**Example:**
```
delete 
Buy groceries
```

---

### 5. Sorting Tasks
To view your tasks in sorted order (by task name), use the sort command.

**Command:**
```
sort
```

---

### 6. Finding a Task by Keyword
To search for tasks containing a specific keyword in their name, use the find command.

**Command:**
```
find <keyword>
```

**Example:**
```
find iP
```

Expected Output:
```
[T] [ ] 2103 iP task
   Description: finish this week's task
```

---

### 7. Marking a Task as Done
Mark a task as completed using the mark command.

**Command:**
```
mark <task name>
```

**Example:**
```
mark 2103 iP task
```

Expected Output:
```
[T] [X] 2103 iP task
   Description: finish this week's task
```

---

### 8. Unmarking a Task
To unmark a task as incomplete, use the unmark command.

**Command:**
```
unmark <task name>
```

**Example:**
```
unmark 2103 iP task
```

Expected Output:
```
[T] [ ] 2103 iP task
   Description: finish this week's task
```

---

## Exiting SigmaBot
When you're done using SigmaBot, you can exit the session by typing:

**Command:**
```
   exit
It's jover?
   yes
```

SigmaBot will save your tasks and gracefully close.

---

Enjoy managing your tasks with **SigmaBot**! Stay productive, organized, and always have a laugh along the way.
