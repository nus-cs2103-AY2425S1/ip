# **ChoaticBot User Guide**
>"I hate perfection. To be perfect is to be unable to improve any further." -  Kurotsuchi Mayuri

ChoaticBot frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

All you need to do is,

1. ensure you have Java `17` on your computer.
2. download ChoaticBot from [here](https://github.com/choaticman/ip/releases/tag/A-Jar).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE!**

What can ChoaticBot do for you:

- [x]  Managing tasks
- [x]  Managing deadlines
- [x]  Managing events
- [ ]  Reminders (coming soon)

![Screenshot of Choaticbot](./Ui.png)

## Features

#### Adding a task to do: `todo`
Adds a ToDo task to the task list.

Format: `todo <Task to do>`

Example: `todo Catch Rayquaza`

<br/>

#### Adding a task with a deadline: `deadline`
Adds a task with a deadline to the task list.

Format: `deadline <Task to do> /by <Date>`
- `Date` is in the format of `yy-MM-dd HH:mm`

Example: `deadline Submit CS2103T IP /by 2024-09-20 23:59`

<br/>

#### Adding a task with a date: `event`
Adds a task with a date to the task list.

Format: `event <Task to do> /from <Start> /to <End>`

Example: `event CS2103T Hackathon 2024 /from Monday /to Wednesday`

<br/>

#### Listing all tasks: `list`
Shows a list of all the tasks.

Format: `list`

<br/>

#### Marking a task as completed: `mark`
Marks the specified task as completed.

Format: `mark <Index>`
- Marks the task at the specified `Index`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: `mark 1`

<br/>

#### Marking a task as uncompleted: `unmark`
Marks the specified task as uncompleted.

Format: `unmark <Index>`
- Unmarks the task at the specified `Index`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: `unmark 1`

<br/>

#### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete <Index>`
- Deletes the task at the specified `Index`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: `delete 1`

<br/>

#### Updating a task: `update`
Updates the specified task from the task list.

Format: `update <Index> <Details>`
- Edits the person at the specified `INDEX`. 
- The index refers to the index number shown in the displayed person list. 
- The index must be a positive integer 1, 2, 3, ...
- `Details` follows the format of the task type.

Examples: 

`update 1 Catch Mewtwo` updates the task to do at index 1.

`update 2 Submit CS2100 Assignment /by 2024-09-16 13:00` updates the task with a deadline at index 2

`update 3 CS2100 Hackathon 2025 /from January 31 /to February 2` updates the task with a date at index 3.

<br/>

#### Finding a keyword: `find`
Shows a list of tasks that contains the keyword.

Format: `find <Keyword>`

Examples: `find Rayquaza` shows the task to do `Catch Rayquaza`.

<br/>

#### Exiting the program: `bye`
Exits the program.

Format: `bye`

<br/>

#### Saving the data
ChoaticBot tasks data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.