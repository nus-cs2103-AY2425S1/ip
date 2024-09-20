# MichaelBot

![Ui.png](Ui.png)

MichaelBot helps you stay on top of all your tasks! 

##  *Quick Start*

1. Check if you have Java 17 or above installed.
2. Download the latest .jar file from [here](https://github.com/arjun2598/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for the chat bot.
4. Open a command terminal, cd into the home folder and execute `java -jar michael.jar` to run the chat bot.
5. Refer to the Features below for details of available commands for MichaelBot.

## *Features*

### Adding Tasks: `todo`, `deadline` or `event`

Adds a task for the chat bot to keep track of.

- A simple task can be added as `todo [task]`.

- A task that needs to be completed by a certain deadline can be added as `deadline [task name] /by [YYYY-MM-DD]`.
- An event that takes place from a start time to an end time can be added as `event [task] /from [start] /to [end]`.

*Examples*:
- `todo read book`
- `deadline return book /by 2024-09-30`
- `event group meeting /from Mon 4pm /to 6pm`


MichaelBot returns your task as confirmation as follows:

```
Got it. I've added this task: 
[D][] return book (by: 30 Sep 2024)
Now you have X tasks in the list.
```

### Marking/Unmarking Tasks

Using the `mark/unmark` command, an existing task can be marked as done or unmarked.

To use these commands, follow the format: "mark X/ unmark X", where X is the position of the task on the list.

Example: `mark 1 / unmark 3`

MichaelBot shows your task as marked as follows:

```
Nice! I've marked this task as done: 
[D][X] return book (by: 30 Sep 2024) 
```

### Deleting tasks

Using the `delete` command, an existing task can be removed from the list.

To delete a task, follow the format: "delete X", where X is the position of the task on the list.

Example: `delete 1`

MichaelBot shows your deleted task as follows:

```
Noted. I've removed this task: 
[D][X] return book (by: 30 Sep 2024)
Now you have X tasks in the list.
```

### Viewing all tasks

All added tasks can be viewed using `list`

### Searching for specific tasks

MichaelBot can search for specific tasks using `find [keyword]`

- The search is case-sensitive
- Parts of the word will also be matched e.g. `re` will match both `read book` and `return book` tasks in the chat bot
- The order of the keywords matters: `find read book` will not return the same results as `find book read`

### Sorting tasks

MichaelBot can sort your tasks in alphabetical order using `sort`

### Exiting 

The program can be exited using `bye`

### Saving data

MichaelBot automatically saves the tasks added and deleted, and there is no need for manual saves.