
# Lexi User Guide

## Product Introduction

Welcome to **Lexi**, a personal task management chatbot! Lexi helps you keep track of your tasks, deadlines, and events with an easy-to-use command interface. Below are the available commands and their respective functionalities to help you organize your tasks effectively.

---

## Adding a Todo Task

To add a simple todo task to your list, use the following command:

**Command:**
```
todo <taskName>
```

**Example:**
```
todo Buy groceries
```

**Expected Output:**
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in the list.
```

---

## Adding a Deadline

To add a task with a deadline, use the following command:

**Command:**
```
deadline <task> /by <date>
```

**Date Format:**
```
DD/MM/YYYY HHmm (e.g., 13/01/2002 1700)
```

**Example:**
```
deadline Submit assignment /by 13/01/2024 2359
```

**Expected Output:**
```
Got it. I've added this task:
  [D][ ] Submit assignment (by: 13 Jan 2024 23:59)
Now you have 2 tasks in the list.
```

---

## Adding an Event

To add an event with a start and end time, use the following command:

**Command:**
```
event <task> /from <start date and time> /to <end date and time>
```

**Date Format:**
```
DD/MM/YYYY HHmm (e.g., 13/01/2002 1700)
```

**Example:**
```
event Team meeting /from 13/01/2024 1500 /to 13/01/2024 1700
```

**Expected Output:**
```
Got it. I've added this task:
  [E][ ] Team meeting (from: 13 Jan 2024 15:00 to: 13 Jan 2024 17:00)
Now you have 3 tasks in the list.
```

---

## Marking a Task as Done

To mark a task as completed, use the following command:

**Command:**
```
mark <taskNumber>
```

**Example:**
```
mark 1
```

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

---

## Marking a Task as Not Done

To mark a task as incomplete (not done), use the following command:

**Command:**
```
unmark <taskNumber>
```

**Example:**
```
unmark 1
```

**Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
```

---

## Deleting a Task

To delete a task from your list, use the following command:

**Command:**
```
delete <taskNumber>
```

**Example:**
```
delete 2
```

**Expected Output:**
```
Noted. I've removed this task:
  [D][ ] Submit assignment (by: 13 Jan 2024 23:59)
Now you have 2 tasks in the list.
```

---

## Listing All Tasks

To view all your current tasks, use the following command:

**Command:**
```
list
```

**Expected Output:**
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [E][ ] Team meeting (from: 13 Jan 2024 15:00 to: 13 Jan 2024 17:00)
```

---

## Finding Related Tasks

To search for tasks by name, use the following command. Lexi can match partial task names, but the search must contain full words:

**Command:**
```
find <taskName>
```

**Example:**
```
find groceries
```

**Expected Output:**
```
Here are the matching tasks in your list:
1. [T][ ] Buy groceries
```

---

## Updating a Task

To update an existing task, use the following command. You can update a task with a new todo, deadline, or event format:

**Command:**
```
update <taskNumber> <new task command>
```

**Example:**
```
update 1 todo Buy vegetables
```

**Expected Output:**
```
Got it. I've updated this task:
  [T][ ] Buy vegetables
```

You can also update a task to a deadline or event:

**Example (updating to a deadline):**
```
update 1 deadline Submit report /by 15/02/2024 1200
```

---

## Exiting the Application

Currently, the `BYE` command is obsolete and just merely says goodbye and nothing else.

---

This concludes the list of commands available in **Lexi**. Use these commands to manage your tasks effectively!
