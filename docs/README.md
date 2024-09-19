# User Guide

ScoobyDoo is a chatbot app for managing tasks, optimized for use via a Command Line Interface (CLI) while also providing a Graphical User Interface (GUI). ScoobyDoo has a unique personality, giving users a pleasant experience while managing their tasks.

---
## Features

> [!NOTE]  
> Words in UPPER_CASE are the parameters to be supplied by the user.  
> e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter.

### Add Todo Task
Add a simple task without a date restriction to the chatbot.  
**Format:** `todo DESCRIPTION`

### Add Deadline Task
Add a task with a deadline to the chatbot.  
**Format:** `deadline DESCRIPTION /by DATE_TIME`
- `DESCRIPTION`: The task description.
- `DATE_TIME`: The deadline in the format `yyyy-MM-dd HH:mm`.

### Add Event Task
Add a task that has a start and end time to the chatbot.  
**Format:** `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`
- `DESCRIPTION`: The event description.
- `START_DATE_TIME`: The start time of the event in the format `yyyy-MM-dd HH:mm`.
- `END_DATE_TIME`: The end time of the event in the format `yyyy-MM-dd HH:mm`.

### Mark Task as Done
Mark a task as completed.  
**Format:** `mark TASK_NUMBER`
- `TASK_NUMBER`: The number of the task in the list.

### Unmark Task
Unmark a completed task to mark it as not done.  
**Format:** `unmark TASK_NUMBER`
- `TASK_NUMBER`: The number of the task in the list.

### Delete Task
Delete a task from the list.  
**Format:** `delete TASK_NUMBER`
- `TASK_NUMBER`: The number of the task to be deleted.

### Undo Last Action
Undo the most recent task-related action (e.g., add, delete, mark, unmark).  
**Format:** `undo`

### Redo Last Undone Action
Redo the most recently undone action.  
**Format:** `redo`

### List Tasks
View all tasks currently in the list, showing their status and descriptions.  
**Format:** `list`

### Find Task
Search for tasks that contain a specific word in their description.  
**Format:** `find KEYWORD`
- `KEYWORD`: The word to search for in the task descriptions.

### Exit the App
Exit the chatbot and close the application.  
**Format:** `bye`
