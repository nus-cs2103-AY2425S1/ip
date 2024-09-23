# MAGA User Guide

![Ui.png](ui.png)

The MAGA bot is the one bot you need to managing deadlines. Add
tasks, add deadlines, and manage your time with the MAGA bot!

## Giving commands
Each command should be typed in the format:

`(keyword) (optional arguments)`,

with a whitespace after the keyword.

Currently, the only available commands are `list`, `mark`, `unmark`,
`delete`, `tag`, `find`, `todo`, `event`, `deadline` and the
exit command `bye`.

## Add To-Do Tasks

Add to-do tasks with a description and no deadline.

Format: `todo (description)`

Example: `todo buy chicken`

The bot will save a to-do task with the description "buy chicken".
A successful use of the command will show the following:

```
Another task for the American people added:
[T][ ] buy chicken
You have 1 task(s) now!
```
## Add Event Tasks

Add event tasks with a description and an event date.

Format: `event (description)/(deadline)` (the date should be
typed in the YYYY-MM-DD format)

Example: `event buy milk/2024-09-30`

The bot will save an event task with the description "buy milk" with
an event date of 2024-9-30. A successful use of the command will show 
the following:

```
Another task for the American people added:
[E][ ] buy milk
You have 2 task(s) now!
```

## Add Deadline Tasks

Add deadline tasks with a description, a beginning and an end date.

Format: `event (description)/(begin)/(end)` (the begin and end
dates should be typed in the YYYY-MM-DD format)

Example: `deadline buy beef/2024-9-30/2024-10-02`

The bot will save a deadline task with the description "buy beef"
from 2024-9-30 to 2024-10-02. A successful use of the command will
show the following:

```
Another task for the American people added:
[D][ ] buy beef
You have 3 task(s) now!
```
## List Tasks
List all current tasks with the `list` command. The `list` command
takes in no additional parameters.

Format: `list`

The bot will list all current tasks in order of when it was added.
A successful use of the command will show the following:

```
Take a look, all the tasks you have here, so many,
yuuuuuuge

1. [T][ ] buy chicken
2. [E][ ] buy milk on 2024 Sep 30
3. [D][ ] buy beef from 2024 Sep 30 to 2024 Oct 02
```

## Mark Tasks
Mark tasks to be done. The `mark` command takes a task number
as a parameter

Format: `mark (number)`

Example: `mark 1`

The bot will mark the current task if it exists. Example output:

```
Ya boi Donald took the LIBERTY to mark this done:
[T][X] buy chicken
```

## Mark Tasks
Unmark tasks to be done. The `unmark` command takes a task number
as a parameter

Format: `unmark (number)`

Example: `unmark 1`

The bot will unmark the current task if it exists. Example output:

```
Here's the task promised but not completed, just like
the DEMS:
[T][ ] buy chicken
```

## Delete Task
Delete a task. The `delete` command takes a task number
as a parameter

Format: `delete (number)`

Example: `delete 1`

The bot will delete the task if it exists. Example output:

```
I've deleted this task:
[T][ ] buy chicken
You have 2 task(s) now!
```

## Find Task
Find a task. The `find` command takes a description as an input,
and returns all tasks who's task description contains it.

Format: `find (description)`

Example: `find milk`

The bot will return the tasks it finds. Example output:

```
Here are the matching tasks in your list:
1. [E][ ] buy milk on 2024-09-30
```

## Tag Task
Tag a task. The `tag` command takes a task number and a tag
as the input, and tags it to the task.

Format: `tag (task number) (tag)`

Example: `tag 1 #moo`

The bot will return a confirmation if it successfully tagged
the task. Example output:

```
Successfully tagged task 1!
```

## Close Bot
Close the bot by typing `bye`. The bot will send a goodbye message
and close automatically.

Format: `bye`

Example output:
```
Yea, I'ma see you in my next RALLY! A vote for me is a
vote for America!
```

## Save tasks
There is no command to save tasks in the bot - the bot automatically
does it for you after every command!

On launching the bot, the bot will automatically load the tasks
that were previously saved with no input required!