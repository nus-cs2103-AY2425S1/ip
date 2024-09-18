# Bro User Guide

![Ui.png](Ui.png)

# Task Management Chatbot

Bro is a simple task management chatbot to help you manage your tasks efficiently. You can create, view, mark, and delete different types of tasks such as to-dos, deadlines, and events. The bot also saves your tasks to a file so you don’t lose your progress.

---

## Features

### 1. Add a To-Do Task

**Example:**

```
  todo Do Project
  ```

**Output**:
  ```
  Got it. I've added this task:
  [T][ ] Do Project
  Now you have 1 tasks in the list.
  ```

### 2. Add a Deadline Task

**Example:**

```
  deadline Submit Assignment /by 2024-10-01 2359
  ```

**Output**:
  ```
  Got it. I've added this task:
  [D][ ] Submit Assignment (by: 01 Oct 2024 2359)
  Now you have 1 tasks in the list.
  ```

### 3. Add a Event Task

**Example:**

```
  event Rehearsal /from 2024-10-01 0000 /to 2024-10-10 2300 
  ```

**Output**:
  ```
  Got it. I've added this task:
  [E][ ] Rehearsal (from: 01 Oct 2024 2359 to: 10 Oct 2024 2300)
  Now you have 1 tasks in the list.
  ```

Example: `list`

// A description of the expected outcome goes here

```
Here are the tasks in your list:
  1.[T][ ] Do Project
  2.[D][ ] Submit Assignment (by: 01 Oct 2024 2359)
  3.[E][ ] Rehearsal (from: 01 Oct 2024 2359 to: 10 Oct 2024 2300)
```

## Marking and Unmarking a Task

### Mark Task:

**Command**: `mark [index]`

**Example**:
  ```
  mark 1
  ```

**Output**:
  ```
  Nice! I've marked this task as done:
    [T][X] Do Project
  ```

### Unmark Task:

**Command**: `unmark [task index]`

**Example**:
  ```
  unmark 1
  ```

**Output**:
  ```
  OK, I've marked this task as not done yet:
  [T][ ] Do Project
  ```



## Deleting a Task

Delete a specific task from your list.

**Command**: `delete [task index]`

**Example**:
  ```
  delete 1
  ```

**Output**:
  ```
  Noted. I've removed this task:
  [T][ ] Do Project
  Now you have 2 tasks in the list.
  ```
## Finding Tasks

Find tasks by keywords using the `find` command.

**Command**: `find [keyword]`

**Example**:
  ```
  find Project
  ```

**Output**:
  ```
  Here are the matching tasks in your list:
  1.[T][ ]  Do Project
  ```

## Duplicate Task Detection

If you try to add a Task that already exists, the chatbot will notify you:
  ```
  Task added seems to already exist. Do you want to continue? [yes/no]
  ```

If you type `yes`, the task will remain as a duplicate task in the list.

If you type `no`, the duplicate task will be deleted.