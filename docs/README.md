# Devon User Guide

![Devon Chatbot Screenshot](Ui.png)

Devon is an interactive chatbot designed to help you manage tasks 
efficiently. It supports task creation, categorization, marking
tasks as done, and more, all with an engaging personality.

## Adding todos

To add a todo task, simply provide the keyword `todo` followed by
the task description. Devon will add this to the list.

Example: `todo <task description>`

Expected output:
```
Got it. I've added this task:
    [T][] <task description>
Now you have <task count> tasks in the list.
```

## Adding deadlines

To add a deadline task, simply provide the keyword `deadline`
followed by the task description, the command `/by`, and the
deadline date and time, in `YYYY-MM-DD HHmm` format.
Devon will add this to the list.

Example: `deadline <task description> /by <date and time>`

Expected output:
```
Got it. I've added this task:
    [D][] <task description> (by: <date and time>)
Now you have <task count> tasks in the list.
```

## Adding events

To add an event task, simply provide the keyword `event`
followed by the task description, the command `/from`, the
start date and time of the event in `YYYY-MM-DD HHmm`, the
command `/to`, and the end date and time of the event in
`YYYY-MM-DD HHmm` format. Devon will add this to the list.

Example: `event <task description> /from <date and time> /to <date and time>`

Expected output:
```
Got it. I've added this task:
    [E][] <task description> (from: <date and time> to: <date and time>)
Now you have <task count> tasks in the list.
```

## Delete task

To delete a task, simply provide the keyword `delete` followed
by the task number of the event. If an invalid number is provided,
Devon will remind you that it is invalid.

Example: `delete 1`

Expected output:
```
Noted. I've removed this task:
    [T][] <task description>
Now you have <task count> tasks in the list.
```

## View all tasks

To view all tasks, simply provide the keyword `list`, and Devon will
display all tasks you currently have.

Example: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][] <task description>
2. ...
```

## Mark task as done

To mark a task as done, simply provide the keyword `mark` followed
by the task number of the event. If an invalid number is provided,
Devon will remind you that it is invalid.

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
    [T][] <task description>
```

## Mark task as undone (a.k.a unmark task)

To mark a task as undone, simply provide the keyword `unmark`
followed by the task number of the event. If an invalid number
is provided, Devon will remind you that it is invalid.

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
    [T][] <task description>
```

## Shortform commands to create tasks

The full-length keywords to create tasks are `todo`,
`deadline` and `event`. However, the user can use the
keywords `t`, `d` and `e` respectively to achieve the
same results.

Example (todo): `t <task description>`
Example (deadline): `d <task description> /by <date and time>`

Expected output:
Same as the output for
- [Adding todos](#adding-todos)
- [Adding deadlines](#adding-deadlines)
- [Adding events](#adding-events)
