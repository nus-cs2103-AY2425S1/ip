# Yapper User Guide

---

**Yapper** is a desktop chatbot designed to help users stay organized with their tasks and events.
It offers **quick setup, intuitive commands, and real-time data saving**,
all optimized for users who prefer a Command Line Interface (CLI) while still benefiting from a
Graphical User Interface (GUI).

![Yapper UI](./Ui.png?raw=true "Yapper Chatbot")

## Setup

---

1. Ensure Java 17 or above is installed on your computer.
2. Download the latest `Yapper.jar` file from [here](https://github.com/valhrd/ip/releases/tag/A-Jar).
3. Open the terminal and navigate to the folder where `Yapper.jar` is located.
4. Run the command: ```java -jar Yapper.jar```
5. Start using Yapper! A window should appear similar to the screenshot above and greet you with the following:

```
Hi, I'm Yapper!
I run things around here.
If you need help, type 'help' or '?' for a list of available commands.
```

## Features

---

### Add tasks

There are 3 types of tasks can be added to the list:

**To-do's**

Format: ```todo TASK_DESCRIPTION```

Example: ```todo Getting started on the assignment```

**Deadlines**

Format: ```deadline TASK_DESCRIPTION /by DEADLINE```

Example: ```deadline Individual project /by Friday```

**Events**

Format: ```event TASK_DESCRIPTION /from START_DATE /to END_DATE```

Example: ```event Comicon /from This Monday /to This Wednesday```

For deadlines and events, **Yapper** is able to read the input of dates and times and convert them accordingly.

Example:

```event Comicon /from 24/11/2024 0900 /to 26/11/2024 2359``` will be converted into:

```[E][ ] Comicon (from: Nov 24 2024 09:00 am to: Nov 26 2024 11:59 pm)```

### List tasks

List out the existing tasks that were created.

Format: ```list```

### Mark tasks

Tasks can be marked one at a time by referencing their index (1-indexed) on the task list.

Format: ```mark TASK_INDEX```

Tasks can also be unmarked.

Format: ```unmark TASK_INDEX```

The ```TASK_INDEX``` should also be a **positive integer** (e.g. 1, 2, 3, ...)

### Find tasks

Find and list out tasks containing a certain keyword.

Format: ```find KEYWORD```

### Delete tasks

Similar to mark/unmark, delete tasks based on their index on the list.

Format: ```delete TASK_INDEX```

### Clear tasks

Clear all the task list using the following command.

Format: ```clear```

### Help menu

A help menu is available in case you forget any commands. The correct format for each command is
also provided.

Format: ```help```

### Bind custom command names
    
Bind a provided alias to an existing command

Format: ```bind ALIAS COMMAND_TO_ALIAS```
Example: ```bind addTodo todo```

Executing the above example will make ```addTodo``` usable for adding to-do's.
Executing ```addTodo Some task``` will result in the following being added to the task list:

```[T][ ] Some task```

### Unbind custom commands

Unbind existing command aliases

Following the example for ```bind```, executing the following:
```unbind addTodo```

Removes the association between ```addTodo``` and the function to add to-do's.

### Resetting commands to default

Removes all aliases added and resets the available commands to its default state.

Format: ```reset```

---

## Exiting the program

Once you're done, simply type ```bye``` in the input and the program will close. Don't worry as
all your task information will be saved.

---

## Command Summary

---

The table below contains all available commands, those under the "Aliases" section indicate that they can
replace the corresponding command and provide the same output.

Example:

```
deadline Submit assignment /by 20/9/2024 2359
d Submit assignment /by 20/9/2024 2359
D Submit assignment /by 20/9/2024 2359
```
All produce the same output of adding a deadline due 20 Sep 2024 11:59 pm.


| Command  | Aliases   | Format                                                     |
|----------|-----------|------------------------------------------------------------|
| todo     | t, T      | ```todo TASK_DESCRIPTION```                                |
| deadline | d, D      | ```deadline TASK_DESCRIPTION /by DEADLINE```               |
| event    | e, E      | ```event TASK_DESCRIPTION /from START_DATE /to END_DATE``` |
| list     | ls        | ```list```                                                 | 
| mark     | m         | ```mark TASK_INDEX```                                      |
| unmark   | um        | ```unmark TASK_INDEX```                                    |
| find     |           | ```find KEYWORD```                                         |
| delete   | del       | ```delete TASK_INDEX```                                    |
| clear    |           | ```clear```                                                |
| help     | ?         | ```help```                                                 |
| bind     |           | ```bind ALIAS COMMAND_TO_ALIAS```                          |
| unbind   |           | ```unbind ALIAS```                                         |
| reset    |           | ```reset```                                                |
| bye      | exit,quit | ```bye```                                                  |
