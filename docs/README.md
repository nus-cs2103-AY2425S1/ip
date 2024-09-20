# Alice User Guide
![Ui](https://github.com/user-attachments/assets/7bfb4362-c75f-4ebf-b0d6-3528215d1526)

Alice can help you to manage your tasks with simple commands.

## Add Tasks
Different tasks can be added with different arguments and commands.
- todos: `todo {description} [/p {priority}]`
- deadlines: `deadline {description} /by {deadline} [/p {priority}]`
- events: `event {description /from {start} /to {end} [/p {priority}]`

Some notes on the commands: 
- deadline, start, end: format is YYYY-MM-DD.
- items in square brackets are optional.
- priority is a string which can be "high, "medium", or "low".
- if not specified, priority is defaulted to be low.

Upon successful addition of task, a success message would be displayed on the GUI.
```
Got it, I've added this task:
{task}
Now you have X tasks in the list.
```

## Delete Tasks
Delete a task based on the task number and the list it is in.

Command: `delete {taskNumber} {priority}`

Some notes on the commands: 
- taskNumber is the number of that task in the list.
- priority is a string which can be "high, "medium", or "low".

Upon successful deletion of task, a success message would be displayed on the GUI.
```
Noted, I've removed this task:
{task}
Now you have X tasks in the list.
```

## Mark and Unmark Tasks
Change the status of the task based on the task number and the list it is in.

Commands: 
- mark: `mark {taskNumber} {priority}`
- unmark: `unmark {taskNumber} {priority}`

Some notes on the commands: 
- taskNumber is the number of that task in the list.
- priority is a string which can be "high, "medium", or "low".

Upon successful marking or unmarking of a task, a success message would be displayed on the GUI.

Mark task message:
```
Noted! I've marked this task as done:
{task}
```
Unmark task message:
```
Ok, I've marked this task as not done yet:
{task}
```

## Set Priority of Task
Changes the priority of the task numbered taskNumber in the list containing prioritylist. Sets new priority as newPriority.

Command: `priority {taskNumber} {prioritylist} {newPriority}`

Some notes on the commands: 
- taskNumber is the number of that task in the list.
- prioritylist is a string which can be "high, "medium", or "low". Specifies which list to reference.
- newPriority is a string which can be "high, "medium", or "low". The new priority to be set.

Upon successful changing of priority, a success message would be displayed on the GUI.
```
Sure, I've changed the priority for this task to {newPriority}:
{task}
```

## List All Tasks
Lists all the tasks in the list separated by their priority.

Command: `list`

The list would be formatted as follows:
```
Here are the tasks in your list:
HIGH PRIORITY:
{task}

MEDIUM PRIORITY:
{task}

LOW PRIORITY:
{task}
```

## Find Tasks By Keyword
Find tasks whose description contains the keyword.

Command: `find {keyword}`

The tasks would be formatted as follows:
```
Here are the matching tasks in your list:
{task}
```

## Exitting the Program
Command: `bye` 

Ends the chatbot conversation and saves the tasks that the user has added.

Command: `exit`

Closes the GUI.
