# Echoa Task Assistant Chat Bot

![Ui.png](Ui.png)

Introducing your new task assistant chatbot, Echoa,
a cutting-edge application designed to simplify your life and boost your productivity.
Leverages on command-line inputs, Echoa helps you manage your tasks using a range of functions!
Whether you’re juggling work deadlines, personal projects, or daily errands, 
this app ensures nothing slips through the cracks.
Experience seamless organization and enhanced focus as you tackle your day with confidence. 
Let’s transform the way you manage your tasks!

## Instruction List
Echoa accepts a range of instruction as below:
- hi
- bye
- todo
- deadline
- event
- update
- mark
- unmark 
- delete
- find
- list

All instructions keyed has to be in lowercase.

## Default Date and Time Inputs

Any date inputs should be inputted as follows:
YYYY-MM-DD
NOTE: Date inputted must be a valid date.

Examples of Invalid Dates:
- 2024-13-01 (Month 13 does not exist)
- 2024-01-33 (Day 33 does not exist)

Any Time inputs should be inputted as follows:
HH:MM
NOTE: Time Inputted must be a valid time.

Examples of Invalid Times:
- 30:00 (Hour 30 does not exist)
- 24:00 (Hour 24 does not exist)
- 12:90 (Minute 90 does not exist)
- 12:60 (Minute 60 does not exist)

## Default Label Inputs
Any label inputs should be inputted as a whole number.

## Hi and Bye

These instructions serve as greetings in which Echoa will respond to.

The format for hi and bye is as follows:
```dtd
hi
bye
```

Any inputs that begins with "hi" and "bye" will be accepted.

Example(s): 
- `hi`
- `hi Echoa`
- `bye`
- `byebye`

Expected output for "hi" instruction:
```dtd
Hello, I'm Echoa!
What can I do for you?
```

Expected out for "bye" instruction:
```dtd
Bye. Hope to see you again soon!
```

## Todo
The todo instruction calls for Echoa to add a ToDo task.
The todo task created will be unmarked by default.
To mark the todo task, please refer to the section on Mark, Unmark and Delete.

The format for adding a todo task is as follows:

```dtd
todo [TASK DESCRIPTION]
```
_Refer to Default Date and Time Inputs for the input format for date and time._
The below inputs are accepted:
- `todo homework`

The below inputs are not accepted:
- `todo` (empty description is not allowed)
- `todo revision / today` (must contain 0 slashes)

Expected output:
```dtd
Task added!
[T][ ] homework
```

## Deadline
The deadline instruction calls for Echoa to add a Deadline task.
The deadline task created will be unmarked by default.
To mark the deadline task, please refer to the section on Mark, Unmark and Delete.
The format for adding a deadline task is as follows:

```dtd
deadline [TASK DESCRIPTION] / [DUE DATE & TIME]
```

_Refer to Default Date and Time Inputs for the input format for date and time._

The below inputs are accepted:
- `deadline submission / 2024-09-18 12:00`
- `deadline submission / 12:00 2024-09-18`
- `deadline submission / by 12:00 2024-09-18`

The below inputs are not accepted:
- `deadline / 2024-09-18 12:00` (empty description is not allowed)
- `deadline submission / 2024-09-18` (empty time is not allowed)
- `deadline submission / 12:00` (empty date is not allowed)
- `deadline submission 2024-09-18 12:00` (0 slashes is not allowed)
- `deadline submission / 2024-09-18 / 12:00` (must contain 1 slash only)

Expected output:
```dtd
Task added!
[D][ ] submission (by: SEPTEMBER 18 2024 12:00)
```


## Event
The deadline instruction calls for Echoa to add a Deadline task.
The deadline task created will be unmarked by default.
To mark the deadline task, please refer to the section on Mark, Unmark and Delete.
The format for adding a event task is as follows:

```dtd
event [TASK DESCRIPTION] / [START DATE & TIME] / [END DATE & DATE]
```

_Refer to Default Date and Time Inputs for the input format for date and time._

The below inputs are accepted:
- `event birthday / 2024-09-18 12:00 / 2024-09-18 18:00`
- `event birthday / 2024-09-18 12:00 / 18:00 2024-09-18`
- `event birthday / from 12:00 2024-09-18 / to 8:00 2024-09-18`

