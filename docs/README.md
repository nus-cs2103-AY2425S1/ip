# SilverWolf User Guide

![](Ui.png)

Having short term memory and not keeping track of your task?

Wanna chat with Silver Wolf?

Introducing the Silver Wolf bot, your ultimate companion to keep track of tasks.

## Main functions:

You can add 3 types of tasks:
- Todos
- Deadlines
- Events

## List of commands and its friendlier syntax :
 - deadline (d)
 - event (e)
 - todo (t)
 - list (l)
 - mark (m)
 - unmark (u)
 - find (f)
 - delete (r)
 - bye (b)

## Running the application

To run the application. ensure you have java 17 install on your computer.
Open terminal on mac or windows and enter this command:

```shell
java -jar silverWolf.jar
```

## Adding Deadlines

To add a deadline in SilverWolf, use the following command:

### Command
`deadline <description> /by dd/mm/yyyy hhmm`

### Example
```shell
deadline Finish CS2103T assignment /by 2/12/2024 1800
```
## Adding Event

To add an event in SilverWolf, use the following command:

### Command
`event <description> /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm`

### Example
```shell
event RC4 Directors meeting /from 29/12/2024 1800 /to 29/12/2024 1830
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
`find <description>`

### Example
```shell
find books
```

Finding without any input will list down all the tasks.

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

Doing so will save your list to a file. Note that the chatbot will load the save file if any. 
Otherwise, it will create a new save file when the chat bot application is running.











