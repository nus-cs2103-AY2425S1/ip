# Potong User Guide

![Screenshot of Potong's user interface](Ui.png)

Potong is a Personal Assistant Chatbot that helps you keep track of your tasks.

## Quick Start

1. Ensure that you have java `17` or above installed on your Computer.
2. Download the latest `.jar` file from [here](https://github.com/BunnyHoppp/ip/releases).
3. Copy the file to a folder you want to store Potong.
4. Open a command terminal, `cd` into the folder with your .jar file, and use `java -jar potong.jar` command to run it.

## Features

### Add Tasks
There are 3 types of tasks to add.

- **ToDos**: tasks without any date/time attached to it *eg. read book*
  - Format: `todo [task]` *todo read book*
- **Deadlines**: tasks that need to be done before a specific date *eg. submit project before 14 Oct 2024*
  - Format: `deadline [task] /by [yyyy-mm-dd]`
- **Events**: tasks that start at a specific date/time and ends at a specific date/time *eg. project meeting from 4 Oct 3pm to 4 Oct 5pm*
  - Format: `event [task] /from [start] /to [end]`

```
Example output:
Got it. I've added this task:
 [T][ ][ ] read book
Now you have 1 tasks in the list.
```

```
Each task is represented as
[T][X][#fun] <task> (additional info)
T - type of task
X - Whether task is done
#fun - tag of task
task - name of task
additional info - any deadlines or time constraint
```

### Delete Tasks
- Delete your tasks that you don't need

Format: `delete [index]`

```
Example output:
Noted. I've removed this task:
 [T][ ][ ] read book
Now you have 0 tasks in the list.
```

### Show Tasks
- See all your tasks in a list

Format: `list`

```
Example output:
Here are the tasks in your list:
1. [T][ ][ ] read book
```

### Mark Tasks
- Mark or unmark your tasks as done or not

Mark Format: `mark [index]` 

Unmark Format: `unmark [index]`

```
Example output:
Nice! I've marked this task as done:
 [T][X][ ] read book
 
OK, I've marked this task as not done yet:
 [T][ ][ ] read book
```

### Find Tasks
- Find your tasks with a certain keyword (all tasks with this keyword will be shown)

Format: `find [keyword]`

```
Example output:
Here are the matching tasks in your list:
1. [T][ ][ ] read book
```

### Tag tasks
- Give your tasks a tag! #FUN

Format: `tag [index] [tag]`

```
Example output:
OK, I've tagged this task as FUN
 [T][ ][#FUN] read book
```

### Exit
- Save and leave the application

Format: `bye`

```
Example output:
Bye! See you again :)
```

**WE HOPE YOU ENJOY USING POTONG** ![potong](Icon.png)