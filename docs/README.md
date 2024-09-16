# Barney User Guide

![Ui.png](./Ui.png)

Barney the dinosaur is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Barney can help you manage your tasks faster than traditional GUI apps.

## Features

### List all task: `list`

List all the tasks in the application.

Format: `list`

### Mark a task: `mark`

Mark a task as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` as done.
- Index must be a positive integer 1, 2, 3, ...

### Unmark a task: `unmark`

Unmark a task as done.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX` as undone.
- Index must be a positive integer 1, 2, 3, ...

### Tag a task: `tag`

Tag a task with a keyword.

Format: `tag INDEX /tag KEYWORD`

- Tags the task at the specified `INDEX` with the `KEYWORD`.
- Index must be a positive integer 1, 2, 3, ...
- There must be a `/tag` before the `KEYWORD`.
- To remove a tag, use the `tag INDEX /tag`.

Examples

- `tag 2 /tag important`
- `tag 3 /tag urgent`

### Adding a todo task: `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION`

- The `DESCRIPTION` must not be empty.
- The `DESCRIPTION` can contain spaces.

Examples

- `todo read book`
- `todo return book`
- `todo buy book`

### Adding a deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline DESCRIPTION /by DATE`

- The `DESCRIPTION` must not be empty.
- The `DESCRIPTION` can contain spaces.
- The `DATE` can be in any format.
- The `DATE` must be after the `/by`.
- The `/by` must be present.

Examples

- `deadline return book /by 2021-09-30`
- `deadline return book /by 2021-09-30 1800`
- `deadline return book /by 2021-09-30 6pm`

### Adding an event task: `event`

Adds an event task to the list.

Format: `event DESCRIPTION /from DATE /to DATE`

- The `DESCRIPTION` must not be empty.
- The `DESCRIPTION` can contain spaces.
- The `DATE` can be in any format.
- The `DATE` must be after the `/from` and before the `/to`.
- The `/from` and `/to` must be present.

Examples

- `event project meeting /from 2021-09-30 1800 /to 2021-09-30 2000`
- `event project meeting /from 2021-09-30 6pm /to 2021-09-30 8pm`
- `event project meeting /from 2021-09-30 1800 /to 2021-09-30 8pm`

### Finding a task: `find`

Finds tasks that contain the keyword.

Format: `find KEYWORD`

- The `KEYWORD` must not be empty.
- The `KEYWORD` can contain spaces.
- The `KEYWORD` is case-sensitive.
- The `KEYWORD` can be a partial match.

Examples

- `find book`
- `find return`
- `find project`

### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- Index must be a positive integer 1, 2, 3, ...
- The task will be permanently removed from the list.

### Exiting the application: `bye`

Exits the application.

Format: `bye`
