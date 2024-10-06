# 🧚‍♀️✨ TaskFairy User Guide ✨🧚‍♀️

![Ui](https://github.com/user-attachments/assets/09bb290f-d207-40de-afd4-dbf10ea82e49)

### 🗣️📢 TO ALL YOU BUSY BUSY BUSINESS LADIES (and gents) OUT THERE!!

TaskFairy is your own personal desktop bestie to help you stay on top of all your tasks 🧚‍♀️! She is **a desktop app for managing _all kinds of tasks_!!** With a quick and easy-to-use **Command Line Interface** (CLI) to type in and organise your various kinds of tasks, coupled with a cute, girly GUI to add a little sparkle of fairy dust to your boring planning routine~ 🪄✨!

## Contents
- [Quick Start](#quick-start)
- [Features](#features)
   - [Viewing help:`help`](#viewing-help)
   - [Adding tasks](#adding-tasks)
      - [Adding todos:`todo`](#adding-todos)
      - [Adding deadlines:`deadline`](#adding-deadlines)
      - [Adding events:`event`](#adding-events)
      - [Adding do-after tasks:`do`](#adding-do-after-tasks)
   - [Task Management](#task-management)
      - [Deleting tasks:`delete`](#deleting-tasks)
      - [Viewing your list:`list`](#viewing-your-list)
      - [Marking/Unmarking a task: `mark`/`unmark`](#marking-or-unmarking-a-task)
      - [Finding tasks by keyword:`find`](#finding-tasks-by-keyword)
- [Interacting with your fairy](#interacting-with-your-fairy)
- [Command summary](#command-summary)

## Quick Start
TaskFairy makes it easy to get started with your task management needs! Here's how to get her all set up...🪄
1. Ensure that you have Java `17` or above installed in your computer ⊹ ࣪ ˖
2. Download the latest `.jar` file from [here](https://github.com/sannie-beep/ip/releases) ⊹ ࣪ ˖
3. Copy the file to the folder you want to use as the *home folder* for your TaskFairy ⊹ ࣪ ˖
4. Open a command terminal, `cd` into the folder you put the jar file in and use the `java -jar taskfairy.jar` command to run the application ⊹ ࣪ ˖
A GUI similar to the screenshot at the top of the page should appear! ⊹ ࣪ ˖

You're all set to start adding tasks to your list and planning out your schedule, girlboss!🧚‍♀️⊹ ࣪ ˖

## Features
### To note about the command format 🪄:

- Words in `UPPER_CASE` or `DATETIME_FORMAT` are the parameters to be supplied by you, the user.\
  e.g. in `deadline DESCRIPTION /by DEADLINE_DATE_AND_TIME`, `DESCRIPTION` and  `DEADLINE_DATE_AND_TIME` are parameters that can be used as `deadline by/ 6/6/2020`.
  
- Parameters MUST be added in the specified order.\
  e.g. `event DESCRIPTION /from FROM_DATE_AND_TIME /to TO__DATE_AND_TIME` **cannot** be re-ordered as `event DESCRIPTION /to TO__DATE_AND_TIME /from FROM_DATE_AND_TIME`

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `bye`) will be ignored.\
e.g. if the command specifies `help me`, it will be interpreted as `help`.
  
### Viewing help
Displays all available commands TaskFairy supports ⊹ ࣪ ˖

Format: `help`

### Adding tasks
#### Adding todos
Adds a new `todo` task to the list ⊹ ࣪ ˖

Format: `todo DESCRIPTION`

Examples:
- `todo buy groceries`
- `todo gracefully take over company`
  
#### Adding deadlines
Adds a new `deadline` task to the list, with a description and deadline in the format `M/d/yyyy HHmm`⊹ ࣪ ˖

Format: `deadline DESCRIPTION /by DEADLINE_DATE_AND_TIME`
- The `DEADLINE_DATE_AND_TIME` **must be in the format** `M/d/yyyy HHmm`

Examples:
- `deadline project proposal /by 5/7/2020 2359`
- `deadline finish plan for mutiny /by 5/8/2020 1200`

#### Adding events
Adds a new `event` task to the list, with a description, from and to details in the format `M/d/yyyy HHmm`⊹ ࣪ ˖

Format: `event DESCRIPTION /from FROM_DATE_AND_TIME /to TO_DATE_AND_TIME`
- The `FROM_DATE_AND_TIME` and `TO_DATE_AND_TIME` **must be in the format** `M/d/yyyy HHmm`

Examples:
- `event lame meeting /from 5/3/2020 1500 /to 5/3/2020 1700`
- `event stage mutiny!! /from 5/9/2020 0900 /to 5/9/2020 1800`

#### Adding do-after tasks
Adds a new `do...after` task to the list, with a description and date to do after in the format `M/d/yyyy HHmm`⊹ ࣪ ˖

Format: `do DESCRIPTION /after DO_AFTER_DATE_AND_TIME`
- The `DO_AFTER_DATE_AND_TIME` **must be in the format** `M/d/yyyy HHmm` 
  
Examples:
- `do meeting minutes /after 5/3/2020 1700`
- `do steer company in new direction as new CEO ⊹ ࣪ ˖ /after 5/10/2020 0800`
  
### Task Management
#### Deleting tasks
Deletes the specified task from your list ⊹ ࣪ ˖

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index **must be a positive integer** 1,2,3,...

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the list

#### Viewing your list
Shows your task list, including what has been marked as done and not done.

Format: `list`

#### Marking or Unmarking a task
Mark a task as done with the `mark` command, or unmark it with `unmark` ⊹ ࣪ ˖

Format: `mark INDEX` or `unmark INDEX`
- Marks/unmarks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index **must be a positive integer** 1,2,3,...

Examples:
- `list` followed by `mark 2` marks the 2nd task in the list as done
- `unmark 2` undos the previous command

#### Finding tasks by keyword
Finds tasks whose descriptions contain any of the given keywords ⊹ ࣪ ˖

Format: `find KEYWORD [MORE_KEYWORDS]`
- The search is **CASE-SENSITIVE** e.g. `beth` is **NOT** the same as ``Beth``
- The **ORDER** of the keywords **MATTERS** e.g. `email beth` **DOES NOT MATCH** `beth email`
- Only the description is searched
- Only **FULL WORDS** will be matched e.g. `beth` will not match `bethany`

Example:
- `find beth` returns `[T][] email beth` and `[D][] update beth (by: Jun 07 2020, 10:00am)`

## Interacting with your fairy
Your fairy will respond to other commands with a touch of magic! 🧚‍♀️⊹ ࣪ ˖

Format: Up to you to find out 😉✨
> Hint: try greeting her or insulting her (not too much though, she has feelings too 😢)

## Command summary
Here's a quick reference for all the available commands ⊹ ࣪ ˖
## Command summary

| Action                          | Format                                  | Examples                                                    |
|----------------------------------|-----------------------------------------|-------------------------------------------------------------|
| **View help**                    | `help`                                  | `help`                                                      |
| **Add a todo task**              | `todo DESCRIPTION`                      | `todo buy groceries`                                        |
| **Add a deadline task**          | `deadline DESCRIPTION /by DEADLINE_DATE_AND_TIME` | `deadline project proposal /by 5/7/2020 2359`               |
| **Add an event task**            | `event DESCRIPTION /from FROM_DATE_AND_TIME /to TO_DATE_AND_TIME` | `event meeting /from 5/3/2020 1500 /to 5/3/2020 1700`       |
| **Add a do-after task**          | `do DESCRIPTION /after DO_AFTER_DATE_AND_TIME` | `do meeting minutes /after 5/3/2020 1700`                    |
| **Delete a task**                | `delete INDEX`                          | `delete 2`                                                  |
| **View task list**               | `list`                                  | `list`                                                      |
| **Mark a task as done**          | `mark INDEX`                            | `mark 2`                                                    |
| **Unmark a task as not done**    | `unmark INDEX`                          | `unmark 2`                                                  |
| **Find tasks by keyword**        | `find KEYWORD [MORE_KEYWORDS]`          | `find beth` / `find email beth`                                             |

