# _Ollie_ User Guide ☺️

<img src="./Ui.png" width="500">

> _Organize with ease, accomplish with **Ollie ☺** !_ ️

</br>


---

# What is Ollie️ ☺️

**Ollie ☺**️ is your task manager app! 
- Manages your tasks for you
- _User-friendly_ syntax using one-letter commands eg `-l` to list all tasks
- [Features](#features)
  - [Add a task](#add-a-task)
      - [Add a Todo Task](#add-a-todo-task) `todo`
      - [Add a Deadline Task](#add-a-deadline-task) `deadline`
      - [Add an Event Task](#add-an-event-task) `event`
  - [List all tasks](#list-all-tasks) `-l`
  - [Mark a task](#mark-a-task)
     - [Mark a task as done](#mark-a-task-as-done) `-m`
     - [Unark a task as done](#unmark-a-task-as-done) `-u`
  - [Delete a task](#delete-a-task) `-d`
  - [Find tasks](#find-tasks) `-f`

</br>


---

# How to use Ollie️ ☺️
1. Make sure Java 17 or above is installed.
2. Download the latest release of `.jar` file [here](https://github.com/rxchell/ip/releases) 👈🏼
3. Open your command terminal. 
4. Navigate to the folder with `ollie.jar`.
5. Run this command in the folder to open the application.
    ```bash
    java -jar ollie.jar
    ```
6. The Ollie ☺ application starts! 🎉 
<img src="./images/Start.png" width="300">

7. Please refer to [features](#features) for the commands that can be typed.


</br>


--- 

# Features
Function | Command | Format | Example
---|---|---|---
[Add a Todo task](#add-a-todo-task) | `todo` | `todo <description>` | `todo read book`
[Add a Deadline task](#add-a-deadline-task) | `deadline` | `deadline <description> /by: <YYYY-MM-DD HH:MM>` | `deadline return book /by: 2021-09-30 18:00`
[Add an Event task](#add-a-deadline-task) | `event` | `event <description> /from: <YYYY-MM-DD HH:MM> /to: <YYYY-MM-DD HH:MM>` | `event meeting /from: 2021-09-17 14:00 /to: 2021-09-17 16:00`
[List all tasks](#list-all-tasks) | `-l` | `-l` | `-l`
[Mark a task as done](#mark-a-task-as-done) | `-m`| `-m <task number>` | `-m 1`
[Mark a task as undone](#unmark-a-task-as-done) | `-u`| `-u <task number>` | `-u 1`
[Delete a task](#delete-a-task) | `-d` | `-d <task number>` | `-d 1`
[Find a task with the keyword](#find-tasks) | `-f` | `-f <keyword to find>`| `-f book`

> [!NOTE]
> Replace the text in `<...>` with the corresponding parameters.
> 
> `task number` is the index of the task in the task list. 

**Other commands:**
- `-b`: Say bye to Ollie ☺

</br>

## Add a task
There are 3 types of tasks that can be added to the task list:
1. **Todo**: Has no date or time 
2. **Deadline**: Has a deadline for the task to be completed by
3. **Event**: Has start and end times for the task

### Add a Todo task
> ```
> todo <description>
> ``` 
> Adds a todo task with a description. 

Example: `todo read book` adds a Todo task to the task list.

<img src="./images/Todo.png" width="300">

### Add a Deadline task
> ```
> deadline <description> /by: <YYYY-MM-DD HH:MM>
> ```
> Adds a deadline task with a description and date.
> 
> The date should be in the format **YYYY-MM-DD HH:MM**, eg `2021-09-17 23:59`.

Example: `deadline return book /by: 2021-09-17 23:59` adds a Deadline task to the task list.

<img src="./images/Deadline.png" width="300">

### Add an Event task
> ```
> event <description> /from: <YYYY-MM-DD HH:MM> /to: <YYYY-MM-DD HH:MM>
> ```
> Adds an event task with a description, start time and end time.
> 
> The start and end times should be in the format **YYYY-MM-DD HH:MM**, eg `2021-09-17 14:00`.

Example: `event meeting /from: 2021-09-17 14:00 /to: 2021-09-17 16:00` adds an Event task to the task list.

<img src="./images/Event.png" width="300">


</br>


## List all tasks
> ```
> -l
> ```
> Lists all tasks in the task list.

Example: `-l` lists all tasks in the task list.

<img src="./images/List.png" width="300">

</br>

## Mark a task
### Mark a task as done
> ```
> -m <task number>
> ```
> Marks a task as done, using the task number specified in the task list.

Example: `-m 1` marks the first task in the list as done.

<img src="./images/Mark.png" width="300">

### Unmark a task as done
> ```
> -u <task number>
> ```
> Marks a task as undone, using the task number specified in the task list.

Example: `-u 1` marks the first task in the list as undone.

<img src="./images/Unmark.png" width="300">


</br>


## Delete a task
> ```
> -d <task number>
> ```
> Deletes a task, using the task number specified in the task list.

Example: `-d 3` deletes the third task in the list.

<img src="./images/Delete.png" width="300">

</br>


## Find tasks
> ```
> -f <keyword to find>
> ```
> Finds tasks with the keyword in the description.

Example: `-f book` finds tasks with the keyword `book` in the description.

<img src="./images/Find.png" width="300">
