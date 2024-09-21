# Twilight User Guide

Twilight is a chatbot for **managing todo, event, and deadline tasks.** It enables users to create and 
store these types of tasks. Users can tag tasks, mark completion and find tasks they desire. 

![Ui.png](Ui.png)

## Quick Start Guide
1. Ensure Java 17 or later is installed on your device. 
2. Download the JAR file from this repository
3. Place the JAR file in a desired directory and run it by double-clicking. 

## Features

### Creating a Todo Task: todo
Adds a task to be completed to the task list.

Format: `todo taskName`

Examples:
`todo read Getting Things Done` `todo Complete weekly reading`

Sample output:
```
added: [T][ ] read Getting Things Done
There are 5 tasks in the list.
```

### Creating a Deadline Task: deadline
Adds a deadline to the task list.

Format: `deadline deadlineName /by YYYY-MM-DD` 

- The date must follow this exact format and be valid.

Examples:
`deadline History Project /by 2024-10-10` `deadline Philosophy Paper /by 2024-01-02`

Sample output:
```
added: [D][ ] History Project by Oct 10 2024
There are 6 tasks in the list.
```

### Creating an Event Task: event
Adds an event with a given start and end time to the task list.

Format: `event eventName /from startTime /to endTime`
 
- The start and end time can be listed in any format as they are strings.

Examples:
`event birthday party /from Friday 5 pm /to Friday 9 pm ` `event project meeting /from 2024-10-10 1 pm /to 2024-10-10 3 pm`

Sample output:
```
added: [E][ ] birthday party from: Friday 5 pm to: Friday 9 pm
There are 7 tasks in the list.
```

### Tagging a Task: tag
Tags a task.

Format: `tag taskNumber #tag`

- Unlimited number of tags can be added.
- Multiple tags can be added at once by listing them in series.

Examples:
`tag 1 #important` `tag 2 #fun #interesting`

Sample output:
```
The task has been tagged:
[D][ ] History Project by Oct 10 2024 #important
```

### Listing Stored Tasks: list
Lists the stored tasks with their status, descriptions and tags. Format: `list`

Sample output:
```
Here are the current tasks:
1. [D][ ] History Project by Oct 10 2024 #important
2. [E][ ] birthday party from: Friday 5 pm to: Friday 9 pm
3. [T][X] read Getting Things Done
```

### Marking and Unmarking Task Completion: mark, unmark
mark: Marks the task as complete.
Format: `mark taskNumber`
Example: `mark 1`

Sample output:
```
Excellent I have marked it:
[D][X] History Project by Oct 10 2024 #important
```

unmark: Unmarks the task as incomplete.
Format: `unmark taskNumber`
Example: `unmark 1` 

### Deleting Tasks: delete
Deletes tasks from the task list.
Format: `delete taskNumber`
Example: `delete 1`

Sample output:
```
Fine I have unmarked it:
[D][ ] History Project by Oct 10 2024 #important
```

### Finding Tasks According to Description: find
Finds tasks whose name match a string description.
Format: `find description`
Example: `Find book`

Sample output:
```
Here are the matches:
1. [T][ ] Read book
2. [T][ ] Return book
```

### Exiting the Application: bye
Closes the application. 
Format: `bye`

### Help Command: help
Gives information on available commands.
Format: `help`

## Command Summary

| Command  | Format                                         | Example                                     |
|:--------:|------------------------------------------------|---------------------------------------------|
|   Todo   | `todo taskName`                                | `todo read book`                            |
|  Event   | `event eventName /from startTime /to endTime`  | `event birthday party /from 5 pm /to 9 pm`  |
| Deadline | `deadline deadlineName /by YYYY-MM-DD`         | `deadline History project /by 2024-10-10`   |
|   Tag    | `tag taskNumber #tag`                          | `tag 1 #fun`                                | 
|   List   | `list`                                         | `list`                                      | 
|   Mark   | `mark taskNumber`                              | `mark 1`                                    |                            
|  Unmark  | `unmark taskNumber`                            | `unmark 3`                                  |
|  Delete  | `delete taskNumber`                            | `delete 2`                                  |                                 
|   Find   | `find description`                             | `find book`                                 |
|   Exit   | `bye`                                          | `bye`                                       |
|   Help   | `help`                                         | `help`                                      |

## Other notes

Twilight stores the tasks and reads them everytime you open the application. If the file has been incorrectly
modified you will be notified. You may choose to close the application and fix the file or continue which will
create a new task list and override the old file entirely. 