# Nameless User Guide

![Ui.png](Ui.png)

## View Tasks

To view all tasks in the list.

Usage: `list`

Expected Output:
```
Here are the tasks in your list:
    1. [T][X] join CCA
    2. [D][ ] return book (by: Aug-12-2024 1200 am)
    3. [E][ ] dental appointment (from: Sep-13-2024 1000 to: Sep-13-2024 1300)
```

## Add Tasks
There are 3 ways of adding tasks: Todo, Deadline, Event.
Note for date and time, the format is `yyyy-MM-dd HH:mm am/pm`

Usage:
1. Todo: `todo <description>`
2. Deadline: `deadline <description> /by <yyyy-MM-dd> <HH:mm am/pm>`
3. Event: `event <description> /from <yyyy-MM-dd> <HH:mm am/pm> /to <yyyy-MM-dd> <HH:mm am/pm>`

Example:
1. Todo: `todo join CCA`
2. Deadline: `deadline return book /by 2024-08-12 12:00 am`
3. Event: `event dental appointment /from 2024-09-13 10:00 am /to 2024-09-13 01:00 pm`

Expected Output:
```
Got it. I've added this task:
    [T][ ] join CCA
Now you have 1 tasks in the list.

Got it. I've added this task:
    [D][ ] return book (by: Aug-12-2024 0000)
Now you have 2 tasks in the list.

Got it. I've added this task:
    [E][ ] dental appointment (from: Sep-13-2024 1000 to: Sep-13-2024 1300)
Now you have 3 tasks in the list.
```

## Mark/Unmark Task
To mark tasks as done or unmark tasks as not done

Usage:
1. Mark: `mark <number>`
2. Unmark: `unmark <number>`

Example:
1. Mark: `mark 1`
2. Unmark: `unmark 1`

Expected Output:
```
Nice! I've marked this task as done:
    [T][X] join CCA

OK, I've unmarked this task as not done yet:
    [T][ ] join CCA
```

## Delete Task
To Delete tasks

Usage:
1. Delete: `delete <number>`

Example:
1. Delete: `delete 1`

Expected Output:
```
Noted. I've removed this task:
    [T][X] join CCA
```

## Find Task
To find tasks from words given

Usage:
1. Find: `find <keyword(s)>`

Example:
1. Find: `find book`

Expected Output:
```
Here are the matching tasks in your list:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: Aug-12-2024 0000)
```

## View Task
To view oustanding task by date

Usage:
1. View: `view <yyyy-MM-dd>`

Example:
1. View: `view 2024-08-12`

Expected Output:
```
Here are the tasks on 2024-08-12 in your list:
1. [D][ ] return book (by: Aug-12-2024 0000)
```

## Save and Load Tasks
Automatically save your tasks and load them when program runs.

## Exit 
Terminate the program by typing `bye`

Usage
1. Bye: `bye`

Tasks list will be saved when you type `bye` so next launch the history still persists 
