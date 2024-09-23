# Bonnie User Guide (18 / 9 / 2024)

![Ui.png](Ui.png)

# Are you looking for an app that keep tracks of your tasks and deadlines? 
**Look no further** as I present to you **Bonnie The Task Tracker**!

Bonnie is a GUI-based, interactive task management software developed in Java. 
The application allows users to create, delete, and organize different types of tasks efficiently. 

# Starting the application

Ensure you have Java 17 or above installed in your Computer.

Download the latest .jar file from here.

Copy the file to the folder you want to use as the home folder for your AddressBook.

Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.

# Features

It offers a number of functionalities that can be called using the following commands below.

| Command | Syntax | Description | Example                                 |
|---------|--------|-------------|-----------------------------------------|
| list | **list** | Displays the list of tasks that you have, with the task number | `list`                                  |
| mark/unmark | **mark/unmark {task number}** | Marks or unmarks that task as done. | `mark 5, unmark 2`                      |
| todo | **todo {task name}** | Adds a todo task into your task list. | `todo Buy groceries`                    |
| deadline | **deadline {task name} /by {YYYY-MM-DD}** | Adds a task with a deadline to your task list. | `deadline Finish report /by 2024-12-31` |
| event | **event {task name} /from {start} /to {end}** | Adds an event with a start/end time to your task list. | `event Meeting /from 10:00 /to 11:00`   |
| delete | **delete {task number}** | Deletes the task with that number from your task list. | `delete 3`                              |
| find | **find {keyword}** | Finds all tasks containing that keyword from your task list. | `find report`                           |
| remind | **remind {number of days}** | Reminds you of your deadline tasks due within this number of days. | `remind 7`                              |
| sort | **sort** | Sorts your task list, making all deadlines appear first in chronological order. | `sort`                                  |

### Clarification

The `sort` command will not change the ordering of any non deadline tasks. It simply rearranges the deadline tasks in chronological
order, appends them to the top of the task list, and leaves all other tasks unchanged at the bottom of the list.