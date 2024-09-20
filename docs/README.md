# Bobby User Guide

![Screenshot of Bobby Ui.](Ui.png)

**Bobby** is a simple, intuitive task management chat application. It allows users to manage their tasks through commands, helping them keep track of daily activities such as deadlines, events, and to-do lists. Bobby saves all tasks locally, allowing users to resume where they left off. Its minimalistic design and responsive command interface make it easy to use and efficient for managing tasks.

## List
Users can list all tasks added using the list command.

Format: `list`

Expected output if there are tasks added:
```plaintext
1.[E][X] carebear (from: Sep 22 2024 to: Sep 23 2024)
2.[D][X] gummybear (by: Sep 24 2024)
```

Expected output if no task added:
```plaintext
No tasks added to the list yet.
```

## Adding Todos
With Bobby, users can add todo tasks which do not have a specific deadline.

Format: `todo TASK_NAME'

Examples:
- `todo assignment`
- `todo linear algebra quiz`

Expected output if todo is successfully added:
```plaintext
Task added successfully:
  [T][ ] linear algebra quiz
Now you have 3 tasks in the list.
```

Expected output if todo is missing a name:
```plaintext
OOPS!!! The description of todo cannot be empty!
```

## Adding deadlines

With Bobby, users can add deadlines to their task list. A deadline task requires a specific due date in the format yyyy-MM-dd, which helps users track when the task needs to be completed.

Format:
`dealine TASK_NAME /by DUE_DATE`

Examples:
- `deadline carebear /by 2024-09-30`
- `deadline math homework /by 2024-09-10`

Expected output if deadline is successfully added:

```plaintext
Task added successfully:
  [D][ ] math homework (by: Sep 10 2024)
Now you have 4 tasks in the list.
```

Expected output if missing date or deadline name:
```plaintext
OOPS!!! The description or deadline of a deadline task cannot be empty.
```

Expected output if date is in wrong format:
```plaintext
OOPS!!! Invalid date format. Please use yyyy-MM-dd.
```

## Adding events
With Bobby, users can also add events that span a specific duration, from a start date to an end date. The dates should be provided in the format yyyy-MM-DD, making it easier to organize events that occur over multiple days.

Format:
`event TASK_NAME /from START_DATE /to END_DATE`

Examples:
- `event capture the flag /from 2024-09-10 /to 2024-09-11`
- `event assignment 1 /from 2024-08-11 /to 2024-08-12`

Expected output if event is successfully added:
```plaintext
Task added successfully:
  [E][ ] assignment 1 (from: Aug 11 2024 to: Aug 12 2024)
Now you have 5 tasks in the list.
```

Expected output if missing date or event name:
```plaintext
OOPS!!! The description, start time, or end time of an event cannot be empty.
```

Expected output if date is in wrong format:
```plaintext
OOPS!!! Invalid date format. Please use yyyy-MM-dd.
```


## Mark and Unmark Tasks
To mark a task as completed, use the `mark` command followed by the task number. This action will update the task to show it has been completed.
Similarly to unmark a task use the `unmark` command followed by the task number to show that it has not been done.
These commands accept a varying number of task numbers.

Format:
- `mark TASK_NUMBER_1 TASK_NUMEBR_2 ...`
- `unmark TASK_NUMBER_1 TASK_NUMEBR_2 ...`

Examples:
-  `mark 4`
- `mark 1 2 3`
- `unmark 5`
- `unmark 3 2 1`

Expected output for mark:
```plaintext
Nice! I've marked these tasks as done:
 - [E][X] carebear (from: Sep 22 2024 to: Sep 23 2024)
 - [D][X] gummybear (by: Sep 24 2024)
 - [T][X] linear algebra quiz
```

Expected output for unmark:
```plaintext
OK, I've marked these tasks as not done yet:
 - [T][ ] linear algebra quiz
 - [D][ ] gummybear (by: Sep 24 2024)
 - [E][ ] carebear (from: Sep 22 2024 to: Sep 23 2024)
```

Expected output if task does not exist for mark and unmark:
```plaintext
OOPS!!! The task number provided is invalid.
```
## Delete
To delete tasks, use the `delete` command followed by the task numbers that you want to delete.

Format:
- `delete TASK_NUMBER_1 TASK_NUMBER 2 ...`

Examples:
- `delete 1`
- `delete 1 2 3`

Expected output:
```plaintext
Task removed successfully:
 - [E][ ] carebear (from: Sep 22 2024 to: Sep 23 2024)
 - [D][ ] gummybear (by: Sep 24 2024)
 - [T][ ] linear algebra quiz
Now you have 2 tasks in the list.
```
Expected output if task does not exist:
```plaintext
OOPS!!! The task number provided is invalid.
```

## Find
Users can use the find keyword to search for a task based on a keyword provided.
This keyword does not need to be the entire name of the task. So for example if there is a task named `math homework` then `find ma` will also return the task.

Format:
- `find KEYWORD`

Examples:
- `find math`
- `find math homework`

Expected output if found:
```plaintext
Here are the matching tasks in your list:
1.[D][ ] math homework (by: Sep 10 2024)
```
Expected ouput if no task found:
```plaintext
No tasks found.
```
## Search date
Users can use the `searchdate` command to search for a task based on the date provided. The date
has to be in the format yyyy-MM-DD.

Format:
- `searchdate 2024-09-10`

Expected output if found:
```plaintext
Here are the matching tasks in your list:
1.[D][ ] math homework (by: Sep 10 2024)
2.[E][ ] assignment 2 (from: Sep 10 2024 to: Sep 11 2024)
```

Expected output if no task found:
```plaintext
No tasks found.
```
## Bye
Users can exit the application using the `bye` command. Upon issuing this command, Bobby will display a farewell message before closing the application.

Format:
- `bye`

Expected output:
```plaintext
Bye. Hope to see you again soon!
```

