# ChattyBuddy User Guide

![Screen of the product](Ui.png)

ChattyBuddy is a personal task management and productivity assistant built using Java and JavaFX. It allows users to manage tasks such as todos, deadlines, and events via a simple chat-like interface. ChattyBuddy helps you organize your tasks and keeps track of your progress, whether it's marking tasks as done, undoing actions, or retrieving previously saved tasks.

## Usage Examples

There are several command keywords: `todo`, `deadline`, `event`, `mark`, `unmark`, `list`, `delete` and `undo` and `bye`.

---
## Display list of tasks

After entering different tasks such as todos and deadlines, they will be saved to the
local storage. The users could retrieve by entering `list`

E.g. 
![img.png](img.png)

---
## Mark/Unmark tasks

The users could mark/unmark a certain task as done/not done.

Format: `mark/unmark (index of the task)`

E.g.
![img_1.png](img_1.png)
---
## Add a Todo task

The users could store a todo task to the chatbot.

Format: `todo (description of the task)`

E.g.
![img_4.png](img_4.png)
---
## Add a Deadline task

The users could store a deadline task to the chatbot.

Format: `todo (description of the task) /by (date)`

E.g.
![img_5.png](img_5.png)
---
## Add an Event task

The users could store an event task to the chatbot.

Format: `event (description of the task) /from (start date) /to (end date)`

Note: If no exact time is specified, the system will automatically set it to 12am

E.g.
![img_6.png](img_6.png)
---
## Delete tasks

The users could delete a certain task by entering the index of the task

Format: `delete (index of the task)`

E.g.
![img_2.png](img_2.png)
---
## Undo command

The users could undo their previous command (except for list and bye)

They could either enter `undo` which indicates that they only want to undo once

or they could enter `undo (number)` to indicate the number of times they want to undo the previous commands

E.g.
![img_3.png](img_3.png)

---
## Exit the application

To exit the application, user just need to enter `bye` and after 3 seconds the application will automatically end.