# Joe User Guide

Joe ChatBot is a desktop app that helps users manage their tasks efficiently. 
It supports different types of tasks such as todos, deadlines and events. 
It can be used through the GUI or command line.

![Joe bot](./Ui.png)
## Features

### Notes about the Command Format:

- Words in `{}` are parameters to be supplied by the user.  
  e.g. in `todo {description}`, `description` is a parameter which can be used as `todo Buy groceries`.

- Parameters must be provided in the order specified by the command.

### Viewing Help: `help`

Displays usage instructions for all available commands.

**Format:** `help`

### Viewing Help for a Specific Command: `help {command}`

Displays usage instructions for a specific command.

**Format:** `help {command}`

**Example**
- `help todo`

### Adding a New Todo: `todo`

Adds a new todo to the list.

**Format:** `todo {todo description}`

**Example:**
- `todo Read a book`

### Adding a New Deadline: `deadline`

Adds a new deadline task to the list. The due date must be in `yyyy-mm-dd` format.

**Format:** `deadline {deadline description} /by {duedate}`

**Example:**
- `deadline Submit assignment /by 2024-09-30`

### Adding a New Event: `event`

Adds a new event to the list with a start date and an <u>optional</u> end date. Dates must be in `yyyy-mm-dd` format.

**Format:** `event {event description} /from {start-date} /to {end-date}`

**Examples:**
- `event Project meeting /from 2024-10-01 /to 2024-10-01`
- `event Conference /from 2024-10-10`

### Listing All Tasks: `list`

Displays a list of all tasks.

**Format:** `list`

### Marking a Task as Complete: `mark`

Marks a task at the specified index as complete. The `index` corresponds to the task's position in the list.

**Format:** `mark {index}`

**Example:**
- `mark 2`

### Unmarking a Task as Incomplete: `unmark`

Unmarks a task at the specified index as incomplete. The `index` corresponds to the task's position in the list.

**Format:** `unmark {index}`

**Example:**
- `unmark 2`

### Deleting a Task: `delete`

Removes a task at the specified index from the list. The `index` corresponds to the task's position in the list.

**Format:** `delete {index}`

**Example:**
- `delete 1`

### Finding a Task: `find`

Finds tasks that match the provided query.

**Format:** `find {query}`

**Example:**
- `find assignment`

### Exiting the Program: `bye`

Saves the current state and exits the program.

**Format:** `bye`
