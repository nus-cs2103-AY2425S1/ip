# ChatgptMoreOOP User Guide
<img width="423" alt="UI" src="https://github.com/user-attachments/assets/6a2e4b5b-b25d-41f3-b3bb-4d27b552907d">

## introduction
chatgptMoreOOP is a desktop chatbot app designed for task managing. With **simple but effective command on a clear interface** the user can do task management easily.

## Features

### list
showing all the task in the tasklist with its index and priority

format:
> list

### todo
Adding a todo task to the tasklist, priority can be specified, but it's optional. Priority should be an integer.

format:
> todo DESCRIPTION p PRIORITY

> todo DESCRIPTION

### deadline
Adding a deadline task to the tasklist with a specified date in the form of yyyy-mm-dd, priority can be specified, but it's optional. Priority should be an integer.

format:
> deadline DESCRIPTION by yyyy-mm-dd p PRIORITY

> deadline DESCRIPTION by yyyy-mm-dd

### event
Adding a event task to the tasklist with a starting time and an ending time, priority can be specified, but it's optional. Priority should be an integer.

format:
> event DESCRIPTION from STARTING_TIME to ENDING_TIME p PRIORITY

> event DESCRIPTION from STARTING_TIME to ENDING_TIME

illustration:
<img width="431" alt="image" src="https://github.com/user-attachments/assets/1b9b2708-5486-4df2-b0f9-41083740f9e2">

### mark
mark an event as done, finding the event by using its index in the list

format:
> mark INDEX

### unmark
reset the event as not finished yet

format:
> unmark INDEX

### find
finds tasks that contain the keyword

format:
> find KEYWORDS

### delete
delete a task through a given index

format:
> delete INDEX

### update
update task description or priority

format:
> update INDEX d DESCRIPTION p PRIORITY

> update INDEX d DESCRIPTION

> update INDEX p PRIORITY