# Hannah Bot User Guide

![Hannah Bot](../src/main/resources/images/Chatbot.png)

## Introduction

Hannah Bot is a simple task management application that allows you to keep track of tasks including todos, deadlines, and events. It features a graphical user interface (GUI) for interacting with the bot, and it can handle various commands to manage and organize your tasks efficiently.

## Features

### Adding Todos
You can add a todo task to the list by using the `todo` command.

**Usage:**
```plaintext
todo <task description>
```

**Example:**
```plaintext
todo Buy groceries
```

**Expected Outcome:**  
The bot will confirm the addition of the new todo task to your list.
```plaintext
Added task!
[T][ ] Buy groceries
You now have 1 task in your list.
```

### Adding Deadlines
Add a task with a specific deadline using the `deadline` command.

**Usage:**
```plaintext
deadline <task description> /by <yyyy-MM-dd>
```

**Example:**
```plaintext
deadline Submit report /by 2024-12-01
```

**Expected Outcome:**  
The bot will add the deadline to your list and display the task.
```plaintext
Added task!
[D][ ] Submit report (by: Dec 1 2024)
You now have 2 tasks in your list.
```

### Adding Events
Add events with specific start and end times using the `event` command.

**Usage:**
```plaintext
event <task description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>
```

**Example:**
```plaintext
event Project meeting /from 2024-12-01 /to 2024-12-02
```

**Expected Outcome:**  
The bot will add the event to your list and display the task.
```plaintext
Added task!
[E][ ] Project meeting (from: Dec 1 2024 to: Dec 2 2024)
You now have 3 tasks in your list.
```

### Viewing All Tasks
View all tasks in the list using the `list` command.

**Usage:**
```plaintext
list
```

**Expected Outcome:**  
The bot will display all tasks currently in the list.
```plaintext
Tasks on your list!
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: Dec 1 2024)
3. [E][ ] Project meeting (from: Dec 1 2024 to: Dec 2 2024)
```

### Marking Tasks as Done
Mark a task as done using the `mark` command.

**Usage:**
```plaintext
mark <task number>
```

**Example:**
```plaintext
mark 1
```

**Expected Outcome:**  
The bot will mark the specified task as done.
```plaintext
Good job! Task Marked!
[T][X] Buy groceries
```

### Unmarking Tasks
Unmark a task as not done using the `unmark` command.

**Usage:**
```plaintext
unmark <task number>
```

**Example:**
```plaintext
unmark 1
```

**Expected Outcome:**  
The bot will unmark the specified task.
```plaintext
Okay, task unmarked!
[T][ ] Buy groceries
```

### Deleting Tasks
Delete a task from the list using the `delete` command.

**Usage:**
```plaintext
delete <task number>
```

**Example:**
```plaintext
delete 1
```

**Expected Outcome:**  
The bot will delete the specified task from the list.
```plaintext
Okay will remove the task:
[T][ ] Buy groceries
You now have 2 tasks in your list.
```

### Finding Tasks
Search for tasks containing a keyword using the `find` command.

**Usage:**
```plaintext
find <keyword>
```

**Example:**
```plaintext
find report
```

**Expected Outcome:**  
The bot will display all tasks that match the keyword.
```plaintext
Here are the matching tasks in your list:
1. [D][ ] Submit report (by: Dec 1 2024)
```

### Exiting the Application
Exit the bot using the `bye` command.

**Usage:**
```plaintext
bye
```

**Expected Outcome:**  
The bot will display a goodbye message and exit.
```plaintext
Bye. Hope to see you again soon!
``` 

## Tips for Better Use
- Use clear and descriptive task descriptions to make it easier to manage your tasks.
- Check the format and date formats (`yyyy-MM-dd`) when adding deadlines or events.
- Use the `list` command frequently to keep track of your tasks.

Hannah Bot provides an intuitive and efficient way to manage your tasks, helping you stay organized and on top of your to-dos. Enjoy using Hannah Bot!