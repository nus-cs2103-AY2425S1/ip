# Carly User Guide

A sample of the chatbot window:
<p align="center">
  <img src="Ui.png" alt="Carly UI" width="450"/>
</p>

## Carly: Your Personal Task Management Chatbot 💬
Carly is an intuitive chatbot designed to help you manage your tasks effortlessly. With simple command-based interactions, you can easily create, mark, unmark, delete, and find tasks—all in one place. Whether you’re planning a busy day or keeping track of deadlines, Carly is here to streamline your productivity. Experience efficient task management like never before with Carly!

---
## Table of Contents
1. [Features](#features)
2. [Help](#help)
    - [Adding To-Dos](#adding-to-dos)
    - [Adding Deadlines](#adding-deadlines)
    - [Adding Events](#adding-events)
    - [Finding Tasks](#finding-tasks)
    - [Marking Tasks](#marking-tasks)
    - [Unmarking Tasks](#unmarking-tasks)
    - [Sorting Tasks](#sorting-tasks)
    - [Listing Tasks](#listing-tasks)
    - [Exiting Carly](#exiting-carly)

---
## Features
- **Adding Tasks**
    - Use `todo TASK_DESCRIPTION` for simple tasks.
    - Use `deadline TASK_DESCRIPTION /by YYYY-MM-DD` for tasks with deadlines.
    - Use `event TASK_DESCRIPTION /from START_TIME /to END_TIME` for events.

- **Finding Tasks**
    - Use `find KEYWORD` to search for specific tasks.

- **Marking Tasks**
    - Use `mark INDEX` to mark a task as done.

- **Unmarking Tasks**
    - Use `unmark INDEX` to revert a task to not done.

- **Sorting Tasks**
    - Use `sort` to organize tasks by their deadlines.

- **Listing Tasks**
    - Use `list` to view all current tasks.

- **Exiting**
    - Use `bye` to end your session.



---
## Help
### Adding To-Dos
Create simple tasks that don’t require a deadline.

#### Usage
To add a to-do, use the following command:
```
todo cook pasta
```

output:
```
Awesome! I've added this task:
    [T][ ] cook pasta
You've got 1 task on your plate!
```   

<br><br>
### Adding deadlines
Set deadlines for your tasks to ensure you stay on track.
Ensure that the date is in **YYYY-MM-DD** format.

#### Usage
To add a deadline, use the following command:  
```
deadline submit assignment1 /by 2024-12-10
```

Output:
```
Awesome! I've added this task:
    [D][ ] submit assignment 1 (by: Dec 10 2024)
You've got 1 task on your plate!
```

<br><br>
### Adding events
Set events for your tasks with start and end datetime.
```
event project meeting /from tuesday 2pm /to 4pm
```
Output:
```
Awesome! I've added this task:
    [E][ ] project meeting (from tuesday 2pm to: 4pm)
You've got 3 task on your plate!
```


<br><br>
### Finding Tasks
Finding specific tasks based on an entry

#### Usage
To find tasks, use the following command:
```
find meeting
```
Output:
```
"Here's the tasks that matches "meeting":
    [E][ ] project meeting (from tuesday 2pm to: 4pm)

You've got 1 task on your plate!
```


<br><br>
### Marking tasks
Mark a task as done.

#### Usage
```
mark 1
```
Output:
```
Nice! I've marked this task as done:
    [X][ ] submit assignment 1 (by: Dec 10 2024)
You've got 4 tasks on your plate!
```

<br><br>
### Unmarking tasks
Unmark a task to indicate it's not done.

#### Usage
```
unmark 1
```
Output:
```
OK! I've unmarked this task:
    [D][ ] submit assignment 1 (by: Dec 10 2024)
You've got 5 tasks on your plate!
```

<br><br>
### Sorting Tasks
Organize your tasks by deadlines.

#### Usage
To sort tasks, use the following command:
```
sort
```
Output:
```
"Here's your sorted list, with tasks sorted by the earliest deadline first: 
    [D][ ] study (by: Dec 12 1987)
    [D][ ] pray (by: Nov 02 2023)
    [D][ ] submit assignment 1 (by: Dec 10 2024)
    [T][ ] cook pasta
    [E][ ] project meeting (from tuesday 2pm to: 4pm)

You've got 5 task on your plate!
```

<br><br>
### Listing Tasks
View all your current tasks.

#### Usage
To list all tasks, use the following command:
```
list
```

Output:
```
Here are the tasks in your list:
1.[D][ ] Submit assignment 1 (by: Dec 10 2024)
2.[T][ ] cook pasta
3.project meeting (from tuesday 2pm to: 4pm)
4.study (by: Dec 12 1987)
5.pray (by: Nov 02 2023)

You've got 2 tasks to tackle!
```


<br><br>
### Exiting
End your session, and exit the window when you're done.

#### Usage
To say goodbye, use the following command:
```declarative
bye
```


