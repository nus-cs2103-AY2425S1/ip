# Taskalyn User Guide

<div align="center">
    <img src="Ui.png" alt="Taskalyn Screenshot" width="350">
</div>

## Introduction

Taskalyn is your personal task manager.\
You can *add* **3 different types of tasks**: *Todo*, *Deadline*, *Event*.\
You can *list* all tasks at the moment.\
You can *mark* tasks as complete, or *unmark* tasks if incomplete.\
You can *delete* any unneeded tasks.\
You can *find* tasks with a certain keyword.\
You can *sort* all deadline tasks by nearest deadline.

## Features
### Adding a Todo Task

- Description: Adds a todo task inside Taskalyn
- Command: `todo {task}` 
- Example: `todo read a book`
- Expected Outcome: 
```
    Got it, I've added this task to your list!
    [T][ ] read a book
    Wah bro... 1 task already! 
```

### Adding a Deadline Task

- Description: Adds a deadline task inside Taskalyn
- Command: `deadline {task} /by {dd-mm-yyyy hhmm}`
- Example: `deadline math homework /by 20-10-2024 2359`
- Expected Outcome:
```
    Got it, I've added this task to your list!
    [D][ ] math homework (by: 20 10 2024, 11:59 PM)
    Wah bro... 2 tasks already! 
```

### Adding an Event Task

- Description: Adds an event task inside Taskalyn
- Command: `event {task} /from {dd-mm-yyyy hhmm} /to {dd-mm-yyyy hhmm}`
- Example: `event farewell party /from 20-10-2024 1500 /to 20-10-2024 1800`
- Expected Outcome:
```
    Got it, I've added this task to your list!
    [E][ ] farewell party (from: 20 10 2024, 3:00 PM to: 20 10 2024, 6:00 PM)
    Wah bro... 3 tasks already! 
```

### Marking a Task as Complete

- Description: Marks a task as complete
- Command: `mark {task number}`
- Example: `mark 1`
- Expected Outcome:
```
    Nice, I've marked this task as complete.
    [T][X] read a book
```

### Unmarking a Task as Incomplete

- Description: Unmarks a task as incomplete
- Command: `unmark {task number}`
- Example: `unmark 1`
- Expected Outcome:
```
    Ok, I've marked this task as incomplete.
    [T][ ] read a book
```

### Deleting a Task

- Description: Deletes a task from Taskalyn
- Command: `delete {task number}`
- Example: `delete 1`
- Expected Outcome:
```
    Awesome bro! One task gone :D
    [T][ ] read a book
    Wah bro... 2 tasks already! 
```

### Listing all Tasks

- Description: Lists all tasks within Taskalyn
- Command: `list`
- Example: `list`
- Expected Outcome:
```
    Here are the tasks in your list:
    1.[D][ ] math homework (by: 20 10 2024, 11:59 PM)
    2.[E][ ] farewell party (from: 20 10 2024, 3:00 PM to: 20 10 2024, 6:00 PM)
```

### Finding Tasks with a Keyword

- Description: Finding tasks with any keyword
- Command: `find {keyword(s)}`
- Example: `find math`
- Expected Outcome:
```
    Here are the matching tasks in your list:
    1.[D][ ] math homework (by: 20 10 2024, 11:59 PM)
```

### Sorting Deadline Tasks

- Description: Sorts deadline tasks from nearest to furthest deadlines
- Command: `sort`
- Example: `sort`
- Expected Outcome:
```
    Here are the sorted deadline tasks in your list:
    1.[D][ ] math homework (by: 20 10 2024, 11:59 PM)
    2.[D][ ] science homework (by: 21 10 2024, 10:00 AM)
    3.[D][ ] english homework (by: 21 10 2024, 11:59 PM)
```

### Exiting Taskalyn

- Description: Exits and closes the Taskalyn application after 5 seconds
- Command: `bye`
- Example: `bye`
- Expected Outcome:\
```
    Bye! Hope to see you again soon!
    
    Shutting down in 5 seconds...
```

## Summary of Commands

| Command  | Command Format                           | Example                                                        |  
|----------|------------------------------------------|----------------------------------------------------------------| 
| todo     | todo {task}                              | todo read a book                                               |
| deadline | deadline {task} /by {date\*}             | deadline math homework /by 20-10-2024 2359                     | 
| event    | event {task} /from {date\*} /to {date\*} | event farewell party /from 20-10-2024 1500 /to 20-10-2024 1800 | 
| mark     | mark {task number}                       | mark 1                                                         | 
| unmark   | unmark {task number}                     | unmark 1                                                       | 
| delete   | delete {task number}                     | delete 1                                                       | 
| list     | list                                     | list                                                           | 
| find     | find {keyword(s)}                        | find math                                                      |
| sort     | sort                                     | sort                                                           | 
| bye      | bye                                      | bye                                                            |

\* Date format must be in `dd-mm-yyyy hhmm` 