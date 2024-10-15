# Chat Kaki User Guide

![Chat Kaki](Ui.png)

Welcome to Chat Kaki, your friendly task management chatbot! Chat Kaki helps you keep track of your tasks through a 
simple and intuitive conversational interface

## Features
- Adding Deadlines
- Adding Events
- Adding ToDos
- Listing Tasks
- Marking Tasks as Done
- Unmarking Tasks
- Deleting Tasks
- Finding Tasks
- Exiting the Application

## Adding deadlines

Add a task with a deadline to your task list.

### Usage

```
deadline TASK /by DATE TIME
```
### Example

```
deadline Submit assignment /by 3/6/2002 0000

```

### Expected Outcome

```
Got it. I've added this task:
  [D][ ] Submit assignment (by: 3/6/2002 0000)
Now you have X tasks in the list.
```
---

## Adding Events

Add an event scheduled at a specific date and time.

### Usage

```
event TASK /from DATE TIME /to DATE TIME
```

### Example

```
event project meeting /from 1/12/2021 0000 /to 1/12/2022 0000
```

### Expected Outcome

```
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 1 2021 12:00 AM to: Dec 1 2022 12:00 AM)
Now you have X tasks in the list.
```
---

## Adding ToDos

Add a task without a deadline to your task list.

### Usage

```
todo TASK
```

### Example

```
todo Read book
```

### Expected Outcome

```
Got it. I've added this task:
  [T][ ] Read book
Now you have X tasks in the list.
```

---

## Listing Tasks

List all tasks in your task list.

### Usage

```
list
```

### Expected Outcome

```
Here are the tasks in your list:
1. [D][ ] Submit assignment (by: Oct 15 2024 11:59 PM)
2. [E][ ] project meeting (from: Dec 1 2021 12:00 AM to: Dec 1 2022 12:00 AM)
3. [T][ ] Read book
```

---

## Marking Tasks as Done

Mark a task as done.

### Usage

```
mark INDEX
```

### Example

```
mark 1
```

### Expected Outcome

```
Nice! I've marked this task as done:
  [D][X] Submit assignment (by: May 12 2024 11:59 PM)
```

---

## Unmarking Tasks

Unmark a task as done.

### Usage

```
unmark INDEX
```

### Example

```
unmark 1
```

### Expected Outcome

```
Nice! I've unmarked this task:
  [D][ ] Submit assignment (by: May 12 2024 11:59 PM)
```

---

## Deleting Tasks

Delete a task from your task list.

### Usage

```

delete INDEX
```

### Example

```
delete 1
```

### Expected Outcome

```
Noted. I've removed this task:
  [D][X] Submit assignment (by: May 12 2024 11:59 PM)
Now you have X tasks in the list.
```

---

## Finding Tasks

Find tasks that contain a specific keyword.

### Usage

```
find KEYWORD [-full] [-desc] [-help]
```

### Options

- `-full`: Find tasks that contain the keyword in their full description.
- `-desc`: Find tasks that contain the keyword in their description.
- `-help`: Display help message for the find command.

### Example

```
find book
```

### Expected Outcome

```
Here are the matching tasks in your list:
1. [T][ ] Read book
```

---

## Exiting the Application

Exit the application.

### Usage

```
bye
```

### Expected Outcome

```
Bye. Hope to see you again soon!
```

---



