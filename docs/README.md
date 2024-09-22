# Kayo User Guide

![image](https://github.com/user-attachments/assets/4eec7f28-1758-4f15-a0dd-755dd7f0fc55)


Kayo is a simple CLI-based task management chatbot. It helps users manage tasks, deadlines, and events with easy-to-use commands. You can add tasks, mark them as complete, find specific tasks, and more.

## Features

### Adding Deadlines

This action adds a deadline to your task list with a specified due date.

**Command:**

```java
deadline <task_name> /by <due_date>
```

**Example:**

```java
deadline finish_project /by 2024-09-30
```

**Expected Output:**

```
Got it. I've added this task: 
[D] [ ] finish_project (by Sep 30 2024)
Now you have 1 task in the list.
```

### Marking a Task as Complete

This feature marks a task as done by its index number.

**Command:**

```java
mark <task_index>
```

**Example:**

```java
mark 1
```

**Expected Output:**

```
Nice! I've marked this task as done: 
[D] [X] finish_project (by Sep 30 2024)
```

### Unmarking a Task

This action marks a previously completed task as not done.

**Command:**

```java
unmark <task_index>
```

**Example:**

```java
unmark 1
```

**Expected Output:**

```
OK, I've marked this task as not done yet: 
[D] [ ] finish_project (by Sep 30 2024)
```

### Listing All Tasks

This feature displays all tasks in the list, including their status.

**Command:**

```java
list
```

**Expected Output:**

```
Here are the tasks in your list:
1. [D] [ ] finish_project (by Sep 30 2024)
```

### Deleting a Task

This action removes a task from the list.

**Command:**

```java
delete <task_index>
```

**Example:**

```java
delete 1
```

**Expected Output:**

```
Noted. I've removed this task: 
[D] [ ] finish_project (by Sep 30 2024)
Now you have 0 tasks in the list.
```

### Finding Tasks by Keyword

You can search for tasks using a specific keyword.

**Command:**

```java
find <keyword>
```

**Example:**

```java
find project
```

**Expected Output:**

```
Here are the matching tasks in your list:
1. [D] [ ] finish_project (by Sep 30 2024)
```

### Exiting the Application

This command closes the Kayo application.

**Command:**

```java
bye
```

**Expected Output:**

```
Bye! Hope to see you again soon!
```

### Saving Data Automatically

Kayo automatically saves your task list to a file after every command, ensuring that no data is lost when you exit the application.
