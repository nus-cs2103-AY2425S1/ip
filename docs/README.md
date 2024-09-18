# Talk-a-Bot User Guide

![Ui of Talk-a-Bot](/Ui.png)

Hiya, pal! Let Talk-a-Bot help you sort out your to-do list with ease!

Talk-a-bot is a Personal Assistant Chatbot designed to help users manage
various types of tasks such as `ToDo`, `Deadline` and `Event`.

Users can add and delete tasks, retrieve their task lists, and more!
Shorter command syntax is also available for the various commands,
allowing more convenient use of the app.

## Features

### Adding tasks

Add simple todo tasks, tasks with deadlines, and events to your task list.

#### ToDo

ToDo tasks consist of only the description of the task.

Format: `todo <description>`

Example:

`todo homework`

Expected Output:

```
Gosh! Another task to complete?
We've got a hustler in the Clubhouse!
Alright buddy, I've added this task:
  [T][ ] homework
to your list!
You now have 4 tasks in total!
```

#### Deadline

Deadline tasks consist of the description of the task and its deadline.

Format: `deadline <description> /by YYYY-MM-DD`

Example:

`deadline submit iP /by 2024-09-20`

Expected Output:

```
Gosh! Another task to complete?
We've got a hustler in the Clubhouse!
Alright buddy, I've added this task:
  [D][ ] submit iP (by: Sep 20 2024)
to your list!
You now have 5 tasks in total!
```

#### Event

Event tasks consist of the description of the task as well as its start and end dates.

Format: `event <description> /from YYYY-MM-DD /to YYYY-MM-DD`

Example:

`event hackathon /from 2024-10-11 /to 2024-10-13`

Expected Output:

```
Gosh! Another task to complete?
We've got a hustler in the Clubhouse!
Alright buddy, I've added this task:
  [E][ ] hackathon (from: Oct 11 2024 to: Oct 13 2024)
to your list!
You now have 6 tasks in total!
```

### Retrieving Task List

Retrieve your current saved task list.

Format: `list`

Expected Output:

```
No problem at all, pal! Here's your to-do list:
  1. [E][ ] dance (from: Dec 6 2024 to: Dec 8 2024)
  2. [D][ ] find Minnie (by: Oct 1 2024)
  3. [T][ ] ask Toodles for help
You're such a go-getter!
```

### Marking Tasks

Mark tasks as completed when done.

Format: `mark <task number>`

Example: `mark 3`

Expected Output:

```
Hot dog! Gee, I really admire your determination!
Another task down:
  [T][X] ask Toodles for help
```

This action can be reversed.

Format: `unmark <task number>`

Example: `unmark 3`

Expected Output:

```
Aw, shucks. No worries, I've marked this task as not done yet:
  [T][ ] ask Toodles for help
It's alright, buddy!
With your dedication, I'm sure it'll be done in no time!
```

### Deleting Tasks

Delete tasks that you no longer need to track.

Format: `delete <task number>`

Example: `delete 4`

Expected Output:

```
Sure thing, no biggie! I've removed this task:
  [T][ ] homework
Your list looks much neater now, pal!
You now have 5 tasks in total.
```

### Finding Tasks

Find tasks containing the keyword(s) or letter(s) given.

Format: `find <keywords/letters>`

Example: `find hack`

Expected Output:

```
Not a problem, Talk-a-Bot's here to help!
Alright pal, here's what I found based on your search:
  1. [E][ ] hackathon (from: Oct 11 2024 to: Oct 13 2024)
```

### Getting Day of the Week

Find out which days of the week `Deadline` and `Event` tasks fall on.

Format: `get day <task number>`

Example: `get day 4`

Expected Output:

```
Gee whiz! Looks like this task is due on a Friday!
```

### Shorter Syntax for Commands

Shorter aliases for commands are available to make Talk-a-Bot more user-friendly.
Refer to the table below for the list of aliases:

| Command    | Alias |
|------------|:-----:|
| `todo`     |  `t`  |
| `deadline` |  `d`  |
| `event`    |  `e`  |
| `list`     |  `l`  |
| `mark`     |  `m`  |
| `unmark`   | `um`  |
| `delete`   | `del` |
| `find`     |  `f`  |
| `get day`  | `gd`  |

### Exiting Talk-a-Bot

Once you are done using Talk-a-Bot, you can exit the program.

Format: `bye`

Expected Output:

```
Aw, shucks. Alright then...
See ya real soon!
```

The application stops running 3 seconds after the goodbye message is displayed.