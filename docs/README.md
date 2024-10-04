# Sora User Guide

Sora is a personal app for managing Todo, Deadline & Event Tasks,
optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Sora can get your management tasks done faster than traditional GUI apps.


![Sora UI](./Ui.png)


## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file from here.
3. Copy the file to the folder you want to use as the home folder for your Sora.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar sora.jar command to run the application.
A GUI similar to the below should appear in a few seconds.


## Features


### Access Help Page

Displays all Features, including Format.

Format: `help`


### List Tasks

Displays a List of all current Tasks.

Format: `list`

Expected output:
```
1. [T][X] Todo Task
2. [D][ ] Deadline Task (by: 12pm)
3. [E][X] Event Task (from: Mar 03 2003 03:33 to: Mar 31 3033 13:33)
```


### Marking Tasks

Marks a Task as Done.

Format: `mark <Task Number>`

Example: `mark 1`

Expected output:
```
Nice! Sora has marked this task as done:
[T][X] Task
```


### Unmarking Tasks

Marks a Task as Not Done.

Format: `unmark <Task Number>`

Example: `unmark 1`

Expected output:
```
Nice! Sora has marked this task as not done:
[T][ ] Task
```


### Adding TODO Tasks

Adds a Task as a TODO.

Format: `todo <Task>`

Example: `todo Task`

Expected output:
```
Got it. Sora has added this task:
[T][ ] Task
Now, you have 1 tasks in your list.
```


### Adding DEADLINE Tasks

Adds a Task as a DEADLINE.

Format: `deadline <Task> /by <Time>`

Example: `deadline Task /by 12pm `

Expected output:
```
Got it. Sora has added this task:
[D][ ] Task (by: 12pm)
Now you have 2 tasks in your list
```


### Adding EVENT Tasks

Adds a Task as a EVENT.

Format: `event <Task> /from <Time> /to <Time>`

Example: `event Task /from 3/3/2003 0333 /to 31/3/3033 1333 `

Expected output:
```
Got it. Sora has added this task:
[E][ ] Task (from: Mar 03 2003 03:33 to: Mar 31 3033 13:33
Now you have 3 tasks in your list
```


### Deleting Tasks

Deletes a Task from current Tasks.

Format: `delete <Task Number>`

Example: `delete 1`

Expected output:
```
Noted. Sora has removed this task:
[T][ ] Task
```


### Finding Tasks

Finds all Tasks matching the Searched String. 

Format: `find <String>`

Example: `find Book`

Expected output:
```
1. [T][X] Read Book
4. [D][ ] Return Book (by: 12pm)
```
