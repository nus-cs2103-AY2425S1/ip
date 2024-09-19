# Bobby User Guide

Bobby the chatbot is a **task management application where user can use it to keep track of tasks**. It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Bobby the chatbot can help you get your tasks done faster than traditional GUI apps.

- [Quick Start](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#quick-start)
- [Features](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#features)
  - [Adding To Do Tasks: `todo`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#adding-to-do-tasks-todo)
  - [Adding Deadline Tasks: `deadline`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#adding-deadline-tasks-deadline)
  - [Adding Event Tasks: `event`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#adding-event-tasks-event)
  - [Finding the list of Tasks: `list`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#finding-the-list-of-tasks-list)
  - [Mark task in list as done: `mark`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#mark-task-in-list-as-done-mark)
  - [Unmark task in list as not done: `unmark`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#unmark-task-in-list-as-not-done-unmark)
  - [Locating a task by its description: `find`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#locating-a-task-by-its-description-find)
  - [Delete a task from the list: `delete`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#delete-a-task-from-the-list-delete)
  - [Exiting the program: `bye`](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#exiting-the-program-bye)
  - [Saving the data](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#saving-the-data)
- [Known Issue](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#known-issue)

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer
2. Download the latest `.jar` file from [here](https://github.com/zhiyi12345/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Chatbot.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar bobby.jar command to run the application. A GUI similar to the below should appear in a few seconds, you will be greeted with a welcome message from Bobby.
   <img width="512" alt="Screenshot 2024-09-19 at 3 01 41 PM" src="https://github.com/user-attachments/assets/ca8c63f8-6dd1-4a3b-8285-d995cd2d2d07">


6. Type the command in the command box and press Enter to execute it.
7. Refer to the [Features](https://github.com/zhiyi12345/ip/edit/master/docs/README.md#features) below for details of each command.

## Features
>[!NOTE]
>- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
>- Date and time format will be in yyyy-MM-dd HHmm.

### Adding To Do Tasks: `todo`

Adds a to do task to the tasklist, there is not a timebased task (no start/end date).

Format: `todo DESCRIPTION`
>[!TIP]
> Description can be in alphanumeric. Can have multiple words.

Example: 
```
todo read book
```
```
todo buy 1 apple
```

### Adding Deadline Tasks: `deadline`

Adds a task with deadline to the tasklist, there is a due date for this task.

Format: `deadline DESCRIPTION /by yyyy-MM-dd HHmm`
>[!TIP]
> 1. Description can be in alphanumeric. Can have multiple words.
> 2. Please follow the format for date & Time

Example: 
```
deadline return book /by 2024-01-17 1600
```
```
deadline buy birthday present /by 2024-12-07 2100
```


### Adding Event Tasks: `event`

Adds an event, where there will be a start and end time

Format: `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`
>[!TIP]
> 1. Description can be in alphanumeric. Can have multiple words.
> 2. Please follow the format for date & Time
> 3. Note that the to timing should be after than from timing

Example: 
```
event storytelling competition /from 2024-01-17 1000 /to 2024-01-17 1800
```
```
event birthday party /from 2024-12-07 1700 /to 2024-12-08 0000
```

### Finding the list of Tasks: `list`
Shows a list of all the task in the tasklist.

Format: 
```list```

### Mark task in list as done: `mark`
Marks a specified task as done.

Format: ```mark INDEX```

- Mark the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed tasklist.
- The index must be a positive integer 1, 2, 3, …​

> [!TIP]
> You can use the `list` function to see all the tasks

Examples:
marks the 8th task in the list as done(if there are at least 8 tasks)
```mark 8```

> [!NOTE]
> 1. Tasks that are already marked as done will be ignored. bot will let you know that the task was already marked.
> 2. Please keep your INDEX within the list index range

### Unmark task in list as not done: `unmark`
Unmarks a specified task that was done to not done.

Format: ```unmark INDEX```

- Unmark the task at the specified `INDEX` from done to not done.
- The index refers to the index number shown in the displayed tasklist.
- The index must be a positive integer 1, 2, 3, …​

> [!TIP]
> You can use the `list` function to see all the tasks

Example:
unmarks the 5th task in the list from done to not done (the 5th task in the list must be marked done)
```unmark 5```

> [!NOTE]
> 1. Tasks that are already not marked as done will be ignored. bot will let you know that the task has not been done.
> 2. Please keep your INDEX within the list index range

### Locating a task by its description: `find`
Finds a task whose description contains any of the given keyword.

Format: `find KEYWORD`

- The search is case-insensitive. e.g APPLE will match apple
- Only full words will be matched e.g. apple will not match apples

Examples:
'''
find apple
'''

### Delete a task from the list: `delete`
Deletes the specified task from the task list.

Format: ```delete INDEX```

- Deletes the task at the specified ```INDEX```.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

> [!TIP]
> You can use the `list` function to see all the tasks

Examples:
delete the 10th task in the list (if there are at least 10 tasks)
```delete 10```

> [!NOTE]
> Please keep your `INDEX` within the list index range

### Exiting the program: `bye`
Exits the program.

Format: 
```bye```

### Saving the data
Tasks data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Known Issue
1. If you **maximize the program window**, the chatbot's **layout will not adjust properly**, and the input bar will move to the bottom of the screen. To avoid this issue, please **use the program in its minimized mode**.
