# Elysia User Guide

![](Ui.png)

This chatbot will manage your tasks for you! Personality heavily inspired by Elysia from Honkai Impact 3.

## Commands



1. list 
   - lists all the current tasks in the tasklist
   - Example: ```list```
2. find
   - finds the task based on the string given(will match description)
   - Usage: find [string]
   - Example: ```find test```
3. mark
   - marks the task as completed. An [x] will be shown when listed
   - Usage: mark [task number]
   - Example: ```mark 2```
4. unmark
   - unmarks the task as uncompleted. A [ ] will be shown when listed
   - Usage: umark [task number]
   - Example: ```unmark 2```
5. delete
   - removes the task from the list
   - Usage: delete [task number]
   - Example: ```delete 3```
6. todo
   - adds a todo task with only a description to the list
   - use r tag to indicate recurring task
   - Usage: todo [OPTIONAL: r] [description]
   - Example: ```todo r finish ip```
7. event
   - adds an event with description, start date, and end date
   - use r tag to indicate recurring task
   - Date formats accepted: YYYY-MM-DD
   - Usage: event [OPTIONAL: r] [description] /from [START_DATE] /to [END_DATE]
   - Example: ```event Midterms everyday... /from 2024-09-1 /to 2024-09-10```
8. deadline
   - adds a deadline with description and date
   - use r tag to indicate recurring task
   - Date formats accepted: YYYY-MM-DD
   - Usage: deadline [OPTIONAL: r] [description] /by [BY_DATE]
   - Example: ```deadline r finish 2100 quiz /by 2024-08-23```
9. bye
   - Saves the current list of tasks in data.txt
   - Currently does not close the application
   - Example: ```bye```

## Resources
  elysia image: https://i.pinimg.com/736x/cd/0e/0d/cd0e0dbb19f35e33bb6e68b4f47d0db8.jpg 

  captain image: https://static.wikia.nocookie.net/houkai3rd/images/c/cd/Captain_Hyperion.jpg/revision/latest?cb=20180516154002

  background image: https://upload-os-bbs.hoyolab.com/upload/2024/03/18/389611819/e648d4bfbec6d9e98084a8ccd469229c_3486531063307202938.jpg?x-oss-process=image%2Fresize%2Cs_1000%2Fauto-orient%2C0%2Finterlace%2C1%2Fformat%2Cwebp%2Fquality%2Cq_70