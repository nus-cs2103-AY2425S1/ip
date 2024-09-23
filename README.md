# Makima User Guide

The following user guide is written for Window users. The application may or may not work 
with other operating systems. Makima is a desktop app for managing generic tasks. 
Makima implements the following type of tasks:

1. ToDo: a named task
2. Deadline: a named task with an end date
3. Event: a named task with a start and end date

- [Quick start](#quick-start)
- [Command Help](#command-help)
    - [todo](#todo)
    - [deadline](#deadline)
    - [event](#event)
    - [delete](#delete)
    - [mark](#mark)
    - [unmark](#unmark)
    - [list](#list)
    - [find](#find)
    - [prioritise](#prioritise)
    - [return](#return)
    - [bye](#bye)
- [Command Summary](#command-summary)
- [Credits](#credits)


## Quick Start

1. Ensure you have Java `17` or above installed.

<details>
<summary>How to check?</summary>

Run the following command in your command terminal:
```
java --version
```
</details>

2. Download the latest jar file from [here](https://github.com/Edsel-Tan/ip/releases/)
3. Copy the file to the folder you want to use as your *home folder* for the application.
4. Run the following command in your command terminal:

```
java -jar makima.jar
```

![GUI](Ui.png)

## Command Help

<details>
<summary>Note on command syntax </summary>

Makima process each command in stages. For example, if the user wants to add a todo task,
they must specify the name of the todo task. The application will then prompt for these input
in seperate stages.

The command formats are then specified as follows:

- \n demarks the end of a stage of a command.
  e.g. in `todo \n TASK_NAME`, the user enters todo. After the bot replies 
  and requests for the task name, the user enters the relevant task name.
- Words in UPPER_CASE are parameters to be supplied by the user.
  e.g. in `todo \n TASK_NAME`, the `TASK_NAME` refers to the desired task name.
- Trailing and leading whitespaces are ignored.
  
</details>

<details>
<summary>Example</summary>

![GUI](Ui2.png)
</details>

<details>
<summary>Why is done this way?</summary>

Some commands require quite a few inputs. Separating the command into stages means that
if one of the fields is formatted wrongly, the user can simply reenter that specific
field! Furthermore, we have implemented a return command so that if you would like to exit
at any stage of the command, you may do so. See [return](#return).

</details>

### todo

Adds a todo.

Format: `todo \n TASK_NAME`

<details>
<summary>Examples</summary>

- `todo \n Buy groceries`
- `todo \n Finish writing the report`
- `todo \n Call the dentist`
</details>

### deadline

Adds a deadline.

Format: `deadline \n TASK_NAME \n END_DATE`

<details>
<summary>Examples</summary>

- `deadline \n Submit assignment \n 2024-09-30T15:00`
- `deadline \n Pay electricity bill \n 2024-10-05T12:00`
- `deadline \n Prepare for meeting \n 2024-09-25T09:30`
</details>

### event

Adds an event.

Format: `event \n EVENT_NAME \n START_DATE \n END_DATE`

<details>
<summary>Examples</summary>

- `event \n Team lunch \n 2024-10-01T13:00 \n 2024-10-01T14:00`
- `event \n Birthday party \n 2024-11-15T18:00 \n 2024-11-15T21:00`
- `event \n Project kickoff \n 2024-09-28T10:00 \n 2024-09-28T11:30`
</details>

### delete

Deletes a task.

Format: `delete \n TASK_INDEX`

<details>
<summary>Examples</summary>

- `delete \n 1` (Deletes the first task)
- `delete \n 3` (Deletes the third task)
</details>

### mark

Marks a task as complete.

Format: `mark \n TASK_INDEX`

<details>
<summary>Examples</summary>

- `mark \n 2` (Marks the second task as complete)
- `mark \n 1` (Marks the first task as complete)
</details>

### unmark

Unmarks a task.

Format: `unmark \n TASK_INDEX`

<details>
<summary>Examples</summary>

- `unmark \n 1` (Unmarks the first task)
- `unmark \n 3` (Unmarks the third task)
</details>

### list

Displays the list of tasks.

Format: `list`

<details>
<summary>Examples</summary>

- `list` (This will show all tasks)
</details>

### find

Finds the task with name containing the specified string.

Format: `find \n SEARCH_TERM`

<details>
<summary>Examples</summary>

- `find \n report` (Finds tasks containing "report")
- `find \n groceries` (Finds tasks containing "groceries")
</details>

### prioritise

Prioritises the selected task.

Format: `prioritise \n TASK_INDEX \n PRIORITY`

<details>
<summary>Examples</summary>

- `prioritise \n 2 \n high` (Sets the priority of the second task to high)
- `prioritise \n 1 \n low` (Sets the priority of the first task to low)
</details>

### return

Exits out the current command.

Format: `return`

<details>
<summary>Examples</summary>

- `return`
</details>

### bye

Exits out the program.

Format: `bye`

<details>
<summary>Examples</summary>

- `bye`
</details>

## Command Summary
| Command     | Description                                      | Format                                     |
|-------------|--------------------------------------------------|--------------------------------------------|
| `todo`      | Adds a todo                                     | `todo \n TASK_NAME`                       |
| `deadline`  | Adds a deadline                                 | `deadline \n TASK_NAME \n DATE`           |
| `event`     | Adds an event                                   | `event \n EVENT_NAME \n START_DATE \n END_DATE` |
| `delete`    | Deletes a task                                  | `delete \n TASK_INDEX`                    |
| `mark`      | Marks a task as complete                        | `mark \n TASK_INDEX`                      |
| `unmark`    | Unmarks a task                                  | `unmark \n TASK_INDEX`                    |
| `list`      | Displays the list of tasks                      | `list`                                     |
| `find`      | Finds tasks by name containing a string         | `find \n SEARCH_TERM`                     |
| `prioritise`| Prioritises a task                              | `prioritise \n TASK_INDEX \n PRIORITY`   |
| `return`    | Exits the current command                       | `return`                                   |
| `bye`       | Exits the program                               | `bye`                                      |

## Credits
Image credits: 
-  [Bot profile](https://www.pixiv.net/en/artworks/103412000)
-  [User profile](https://yorushika.lnk.to/Moonbath)
