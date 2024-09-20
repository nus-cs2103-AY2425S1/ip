# Rose User Guide 🌹

Rose is your **personal task manager chatbot**. Rose helps you manage your tasks via a simple Command-Line Interface (CLI) while still having a Graphical User Interface (GUI). By allowing you to add, delete, mark, find, and tag your task, Rose ensures you stay on top of what matters most!


## Adding a Task

### 1. ToDo Task
Adds a task that needs to be done without any date/time attached to it.

**Format:** `todo <TASK_DESCRIPTION>`

**Examples:**
- `todo read class materials`
- `todo do LAT2201 homework`
- `todo laundry`

**Expected output:**
```angular2html
Got it~~ I've added this task :
    [T][] read class materials
Now you have 7 tasks in the list.
```

### 2. Deadline Task
Adds a task that needs to be done before a specific date/time.

**Format:** `deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd>`

**Examples:**
  - `deadline LAT2201 homework /by 2024-09-20`
  - `deadline submit CCA proposal /by 2024-10-01`
  - `deadline apply for TA position /by 2024-12-01`

**Expected output:**
```angular2html
Got it~~ I've added this task :
    [D][] LAT2201 homework (by: Sep 20 2024)
Now you have 5 tasks in the list.
```

### 3. Event Task
Adds a task that starts at a specific date/time and ends at a specific date/time.

**Format:** `event <TASK_DESCRIPTION> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`

**Examples:**
  - `event hackhaton /from 2024-10-10 /to 2024-10-12'
  - 'event career fair /from 2024-12-01 /to 2024-12-04'

**Expected output:**
```angular2html
Got it~~ I've added this task :
    [E][] hackhaton (from: Oct 10 2024 to: Oct 12 2024)
Now you have 1 tasks in the list.
```


### 4. Tagging a Task

Tags a task by a specific tag, like #urgent, #fun, #easy, or anything user wants.
This is optional and can only be done during task creation

**Format**: <TASK_CREATION_COMMAND> #<TAG_WORD>

**Examples:**
  - `todo read class materials #urgent`
  - `deadline submit CCA proposal /by 2024-10-01 #CCA`
  - 'event career fair /from 2024-12-01 /to 2024-12-04 #fun'

**Expected output:**
```angular2html
Got it~~ I've added this task :
    [D][] submit CCA proposal (by: Oct 01 2024) #CCA
Now you have 7 tasks in the list.
```



## Listing Tasks
Displays all tasks in your list.

**Format:** `list`

**Expected output:**
```angular2html
You have 5 task(s)
    1. [T][] read class materials
    2. [D][X] submit CCA proposal (by: Oct 01 2024) #CCA
    3. [E][] hackhaton (from: Oct 10 2024 to: Oct 12 2024)
    4. [D][X] LAT2201 homework (by: Sep 20 2024)
    5. [T][] buy gift for mom #urgent
```

## Marking a Task as Done

Marks a task as done/completed.

**Format:** `mark <TASK_INDEX>`

**Examples:**
- 'mark 1'
- 'mark 4'

**Expected output:**
```angular2html
Congrats on completing this task~~
This task is marked as done :
    [T][X] read class materials
```

## Unmarking a Task

Unmarks a task to be not done/incomplete.

**Format:** `unmark <TASK_INDEX>`

**Examples:**
- `unmark 1'
- 'unmark 4'

**Expected output:**
```angular2html
Don't forget to complete this task later~~
This task is marked as not done :
    [T][] read class materials
```

## Deleting a Task

Removes a task from your list by its index.

**Format:** `delete <TASK_INDEX>`

**Examples:**
- `delete 2'
- 'delete 3'

**Expected output:**
```
Noted~~ I've removed this task :
[D][X] submit CCA proposal (by: Oct 01 2024) #CCA
Now you have 4 tasks in the list
```

## Finding Task(s)

Search for tasks containing a keyword.

**Format:** `find <KEYWORD>`

**Examples:**
- `find homework'

**Expected output:**
```
Here are 1 task that matches your keyword :
[D][X] LAT2201 homework (by: Sep 20 2024)
```



## Exiting Rose

Exits the application

**Format**: `bye`

**Expected output:**
```angular2html
See you~~ Don't forget to drink some water ^_^
```
