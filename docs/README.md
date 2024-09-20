# Ned User Guide

![Screenshot of Ned's UI](Ui.png)

Ned is a chatbot application. Normally, a lord of winterfell, you now hold power over him as the 1 true ruler of the 
Seven Kingdoms. He is here to help you with your rule, by helping you record down tasks.

After all, a wise king seeks the help of his friends and allies, do they not?

## Adding todos
Todos are a special type of task, with no start or end date.

To add todos, you need to specify:
- A description

Examples of correct usage:
- `todo <task description>`
- `todo read`
- `todo watch CS2100 lecture`

A successful execution of `todo` will create a new todo task which is added to the list of tasks
```
Aye, I've added this task m'lord:
   [T][] read
Now you've 1 tasks left. Get to it then! 
```
## Adding deadlines
Deadlines are a special type of task, with a fixed due date.

To add deadlines, you need to specify:
- A description
- A date by which the deadline is due by (in ISO 8601)

Examples of correct usage:
- `deadline <task description> /by <ISO 8601 timing>`
- `deadline do assignment 1 /by 2024-09-18`
- `deadline submit homework /by 2030-02-20`


A successful execution of `deadline` will create a new deadline task, which is added to the list of tasks.
```
Aye, I've added this task m'lord:
   [D][] read (by: SEPTEMBER 30 2024)
Now you've 1 tasks left. Get to it then!
```
## Adding events
Events are a special type of task, with a fixed start date and a fixed end date.

To add events, you need to specify:
- A task description
- A start date, the 'from' date (in ISO 8601)
- An end date, the 'to' date (in ISO 8601)

Examples of correct usage:
- `event <task description> /from <ISO 8601 timing> /by <ISO 8601 timing>`
- `event exam week /from 2024-09-24 /to 2024-09-31`
- `event holiday /from 2024-10-02 /to 2024-10-07`

A successful execution of `event` will create a new event task, which is added to the list of tasks.
```
Aye, I've added this task m'lord:
   [E][] read (from: SEPTEMBER 30 2024 to: SEPTEMBER 31 2024)
Now you've 1 tasks left. Get to it then!
```

## Marking tasks
Mark is used to change the status of a task to 'completed'.

To mark events, you need to specify:
- The index of the task you want to mark, which can be obtained by the `list` command

Examples of correct usage:
- `mark 1`
A successful execution of `mark` will change the status of a task to be 'completed'.
```
Good work. One down, more to go!
T[x] read
```

## Unmarking tasks
Unmark is used to change the status of a task to 'uncompleted'.

To unmark events, you need to specify:
- The index of the task you want to unmark, which can be obtained by the `list` 
command

Examples of correct usage:
- `unmark 1'
A successful execution of `unmark` will change the status of a task to be 'uncompleted'.
```
Oh no. One back up, more to go!
T[x] read
```

## Finding tasks
Find is used to find any tasks in the current lists of tasks, which contain as a substring, the search term specified.

To find tasks, you need to specify:
- A search term

Examples of correct usage:
- `find book`
- `find workout dumbbell`
A successful execution of `find` will return a list of all the tasks which contain the search term as a substring.

If no tasks are found, an empty string is returned.

```
1.[T][X] read book
1.[D][] return book (by: SEPTEMBER 20 2024)
```

## Listing tasks 
List is used to print out the list of tasks to the screen, complete with the indexes of tasks.

Examples of correct usage:
- `list`

A successful execution of `find` will return a list of all the tasks

```
 1.[T][] read
 2.[D][] return book (by: SEPTEMBER 20 2024)
```

## Deleting tasks
Delete is used to remove a specified task, through its provided index.

Examples of correct usage:
- `delete 1`

A successful execution of `delete` will remove the task from both the current list of tasks and cached list of tasks.

```
Aye, I've removed this task m'lord:
    [T][] workout
Now you've 3 tasks left. Get to it then!
```
## Getting a list of commands
Help is used to print the list of currently available commands, and the parameters needed for each command.

Examples of correct usage:
- `help`

A successful execution of `help` will list all currently available commands and how to use them.

```
    Usage:
    list                  - Shows the list of all tasks
    ...
```
## Quitting the chatbot
The `bye` command can be used to exit the application. 

Examples of correct usage:
- `bye`

A successful execution of `bye' will print a farewell message from ned, and close the application after 2 seconds.

```
   I wish you good fortune in the wars to come, m'lord.
```
