# NextGPT Chatbot User Guide


Welcome! This is a greenfield Java project. It's named the GBot. Given below are instructions on how to use it.

## Setting Up NextGPT

1. To get started with NextGPT, ensure that you have Java 11 or above installed.
2. Download nextgpt.jar from here.
3. Copy the jar file into an empty folder.
4. Open a command window in that folder.
5. Run the command java -jar nextgpt.jar.

## Features
> [!NOTE]
> - All parameters are mandatory. Commands with parameters that are in the wrong format (e.g. wrong order, empty parameters, additional parameters) will cause an error.
> - Extraneous parameters for commands that do not take in parameters (such as list and exit) will be ignored.
    e.g. if the command specifies list 123, it will be interpreted as list.
> - Date formats must be in yyyy-mm-dd e.g. `10th September 2024` should be formatted as `"2024-08-10"`.

### Adding todo task: `Todo`
Adds a task with no time restriction to your list.

Format: `todo NAME`

Examples:
- `todo homework`


### Adding deadline task: `Deadline`
Adds a task with a deadline to your task list.

Format: `deadline NAME /by BY_DATE`

- Only ONE BY_DATE is  allowed

Examples:
- `deadline complete assignment /by 2024-03-01`


### Adding event task: `Event`
Adds an event that falls with a start and end date to your list.

Format: `event NAME /from START_DATE /to /END_DATE`

- Multiple FROM_DATE and TO_DATE are not allowed

Examples:
- `event holiday /from 2024-08-10 /to 2024-08-15`


### Show list of tasks: `List`
Show list of all tasks.

Format: `list`


### Mark task as done: `Mark`
Marks the specified task from the list as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `mark 2` marks the second task in the list as done.
- `mark 5` in a list with less than 5 tasks eg 3 tasks will produce an error message.


### Mark task as not done: `Unmark`
Marks the specified task from the list as not done.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX` as not done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `unmark 2` marks the second task in the list as not done.
- `unmark 5` in a list with 3 tasks will produce an error message.


### Remove task from list: `Delete`
Removes the specified task from the list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `delete 2` deletes the second task from the list.
- `delete 5` in a list with 3 tasks will produce an error message.


### Search for task: `Find`
Searches for tasks with a specified keyword.

Format: `find KEYWORD`

- The search is case-insensitive. e.g `Book` will match `book`
- Only the task name is searched.
- Partial words can be matched e.g. `work` will match `homework`
- Supports the search of one keyword only


### Show list of events for a date: `Schedule`
Shows the list of events for a specified date.

Format: `schedule DATE`

Examples:
- `schedule 2024-01-01`


### Exit the program: `Bye`
Exits the program.

Format: `bye`