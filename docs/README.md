# Nebula User Guide

Welcome to the Neubla user guide! Nebula is an intelligent task management chatbot that helps you organize your tasks efficiently. You can interact with Nebula using various commands to manage your to-dos, deadlines, and events.

Below is a screenshot of Nebula in action:

![Product Screenshot](./ui.png)  <!-- Replace with the actual screenshot path -->

## Commands Supported

Nebula supports the following commands to help you manage your tasks, deadlines, and events:

### 1. `list`
Lists all the tasks in your current task list.

**Example:**
`list`
``` 
Here are the tasks in your list:
1. [T][X] Finish homework
2. [D][ ] Submit project /by 25-09-2024 23:59
```

### 2. `bye`
Ends the conversation with Nebula and exits the program.

**Example:**
`bye`
``` 
Bye! Hope to see you again soon :)
```

### 3. `todo`
Adds a Todo task to your task list.

**Example:**
`todo Read a book`
``` 
Got it! I've added this task:
    [T][] Read a book
Now you have 3 tasks in the list.
```

### 4. `deadline`
Adds a Deadline task to your task list.

**Example:**
`deadline Submit report /by 2024-09-30 17:00`
``` 
Got it! I've added this task:
    [D][] Submit report (by: September 30, 2024 17:00)
Now you have 4 tasks in the list.
```

### 5. `event`
Adds an Event task to your task list.

**Example:**
`event Team meeting /from 2024-10-01 14:00 /to 2024-10-01 16:00`
``` 
Got it! I've added this task:
    [E][] Team meeting (from: October 1, 2024 14:00 to October 1, 2024 16:00)
Now you have 5 tasks in the list.
```

### 6. `mark`
Marks the specified task as completed

**Example:**
`mark 1`
``` 
Nice! This task has successfully been marked:
    [T][X] Finish homework
```

### 7. `unmark`
Marks the specified task as not completed

**Example:**
`unmark 1`
``` 
Nice! This task has successfully been unmarked:
    [T][] Finish homework
```

### 8. `delete`
Deletes the specified task from the task list

**Example:**
`delete 2`
``` 
Noted. I've removed this task:
    [D][ ] Submit report (by September 30, 2024 17:00)
Now you have 4 tasks in the list.
```

### 9. `find`
Finds tasks in task list that contain a specific keyword

**Example:**
`find book`
``` 
Here are the matching tasks in your list:
1. [T][] Read a book
```

Hope you have fun using Nebula :)