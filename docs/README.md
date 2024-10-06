# TrackBot User Guide

![Ui.png](Ui.png)

**TrackBot is a desktop application for managing your everyday tasks.**

By combining Command Line Interface (CLI) and Graphical User Interface (GUI),
it allows everyone to manage their tasks faster than traditional GUI apps.
Our GUI allows users to view their interactions with TrackBot.

## Table of Contents
- [Features](#features)
  * [Adding a task](#adding-a-task)
    - [ToDo](#1-adding-a-todo-task--todo)
    - [Deadline](#2-adding-a-task-with-deadline--deadline)
    - [Event](#3-adding-an-event-with-starting-datetime-and-ending-datetime--event)
  * [Listing all tasks](#listing-all-tasks--list)
  * [Deleting a task](#deleting-a-task--delete)
  * [Marking a task](#marking-a-task--mark)
  * [Unmarking a task](#unmarking-a-task--unmark)
  * [Finding a task](#finding-a-task--find)
  * [Exiting the application](#exiting-the-application--bye)
- [Command Summary](#command-summary)

## Features

### Adding a task
- Duplicated task with the same descriptions will not be added.
- Users have to delete the existing task with the same description before adding a task with the same description.

#### 1. Adding a todo task : ```todo```

Adds a todo task to the task list.

Format: ```todo <task description>```

Example: ```todo read book```

Expected output:
<br>
![img.png](todo_output.png)

#### 2. Adding a task with deadline : ```deadline```

Adds a task with a deadline to the task list.

Format: ```deadline <task description> /by <date/time>```
- The input date can be in the format of "yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy", "dd-MMM-yyyy" and it will then be 
formatted to "MMM d yyyy", or,
- "yyyy-MM-dd HH:mm" and it will be formatted to "MMM d yyyy HH:mm", or,
- any format but it will not be formatted to any date or time.

Examples: 
- ```deadline return book /by 2024-12-29```
- ```deadline assignment /by 30-Dec-2025```
- ```deadline return books /by 2026-11-29 20:20```
- ```deadline quiz /by tonight```

Expected output:
<br>
![deadline_output.png](deadline_output.png)
<br>
![deadline_output_nodate.png](deadline_output_nodate.png)

#### 3. Adding an event with starting date/time and ending date/time : ```event```

Adds an event with a starting date/time and ending date/time to the task list.

Format: ```event <description> /from <start> /to <end>```
- The start and end can be in the format of "yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy", "dd-MMM-yyyy" and it will 
then be formatted to "MMM d yyyy", or,
- "yyyy-MM-dd HH:mm" and it will be formatted to "MMM d yyyy HH:mm", or,
- any format but it will not be formatted to any date or time.

Examples:
- ```event camp /from 2024-06-02 /to 2024-06-03```
- ```event project zoom /from 2024-12-02 12:12 /to 2024-12-03 13:30```

Expected output:
<br>
![event_output_date.png](event_output_date.png)
<br>

### Listing all tasks : ```list```

Shows a list of all tasks in the task list.

Format: ```list```
- Do not type anything before or after the ```list``` command as TrackBot will not understand that command.

Expected output:
<br>
![list_output.png](list_output.png)

### Deleting a task : ```delete```

Deletes a task with a specified task number from the task list.

Format: ```delete <task number>```
- Entering an invalid task number will produce the following output
```Please enter a valid task number.```
- Examples of invalid task numbers are negative integers and ```delete 100```
when there is only 3 tasks in the task list.

Examples:
- ```delete 1```
- ```delete 3```

Expected output:
<br>
![delete_output.png](delete_output.png)

### Marking a task : ```mark```

Marks a task with a specified task number in the task list.

Format: ```mark <task number>```
- Entering an invalid task number will produce the following output
  ```Please enter a valid task number.```
- Examples of invalid task numbers are negative integers and ```mark 100```
  when there is only 3 tasks in the task list.

Examples:
- ```mark 1```
- ```mark 2```


Expected output:
<br>
![mark_output.png](mark_output.png)


### Unmarking a task : ```unmark```

Unmarks a task with a specified task number in the task list.

Format: ```unmark <task number>```
- Entering an invalid task number will produce the following output
  ```Please enter a valid task number.```
- Examples of invalid task numbers are negative integers and ```unmark 100```
  when there is only 3 tasks in the task list.

Examples:
- ```unmark 1```
- ```unmark 2```

Expected output:
<br>
![unmark_output.png](unmark_output.png)

### Finding a task : ```find```

Find a task with a specified description in the task list.

Format: ```find <task keyword>```
- The search is case-insensitive. e.g Quiz will match quiz
- Only the description is searched.
- Partial or full words will be matched e.g. assign will match assignment

Examples:
- ```find read book```
- ```find assign```

Expected output:
<br>
![find_half_output.png](find_half_output.png)

### Exiting the application : ```bye```

The application will display the exit message and automatically close after a 5-second delay.

Expected output:
<br>
![bye_output.png](bye_output.png)

<br>
--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                      |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `todo <task description>` <br> e.g. `todo read book` <br> `deadline <task description> /by <date/time>` <br> e.g. `deadline assignment /by 30-Dec-2025` <br> `event <description> /from <start> /to <end>` <br> e.g. `event project zoom /from 2024-12-02 12:12 /to 2024-12-03 13:30` |
| **List**   | `list`                                                                                                                                                                                                                                                                                |
| **Delete** | `delete <task number>`<br> e.g., `delete 3`                                                                                                                                                                                                                                           |
| **Mark**   | `mark <task number>`<br> e.g., `mark 3`                                                                                                                                                                                                                                               |
| **Unmark** | `unmark <task number>`<br> e.g., `unmark 3`                                                                                                                                                                                                                                           |
| **Find**   | `find <task keyword>`<br> e.g., `find quiz`                                                                                                                                                                                                                                           |
| **Exit**   | `bye`                                                                                                                                                                                                                                                                                 |

