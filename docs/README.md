# Bill User Guide

![Ui.png](Ui.png)

Bill bot is your personalised task tracker! 
With Bill you will be able to efficiently manage your tasks using an interactive GUI.
Bill guarantees you will stay on top of your tasks at all times! 


## Help, a user guide

Gives the user a guide on all possible commands. To use this command type:
```
help
```

This will return a brief guide on all possible commands the user can use. To find the exact formatting 
of each command, simply type in one of the commands, and the suggested format will appear. 


## Adding todo tasks
Allows the user to add a todo task. To use this command type:

```
todo [description]
```
Example:
```
todo juggle
```

This will allow the user to add a simple todo task. 


## Adding deadline tasks
Allows the user to add a deadline task. To use this commands type:
```
deadline [description] /by [month day year] 
```
Example:
```
deadline climb /by Dec 10 2024
```

or an alternative formatting for date: 
```
deadline [description] /by [year-month-date]
```
Example:
```
deadline climb /by 2024-12-10
```
This allows the user to add a deadline task. Note that deadline tasks must follow one of the 
two specified formatters for date. If the user fails to adhere to this a message will appear suggesting
the correct format. 


## Adding event tasks
Allows the user to add event tasks that spans a duration. To use this commands type:
```
event [description] /from [String] /to [String]
```
Example:
```
event watch /from Sunday /to Monday
```
This allows users to add events spanning over time. To allow for flexibility, unlike deadline above, events
do not need to adhere to strict date formatting. This allows more customizability for the user. 


## Marking a task as complete 
Allows the user to mark a task as complete. To use this commands type:
```
mark [task number]
```
Example:
```
mark 1
```

This allows users to check off tasks that have been completed. Furthermore, after marking the task, 
Bill will state which task was marked. Only tasks with valid tasks numbers can be marked. 


## Unmarking a task as not complete
Allows the user to unmark a task as incomplete. To use this commands type:
```
unmark [task number]
```
Example:
```
unmark 1
```

This allows users to unmark tasks that have yet to be completed. Furthermore, after unmarking the task,
Bill will state which task was unmarked. Only tasks with valid tasks numbers can be unmarked.


## Show list of tasks
Allows the user to see all current tasks. To use this command type: 
```
list
```
This allows users to have a high level overview of all tasks. It will also show the types of task and
if they have been marked or not. 

## Find tasks matching your description 
Allows the user to see all current tasks whose descriptions match the user's description. To use this command type:
```
find [description] 
```
Example:
```
find climb
```
This command is similar to the list command above, but now returns a filtered lists of tasks whose description 
matches that of the user. This is partial matching, thus as long as the user input in a subset of the possible 
descriptions those matching tasks will be returned. 


## Deleting tasks
Allows the user to delete tasks which are no longer needed. To use this command type:
```
delete [task number]
```
```
delete 1
```
This allows the user to delete tasks from the list of tasks. Furthermore, after deleting the task, 
Bill will state which task was deleted. Only tasks with valid tasks numbers can be deleted.


## Bye shuts down Bill
Allows the user to shut down Bill bot. To use this command type:
```
bye
```

This allows the user to shut down Bill bot. Bill will say goodbye to the user and the input and send 
fields will be disabled. Thus, the user can no longer use Bill, to close the application press the cross symbol 
on the top right. 