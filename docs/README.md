# Boombotroz User Guide

![Screenshot of my chatbot's GUI when it is ran](./Ui.png)

Boombotroz is a interactive chatbot that helps the user to keep track of the tasks they have in their lives. 

Boombotroz offers the tracking of 3 types of task: 
- ToDo
- Deadline
- Event

Modification to these tasks can be done using commands stated below.


## Saving Data

- Data is saved everytime a command that modifies the task list is executed.
- Data is stored in a text file `data.txt` that is found in the application's src folder.

## Features

### Command Format:
- Words in `[square brackets]` to be determined by the user.  
  Example: `todo [description]` requires user to supply their own **description**.
- Parameters that use date are to be preferably given in the `YYYY-MM-DD` format.   
  Example: `2025-06-06`

### Available Commands:


#### 1. `list`
Lists all current tasks in the task list.

The tasks are sorted in order of priority, with highest priority at the top.

Example: list
```
Here are the tasks in your list:
1.[D][X][5] finish iP (by: Sep 20 2024)
2.[T][ ][2] exercise at the gym
3.[T][ ][1] take a nap
```

#### 2. `mark [task index]`
Marks a task to indicate completion.

Example: mark 3
```
Nice! I've marked this as done:
[T][X][1] take a nap
```

#### 3. `unmark [task index]`
Unmarks a task to indicate incompletion.

Example: unmark 3
```
OK, I've marked this task as not done yet:
[T][ ][1] take a nap
```

#### 4. `delete [task index]`
Deletes a task from the task list and informs of number of tasks currently.

Example: delete 3
```
Noted. I've removed this task: 
  [T][ ][1] take a nap
Now you have 2 tasks in the list.
```

#### 5. `find [keyword]`
Finds tasks containing the specified keyword.

Example: find exercise
```
Here are the matching tasks in your list:
1.[T][ ][2] exercise at the gym
```

#### 6. `todo [description] /prior [level]`
Adds a todo task with its priority level to the list.

Informs of number of tasks currently.

Example: todo feed my dog /prior 3
```
Got it. I've added this task:
  [T][ ][3] feed my dog
Now you have 3 tasks in the list.
```

#### 7. `deadline [description] /by [date] /prior [level]`
Adds a deadline task with a specified date/time and its priority level to the list.

Informs of number of tasks currently.

Example: deadline clean the house /by 2025-06-06 /prior 2
```
Got it. I've added this task:
  [D][ ][2] clean the house (by: Jun 06 2025)
Now you have 4 tasks in the list.
```

#### 8. `event [description] /from [start time] /to [end time] /prior [leve]`
Adds an event task with a specified start and end date/time, and its priority level to the list.

Informs of number of tasks currently.

Example: event conference meeting /from 2025-06-06 /to 2025-06-07 /prior 5
```
Got it. I've added this task:
  [E][ ][5] conference meeting (from: Jun 06 2025 to: Jun 07 2025)
Now you have 5 tasks in the list.
```

## Credits
- https://github.com/nigeltzy/ip
- https://chpic.su/en/stickers/BubuAndDudu21_Sbor/038/
- https://i.scdn.co/image/ab6761610000e5eb8a3dd5854574c440b83e42c6
