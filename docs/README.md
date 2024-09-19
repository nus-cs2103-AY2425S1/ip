# DUCK User Guide 🦆🦆
> __"HONK! HONK! HONK!"__ _-Duck_

![Product screenshot.](Ui.png)

Too many things to track? DUCK knows exactly how that feels! DUCK is here to help you lessen the load.

DUCK has many functions to help you! It is:
- Fun!
- Easy!
- Fast!

Read on to find out what DUCK can do!

## Adding Tasks

Command: 
- todo <task_description>

   Example: `todo quiz`


- deadline <task_description> /by <YYYY-MM-DD>

  Example: `deadline assignment /by 2024-01-01`


- event <task_description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>

  Example: `event meeting /from 2024-01-02 /to 2024-01-03`

DUCK will save all the tasks locally!

## Listing Tasks

Command: `list`

DUCK lists all the tasks you have save, for example:

```
1. [T][ ] quiz
2. [D][ ] assignment (by: Jan 1 2024)
3. [E][ ] meeting (from: Jan 2 2024 to: Jan 3 2024)
```
## Delete A Task

Command: delete <task_number>

Example:   `delete 2`

DUCK deletes the task at the given number! The confirmation message is:
```
Noted. I've removed this task:
[D][X] assignment (by: Jan 1 2024)
Now you have 2 tasks in the list.
```

## Mark A Task

Command: mark <task_number>

Example:   `mark 2`

DUCK marks the task at the given number as done! The confirmation message is:
```
Nice! I've marked this task as done:
[D][X] assignment (by: Jan 1 2024)
```
## Unmark A Task

Command: unmark <task_number>

Example:   `unmark 2`

DUCK unmarks the task at the given number. The confirmation message is:
```
OK, I've marked this task as not done yet:
[D][ ] assignment (by: Jan 1 2024)
```
## Find A Task

Command: find <keyword>

Example:   `find assignment`

DUCK returns all tasks whose descriptions contains the keywords. They are listed as:
```
Here are the matching tasks in your list:
1. [T][ ] CS2103 assignment
2. [D][ ] CS2109S assignment (by: Jan 1 2024)
```

## Undo A Command

Command: `undo`

Regret it? Don't worry DUCK can also reverse your last command.


# TO DOWNLOAD:
1. Download from [here](https://github.com/althea28/ip)
2. Double-click it to start chatting
3. Add tasks
4. Let **DUCK** manage your tasks for you