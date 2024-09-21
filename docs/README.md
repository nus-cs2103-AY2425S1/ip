# Bing User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
![Product Screenshot](Ui.png)

// Product intro goes here
## Introduction

Welcome to **Bing**, your personalised task management assistant. With Bing, you can effortlessly manage your tasks, deadlines, and events. Bing's intuitive commands help you stay organized and on top of your schedule, offering a clean interface for both storing, marking and finding tasks.


## Features

### Viewing Tasks
Users can view the stored tasks using the command list
Example - 
```
list
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] read book
______________________________
```

### Adding ToDos
Users can add a ToDo task to the list using the commmand - todo [task name]
Example -
```
todo assignment
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] read book
2. [T][] assignment
______________________________
```

### Adding Deadlines
Users can add a Deadline task to the list using the commmand - deadline [task name] /by [DD/MM/YYYY HHMM]
Example -
```
deadline return book /by 08/12/2024 1600
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] read book
2. [T][] assignment
3. [D][] return book (by: Dec 2 2019 1800)
______________________________
```

### Adding Events
Users can add an Event  to the list using the commmand - event [event name] /from [DD/MM/YYYY HHMM] /to [DD/MM/YYYY HHMM]
Example -
```
event function /from 08/12/2024 1200 /to 08/12/2024 1600
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] read book
2. [T][] assignment
3. [D][] return book (by: Dec 2 2019 1800)
4. [E][] function (from: Dec 8 2024 1200 to: Dec 8 2024 1600)
______________________________
```

### Finding Tasks
Users can find a task in the list using the commmand - find [keyword]
Example -
```
find book
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] read book
2. [D][] return book (by: Dec 2 2019 1800)
______________________________
```

### Deleting Tasks
Users can delete a task in the list using the commmand - delete [task number]
Example -
```
delete 1
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] assignment
2. [D][] return book (by: Dec 2 2019 1800)
3. [E][] function (from: Dec 8 2024 1200 to: Dec 8 2024 1600)
______________________________
```

### Marking Tasks
Users can mark a task as done in the list using the commmand - mark [task number]
Example -
```
mark 1
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][X] assignment
2. [D][] return book (by: Dec 2 2019 1800)
3. [E][] function (from: Dec 8 2024 1200 to: Dec 8 2024 1600)
______________________________
```

### Unmarking Tasks
Users can unmark a task in the list using the commmand - unmark [task number]
Example -
```
unmark 1
```
Expected output -
```
______________________________
All tasks in your list:
1. [T][] assignment
2. [D][] return book (by: Dec 2 2019 1800)
3. [E][] function (from: Dec 8 2024 1200 to: Dec 8 2024 1600)
______________________________
```

### View statistics
Users can see statistics such as total tasks in the list, number of marked tasks in the list and number of unmarked tasks in the list using the command - stats
Example -
```
stats
```
Expected output -
```
Total tasks : 3
Marked tasks : 0
Unmarked tasks : 0 
```

### Exiting application
Users can exit the application using the command - bye
Example -
```
bye
```
Expected output -
```
Bye!
Have a good day !
```

// Describe the action and its outcome.

// Give examples of usage

//Example: `keyword (optional arguments)`

// A description of the expected outcome goes here
