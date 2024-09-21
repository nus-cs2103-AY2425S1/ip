# Espresso User Guide

Welcome to Espresso User Guide. Espresso is a task management application which makes handling your tasks easier.With its straightforward commands and handy graphical user interface, users can add, delete, mark, unmark, and search for tasks easily. spresso helps you manage deadlines, events, and to-do items, ensuring that you stay productive and organised.
![Product Screenshot](Ui.png)

## List of Features

1. **Add Tasks**
Espresso allows user to add three types of tasks : _Todo, Deadline and Event_
For example :
```
todo team project
deadline submission /by 20-09-2024
event recess /from 23-09-2024 /to 30-09-2024
```

2. **Delete Tasks**
Espresso also allows users to remove tasks from their list.
For example :
```
delete 2
```

3.  **Mark Tasks as Done**
Users can mark tasks as done using the respective task number. 
For example :
```
mark 1
```

4. **Unmark Tasks**
Users can unmark tasks using the respective task number.
For example :
```
unmark 1
```

5. **List Tasks**
Users can also view their task list with the task types, their statuses and dates.
For example :
```
list
```

6. **Find Tasks**
Users can search for tasks using a specific phrase or keyword.
For example :
```
find meet
```

7. **Exiting the application**
Users can exit the application with the `bye` command.
```

bye
```

## Exception Handling
Espresso is designed with a strong error-handling system to ensure smooth operation. When any issues arise during task processing, Espresso handles them gracefully, providing clear and helpful error messages.

### Common Type of Errors

1. **Invalid Command**
If a user enters a command that Espresso does not recognize, it will notify with an error message, helping to correct the command.

2. **File Parsing Issues**
In case Espresso encounters issues in accessing files, it will print an error message.

## Saving Functionality
Espresso saves all tasks after you exit the application for future reference.

### Here is what your task list might look like
```
[D][ ] Finish project (by: 20 Sep 2024)
```

## Conflict Functionality
Espresso is equipped to detect if a task being added clashes with another task in the list and inform the user of the same.

## Exiting Espresso Bot
In order to exit the bot and save your changes just make use of the `bye` command.
