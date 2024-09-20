# Yoda User Guide
**A Star Wars themed chat-bot for you to manage your tasks today!**
![Screenshot of Yoda GUI](https://maertan.github.io/ip/Ui.png)


## Installing Yoda
1. Ensure that your computer runs Java 17. (run `java -version` in terminal to ensure that your terminal is using Java 17)
2. Download the latest `yoda.jar` file.
3. Open a terminal window at the folder containing `yoda.jar`. 
4. Run the command `java -jar yoda.jar`

## Features

### 1. Adding tasks
There are three types of tasks you can manage using Yoda - todos, deadlines, and events

#### a. Todos
To add a todo, type `todo {description}` or `t {description}`. A todo task will be added to your list.

#### b. Deadlines
To add a deadline, type `deadline {description} /by {date}` or `dl {description} /by {date}`. A deadline task will be added to your list.
- The format of the date should be `yyyy-mm-dd`

#### c. Events
To add an event, type `event {description} /from {datetime} /to {datetime}` or `e {description} /from {datetime} /to {datetime}`. An event task will be added to your list.
- The format of the datetime should be `yyyy-mm-dd HHmm`. 

### 2. Deleting tasks
To delete a task, type `delete {index}` or `d {index}`. For example `delete 1` deletes the first task.

### 3. Listing tasks
To list all tasks, type `list` or `l`. A list of all tasks will be shown

### 4. Marking and Unmarking tasks
To mark a task as complete, type `mark {index}` or `m {index}`. For example `mark 1` marks the first task as done.

Likewise, to mark a task as incomplete type `unmark {index}` or `um {index}`.

### 5. Finding tasks
To find any tasks containing specific words, type `find {search}`. A list of matching tasks will be shown.

### 6. Saying goodbye
To end the chat, type `bye`. Yoda will say goodbye to you.
