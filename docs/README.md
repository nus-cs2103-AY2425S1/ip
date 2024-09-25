# Ah Bang Mang

This is a Singaporean uncle helper chatbot.

## Features
1. View task list
2. Add varied tasks to task list
    1. Todo: regular task that only displays a description
    2. Deadline: task that can include a deadline
    3. Event: task that has a start and end date
3. Mark tasks as done/ undone
4. Delete tasks from list
5. Find tasks within list


### Bonus
* Appealing GUI
* Singlish responses by chatbot
* Task list is saved to computer hard drive to retrieve when needed
* Asking `help` will provide the full list of commands and respective notations

--------------------------------------------
## User Guide
This guide will walk you through how to use the key features of Ah Bang Mang.

### 1. Viewing the Task List

To view all the tasks you have in your list, simply use the following command: ```list```

This command will display all tasks with their current status (done or not done) and their details.

### 2. Adding Tasks

You can add various types of tasks to your list. Here's how:

* #### Todo: ```todo <description>```
    * for example, ```todo finish homework```


* #### Deadline: ```deadline <description> /by <date time>```
    * The date-time should be in the format `yyyy-MM-ddTHH:mm`, or `yyyy-MM-dd` to set the time automatically to `23:59`.
      You can also use `today` to set the deadline to the end of the current day.

    * for example, ```deadline Submit report /by 2024-09-17T23:59```

* #### Event: ```event <description> /from <start-date-time> /to <end-date-time>```
* The date-time should be in the format `yyyy-MM-ddTHH:mm`, or `yyyy-MM-dd` if the event has no specific time.
    * for example, ```event Team meeting /from 2024-09-17T09:00 /to 2024-09-17T11:00```

### 3. Marking Tasks
You can mark tasks as done or undone using the following commands:
* #### Mark Task as Done: `mark <task-number>`
    * for example, `mark 1`
* #### Mark Task as Undone: `unmark <task-number>`
    * for example, `unmark 1`

### 4. Delete Tasks
* You can delete tasks from list by this simple command: `delete <task-number>`
    * for example, `delete 1`

### 5. Find Tasks
* You can find tasks from list by this simple command: `find <query>`
    * for example, `find homework`

--------------------------------------------
## Acknowledgements:
1. ChatGPT: Writing JUnit command tests
2. SE-EDU JavaFX Tutorial: GUI base code