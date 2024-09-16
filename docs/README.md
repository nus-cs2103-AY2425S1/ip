# BottleOpener

## Overview
**BottleOpener** is a task management chatbot that helps you
track and manage various tasks such as todos, deadlines,
and events. The chatbot provides an interactive way to
manage your tasks through a simple command-line interface (CLI)
or graphical interface (GUI).

## Features
- **Add** tasks: Easily add ToDo, Deadline, or Event tasks.
- **Mark** tasks: Mark tasks as done or undone.
- **Delete** tasks: Remove tasks you no longer need.
- **List** tasks: View all tasks in the task list.
- **Remind** deadlines: Receive automatic reminders for
  upcoming deadlines.

## Quick Start
1. Launch the application.
2. Type in commands.

### Commands
+ `todo` creates a simple task that does not have a deadline or time.
```declarative
todo <task_description>
```
+ `deadline` creates a task with a due date.
```declarative
deadline <task_description> /by <due_date>
```
+ `event` creates a task that is scheduled to happen at a specific time.
```declarative
event <task_description> /from <start_time> /to <end_time>
```
+ `list` displays all the tasks stored in the chat bot.
```declarative
list
```
+ `mark` / `unmark` marks or unmarks a specified task as completed or not completed.
```declarative
mark <task_number>
unmark <task_number>
```
+ `delete` removes a task from the list.
```declarative
delete <task_number>
```
+ `remind` displays all the tasks with upcoming deadlines.
```declarative
remind
```
+ `bye` exits and closes the chat bot.
```declarative
bye
```
