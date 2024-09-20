# EmoteX User Guide

![image](https://github.com/user-attachments/assets/b3fc3c08-b3e0-4a99-958b-fc1dfbec02b2)


***Welcome to EmoteX! 
 Your go to app for keeping track of all your tasks, deadlines and events admist your hectic schedule! :)
 Have a look at all our features before trying out EmoteX!***

# ***Features***

Dates:
- can be sepcified in 2 formats 
e.g. yyyy-MM-dd or dd/MM/yyyy

Time:
- can be sepcified in only 1 format
e.g. 16:00

Event:
- all fields (start Time, end Time & date must be specified)

Deadline:
- can be stored in 3 formats
- only date
- only time
- date and time

Unrecognised parameters will result in a help guide being shown

 ## Guide : hi
*Shows an intro message followed by a guide on how to use the features*
 ![image](https://github.com/user-attachments/assets/bfdccc12-ac80-48b0-b4e8-d3e6420bc878)

## Adding deadlines : deadline 

*Adds a deadline task to the list of tasks and shows successfully added messsage*

![image](https://github.com/user-attachments/assets/94a44d7e-3e2d-4dc1-9067-84e95d07336e)


Format: deadline TASK /by DATE TIME(optional)

## Adding todos : todo 

*Adds a todo task to the list of tasks and shows successfully added messsage*

![image](https://github.com/user-attachments/assets/9063b806-5934-4f34-a775-209d3b623fed)

Format: todo TASK 

## Adding events : event 

*Adds a event to the list of tasks and shows successfully added messsage*

![image](https://github.com/user-attachments/assets/718ef384-40f8-49d8-9308-4894c84339cb)

Format: event TASK /from STARTTIME /to ENDTIME /on DATE 

## Deleting task : delete 

*Deletes task at sepcifed index*

![image](https://github.com/user-attachments/assets/4aed6628-6fc1-4297-9d0b-2ac4a5028093)

Format: delete INDEX

## Updating task : update 

*Updates task at sepcifed index with the given new value*

### deadline
Format_1 (deadline): update INDEX datetime /to NEWDATETIME

- for deadline task with both date and time
![image](https://github.com/user-attachments/assets/717608dd-7b97-493b-bcf8-77f3c4727bd9)

Format_2 (deadline): update INDEX date /to NEWDATE

- for deadline task with only date
  
![image](https://github.com/user-attachments/assets/e9ce0603-3dc5-4fdb-9306-c6d5fd9318ae)

Format_3 (deadline): update INDEX time /to NEWTIME

- for deadline task with only time
  
![image](https://github.com/user-attachments/assets/a1f2ab9a-befb-42bd-9ad7-4fca1efd3c42)

### event
Format_1 (event): update INDEX date /to NEWDATE
![image](https://github.com/user-attachments/assets/17e47e0e-d095-4f72-89d0-f9b6e50f04bf)

Format_2 (event): update INDEX startTime /to NEWTIME
![image](https://github.com/user-attachments/assets/4079782f-ac94-4043-9aa1-aa9e1ead5f50)

Format_3 (event): update INDEX endTime /to NEWTIME
![image](https://github.com/user-attachments/assets/51f36814-a1f2-489a-ad86-bbc92fa34131)

## Marking task : mark 

*Marks task at sepcifed index as completed*

![image](https://github.com/user-attachments/assets/18974595-20f6-4a98-9651-4c19bb63095f)

Format: mark INDEX

## Unmarking task : unmark 

*Marks task at sepcifed index as incomplete*

![image](https://github.com/user-attachments/assets/44545c6a-02cb-4825-8aaf-98d2ee3301a8)

Format: unmark INDEX

## Finding task : find 

*Shows tasks that contains keywords*

![image](https://github.com/user-attachments/assets/5ee87ae7-8b82-48eb-80ba-05e369becd9a)

Format: find KEYWORDS

## Due task : due 

*Shows tasks that are due on given date*

![image](https://github.com/user-attachments/assets/02b078db-9ca8-4ce5-ac23-0ea3fe40fa5c)

Format: due DATE 

## View task : list 

*Shows tasks in task list*

![image](https://github.com/user-attachments/assets/eccde4d2-287b-4240-9ddc-35f78eab12d2)

Format: list 

## Finish task : bye 

*Shows exit message*

![image](https://github.com/user-attachments/assets/382b1f07-3f07-48f9-9da9-ab8797408e9d)

Format: bye 

## Saving the data
EmoteX task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.






