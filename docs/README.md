# Stobberi User Guide

![Stobberi UI](https://github.com/ahmadsyuaib/ip/raw/master/docs/Ui.png?raw=true)

Stobberi is a smart, command-driven task manager built for speed and simplicity. Optimized for the Command Line Interface (CLI) and paired with an intuitive GUI, it makes organizing, tracking, and completing tasks effortless. With just a few commands, you’ll stay on top of your tasks and boost your productivity like never before.

---

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Display the current list: `list`](#display-the-current-list-list)
  - [Create a task to do: `todo`](#create-a-task-to-do-todo)
  - [Exits the application: `bye`](#exits-the-application-bye)
  - [Create a task with a deadline: `deadline`](#create-a-task-with-a-deadline-deadline)
  - [Create an event task: `event`](#create-an-event-task-event)
  - [Mark a task: `mark`](#mark-a-task-mark)
  - [Unmark a task: `unmark`](#unmark-a-task-unmark)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Search for a task: `find`](#search-for-a-task-find)
  - [Find tasks on a certain date: `date`](#find-tasks-on-a-certain-date-date)
  - [Clear all the tasks: `[COMING SOON]`](#clear-all-the-tasks-coming-soon)
  - [Edit a current task: `[COMING SOON]`](#edit-a-current-task-coming-soon)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java 17 or above installed on your computer. [Download Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)

2. Download the latest `.jar` file from [here](https://github.com/ahmadsyuaib/ip/releases).

3. Copy the file to the folder you want to use as the *home folder* for Stobberi.

4. Open a command terminal, `cd` into the folder where you placed the `.jar` file, and run the following command to start the application:
   ```bash
   java -jar stobberi.jar

---

## Features

> **Note about command format:**
> - Words in `UPPER_CASE` represent parameters you need to provide.
> - Commands like `list`, `bye` and `?` do not require any parameters. 
> - Providing extra parameters will result in an invalid command.  
>   - For example, entering `list 123` is incorrect and will not work.
> - Keep to 1 spacing strictly between words.
> - All words are case-sensitive.
> - For all date-time, the input will be expecting the date-time format to be in `dd-MM-yyyy HHmm'hrs'`
>   - Example: `03-02-2025 1800hrs`

## Display the current list: `list`
Shows a `list` of all current tasks.  

- Any words or numbers after the word `list` will result in the command not being called.
- List will be displayed sorted by when you added the task.

**Usage:** `list`

## Create a task to do: `todo`
Creates a new task of type `todo` and add it to the current list.  

- Any numbers or special characters added will be assumed to be part of the task description.

**Usage:** `todo TASK_DESCRIPTION`

## Exits the application: `bye`
Exits the application and saves the current list.  

- Saves the current list to a local file.
- Any words or numbers after the word `bye` will result in the command not being called.

**Usage:** `deadline TASK_DESCRIPTION /by DATE_TIME`

## Create a task with a deadline: `deadline`
Creates a new task of type `deadline` which has a deadline.  

- Any numbers or special characters added will be assumed to be part of the task description.
- Only one "/by" is expected. If there are multiple, it will be considered as an invalid date input.

**Usage:** `deadline TASK_DESCRIPTION /by DATE_TIME`

## Create an event task: `event`
Creates a new task of type event which has a start date-time and end date-time.  

- Any numbers or special characters added will be assumed to be part of the task description.
- Only one "/from" and one "/to" are expected. If there are multiple, it will be considered as an invalid date input.

**Usage:** `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME`

## Mark a task: `mark`
Marks a task as completed.  

- A completed task is marked with [X] when the list is displayed.

**Usage:** `mark TASK_NUMBER`

## Unmark a task: `unmark`
Marks a task as incomplete.  

- An incomplete task is marked with [ ] when the list is displayed.
- A task is assumed to be incomplete when created

**Usage:** `unmark TASK_NUMBER`

## Delete a task: `delete`
Deletes a task on the current list. 

- The task number is based on the number displayed when `list` is called.

**Usage:** `delete TASK_NUMBER`

## Search for a task: `find`
Searches for tasks that contain a keyword.  

- This searches for the keyword in the description.
- If the keyword is within a word, the task will also be displayed
  - For example, if the task description is 'complete assignment', calling `find sign` will display this task.

**Usage:** `find KEYWORD`

## Find tasks on a certain date: `date`
Lists all tasks scheduled on a specific date.  

- For events, they will display all the tasks whereby the input date falls during the event.
- For deadline, they will display all the tasks whereby the input date falls on the deadline date.

**Usage:** `date DATE`

## Display list of commands: `?`
Displays a helping list of all available commands.
**Usage:** `?`

## Clear all the tasks `[coming soon]`
Clears all tasks.

## Edit a current task `[coming soon]`
Edits a task on the list

---

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Install the app on the new computer, then overwrite the newly created empty data file with the data file from your previous Stobberi home folder.

---

## Known Issues

1. If you move the application to a secondary screen and later switch to using only the primary screen, the GUI might open off-screen. To fix this, delete the `preferences.json` file before relaunching the application.

---

## Command Summary

| Action   | Format and Examples                                                                                                                     |
|----------|-----------------------------------------------------------------------------------------------------------------------------------------|
| List     | `list`                                                                                                                                  |
| Todo     | `todo TASK_DESCRIPTION` <br> Example: `todo complete homework`                                                                          |
| Bye      | `bye`                                                                                                                                   |
| Deadline | `deadline TASK_DESCRIPTION /by DATE_TIME` <br> Example: `deadline assignment /by 12-12-2024 2359hrs`                                    |
| Event    | `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME` <br> Example: `event assignment /from 03-12-2024 0500hrs /to 12-12-2024 1900hrs` |
| Mark     | `mark TASK_NUMBER` <br> Example: `mark 1`                                                                                               |
| Unmark   | `unmark TASK_NUMBER` <br> Example: `unmark 2`                                                                                           |
| Delete   | `delete TASK_NUMBER` <br> Example: `delete 3`                                                                                           |
| Find     | `find KEYWORD` <br> Example: `find complete`                                                                                            |
| Date     | `date DATE` <br> Example: `date 07-12-2024`                                                                                             |
| Help     | `?`                                                                                                                                     |
| Clear    | [coming soon]                                                                                                                           |
| Edit     | [coming soon]                                                                                                                           |

