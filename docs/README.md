# Prince User Guide

<img width="1710" alt="Ui" src="https://github.com/user-attachments/assets/01d31738-33f4-4ae6-9c65-f9bed287d5ac">


Welcome to Prince, your personal task management assistant! Prince helps to keep track of all your tasks, whether they
are regular ToDo tasks, tasks with Deadlines or just events, in a simple and efficient way.



<img width="1710" alt="UiAdd" src="https://github.com/user-attachments/assets/58382ed8-2883-4845-8dbf-cda149753752">



## Adding ToDo task

This action adds a simple to-do task without any date or time constraints.

```
Format : 'todo Paint flowers'
```

The response message prints a message indicating the success of addition of the task and displays the updated
length of the list.

**Expected outcome :**
'Got it. I've added this task:
[T][ ] Paint flowers
Now you have 1 tasks in the list
How else would you like me to edit your TODO list today?'


## Adding tasks with deadlines

This action adds a task that has a deadline.

```
Format : 'deadline Submit assignment /by 2024-09-30 1800'
```

The response message prints a message indicating the success of addition of the task and displays the updated
length of the list.

**Expected outcome :**
'Got it. I've added this task:
[D][ ] Submit assignment (by: Sep 30 2024, 6:00 pm)
Now you have 1 tasks in the list
How else would you like me to edit your TODO list today?'


## Adding events with a start and end time

This action adds an event that has a start date and time, as well as an end date and time.

```
Format : 'event award ceremony /from 2024-12-31 1800 /to 2024-12-31 1900'
```

The response message prints a message indicating the success of addition of the task and displays the updated
length of the list.

**Expected outcome :**
'Got it. I've added this task:
[E][ ] award ceremony (from: Dec 31 2024, 6:00 pm to: Dec 31 2024, 7:00 pm)
Now you have 1 tasks in the list
How else would you like me to edit your TODO list today?'

<img width="1710" alt="UiDelete List" src="https://github.com/user-attachments/assets/efc9752a-b83d-4bde-b50b-26ad5cfaad48">


## Deleting tasks

This action deletes a task from the list.

```
Format : 'delete 1'
```

The response message prints a message indicating the success of deletion of the first task in the list and displays the
updated length of the list.

**Expected outcome :**
'Noted. I've removed this task:
[T][ ] Buy groceries
Now you have 0 tasks in the list
How else would you like me to edit your TODO list today?'


## Mark task as done

This action marks a task as done if the task is undone.

```
Format : 'mark 1'
```

The response message prints a message indicating the success of marking the first task in the list if the task is
undone. If the task is already completed, a message is returned that the task has already been marked as done.

**Expected outcome :**  
'Nice! I've marked this task as done:
[T][ ] Buy groceries'

**or**

'This task has already been marked as done!'

## Unmark a task

This action unmarks a task if the task was previously marked as done.

```
Format : 'unmark 1'
```

The response message prints a message indicating the success of unmarking the first task in the list if the task is
done. If the task is not done yet, a message is returned that the task is still incomplete.

**Expected outcome :**
'OK! I've marked this task as not done yet:
[T][ ] Buy groceries'

**or**

'This task has not been completed yet!'

## Find a keyword or phrase

This action searches for tasks containing a specific keyword or phrase.

```
Format : 'find groceries'
```

The response message returns a list of tasks that contain the specific word or phrase.

**Expected outcome :**
'Here are the matching tasks in your list:
1.[T][ ] Buy groceries'

**or**

'You have no current tasks containing 'cat'.
Please try again with another word.'

## Tasks in List View

This action lists all tasks currently saved in the task list.

```
Format : 'list'
```

The response message returns a list of tasks currently saved. If there are no tasks, a message
is printed saying that there are currently zero tasks to be completed.

**Expected outcome :**
'Here are the tasks in your list:
1.[T][ ] Buy groceries'

**or**

'You do not have any tasks in your list as of now.'

<img width="1710" alt="UiArchive Bye" src="https://github.com/user-attachments/assets/418e2687-fb75-487e-9da7-e4221c2eb9c7">

## Archive a list

This action archives the current list of tasks.

```
Format : 'archive'
```

The response message prints a message indicating the success of archiving the tasks in the list.

**Expected outcome :**
'List archived successfully.'

## Archived tasks in List View

This action lists all tasks that have been archived.

```
Format : 'archivelist'
```

The response message returns a list of tasks currently archived.

**Expected outcome :**
'Here are the tasks in your list:
1.[T][ ] Buy groceries'

## Exiting the application

This action displays a farewell message.

```
Format : 'bye'
```

The response message returns a farewell message.

**Expected outcome :**
'Bye! Hope to see you again soon!'


## Handling Invalid Commands

This action prompts an error message to be returned if the command is unknown.

```
Format : 'bring task'
```

The response message is an error message that lets users know about the unknown command used.

**Expected outcome :**
'Unknown command detected: 'bring task'. Sorry I do not know what that means :(
Please try again with a proper command. Make sure you are not adding any unecessary spaces or characters.'