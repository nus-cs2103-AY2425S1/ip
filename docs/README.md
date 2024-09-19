# Sage User Guide

Welcome to Sage, a task management application that helps you track your 
tasks, deadlines, and events. With Sage, you can manage your to-do list 
through a simple chatbot interface, making it easy to organise and 
track your tasks.

**Key Features**
+ Add Tasks: Add ToDo tasks, Deadlines, and Events.
+ List Tasks: View all your tasks in a list format.
+ Mark/Unmark Tasks: Mark tasks as done or not done.
+ Delete Tasks: Remove tasks from your list.
+ Find Tasks: Search for tasks containing a specific keyword.
+ Save and Load Tasks: Automatically save your tasks to a file and load them when you restart Sage.
+ Exit: Save changes and exit the application.

### Adding todos

Command: `todo <task>`

Example:
```
todo Read book
```
Expected output:
```
Got it. I've added this task:
[T][ ] Read book
Now you have 1 task in the list.
```

### Adding Deadlines
Add a task with a deadline using the /by keyword.

Command: `deadline <task description> /by <deadline>`

Example:
```
deadline Submit assignment /by 2024-10-01 2359
```
Expected output:
```
Got it. I've added this task:
[D][ ] Submit assignment (by: Oct 1 2024 23:59)
Now you have 2 tasks in the list.
```

### Adding Events
Add a task with a start and end time using /from and /to.

Command: `event <task description> /from <start time> /to <end time>`

Example:
```
event Project meeting /from 2024-09-15 1400 /to 2024-09-15 1600
```
Expected output:
```
Got it. I've added this task:
[E][ ] Project meeting (from: Sep 15 2024 14:00 to: Sep 15 2024 16:00)
Now you have 3 tasks in the list.
```

### Listing All Tasks
View all tasks currently stored in the task list.

Command: `list`

Example:
```
list
```
Expected output:
```
Here are the tasks in your list:
1. [T][ ] Read book
2. [D][ ] Submit assignment (by: Oct 1 2024 23:59)
3. [E][ ] Project meeting (from: Sep 15 2024 14:00 to: Sep 15 2024 16:00)
```

### Marking Tasks as Done or Not Done
Mark a specific task as done.

Command: `mark <task number>`

Example:
```
mark 1
```
Expected output:
```
Nice! I've marked this task as done:
[T][X] Read book
```

### Unmark Task
Unmark a specific task, marking it as not done.

Command: `unmark <task number>`

Example:
```
unmark 1
```
Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] Read book
```

### Deleting Tasks
Remove a task from your task list.

Command: `delete <task number>`

Example:
```
delete 2
```
Expected output:
```
Noted. I've removed this task:
[D][ ] Submit assignment (by: Oct 1 2024 23:59)
Now you have 2 tasks in the list.
```

### Finding Tasks
Find a task from your task list.

Command: `find <task>`

Example:
```
find assignment
```
Expected output:
```
Here are the matching tasks in your list:
[D][ ] Submit assignment (by: Oct 1 2024 23:59)
```

### Saving and Loading Tasks
Sage automatically saves your tasks to a file (data/sage.txt) when you exit, 
and loads them again the next time you start the application. This ensures 
that your task list is persistent across multiple sessions.

### Exit the Application
Save your current task list and exit Sage.

Command: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```