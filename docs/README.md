# Noosy User Guide

![Screenshot 2024-09-23 at 12.01.56 PM.png](..%2F..%2F..%2FDocuments%2FScreenshot%202024-09-23%20at%2012.01.56%E2%80%AFPM.png)

Welcome to Noosy, your simple and intuitive task manager. 
Noosy helps you manage todos, deadlines, and events efficiently, 
keeping your tasks organized. 
This guide will walk you through the various features of Noosy, 
providing examples for each.

## Adding todos
To add a `todo` (without deadline or duration),
simply use the todo command followed by the task description.

Command:
```
todo <task description> 
```

Example:
```
todo Submit project meeting minutes
```

Expected output:
```
I added it to the list!
  [T][ ] Submit project meeting minutes
We've now got X tasks!
```

## Adding deadlines

To add a `deadline`,
simply use the deadline command followed by the task description and the deadline date.

Command:
```
deadline <task description> /by <yyyy-mm-dd>
```

Example:
```
deadline Submit project report /by 2024-10-01
```

Expected output:
```
I added it to the list!
  [D][ ] Submit project report (by: Oct 1 2024)
We've now got X tasks!
```

## Adding events

To add an `event`, 
simply use the event command followed by the task description 
and the start and end timings.

Command:
```
event [task description] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]
```

Example:
```
event team meeting /from 2024-09-25 0900 /to 2024-09-25 1100
```

Expected output:
```
I added it to the list!
  [E][ ] team meeting (from: Sep 25 2024 09:00 to: Sep 25 2024 11:00)
We've now got X tasks!
```

## Listing Tasks


To view all your tasks, use the `list` command.

Expected output:
```
Heyo, here are the tasks we have:
1. [T][ ] read book
2. [D][ ] submit report (by: Sep 25 2024)
3. [E][ ] team meeting (from: Sep 25 2024 09:00 to: Sep 25 2024 11:00)
```


## Marking a Task as Done

The `mark` command allows you to mark a task as completed.

Command:
```
mark [task number]
```

Example: 
```
mark 1
```

Expected Output:
```
Hooray you've done this:
  [T][X] read book
```

## Unmarking a Task

To unmark a task (i.e., mark it as incomplete), use the `unmark` command.

Command:
```
unmark [task number]
```

Example:
```
unmark 1
```

Expected Output:
```
Ok don't worry, you can continue working on this:
  [T][ ] read book
```

## Deleting a Task

If you need to remove a task, 
the `delete` command helps you delete it from your list.

Command:
```
delete [task number]
```

Example:
```
delete 2
```

Expected Output:
```
I've kicked it out for you:
  [D][ ] submit report (by: Sep 25 2024)
```

## Finding Tasks

You can search for tasks that contain a specific keyword 
using the `find` command.


Command:
```
find [keyword]
```

Example:
```
find report
```

Expected Output:
```
Ahh, I've found the following for you!
1. [D][ ] submit report (by: Sep 25 2024)
```

## Searching Tasks by Date
To find all tasks occurring on a specific date, use the `on` command.

Command:
```
on [YYYY-MM-DD]
```

Example:
```
on 2024-09-25
```

Expected Output:
```
Here are the tasks on 2024-09-25:
1. [D][ ] submit report (by: Sep 25 2024)
2. [E][ ] team meeting (from: Sep 25 2024 09:00 to: Sep 25 2024 11:00)
```

## Exiting Noosy
To exit the application, type `bye`.

Command:
```
bye
```

## Error Handling
Noosy provides helpful error messages if your input is incorrect or incomplete. For example:

If you forget to specify a task description or deadline.

If you provide an invalid date format.

Example error:
```
I think you forgot the deadline. 
Remember to type in the task and /by the deadline!
```


