# Deez User Guide

![Screenshot](./Ui.png)
Are you tired of juggling multiple tasks and deadlines, only to find yourself overwhelmed and stressed out? Do you
struggle to stay organized and focused amidst the chaos of everyday life?

Look no further! We're thrilled to introduce Deez, the innovative productivity tool designed to help you manage your
time more effectively, prioritize your tasks, and achieve greater control over your busy schedule.

# Commands

1. **bye**
    * Action: Exit the application
    * Outcome: The application terminates
    * Usage: `bye`

2. **list**
    * Action: List all tasks
    * Outcome: A list of all tasks is displayed
    * Usage: `list`

3. **mark**
    * Action: Mark a task as completed
    * Outcome: The task is marked as completed
    * Usage: `mark <task_index>`

4. **unmark**
    * Action: Unmark a task as incomplete
    * Outcome: The task is unmarked as incomplete
    * Usage: `unmark <task_index>`

5. **todo**
    * Action: Create a new task with a given description and deadline
    * Outcome: A new task is created with the given description and deadline
    * Usage: `todo Project meeting /by 2019-10-15 1800`

6. **deadline**
    * Action: Set a deadline for an existing task
    * Outcome: The deadline for the task is updated
    * Usage: `deadline return book /by 2019-10-15 1800`

7. **event**
    * Action: Create a new event with a given description, start date, and end date
    * Outcome: A new event is created with the given description, start date, and end date
    * Usage: `event project meeting /from 2019-10-15 1800 /to 2019-10-15 1900`

8. **delete**
    * Action: Delete a task
    * Outcome: The task is deleted
    * Usage: `delete <task_index>`

9. **save**
    * Action: Save the current state of the application to file
    * Outcome: The application's state is saved to file
    * Usage: `save`

10. **find**
    * Action: Search for a task using a keyword
    * Outcome: A list of tasks matching the keyword is displayed
    * Usage: `find "keyword"`

# Tags feature

Add tags to your tasks!

* Usage: `todo example #tag #cool`
* Usage: `deadline example #tag #cool`
* Usage: `deadline return book /by 2019-10-15 1800 #example`
