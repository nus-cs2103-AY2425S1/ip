# ChatgptMoreOOP User Guide
<img width="423" alt="UI" src="https://github.com/user-attachments/assets/6a2e4b5b-b25d-41f3-b3bb-4d27b552907d">

## introduction
chatgptMoreOOP is a desktop chatbot app designed for task managing. With **simple but effective command on a clear interface** the user can do task management easily.

## prerequiste
Ensure you have Java 17 installed on your system. You can verify your Java version by running the following command in your terminal or command prompt:
```angular2html
java -version
```
should output
```angular2html
java version "17.0.x"
```

## Steps to Test
1. download the jar file main.jar
```angular2html
https://github.com/lancehaha/ip/releases/tag/A-Release
```
2. open your terminal and go to the directory where you settle the jar file
+ ```angular2html
  cd <the folder>
  ```
3. run the jar file with java17 and javafx components
+ ```angular2html
  java -jar ChatgptMoreOOP.jar
  ```

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
