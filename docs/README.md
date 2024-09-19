# Spiderman User Guide

![Spiderman Screenshot](Ui.png)

Spiderman helps you capture every task in your web of productivity, so you never have to remember everything on your own. 
Just like Spidey always stays on top of his game, you will too!

There are three types of task that you can add:

1. ToDo Tasks
2. Deadline Tasks
3. Event Tasks

## Viewing entire task list
Shows current saved tasks in task list.  
_**NOTE:** If this is your first time booting up the chatbot, there would be no tasks shown._  

**Syntax:**   
```
list
```

**Example:** 
```
list
```

**Expected outcome (if you have existing tasks saved):**
```
Alright! Here is your current tasks list:
1. [T][] buy bread
2. [D][] sell bread (by: Oct 1 2024)
3. [E][] project meeting (from: 1 Dec 2024, 12:30:00pm to: 1 Dec 2024, 6:30:00pm)
```

## Adding todos
Add todo task to the tasks list.  
A todo task is a simple task with just the description.  

**Syntax:**   
```
todo {description}
```

**Example:** 
```
todo buy bread
```

**Expected outcome:**
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```

## Adding deadlines
Add deadline task to the tasks list.  
A deadline task is a task that has a due date.  

**Syntax:**  
```
deadline {description} /by {YYYY-MM-dd}
```

**Example:** 
```
deadline project meeting /by 2024-04-01
```

**Expected outcome:**  
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```

## Adding events
Add event task to the tasks list. An event task is a task that has
a starting datetime and an ending datetime.  

**Syntax:** 
```
event {description} /from {YYYY-MM-dd HH:mm} /to {YYYY-MM-dd HH:mm}
```

**Example:** 
```
event project meeting /from 2024-12-12 14:00 /to 2024-12-12 16:00
```

**Expected outcome:**
```
Cool! I'll add this to your task list!
You now have {total number of tasks} tasks in your task list.
```
## Update task
Update a task from the task list.  

**Syntax:**   
```
update {task index} [/description {description}] [/by {YYYY-MM-dd}] [/from {YYYY-MM-dd HH:mm}] [/to {YYYY-MM-dd HH:mm}]
```

**Parameters**  
- `{task index}`  
The index of the task in the task list. This is required to specify which task to update.

- `/description {description}`  
Optional. Updates the task's description. Provide the new task description here.

- `/by {YYYY-MM-dd}`  
Optional. Updates the task's due date to the specified date in YYYY-MM-dd format.

- `/from {YYYY-MM-dd HH:mm}`  
Optional. Updates the task's start time to the specified date and time in YYYY-MM-dd HH:mm format.

- `/to {YYYY-MM-dd HH:mm}`  
Optional. Updates the task's end time to the specified date and time in YYYY-MM-dd HH:mm format.

**Notes**  
- At least one of the parameters (`/description`, `/by`, `/from`, or `/to`) must be provided. 
- You can update multiple fields at once, but it is subjected to the type of task you are updating.

**Example:** 
```
update 1 /description buy milk
```

Expected outcome:
```
Updated Task: [T][] buy milk
```
## Delete task
Delete a task from the task list.  

**Syntax:** 
```
delete {task index}
```

**Example:**  
```
delete 1
```

**Expected outcome:**
```
Alright! I will delete this task for you!
[T][] buy bread
```

## Mark task as done
Mark task as completed.  

**Syntax:**   
```
mark {task index}
```

**Example:**   
```
mark 1
```

**Expected outcome:**
```
Great! I've marked this task as done:
[T][X] buy bread
```
## Mark task as not done
Unmark a previously marked as completed task.  

**Syntax:** 
```
unmark {task index}
```

**Example:** 
```
unmark 1
```

**Expected outcome:**
```
OK, this task will be marked as not done yet:
[T][] buy bread
```

## Finding specific task
Find a task with a specified keyword. The keyword can be half written.

**Syntax:**  
```
find {keyword}
```

**Example:**  
```
find bread
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] buy bread
```