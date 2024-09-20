# RainyBot User Guide

![RainyBot photo](Ui.png)

RAINY - Responsive, Automated, Intelligence Network for You. Use the chatbot for tracking your personal tasks!!

# List of Functions
## ToDo
Add a ToDo task to your list of tasks.

Format: todo 'name of ToDo'

Example: todo borrow book

## Deadline
Add a Deadline task to your list of tasks.

Format: deadline 'name of Deadline' / by DD/MM/YYYY HHMM

Example: deadline return book /by 02/12/2019 1800

## Event
Add an Event task to your list of tasks.

Format: event 'name of Event' /on DD/MM/YYYY /HHMM to HHMM

Example: event project meeting /on 19/08/1998 /1800 to 2000

## List Tasks
View the list of tasks currently.

Format: list

Example: n/a

## Mark Task
Mark a task as done.

Format: mark [task number]

Example: mark 1

## Unmark Task
Unmark a marked task, leaving it still undone in the list.

Format: unmark [task number]

Example: unmark 1

## Sort Tasks
Sort your task list by the due date of the task in the case of a Deadline or the date of the task in the case of an Event. For ToDo tasks, they will be sorted to the bottom of the list.

Format: sort

Example: n/a

## Find Task
Find a list of tasks which contain a specific keyword.

Format: find 'keyword'

Example: find book

## Delete task
Delete a task from the list.

Format: delete [task number]

Example: delete 1

## Mark Task
Mark a task as done.

Format: mark [task number]

Example: mark 1

## Update ToDo
Update a ToDo task with a new task description

Format: update [task number], 'new description'

Example: update 1, meeting

## Update Deadline
Update a Deadline task with a new task description and/or a new date and time.

Format: update [task number], name 'new description', date DD/MM/YYYY HHMM

Example: update 1, name meeting, date 19/08/2001 2000
Note: It is optional to include both parameters to update, meaning you could possibly issue this command to update just the task description:

update 1: name meeting

or issue this command to update just the task due date:
update 1: date DD/MM/YYYY HHMM

## Update Event
Update an Event task with a new task description and/or a new date and time.

Format: update [task number], name 'new description', date DD/MM/YYYY, time HHMM to HHMM

Example: update 1, name meeting, date 19/08/2001, time 2000 to 22200
Note: It is optional to include all 3 parameters to update, meaning you could possibly issue this command to update just the task description:

update 1, name meeting

or issue this command to update just the task date:
update 1, date DD/MM/YYYY

or issue this command to update just the task timeframe:
update 1, time HHMM to HHMM

or any two out of the three parameters, like this:
update 1, time HHMM to HHMM, date 19/08/2001

## Bye

Issuing this command will exit the program.

Format: bye

Example: n/a


## Invalid Commands

For certain invalid commands, the chatbot will issue you a warning message with a suggestion to alter your command.
For commands containing more grievous errors, the chatbot will issue you a warning message in red, and you will be able to send your command until you change it.