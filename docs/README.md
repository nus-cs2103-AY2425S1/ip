# Alex User Guide

![Uploading Ui.png…]()

// Product intro goes here
Alex is a smart, friendly, and intuitive task management chatbot. It helps you   keep track of tasks, deadlines, and events effortlessly through a user-friendly interface. With Alex, you can manage your to-dos, schedule important events, and set deadlines. Whether you're looking to organize your day or track long-term projects, Alex has got you covered.

## Adding deadlines

// Describe the action and its outcome.
Action: Adds a task with a deadline to the task list.

Usage: deadline DESCRIPTION /by DATE

Description: Specify the name of the task (e.g., "Submit report").
Date: Enter the deadline using the format yyyy-MM-dd HH:mm (e.g., 2024-09-19 15:55) or a valid day of week (e.g Sunday).

// Give examples of usage
Example:
Command: deadline deadline return book /by 2024-09-09 15:55  

Expected Output:
Got it. I've added the following task:
  [D][ ] submit project (by: Sep 09 2024 15:55)
Now you have X tasks in the list.


```
expected output
```

## Adding ToDos

// Feature details
Action: Adds a simple to-do task to the task list.

Usage: todo DESCRIPTION

DESCRIPTION: Provide the task description (e.g., "Buy groceries").
Example:
Command: todo buy groceries  

Expected Output:
Got it. I've added the following task:
  [T][ ] buy groceries
Now you have X tasks in the list.


## Adding Events

// Feature details
Action: Adds an event to the task list, specifying the start and end times.

Usage: event DESCRIPTION /from START /to END

DESCRIPTION: The event name (e.g., "Team meeting").
Start: Start time/date for the event using the format "[Day X pm]"
End: End date for the event using the format "[X pm]"
Example:
Command: event project meeting /from Mon 2pm /to 4pm

Expected Output:
Got it. I've added the following task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have X tasks in the list.

## Adding Fixed Duration Tasks
Action: Adds a fixed duration task to the task list, specifying the period which the task is active for.

Usage: fixed duration DESCRIPTION (DURATION)
DESCRIPTION: Task name (e.g buy food)
DURATION: Period which task is active for, using the format [X minutes]
Example:
Command: fixed duration buy food (5 minutes)

Expected output:
Got it. I've added the following task:
[F][] buy food (Duration: 5 minutes)
Now you have X tasks in the list.

## Adding Default Tasks
Action: Adds a basic task without any additional properties like deadlines or events.

Usage: DESCRIPTION
DESCRIPTION: Default task name

Example:
Command: Read book

Expected Output:
Got it. I've added the following task:
added: Read book
Now you have X tasks in the list.

## Marking Tasks as Done
Action: Marks a task as done.

Usage: mark TASK_NUMBER

TASK_NUMBER: The number of the task in the list.
Example:
Command: mark 2

Expected Output:
Nice! I've marked this task as done:
  [T][X] buy groceries

## Unmarking Tasks as not Done
Action: Unmarks a task which was previously done.

Usage: unmark TASK_NUMBER

TASK_NUMBER: The number of the task in the list.
Example:
Command: unmark 2

Expected Output:
OK, I've marked this task as not done yet:
       [ ] return book

## Deleting Tasks
Action: Deletes a task from the list.

Usage: delete TASK_NUMBER

Example:
Command: delete 2
Expected Output:
Deleted task:
  [T][X] buy groceries
Now you have X tasks in the list.

## Viewing Tasks
Action: Lists all tasks in your task list.

Usage: list

Example:
Command: list
Expected Output:
Here are the matching tasks in your list:
1. [T][] buy books
2. [D][] finish work (by Sunday)
3. [T][] pick up gift

## Finding Tasks
Action: Find all tasks containing specified keyword

Usage: find TASK_DESCRIPTION

TASK_DESCRIPTION: The description of any task user wants to find from task list.
Example:

## Telling user a joke
Action: Tells user a joke for entertainment

Usage: tell me a joke

Example:
Command: tell me a joke
Expected Output:
Why do programmers prefer dark mode? Because the light attracts bugs!



