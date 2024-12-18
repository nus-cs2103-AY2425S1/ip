# Barry User Guide
![Ui Image](./Ui.png)

Barry is a friendly, text-based chatbot designed to help you manage your task with ease.
Whether you need to track simple to-dos, deadlines with specific dates, or events with
start and end times, Barry is here to assist. Barry operates via text input, making it fast
and easy to manage your tasks.

## Adding ToDos
The `todo` command allows you to add a task
```
todo <description>
```
For example:
```
todo Run
```
Expected outcome:
```
bzzz OK! I've added this task:
  [T][ ] Run
Now you have 1 tasks in the list. 
```
## Adding deadlines
The `deadline` command allows you to add a task with a specific deadline (in the yyyy-mm-dd format).
``` 
deadline <description> /by <yyyy-mm-dd>
```
For example:
```
deadline Submit assignment /by 2024-09-30
```
Expected outcome:
```
bzzz OK! I've added this task:
  [D][ ] Submit assignment (by: Sep 30 2024)
Now you have 2 tasks in the list.
```

## Adding Events
The `event` command allows you to add a event with start and end times, useful for managing appointments
or scheduled meetings.
``` 
event <description> /from <HH:mm> /to <HH:mm>
```
``` 
event <description> /from <yyyy-mm-dd HH:mm> /to <yyyy-mm-dd HH:mm>
```
``` 
event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>

```
For example:
```
event Tuition class /from 2023-09-20 4pm /to 2023-09-20 6pm
```
Expected outcome:
```
bzzz OK! I've added this task:
  [E][ ] Tuition class (from: 2023-09-20 4pm to: 2023-09-20 6pm)
Now you have 3 tasks in the list.
```

## Listing All tasks
The `list` command allows you to list all tasks in the task list.
``` 
list
```
For example:
```
list
```
Expected outcome:
```
Here are the tasks in your list:
  1.[T][ ] Run
  1.[D][ ] Submit assignment (by: Sep 30 2024)
  2.[E][ ] Tuition class (from: 2023-09-20 4pm to: 2023-09-20 6pm)
```
## Marking a Task as Done
The `mark` command allows you to mark a task as complete.
``` 
mark <task number>
```
For example:
```
mark 3
```
Expected outcome:
```
Nice! I've marked this task as done:
  [E][X] Tuition class (from: 2023-09-20 4pm to: 2023-09-20 6pm)
```
## Unmarking a Task
The `unmark` command allows you to undo a marked task.
```
unmark <task number>
```
For Example:
```
unmark 3
```
Expected outcome:
```
OK! I've marked this task as not done yet:
[E][] Tuition class (from: 2023-09-20 4pm to: 2023-09-20 6pm)
```
## Deleting a Task
The `delete` command can be used to remove a task from your list.
```
delete <task number>
```
For example:
```
delete 2
```
Expected outcome:
```
Noted. I've removed this task:
  [D][ ] Submit assignment (by: Sep 30 2024)
Now you have 2 tasks in the list.
```
## Finding Tasks by Keyword
The `find` command lets you search for tasks using a specific keyword.
```
find <keyword>
```
For example:
```
find tuition
```
Expected outcome:
```
Here are the matching tasks in your list:
  1.[E][] Tuition class (from: 2023-09-20 4pm to: 2023-09-20 6pm)
```
## Help Command
The `help` command shows a list of available commands and their description.
```
help
```
For example:
```
help
```
Expected outcome:
```
Here are the commands you can use:
 bye - Exits the program
 list - Lists all tasks
 todo <description> - Adds a todo task
 deadline <description> /by <yyyy-mm-dd> - Adds a task with a deadline
 event <description> /from <start date/time> /to <start date/time> - Adds an event with a start and end time
 mark <index> - Marks a task as done
 unmark <index> - Marks a task as not done
 delete <index> - Deletes a task
 find <keyword> - Finds tasks by keyword
 help - Shows this help page
```
---
This documentation outlines the features of Barry, including how to add, manage and
search tasks efficiently.
