# Bob User Guide

![BobUi](docs/Ui.png)

Bob is a user-friendly task managing chatbot designed to help you keep track of 
your tasks, deadlines, and events efficiently. With a simple command-based 
interface, Bob allows you to add, edit, and manage your tasks effortlessly. 
Whether you're managing deadlines or keeping track of events, Bob's got you covered!

## Features

### 1. Add Tasks
- **To-Do**: Add simple tasks with just a description.
    - Command: `todo [description]`
    - Example: `todo Read a book`

- **Deadline**: Add tasks with a specific deadline.
    - Command: `deadline [description] /by [dd/mm/yyyy HHmm]`
    - Example: `deadline Submit assignment /by 25/09/2024 2359`

- **Event**: Add tasks with a specific start and end time.
    - Command: `event [description] /from [start time] /to [end time]`
    - Example: `event Project meeting /from 24/09/2024 1400 /to 24/09/2024 1600`

### 2. View Tasks
- View all tasks in your list.
    - Command: `list`
    - Example: `list`

### 3. Mark Tasks as Done
- Mark a task as completed.
    - Command: `done [task number]`
    - Example: `done 2`

### 4. Delete Tasks
- Remove a task from your list.
    - Command: `delete [task number]`
    - Example: `delete 3`

### 5. Find Tasks
- Search for tasks by a keyword.
    - Command: `find [keyword]`
    - Example: `find meeting`

### 6. Update Tasks
- Update details of existing tasks, such as changing the end time of an event.
    - Command: `update [task number] [new details]`
    - Example: `update 4 /to 25/09/2024 1800`

### 7. Error Handling
- Bob provides informative error messages for invalid commands, ensuring you always know what went wrong.

