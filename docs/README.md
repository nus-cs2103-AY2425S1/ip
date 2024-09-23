# ToMo User Guide

<img src="/docs/Ui.png" alt="Eli chatbot screenshot" width="300"/>

ToMo is a **precise** chatbot that helps you managing your tasks.

## Quickstart
- Download the JAR file [here](https://github.com/kienvumrpm/ip/releases)
- Add your tasks (todo, deadline, event)
- Mark some of your tasks
- Tag some of your tasks
- Close the GUI (It'll automatically save your task 🤯)

## Features
### Conventions
In following features, some notations are used that help you get a better view of command formats:
- `{description}` and `{pattern}` are non-empty strings, represent description of a task and the pattern to filter respectively.
- `{deadline}` `{start}` and `{end}` are strings with time format (YYYY-MM-DD HHMM), represent deadline, start time and end time for a task respectively.
- `{idx}` is the integer index that represent that help you to locates a task. The index should be positive and not greater than number of tasks you have.
- `{tag}` is following hashtag format which is # followed by a non-empty string. It represents the tag you want to handles.
### Adding ToDo tasks
To add ToDo task, you need to type in the command that have format `todo {description}`.

Example: `todo borrow book`

Expected output: 
```
A task is added:
[T][ ] borrow book tags:
```
### Adding Deadline tasks
To add Deadline task, you need to type in the command that have format `deadline {description} /by {deadline}`.

Example: `deadline return book /by 2024-09-23 1700`

Expected output:
```
A task is added:
[D][ ] return book (by: Sep 23 2024, 05:00 PM) tags:
```
### Adding Event tasks
To add Event task, you need to type in the command that have format `event {description /from {start} /to {end}`.

Example: `event visit home /from 2024-09-24 1700 /to 2024-10-01 0930`

Expected output:
```
A task is added:
[E][ ] visit home (from: Sep 24 2024, 05:00 PM to: Oct 01 2024, 09:00 AM) tags:
```
### Listing tasks
To list all the tasks, you need to type in the simple command `list`.

Expected output: 
```
You have 3 tasks as follow:
1. [T][ ] borrow book tags:
2. [D][ ] return book (by: Sep 23 2024, 05:00 PM) tags:
3. [E][ ] visit home (from: Sep 24 2024, 05:00 PM to: Oct 01 2024, 09:30 AM) tags:
```
### Marking tasks
To mark a task as done, you need to type in the command that have format `mark {idx}`.

Example: `mark 1`

Expected output:
```
A task is marked:
[T][X] borrow book tags:
```
### Unmarking tasks
To mark a task as undone, you need to type in the command that have format `unmark {idx}`.

Example: `unmark 1`

Expected output:
```
A task is unmarked:
[T][ ] borrow book tags:
```
### Filtering tasks
To filter all the tasks that have some pattern, you need to type in the command that have format `find {pattern}`.

Example: `find book`

Expected output:
```
You have 2 tasks that contains the pattern "book" is follow:
1. [T][ ] borrow book tags:
2. [D][ ] return book (by: Sep 23 2024, 05:00 PM) tags:
```
### Deleting tasks
To delete a task, you need to type in the command that have format `delete {idx}`.

Example: `delete 2`
```
A task is deleted:
[D][ ] return book (by: Sep 23 2024, 05:00 PM) tags:
```
### Tagging tasks
To tag a task, you need to type in the command that have format `tag {idx} {tag}`.

Example: `tag 2 #family`

Expected output:
```
A task is tagged:
[E][ ] visit home (from: Sep 24 2024, 05:00 PM to: Oct 01 2024, 09:30 AM) tags: #family
```
### Untagging tasks
To untag a task, you need to type in the command that have format `untag {idx} {tag}`.

Example: `untag 2 #family`

Expected output:
```
A task is untagged:
[E][ ] visit home (from: Sep 24 2024, 05:00 PM to: Oct 01 2024, 09:30 AM) tags:
```

Hacks: You can use `find` command to list all the tasks that have some specific tag.

### Exit the conversation
No need to say goodbye, once you exit the conversation, your tasks are automatically saved.
