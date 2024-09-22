**Yapper - Task Management Chatbot**
====================================

Welcome to **Yapper**, your personal assistant for managing tasks, deadlines, and events! Yapper is designed to help you stay on top of your to-do list by providing an intuitive, command-based interface. Whether you need to mark tasks as complete, add new tasks, or snooze deadlines, Yapper has you covered.

**Features**
------------

### 1\. **View All Tasks**

-   **Command**: `list`
-   Displays all tasks in your list.

### 2\. **Add Tasks**

-   **To-do Task**:
    -   **Command**: `todo DESCRIPTION`
    -   Adds a task with just a description.
    -   Example: `todo read a book`
-   **Deadline Task**:
    -   **Command**: `deadline DESCRIPTION /by DATE`
    -   Adds a task with a deadline.
    -   Example: `deadline submit report /by 2024-09-30 1800`
-   **Event Task**:
    -   **Command**: `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`
    -   Adds an event with a start and end time, where both times should follow the format yyyy-MM-dd HHmm (24-hour format).
    -   Example: `event team meeting /from 2024-09-30 1400 /to 2024-09-30 1600`

### 3\. **Mark Tasks as Done or Not Done**

-   **Mark a task as done**:
    -   **Command**: `mark TASK_NUMBER`
    -   Marks a task as complete.
    -   Example: `mark 1`
-   **Mark a task as not done**:
    -   **Command**: `unmark TASK_NUMBER`
    -   Reverts a task back to incomplete status.
    -   Example: `unmark 2`

### 4\. **Delete a Task**

-   **Command**: `delete TASK_NUMBER`
-   Removes the specified task from your list.
-   Example: `delete 3`

### 5\. **Find Tasks by Keyword**

-   **Command**: `find KEYWORD`
-   Searches for tasks containing the keyword (case-insensitive).
-   Example: `find report`

### 6\. **Snooze a Deadline**

-   **Command**: `snooze TASK_NUMBER SNOOZE_AMOUNT`
-   Snoozes (postpones) the deadline of a `Deadline` task by a specific number of days or hours.
-   Example:
    -   `snooze 1 2d` (snooze by 2 days)
    -   `snooze 2 3h` (snooze by 3 hours)
-   Note: The snooze feature only works for `Deadline` tasks.

### 7\. **Exit the Application**

-   **Command**: `bye`
-   Saves all tasks and exits the app.

* * * * *

**Command Format Notes**
------------------------

-   **UPPER_CASE** represents user-provided input.\
    Example: `mark TASK_NUMBER` where `TASK_NUMBER` is the index of the task.
-   **/by**, **/from**, and **/to** are required keywords for certain commands (like deadlines and events).

### **Date and Time Format**

-   When entering a deadline, use the format `yyyy-MM-dd HHmm` (e.g., `2024-09-30 1800` for September 30, 2024, at 6:00 PM).

* * * * *

**How Yapper Handles Tasks**
----------------------------

-   **Auto-Save**: Yapper saves your tasks automatically when you exit the application.
-   **Persistent Storage**: Your tasks are stored on your local file system, so they're always available when you reopen the app.