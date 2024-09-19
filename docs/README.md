# Nuffle User Guide

![img.png](img.png)

# Introduction

Welcome to Nuffle, your intelligent task management assistant! Nuffle has the ability to help you organise
tasks, set deadlines, and manage your day-to-day effectively. Nuffle allows you to interact
with ease and focus on your goals without worrying about missing a deadline or
forgetting a task.

This guide will walk you through the key features and commands you can use to get the most
out Nuffle.

# Nuffle Features

## Adding deadlines

To add a deadline for a task, simply enter the task with the deadline details. Nuffle
will interpret the task and create a deadline for it.

Action:  
Adding a deadline task. 
  
Outcome:  
Nuffle saves the task and schedules it by the specified date. 

Usage:
```
deadline <description> /by <deadline datetime>
```
/by format: yyyy-MM-dd HHmm

Example:   
```
deadline submit report /by 2024-09-30 2359
```

  
Output:

```
Got it. I've added this task:
  [D][] submit report (by: Sep 30 2024, 11:59 pm)
Now you have 3 tasks in your list.
```

## Adding Todos

To add a todo for a task, simply enter the task with the todos details. Nuffle
will interpret the task and create a todo for it.

Action:  
Adding a todo task.

Outcome:  
Nuffle saves the task.

Usage:
```
todo <description>
```

Example:
```
todo meet friends
```

Output:

```
Got it. I've added this task:
  [T][] meet friends
Now you have 3 tasks in your list.
```

## Adding events

To add an event for a task, simply enter the task with the event details. Nuffle
will interpret the task and create an event.

Action:  
Adding an event task.

Outcome:  
Nuffle saves the task and schedules it by the specified date.

Usage:
```
event <description> /from <from datetime> /to <to datetime>
```

Example: 
```
event attend lecture /from 2024-09-30 1500 /to 2024-09-30 1600
```
/to format: yyyy-MM-dd HHmm  
/from format: yyyy-MM-dd HHmm

Output:

```
Got it. I've added this task:
  [E][] attend lecture (from: Sep 30 2024, 3:00 pm to: Sep 30 2024, 4:00 pm)
Now you have 3 tasks in your list.
```

## Adding loans

To add a loan, simply enter the task with the loan details. Nuffle
will interpret the task and create a loan.

Action:  
Adding a loan task.

Outcome:  
Nuffle saves the task and generate the loan status and details.

Usage:
```
loan /B <borrower> /L <lender> /amt <amount loaned> /due <due datetime>
```

Example: 
```
loan /B James /L Steven /amt 100 /due 2024-09-30 1600
```
/due format: yyyy-MM-dd HHmm

Output:
```
Got it. I've added this task:
  [L] James owes Steven $100.0, due by Sep 30 2024, 4:00 pm (pending)
Now you have 3 tasks in your list.
```

## Listing Tasks

You can view all your tasks, including their status and deadlines,
using the `list` command.  
  
Action:  
Listing all current tasks.  

Outcome:  
Nuffle displays all saved tasks with details on completion status and deadlines.  

Usage:
```
list
```

Output:
```
Here are the tasks in your list:
  1. [D][ ] submit report (by: Sep 30 2024)

```


## Marking Tasks

When a task is complete, you can mark it as done to keep track of your progress.

Action:  
Mark a task as completed.  
  
Outcome:  
Nuffle updates the status of the task.  

Usage:
```
mark <index of the task>
```
  
Example:
```
mark 1
```

Output:
```
Nice! I've marked this task as done!
  [D][X] submit report (by: Sep 30 2024 2359)
```

## Unmarking Tasks

When a task is incomplete but marked as done, you can unmark it as not done to keep track of your progress.

Action:  
Mark a task as completed.

Outcome:  
Nuffle updates the status of the task.

Usage:
```
unmark <index of the task>
```

Example:
```
unmark 1
```

Output:
```
Nice! I have marked this task as not done yet.
  [D][] submit report (by: Sep 30 2024 2359)
```

## Deleting Tasks


If you no longer need a task, you can delete it from your list.  

Action:  
Deleting a task.

Outcome:  
Nuffle removes the task from the list.

Usage:
```
delete <index of the task>
```

Example:
```
delete 1
```

Output:
```
Noted. I've removed this task:
  [D][X] submit report (by: Sep 30 2024 2359)
Now you have 4 tasks in the list.
```

## Finding Tasks

You can search for tasks that contain a specific keyword using the find command.


Action:  
Search for tasks by keyword.

Outcome:  
Nuffle shows all tasks that contain the keyword.

Usage:
```
find <keyword>
```

Example:
```
find report
```
Output:
```
Here are the matching tasks in your list:
1. [D][] submit report (by: Sep 30 2024 2359)
```

## Exiting Nuffle

To close the application, use the bye command.

Action:  
Exit Nuffle.

Outcome:  
The program saves your progress and exits.



Usage:
```
bye
```

Output:
```
Bye. Hope to see you again soon!
```


