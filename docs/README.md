# MonoBot User Guide

![Screenshot of Ui of app](./Ui.png)

MonoBot is a graphical user interface (GUI) desktop application that helps you track your upcoming tasks

## Adding tasks

You can add tasks of three types: Todo, Deadline and Event
Use a keyword to specify the task type.

To add a todo task, enter "todo \<task details>"\
To add a deadline task, enter "deadline /by \<due date>"\
To add a event task, enter "event /from \<start time> /to \<end time>"

For the dates and time, use the following format: DD/MM/YYYY HHMM

Example:
`event cs2103t lecture /from 19/03/2025 1400 /to 19/03/2025 1600`

Expected output:
```
Added: [E][ ] cs2103t lecture (from: Mar 19 2025, 14:00 to: Mar 19 2025, 16:00)
Now you have 1 task(s) in the list
```

## Viewing all tasks

You can view all the tasks you have added to your list by using the "list" command.

Example: `list`

Expected output:
```
Now you have 1 task(s) in the list
Here are the tasks in your list:
1. [E][ ] cs2103t lecture (from: Mar 19 2025, 14:00 to: Mar 19 2025, 16:00)
```

## Deleting tasks

You can remove tasks from your list by using the "delete command" followed by the item number of the task you would like to delete

You can delete multiple tasks at once too, simply separate the item numbers you would like to delete with a space.

Example: `delete 1 3`

Expected output:
```
Noted! I've removed these task(s):
[E][ ] CCA (from: Sep 17 2024, 18:00 to: Sep 17 2024, 20:00)
[T][ ] iP final submission
Now you have 2 task(s) in the list
```

## Marking and Unmarking tasks

You can mark and unmark tasks in your list to reflect its completion status using the "mark" and "unmark" commands respectively.

You can mark or unmark multiple tasks at once, by indicating the list numbers of the tasks you wish to mark / unmark, separated by a space

Example: `mark 1 3`

Expected output:
```
Nice! I've marked the following task(s) as completed:
[E][X] CCA (from: Sep 17 2024, 18:00 to: Sep 17 2024, 20:00)
[E][X] cs2103t lecture (from: Mar 19 2025, 14:00 to: Mar 19 2025, 16:00)
```
Example: `unmark 1 3`

Expected output:
```
Ok! I've marked the following task(s) as incomplete:
[E][ ] CCA (from: Sep 17 2024, 18:00 to: Sep 17 2024, 20:00)
[E][ ] cs2103t lecture (from: Mar 19 2025, 14:00 to: Mar 19 2025, 16:00)
```

## Finding tasks

You can look for tasks in your list by the keywords used in the task by using the "find" command followed by the keyword(s) in the task you are looking for.

Example: `find cca lecture`

Expected output:
```
Here are the matching tasks in your list:
1. [E][ ] CCA (from: Sep 17 2024, 18:00 to: Sep 17 2024, 20:00)
2. [E][ ] cs2103t lecture (from: Mar 19 2025, 14:00 to: Mar 19 2025, 16:00)
```

## Quitting the app

You can quit the app by using the "bye" command.

Example: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```



