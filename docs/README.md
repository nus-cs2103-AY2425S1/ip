# Opus User Guide

This is the Userguide for Opus

## Features Summary

- **Adds Tasks:** Automatically adds tasks to a file.
- **Mark Tasks as Done:** You can mark tasks as completed.
- **Delete Tasks:** Allows you to remove tasks from the list.
- **List Tasks:** Displays all tasks in your list with their current status.

## Task Types

Opus supports three types of tasks:
- **Deadlines:** Tasks that need to be completed by a specific date.
- **Events:** Tasks that span a specific time period.
- **ToDos:** Simple tasks without deadlines or timeframes.

## Using Opus


### Feature 1: Adding Tasks

To add a Task to the List, you have to specify what task you are adding. The task could be a Deadline, an Event, or a ToDo.

#### Adding a Deadline

###### Format of input : `deadline <task> /by <deadline-date>`
###### Example Input: `deadline return book /by Sunday`

###### Expected output:
```
Got it. I've added this task:
       [D][ ] return book (by: Sunday)
Now you have 1 task in the list.
```

#### Adding an Event

###### Format of input : `event <task> /from <start-time> /to <end-time>`
###### Example input: `event project meeting /from Mon 2pm /to 4pm`

###### Expected output:
```
Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 2 tasks in the list.
```
#### Adding a ToDo

###### Format of input : `todo <task>`
###### Example input: `todo submit report`

###### Expected output:
```
Got it. I've added this task:
       [T][ ] submit report
     Now you have 3 tasks in the list.
```


### Feature 2: Marking Tasks as Done

Use the `mark` command to mark a task as completed.
###### Format of input : `mark <task-number>`
###### Example : `mark 1`
###### Expected output: 
```
Nice! I've marked this task as done: [D][X] return book (by: Sunday)
```

### Feature 3: Deleting Tasks

Use the `delete` command to delete a Task.
###### Format of input : `delete <task-number>`

###### Example : `delete 1`
###### Expected output: 
```
Noted. I've removed this task: 
[D][X] return book (by: Sunday)
Now you have 2 tasks in the list.
```


### Feature 4: Listing Tasks

Use the `list` command to list all Tasks.
###### Format of input : `list`

###### Example : `list`
###### Expected output: 
```
[E][ ] project meeting (from: Mon 2pm to: 4pm)
[T][ ] submit report
```

## Command to run to start the UI
Run the following command to start the UI
`./gradlew run`
