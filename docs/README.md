# Darwin

This is a chatbot with an interface that allows users to keep track of tasks.
![Ui](Ui.png)

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest `.jar` file from here.
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar darwin.jar` command to run the application.

## Features
### Adding a task
There are 3 types of tasks.
#### ToDo
`todo DESCRIPTION`: adds a ToDo task with the given description.

Example of usage:
- `todo read book`
- `T read book` (short form)

#### Deadline
`deadline DESCRIPTION /by DATE`: adds a Deadline task with the given description and date.

Example of usage:
- `deadline return book /by 2024-09-30 12:00`
- `D return book /by 2024-09-30 12:00` (short form)

#### Event
`event DESCRIPTION /from START_DATE /to END_DATE`: adds an Event task with the given description, start date and end date.

Example of usage:
- `event project meeting /from 2024-09-30 12:00 /to 2024-10-01 12:00`
- `E project meeting /from 2024-09-30 12:00 /to 2024-10-01 12:00` (short form)

### Listing all tasks
`list`: lists all tasks in the chatbot.

Example of usage:
- `list`

### Marking / Unmarking of tasks
#### Marking as done
`done INDEX`: marks the task at the specified index as done.

Example of usage:
- `done 1` marks task 1 as done.

#### Unmarking as done
`undone INDEX`: unmarks the task at the specified index as done.

Example of usage:
- `undone 1` unmarks task 1 as done.

### Deleting a task
`delete INDEX`: deletes the task at the specified index.

Example of usage:
- `delete 1` deletes task 1.

### Finding tasks
`find KEYWORD`: finds all tasks with the specified keyword.

Example of usage:
- `find book` finds all tasks with the keyword "book".

### Saving the data
Darwin data is saved in the file `tasks.txt` automatically after any command that changes the data. There is no need to save manually.