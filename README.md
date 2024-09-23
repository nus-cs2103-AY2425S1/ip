# Elysia User Guide

![](docs/Ui.png)

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