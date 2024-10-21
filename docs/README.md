# Vecrosen User Guide

![Screenshot of application](Ui.png)

Vecrosen is a simple memo app you can use to keep track of your tasks.

## Adding todos, deadlines, and/or clients

Entering todo/deadline/event followed by a sentence adds a deadline or event to the list of tasks.
Entering client adds the info of a client to the list of clients.

In the case of deadline, one additional argument has to be supplied: by.

In the case of event, two additional arguments have to be supplied: begin and end.

In the case of client, one additional argument has to be supplied: address.

In all cases, extra arguments are specified by writing the name of the argument following a slash
before the argument.

Example:
```
todo Chores
deadline Homework /by 2024-11-26
event Marathon /begin 2024-10-14 /end 2024-10-17
client Mary Smith /address 1 Apple St.
```

Result:
```
Todo added: Chores
Deadline added: Homework
Event added: Marathon
Client added: Mary Smith
```

## Deleting tasks

Entering "delete" followed by the task number will delete the task at that position on the list.

Example: `delete 1`

Result:
```
Removing task: Chores
You now have 1 tasks left in record.
```

## Marking tasks as complete/incomplete

Entering "mark" or "unmark" followed by the index of the task to update marks the selected task as complete/incomplete.

Example: `mark 1`

Result:
```
Task marked as complete: Homework
```

## Viewing the lists

Entering "list" displays the contents of both lists.

Example can be seen in the image above.

## Filtering the task list

Entering "find" followed by a search term shows only tasks which have said term as a substring.

Example: `find mew`

Result:
```
Here are the matching tasks:
1.[D][X] Homework (by: 26 Nov 2024)
```