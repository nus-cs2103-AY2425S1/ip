# Matcha User Guide 🍵

![Screenshot of Matcha app](Ui.png)

**Matcha** is a chatbot created to help users to **manage their tasks**. 
It is a CLI app with a GUI interface that allows users to **add**, **delete**, **mark** and **view** their tasks.

## Features

### View all tasks

You can view all your events, todos and deadlines via the `list` command.

Format: `list` 

Usage: `list`

Expected Output:
```
Here are your tasks:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: Dec 12 2024 12:12 pm)
    3. [E][ ] project meeting (from: Dec 12 2024 12:12 pm to: Dec 12 2024 12:12 pm)
```

### Add New Tasks

You can add new tasks to the list by using the `todo`, `event` and `deadline` commands.
>❗ Note: The date and time must be in the format `YYYY-MM-DD HHMM`.

Format:
1. `todo <description>`
2. `deadline <description> /by <DEADLINE DATE> <DEADLINE TIME>`
3. `event <description> /from <START DATE> <START TIME> /to <END DATE> <END TIME>`

Usage:
1. `todo borrow book`
2. `deadline return book /by 2024-12-12 12:12`
3. `event project meeting /from 2024-12-12 1212 /to 2024-12-12 1212`

Expected Output:
```
Alright, I have added this Todo:
	[T][ ] borrow book
You have 1 tasks in the list.
```
```
Alright, I have added this Deadline:
	[D][ ] return book (by: Dec 12 2024 12:12 pm)
You have 2 tasks in the list.
```
```
Alright, I have added this Event:
	[E][ ] project meeting (from: Dec 12 2024 12:12 pm to: Dec 12 2024 12:12 pm)
You have 3 tasks in the list.
```

### Detect duplicate tasks

Matcha will detect and prevent you from adding duplicate tasks.

Example Input:
```
todo borrow book
```
Expected Output:
```
This task already exists in the list! Task not added.
```

### Mark/Unmark Task

You can mark tasks as done or unmark them by using the `mark` or `unmark` command.

Format:
1. `mark <task number>`
2. `unmark <task number>`

Usage:
1. `mark 1`
2. `unmark 1`

Expected Output:
```
I have successfully marked this task as done:
    [T][X] borrow book
```
```
I have successfully mark this task as not done:
    [T][ ] borrow book
```

### Delete Task

You can delete tasks from the list by using the `delete` command.

Format: `delete <task number>`

Usage: `delete 1`

Expected Output:
```
Alright, I have removed this task:
    [T][ ] borrow book
You have 2 tasks in the list.
```

### Find Task

You can find specific tasks by using the `find` command.

Format: `find <keyword>`

Usage: `find book`

Expected Output:
```
Here are the matching tasks:
    1. [D][ ] return book (by: Dec 12 2024 12:12 pm)
```

## Save/Load Data

Matcha will automatically save your tasks when you exit your app.

## Exit App

You can exit the app by using the `bye` command.
>❗Note: App will close after a few seconds from issuing the command

Format: `bye`

Usage: `bye`

Expected Output:
```
Goodbye! Hope to see you again soon!
```

## Command Summary

| Command        | Format                                                                          | Usage                                                             | Expected Output                    |
|----------------|---------------------------------------------------------------------------------|-------------------------------------------------------------------|------------------------------------|
| View all tasks | `list`                                                                          | `list`                                                            | Lists all tasks                    |
| Add Todo       | `todo <description>`                                                            | `todo borrow book`                                                | Adds a new Todo task               |
| Add Deadline   | `deadline <description> /by <DATE> <TIME>`                                      | `deadline return book /by 2024-12-12 12:12`                       | Adds a new Deadline task           |
| Add Event      | `event <description> /from <START DATE> <START TIME> /to <END DATE> <END TIME>` | `event project meeting /from 2024-12-12 1212 /to 2024-12-12 1212` | Adds a new Event task              |
| Mark Task      | `mark <task number>`                                                            | `mark 1`                                                          | Marks a task as done               |
| Unmark Task    | `unmark <task number>`                                                          | `unmark 1`                                                        | Marks a task as not done           |
| Delete Task    | `delete <task number>`                                                          | `delete 1`                                                        | Deletes a task                     |
| Find Task      | `find <keyword>`                                                                | `find book`                                                       | Finds tasks that match the keyword |
| Exit App       | `bye`                                                                           | `bye`                                                             | Exits the app                      |
