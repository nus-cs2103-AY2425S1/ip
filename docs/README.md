# Evan User Guide

Evan is a chatbot that manages your tasks in a simple and user-friendly way.

![Screenshot of the Evan chatbot application](Ui.png)

## Quickstart

1. Ensure you have Java `17` or above installed in your computer.

2. Download the latest `.jar` from [here](https://github.com/zackjh/ip/releases).

3. Copy the file to the folder you want to use as the *home* folder for Evan.

4. Open a command terminal, `cd` into the folder your put the `.jar` file in, and use the `java -jar evan.jar` command
   to run the application.

   A GUI similar to the one shown in the image above should appear in a few seconds.

5. Type a command in the text field and press `Enter` to execute it. E.g. Typing `help` and pressing `Enter` will prompt
   Evan to show you the available commands.
6. Refer to the features below to view a more detailed breakdown of each command.

## Features

### Listing tasks: `list`

Lists all currently tracked tasks.

Format: `list`

### Adding tasks: `todo`, `deadline`, and `event`

Adds a task, which can be either a `todo`, `deadline`, or `event`

Format:

- `todo <description>`
- `deadline <description> /by <when>`
- `event <description> /from <start> /to <end>`

`<when>`, `<start>`, and `<end>` can be a:

- Plain string
- Date, with the format: `yyyy-MM-dd` (e.g. `2024-11-02`)
- Datetime, with the format: `yyyy-MM-dd HHmm` (e.g. `2024-11-02 1830`)

### Marking task as complete/uncompleted: `mark` and `unmark`

Updates the status of the task with the given task number.

Format:

- `mark <task_number>`
- `unmark <task_number>`

### Deleting a task: `delete`

Deletes a task with the given task number.

Format: `delete <task_number>`

### Finding a task: `find`

Finds a task with a matching description.

Format: `find <description>`

### Getting help: `help`

Displays the commands that Evan accepts.

Format: `help`

### Saving the data

Your tasks are automatically stored in the file `data/tasks.txt` whenever you make a change to your task list.
There is no need (or way) to save manually.

### Editing the data file

**WARNING: PROCEED WITH CAUTION**

You can manually edit `data/tasks.txt` to update your saved tasks.
However, if any part of the file contains invalid text, the entire save file will be overwritten.

