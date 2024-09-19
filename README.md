# Dipsy User Guide

![Dipsy Product Screenshot](docs/Ui.png)

**Dipsy** (named after my cat) is a task management chatbot that helps users manage tasks, deadlines, and events using a command-line interface. 

The chatbot allows users to add, manage, and list tasks, as well as mark them as completed.   
Users can view a command guide by clicking on the `?` button at the bottom right of the GUI.

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.

2. Download the latest .jar file from [here](https://github.com/itsme-zeix/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dipsy.jar` command to run the application. A GUI should appear in a few seconds.
5. Refer to Features or Command Summary below for details of each command.

## Features

### Adding a To-do Task

The `todo` command allows users to add a simple to-do task to their task list.

#### Usage:
```
`todo <description>`
```
- `<description>`: A short description of the task.

#### Example:
```
todo Buy treats for Dipsy
```

#### Expected Outcome:
The to-do task will be added to the task list, and Dipsy will display a confirmation message:
```
(=ↀωↀ=)ノ Task added!
[T][] Buy treats for Dipsy
You now have 1 tasks in your list.
```

### Adding a Deadline

The `deadline` command allows users to add tasks with a specific deadline.

#### Usage:
```
deadline <description> /by <date>
```
- `<description>`: A short description of the task.
- `<date>`: The deadline for the task in `yyyy-MM-dd` format.

#### Example:
```
deadline Submit report /by 2024-05-12
```

#### Expected Outcome:
The deadline task will be added to your task list, and Dipsy will display a confirmation message along with the updated task count.
```
(=ↀωↀ=)ノ Task added!
[D][] Submit Report (by: May 12 2024)
You now have 2 tasks in your list.
```

### Adding an Event

The `event` command allows users to add an event with a start and end date.

#### Usage:
```
event <description> /from <start> /to <end>
```
- `<description>`: A short description of the event.
- `<start>`: The start date in `yyyy-MM-dd` format.
- `<end>`: The end date in `yyyy-MM-dd` format.


#### Example:
```
event Conference /from 2024-08-12 /to 2024-08-14
```

#### Expected Outcome:
The event will be added to the task list, and Dipsy will display a confirmation message with the updated task count.
```
(=ↀωↀ=)ノ Task added!
[E][] Conference (from: Aug 12 2024 to: Aug 14 2024)
You now have 3 tasks in your list.
```

### Deleting a Task

The `delete` command allows users to delete a specified task. 

#### Usage:
```
delete <index>
```
- `<index>`: The index number of the task to be deleted.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …

#### Example:
```
delete 1
```

#### Expected Outcome:
The task at index 1 will be deleted, and Dipsy will display a confirmation message along with the updated task count. 
```
Purrr, I've swatted this task away:
[T][] Buy treats for Dipsy
You now have 2 tasks in your list.
```

### Marking a Task as Done

The `mark` command allows users to mark a specified task as completed.

#### Usage:
```
mark <index>
```
- `<index>`: The index number of the task to mark as completed.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …

#### Example:
```
mark 1
```

#### Expected Outcome:
The task at index 1 will be marked as done, and Dipsy will display the updated status of the task.
```
Meow! I've scratched this task off the list!
[D][X] Submit Report (by: May 12 2024)
```

### Unmarking a Task
The `unmark` command allows users to unmark a specified task to indicate that it is not completed.

#### Usage:
```
unmark <index>
- `<index>`: The index number of the task to unmark.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …
```

#### Example:
```
unmark 1
```

#### Expected Outcome:
The task at index 1 will be unmarked, and Dipsy will display the updated status of the task.
```
Mrrreow! I've batted this task back onto the list!
[D][] Submit Report (by: May 12 2024)
```

### Listing Tasks

The `list` command allows users to view tasks in two ways:
- **Without a date**: Lists all tasks currently in the task list.
- **With a date**: Lists tasks that have deadlines or events on a specific date.

#### Usage:

- To list all tasks: 
  ```
  list
  ```
    
- To list tasks on a specific date: 
  ```
  list <date>
  ```
  - `<date>`: The date to filter tasks by, in `yyyy-MM-dd` format.

#### Example:
- Listing all tasks: `list`
- Listing tasks for May 12, 2024: `list 2024-05-12`

#### Expected Outcome:
- **For `list` (no date)**:
  - Dipsy will list all the tasks in the current task list, along with their status (whether they are marked as done or not).
    ```
    Time to stretch those paws and tackle your tasks!
    1. [D][ ] Submit report (by: May 12 2024)
    2. [E][ ] Conference (from: Aug 12 2024 to Aug 14 2024)
    ```
- **For `list <date>` (with a specific date)**:
  - Dipsy will list all tasks that match the specified date.
    ```
    Time to stretch those paws and tackle your tasks!
    1. [D][ ] Submit report (by: May 12 2024)
    ```

### Exiting the Program

The `bye` command allows users to exit the Dipsy application.

#### Usage:

```
bye
```

#### Expected Outcome:
Dipsy will display a farewell message and close the application.
```
Fur-well friend, stay paw-sitive!
```

### Command Guide 
The command guide can be viewed  by clicking on the `?` button at the bottom right of the GUI.

### Saving the data

Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Modifying the data file

Task list data are saved automatically as a CSV file `[JAR file location]/data/taskTable.csv`. Advanced users are welcome to update data directly by editing that data file.

> [!CAUTION]
> Certain edits can cause the the application to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

## FAQ
**Q**: How do I transfer my data to another Computer?  
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous `Dipsy` folder. 
The data file can be found at `[JAR file location]/data/taskTable.csv`.

## Command Summary

| Action           | Format, Examples                                                                                            |
|------------------|-------------------------------------------------------------------------------------------------------------|
| **Add Todo**     | `todo <description>` <br> e.g., `todo Buy treats for Dipsy`                                                 |
| **Add Deadline** | `deadline <description> /by <date>` <br> e.g., `deadline Submit report /by 2024-05-12`                      |
| **Add Event**    | `event <description> /from <start> /to <end>` <br> e.g., `event Conference /from 2024-08-12 /to 2024-08-14` |
| **Delete**       | `delete <index>` <br> e.g., `delete 3`                                                                      |
| **Mark**         | `mark <index>` <br> e.g., `mark 1`                                                                          |
| **Unmark**       | `unmark <index>` <br> e.g., `unmark 1`                                                                      |
| **List**         | `list` <br> `list <date>` <br> e.g., `list 2024-05-12`                                                      |
| **Exit**         | `bye`                                                                                                       |