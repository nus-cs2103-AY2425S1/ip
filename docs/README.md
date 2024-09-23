# EASTON User Guide

### Easton is a Chatbot that helps you manage your tasks such as todos, deadlines and events.

![Screenshot of User Interface](Ui.png)

> “Create a short list of things you need to 
> get done today.”- Matt D'Avella 
> ([My Simple Productivity System](https://www.youtube.com/watch?v=BtiQvhQF8IA))

# List of Features

* List
* Add Todo
* Add Deadline
* Add Event
* Mark Task
* Unmark Task
* Delete Task
* Find Task
* Archive Task

---

## List : `list`

Shows a task list that the user has created.

Format: `list`

Example Outcome: List of tasks is displayed.
```
Here are the tasks in your list:
1.[T][X] cs2103 quiz
2.[D][ ] cs2103 assignment (by: Sept 20 2024 23:59)
3.[E][ ] cs2103 project (from: Sept 16 2024 0:00 to: Sept 20 2024 23:59)
```

---

## Add Todo : `todo`

Adds a todo task to the task list.

Format: `todo <description>`

Examples: `todo cs2103 quiz`

Example Outcome: Confirmation message that the todo has been created.
```
Got it. I've added this task:
[T][ ] cs2103 quiz
Now you have 1 tasks in the list.
```

---

## Add Deadline : `deadline`

Adds a deadline task to the task list.

Format: `deadline <description> /by <due_date>`

Examples: `deadline cs2103 assignment /by 20/09/2024 23:59`

Example Outcome: Confirmation message that the deadline has been created.
```
Got it. I've added this task:
[D][ ] cs2103 assignment (by: Sept 20 2024 23:59)
Now you have 2 tasks in the list.
```
---

## Add Event : `event`

Adds a deadline task to the task list.

Format: `event <description> /from <start_date> /to <end_date>`

Examples: `event cs2103 project /from 16/09/2024 00:00 /to 20/09/2024 23:59`

Example Outcome: Confirmation message that the event has been created.
```
Got it. I've added this task:
[E][ ] cs2103 project (from: Sept 16 2024 0:00 to: Sept 20 2024 23:59)
Now you have 3 tasks in the list.
```
---

## Mark Task : `mark`

Mark a given task as complete.

Format: `mark <index>`

Examples: `mark 1`

Example Outcome: Confirmation message that the task has been marked.
```
Nice! I've marked this task as done:
[T][X] cs2103 quiz
```
---

## Unmark Task : `unmark`

Unmark a given task as incomplete.

Format: `unmark <index>`

Examples: `unmark 1`

Example Outcome: Confirmation message that the task has been unmarked.
```
OK, I've marked this task as not done yet:
[T][ ] cs2103 quiz
```
---

## Delete Task : `delete`

Delete a task from the task list.

Format: `delete <index>`

Examples: `delete 2`

Example Outcome: Confirmation message that the task has been deleted.
```
Noted. I've removed this task:
[D][ ] cs2103 assignment (by: Sept 20 2024 23:59)
Now you have 2 tasks in the list.
```
---

## Find Task : `find`

Find a task from the task list by specific keywords.

Format: `find <keywords...>`

Examples: `find cs2103`

Example Outcome: List of tasks that matches the keywords.
```
Here are the matching tasks in your list:
1.[T][ ] cs2103 quiz
2.[E][ ] cs2103 project (from: Sept 16 2024 0:00 to: Sept 20 2024 23:59)
```
---

## Archive Task : `archive`

Archive task(s) from the task list into a new file.

Format: `archive <all | index>`

Examples: `archive all`

Example Outcome: List of tasks that have been archived.
```
The following task(s) were archived:
1.[T][ ] cs2103 quiz
2.[E][ ] cs2103 project (from: Sept 16 2024 0:00 to: Sept 20 2024 23:59)
```
--- 

## Exit : `bye`

Exits the program.

Format: `bye`
