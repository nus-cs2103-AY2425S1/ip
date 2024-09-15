# Soju User Guide

## Soju Screenshot
![Product screenshot of Soju](Ui.png)

## Soju Introduction
Hi, Soju is a chatbot that helps users make their own to do list.
It helps the user keep track of their tasks too. It has 3 types of 
tasks that can be added, mainly todos, deadlines and events.

### Command List
1. **Add a Deadline Task**
    - **Format**: `deadline <description> /by <yyyy-MM-dd>`
    - **Description**: Adds a task with a description and a deadline date.
    - **Example**: `deadline Finish project report /by 2024-09-20`

2. **Add a Todo Task**
    - **Format**: `todo <description>`
    - **Description**: Adds a task without a specific deadline.
    - **Example**: `todo Buy groceries`

3. **Add an Event Task**
    - **Format**: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
    - **Description**: Adds a task with a description and an event date.
    - **Example**: `event Team meeting /from 2024-09-15 1300 /to 2024-09-15 1500`

4. **List All Tasks**
    - **Format**: `list`
    - **Description**: Lists all the tasks in the current task list.
    - **Example**: `list`

5. **Mark Task as Done**
    - **Format**: `done <task_number>`
    - **Description**: Marks a specified task as completed.
    - **Example**: `done 1`

6. **Delete a Task**
    - **Format**: `delete <task_number>`
    - **Description**: Deletes a specified task from the list.
    - **Example**: `delete 2`

7. **Find Tasks by Keyword**
    - **Format**: `find <keyword>`
    - **Description**: Searches for tasks containing the specified keyword.
    - **Example**: `find report`

8. **Exit the Application**
    - **Format**: `bye`
    - **Description**: Exits the application.
    - **Example**: `bye`

### Notes:
- **`<description>`**: A brief text describing the task.
- **`<yyyy-MM-dd>`**: The date in ISO format (year-month-day).
- **`<yyyy-MM-dd HHmm>`**: The datetime in ISO format (year-month-day hour-min).
- **`<task_number>`**: The index of the task in the list, usually starting from 1.