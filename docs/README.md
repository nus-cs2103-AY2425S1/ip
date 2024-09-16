# CasperBot User Guide

![CasperBot Product Image](./Ui.png)

This is a chat bot made for CS2103T AY24/25 S1 Individual Project. It helps user tracks their outstanding tasks.
There are 3 types of tasks that can be tracked, todos, deadlines and events.

## Adding deadlines
1. **Add a Todo Task**
    - **Format**: `todo <description>`
    - **Description**: Adds a task without a specific deadline.
    - **Example**: `todo say hi to Casper`

2. **Add a Deadline Task**
    - **Format**: `deadline <description> /by <yyyy-MM-dd>`
    - **Description**: Adds a task with a description and a deadline date.
    - **Example**: `deadline Finish project report /by 2024-09-20`

3. **Add an Event Task**
    - **Format**: `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`
    - **Description**: Adds a task with a description, an event start date and an event end date.
    - **Example**: `event meet casper /from 2024-09-15 /to 2024-09-16`

4. **List All Tasks**
    - **Format**: `list`
    - **Description**: Lists all tasks in the task list.

5. **Mark Task as Done**
    - **Format**: `done <task_number>`
    - **Description**: Marks a task as completed. The task is specified by its index on the task list.
    - **Example**: `done 1`

6. **Delete a Task**
    - **Format**: `delete <task_number>`
    - **Description**: Deletes a task from the list. The task is specified by its index on the task list.
    - **Example**: `delete 2`

7. **Find Tasks by Keyword**
    - **Format**: `find <keyword>`
    - **Description**: Searches for tasks containing the specified keyword.
    - **Example**: `find casper`

8. **View Schedule of Date**
    - **Format**: `view <yyyy-MM-dd>`
    - **Description**: Displays all deadlines due and all events ongoing on the specified date.
    - **Example**: `view 2024-09-22`

9. **Exit the Application**
    - **Format**: `bye`
    - **Description**: Exits the application.

### Notes:
- **`<description>`**: A brief text describing the task.
- **`<yyyy-MM-dd>`**: The date in ISO format (year-month-day).
- **`<task_number>`**: The index of the task in the list, usually starting from 1.