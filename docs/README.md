# Friday User Guide

![UI](Ui.png)

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [Usage](#usage)
    - [Adding a Task](#adding-a-task)
        - [Types of Tasks](#types-of-tasks)
    - [Viewing Tasks](#viewing-tasks)
    - [Deleting a Task](#deleting-a-task)
    - [Marking a Task](#marking-a-task)
    - [Unmarking a Task](#unmarking-a-task)
    - [Sorting Deadlines](#sorting-deadlines)
    - [Searching Tasks](#searching-tasks)
    - [Finding Tasks](#finding-tasks)
    - [Exiting the Application](#exiting-the-application)
- [File Structure](#file-structure)
- [Exception Handling](#exception-handling)
- [Task Format Stored in File](#task-format-stored-in-file)
- [Conclusion](#conclusion)

## Introduction
Welcome to the Friday Task Manager! This application helps users manage their tasks efficiently. 
You can add, delete, and view tasks through a user-friendly interface.

## Features
- **Add Tasks**: Add new tasks(Todo, Deadline, Event) with specific details.
- **Delete Tasks**: Remove tasks that are no longer needed.
- **View Tasks**: Display all tasks in a list format.
- **Save Tasks**: Automatically save tasks to a file for persistence.
- **Mark Tasks**: Mark tasks that are done or not done.
- **Unmark Tasks**: Unmark tasks that are done or not done.
- **Sort Tasks**: Sorts tasks based on date in ascending order.
- **Search Tasks**: Search outstanding tasks based on date
- **Find Tasks**: Search tasks based on the name.
- **Exit**: Exit the application.

## Getting Started

### Prerequisites
- openjdk version "17.0.11" 2024-04-16 LTS or higher

### Installation
1. Download the latest JAR file from the [releases page](https://github.com/mingyang143/ip/releases).
2. Save the JAR file to your desired location.

### Running the Application
1. Open a terminal.
2. Navigate to the directory where you saved the JAR file.
3. Run the application using the following command:
    ```sh
    java -jar friday.jar
    ```

## Usage
<span style="color:red"> The main way for users to write commands is at the input field of either the GUI window or CLI terminal.</span>

### Adding a Task

###### Types of Tasks
- **Todo Task**: A simple task with a description.
- **Deadline Task**: A task with a description and a deadline date.
- **Event Task**: A task with a description, a start date, and an end date.

There are different types of tasks and users need to use different commands depending on the type:
1. For todo tasks the command is: todo \<description>. 
    ```sh
    todo Task 1
    ```
2. For deadline tasks the command is: deadline <description> /by \<date> where the date must be in YYYY-MM-DD format. 
    ```sh
    deadline Task 2 /by 2024-10-10
    ```
3. For event tasks, the command is: event \<description> /from \<start> /to \<end> where the /from and /to dates must 
be in YYYY-MM-DD format and the /from date must be before the /to date. 
    ```sh
    event Task 3 /from 2024-09-20 /to 2024-09-23
    ```
   
### Viewing Tasks
to view a task, run the command: list. 
```sh
list
```

### Deleting a Task
To delete a task, find out the task index number for the task to be deleted first by viewing all the tasks. 
Then after knowing the index number you can run the command: delete \<taskIndex>
```sh
delete 1
```

### Marking a Task
To mark a task, find out the task index number for the task to be deleted first by viewing all the tasks. 
Then after knowing the index number you can run the command: mark \<taskIndex>
```sh
mark 1
```

### Unmarking a Task
To unmark a task, find out the task index number for the task to be deleted first by viewing all the tasks.
Then after knowing the index number you can run the command: unmark \<taskIndex>
```sh
unmark 1
```

### Sorting Deadlines
To sort the deadlines in ascending order of its due date, run the command: sort.
```sh
sort
```

### Searching Tasks
To search for outstanding tasks based on date, run the command: search \<date> where the date must be in 
YYYY-MM-DD format. 
```sh
search 2024-10-10
```

### Finding Tasks
To find tasks based on a keyword that matches the description, run the command: find \<keyword>.
```sh
find Task
```

### Exiting the Application
To exit the application, run the command: bye.
```sh
bye
```

## File Structure
- `src/main/java`: Contains the Java source files.
- `src/main/resources`: Contains the FXML files for the GUI.
- `data`: Contains the task list file (`fridayTaskList.txt`).

## Exception Handling
The application handles various exceptions, such as:
- **FridayException**: Custom exception for handling specific Friday errors.
- **IOException**: Handles errors related to file operations.
- **DateTimeParseException**: Handles errors related to date parsing.

## Task format stored in file
Tasks are stored automatically in the `data/fridayTaskList.txt` file in the following format:

- D 0 Task20 /by 2025-10-10
- T 0 task 21
- E 1 task22 /from 2024-09-20 /to 2024-09-23

## Conclusion
Thank you for using the Friday Task Manager! We hope this guide helps you get the most out of the application.
For any issues or suggestions, please contact the developer at [e1121724@u.nus.edu](mailto:e1121724@u.nus.edu).