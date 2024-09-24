# Ponder Pika - User Guide

![Ui.png](Ui.png)

> A project is complete, when it starts working for you, rather than you working for it - Scott Allen

Welcome to PonderPika, your friendly task management system designed to help you keep track of all your tasks 
effortlessly. It is optimized for use **via a Command Line Interface (CLI) while still having the benefits of 
a Graphical User Interface (GUI)**. Whether you’re managing personal projects, deadlines, or events, PonderPika 
offers a straightforward and user-friendly interface for organizing your to-do lists with ease.
 
* Quick Start
* Features
    - adding a todo: `todo`
    - adding a deadline: `deadline /by`
    - adding an event: `event /from /to`
    - listing all tasks: `list`
    - mark a task: `mark`
    - unmark a task: `unmark`
    - delete a task: `delete`
    - find a task: `find`
    - exiting the program: `bye`
    - **Additional feature:** set priority to a task: `priority`

## Quick Start
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest .jar file from here.
3. Copy the file to the folder you want to use as the home folder for your AddressBook. 
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.

A GUI similar to the below should appear in a few seconds. Ponder Pika is ready for you with a greeting message.
![GUIstart.png](..%2Fsrc%2Fmain%2Fresources%2Fimages%2FGUIstart.png) 
5. Type the command in the command box and press Enter or Send button to execute it.
   Some example commands you can try:

  - list : Lists all tasks currently in the list.

  - todo Eat food : Adds a todo type task with description as **'eat food'**.

  - delete 3 : Deletes the 3rd task shown in the current list.

  - mark 2 : Marks the 2nd task as done in the list.

  - bye : Exits the app.

Refer to the Features below for details of each command.

## Features

### Add a Todo: `todo`
Adds a task of type todo in the list of tasks.

FORMAT: `todo description_of_task`

EXAMPLES:
* `todo Eat food`
* `todo Sing a song`

EXPECTED OUTPUT:
```
Pika! I have added your todo: Sing a song
Peek-A-Boo! We have 2 tasks in our list 
```

### Add a Deadline: `deadline`
Adds a task of type deadline in the list of tasks.

FORMAT: `deadline description_of_task /by due_date_and_time`

EXAMPLES:
* `deadline Assignment /by 22/09/2024 23:59`
* `deadline Project /by 20/11/2024 23:59`

EXPECTED OUTPUT:
```
Pika! I have added a deadline: Project
Peek-A-Boo! We have 4 tasks in our list 
```

### Add an Event: `event`
Adds a task of type event in the list of tasks.

FORMAT: `event description_of_task /from due_date_and_time /to due_date_and_time`

EXAMPLES:
* `event Bithday party /from 21/10/2024 00:00 /to 21/10/2024 23:59`
* `event Navratri Night /from 03/10/2024 00:00 /to 12/10/2024 23:59`

EXPECTED OUTPUT:
```
Pika! I have added your event: Navratri Night
Peek-A-Boo! We have 6 tasks in our list 
```

### List all tasks: `list`
Lists all the tasks currently available in the list

FORMAT: `list`

EXPECTED OUTPUT:
```
Here are the following tasks:
1. [T][] Eat food
2. [T][] Sing a song
3. [D][] Assignment (by: 22 Sep 2024 23:59)
4. [D][] Project (by: 20 Nov 2024 23:59)
5. [E][] Bithday party (from: 21 Oct 2024 00:00 to: 21 Oct 2024 23:59)
6. [E][] Navratri Night (from: 3 Oct 2024 00:00 to: 12 Oct 2024 23:59)
```

### Mark a task: `mark`
Marks a specified task as done. While printing, it shows as "X" for tasks marked as done.

FORMAT: `mark index_number_of_task`

EXAMPLES:
* `mark 1`
* `mark 3`
* `mark 4`

EXPECTED OUTPUT:
```
Task 4 has been marked as done
```

### Unmark a task: `unmark`
Unmarks a specified task in the list. While printing, it shows as "" for tasks undone.

FORMAT: `mark index_number_of_task`

EXAMPLES:
* `unmark 1`
* `unmark 3`

EXPECTED OUTPUT:
```
Task 5 has been unmarked!
```

### Delete a task: `delete`
Deletes a specified task in the list.

FORMAT: `delete index_number_of_task`

EXAMPLES:
* `delete 1`
* `delete 6`

EXPECTED OUTPUT:
```
Your task has been deleted.
Peek-A-Boo! We have 4 tasks in our list
```

### Find a task: `find`
Finds a task in the list with reference to a specified keyword.

FORMAT: `find keyword`

EXAMPLES:
* `find song`
* `find Project`

EXPECTED OUTPUT:
```
[D][] Project (by: 20 Nov 2024 23:59)
```

### Exit the program: `bye`
Exits the program with command "bye". Closes the GUI after 1 second of displaying Exited.

FORMAT: `bye`

EXPECTED OUTPUT:
```
Exited!
```

### **Additional feature:** Set priority to a task: `priority`
Sets a priority level to a specified task in the list. Contains levels: HIGH, MEDIUM, LOW, NONE (default).

FORMAT: `priority priority_level index_number_of_task`

EXAMPLES:
* `priority H 3`
* `priority M 2`

EXPECTED OUTPUT:
```
Priority set to M for Assignment
```

## Saving the data
Ponder Pika helps you to save data automatically while we exit the program.
It helps you from the effort and time to save it manually!:+1:

## Known Issues
1. **When entering a duplicate task**, currently it does not allow and throws a DuplicateTaskException. 
However, it should allow for deadline and event where description could be same but dates and time are different.
2. **Send button overlaps** a bit of user input text field if window not widened.

## Command Summary

| ACTION         | FORMAT AND EXAMPLES                                                                                                                        |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| add todo       | todo task_description, eg: todo tango dance                                                                                                |
| add deadline   | deadline task_description /by 21/10/2024 12:00, <br/>eg: deadline Assignment /by 22/09/2024 23:59                                          |
| add event      | event task_description /from 1/12/2024 23:59 /to 2/12/2024 23:59, <br/>eg: event Bithday party /from 21/10/2024 00:00 /to 21/10/2024 23:59 |
| list all tasks | list                                                                                                                                       |
| mark a task    | mark index_number, eg: mark 5                                                                                                              |
| unmark a task  | unmark index_number, eg: unmark 10                                                                                                         |
| delete a task  | delete index_number, eg: delete 6                                                                                                          |
| find a task    | find keyword, eg: find party                                                                                                               |
| exit program   | exit                                                                                                                                       |
| set priority   | priority priority_level index_number, eg: priority H 4                                                                                     |

