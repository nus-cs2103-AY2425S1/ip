# WansBot User Guide

![UI](https://github.com/user-attachments/assets/ece60c9f-0449-430d-9b9a-74d7683dc570)

WansBot is a simple **chat bot app for managing your daily tasks**, complete with a Graphical User Interface (GUI)! Other than that it can also **memorise answers to questions that you have that you may want remembered**. Since we all get forgetful sometimes it's nice to have a backup plan in the reliable WansBot!

- [Quickstart](#quickstart)
- [Features](#features)
  - [Adding ToDos: `todos`](#adding-todos-todos)
  - [Adding Tasks with Deadlines: `deadline`](#adding-tasks-with-deadlines-deadline)
  - [Adding Tasks with a start and end date: `event`](#adding-tasks-with-a-start-and-end-date-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list).
  - [Marking tasks complete: `mark`](#marking-tasks-complete-mark)
  - [Unmarking tasks: `unmark`](#unmarking-tasks-unmark)
  - [Removing tasks: `remove`](#removing-tasks-remove)
  - [Finding tasks by date: `findtask`](#finding-tasks-by-date-findtask)
  - [Finding tasks by name: `findname`](#finding-tasks-by-name-findname)
  - [Saving tasks: `save`](#saving-tasks-save)
  - [Saving questions: `question`](#saving-questions-question)
  - [Answering saved questions: `answer`](#answering-saved-questions-answer)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
 - [Command Summary](#command-summary)

***

## QuickStart

1. Ensure you have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed in your computer.
2. Download the latest .jar file [here](https://github.com/bigismols/ip/releases).
3. Open your terminal, `cd` into the folder the jar file is in and use `java -jar wansbot.jar` command to run the application. A GUI similar to the one you see at the top of this page should appear.
4. Type in your command in the command box and press Enter to Execute it! For more details on commands visit [Features Section](#features)

***

## Features

> [!NOTE]
Everything in brackets() are necessary but should be replcaed with a name. E.g. todos (task_name) should be something like todos Do homework.
All commands are case insensitive.

## Adding ToDos: `todos`

Adds a todo task to the user's task list.

Format: `todos (task_name)`

Examples:
- `todos read`
- `todos study for exams`

***

## Adding Tasks with Deadlines: `deadline`

Adds a task with a deadline

Format: `deadline (task_name) /by (date)`

> [!IMPORTANT]
Your (date) here must be in the format YYYY-MM-DD. WansBot will tell you that your deadline is formatted wrongly otherwise!

Examples:
- `deadline read /by 2024-01-01`
- `deadline study bio /by 2020-11-15`

***

## Adding Tasks with a start and end date: `event`

Adds a task to the user's task list with a start date and an end date.

Format: `event  (task_name) /from (start_date) /to (end_date)`

Examples:
- `event recess week /from 2024-09-20 /to 2024-09-27`
- `event exam period /from 2024-11-15 /to 2024-11-30`

***

## Listing all tasks: `list`

Lists all the current tasks in user's task list currently. All tasks are listed with 2 square brackets before the task name and additional information. The first square bracket contains the type of task e.g. todos, deadline, or event and the second square bracket indicates whether the task has been completed or not. X means complete. Marking will be discussed later in this guide. Below is an example of how todos, deadlines, and events would be shown when `list` is used.

![image](https://github.com/user-attachments/assets/c281b6a9-b9fa-4484-9e4d-81e84f2547e0)

Format: `list`

***

## Marking tasks complete: `mark`

Marks a task as complete.

Format: `mark (task_number)

Examples:
- `mark 1`
- `mark 10`

> [!NOTE]
> Task number will correspond to the number on the left of the list when `list` is used.

***

## Unmarking tasks: `unmark`

Unmarks a task and label it as incomplete ie. no X.

Format: `unmark (task_number)`

Examples:
- `unmark 2`
- `unmark 11`

***

## Removing tasks: `remove`

Removes task from the user's task list.

Format: `remove (task_number)`

Examples:
- `remove 3`
- `remove 13`

> [!NOTE]
> This is not to be confused with marking and unmarking. This COMPLETELY removes the task from the task list and **will also shift task numbers after the removed task**.
> Ensure that you do a `list` command to see the updated task numbers.

***

## Finding tasks by date: `findtask`

Finds a task that falls on or within a particular date. Todos will **NOT** be displayed here. Display format is similar to [list](#listing-all-tasks-list).

Format: `findtask (date)`

Examples:
- `findtask 2023-04-12`
- `findtask 2023-12-27`

***

## Finding tasks by name: `findname`

Finds a task whose name contains the string after findname.

Format: `findname (any_string)`

Examples:
- `findname rea`
- `findname read`

***

## Saving tasks: `save`

Saves the current user task list to `data/usertasklist.txt`. Data will be in the same directory as `src` folder. Data will be saved in the format below. If data folder or usertasklist.txt does not exist, they will be created.

![image](https://github.com/user-attachments/assets/f65c677e-f7fb-45ce-80e3-e95fefea5836)

Format: `save`

> [!CAUTION]
> Running `save` command will **OVERWRITE** any existing user's task list. Be careful before you save!

***

## Saving questions: `question`

Saves a question for WansBot to answer, along with the answer for that question. *Will not answer the question**

Format: `question (your_question_ending_with_a_question_mark) (your_answer_for_that_question)`

Examples:
- `question What is my name? Adib'
- 'question What is the name of my favourite pet? Evesters`

> [!IMPORTANT]
> Questions are case sensitive! WansBot needs the **exact** character of your question, or will not be able to answer it later on.

***

## Answering saved questions: `answer`

Answers the question posed by user. Question **must have been saved beforehand with `question`.

Format: `answer (your_saved_question)`

Examples:
- `answer What is my name?`
- `answer What is the name of my favourite pet?`

> [!IMPORTANT]
> Similarly, questions in `answer` need to be **exact**. They are case sensitive and need to end with `?` character.

***

## Exiting the program: `bye`

Exits the program.

Format: `bye`

***

## Command Summary

| Action | Format, Examples |
| -------| -----------------|
| `todos`| `todos (task_name)` e.g. `todos study for exams`|
| `deadline` | `deadline (task_name) /by (date)` e.g. `deadline study bio /by 2020-11-15`|
| `event` | `event  (task_name) /from (start_date) /to (end_date)` e.g. `event exam period /from 2024-11-15 /to 2024-11-30`|
| `list` | `list` |
| `mark` | `mark (task_number)` e.g. `mark 1` |
| `unmark` | `unmark (task_number)` e.g. `unmark 2` |
| `remove ` | `remove (task_number)` e.g. `remove 3` |
| `findtask` | `findtask (date)` e.g. `findtask 2023-04-12` |
| `findname` | `findname (any_string)` e.g. `findname rea` |
| `save` | `save` |
| `question` | `question (your_question_ending_with_a_question_mark) (your_answer_for_that_question)` e.g. `question What is my name? Adib` |
| `answer` | `answer (your_saved_question)` e.g. `answer What is my name?` |
| `bye` | `bye` |
