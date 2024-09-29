# Streams User Guide

Streams is a chatbot-style task management application that helps you keep track of your todos, deadlines, and events.

![Screenshot of User Interface](Ui.png)


## Feature List

### 1. Viewing list of valid commands 

This action helps the user by providing a list of valid commands with their formats.

**Command:**

```java
/help
```

**Expected Output:**

```
Below is a list of all possible commands and their formarts: 
list
done [task number]
unmark [task number]
delete [task number]
todo [task description]
deadline [task description] /by [YYYY-MM-dd HH:mm] 
event [task description] /from [YYYY-MM-dd HH:mm] /to [YYYY-MM-dd HH:mm]
find [keyword]
sort-deadline
list-date [YYYY-MM-dd]
list-week 
tag [task number] [tag description] 
list-tag [keyword] 
tag-remove [task number] [description]
bye 
```
### 2. Adding Todos

This action adds a TODO to your task list.

**Command:**

```java
todo <task-description> 
```

**Example:**

```java
todo read book
```

**Expected Output:**

```
Got it. I've added this task: 
[T][X] read book []
Now you have 1 task in the list.
```

### 3. Adding Deadlines

This action adds a deadline to your task list with a specified due date.

**Command:**

```java
deadline <task-description> /by <due-date: YYYY-MM-DD HH:MM>
```

**Example:**

```java
deadline finish iP /by 2024-09-30 16:00
```

**Expected Output:**

```
Got it. I've added this task: 
[D][X] finish iP [] (by:Sep 30 2024,16:00)
Now you have 2 tasks in the list.
```

### 4. Adding Events

This action adds an event to your task list with a specified duration.

**Command:**

```java
event <task-description> /from <YYYY-MM-DD HH:MM HH:MM> /to <YYYY-MM-DD HH:MM HH:MM>
```

**Example:**

```java
event PGPRuns /from 2024-09-30 16:00 /to 2024-09-30 18:00
```

**Expected Output:**

```
Got it. I've added this task: 
[E][X] PGPRuns [] (from:Sep 30 2024,16:00 to:Sep 30 2024,18:00)
Now you have 3 tasks in the list.
```

### 5. Marking a Task as Complete

This feature marks a task as done by its index number.

**Command:**

```java
done <task_index>
```

**Example:**

```java
done 2
```

**Expected Output:**

```
marked task done:[D][✓] finish iP [] (by:Sep 30 2024,16:00)
```

### 6. Unmarking a Task

This action marks a previously completed task as not done.

**Command:**

```java
undone <task_index>
```

**Example:**

```java
undone 2
```

**Expected Output:**

```
marked task as not done:[D][X] finish iP [] (by:Sep 30 2024,16:00)
```

### 7. Listing All Tasks

This feature displays all tasks in the list, including their status.

**Command:**

```java
list
```

**Expected Output:**

```
1.[T][X] read book []
2.[D][X] finish iP [] (by:Sep 30 2024,16:00)
3.[E][X] PGPRuns [] (from:Sep 30 2024,16:00 to:Sep 30 2024,18:00)
```

### 8. Deleting a Task

This action removes a task from the list.

**Command:**

```java
delete <task_index>
```

**Example:**

```java
delete 2
```

**Expected Output:**

```
okkieee..i've removed this task:[D][X] finish iP (by:Sep 30 2024,16:00)
yayyayayy!!!!now you have 2 tasks in the list
```

### 9. Finding Tasks by Keyword

You can search for tasks using a specific keyword.

**Command:**

```java
find <keyword>
```

**Example:**

```java
find read
```

**Expected Output:**

```
Here are the matching tasks in your list:
1.[T][X] read book []
```

### 10.Tagging a task

This command helps you tag a particular task using task index.

**Command:**

```java
tag <task-index> <tag-description> 
```

**Example:**

```java
tag 1 important 
```

**Expected Output:**

```
Added tag 'important' to task:[T][X] read book[important]
```
### 11.Finding tasks with the same keyword in the tag

This command lists all tasks with tags containing a particular keyword

**Command:**

```java
list-tag <keyword>
```

**Example:**

```java
list-tag important
```

**Expected Output:**

```
Tasks with tag 'important':
1.[T][X] read book[important]
2.[D][X] submit ICA [important] (by:Sep 30 2024,16:00)
```
### 12.Removing a particular tag from a particular task 

This command removes the given tag from the given task based on task index.

**Command:**

```java
tag-remove <task-index> <tag-description>
```

**Example:**

```java
tag-remove 1 important
```

**Expected Output:**

```
Removed tag 'important'from task:[T][X] read book[]
```

### 13.Sorting tasks by deadline

This command helps in sorting the deadline tasks based on their due dates.

**Command:**

```java
sort-deadline
```

**Expected Output:**

```
Deadline tasks sorted by due date:
1.[D][X] submit ICA [important] (by:Sep 30 2024,16:00)
2.[D][X] lab work [] (by:Oct 01 2024,12:00)
```

### 14.Listing all tasks due on a particular day

This command lists all the tasks that are due on a particular date.

**Command:**

```java
list-date <date: YYYY-MM-DD>
```

**Example:**

```java
list-date 2024-09-30
```

**Expected Output:**
```
tasks on Sep 30 2024:
1.[E][X] PGPRuns [] (from:Sep 30 2024,16:00 to:Sep 30 2024,18:00)
2.[D][X] submit ICA [important] (by:Sep 30 2024,16:00)
```

### 15.Listing all tasks due on that particular week

This command lists all tasks due on that particular week.

**Command:**

```java
list-week
```

**Expected Output:**
```
tasks in the upcoming week:
1.[E][X] PGPRuns [] (from:Sep 30 2024,16:00 to:Sep 30 2024,18:00)
2.[D][X] submit ICA [important] (by:Sep 30 2024,16:00)
3.[D][X] lab work [] (by:Oct 01 2024,12:00)
```

### 16.Exiting the Application

This command closes the Streams application.

**Command:**

```java
bye
```

**Expected Output:**

```
Bye.Hope to see you soon!
Exiting Streams.Goodbye!
```


### Saving Data Automatically

Streams automatically saves your task list to a file after every command, 
ensuring that no data is lost when you exit the application.