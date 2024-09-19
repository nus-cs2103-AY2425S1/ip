# Appleaster

Appleaster is a quirky, apple-themed chatbot that helps you manage your tasks with a dash of fruity fun. It's here to make your day-to-day task management as sweet as apple pie!

![Appleaster UI](Ui.png)

## Quick start

1. Ensure you have Java 17 installed.
2. Download the latest `appleaster.jar` from the [releases](https://github.com/yourusername/appleaster/releases) page.
3. Copy the file to the folder you want to use as the home folder for Appleaster.
4. Open a command window in that folder.
5. Run the command `java -jar appleaster.jar`. The GUI should appear in a few seconds.

## Features 

* Add todos, deadlines, and events
* List all tasks
* Mark tasks as done
* Delete tasks
* Find tasks by keyword
* View schedule for a specific date

## Usage

### `todo`: Add a todo task

```
todo Buy apples
```

### `deadline`: Add a task with a deadline

```
deadline Make apple pie /by 2024-09-16 1800
```

### `event`: Add an event

```
event Apple Festival /from 2024-09-20 1000 /to 2024-09-20 1800
```

### `list`: List all tasks

```
list
```

### `mark`: Mark a task as completed

```
mark 1
```

### `unmark`: Mark a task as not completed

```
unmark 1
```

### `delete`: Delete a task

```
delete 2
```

### `find`: Find tasks by keyword

```
find apple
```

### `view`: View schedule for a specific date

```
view 2024-09-16
```

### `bye`: Exit the program

```
bye
```

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| `todo` | `todo TASK` | `todo Buy apples` |
| `deadline` | `deadline TASK /by DATE TIME` | `deadline Make apple pie /by 2024-09-16 1800` |
| `event` | `event TASK /from START_DATE START_TIME /to END_DATE END_TIME` | `event Apple Festival /from 2024-09-20 1000 /to 2024-09-20 1800` |
| `list` | `list` | `list` |
| `mark` | `mark TASK_NUMBER` | `mark 1` |
| `unmark` | `unmark TASK_NUMBER` | `unmark 1` |
| `delete` | `delete TASK_NUMBER` | `delete 2` |
| `find` | `find KEYWORD` | `find apple` |
| `view` | `view DATE` | `view 2024-09-16` |
| `bye` | `bye` | `bye` |

## Saving

Appleaster automatically saves your tasks to a file named `tasks.txt` in the same directory as the JAR file. This file is loaded when you start Appleaster, so your tasks persist between sessions.

## Contact Us

If you have any questions, feel free to contact me at `manaslegodesigns@gmail.com`.