# Slave Chatbot User Guide

![Screenshot of Application](./Ui.png);

Slave is your very own chatbot to help you remember tasks that you have to do, albeit doing so very unwillingly.

## Quick Start

1. Ensure you have Java `17` or above installed in your computer.
1. Download the `.jar` file from Release v0.2.
1. Run the `.jar` file.

## Supported Tasks

### Non-Recurring Tasks

1. Todos

### Recurring Tasks

1. Deadlines (With an end date)
1. Events (With a start and end date)

### Recurring Frequencies

1. `NEVER`
1. `DAILY`
1. `WEEKLY`
1. `BIMONTHLY`
1. `MONTHLY`
1. `QUARTERLY`
1. `BIANNUALLY`
1. `ANNUALLY`

## Supported Commands

### Task Creation

- `todo NAME` - Creates a Todo with a name `NAME`.
- `deadline NAME /by DATE` - Creates a Deadline with a name `NAME` and a deadline `DATE`.
- `event NAME /from DATE1 /to DATE2` - Creates an Event with a name `NAME` from `DATE1` to `DATE2`.

The above commands sets the recurring frequency to `NEVER` by default.

You can specify the recurring frequency of the task by adding the `/rec FREQ` after the commands above to create a task
with a recuring frequency of `FREQ`.

### Searching For Tasks

- `list` - Prints all tasks currently stored in the task list.
- `schedule DATE` - Prints all recurring tasks that have not ended on the date `DATE`.
- `find SUBSTRING` - Prints all tasks with names containing the word `SUBSTRING`.

### Task Management

- `mark INDEX` - Marks the `INDEX`th task on the list as complete.
- `unmark INDEX` - Marks the `INDEX`th task on the list as incomplete.
- `delete INDEX` - Deletes the `INDEX`th task on the list.
- `clear` - Deletes all tasks in the list.

### Termination

- `bye` - Closes the application after 3s.

## Date formatting

All dates should follow the format yyyy-mm-dd

## Bad Inputs

In the event that you have not followed the format for commands, your speech bubble will turn red, indicating that
your command is not carried out.
