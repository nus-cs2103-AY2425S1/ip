# Hana User Guide

![Product screenshot](Ui.png)

Hana is a task management chatbot designed to help users manage tasks such as Todos, Deadlines, and Events. It allows you to list, add, mark, and delete tasks. Additionally, Hana supports mass operations for marking, unmarking, or deleting tasks based on keywords, making task management more efficient.


## Adding A Todo

Adds a simple Todo task.

Example: `todo <description>`

Example usage: `todo Buy groceries`

Expected outcome: 
```
Got it! I've added this task: 
     [T][ ] Buy groceries 
Now you have X tasks in the list.
```


## Adding A Deadline

Adds a deadline allows you to set a task with a specific date by which it must be completed.

Example: `deadline <description> /by <date>`

Example usage: `deadline Submit assignment /by 2024-09-30`

Expected outcome:
```
Got it! I've added this task: 
     [D][ ] Submit assignment (by: Sep 30 2024)
Now you have X tasks in the list.
```


## Adding An Event

Adds an event with a start and end date.

Example: `event <description> /from <start date> /to <end date>`

Example usage: `event Project meeting /from 2024-10-01 /to 2024-10-02`

Expected outcome:
```
Got it! I've added this task: 
     [E][ ] Project meeting (from: Oct 01 2024 to: Oct 02 2024)
Now you have X tasks in the list.
```


## Listing All Tasks

Lists all the tasks currently stored, including Todos, Deadlines, and Events.

Example: `list`

Example usage: `list`

Expected outcome:
```
Here are the tasks in your list:
     1. [T][ ] Buy groceries
     2. [D][ ] Submit assignment (by: Sep 30 2024)
     3. [E][ ] Project meeting (from: Oct 01 2024 to: Oct 02 2024)
```


## Marking a Task as Done

Marks a specific task as done.

Example: `mark <task number>`

Example usage: `mark 1`

Expected outcome
```
Nice! I've marked this task as done: 
     [T][X] Buy groceries
```



## Unmarking a Task

Unmarks a specific task, marking it as incomplete.

Example: `unmark <task number>`

Example usage: `unmark 2`

Expected outcome
```
Nice! I've unmarked this task: 
  [D][ ] Submit assignment (by: Sep 30 2024)
```


## Deleting a Task

Deletes a specific task from the list.

Example: `delete <task number>`

Example usage: `delete 3`

Expected outcome
```
Noted. I've removed this task: 
     [E][ ] Project meeting (from: Oct 01 2024 to: Oct 02 2024) 
Now you have X tasks in the list.
```


## Finding Tasks

Searches for tasks that contain the given keyword.

Example: `find <keyword>`

Example usage: `find groc`

Expected outcome
```
Here are the matching tasks in your list: 
     1. [T][ ] Buy groceries
```


## Mass Operations (Mark, Unmark, Delete)

Mass operations allow you to perform actions on multiple tasks that contain a certain keyword. The system will display the tasks that match the keyword and ask for confirmation before proceeding with the operation.

### Mass Mark

Marks all tasks containing a specific keyword as done. The system will list the matching tasks and ask for confirmation. Enter `y` to proceed with marking the tasks.

Example: `massMark <keyword>`

Example usage: `massMark u`

Expected outcome
```
Here are the matching tasks in your list:
     1. [T][X] Buy groceries
     2. [D][ ] Submit assignment (by: Sep 30 2024)
Are you sure you want to mark the following tasks? Enter 'y' for yes.
```

  If the user enters `y`:
```
The tasks have been successfully marked.
```

### Mass Unmark

Unmarks all tasks containing a specific keyword as done. The system will list the matching tasks and ask for confirmation. Enter `y` to proceed with unmarking the tasks.

Example: `massUnmark <keyword>`

Example usage: `massUnmark u`

Expected outcome
```
Here are the matching tasks in your list:
     1. [T][X] Buy groceries
     2. [D][X] Submit assignment (by: Sep 30 2024)
Are you sure you want to unmark the following tasks? Enter 'y' for yes.
```

If the user enters `y`:
```
The tasks have been successfully unmarked.
```

### Mass Delete

Deletes all tasks containing a specific keyword as done. The system will list the matching tasks and ask for confirmation. Enter `y` to proceed with deleting the tasks.

Example: `massDelete <keyword>`

Example usage: `massDelete u`

Expected outcome
```
Here are the matching tasks in your list:
     1. [T][ ] Buy groceries
     2. [D][ ] Submit assignment (by: Sep 30 2024)
Are you sure you want to delete the following tasks? Enter 'y' for yes.
```

If the user enters `y`:
```
The tasks have been successfully deleted.
```


## Exiting Hana

Exits the Hana program.

Example: `bye`

Example usage: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
















