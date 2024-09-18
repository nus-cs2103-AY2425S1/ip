# Park User Guide

![Screenshot of product](Ui.png)

Park is a desktop app for managing tasks. Here are the commands you need to know to get started.

### Viewing help: `help`

Shows a simplified list of commands in the app.

Format: `help`

### Viewing task list: `list`

Shows a list of all tasks.

Format: `list`

### Adding a todo task: `todo`

Adds a task of type todo to the list. This task only has a description.

Format: `todo DESC`

Example: `todo Submit internship applications`

### Adding a deadline task: `deadline`

Adds a task of type deadline to the list. This task has a description and a deadline.

Format: `deadline DESC /by DATETIME`
- Deadline must be in the format `yyyy-MM-dd HHmm`.

Example: `deadline Submit iP final version /by 2024-09-18 2359`

### Adding a event task: `event`

Adds a task of type event to the list. This task has a description, start and end.

Format: `event DESC /from START /to END`
- Start and end must be in the format `yyyy-MM-dd HHmm`.

Example: `event CS2103 Tutorial /from 2024-09-20 1200 /to 2024-09-20 1300`

### Marking a task as done: `mark`

Marks a task at the specified index as done.

Format: `mark INDEX`

### Marking a task as not done: `unmark`

Marks a task at the specified index as not done.

Format: `unmark INDEX`

### Deleting a task: `delete`

Deletes a task at the specified index.

Format: `delete INDEX`

### Finding a task: `find`

Finds tasks with descriptions that match a specified input.

Format: `find KEYWORD`
- The search is case-sensitive.
- Tasks will match as long as their description contains the keyword e.g. searching `Tu` will match `CS2103 Tutorial`

Example: `find Submit` returns `Submit internship applications` and `Submit iP final version`.

### Exiting the application: `bye`

Exits the application.

Format: `bye`