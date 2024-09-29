## Bob Task Manager

Bob is a simple task manager with a graphical user interface for tracking ToDo tasks, Deadlines, and Events. It allows users to easily manage tasks, set reminders, and save tasks to a file.

## Features

1. Add Tasks: Add ToDo, Deadline, or Event tasks. 
2. Mark/Unmark Tasks: Mark tasks as done or undone. 
3. Delete Tasks: Remove tasks from the list. 
4. Find Tasks: Search for tasks by keyword. 
5. Reminders: View upcoming tasks within the next two days. 
6. Data Persistence: Automatically saves tasks to a file. 
7. Clear Tasks: Remove all tasks from the list.

## Getting Started

Clone the repository.
Compile and run the program from the command line.
Enter commands to manage your tasks.

## Commands

| Command                                                      | Description              |
|--------------------------------------------------------------|--------------------------|
| `todo [task]`                                                | Adds a ToDo task         |
| `deadline [task] /by [MM-dd-yyyy HHmm]`                      | Adds a Deadline task     |
| `event [task] /from [MM-dd-yyyy HHmm] /to [MM-dd-yyyy HHmm]` | Adds an Event task       |
| `list`                                                       | Lists all tasks          |
| `mark [task number]`                                         | Marks a task as done     |
| `unmark [task number]`                                       | Marks a task as not done |
| `delete [task number]`                                       | Deletes a task           |
| `find [keyword]`                                             | Finds tasks by keyword   |
| `clear`                                                      | Clears all tasks         |
| `remind`                                                     | Shows upcoming tasks     |
| `bye`                                                        | Exits the program        |


## Requirements

Java 11 or higher

## Usage

```bash
java -jar bob.jar
```
## File Management

Tasks are saved to a file named save.txt in the data folder. You can move or delete this file to reset your tasks.