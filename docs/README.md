# Orion User Guide

Orion is a desktop app for managing your tasks, deadlines and events. 
If you've ever found it difficult to keep up with all your daily tasks, hopefully
Orion is the app for you!

![Screenshot of Ui](Ui.png)

## Table of Contents

* [Quick start](#quick-start)
* Supported features and commands:
  * [Viewing help](#viewing-help): `help`
  * [Listing all tasks](#listing-all-tasks): `list`
  * [Adding a todo task](#adding-a-todo-task): `todo`
  * [Adding a deadline](#adding-a-deadline): `deadline`
  * [Adding an event](#adding-an-event): `event`
  * [Finding a task](#finding-a-task): `find`
  * [Marking a task as completed](#marking-a-task-as-completed): `mark`
  * [Marking a task as uncompleted](#marking-a-task-as-uncompleted): `unmark`
  * [Deleting a task](#deleting-a-task): `delete`
  * [Exiting the Orion application](#exiting-the-orion-application): `bye`

## Quick start

1. Ensure you have Java 17 or above installed in your Computer.
1. Download the latest .jar file from [here](https://github.com/elliot-tang/ip/releases/tag/A-Release).
1. Copy the file to the folder you want to use as the home folder for your Orion application.
1. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar 
   Orion.jar command to run the application.

## Viewing help

Shows a message listing out all the supported commands.

Format: `help`

Examples:

> help

```dtd
Here are a list of commands that you can use!
list: Shows a list of all your tasks, together with their completion status and deadlines if applicable.
...
```

## Listing all tasks

Shows a list of all tasks, both completed and uncompleted.
The tasks will be shown in the following format:

* Todos: `<task number>. [T][completion status] <task description>`
* Deadlines: `<task number>. [D][completion status] <task description> (due by: <deadline>)`
* Events: `<task number>. [E][completion status] <task description> (from: <start>, to: <end>)`

Format: `list`

Examples:

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][X] finish homework
```

Or if there are no tasks in the list:

> list

```dtd
Your task list is empty! Well done!
```

## Adding a todo task

Adds a todo task to the list with the provided description.

Format: `todo <task description>`

Examples:

> todo write user guide

```dtd
Sure! I've added the following task to your list:
[T][ ] write user guide
Now you have 3 tasks in your list.
```

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][X] finish homework
3. [T][ ] write user guide
```

## Adding a deadline

Adds a deadline to the list with the provided description and deadline.

Format: `deadline <task description> /by <yyyy-mm-dd>`

Examples:

> deadline finish user guide /by 2024-09-20

```dtd
Sure! I've added the following task to your list:
[D][ ] finish user guide (due by: 20/9/2024)
Now you have 3 tasks in your list.
```

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][X] finish homework
3. [D][ ] finish user guide (due by: 20/9/2024)
```

## Adding an event

Adds an event to the list with the provided description, start date and end date.

Format: `event <task description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

Examples:

> event recess week finally /from 2024-09-21 /to 2024-09-29

```dtd
Sure! I've added the following task to your list:
[E][ ] recess week finally (from: 21/9/2024, to: 29/9/2024)
Now you have 3 tasks in your list.
```

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][X] finish homework
3. [E][ ] recess week finally (from: 21/9/2024, to: 29/9/2024)
```

## Finding a task

Shows a list of all tasks whose task description contains the specified query.

Format `find <search query>`

Examples:

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][X] finish homework
3. [T][ ] read another book
```

> find book

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][ ] read another book
```

## Marking a task as completed

Marks a specified task as completed.

Format `mark <task number>`

Examples:

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][ ] finish homework
3. [T][ ] read another book
```

> mark 3

```dtd
Sure! I've marked the following task as done:
[T][X] read another book
```

## Marking a task as uncompleted

Marks a specified task as not completed.

Format: `unmark <task number>`

Examples:

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][ ] finish homework
3. [T][X] read another book
```

> unmark 3

```dtd
Sure! I've marked the following task as undone:
[T][ ] read another book
```

## Deleting a task

Deletes a specified task.

Format: `delete <task number>`

Examples:

> list

```dtd
Here are the tasks in your list:
1. [T][ ] read book
2. [T][ ] finish homework
3. [T][X] read another book
```

> delete 3

```dtd
Sure! I've deleted the following task:
[T][X] read another book
Now you have 2 tasks in your list.
```

## Exiting the Orion application

Saves the current task list and exits the application.

Format: `bye`

Examples: 

> bye

```dtd
Your task list has been saved successfully!
Bye! Hope to see you again soon!
```

## Image Attribution

[Orion Nebula Image](https://www.flickr.com/photos/7634329@N07/495636660/)

[User Image](https://www.flaticon.com/free-icon/blank-user_16467)