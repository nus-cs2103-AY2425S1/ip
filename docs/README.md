# Assistinator User Guide


// Product screenshot goes here

Assistinator is a **desktop application for managing tasks**. It supports a wide range of commands and a wide range of tasks
deadlines and events included.

## Features
### Listing all tasks: `list`

Shows a list of all tasks

Format: `list`


### Adding to-do tasks: `todo`

Adds a to-do task to the task list. These tasks do not have deadlines or timelines.

**Format:** `todo [DESCRIPTION]`

- `DESCRIPTION` is the description of the task.

**Example:** `todo read book`

- Adds a to-do task with description 'read book' to the task list.

**Expected Outcome:**

```
I have added this task:
[T][ ] read book
Your evil agenda contains 7 tasks
```
### Adding deadline tasks: `deadline`

Adds a task with deadline to the task list.

**Format:** `deadline [DESCRIPTION] /by [DEADLINE]`

- `DESCRIPTION` is the description of the task.
- `DEADLINE` is the deadline for the task in the format `YYYY-MM-DD HH:MM`

**Example:** `deadline submit proposal /by 2024-08-17 12:00`

- Adds a deadline task with description 'submit proposal' and deadline of 17 August 2024 12:00 PM

**Expected Outcome:**
```
I have added this task:
[D][ ] submit proposal (by: 2024-08-17 12:00)
Your evil agenda contains 8 tasks
```

### Adding event tasks: `event`

Adds an event task to the task list.

**Format:** `event [DESCRIPTION] /from [START] /to [END]`

- `DESCRIPTION` is the description of the task.
- `START` is the start time of the event task.
- `END` is the end time of the event task.

>[!NOTE] Entering new events with clashing time periods with existing events will produce a warning and new event will
> not be added.

**Example:** `event birthday party /from 2024-09-09 17:00 /to 2024-09-09 22:00`

- Adds an event task with description 'birthday party' from 9 September 2024 5:00 PM to 9 September 2024 10:00 PM

**Expected Outcome:**
```
I have added this task:
[E][ ] birthday party (from: 2024-09-09 17:00 to: 2024-09-09 22:00)
Your evil agenda contains 9 tasks
```
### Mark tasks as done: `mark`

Marks a task as done.

**Format:** `mark [INDEX]`

- Marks task at specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

**Example:** `mark 2`

- Marks the second task in the list as done

**Expected Outcome:**
```
I have marked task 2 as done:
1.[D][ ] submit proposal (by: 2024-08-17 12:00)
2.[T][X] read book
3.[D][ ] submit proposal (by: 2024-08-17 12:00)
4.[E][ ] birthday party (from: 2024-09-09 17:00 to: 2024-09-09 22:00)
```

### Unmark tasks as done: `unmark`

Marks a task as undone.

**Format:** `unmark [INDEX]`

- Marks task at specified `INDEX` as undone.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

**Example:** `unmark 2`

- Marks the second task in the list as undone

**Expected Outcome:**
```
I have marked task 2 as undone:
1.[D][ ] submit proposal (by: 2024-08-17 12:00)
2.[T][ ] read book
3.[D][ ] submit proposal (by: 2024-08-17 12:00)
4.[E][ ] birthday party (from: 2024-09-09 17:00 to: 2024-09-09 22:00)
```

### Delete tasks: `delete`

Deletes a specified task from the task list.

**Format:** `delete [INDEX]`

- Deletes task at specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

**Example:** `delete 5`

- Deletes the fifth task in the list.

**Expected Outcome:**
```
Task 5 deleted successfully
Your evil agenda contains 4 tasks
```

### Find a task using keyword: `find`

Finds tasks whose description contains specified keyword.

**Format:** `find [KEYWORD]`

- `[KEYWORD]` is the word you want to search for in tasks.

**Example:** `find submit`

- Lists all tasks that contain the keyword 'submit' 

**Expected Outcome:**
```
2.[D][ ] submit proposal (by: 2024-08-17 12:00)
4.[D][ ] submit assignment (by: 2024-09-08 12:00)
```

### Exiting application: `bye`

Exits the program.

**Format:** `bye`

**Expected Outcome:**
```
Goodbye. I hope to compute your evil schemes again soon.
```

### Saving the data

Assistinator data is saved automatically to a file named assistinator.txt in a folder named data, in the user's home 
directory. The data is automatically loaded when you start the application and the data is saved when you exit the 
application.

### Editing the data file

Assistinator data is automatically saved as a txt file `[JAR file location]/data/assistinator.txt`.
Advanced users are welcome to update data directly by editing that data file.

> [!CAUTION]
> Editing the data file directly with invalid formats may cause Assistinator to behave unexpectedly.
>Assistinator will only load the lines of data that are in the correct format. Data with incorrect format will be
>discarded when you exit the app.

### Command Summary

| Action   | Format, Examples                                                                                                  |
|----------|-------------------------------------------------------------------------------------------------------------------|
| List     | `list`                                                                                                            |
| Todo     | `todo [DESCRIPTION]`, `todo read book`                                                                            |
| Deadline | `deadline [DESCRIPTION] /by [DEADLINE]`, `deadline submit proposal /by 2024-08-17 12:00`                          |
| Event    | `event [DESCRIPTION] /from [START] /to [END]`, `event birthday party /from 2024-09-09 17:00 /to 2024-09-09 22:00` |
| Mark     | `unmark [INDEX]`, `mark 2`                                                                                        |
| Unmark   | `unmark [INDEX]`, `unmark 2`                                                                                      |
| Delete   | `delete [INDEX]`, `delete 5`                                                                                      |
| Find     | `find [KEYWORD]`, `find submit`                                                                                   |
| Bye      | `bye`                                                                                                             |