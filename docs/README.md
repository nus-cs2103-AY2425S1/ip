# Derek User Guide

<img width="1440" alt="Ui" src="https://github.com/user-attachments/assets/f7e32e23-f968-4221-b473-79a6a937d550">

Meet Derek! Your best friend, todo list, calendar all in one!  

## Adding deadlines

Derek allows you to set deadlines on your tasks

Example: deadline (task) /by (deadline)

User:
deadline complete cs2100 assignment /by 16/09/2024 13:00

Derek:
yay!
[D][] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)

## Adding todo tasks

Derek allows you to keep track of your todos

Example: todo (task)

User:
todo cs2103T iP increments

Derek:
great job!
[T][] cs2103T iP increments



## Adding events

Derek allows you to keep track of your events

Example: event (event details) /from (start time) /to (end time)

User:
event RV10 gala /from 21/09/2024 09:00 /to 21/09/2024 13:00

Derek:
you're on a roll!
[E][] RV10 gala (from: 21 Sep 2024 09:00 am to: 21 Sep 2024 01:00 pm)

## Display list

Derek stores all your given information and returns it to you as a nice list!

Example: list

User:
list

Derek:
1. [D][] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)
2. [T][] cs2103T iP increments
3. [E][] RV10 gala (from: 21 Sep 2024 09:00 am to: 21 Sep 2024 01:00 pm)

## Complete tasks

Derek even allows you to indicate task completion

Example: mark (task index)

User:
mark 1

Derek:
I think these are your tasks! Please don't leave me!!!
1. [D][X] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)
2. [T][] cs2103T iP increments
3. [E][] RV10 gala (from: 21 Sep 2024 09:00 am to: 21 Sep 2024 01:00 pm)


## Undo tasks

If you accidentally indicated a task as complete, you can unmark it

Example: unmark (task index)

User:
unmark 1

Derek:
1. [D][] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)
2. [T][] cs2103T iP increments
3. [E][] RV10 gala (from: 21 Sep 2024 09:00 am to: 21 Sep 2024 01:00 pm)

## Delete tasks

Allows you to delete tasks

Example: delete (task index)

User: 
delete 3

Derek:
1. [D][] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)
2. [T][] cs2103T iP increments


## Find tasks

Allows you to find tasks from your list using keywords

Example: find (keyword)

User:
find cs2103T

Derek:
Here you are! 
[T][] cs2103T iP increments

## List completed tasks

Derek even tells you the tasks you have conpleted in the past week 

Example: completed

User:
completed

Derek:
Here are your tasks completed in the past week, I think you can do better
1. [D][X] complete cs2100 assignment (by: 16 Sep 2024 01:00 pm)


