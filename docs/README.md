# Lama User Guide

![Lama Ui Display](Ui.png)

**Lama** is a *SMART* chatbot designed to help you efficiently manage your daily tasks. 

The GUI version of Lama simplifies task management by enabling you to interact with the bot using simple, intuitive commands.

---

## Adding TODO Task

Add a to-do task with no specific deadline using the `todo` command.

**Command**: `todo [Your TODO]`

- **Example**:
  ```
  todo Read Book
  ```

- **Expected Output**:
  ```
  Got it. I've added this task:
    [T][ ] Read Book
  Now you have 1 tasks in the list.
  ```

---

## Adding Deadline

Add a task with a specific deadline using the `deadline` command.

**Command**: `deadline [Your TODO] /by [date of deadline]`

- **Example**:
  ```
  deadline Problem Set 4 /by 2024-09-21
  ```

- **Expected Output**:
  ```
  Got it. I've added this task:
    [D][ ] Problem Set 4 (by: Sep 21 2024)
  Now you have 2 tasks in the list.
  ```

---

## Adding Event

Add an `event` that starts and ends at a specific time.

**Command**: `event [Your event] /from [start time] /to [end time]`

- **Example**:
  ```
  event Project Meeting /from 2024-09-18 2000 /to 2024-09-18 2200
  ```

- **Expected Output**:
  ```
  Got it. I've added this event:
    [E][ ] Project Meeting (from: Sep 18 2024 20:00 to: Sep 18 2024 22:00)
  Now you have 3 tasks in the list.
  ```

---

## List

Display the current list of tasks using the `list` command.

**Command**: `list`

- **Expected Output**:
  ```
  Here are the tasks in your list:
  1.[T][ ] Read Book
  2.[D][ ] Problem Set 4 (by: Sep 21 2024)
  3.[E][ ] Project Meeting (from: Sep 18 2024 20:00 to: Sep 18 2024 22:00)
  ```

---

## Marking and Unmarking Task

You can mark a task as completed or unmark it if needed.

### Mark Task:

- **Command**: `mark [task index]`

- **Example**:
  ```
  mark 1
  ```

- **Expected Output**:
  ```
  Nice! I've marked this task as done:
    [T][X] Read Book
  ```

### Unmark Task:

- **Command**: `unmark [task index]`

- **Example**:
  ```
  unmark 1
  ```

- **Expected Output**:
  ```
  OK, I've marked this task as not done yet:
    [T][ ] Read Book
  ```

---

## Deleting Task

Delete a specific task from your list.

**Command**: `delete [task index]`

- **Example**:
  ```
  delete 1
  ```

- **Expected Output**:
  ```
  Noted. I've removed this task:
    [T][ ] Read Book
  Now you have 2 tasks in the list.
  ```

---

## Finding Tasks

Find tasks by keywords using the `find` command.

**Command**: `find [keywords]`

- **Example**:
  ```
  find project
  ```

- **Expected Output**:
  ```
  Here are the matching tasks in your list:
  1.[E][ ] Project Meeting (from: Sep 18 2024 20:00 to: Sep 18 2024 22:00)
  ```

---

## Aliases
Create an alias for a frequently used command. *All commands have a default alias*.

### Create Alias:

- **Command**: `alias [alias] [command]`

- **Example**:
  ```
  alias ls list
  ```

- **Expected Output**:
  ```
  Alias set: ls -> list
  ```

### View Aliases:

- **Command**: `aliases`

- **Expected Output**:
  ```
  Here are the aliases in your list:
  a -> alias
  b -> bye
  d -> deadline
  e -> event
  f -> find
  l -> list
  m -> mark
  r -> remove
  t -> todo
  u -> unmark
  del -> delete
  ls -> list
  ```

### Remove Alias:

- **Command**: `remove [alias]`

- **Example**:
  ```
  remove ls
  ```

- **Expected Output**:
  ```
  Alias 'ls' has been deleted.
  ```

---

## Bye

Exit the application.

- **Command**: `bye`

- **Example**:
  ```
  bye
  ```

- **Expected Output**:
  ```
  Bye. Hope to see you again soon!
  ```

---
