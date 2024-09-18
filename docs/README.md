# James User Guide

Welcome to James Chatbot, a simple and efficient command-line assistant that helps you manage your tasks. 
This guide will walk you through how to interact with James and the various commands you can use to add, modify, 
and manage your tasks effectively.

## Key Features
- Add tasks: To-do, deadlines, and events.
- Mark tasks as done or not done.
- Delete tasks.
- List all tasks.
- Search for tasks by keywords.
- Save tasks between sessions.

## How to Use James
1. Starting the Chatbot
   Once you have set up and launched James, you will interact with it through text commands. Simply type a command and hit "Enter" to execute it.


2. Below is a list of commands you can use with James:
   - ### `list`

        Description: Lists all tasks currently in your task list.
        ```
        Here are your tasks!
        1. [T][ ] buy groceries
        2. [D][ ] complete assignment (by: Dec 12 2024 1800)
        3. [E][ ] hall event (from: Sep 20 2024 2000 to: Sep 20 2024 2200)
        ```
   - ### `todo`

        Description: Adds a new to-do task.
   
        Command: `todo <task_description>`

        Example: 
   
        ```
        todo read book
        ```
     
        Response:
   
        ```
        Task added:
        [T][ ] read book
        Now you have 4 tasks in the list.
        ```
   - ### `deadline`

        Description: Adds a new deadline task with a specific due date.

        Command: `deadline <task_description> /by <due_date>`

        Example:

        ```
        deadline Submit assignment /by 2024-09-20T10:00:00
        ```
     
        Response:

        ```
        Task added:
        [D][ ] Submit assignment (by: Sep 20 2024 1000)
        Now you have 5 tasks in the list.
        ```
   - ### `event`

        Description: Adds a new event task with a start and end time. 

        Command: `event <task_description> /from <start_time> /to <end_time>`

        Example:
        ```
        event Attend conference /from 2024-09-21T09:00:00 /to 2024-09-21T17:00:00
        ```
     
        Response:
        ```
        Task added:
        [E][ ] Attend conference (from: Sep 21 2024 0900 to: Sep 21 2024 1700)
        Now you have 6 tasks in the list.
        ```
   - ### `mark`

        Description: Marks a specified task as done.

        Command: `mark <task_number>`

        Example:
        ```
        mark 4
        ```
     
        Response:
        ```
        Task marked as done:
        [T][X] Read book
        ```
   - ### `unmark`

        Description: Marks a specified task as not done.

        Command: `unmark <task_number>`

        Example:
        ```
        unmark 4
        ```
     
        Response:
        ```
        Task marked as not done:
        [T][ ] Read book
        ```
   - ### `delete`

        Description: Deletes a task by specifying its number.

        Command: `delete <task_number>`

        Example:
        ```
        delete 5
        ```
     
        Response:
        ```
        Task removed:
        [D][ ] Submit assignment (by: Sep 20 2024 1000)
        Now you have 2 tasks in the list.
        ```
   - ### `find`

        Description: Searches for tasks that contain a specific keyword.

        Command: `find <keyword>`

        Example:
        ```
        find book
        ```
        Response:
        ```
        Here are the matching tasks in your list:
        1. [T][ ] Read book
        ```
     
     

        
   
   
