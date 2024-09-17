# Snah User Guide

![Screenshot of Snah](./Ui.png)

**Snah** is the ultimate productivity tool for the next generation 🚀🚀

Features includes:

- Create a **ToDo** ✅
- Create a **Deadline** 📅
- Create an **Event** 🎫
- Marking tasks as **Done** ⭕

Quality of Life Features:

- Comprehensive help command
- Clear all tasks on one go!
- Saving tasks locally

# Create Tasks

## Adding ToDos

Add a todo to remind you of what you need to do in the future

Command: `todo <description>`

```bash
# todo Cook a delicious meal
[ ] (Todo) Cook a delicious meal
```

## Adding Events

Add an event that includes the start and end time of the event

Command: `event <description> /from <start note> /to <end note>`

```bash
# event Swifty tour /from when I have money /to tomorrow
[ ] (Event: from when I have money to tomorrow) Swifty tour
```

## Adding Deadlines

Add a deadline that includes a date that is beautifully formatted

Command: `deadline <description> /by <date in yyyy-mm-dd>`

```bash
# deadline NOC Sign up /by 2024-09-18
[ ] (Deadline: by Sep 18 2024) NOC Sign up
```

# Exitting the App

## Bye bye

Closing the app once you are done

Command: `bye`

```bash
# bye
Application terminated
```

# Manage Tasks

## See all tasks

See all your available tasks, sorted by the time you added them in

Command: `list`

```bash
# list
1. [ ] (Event: from when I have money to tomorrow) Swifty tour
2. [ ] (Deadline: by Sep 18 2024) NOC Sign up
```

## Find tasks

Find tasks by keywords, including dates and where they start or end

Command: `find <query word>`

```bash
# find money
1. [ ] (Event: from when I have money to tomorrow) Swifty tour
```

## Mark/Unmark task

Mark task as complete to track your progress

Command: `mark <task number>`

```bash
# mark 1
[x] (Event: from when I have money to tomorrow) Swifty tour
```

Command: `unmark <task number>`

Unmark task that has not been completed

```bash
# unmark 1
[ ] (Event: from when I have money to tomorrow) Swifty tour
```

## Delete task

Delete task that is no longer used

Command: `delete <task number>`

```bash
# delete 1
Alright, the task is removed
[x] (Event: from when I have money to tomorrow) Swifty tour
```

## Clear tasks

Shortcut to delete all tasks

> ⚠️ **WARNING!** ⚠️  
> This is **not reversible**

Command: `clear`

```bash
# clear
Tasks cleared
```

# Miscellaneous Commands

## Getting help

Get a list of all available commands and how to use them

Command: `help`

```bash
# help
Tasks cleared
```
