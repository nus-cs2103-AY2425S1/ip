<h1 style="color: #db9570" id="nether-user-guide">Nether User Guide</h1>

___

<img src="Ui.png" width="300"/>

**Welcome to Nether!**

Meet your personal aide, Nether, a chatbot designed to make your life easier by keep track of all your tasks 
systematically. Whether you're juggling deadlines, attending events, or simply trying to keep your daily tasks in order, 
Nether is here to help.

With Nether, you can organize your tasks through simple chat commands. Nether offers a streamlined, conversational
experience that feels natural and straightforward.

## Table of Contents
- [Nether User Guide](#nether-user-guide)
- [Features](#features)
  - [Add a ToDo Task: `todo`](#add-a-todo-task-todo) 
  - [Add a Deadline Task: `deadline`](#add-a-deadline-task-deadline)
  - [Add an Event Task: `event`](#add-an-event-task-event)
  - [Mark a Task: `mark`, `unmark`](#mark-a-task-mark-unmark)
  - [List Out Tasks: `list`](#list-out-tasks-list)
  - [Find a Task: `find`](#find-a-task-find)
  - [Delete a Task: `delete`](#delete-a-task-delete)
  - [Tagging: `#`](#tagging-)
  - [Miscellaneous Commands: `nether`, `bye`](#miscellaneous-commands-nether-bye)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)
- [Acknowledgements](#acknowledgements)

<h1 style="color: #db9570" id="features">Features</h1>

_____
## Add a ToDo Task: `todo`

Adds a `todo`  task to the task list.

Format: `todo (description) [#tag]`

> [!NOTE]
> `#tag` is an optional part of the command. Learn more about how to add tags to your tasks [here](#tagging-)

Example input: 
`todo Read Book`

Expected output:
```
Got it. I've added this task:
  [T][ ] Read Book
```

## Add a Deadline Task: `deadline`

Adds a `deadline` task to the task list.

Format: `deadline (description) [#tag] /by (time)`

> [!IMPORTANT]
> The only acceptable time format is `yyyy-MM-dd HHmm`
> e.g. `2024-09-19-2359`

Example input: `deadline Submit CS2103T Assignment /by 2024-09-20 2359`

Expected output:
```
Got it. I've added this task:
  [D][ ] Submit CS2103T Assignment (by: Sep 20 2024, 11:59pm)
```

## Add an Event Task: `event`

Adds an `event` task to the task list.

Format: `event (description) [#tag] /from (time) /to (time)`

Example input: `event Festival /from 2024-09-01 0700 /to 2024-09-03 1900`

Expected output:
```
Got it. I've added this task:
  [E][ ] Festival (from: Sep 1 2024, 7:00am to: Sep 03 2024, 7:00pm)
```

## Mark a Task: `mark`, `unmark`

Mark your task as done or not done using `mark` and `unmark` respectively.

### Mark a task as done: `mark`
Format: `mark (task number)`

Example input: `mark 3`

Expected output: 
```
Well done! I've marked this task as done:
  [E][X] Festival (from: Sep 1 2024, 7:00am to: Sep 03 2024, 7:00pm)
```

### Mark a task as not done: `unmark`
Format `unmark (task number)`

Example input: `unmark 3`

Expected output: 
```
Understood, I've marked this task as not done:
  [E][ ] Festival (from: Sep 1 2024, 7:00am to: Sep 03 2024, 7:00pm)
```

## List Out Tasks: `list`
List out all the tasks you have in your task list.

Format: `list`

Example input: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][ ] Read Book
2. [D][ ] Submit CS2103T Assignment (by: Sep 20 2024, 11:59pm)
3. [E][ ] Festival (from: Sep 1 2024, 7:00am to: Sep 03 2024, 7:00pm)
```

## Find a Task: `find`
Find all tasks that contain the input search keyword (not case-sensitive).

Format: `find (keyword)`

Example input: `find book`

Expected output: 
```
Here are the tasks that match your search in your list:
1. [T][ ] Read Book
```

## Delete a Task: `delete`
Delete a task from your task list.

Format: `delete (task number)`

Example input: `delete 1`

Expected output:
```
Noted, I've removed this task from the list:
  [T][ ] Read Book
Now you have 2 tasks in the list.
```

## Tagging: `#`
Tag or find your tasks using `#`. 

> [!IMPORTANT]
> Tags may not contain any whitespace.

### Add tasks with a tag

Format: `(type) (description) [#tag] [time for deadline or event task]`

Example input: `deadline Do Laundry #Chores /by 2024-09-20 0600`

Expected output:
```
Got it. I've added this task:
  [D][ ] <Chores> Do Laundry (by: Sep 20 2024, 6:00am)
```

### Find tasks with a tag

List out all the tasks that contain the searched tag.

Format: `find (tag)`

Example input: `find #chores`

Expected output:
```
Here are the tasks that match your search in your list:
1. [D][ ] <Chores> Do Laundry (by: Sep 20 2024, 6:00am)
```

## Miscellaneous Commands: `nether`, `bye`

`nether` prompts nether to respond to you in a not so interesting way.

`bye` stops nether from running and closes the application after a short delay.

___
<h1 style="color: #db9570" id="known-issues">Known Issues</h1>
___

NONE (as of now)


<h1 style="color: #db9570" id="command-summary">Command Summary</h1>
___

- ToDo: `todo Read book`
- Deadline: `deadline Return book /by 2024-09-20 2359`
- Event: `event Book Festival /from 2024-09-01 0700 /to 2024-09-03 1200`
- Mark: `mark 1`, `unmark 2` 
- List: `list`
- Find: `find book`
- Delete: `delete 3`
- Tagging: `todo Read Book #for-fun`
- Misc: `nether`, `bye`

<h1 style="color: #db9570" id="acknowledgements">Acknowledgements</h1>

1. Used ChatGPT to help generate roughly half of the JavaDoc comments.
2. Used ChatGPT to give recommendations on how to refactor the code to be more OOP, using the existing code as a base.
3. Followed majority of the code for GUI implementation from the JavaFX guide provided.