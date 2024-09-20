# Charlotte User Guide

![Screenshot of the product](/docs/Ui.png)

Charlotte is a personal chatbot assistant designed to help you manage your tasks and events. 

## Adding To-dos
Adds a to-do task to your task list

Format: `todo [TASK] `

Example: `todo read book`

Expected output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 2 tasks in the list.
```

## Adding deadlines

Adds a task with a deadline to your task list

Format: `deadline [TASK] /by [DATE]`

Example: `deadline return book /by 2024-9-22`

Expected output:
```
Got it. I've added this task:
[D][ ] return book (by: Sep 22 2024)
Now you have 3 tasks in the list.
```

## Adding events
Adds an event with its duration to your task list

Format: `event [TASK] /from [START_DATE] /to [END_DATE]`

Example: `event camp /from 2024-10-12 /to 2024-10-16`

Expected output:
```
Got it. I've added this task:
[E][ ] camp (from: Oct 12 2024 to: Oct 16 2024)
Now you have 4 tasks in the list.
```

## Listing all tasks
Shows a list of all tasks in your task list

Format: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 22 2024)
3. [E][ ] camp (from: Oct 12 2024 to: Oct 16 2024)
```

## Deleting a task
Deletes a specific task from your task list

Format: `delete [INDEX]`
- Deletes the task at the specified index
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, ...

Example: `delete 2`

Expected output:
```
Noted. I've removed this task:
[D][ ] return book (by: Sep 22 2024)
Now you have 2 tasks in the list.
```

## Marking a task
Marks a specific task in your task list as done

Format: `mark [INDEX]`
- Marks the task at the specified index as done
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, ...

Example: `mark 2`

Expected output:
```
Nice! I've marked this task as done:
[D][X] return book (by: Sep 22 2024)
```

## Un-marking a task
Un-marks a specific task in your task list

Format: `unmark [INDEX]`
- Un-marks the task at the specified index
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, ...

Example: `unmark 2`

Expected output:
```
OK, I've marked this task as not done yet:
[D][ ] return book (by: Sep 22 2024)
```

## Finding tasks using a keyword
Finds tasks whose descriptions contain the given keyword

Format: `find book`

Expected output:
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 22 2024)
```

## Providing reminders for upcoming tasks
Shows a list of all tasks with deadlines within one week and events starting within one week

Format: `reminders`

Expected output:
```
Here are your tasks with upcoming deadlines or events starting within one week:
[D][ ] return book (by: Sep 22 2024)
```