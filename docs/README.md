# BotimusPrime User Guide

![Screenshot of the Botimus Prime chatbot](./Ui.png)

Hey, human! This is a user guide on how to use BotimusPrime, a chatbot designed
for **keeping track of your daily tasks and schedules via a Command Line Interface (CLI)!**

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Adding To-dos

The chatbot allows you to add to-dos, which are simple tasks that don't 
have a specific deadline or event time.

### Command Format:
`todo {task_description}`

- This command allows you to add a basic task to your list without any time constraints.

### Example:
`todo Buy groceries`

- The command above adds "Buy groceries" to your list of tasks.

#### Expected Outcome:
Alright. I've added the task:  
[T][ ] Buy groceries  
Now you have 1 task in the list.

## Adding Deadlines

This bot allows you to add deadlines, which are tasks that need to be completed by a certain date and time.

### Command Format:
`deadline {task_description} /by {date}`   

*Note: acceptable date formats are YYYY-MM-DD, and DD/MM/YYYY.  
Optional: add a HHMM at the back to specify a time.*

- This command allows you to add a task with a specific deadline.

### Example:
`deadline Submit project /by 2024-09-30 2359`

- The command above adds "Submit project" with a deadline of September 30, 2024, at 11:59 PM.

#### Expected Outcome:
Alright. I've added the task:  
[D][ ] Submit project (by: 2024-09-30 23:59)  
Now you have 2 tasks in the list.

## Adding Events

This bot allows you to add events, which are tasks that happen during a 
specific period, with both start and end times.

### Command Format:
`event {event_description} /from {date} /to {date}`  

*Note: acceptable date formats are YYYY-MM-DD, and DD/MM/YYYY.  
Optional: add a HHMM at the back to specify a time.*

- This command allows you to add an event with start and end times.

### Example:
`event Team meeting /from 2024-09-21 0900 /to 2024-09-21 1100`


- The command above adds "Team meeting" scheduled from 9:00 AM to 11:00 AM on September 21, 2024.

#### Expected Outcome:
Alright. I've added the task:  
[E][ ] Team meeting (from: 2024-09-21 09:00 to: 2024-09-21 11:00)  
Now you have 3 tasks in the list.

## Showing the tasklist

This chatbot allows you to see all tasks.

## Command Format:
`list`

### Example:
`list`

- The command above shows all tasks in your task list.

#### Expected Outcome
Here are the tasks in your list:  
1. [T][ ] Buy groceries  
2. [D][ ] Submit project (by: 2024-09-30 23:59)
3. [E][ ] Team meeting (from: 2024-09-21 09:00 to: 2024-09-21 11:00)

## Deleting tasks

This chatbot allows you to delete tasks from the tasklist. **Note that index starts with 1**.

### Command Format
`delete {item index}`  
*Note: index starts from 1*

### Example:
`delete 1`


- The command above deletes the first task in the list.

#### Expected Outcome:
Noted. I've removed the task:  
[T][ ] Buy groceries  
Now you have 2 tasks in the list.

## Marking (and unmarking) tasks

This chatbot allows you to mark (and unmark) tasks as done.

### Command Format:

`mark {item index}`  
`unmark {item index}`  
*Note: index starts from 1*

### Example:
`unmark 1`  

- The command above marks the first task in the list as done.

#### Expected Outcome:
OK, I've marked this task as not done yet:  
[D][ ] Submit project (by: 2024-09-30 23:59)  


## Finding tasks

This chatbot allows you to find tasks in your tasklist.

### Command Format:
`find {keyword}`

### Example:
`find Submit`

- This command finds all tasks with the word "Submit".

#### Expected Outcome: 
Here are the matching tasks in your list:  
[D][ ] Submit project (by: 2024-09-30 23:59)  

## View tasks for a day

This chatbot allows you to view all tasks taking place that day. 

## Command Format: 
`view {date}`

### Example:
`view 2024-09-30`


#### Expected Outcome:
Here's your schedule for September 30, 2024:  
[D][ ] Submit project (by: 2024-09-30 23:59)  


