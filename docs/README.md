# Alice User Guide

<img src="/docs/Ui.png" alt="Product screenshot" width="250"/>

Alice is a chatbot that tracks all your tasks 

## Features

### Add todo task

Example of usage:

`todo cs2100 assignment 1`

Expected outcome:

```
Got it. I've added this task: 
[T][ ] cs2100 assignment 1
Now you have 1 tasks in the list
```

###  Add deadline task

Example of usage:

`deadline essay /by 20-10-2024 1800`

Expected outcome:

```
Got it. I've added this task: 
[D][ ] essay (by: Oct 20 2024 18:00)
Now you have 1 tasks in the list.
```

### Add event task

Example of usage:

`event read book /from 30-10-2024 1800 /to 30-10-2024 2000`

Expected outcome:

```
Got it. I've added this task: 
[E][ ] read book (from: Oct 30 2024 18:00 to: Oct 30 2024 20:00)
Now you have 1 tasks in the list.
```

### Delete task

Example of usage:

`delete 1`

Expected outcome:

```
Noted, I've removed this task:
[T][ ] cs2100 assignment 1
Now you have 1 tasks in your list.
```

### Mark as completed


Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] cs2100 assignment 1
```

### Mark as not completed


Example of usage:

`unmark 1`

Expected outcome:

```
Ok, I've marked this task as not done yet:
[T][ ] cs2100 assignment 1
```
### List

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list: 
1. [T][X] cs2100 assignment 1
2. [E][ ] read book (from: Oct 30 2024 18:00 to: Oct 30 2024 20:00)
```

### Find task by keyword

Example of usage:

`find assignment`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] cs2100 assignment 1
```

### Bye

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```