# Momo User Guide

![Screenshot of Momo chatbot](docs/Ui.png)

Want to manage and complete your tasks with a little extra  _caring_ nudge? Momo is the perfect companion for you!
This friendly chatbot helps you to organize your daily tasks, deadlines and events!

## Features summary

Momo kindly supports 3 types of tasks:
1. Todo tasks: Basic tasks with a description
2. Deadline tasks: Tasks that must be completed by a certain date
3. Event tasks: Tasks with a start and end date

Additionally, Momo also supports:
- Deleting a task: Deleting any task in the list
- Mark/Unmarking a task: Marking tasks as complete or incomplete
- Listing all tasks
- Archiving: Archiving all tasks or one task of choice
- Find: Finding all tasks which contain a keyword
- Exiting the program

--- 
## Quick Start

To start using Momo, simply:
1. Ensure you have Java 17 installed on your computer
2. Download `momo.jar`
3. Open your Mac/Windows terminal and enter the following command to start Momo:
   ``` java -jar momo.jar ```
4. Type the command in the input box and press `Send` to execute it.

Some sample commands you can try:
- `todo finish readme`: Adds a todo task named "finish readme"
- `deadline finish project /by 2025-04-05`: Adds a deadline named "finish project" that must be done by 5 April 2025
- `list`: Lists all tasks
- `delete 1`: Deletes the first task shown on the list
- `bye`: Leaves the app

### Quick features overview
- Delete: `delete <task_index>`
- Mark/Unmark: `mark <task_index>` and `unmark <task_index>`
- List: `list`
- Archive: `archive all` or `archive <task_index>`
- Find: `find <keyword>`
- Exiting the program: `bye`

--- 
## Adding a task
You can add three types of tasks: Todos, Deadlines, and Events.

Whenever a task is added correctly, there will be a message sent which shows the task just added to confirm it and
shows the number of tasks in the current list.

### Adding a Todo: ``
To add a simple task without a due date, use the todo command followed by the task description.
``` 
todo <task_description>
```

#### Example usage
``` 
todo finish readme

```

### Adding a deadline
To add a task that must be completed by a specific date, use the deadline command followed by the description and due
date formatted as YYYY-MM-DD.
``` 
deadline <task_description> /by <due_date>
```

#### Example usage
``` 
deadline submit project 1 /by 2024-10-01
```


##### Expected outcome
```
Noted. I've added this task:
[D][ ] submit report (Oct 1 2024)
Now you have 12 task(s) in the list.
```

### Adding an Event
To add an event that has a start and end date, use the event command followed by the description, start date, and end date.
``` 
event <task_description> /from <start_date> /to <end_date>
```
#### Example usage
``` 
event catching up with sleep /from 2024-09-21 /to 2024-10-29

```
---

## Other Features


### List

Use the `list` command to list all of the tasks in the active list (tasks which have not been deleted or archived)
```
list
```

### Marking a task

Use the `mark` command followed by the task index to mark a task as complete.
``` 
mark <task_index> 
```

#### Example usage
``` 
mark 3

```
### Unmarking a task
To set a task back to incomplete, use the unmark command followed by the task index.
``` 
unmark <task_index> 
```

#### Example usage
``` 
unmark 3

```
### Deleting a task
To remove a task from the list, use the `delete` command followed by the task index.

```
delete <task_index>
```

#### Example usage
``` 
delete 2

```
### Finding a task
To find a specific task in your list via a keyword, use the find command followed by the keyword.
```
find <task_index>
```
#### Example usage
``` 
find sleep
```

### Archiving
You can archive all tasks or a specific task to remove them from the current list and instead place them in another
file inside the data folder storing all Momo related information titled `archive.txt`.

1. To archive *all* tasks: `archive all`
2. To archive *a specific* task: `archive <task_index>`


### Exiting the Momo chatbot
To end the Momo chatbot, simply use the command `bye`.

