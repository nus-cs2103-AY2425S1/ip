#  Trackie User Guide


![](./Ui.png)


Introducing the Trackie Bot - Add different types of tasks and manage your tasks with ease!

## Adding a todo task
Todo tasks are tasks with just a description.
Say, you wanted to add a todo task with the description "task1", 
here's how you would do it:\
`t task1`

The bot will return:
> Added: task1

## Adding a deadline task
Deadline tasks are tasks that come with a deadline.
To add a deadline task, simply provide the description
and the deadline as a date-time in "yyyymmdd hhmm" format.

For instance, let's say that you want to add the following deadline:
- Description: finish assignment
- Deadline: 26th September 2024, 2359

This is done by issuing the following command:\
`d finish assignment /by 20240926 2359`

As a result, the bot will return:
> Added: finish assignment (by: 26 Sep 2024, 11:59:00pm)

## Adding an event task
Event tasks are tasks that have both a start and end time.
To add an event task, simply provide the description, the start time and the end time.
Both the start time and end time should be in the same date-time format as that of the
deadline task.

For instance, to add the following event:
- Description: attend party
- Start time: 19th August 2021, 1900
- End time: 19th August 2021, 2300

This is how it's done:\
`e attend party /from 20210819 1900 /to 20210819 2300`

And this is what you'll get:
> Added: attend party (from: 19 Aug 2021, 7:00:00pm to: 19 Aug 2021, 11:00:00pm)

## Listing Tasks
To see a list of all your tasks, simply type `ls`.

This will yield you a list of all your tasks, for instance:
> Here are your current tasks: <br> <br> 1. [D][ ] finish project (by: 10 Jul 2020, 7:00:00am) <br> 2. [T][ ] walk the dog

## Marking/Unmarking Tasks
You can choose to mark/unmark tasks by typing "mark x" where x is the 
index of the task you want to mark/unmark **according to the list shown
by the list command.**

Examples:

`mark 1`: 
> Gratz, you've completed: task 1

`unmark 2`:
> Aight, I've unmarked the following: another task


## Deleting Tasks
Simply type "rm" followed by the index of the task to delete said task.

Example:

`rm 2`:
> Deleted: do my homework

## Finding Tasks
If you would like to find a task, simply use the `find` command as follows: `find task1`.

All the tasks that have "task1" as a substring of their description will be included in the list of tasks found.
For instance, "task1" and "sometask123" are both matching queries.

The bot will return the number of relevant tasks found and list those tasks as follows:
>2 matching task(s) found: <br> <br> task1 <br> sometask123 (by: 1 Jan 2020, 12:00:00am)

## Help page
If you're unsure about the list of commands that are available, you can always
type `help` to bring up the list of available commands.
