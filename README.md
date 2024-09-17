# SKD User Guide
 *
 * Welcome to SKD (Simple Keeptracker Duke)
 * SKD is a personal task manager chatbot designed to help you track tasks easily. 
 * From simple to-dos to complex events and deadlines, SKD helps you organize your day effectively.
 *
 * ------------------------------------------------------------
## Table of Contents:
 * 1. Features
 * 2. Quick Start
 * 3. Commands Summary
 * 4. Detailed Features
 *    - Adding Tasks (ToDo, Event, Deadline)
 *    - Marking and Unmarking Tasks
 *    - Snoozing Tasks
 *    - Deleting Tasks
 *    - Finding Tasks
 *    - Listing Tasks
 *    - Exiting the Application
 * 5. Saving Tasks
 * 6. FAQ
 * ------------------------------------------------------------
 *
### Features:
 * - Add different types of tasks (ToDo, Event, Deadline).
 * - Mark or unmark tasks as complete.
 * - Snooze deadlines and events.
 * - Delete tasks.
 * - Find tasks by keyword.
 * - List all tasks.
 * - Automatic saving of tasks.
 * - User-friendly interface with snoozing and task search.
 * - Exiting the application via a simple command.
 *
### Quick Start:
 * 1. Download the SKD chatbot from our GitHub repository: 
 *    https://github.com/nus-cs2103-AY2425S1/ip
 * 2. Follow the instructions in the README to run the application.
 * 3. Begin entering commands in the terminal to add or manage tasks.
 *
 * Example:
 * todo read book
 * event project meeting /from 2024-09-12 14:00 /to 2024-09-12 16:00
 *
### Commands Summary:
 * - todo [task] : Adds a ToDo task.
 * - deadline [task] /by [yyyy-mm-dd hh:mm] : Adds a Deadline task.
 * - event [task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm] : Adds an Event task.
 * - mark [index] : Marks a task as done.
 * - unmark [index] : Unmarks a task as undone.
 * - snooze [index] [days] : Snoozes a deadline or event by a certain number of days.
 * - delete [index] : Deletes a task.
 * - find [keyword] : Finds tasks containing a keyword.
 * - list : Lists all tasks.
 * - bye : Exits the application.
 *
### Detailed Features:
 * 
 * 1. Adding Tasks:
 * To add a task, use one of the following commands:
 * - todo [task] : Adds a ToDo task.
 *   Example: todo Buy groceries
 *
 * - deadline [task] /by [yyyy-mm-dd hh:mm] : Adds a Deadline task.
 *   Example: deadline Submit assignment /by 2024-09-20 23:59
 *
 * - event [task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm] : Adds an Event task.
 *   Example: event Team meeting /from 2024-09-10 10:00 /to 2024-09-10 12:00
 * 
 * 2. Marking and Unmarking Tasks:
 * - Mark a task as complete using: mark [index]
 *   Example: mark 2
 *
 * - Unmark a task using: unmark [index]
 *   Example: unmark 2
 *
 * 3. Snoozing Tasks:
 * - Snooze a deadline or event by a certain number of days.
 *   Command: snooze [index] [days]
 *   Example: snooze 2 3
 *
 * 4. Deleting Tasks:
 * - Delete any task using: delete [index]
 *   Example: delete 3
 *
 * 5. Finding Tasks:
 * - Search for tasks using a keyword: find [keyword]
 *   Example: find book
 *
 * 6. Listing Tasks:
 * - List all tasks using: list
 *
 * 7. Exiting the Application:
 * - Exit the application using: bye
 *
 * Saving Tasks:
 * Tasks are automatically saved after each change. When you restart SKD, tasks will be loaded from the saved file.
