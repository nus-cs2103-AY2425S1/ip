# Luke User Guide

![Screenshot of Luke application](Ui.png)

## What is Luke?
Luke is your best friend who will take note of what you need to do.

## Functionalities
Luke take notes of the tasks for the user. All are considered **Task**.

### Adding todo
Todo, as the name suggest, are things that you want to do.
It will take note of the things you need to do and will remember it.

For example, you want to buy some flower for your waifu so you note down "Buy flowers for waifu" 
and duke will remember it in his text based brain.

Example usage: `todo buy flower for waifu`

Upon successful execution, it will tell you the todo has been added.

### Adding deadlines
Deadline, as the name suggest, is the things you want to do but has a deadline.

Example usage: `deadline buy flower for waifu /by 2024-01-01`

**Note the date supplied must be strictly in the format of yyyy-MM-dd**

Upon successful execution, it will show you that the deadline is added.

**Note that the date will automatically be changed to MMM dd yyyy format**

### Adding event
Event has a start and ending date.

Example usage: `event go on a date with wifey /from 2024-01-01 /to 2024-01-01`

**Note the date supplied must be strictly in the format of yyyy-MM-dd**

Upon successful execution, it will show you that the event is added.

**Note that the date will automatically be changed to MMM dd yyyy format**

### Adding note
Note is just for note-taking purpose. It has the same functionality as todo except that it does not have to be completed.

Example usage: `note wifey doesn't like me playing league of legends`

Upon successful execution, it will show that note has been added.

### Finding tasks
Finding is used to find tasks that matches your description.

Example usage: `find wifey`

Upon successful execution, it will show a list of tasks with description that contains wifey.

### Listing tasks
List as the name suggest lists all the tasks that are currently remembered by Luke.

Example usage: `list`

Upon successful execution, it will list all the tasks that are currently in Luke's memory.

### Mark tasks
Marking a task will mark it to be complete. You need to tell Luke which tasks you want to mark as complete.
Marking an already completed task will do nothing.

Example usage: `mark 4`

**Note that the tasks number is not fixed, it will change depending on the order it is currently in.**

Upon successful execution, Luke will tell you that the task number 4 has been marked.

### Unmark tasks
Unmarking will unmark as completed tasks as incomplete. Unmarking a incomplete tasks will do nothing.

Example usage: `unmark 4`

**Note that the tasks number is not fixed, it will change depending on the order it is currently in.**

Upon successful execution, Luke will tell you that the task number 4 has been unmarked.

### Bye
Bye will just bid you farewell, but like all best friend, he will still linger around to see if you need anything else.

Example usage: `bye`

Upon successful execution, Luke will say "Aight, Cya later".

## Errors
If you encounter any error, Luke will guide you through and tell you what is wrong when possible. Don't fret hombre, the toothpick has your back.