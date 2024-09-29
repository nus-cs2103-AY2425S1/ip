# Pixy User Guide

Welcome to Pixy ChatBot! This guide will help you get started with using Pixy ChatBot and understanding its various features.

## Introduction

Pixy ChatBot is a simple, interactive chatbot designed to help manage your tasks effeciently. 
![Pixy ChatBot](Ui.png)

## Features

### 1. Adding Tasks

Pixy ChatBot supports three types of tasks:
- **Todo**: A basic task with no date/time attached.
- **Deadline**: A task that needs to be completed by a specific date and time.
- **Event**: A task that has a start and end time.

### Commands:
- Add a todo: `todo Task description`
- Add a deadline: `deadline Task description /by DD/M/YYYY HHmm`
- Add an event: `event Task description /from from DD/M/YYYY HHmm /to DD/M/YYYY HHmm`

#### Example Commands:
- Add a todo: `todo Read a book`
- Add a deadline: `deadline Submit assignment /by 20/9/2024 2359`
- Add an event: `event Team meeting /from from 18/9/2024 1400 /to 18/9/2024 1600`

### Expected Outputs:
- Add a todo:
  ```
  Added new todo: Read a book. You now have 1 task(s).
  ```
- Add a deadline:
  ```
  Added new deadline: Test deadline (by: 20/09/2024 1200). You now have 1 task(s).
  ```
- Add an event:
  ```
  Added new event: Team meeting (from: 18/9/2024 1400 to: 18/9/2024 1600). You now have 1 task(s).
  ```

### 2. Listing All Tasks

To see all the tasks you've added so far, use the `list` command.

#### Command:
- `list`
  
### 3. Marking and Unmarking Tasks

You can mark tasks as done or not done.

#### Commands:
- Mark a task as done: `mark TaskNumber`
- Unmark a task as not done: `unmark TaskNumber`

#### Example Commands:
- Mark a task as done: `mark 1`
- Unmark a task as not done: `unmark 1`

#### Expected Outputs:
- mark 1:
  ```
  Task marked as done: Read book.
  ```
- unmark 1:
  ```
  Task unmarked: Read book.
  ```

### 4. Finding Tasks

You can search for tasks by keywords.

#### Command:
- Find tasks: `find Task description`
  
#### Example Command:
- Find tasks: `find book`

#### Expected Outputs:
- find book:
  ```
  Here are the matching tasks in your list:
  1. [T][] Read book.
  ```

### 5. Deleting Tasks

Remove tasks that you no longer need.

#### Commands:
- Delete a task: `delete TaskNumber`
  
#### Example Command:
- Delete a task: `delete 1`

#### Expected Outputs:
- Delete a task:
  ```
  Task deleted: Read book. You now have 0 task(s).
  ```

### 7. Exiting the Chatbot

When you're done, you can exit the chatbot with a simple command.

#### Command:
- `bye`

## How to Use Pixy Chatbot

1. Launch the chatbot application.
2. Enter your commands into the text field at the bottom and press the "Send" button.
3. Interact with the chatbot by using the commands listed above.
4. On clicking the exit button the chatbot closes.

## Tips for Using Kobe Chatbot

- Make sure to use the correct date format `DD/M/YYY HHmm` when adding deadlines or events.
- You can view all your tasks anytime using the `list` command.

## Screenshot

Below is a screenshot of the Pixy ChatBot interface:

![Pixy ChatBot](Ui.png)

### Happy task managing with Pixy ChatBot!
