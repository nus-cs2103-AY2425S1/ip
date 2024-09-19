# Stan User Guide

![Screenshot of Stan bot](Ui.png)

“The best way to predict the future is to invent it.” – Alan Kay

Stan is here to make your task management effortless and efficient. With Stan, you can:
- [Features & Usage](#features--usage)
    - [Adding ToDo](#adding-todo-todo-todo_description)
    - [Adding Deadline](#adding-deadline-deadline-deadline_description-by-yyyy-mm-dd-hhmm)
    - [Adding Event](#adding-event-event-event_description-from-yyyy-mm-dd-hhmm-to-yyyy-mm-dd-hhmm)
    - [Listing all tasks](#listing-all-tasks-list)
    - [Marking task](#marking-task-mark-task_index)
    - [Unmarking task](#unmarking-task-unmark-task_index)
    - [Deleting task](#deleting-task-delete-task_index)
    - [Finding task using keyword](#Finding task)
    - [Exiting application](#exiting-application-bye)
- [FAQ](#faq)
## Quick Start
1. Here's how you can get started:
2. Download Stan from the [release page](https://github.com/stanleytangzh/ip/releases).
3. Move `stan.jar` to the folder you want to use as the home folder for your Stan chatbot.
4. Double-click on the `stan.jar` file.
5. Start adding your tasks with simple commands.
6. Let Stan do the heavy lifting for you! 😎

## Features & Usage

### Adding ToDo: `todo TODO_DESCRIPTION`

Add a todo without any date & time.

- Example of usage: `todo read` or `t read`

- Expected outcome: The todo will be added to your task list and saved locally.

```
✅ Task added successfully! 📝
[T][ ] read
Now you have 1 tasks in the list. Keep it up! 💪
```

### Adding Deadline: `deadline DEADLINE_DESCRIPTION /by yyyy-MM-dd HHmm`

Add a deadline to be completed by a date & time.

- Example of usage: `deadline sleep /by 2023-07-01 2359` or `d sleep /by 2023-07-01 2359`

- Expected outcome: The deadline will be added to your task list and saved locally.

```
✅ Task added successfully! 📝
[D][ ] sleep (by: Jul 01 2023, 1159pm)
Now you have 2 tasks in the list. Keep it up! 💪
```

### Adding Event: `event EVENT_DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

Add an event that occurs at a date & time.

- Example of usage: `event drink /from 2024-07-29 1100 /to 2024-07-29 2000` or `e drink /from 2024-07-29 1100 /to 2024-07-29 2000`

- Expected outcome: The event will be added to your task list and saved locally.

```
✅ Task added successfully! 📝
[E][ ] drink (from: Jul 29 2024 11:00am to: 8:00pm)
Now you have 3 tasks in the list. Keep it up! 💪
```
### Listing all tasks: `list`

Display a list of all your tasks.

- Example of usage: `list` or `ls`

- Expected outcome: The list of tasks stored locally will be displayed.

```
📋 Here are the tasks in your list:
1.[T][ ] read
2.[D][ ] sleep (by: Jul 01 2023, 1159pm)
3.[E][ ] drink (from: Jul 29 2024 11:00am to: 8:00pm)
Stay focused! You got this! 💪
```

### Marking task: `mark TASK_INDEX`

Marks the task with specified task index as done, using a "X".

- Example of usage: `mark 1`

- Expected outcome: The task will be marked with "[X]" and updated locally.

```
🎉 Awesome! I've marked this task as done! 🎯
[T][X] read
You're on a roll with 3 tasks left! Keep going! 🚀
```

### Unmarking task: `unmark TASK_INDEX`

Unmarks the task with specified task index to update task status as not done, and removes "X".

- Example of usage: `unmark 1`

- Expected outcome: The task will be unmarked with "[ ]" and updated locally.

```
🔄 Oops! I've marked this task as not done yet. 🔄
[T][ ] revision
You have 3 tasks in the list. Let's get it done! 💼
```

### Deleting task: `delete TASK_INDEX`

Deletes the task with specified task index from your task list.

- Example of usage: `delete 3` or `del 3`

- Expected outcome: The task will be deleted from your task list and updated locally.

```
🗑️ Task deleted! 💨
I've removed this task:
[E][ ] drink (from: Jul 29 2024 11:00am to: 8:00pm)
Now you have 2 tasks left. Clean and tidy! ✨
```

### Finding task using keyword: `find KEYWORD`

Finds all matching tasks containing the keyword from your task list.

- Example of usage: `find sleep` or `f sleep`

- Expected outcome: All matching tasks containing the keyword will be displayed.

```
🔍 Found these matching tasks:
1.[D][ ] sleep (by: Jul 01 2023, 1159pm)
Happy to help! 🎉
```
### Exiting application: `bye`

Terminates Stan chatbot and exits the application.

- Example of usage: `bye` 

- Expected outcome: A goodbye message is shown. The program window will close automatically.

```
👋 Bye-bye! Hope to see you again soon! Take care! 🌟
```

## FAQ

__Q:__ Is my task list saved?  
__A:__ Yes. All of your data will be saved and updated automatically when you use Stan.

__Q:__ How do I access all my saved data?  
__A:__ You can go to the subfolder named `data` located in the same folder as `stan.jar`. Next, you can open the file named `stan.txt` located under `data`.

__Q:__ Can I edit the saved file directly and load those data when I run Stan?  
__A:__ It is possible but not recommended to make edits directly in `stan.txt`, as doing so might corrupt the data if not done correctly.

__Q:__ What to do if my saved data is corrupted and could not be read?  
__A:__ Unfortunately, your data cannot be restored. You can delete the `stan.txt` file or simply delete the whole `data` folder. A __fresh__ `stan.txt` will be created when you run the program again.