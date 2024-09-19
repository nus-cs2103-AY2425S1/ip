# Bwead User Guide

![Ui](https://github.com/user-attachments/assets/cdb08fb4-0196-4223-84c1-b2d1a7e566a8)

Bwead is a chatbox which helps you keep track of your Todos, Deadlines, and Events!

## Adding Todos

Adds a todo task.

Example: `todo return book`

expected output:
```
Got it. I've added this task:[T][] return book
Now you have 4 tasks in the list.
```

## Adding deadlines

Adds a deadline task with a date and time.

Example: `deadline return book /by 2020-10-01 1900`

expected output:
```
Got it. I've added this task:[D][] return book (by: Oct 1 2020, 19:00)
Now you have 4 tasks in the list.
```
## Adding Events

Adds an event task with a starting and ending date and time.

Example: `event lend book /from 2020-10-01 1900 /to 2021-10-01 1800`

expected output:
```
Got it. I've added this task:[E][] lend book (from: Oct 1 2020, 19:00 to: Oct 1 2021, 18.00)
Now you have 4 tasks in the list.
```

## Deleting tasks

Deletes a task from the list according to its index on the list.

Example: `delete 2`

(the second task will be deleted from the list)

expected output: 
```
Noted. I've removed this task:[D][] return book (by: Oct 1 2020, 19:00)
Now you have 3 tasks in the list.
```

## Listing tasks

Lists all current tasks

Example: `list`

expected output: 
```
1.[E][] lend book (from: Oct 10 2020, 10.00 to: Mar 3 2021, 10.00)
2.[D][] return book (by: Dec 16 2020, 19.00)
3.[T][] go class
```

## Marking / unmarking tasks

Marks tasks as done / undone according to their index in the task list.

Example: `mark 2`

expected output: 
```
Nice! I've marked this task as done: go class
```

## Snoozing

reschedules the event dates and times 
please include the task name and all final dates and times even if youre only changing one part (eg. only the time/ only for the from date)
note: this is only available for deadlines and events!

Example: `snooze event lend book from 2020-10-10 1000 to 2021-03-03 1000`

expected output: 
```
event task lend book 's date and time is updated!
```
Example: `snooze deadline return book by 2020-10-10 1000`

expected output: 
```
event task return book 's date and time is updated!
```

## Exiting

closes the chatbox.

Example: `bye`

expected output: 
```
Bye.Hope to see you again soon!
```
