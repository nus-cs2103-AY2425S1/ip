# SumoDE

This is a task management chatbot. Given below are instructions on how to use it.

[Quick Start](https://github.com/FooChao/ip?tab=readme-ov-file#quick-start)

[Features](https://github.com/FooChao/ip?tab=readme-ov-file#features)

## Quick Start

1. Ensure you have Java 17 or above on your computer.
2. Download the latest `.jar` file from [here](https://github.com/FooChao/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Double click the .jar file to run it.

## Features

### Adding a todo task `todo` 

Format: todo TASKDESCRIPTION

Example: todo study CS1231S


### Adding a deadline task `deadline`

Format: deadline TASKDESCRIPTION /by DEADLINE

Example: deadline Finish CS2103T ip /by 20 Sep 2024


### Adding an event task `event`

Format: event TASKDESCRIPTION /from START /to END

Example: event mug for exam /from 20 Sep 2024 /to 29 Sep 2024

> [!NOTE]
> To prevent errors, task with exact same name are not allowed.

### Listing all stored tasks `list`

Format: list

### Listing matching tasks `find`

Format: find STRINGTOMATCH

Example: find study

### Mark a task as done `mark`

Format: mark INDEX

Example: mark 1

### Mark a task as not completed `unmark`

Format: unmark INDEX

Example: unmark 1

### Delete a task `delete`

Format: delete INDEX

Example: delete 1

### Close the app `bye`

Format: bye