The below inputs are not accepted:
- `event / 2024-09-18 12:00 / 2024-09-18 18:00` (empty description is not allowed)
- `event birthday / 2024-09-18 12:00` (empty start or end details is not allowed)
- `event birthday 2024-09-18 12:00 2024-09-18 18:00` (0 slashes is not allowed)
- `event birthday / 2024-09-18 / 12:00 / 2024-09-18 / 18:00` (must contain 2 slashes only)

Expected output:
```dtd
Task added!
[E][ ] birthday (from: SEPTEMBER 18 2024 12:00 to: SEPTEMBER 18 2024 18:00)
```

## Update
The update instruction calls for Echoa to update any task (todo, deadline or event), based on a specified label.

The format for adding a event task is as follows:

For todo task:
```dtd
update [LABEL] /d [NEW_DESCRIPTION]
```
For deadline task:
```dtd
update [LABEL] /d [NEW_DESCRIPTION] /e [NEW_DUE_DATE_AND/OR_TIME]
```
_Fields are optional_

For event task:
```dtd
update [LABEL] /d [NEW_DESCRIPTION] /s [NEW_START_DATE_AND/OR_TIME] /e [NEW_DUE_DATE_AND/OR_TIME]
```
_Fields are optional_

The below inputs are accepted:
- `update 1 /d cs2103t homework`
- `update 2 /d ip submission /e 2024-09-20 23:59`
- `update 3 /d joey's birthday /s 2024-11-30 00:00 /e 2024-11-30 23:59`
- `update 3 /d joey's birthday` (description only)
- `update 3 /s 2024-11-30 00:00` (start details only)
- `update 3 /e 2024-11-30 23:59` (end details only)
- `update 3 /e 2024-11-30` (date only)
- `update 3 /e 23:00` (time only)

The below inputs are not accepted:
- `update 100` (label does not exist in list)
- `update -1` (negative labels are not allowed)
- `update d` (only numerical values are accepted)
- `update 1 /e 12:00` (only d is accepted for todo)
- `update 2 /s 12:00` (only d and e is accepted for deadline)
- `update 3 /f 12:00` (only d, s and e is accepted for event)

Expected output:
```dtd
Task updated!
[T][X] cs2103t homework
```

## Mark, Unmark and Delete
The mark, unmark and delete instruction calls for Echoa to mark, unmark and delete tasks based on their specified label.

The format for marking, unmarking and deleting tasks is as follows:
```dtd
mark [label]
unmark [label]
delete [label]
```

The below inputs are accepted:
- `mark 1`
- `unmark 2`
- `delete 3`

The below inputs are not accepted:
- `mark 100` (label does not exist in list)
- `unmark -1` (negative labels are not allowed)
- `delete d` (only numerical values are allowed)

Expected output of mark:
```dtd
Task marked :)
[T][X] homework
```

Expected output of unmark:
```dtd
Task unamrked :(
[D][ ] submission (by: SEPTEMBER 18 2024 12:00)
```
Expected output of delete:
```dtd
Task deleted :/
[E][ ] birthday (from: SEPTEMBER 18 2024 12:00 to: SEPTEMBER 18 2024 18:00)
```


## Find
The find instruction calls for Echoa to list all tasks with the specified keyword in its description.
The format for finding tasks with the specified keyword is as follows:
```dtd
find [KEYWORD]
```
The below inputs are accepted:
- `find birthday`
- `find` (lists all tasks)


Expected output:
```dtd
1. [E][ ] birthday (from: SEPTEMBER 18 2024 12:00 to: SEPTEMBER 18 2024 18:00)
```

## List
The list instruction calls for Echoa to list all tasks at hand.
The format for adding a todo task is as follows:

```dtd
list
```

Any inputs that begins with "list" will be accepted.
Example(s):
- `list`
- `list tasks`

Expected output:
```dtd
1. [T][X] homework
2. [D][ ] submission (by: SEPTEMBER 18 2024 12:00)
3. [E][ ] birthday (from: SEPTEMBER 18 2024 12:00 to: SEPTEMBER 18 2024 18:00)
```