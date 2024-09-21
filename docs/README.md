# Ontos User Guide

![sample image of gui](Ui.png)

## Features

### Save Location
Start by giving Ontos the name of your save file

Example: `Ontos`
```
 Save location set to: Ontos.txt
 Hello! I'm Ontos
 What can I do for you?
```

### Help
Gives you a list of commands that can be used with Ontos

Format: `help`

Example: `help`
```
Here are the commands you can use:
1. list: Lists all tasks in the task list.
2. todo <description>: Adds a todo task to the task list.
3. deadline <description> /by <yyyy-mm-dd>: Adds a deadline task to the task list.
4. event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>: Adds an event task to the task list.
5. mark <index>: Marks the task at the specified index as done.
6. unmark <index>: Marks the task at the specified index as undone.
7. delete <index>: Deletes the task at the specified index.
8. find <keyword>: Finds tasks that contain the keyword.
9. help: Displays the list of commands.
10. bye: Prints the goodbye message.
```

### List
Shows a list of available tasks

Format: `list`

Example: `list`
```
 Here are the tasks in your list:
 1. [T][ ] first task
 2. [D][ ] first deadline (by: 2024-09-24)
 3. [E][ ] first event (from: 2024-09-20 to: 2024-09-21)
```

### Todo
Add a task without any time limit

Format: `todo <Task>`

Example: `todo first task`
```
 Got it. I've added this task:
 [T][ ] first task
 Now you have 1 tasks in the list.
```

### Deadline
Add a task with a specified deadline

Format: `deadline <task> /by <deadline>`
where deadline is a date in the format `yyyy-mm-dd`

Example: `deadline first deadline /by 2024-09-24`
```
 Got it. I've added this task:
 [D][ ] first deadline (by: 2024-09-24)
 Now you have 2 tasks in the list.
```

### Event
Add an event with a start and end date

Format: `event <description> /from <start-date> /to <end-date>`
where start-date and end-date are in the format `yyyy-mm-dd`

Example: `event first event /from 2024-09-20 /to 2024-09-21`
```
 Got it. I've added this task:
 [E][ ] first event (from: 2024-09-20 to: 2024-09-21)
 Now you have 3 tasks in the list.
```

### Mark
Mark a task as completed

Format: `mark <index>`

Example: `mark 2`
```
 Nice! I've marked this task as done:
 [D][X] first deadline (by: 2024-09-24)
```

### Unmark
Mark a completed task as not done

Format: `unmark <index>`

Example: `unmark 2`
```
 Okay, I've unmarked this task:
 [D][ ] first deadline (by: 2024-09-24)
```

### Delete
Remove a task from the list

Format: `delete <index>`

Example: `delete 2`
```
 Noted. I've removed this task:
 [D][ ] first deadline (by: 2024-09-24)
 Now you have 2 tasks in the list.
```

### Find
Search for tasks containing a keyword

Format: `find <keyword>`

Example: `find event`
```
 Here are the matching tasks in your list:
 1. [E][ ] first event (from: 2024-09-20 to: 2024-09-21)
```

### Bye
Exit the program

Format: `bye`

Example: `bye`
```
 Bye. Hope to see you again soon!
```
