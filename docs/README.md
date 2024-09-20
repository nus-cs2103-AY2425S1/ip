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
// to be edited later

### 2. Deadline Task
Adds a task that needs to be done before a specific date/time.

**Format:** `deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd>`

**Examples:**
  - `deadline LAT2201 homework /by 2024-09-20`
  - `deadline submit CCA proposal /by 2024-10-01`
  - `deadline apply for TA position /by 2024-12-01`

**Expected output:**
// to be edited later

### 3. Event Task
Adds a task that starts at a specific date/time and ends at a specific date/time.

**Format:** `event <TASK_DESCRIPTION> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`

**Examples:**
  - `event hackhaton /from 2024-10-10 /to 2024-10-12'
  - 'event career fair /from 2024-12-01 /to 2024-12-04'

**Expected output:**
// to be edited later


### 4. Tagging a Task

Tags a task by a specific tag, like #urgent or #fun. This is optional and can only be done during task creation

**Format**: <TASK_CREATION_COMMAND> #<TAG_WORD>

**Examples:**
  - `todo read class materials #urgent`
  - `deadline submit CCA proposal /by 2024-10-01 #CCA`
  - 'event career fair /from 2024-12-01 /to 2024-12-04 #fun'

**Expected output:**
// to be edited later


## Listing Tasks
Displays all tasks in your list.

**Format:** `list`

**Expected output:**
//to be edited later

## Marking a Task as Done

Marks a task as done/completed.

**Format:** `mark <TASK_INDEX>`

**Examples:**
- 'mark 1'
- 'mark 4'

**Expected output:**
//to be edited later

## Unmarking a Task

Unmarks a task to be not done/incomplete.

**Format:** `unmark <TASK_INDEX>`

**Examples:**
- `unmark 1'
- 'unmark 4'

**Expected output:**
//to be edited later

## Deleting a Task

Removes a task from your list by its index.

**Format:** `delete <TASK_INDEX>`

**Examples:**
- `delete 2'
- 'delete 3'

**Expected output:**
//to be edited later

## Finding Task(s)

Search for tasks containing a keyword.

**Format:** `find <KEYWORD>`

**Examples:**
- `find homework'

**Expected output:**
//to be edited later


## Exiting Rose

Exits the application

**Format**: `bye`

**Expected output:**
//to be edited later
