# Tanjiro Bot 
![Screenshot of the Tanjiro Bot](/docs/Ui.png)

## Description:
Tanjiro Bot is an intelligent assistant bot that helps you stay organised and productive. It provides various commands for the user to use:
## Various types of commands:

### 1. List Command
This command outputs all tasks existing in the current task list.

Sample Input:
```agsl
list
```
Sample output:
```
1. [T][_] sleep
2. [T][_] exercise
3. [D][_] ip project  (by: 2024-12-12 23:59 pm)
4. [E][_] exam  (from: 2024-10-10 15:00 pm to: 2024-10-10 17:00 pm)
5. [T][X] play
6. [T][_] study
7. [T][X] shower
8. [D][_] assignment  (by: 2024-11-11 11:11 am)
```
### 2. Todo Command
This command adds a "todo" task into the current task list.

Instructions: Input the task name after the todo command.

Input:
```
todo ____
```
Sample Input:
```
todo sleep
```
Sample Output:
```
Got it. I've added this task:
[T][_] sleep
Now you have 9 tasks in the list.
```
### 3. Deadline Command
This command adds a "deadline" task into the current task list.

Instructions: Input the task name followed by a date and time, in this format: "yyyy-mm-dd HHmm".

Input:
```
deadline ____ /by ____
```
Sample Input:
```
deadline play computer /by 2024-12-12 1212
```
Sample Output:
```
Got it. I've added this task:
[D][_] play computer (by: 2024-12-12 12:12 pm)
Now you have 10 tasks in the list.
```

### 4. Event Command
This command adds an "event" task into the current task list.

Instructions: Input the task name followed by two date and time, in this format: "yyyy-mm-dd HHmm".

Input:
```
event ___ /from ____ /to ____
```

Sample Input:
```
event music festival /from 2024-12-30 1800 /to 2024-12-31 0000
```

Sample Output:
```
Got it. I've added this task:
[E][_] music festival (from: 2024-12-30 18:00 pm to: 2024-12-31 00:00 am)
Now you have 11 tasks in the list.
```

### 5. Find Command
This command find the relevant tasks in the current task list based on a given name and display it in a list.

Instructions: Input the task name you are looking for. The bot will display the relevant tasks in a list format to you.

Input:
```
find ___
```
Sample Input:
```
find sleep
```
Sample Output:
```
Here are the matching tasks in your list:
1. [T][X] sleep
2. [T][_] sleep
```

### 6. View Command
This command find the relevant tasks in the current task list based on a given date.

Instructions: Input the given date in this format: "yyyy-mm-dd".

Input:
```
view ___
```
Sample Input:
```
view 2024-12-12
```
Sample Output:
```
Here are the matching tasks for this date: 2024-12-12
1. [D][_] ip project (by: 2024-12-12 23:59 pm)
2. [D][_] play computer (by: 2024-12-12 12:12 pm)
```

### 7. Mark Command
This command marks the relevant task given the index in the list.

Instructions: Input the index in the list.

Input:
```
mark _
```
Sample Input:
```
mark 4
```
Sample Output:
```
Nice! I've marked this task as done:
[X] exam
```

### 8. Unmark Command
This command unmarks the relevant task given the index in the list.

Instructions: Input the index in the list.

Input:
```
unmark _
```
Sample Input:
```
unmark 4
```
Sample Output:
```
OK, I've unmarked this task as not done yet:
[_] exam
```

### 9. Delete Command
This command deletes the relevant task given the index in the list.

Instructions: Input the index in the list.

Input:
```
delete _
```
Sample Input:
```
delete 9
```
Sample Output:
```
Noted. I've removed this task:
[T][_] sleep
Now you have 10 tasks in the list.
```

### 10. Bye Command
This commands ends the service with Tanjiro Bot. However, all relevant tasks has been saved.

Sample Input:
```
Bye
```
Sample Output:
```
Bye! Hope to see you again!
```
