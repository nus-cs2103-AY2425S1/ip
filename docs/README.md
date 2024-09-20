# Bobby
Bobby is your personal butler, he helps to manage **any** and **every** tasks you have!

Compared to the conventional way of using your mouse and keyboard, Bobby works best when you type. Type away all your tasks and never lose track of any of them again!

## Installation

To get your personal butler:
1. Ensure that you have `Java 17` downloaded on your device.
2. Download the latest `jar` file
3. Run `java -jar bobby_v1.jar` in the directory of where the `jar` file is located
4. A GUI window should pop up as shown below:

![img.png](startupImage.png)

## Features

> Notes about input format:
> - Words in `UPPERCASE` are parameters to be supplied by the user
> - Order of arguments cannot be changed
> - Extra arguments will be interpreted as incorrect input

Here are a list of features that Bobby provides:
### Creating tasks

Create a todo task in the following format: `todo TASK_NAME DESCRIPTION`

Create a deadline task in the following format: `deadline TASK_NAME /by DATE`

> The `DATE` has to be in `YYYY-MM-DD` format

Create an event task in the following format: `event TASK_NAME /from START /to END`

### List

List out all your tasks in with `list`

### Task completion

You can mark a task as completed by inputting `mark TASK_NUMBER`

you can also unmark a task by inputting `unmark TASK_NUMBER`

- `TASK_NUMBER` indicates the index of the task in `list`

### Removing Tasks

Remove any task with `delete TASK_NUMBER`
- `TASK_NUMBER` indicates the index of the task in `list`

### Finding matching tasks

If you want to related tasks, simply use `find KEYWORD` to display all the tasks related 
to `KEYWORD`

E.g: `find assignment` will display a list of tasks containing the word `assignment`
- `find` is case-sensitive. E.g: `Homework` and `homework` will not match

### Archiving

To archive a task, `archive TASK_NUMBER`
To view all your archived tasks, simply `listarchive`.

> **TIP:** Archive tasks after they are marked completed to prevent a very long list of tasks.

## Saving and Loading
The list of tasks are stored in `[JAR_FILE_LOCATION]/data.txt` and list of archived tasks are stored in 
`[JAR_FILE_LOCATION]/archive.txt`. When the app is start up, Bobby will load in the data from these files. 
If these files are not present, they will automatically be created. Upon any modifications, 
Bobby will auto save the files. There is no need to save manually.


> **CAUTION**: In the event if there is any need to edit the data files directly, it is important to follow the format 
> correctly or the application might not work as intended. It is therefore recommended to work with a
> backup copy instead.

## Command Summary

| Command | Format |
|:-:|:-:|
| Todo | `todo TASK_NAME` |
| Deadline | `deadline TASK_NAME /by DATE` |
| Event | `event TASK_NAME /from START /to END` |
| List | `list` |
| Mark | `mark TASK_NUMBER` |
| Unmark | `unmark TASK_NUMBER` |
| Delete | `delete TASK_NUMBER` |
| Find | `find KEYWORD` |
| Archive | `archive TASK_NUMBER` |
| List archived tasks | `listarchive` |
