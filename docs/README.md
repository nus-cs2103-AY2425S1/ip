# Astor User Guide

// ![Product screenshot of Astor](https://raw.githubusercontent.com/yyihaoc/ip/refs/heads/master/docs/Ui.png)

## Why choose Astor?
Astor is your go-to app for recording tasks and remembering deadlines for you!
It is free to install and easy to pick up!

## Features

### 1. Adding task without deadlines

- Add a "todo" task in our app using `todo (name_of_task)`
- Example: `todo read Harry Potter`

### 2. Adding task with deadlines

- Add a "deadline" task in our app using `deadline (name_of_task) /by (deadline)`
- Example: `deadline Algebra Assignment 1 /by 20/12/2023`

### 3. Adding events

- Add a "event" task in our app by `event (name_of_event) /from (start_date) /to (end_date)`
- Example: `event party /from 12/12/2012 /to 13/12/2012`

### 4. Mark and Unmark tasks

- Mark todos, deadlines, events as completed using `mark [indexes_of_tasks]`
- Unmark todos, deadlines, events as completed using `unmark [indexes_of_tasks]`
- Example: `mark 1 2 3` marks tasks 1, 2, 3 in the list as completed.

### 5. Delete tasks
- Delete tasks using `delete [index_of_task]`
- Example: `delete 1` deletes the first task in the list.

### 6. View all tasks
- See all your tasks by using `list`
- Returns a well-formatted list of tasks in the order they are added, and includes information about their type of 
task, completion status, task description, and dates (varies).

### 7. Find task
- Find your task using `find (keyword)`
- Returns the task with task description that includes the keyword

## Installation

### Steps:
1. Download the jar file
2. Open your terminal or command prompt, navigate to the directory where the JAR file is located, 
and run the following command: `java -jar astor.jar`

## Contributing
If you’d like to contribute to this project:

1. Fork the repository
2. Create a new branch (git checkout -b feature/new-feature)
3. Commit your changes (git commit -m 'Add new feature')
4. Push to the branch (git push origin feature/new-feature)
5. Open a pull request