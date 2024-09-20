# Nimbus User Guide

![Screenshot](Ui.png)

#### Introducing Nimbus!
A cute cloud that keeps track of your daily tasks.  
It can handle todo tasks, tasks with deadlines and also events!

## Add Tasks

### Add Todo Task
This adds a todo task which is any task that does not have any due dates.  
##### Format:  
```
todo Cut the apple
```
The task will be created and added to the existing task list.  

##### Expected Output:
```
Nimbus added this: 
[T][ ] Cut the apple
```

### Add Deadline Task
This adds a deadline task which is a task that has a deadline.  
##### Format:  
```
deadline quiz /by 22/8/2024 1200
```
The task will be created and added to the existing task list.  

##### Expected Output:
```
Nimbus added this: 
[D][ ] quiz (by: Aug 22 2024 12:00 pm)
```

### Add Event Task
This adds an event task which is a task that has a start and end time.  
##### Format:  
```
event floor event /from 25/9/2024 1400 /to 1900
```
The task will be created and added to the existing task list.  

##### Expected Output:
```
Nimbus added this: 
[E][ ] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
```

## Delete Task
This deletes a task when provided an index.  
##### Format:  
```
delete 1
```
The task will be deleted from the existing task list.  

##### Expected Output:
```
Nimbus has removed the task! 
    [T][ ] Cut the apple
```

## List Task
This lists out all existing tasks in the task list 
##### Format:  
```
list
```
Prints all existing tasks in the task list.  

##### Expected Output:
```
Nimbus says this is your list: 
1. [E][ ] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
```

## Mark Task
This marks a task as done.  
##### Format:  
```
mark 1
```
The task will be marked done in the existing task list.  

##### Expected Output:
```
Nimbus shall mark this as done:
    [E][X] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
```

## Unmark Task
This marks a task as undone.  
##### Format:  
```
unmark 1
```
The task will be marked undone in the existing task list.  

##### Expected Output:
```
Nimbus shall mark this as not done:
    [E][ ] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
```

## Find Task
Based on the provided keyword, find the task in the task list.  
##### Format:  
```
find floor event
```
Prints a list of tasks that contain the keyword in their task descriptions.  

##### Expected Output:
```
1. [E][ ] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
Here are your results :3
```

## Check Task
Based on the provided date time, find tasks due on the date or events happening on the date.  
##### Format:  
```
check 25/9/2024
```
Prints a list of tasks that either due on the selected date or happening on the date.  

##### Expected Output:
```
1. [E][ ] floor event (from: Sep 25 2024 2:00 pm to: Sep 25 2024 7:00 pm)
Nimbus reminds you that these tasks are due on 2024-09-25
```

## Ending the program
Ends the program and saves all changes in text file.  
GUI will close after 3 seconds of wait time.
##### Format:  
```
bye
```
Prints a goodbye message.  

##### Expected Output:
```

     .-') _             _   .-')    .-. .-')                  .-')    
    ( OO ) )           ( '.( OO )_  \  ( OO )                ( OO ).  
,--./ ,--,'    ,-.-')   ,--.   ,--.) ;-----.\   ,--. ,--.   (_)---\_) 
|   \ |  |\    |  |OO)  |   `.'   |  | .-.  |   |  | |  |   /    _ |  
|    \|  | )   |  |  \  |         |  | '-' /_)  |  | | .-') \  :` `.  
|  .     |/    |  |(_/  |  |'.'|  |  | .-. `.   |  |_|( OO ) '..`''.) 
|  |\    |    ,|  |_.'  |  |   |  |  | |  \  |  |  | | `-' /.-._)   \ 
|  | \   |   (_|  |     |  |   |  |  | '--'  / ('  '-'(_.-' \       / 
`--'  `--'     `--'     `--'   `--'  `------'    `-----'     `-----'  
BAIBAI! NIMBUS WEEEEEEEEE
------------------------------------------------------------------------
```

## Handling of exceptions
There are multiple exceptions to catch invalid user input and they  
provide guidance on the correct way to format your input!

# Hope that you enjoy using Nimbus!!!
