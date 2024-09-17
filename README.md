# Monique User Guide

## Introducing Monique [![version](https://img.shields.io/badge/version-0.2-yellow.svg)](https://semver.org)

#### Handle the fire and messiness of life with Monique :)
Monique is a desktop chat-bot app for managing your todo list

![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](Ui.png)

## Quick Guide to using Monique

### Ensuring required installation of java is fulfilled
This project uses Java 17. Ensure that your PC has this first

## Launching monique
#### 1. Download the latest 'Monique.jar' file linked [here](https://github.com/emmannyyy/ip/releases/download/v0.2/Monique.jar).
#### 2. Navigate to the folder where you downloaded the `Monique.jar` file.
#### 3. Run monique using the following command:
```bash
java -jar monique.jar
```
#### 4. The app will launch. To find out more about the commands, you can type "/commands"
Here is a few commands you can try:
- `list' : Monique will list all tasks on your deadlines
- 'find XX YY' : Monique will search through your tasks to find related tasks which contains "XX" and/or "YY"
- 'delete 1' : Monique will delete the first task on your task list

#### 5. Refer to the features below to get more in depth explanation of each command.

---
## Features

### List Tasks
To list all current tasks, type `list`. This command will display all tasks along with their statuses.
```bash
list
```
---
### Mark/Unmark a Task
To mark a task as complete, type `mark {task_number}`. For example, `mark 1` will mark task 1 as complete. Conversely, to unmark a task as incomplete, type `unmark {task_number}`. For example, `unmark 2` will mark task 2 as incomplete. Note that tasks are indexed starting from 1.

For example, you can do this to mark the first task as complete.
```bash
mark 1
```
---
### Add a Todo Task
To add a todo item, type `todo {description}`. For instance, `todo read a book` will add "read a book" to your tasks.

For example, this adds a todo task of 'homework'
```bash
todo homework
```
---
### Add a Deadline Task
To add a deadline item, type `deadline {description} /by {date} {optional_time}`. For example, `deadline submit report /by 12-12-2024 1700` adds a task with a deadline of December 12, 2024, at 5:00 PM. Valid date formats include:
- Days of the week ("Monday" to "Sunday" or abbreviations like "mon" to "sun")
- Date formats such as "DD/MM/YYYY", "DD-MM-YYYY"
- "tomorrow"

Valid time formats are:
- "HHmm" for 24-hour format
- "h:mm" or "h:m" for 12-hour format with am/pm

For example, this adds a deadline for 'homework' to the next day at 5pm
```bash
deadline homework /by tomorrow 5:00pm
```
---
### Add an Event Task
To add an event item, type `event {description} /from {start_date} {start_time} /to {end_date} {end_time}`. For example, `event meeting /from tomorrow 1400 /to 12-12-2024 1700` schedules an event starting from tomorrow at 2:00 PM and ending on December 12, 2024, at 5:00 PM.
For example, this adds an event for 'chilling w the homies' from the next day 6pm to 7pm
```bash
event chilling w the homies /from tomorrow 6:00pm /to tomorrow 11:59pm
```
---
### Delete a Task
To delete a task, type `delete {task_number}`. For example, `delete 3` will delete the task numbered 3.

For example, this deletes the first task
```bash
delete 1
```
---
### Find a Task
To find a task that contains a (few) key phrases, type `find {search_key 1} {search_key 2} ...`.
If you are only searching for 1 task, you may omit the other search keys.

For example, this command will search for tasks that contain the phrases "homework" or "assignment"
```bash
find homework assignment
```
---
### Exit the Application and Save data
To exit the application, type `bye`. This command will save your progress and close the application.
```bash
bye
```
---