# Toothless User Guide

Welcome to Toothless, your friendly dragon companion for managing your tasks, deadlines and events! This user guide will help you get started with Toothless and its features.

![Toothless sample display](./Ui.png)

# Features
## Category A: Adding Tasks

### ToDo Task :`todo`
- **Description**: Adds a ToDo task to the task list.
- **Format**: `todo <description>`

Adds a ToDo task with the specified description to the task list.
A message indicating the task has been added will be displayed.

Example: `todo train Toothless`

```
Your task
    [T][ ] train toothless
added to the quest board!

Now there is 1 quest in your quest board. 
```

### Deadline Task :`deadline`
- **Description**: Adds a Deadline task to the task list.
- **Format**: `deadline <description> /by <date time>`

Adds a Deadline task with the specified description and date (dd MMM YYYY HH:mm) to the task list.
A message indicating the task has been added will be displayed.

Example: `deadline feed Toothless /by 12-12-2024 2359`

```
Your task
    [D][ ] feed toothless (by: 12 Dec 2024 23:59)
added to the quest board!

Now there is 1 quest in your quest board!
```

### Event Task :`event`
- **Description**: Adds an Event task to the task list.
- **Format**: `event <description> /from <date time> /to <date time>`

Adds an Event task with the specified description and timeline (dd MMM YYYY HH:mm) to the task list.
A message indicating the task has been added will be displayed.

Example: `event Toothless birthday /from 12-12-2024 0000 /to 12-12-2024 2359`

```
Your task
    [E][ ] toothless birthday (from: 12 Dec 2024 00:00 to: 12 Dec 2024 23:59)
added to the quest board!

Now there is 1 quest in your quest board!
```

## Category B: Managing Tasks

### List all tasks :`list`
- **Description**: Lists all tasks in the task list.
- **Format**: `list`

Displays all tasks in the task list.
Each task will be displayed with its task type, completion status, description and timeline (if applicable).

Example: `list`

```
 Here are the tasks on the quest board:

 |-------------Quest Board -----------------|
    1. [T][ ] train toothless
    2. [D][ ] feed toothless (by: 12 Dec 2024 23:59)
    3. [E][ ] toothless birthday (from: 12 Dec 2024 00:00 to: 12 Dec 2024 23:59)
 |------------------------------------------|
```

### Mark task as done :`mark`
- **Description**: Marks a task as done.
- **Format**: `mark <index>`

Marks the task at the specified index as done.
A message indicating the task has been marked as done will be displayed.

Example: `mark 1`

```
Good job! You have completed this quest!
[T][X] train toothless
```

### Mark task as undone :`unmark`
- **Description**: Marks a task as undone.
- **Format**: `unmark <index>`

Marks the task at the specified index as undone.
A message indicating the task has been marked as undone will be displayed.

Example: `unmark 1`

```
Oops!Quest is back in play!
[T][ ] train toothless
```

### Delete task :`delete`
- **Description**: Deletes a task from the task list.
- **Format**: `delete <index>`

Deletes the task at the specified index from the task list.
A message indicating the task has been deleted will be displayed.

Example: `delete 1`

```
The quest
    [T][ ] train toothless
is removed from the quest board.

Now there is 2 quests in your quest board.
```

### Find task :`find`
- **Description**: Finds tasks with the specified keyword.
- **Format**: `find <keyword>`

Finds tasks with the specified keyword in the task list.
Displays all tasks that contain the keyword.

Example: `find toothless`

```
Here are the quests that match your keyword:

1. [T][ ] train toothless
2. [D][ ] feed toothless (by: 12 Dec 2024 23:59)
3. [E][ ] toothless birthday (from: 12 Dec 2024 00:00 to: 12 Dec 2024 23:59)
```

## Category C: Other Commands
### Help :`help`
- **Description**: Displays all available commands.
- **Format**: `help`

Displays all available commands and their formats.

Example: `help`

```
Here are the list of commands you can use:

Category A: Adding tasks
1. Todo task: todo <description>
2. Deadline task: deadline <description> /by <deadline>
3. Event Task: event <description> /from <start time> /to <end time>

Category B: Managing tasks
4. List all tasks: list
5. Mark a task as done: mark <index>
6. Marks a task as undone: unmark <index>
7. Delete a task based on index: delete <index>
8. Find the task with the keyword: find <keyword>

Category C: Other commands
9. Shows the list of commands: help
10. Exit the program: bye
```
### Greetings : `hi`
- **Description**: Greets the user.
- **Format**: `hi`

Greets the user with a message from Toothless.

Example: `hi`

```
Hello! I'm Toothless\nHow can I help today dragon rider?
```

### Exit :`bye`
- **Description**: Exits the program.
- **Format`: `bye`

Exits the program and saves the task list to the hard disk.
A message indicating the program has been exited will be displayed.
The text field and send button will be disabled.

Example: `bye`

```
Until next time, dragon rider!
Toothless the Night Fury, signing off.
See you soon!

(The input is disabled, restart to chat with Toothless again!)
```

# Acknowledgements
- The dragon and user icon is used from the video "The Ultimate How to Train Your Dragon Recap Cartoon" by Cas van de Pol. (https://youtu.be/4dDUWRWqcIo?si=2XFx8QF6qbVMHcVk) 
- The background is used from Pinterest (https://www.pinterest.com/pin/434315957799147671/)
- AI tools like ChatGPT by OpenAI, Claude by Anthropic AI and Copilot by Github are used to aid in developing the project. Can refer to [this file](/Users/joannehing/Documents/CS2103T/Individual Project/AI.md) for more information.
