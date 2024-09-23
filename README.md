# Optimus User Guide

## Introduction
_Ever wanted to note down your things?  
Ever thought that writing down important things on calendar was tiring?_  
**Well here is my solution: Optimus**  

Optimus is your own personal task manager  
With Optimus you can add **Todo, Deadline and Event** based tasks  
Futher commands are listed below!
Have fun!


## Features  
### Adding Todo task

_Adds a Todo task in Optimus_  
Command: `todo`  
Example: `todo eat food`  
Expected output:
```
Got it. I've added this task:  
 [T][] eat food  
Now you have 1 task in the list.
```
### Adding Deadline task 

_Adds a Deadline task in Optimus_  
Command: `deadline`  
Example: `deadline submit hw /by 22/02/2024 16:00`  
Expected output:
```
Got it. I've added this task:  
 [D][] submit hw (by: Feb 22 2024, 4:00pm)  
Now you have 2 tasks in the list.
```

### Adding Event task

_Adds a Event task in Optimus_  
Command: `event`  
Example: `event take photos /from 22/02/2024 16:00 /to 22/02/2024 20:00`  
Expected output:
```
Got it. I've added this task:  
 [E][] take photos (on: Feb 22 2024, 4:00pm - Feb 22 2024, 8:00pm)  
Now you have 3 tasks in the list.
```

### List all tasks
_Lists out all tasks in Optimus_  
Command: `list`  
Example: `list`  
Expected output:
```
Here are the tasks in your list:  
1.[T][] eat food 
2.[D][] submit hw (by: Feb 22 2024, 4:00pm)
3.[E][] take photos (on: Feb 22 2024, 4:00pm - Feb 22 2024, 8:00pm)  
```
### Mark a task as done
_Mark tasks as complete in Optimus_  
Command: `mark`  
Example: `mark 1`  
Expected output:
```
Nice I've marked this task as done:
 [T][X] eat food
```
### Unmark a task as incomplete
_Unmark incomplete tasks in Optimus_  
Command: `unmark`  
Example: `unmark 1`  
Expected output:
```
OK, I've marked this task as not done yet:
 [T][] eat food
```
### Delete tasks
_Delete tasks in Optimus_  
Command: `delete`  
Example: `delete 1`  
Expected output:
```
Noted. I've removed this task:  
 [T][] eat food  
Now you have 2 tasks in the list.
```
### Find tasks

_Finds all related tasks according to input in Optimus_  
**You can use more than one keyword to search**  
Command: `find`  
Example: `find hw`  
Expected output:
```
Here are the matching tasks in your list:
 1.[D][] submit hw (by: Feb 22 2024, 4:00pm)
```
### Exit Optimus
_Closes the program in Optimus and stores your changes_  
Command: `bye`  
Example: `bye`  
Expected output:
```
Bye! Hope to see you again soon!
```
