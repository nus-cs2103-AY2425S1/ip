# Asura User Guide

![Asura Image](./Ui.png)

Asura helps you track your tasks through a chatbot.

---

## Commands

### 1. `list`
Displays the items in the task list.

**Example:** list

**Expected Output**
```
Here are the tasks in your list:
1. <Task>
2. <Another Task>
3. <Yet Another Task>
```


---

### 2. `todo <Task name>`
Adds a new todo task into the task list.

**Parameters:**
- `<Task name>`: The name of the task to be added.

**Example:** todo Read Book

**Expected Output**
```
Got it. I've added this todo:
[T][] Read Book
Now you have # tasks in your list.
```

---

### 3. `deadline <Task name> /by <Date> <Time>`
Adds a new deadline task into the task list.

**Parameters:**
- `<Task name>`: The name of the task.
- `/by <DateTime>`: The due date and time for the task.

**Example:** deadline Submit assignment /by 2024-10-01 12:10:20

**Expected Output**
```
Got it. I've added this deadline:
[D][] Submit assignment (by: Oct 01 2024 12:10:20pm)
Now you have # tasks in your list.
```

---

### 4. `event <Task name> /from <DateTime> /to <DateTime>`
Adds a new event task into the task list.

**Parameters:**
- `<Task name>`: The name of the event.
- `/from <DateTime>`: The start date and time of the event.
- `/to <DateTime>`: The end date and time of the event.

**Example:** event Project meeting /from 2024-09-20 10:00:00 /to 2024-09-20 12:00:00

**Expected Output**
```
Got it. I've added this deadline:
[E][] Submit assignment (from: 2024-09-20 10:00:00 to: 2024-09-20 12:00:00)
Now you have # tasks in your list.
```

---

### 5. `mark <Task number>`
Marks the indicated task as completed.

**Parameters:**
- `<Task number>`: The number of the task in the list to be marked as completed.

**Example:** mark 1

**Expected Output**
```
Nice! I've marked this task as done:
[T][] <Task>
```

---

### 6. `unmark <Task number>`
Marks the indicated task as uncompleted.

**Parameters:**
- `<Task number>`: The number of the task in the list to be marked as uncompleted.

**Example:** unmark 1

**Expected Output**
```
OK, I've marked this task as not done yet:
[T][] <Task>
```

---

### 7. `delete <Task number>`
Deletes the indicated task from the task list.

**Parameters:**
- `<Task number>`: The number of the task in the list to be deleted.

**Example:** delete 2

**Expected Output**
```
Noted! I've removed this task :
[T][] <Task>
Now you have # tasks in your list.
```

---

### 8. `find <Task name>`
Searches the task list to find matching tasks.

**Parameters:**
- `<Task name>`: The name or part of the task name to search for.

**Example:** find submit

**Expected Output**
```
1. [D][] Submit assignment (by: Oct 01 2024 12:10:20pm)
```

---

### 9. `help`
Displays the help page

**Example:** help

**Expected Output**
```
Asura Help Page
-----------------------
Commands
list                                displays the items in the task list.
todo <Task name>                     Adds a new todo task into the task list.
deadline <Task name> /by <Date>      Adds a new deadline task into the task list.
event <Task name> /from <DateTime> /to <DateTime>      Adds a new event task into the task list.
mark <Task number>                  Marks the indicated task as completed.
unmark <Task number>                Marks the indicated task as uncompleted.
delete <Task number>                Deletes the indicated task from the task list.
find <Task name>                    Searches the task list to find matching tasks to the input.
-----------------------

```

---
