# Mittens: User Guide

![Screenshot of the UI](/docs/Ui.png)

_Mittens_ is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). Get through your day with the cute cat _Mittens_ and doing tasks will feel like a breeze!

## Quick start

1. Ensure you have Java `17` installed in your computer.
2. Download the latest `mittens.jar` from [here](izruff.github.io/ip/releases).
3. Open your Mittens app for the first time by running `java -jar mittens.jar` on your command line. A folder named `data` will be created, which Mittens will use to save your task list.
4. Try typing some commands into the input box and hit Enter or the Send button to see Mittens in action! Here are some commands to get you started:
> - `list`: lists all stored tasks
> - `todo Read book`: adds a todo task named "Read book"
> - `deadline Submit work \by 2024-09-30`: adds a deadline task named "Submit work" with a deadline of September 30th
> - `mark 1`: marks the first task in the list as done
> - `bye`: exits the app

## Adding tasks: `todo`, `deadline`, `event`

There are three types of tasks you can add:

- `todo`: A simple task with a description, no dates or times.
- `deadline`: A task with a description and the deadline date.
- `event`: A task with a description and a date range of when the event is happening.

Format:

- `todo <description>`
- `deadline <description> \by <date>`
- `event <description> \from <start-date> \to <end-date>`

Example:

- `todo Read book`
- `deadline Submit work \by 2024-09-30`
- `event Meeting \from 2024-09-30 \to 2024-10-01`

## List tasks: `list`

You can list all tasks you have stored in Mittens by typing `list`.

Each task is displayed in the following format:
> `[<task-type>][<done-status>] <description>`

For example, a done deadline task will be displayed as:
> `[D][X] Submit work (due Sep 30 2024)`

Format: `list`

## Mark tasks as done or not done: `mark`, `unmark`

You can mark a certain task as done or not done using `mark` and `unmark`.

Format:
- `mark <task-number>`
- `unmark <task-number>`

## Delete tasks: `delete`

You can delete a certain task using `delete`.

Format: `delete <task-number>`

## Find tasks: `find`

You can find tasks that contain a certain keyword using `find`.

Format: `find <keyword>`

## Exit the app: `bye`

You can exit the app by typing `bye`. Or, you can just exit through the window's close button.

Format: `bye`
