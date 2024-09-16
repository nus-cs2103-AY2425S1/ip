# The BotFather User Guide

![The BotFather Screenshot](Ui.png)

The BotFather is a task management application designed to help users efficiently manage 
their tasks through a user-friendly graphical interface. 
The application allows you to add, mark, unmark, delete, and view tasks with ease. 
With its intuitive design, 
you can keep track of your deadlines, events, and to-do items seamlessly.

## Features List

### 1. **Add Tasks**
- Users can add three types of tasks: `todo`, `deadline`, and `event`.
- Task format: `[Task Type] [Status] Description (Date, if applicable)`.

### 2. **Mark Tasks as Done**
- Mark tasks as completed using the task number.

### 3. **Unmark Tasks**
- Undo the completion of a task using the task number.

### 4. **Delete Tasks**
- Remove a task from the list using the task number.

### 5. **List All Tasks**
- Display the current task list with task numbers, types, statuses, and dates.

### 6. **Find Tasks by Keyword**
- Search for tasks that match a specific keyword or phrase.

### 7. **Exit Application**
- Close the application with a command.

---

## Exception Handling

The BotFather is equipped with a robust error-handling mechanism to ensure smooth and 
reliable operation. If any issues arise during task processing, 
they are managed gracefully, preventing crashes and providing 
user-friendly error messages.

### Common Errors

1. **Unknown Command**  
   When the user inputs an unrecognized command, an error message is returned.

2. **Invalid Task Number**  
   When the user attempts to mark, unmark, or delete a task that doesn't exist, 
   an error is shown.


3. **Missing Arguments**  
   If a required argument is not provided for certain commands, 
    The BotFather alerts the user.


4. **File Access Issues**  
   If there's a problem reading from or writing to the data file 
    (e.g., incorrect file path or file corruption), 
    an error message is displayed, but the application continues running.

These exceptions ensure that even when users make mistakes or 
encounter file-related issues, the program continues to 
function without crashing, while providing informative feedback 
for correcting errors.


---

## Adding Deadlines

To add a deadline task, use the `deadline` command followed by the description of the 
task and the deadline date. This action creates a new deadline task and adds it to your 
task list.

### Example Usage

```commandline
deadline return book report /by 12-09-2024 23:59
```
### Expected Output

```
Look how they massacred my boy
[D][ ] Finish project report (by: 12 Sep 2024, 23:59)
```
## Adding Event

To add an Event task, use the `event` command followed by the description of the task and the from and to date. This action creates a new event task and adds it to your task list.

## Adding Todo

To add a todo task, use the `todo` command followed by the description of the task. This action creates a new todo task and adds it to your task list.

## Marking Tasks

To mark a task as completed, use the `mark` command followed by the task number. This action updates the task's status to completed.

### Example Usage

```commandline
mark 2
```

### Expected Output

```
It will be done
[D][X] Finish project report (by: 12 Sep 2024, 23:59)
```


## Unmarking Tasks

To unmark a task, use the `unmark` command followed by the task number. 
This action reverts the task's status to not completed.


## Deleting Tasks

To delete a task, use the `delete` command followed by the task number. 
This action removes the task from your task list.


## Finding Tasks

To search for tasks that match a specific keyword, use the `find` command 
followed by the keyword. This action displays all tasks containing the keyword.

## Listing the Tasks

To list all tasks, use the `list` command This action displays all tasks 
in your task-list.


## Exiting the Application

To exit the application, use the `bye` command. This will close the application.

