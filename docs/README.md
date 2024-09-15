# Thanos User Guide
![Product Screenshot](Ui.png)

Thanos is a one-stop task management companion that allows users to manage their tasks efficiently.


## Features
### Add todos: `todo`
Add a Todo task to your task list.

Format: `todo <description>`

- Example: `todo read book`
  - Expected outcome:
    ```
    Got it. I've added this task:
      [T][ ] read book
    Now you have 1 tasks in the list.
    ```

### Add deadlines: `deadline`
Add a Deadline task with a due date to your task list.

Format: `deadline <description> /by <deadline>`

- Example: `deadline submit report /by 15-08-2024 23:59`
    - Expected outcome:
      ```
      Got it. I've added this task:
        [D][ ] submit report (by: Aug 15 2024 23:59)
      Now you have 2 tasks in the list.
      ```

### Add events: `event`
Add an Event task with a start date/time and end date/time to your task list.

Format: `event <description> /from <start_date/time> /to <end_date/time>`

- Example: `event project meeting /from 15-08-2024 16:00 /to 15-08-2024 18:00`
    - Expected outcome:
      ```
      Got it. I've added this task:
        [E][ ] project meeting (from: Aug 15 2024 16:00 to: Aug 15 2024 18:00)
      Now you have 3 tasks in the list.
      ```

### List tasks: `list`
Display a list of all tasks in your task list.

Format: `list`

- Example: `list`
    - Expected outcome:
      ```
      Here are the tasks in your list:
      1.[T][ ] read book
      2.[D][ ] submit report (by: Aug 15 2024 23:59)
      3.[E][ ] project meeting (from: Aug 15 2024 16:00 to: Aug 15 2024 18:00)
      ```

### Mark tasks: `mark`
Mark a specific task as done

Format: `mark <task_index>`

- Example: `mark 1`
    - Expected outcome:
      ```
      Nice! I've marked this task as done:
        [T][X] read book
      ```

### Unmark tasks: `unmark`
Unmark a specific task as not done

Format: `unmark <task_index>`

- Example: `unmark 1`
    - Expected outcome:
      ```
      OK, I've marked this task as not done yet:
        [T][ ] read book
      ```


### Find tasks: `find`
Find tasks containing a specific keyword

Format: `find <keyword>`

- Example: `find read book`
    - Expected outcome:
      ```
      Here are the matching tasks in your task list:
      1.[T][ ] read book
      ```

### Find tasks by date: `date`
Find tasks occurring on a specific date

Format: `date <date_to_search>`

- Example: `date 15-08-2024 23:59`
    - Expected outcome:
      ```
      Here are the matching tasks in your task list:
      1.[D][ ] submit report (by: Aug 15 2024 23:59)
      ```

### Delete tasks: `delete`
Delete a specific task from your task list

Format: `delete <task_index>`

- Example: `delete 1`
    - Expected outcome:
      ```
      Noted. I've removed these task(s):
      1.[T][ ] read book
      Now you have 2 tasks in your list.
      ```

### Exit the program: `bye`
Exit the program and close the chatbot.

Format: `bye`
