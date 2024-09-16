# ThatOneGuy User Guide

ThatOneGuy is a task management application that seems to absolutely hate its job.

Nevertheless, it will still help keep track of tasks, so you don't have to.


![ThatOneGuyUI](./Ui.png)
The app in use

## Adding tasks

Three task types are supported by ThatOneGuy:
* Todos have a description attached.
* Deadlines have a due date in addition to a description.
* Events have two dates, each depicting the start and end of the event, as well as a description.

To add a todo, use `todo <task description>`

To add a deadline, use `deadline <task description> /by <due date>`

To add an event, use `event <task description> /from <start date> /to <end date>`

Note that dates are formatted as YYYY-MM-DD hh:mm (eg. 2024-10-20 12:30)

Example 1: `todo break something` outputs:
```
Fine. Added this lousy task:
[T] [ ] break something
That's 1 tasks for your ass to handle.
```

Example 2: `deadline whack table /by 2024-09-01 12:00` outputs:
```
Fine. Added this lousy task:
[D] [ ] whack table (by: 2024-09-01 12:00)
That's 2 tasks for your ass to handle.
```

Example 3: `event malding /from 2024-09-02 11:00 /to 2024-09-04 19:00` outputs:
```
Fine. Added this lousy task:
[E] [ ] malding (from: 2024-09-02 11:00 to: 2024-09-04 19:00)
That's 3 tasks for your ass to handle.
```

## Listing tasks

Bring up a list of your current tasks using `list`.

This is the output in this case:
```
Here are your damned tasks. Complete them or something.
1. [T] [ ] break something
2. [D] [ ] whack table (by: 2024-09-01 12:00)
3. [E] [ ] malding (from: 2024-09-02 11:00 to: 2024-09-04 19:00)
```

## Marking tasks as complete/incomplete
 ThatOneGuy supports marking tasks as complete/incomplete. It even alerts you if a task was already marked as complete/incomplete.
 
To mark a task as complete, use `mark <task number>`.

Example 1: `mark 2`