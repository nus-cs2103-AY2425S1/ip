# Wojak User Guide

Wojak is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having 
the benefits of a Graphical User Interface (GUI). If you can type fast, Wojak can get your tasks 
management done faster than traditional GUI apps.

- [Quick Start](#quick-start)
- Features
  - [Adding a todo: `todo`](#adding-a-todo)
  - [Adding a deadline](#adding-a-deadline) `deadline`
  - [Adding an event](#adding-an-event) `event`
  - [Listing all tasks](#listing-all-tasks): `list`
  - [Find a task: `find`](#find-a-task)
  - [Mark a task: `mark`](#mark-a-task)
  - [Unmark a task: `unmark`](#unmark-a-task)
  - [Delete a task `delete`](#delete-a-task)
  - [Modify the DB](#modify-the-db)
  - [Exiting the program: `bye`](#exiting-the-program)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have java 17 or above installed in your computer
2. Download the latest .jar file from [here](https://github.com/se-edu/addressbook-level3/releases).
3. Copy the file to the folder you want to use as your home folder for your Wojak
4. Open a command terminal, `cd` into the folder you put the jar in, and use the `java -jar Wojak.jar`
command to run the application. A GUI similar to the below should appear in a few seconds.
![wojak](Ui.png)

## Features

- items in `<>` are compulsory in the commands.
  - eg. `todo <your input>`

## Adding a todo

Adds a todo into Wojak. Follow the example below and hit enter.

Format: `todo <your input>`

-  Note, input must not be empty

Examples:
- `todo homework`
- `todo many things i want`

## Adding a deadline
Adds a deadline into Wojak. Follow the example below and hit enter.

Format: `deadline <some deadline> /by <iso-8601 compliant date>`

-  Note, input must not be empty

Examples:
- `deadline some deadline /by 2024-10-01`


## Adding an event
Adds an event into Wojak. Follow the example below and hit enter.

Format: `event <some event> /from <iso-8601 compliant date> /to <iso-8601 compliant date>`

- Note, input must not be empty
- Also, no date validation logic, so anything goes.

Examples:
- `event some event /from 2024-10-01 /to 2024-10-22`
- `event some event /from 2025-10-01 /to 2024-10-22`

## Listing all tasks

List everything in your todo lists. Follow the example below and hit enter.

Format: `list`

Examples:
- `list`

## Find a task

Find a task (todo, deadline, event) that matches the search string. 
Follow the example below and hit enter.

Format: `find <search string>`

Examples
- `find some event`
- `find homework`

## Mark a task

Mark a task (todo, deadline, event) as done. Follow the example below and hit enter

Format: `mark <index of task>`
- List is not zero indexed, so don't use `mark 0`.

Examples
- `mark 1`


## Unmark a task

Unmark a task (todo, deadline, event) as undone. Follow the example below and hit enter

Format: `unmark <index of task>`
- List is not zero indexed, so don't use `mark 0`.

Examples
- `unmark 1`

## Delete a task
Delete a task (todo, deadline, event) from Wojak. Follow the example below and hit enter

Format: `delete <index of task>`
- List is not zero indexed, so don't use `mark 0`.

Examples
- `delete 1`

## Modify the DB

To modify where Wojak can persist data to and find the DB, 
go to `config.txt` and type in a name of the file. You need to have
read, write and execute permissions in the folder.

## Exiting the program

To exit the chatbot, type this command and press send

Format: `bye`

Examples
- `bye`

## FAQ

Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the 
data of your previous Wojak home folder (the `db.txt`).

## Known issues

## Command summary

1. Add todo, eg: `todo this is my todo`
2. Add deadline, eg: `deadline some deadline /by 2024-10-01`
3. Add event, eg: `event some event /from 2024-10-01 /to 2024-10-22`
4. List tasks, eg: `list`
5. Find a task, eg: `find deadline`
6. Mark a task, eg: `mark 1`
7. Unmark a task, eg: `unmark 1`
8. Delete a task, eg: `delete 1`
9. Exit the program, eg: `bye`
