# YapperBot User Guide

Welcome to YapperBot! YapperBot is your friendly task management assistant that helps you keep track of your to-dos,
deadlines, and events with ease. This guide will walk you through the essential features and commands of YapperBot.

## 1. Managing Tasks

### Add a Todo Task
  * Command: todo <description>
  * Example: `todo read book`
  * Adds a simple task without any date or time attached.

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task(s) in the list.
```
### Add a Deadline Task
  * Command: deadline <description> /by <date>
  * Example: `deadline read book /by 17/9/2024 1800`
  * Adds a task with a specific deadline. The date can be formatted as d/M/yyyy HHmm or yyyy-MM-dd.

```
Got it. I've added this task:
    [D][ ] read book (by: Sep 17 2024 6:00 pm)
Now you have 1 task(s) in the list.
```
### Add a Event Task
  * Command: event <description> /from <start time> /to <end time>
  * Example: `event read book /from 2/12/2024 0800 /to 2/12/2024 1000`
  * Adds an event task that occurs within a specified time frame. The date can be formatted as d/M/yyyy HHmm.

```
Got it. I've added this task:
    [D][ ] read book (from: Sep 17 2024 2:00 pm to Sep 17 2024 4:00 pm)
Now you have 1 task(s) in the list.
```

## 2. Viewing Tasks

### List All Tasks
  * Command: `list`
  * Displays all tasks in your list, showing their status and details.

## 3. Updating Tasks

### Mark a Task as Done
  * Command: mark <task number>
  * Example: `mark 1`
  * Marks the specified task as completed.

```
Nice! I've marked this task as done:
    [T][X] sleep
```

### Unmark a Task
  * Command: unmark <task number>
  * Example: `unmark 1`
  * Marks the specified task as not completed.

```
OK, I've marked this task as not done yet:
    [T][ ] sleep
```

## 4. Managing Tasks

### Delete a Task
  * Command: delete <task number>
  * Example: `delete 2`
  * Deletes the specified task from the list.

```
Noted. I've removed this task:
    [T][ ] read book
Now you have 6 task(s) in the list.
```

## 5. Finding Tasks

### Find Tasks by Keyword
  * Command: find <keyword>
  * Example: `find meeting`
  * Searches and displays tasks containing the specified keyword.

## 6. Getting Help

### Help Command
  * Command: `help`
  * Displays a list of all available commands and their usage.

## 7. Exiting the Application

### Exit YapperBot
  * Command: `bye`
  * Saves your tasks and exits the application after displaying a goodbye message.

```
Bye! Hope to see you again soon!
```

## Tips
### Task Numbers: 
* Task numbers correspond to the position of the task in the list displayed by the list command.
### Date and Time Formats: 
* Ensure dates are formatted correctly when adding deadlines and events for proper recognition by YapperBot.
* For any further assistance, feel free to use the `help` command within the application.