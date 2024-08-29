# SilverWolf User Guide

Welcome to the SilverWolf User Guide! This guide will help you understand how to use SilverWolf chat bot effectively.

## List of commands:
 - deadline
 - event
 - todo
 - list
 - mark
 - unmark
 - find
 - delete
 - bye
## Adding Deadlines

To add a deadline in SilverWolf, use the following command:

### Command
`deadline <description> /by <date>`

### Example
```shell
deadline Finish CS2103T assignment /by 2024-08-31
```
## Adding Event

To add an event in SilverWolf, use the following command:

### Command
`event <description> /from <date> /to <date>`

### Example
```shell
event Project Meeting /from 2024-08-31 /to 2040-08-34
```

## Adding Todo

To add todo in SilverWolf, use the following command:

### Command
`todo <description>`

### Example
```shell
todo eat dinner
```
## List

To show the full list of tasks, use the following command:

### Command
`list`

## Mark

To mark a task as done, use the following command:

### Command
`mark <index>`

Note: to get the index, use `list` command which displays the index.

### Example
```shell
mark 1
```

## Unmark

To unmark a task as not done, use the following command:

### Command
`unmark <index>`

Note: to get the index, use `list` command which displays the index.

### Example
```shell
unmark 1
```

## Find

To search for a task(s), use the following command:

### Command
`search <description>`

### Example
```shell
search books
```

## Delete

To delete a task, use the following command:

### Command
`delete <index>`

Note: to get the index, use `list` command which displays the index.

### Example
```shell
delete 2
```

## Bye

To terminate the chatbot, use the following command:

### Command
`bye`

Doing so will save your list to a file. Note that the chatbot will load the save file if an of create a new save file when the chat bot application is running.











