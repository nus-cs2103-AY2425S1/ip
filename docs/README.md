# Rex User Guide

Rex is many things. A chatbot, a task tracker, a ***companion***. 🦖

It is a desktop app for tracking tasks, 
optimized for use via a CLI while still having the benefits of a GUI. rawr 🦖

![Screenshot](./Ui.png)

## Features

Notes on commands:
- Extraneous parameters for commands will throw an error (eg. `help 123` will not be recognised, enter `help` instead)
- Erroneous parameters will also throw an error if entered in the wrong format
- Task descriptions must contain at least one character that is not whitespace
- Task numbers are relative to the list of **ALL** tasks
- Date format: DD-MM-YY (eg. `1 Jan 2024` and `1/1/24` will not be recognised, enter `01-01-24` instead)
- Time format: HHmm; 24-hour format (eg. `11:59pm` and `23:59` will not be recognised, enter `2359` instead)

Error messages will show the correct usage of the command, so fret not!
Alternatively, enter `help` to show the list of commands available! rawr 🦖

### Viewing Help
Shows the list of available commands and how to use them

Format: `help`

### Adding a task
Adds a ToDo, Deadline or Event task to Rex's huge memory for him to track them for you

Format:
- `todo <description>`
- `deadline <description> /by <date> <time>`
- `event <description> /from <date> <time> /to <date> <time>`

### Listing all tasks
Displays a list of added tasks, their statuses and times

Format: `list`

### Viewing a schedule
Displays the tasks to be done by or starting from the specified date

Format: `schedule <date>`

### Finding a task
Display the tasks that contains the specified keyword

Format: `find <keyword>`

### Marking/unmarking a task
Marks/unmarks tasks as completed and updates the list

Format:
- `mark <task number>`
- `unmark <task number>`

### Deleting a task
Deletes a task from the list

Format: `delete <task number>`

### rawr
Talks to Rex in an intellectual and sophisticated manner

Format: `rawr` 🦖 (keep it simple or Rex may get agitated)

### Exiting the program
Exits the program and politely saying bye to Rex

Format: `bye`

### Saving the data
Data on tasks is saved in Rex's massive brain (your computer's hard disk, actually) automatically after any command changes the data.
There is no need to save manually.

## Command Summary Table

| Command    | Action                             | Format                                                                       |
|:-----------|:-----------------------------------|:-----------------------------------------------------------------------------|
| `help`     | Show list of available commands    | `help`                                                                       |
| `todo`     | Adds a ToDo                        | `todo <description>`                                                         |
| `deadline` | Adds a Deadline                    | `deadline <description> /by <date> <time>`                                   |
| `event`    | Adds an Event                      | `event <description> /from <date> <time> /to <date> <time>`                  |
| `list`     | Display list of all tasks          | `list`                                                                       |
| `schedule` | Display tasks on specified date    | `schedule <date>`                                                            |                                                     
| `find`     | Display tasks that contain keyword | `find <keyword>`                                                             |
| `mark`     | Mark task as done                  | `mark <task number>`                                                         |
| `unmark`   | Mark task as not done              | `unmark <task number>`                                                       |
| `delete`   | Delete task from list              | `delete <task number>`                                                       |
| `rawr`     | rawr 🦖                            | `rawr`                                                                       |
| `bye`      | Exits the program                  | `bye`                                                                        |
