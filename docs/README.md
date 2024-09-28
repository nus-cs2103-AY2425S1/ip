# Simon User Guide


Simon is a desktop app for managing tasks through the use of a Graphical User Interface (GUI)

![alt text]([https://github.com/yuvrajaryan/ip/blob/master/docs/Ui.png](https://raw.githubusercontent.com/yuvrajaryan/ip/master/docs/Ui.png))

## Deadline Command

Command: deadline\
Description: Adds a deadline task with a specific due date.\
Example: `deadline Finish homework /by 15/9/2024 2359`\
Explanation: Adds a task with the description "Finish homework" and a deadline of September 15, 2024, at 23:59.

## Event Command

Command: event\
Description: Adds an event with a start and end time.\
Usage Example: `event Team meeting /from 14/9/2024 0900 /to 14/9/2024 1100`\
Explanation: Adds an event with the description "Team meeting," starting on September 14, 2024, at 09:00 and ending on the same day at 11:00.

## Todo Command

Command: todo\
Description: Adds a to-do task with a description.\
Usage Example: `todo Buy groceries`\
Explanation: Adds a to-do task with the description "Buy groceries."


## Delete Command

Command: delete\
Description: Deletes a task by index.\
Usage Example: `delete 3`\
Explanation: Deletes the task at index 3.

## Find Command

Command: find\
Description: Searches for tasks by title.\
Usage Example: `find project`\
Explanation: Searches for tasks that contain "project" in their title.

## List Command

Command: list\
Description: Displays a list of all tasks.\
Usage Example: `list`

## Mark Command

Command: mark\
Description: Marks a task as completed.\
Usage Example: `mark 1`\
Explanation: Marks the task at index 1 as completed.

## Unmark Command

Command: unmark\
Description: Marks a task as not completed.\
Usage Example: `unmark 2`\
Explanation: Marks the task at index 2 as not completed.



## Error Handling
Invalid Commands: If an unrecognized command is entered, you will be prompted to correct your input.
