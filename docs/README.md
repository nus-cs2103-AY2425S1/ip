# Echo User Guide

![Screenshot of Echo UI](Ui.png)

Echo is a cat Chat Bot that can help users track their tasks.
There are 3 different types of task it can track:
* Todo  
* Deadline
* Event

## Viewing help : `help`

Shows a pop-up page with all the valid commands on their 
purpose and how to use them

Example: `help`

Expected output:
```
1. list --> list
  - Prints the list of task you have
2. mark --> mark [index]
  - Marks the task at index to be completed                   
3. unmark --> unmark [index]
  - Unmark the task at index to be not completed
4. todo --> todo [task description]
  - Adds a todo task with task description into your tasklist
5. deadline --> deadline [task description] /by[dd-MM-yyyy HHmm]
  - Adds a deadline task with task description 
    and deadline into your tasklist
6. event --> event [task description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]
  - Adds an event task with task description, start time 
    and end time to your tasklist
7. delete --> delete [index]
  - Delete a task at index
8. find --> find [keyword]
  - Prints a list of task containing keyword in the task description
9. bye --> bye
  - Terminate the chat bot and save task list into txt file
10. help --> help
  - Prints a list of valid commands
```

## Listing all the task added : `list`

Shows a list of tasks added to the Chat Bot

Example: `list`

Expected output:
```
Here are the tasks in your list:
1.[T][X] return book
2.[T][ ] buy present
```

## Adding todo : `todo`

Adds a task with a description to the list of task

Format: `todo <description>`

Example: `todo textbook pg 1-2`

Expected output:
```
Got it! I've added this task:
[T][] textbook pg 1-2
Now you have 3 tasks in the list.
```

## Adding deadline : `deadline`

Adds a task with a description and a deadline to the list of task

Format: `deadline <description> /by <dd-MM-yy HHmm>`

Example: `deadline essay /by 14-09-2024 2020`

Expected output:
```
Got it! I've added this task:
[D][] essay (by: 14 Sep 2024, 8:20:00 pm)
Now you have 4 tasks in the list.
```

## Adding event: `event`

Adds a task with a description, a start date and time and an
end date and time to the list of task

Format: `event <description> /from <dd-MM-yy HHmm> /to <dd-MM-yy HHmm>`

Example: `event party /from 14-09-2024 1200 /to 14-09-2024 1400`

Expected output:
```
Got it! I've added this task:
[E][] party (from: 14 Sep 2024, 12:00:00 pm
to: 14 Sep 2024, 2:00:00 pm)
Now you have 5 tasks in the list.
```

## Deleting a task : `delete`

Delete a task at a specific index in the list of task

Format: `delete <index>`

Example: `delete 1`

Expected output:
```
Got it! I've removed this task:
[T][X] return book
Now you have 4 task in the list.
```