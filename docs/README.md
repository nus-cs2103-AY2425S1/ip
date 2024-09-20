# Ontos User Guide

![sample image of gui](https://github.com/KiKuasaurus/ip/blob/master/docs/Ui.png)

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
5. done <index>: Marks the task at the specified index as done.
6. delete <index>: Deletes the task at the specified index.
7. find <keyword>: Finds tasks that contain the keyword.
8. help: Displays the list of commands.
9. bye: Exits the program.
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

### `