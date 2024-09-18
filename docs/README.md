# Blitz User Guide

<img src="Ui.png" alt="UI Screenshot" width="300"/>

Blitz is a interactive chatbot to help plan tasks. Save important deadlines, dates all using your keyboard!! 

## General Instructions
1. All tasks must have descriptions 
2. All dates must follow a dd/MM/yyyy format 
3. Add tags at the end, after date and time
4. Commands such as `list` do not take parameters 

## Adding todo
Add a todo task using the command below
```
todo math homework
```

## Adding deadlines
Add a deadline task using the command below 
```
deadline assignment 1 submission /by 12/4/2024 0800
```
**Note:** Time is optional

## Adding events 
Add a event task using the command below
```
event wedding /from 10/10/2024 1700 /to 10/10/2024 1900
```
**Note:** Time is optional

### TAGS!!
Add tags to tasks to filter by tags
```
deadline assignment 1 submission /by 12/4/2024 0800 -t urgent difficult
```
Filter by tags using `find -t`

### Filter tasks 
Tasks can be filtered by:
1. Description
2. Tags

```
find -t urgent
find assignment
```
Without flag (e.g. `-t`) find defaults to filtering by description

### Mark Tasks as done / undone
```dtd
list
mark 1
unmark 3
```
`list` is usually called  beforehand so user can see the indexes of the tasks and mark/unmark the appropriate task 


Output **before** marking: `1. [T][ ] science hw tags: no tags`

Output **after** marking: `1. [T][X] science hw tags: no tags`

### Delete tasks
```dtd
list
delete 1
```
`list` is usually called  beforehand so user can see the indexes of the tasks and delete the appropriate task

### Exit
The `bye` command prints a farewell message but the program will still be running. Please exit, stop running the app to close it. 

Don't worry about saving, it is done automatically for you!! :blush:
