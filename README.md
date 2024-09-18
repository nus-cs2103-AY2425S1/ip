# SumoDE

This is a task management chatbot. Given below are instructions on how to use it.

[Quick Start](https://foochao.github.io/ip/#quick-start)

[Features](https://foochao.github.io/ip/#features)
- [Adding a todo task `todo`](https://foochao.github.io/ip/#adding-a-todo-task-todo)
- [Adding a deadline task `deadline`](https://foochao.github.io/ip/#adding-a-deadline-task-deadline)
- [Adding an event task `event`](https://foochao.github.io/ip/#adding-an-event-task-event)
- [Listing all stored tasks `list`](https://foochao.github.io/ip/#listing-all-stored-tasks-list)
- [Listing matching tasks `find`](https://foochao.github.io/ip/#listing-matching-tasks-find)
- [Mark a task as done `mark`](https://foochao.github.io/ip/#mark-a-task-as-done-mark)
- [Mark a task as not completed `unmark`](https://foochao.github.io/ip/#mark-a-task-as-not-completed-unmark)
- [Delete a task `delete`](https://foochao.github.io/ip/#delete-a-task-delete)
- [Close the app `bye`](https://foochao.github.io/ip/#close-the-app-bye)

[Command Summary](https://foochao.github.io/ip/#command-summary)


![Screenshot of SumoDE's GUI](docs/Ui.png)



## Quick Start

1. Ensure you have Java 17 or above on your computer.
2. Download the latest `.jar` file from [here](https://github.com/FooChao/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Double click the .jar file to run it.

## Features

### Adding a todo task `todo` 

Format: todo TASKDESCRIPTION

Example: todo study CS1231S

Parameter: 
- TASKDESCRIPTION is compulsory and must be an non-empty string.


### Adding a deadline task `deadline`

Format: deadline TASKDESCRIPTION /by DEADLINE

Example: deadline Finish CS2103T ip /by 20 Sep 2024

Parameter: 
- TASKDESCRIPTION is compulsory and must be an non-empty string.
-  DEADLINE can be in the form of string or date or time (where day come before month e.g 10/09/2024 for 10th Sep 2024 and NOT 9th Oct 2024).
-  Almost all dates format (except those where month come before date) are allowed.


### Adding an event task `event`

Format: event TASKDESCRIPTION /from START /to END

Example: event mug for exam /from 20 Sep 2024 /to 29 Sep 2024

Parameter: 
- TASKDESCRIPTION is compulsory and must be an non-empty string.
-  START/END can be in the form of string or date or time (where day come before month e.g 10/09/2024 for 10th Sep 2024 and NOT 9th Oct 2024).
-  Almost all dates format (except those where month come before date) are allowed.

> [!NOTE]
> To prevent errors, task with exact same name are not allowed. (even if they are different task types).

### Listing all stored tasks `list`

Format: list

### Listing matching tasks `find`

Format: find STRINGTOMATCH

Example: find study

Parameter: 
- STRINGTOMATCH is compulsory and must be an non-empty string.

### Mark a task as done `mark`

Format: mark INDEX

Example: mark 1

Parameter:
- INDEX: The index must be a  positive integer and less than or equal to the total number of tasks.

### Mark a task as not completed `unmark`

Format: unmark INDEX

Example: unmark 1

Parameter:
- INDEX: The index must be a  positive integer and less than or equal to the total number of tasks.

### Delete a task `delete`

Format: delete INDEX

Example: delete 1

Parameter:
- INDEX: The index must be a  positive integer and less than or equal to the total number of tasks.

### Close the app `bye`

Format: bye

## Command Summary

| Action | Description | Example |
| --- | --- | --- |
| `todo` | todo TASKDESCRIPTION | todo study CS1231S |
| `deadline` | deadline TASKDESCRIPTION /by DEADLINE | deadline Finish CS2103T ip /by 20 Sep 2024 |
| `event` | event TASKDESCRIPTION /from START /to END | event mug for exam /from 20 Sep 2024 /to 29 Sep 2024 |
| `list` | list | list |
| `find` | find STRINGTOMATCH | find study |
| `mark` | mark INDEX | mark 1 |
| `unmark` | unmark INDEX | unmark 1 |
| `delete` | delete INDEX | delete 1 |
| `bye` | bye | bye |

