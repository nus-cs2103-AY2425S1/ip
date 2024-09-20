# Conversage User Guide


![Product Screenshot](Ui.png)


ConverSage is a chatbot application designed to help you manage your tasks efficiently. It supports various types of tasks such as ToDos, Deadlines, and Events, and provides a simple and intuitive interface for interacting with the bot.

## Adding Deadlines

To add a deadline task, use the `deadline` command followed by the task description and the deadline date and time.

Example: `deadline finish project /by 2024-09-09 2359`

Expected outcome:

```
Understood, I've added this task: [D][ ] finish project (by: Sep 9 2024, 11:59PM)
You have 1 tasks in your list. 
```


## Adding ToDo Tasks

To add a ToDo task, use the `todo` command followed by the task description.

Example: `todo read book`

Expected outcome:

```
Understood, I've added this task: [T][ ] read book
You have 2 tasks in your list.
```

## Adding Event Tasks
To add a ToDo task, use the `event` command followed by the event description and from and to date and time.

Example: `event finish meditation /from 2024-09-09 2300 /to 2024-09-09 2359`

Expected outcome:

```
Understood, I've added this task: [E][ ] finish meditation (from: Sep 9 2024, 11:00PM to: Sep 9 2024, 11:59PM)
You have 3 tasks in your list.
```


## Listing All Tasks

To list all tasks, use the `list` command.

Example: `list`

Expected outcome:

```
1. [D][ ] finish project (by: Sep 9 2024, 11:59PM)
2. [T][ ] read book
3. [E][ ] finish meditation (from: Sep 9 2024, 11:00PM to: Sep 9 2024, 11:59PM)

```



## Marking Tasks As Done

To mark a task as done, use the `mark` command followed by the task number.

Example: `mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] finish project (by: Sep 9 2024, 11:59PM)

```


## Unmarking Tasks

To unmark a task, use the `unmark` command followed by the task number.

Example: `unmark 1`

Expected outcome:

```
I've marked this task as not done yet, get to it quickly.
[D][ ] finish project (by: Sep 9 2024, 11:59PM)
```


## Deleting Tasks

To delete a task, use the `delete` command followed by the task number.

Example: `delete 1`

Expected outcome:

```
Noted, I've removed this task:
  [D][ ] finish project (by: Sep 9 2024, 11:59PM)
You have 2 tasks in your list.
```


## Finding Tasks

To find tasks containing a specific keyword, use the `find` command followed by the keyword.

Example: `find book`

Expected outcome:

```
Unveiled by the sage's insight, these tasks resonate with your search:
1. [T][ ] read book

```


## Exiting The Application

To exit the application, use the `bye` command.

Example: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```


## Getting Help

If you ever need help with the format of the commands/what commands are available, use the `help` command

Example: `help`

Expected outcome:
```
Here are the available commands:
1. todo [task description] - Adds a ToDo task
2. deadline [task description] /by [date] - Adds a Deadline task
3. event [task description] /from [start time] /to [end time] - Adds an Event task
4. list - Lists all tasks
5. mark [task number] - Marks a task as done
6. unmark [task number] - Unmarks a task
7. delete [task number] - Deletes a task              
8. find [keyword] - Finds tasks containing the keyword
9. bye - Exits the application                        
10. help - Displays this help message

```