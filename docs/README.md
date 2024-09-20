# Terminator User Guide

![Product screenshot](Ui.png)

The Terminator Chatbot is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).  

## Viewing Help

To get started, type `help` or press the `...` button in the top right hand corner and click the 'Help' button.

Format: `help`

## Adding todo tasks

Adds a todo task to the task list.

Format: `todo <description>`

Example: `todo destroy aliens`

```
Mission parameters updated. Added new objective:
[T][ ] destroy aliens
```

## Adding deadlines

Adds a deadline task to the task list.

Format: `deadline <description> /by dd/MM/yyyy HHmm`

Example: `deadline destroy aliens /by 01/01/2000 0900`

```
Mission parameters updated. Added new objective:
[D][ ] destroy aliens by: Jan 1 2000 09:00
```

## Adding events

Adds a todo task to the task list.

Format: `event <description> /from dd/MM/yyyy HH:mm /to dd/MM/yyyy HH:mm`

Example: `event attend meeting /from 01/01/2024 0900 /to 01/01/2024 1300`

```
Mission parameters updated. Added new objective:
[E][ ] attend meeting from: Jan 1 2024 09:00 to: Jan 1 2024 13:00
```

## Marking tasks

Marks a task as complete. 

Format: `mark <index>`

Example: `mark 1`

```
Objective marked as completed. Awaiting next directive:
[E][X]  attend meeting from: Jan 1 2024 09:00 to: Jan 1 2024 13:00
```

## Unmarking tasks

Marks a task as incomplete.

Format: `unmark <index>`

Example: `unmark 1`

```
Objective reopened:
[E][ ]  attend meeting from: Jan 1 2024 09:00 to: Jan 1 2024 13:00
```

## Finding tasks

Finds a task with a matching description. 

Format: `find <description>`

Example: `find meeting`

```
Match found. 
1.[E][ ]  attend meeting from: Jan 1 2024 09:00 to: Jan 1 2024 13:00
```

## Deleting tasks

Deletes a task from the task list.

Format: `delete <index>`

Example: `delete 1`

```
Successfully wiped all traces of the task from the database. 
[E][ ]  attend meeting from: Jan 1 2024 09:00 to: Jan 1 2024 13:00
No mission objectives specified. 
```