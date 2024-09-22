# Grok User Guide

Welcome to the user guide for the Grok chatbot!

This chatbot can help you to grok productivity 
(that is, intuitively grasp productivity) by managing your todos, deadlines,
and events all in the same place.

![A screenshot of the Grok chatbot in use.](Ui.png)

These are the features of the Grok chatbot. If a command is required to use
the feature, it will be stated in the respective feature description.

### Features
- [Ease of use in setup, entry, and exit](#1-ease-of-setup-entry-and-exit)
- [Adding of several types of items (or "tasks") into a list of tasks:](#2-list-of-tasks)
  - [Todo](#2a-adding-a-todo)
  - [Deadline](#2b-adding-a-deadline)
  - [Event](#2c-adding-an-event)
- [Persistent text storage](#3-persistent-text-storage)
- [Deletion of tasks](#4-deletion-of-tasks)
- [Marking or unmarking of specific tasks as done or not done](#5-markingunmarking-of-tasks)
- [Searching for specific task(s)](#6-finding-of-tasks)

## 1: Ease of setup, entry, and exit

Grok is easy to start using as all of the required components are in a single `.jar` file.
1. Find the latest `.jar` file as a [release](https://github.com/josh1248/ip/releases).
2. Download that file in a directory, preferably empty.
3. Recommended Java version: Azul 17.0.12
4. Run the `.jar` file using JDK. You may manually run it in the command line by being in the 
same folder as your `.jar` file and running:
```
java -jar grok.jar
```
5. You are done!
6. To exit, type `bye`, or click the "X" button of the CLI.

```
expected output
```

## 2: List of tasks
The chatbot keeps track of a list of tasks provided. To see this list, use the following command format:
```
list
```

You should expect a neatly arranged and enumerated list of tasks.

## 2A: Adding a Todo

A Todo is a task which can be added using the following command format:

`todo <description name>`

Example: `todo buy groceries`

- Take note that the description should not be empty (so `todo ` only will not be permitted).
- Additionally, no tasks are allowed to have the same description.

The command will append the todo created to the end of the list of tasks, and a success
message will be provided (with X denoting how many total tasks are present):

```
Got it. I've added this task:
[T] [] Buy groceries
Now, you have X tasks in the list.
```

## 2B: Adding a Deadline

A Deadline is a task with a deadline which can be added using the following command format:

`deadline <description name> /by <date, in yyyy-mm-dd format>`

Example: `deadline cs2103t ip /by 2024-09-20`

- Take note that the description should not be empty (so `deadline /by 2024-09-20` only will not be permitted).
- Additionally, if the date provided is invalid, an error message will be provided to inform you.
- Additionally, no tasks are allowed to have the same description.

The command will append the deadline created to the end of the list of tasks, and a success
message will be provided (with X denoting how many total tasks are present):

```
Got it. I've added this task:
[D] [] cs2103t ip (by: 20 Sep 2024)
Now, you have X tasks in the list.
```
## 2C: Adding an Event

An Event is a task with a start and end date which can be added using the following command format:

`event <description name> /from <start date> /to <end date>`

Example: `event holidays /from 2024-09-21 /to 2024-09-30`

- Take note that the description should not be empty.
- Additionally, if the start or end date provided is invalid, an error message will be provided to inform you.
- Additionally, no tasks are allowed to have the same description.

The command will append the event created to the end of the list of tasks, and a success
message will be provided (with X denoting how many total tasks are present):

```
Got it. I've added this task:
[E] [] holidays (from: 2024-09-21 to: 2024-09-30)
Now, you have X tasks in the list.
```

## 3: Persistent text storage

Your list of tasks will be persistently stored as a text file under the `data` folder, 
which will be created under the same directory as your `.jar` file.


## 4: Deletion of tasks

You may delete any task by referring to its index (starting from 1) for deletion:

```
delete <task index to remove>
```

Example: `delete 1` removes the first task from the list.

The index provided should be within the bounds of the list, or else an error message will be provided.

If deletion is successful, a success message will be provided 
(with X denoting how many total tasks are present):

```
Got it. I've deleted this task:
(Task description)
Now you have X tasks in the list.
```

## 5: Marking/Unmarking of tasks

You may mark a task as done by referring to its index (starting from 1) for marking:
```
mark <task index to mark>
```

You may also unmark a task by referring to its index:
```
unmark <task index to unmark>
```

The index provided should be within the bounds of the list, or else an error message will be provided.

Example: 
- `mark 1` marks the first task from the list as done.
- `mark 3` unmarks the third task from the list.

The index provided should be within the bounds of the list, or else an error message will be provided.

If marking / unmarking is successful, a success message will be provided.

## 6: Finding of tasks

You may search for task(s) that contain some search query using `find`:

```
find <search string>
```

Example: `find ip`

- If the search string is empty, an error message will be provided to inform you.

If successful, the tasks that contain the search query will be returned in a list as so:

```
Here are the matching tasks for 'ip' in your list:
...
```