# Dumpling User Guide

![Screenshot of Dumpling](https://yiiilonggg.github.io/ip/Ui.png)

### :dumpling: Dumpling :dumpling:
#### Wrapping all your tasks up

## Adding tasks: `todo`, `deadline`, `event`

There are three different types of tasks Dumpling supports.
1. ToDo
2. Deadline
3. Event

The syntax of adding the different tasks are as follows,
where content is expected between the square brackets:
```
todo [task description]
deadline [task description] /by [yyyy-MM-dd]
event [task description] /from [starting time] /to [ending time]
```

Examples of adding tasks:
* `event project meeting /from 9 Sep. 9pm /to 10pm` adds an event task
* `todo go to the gym` adds a simple todo task

## Adding Notes: `note`

You can add notes to tasks as well!

The syntax of adding notes to a task is as follows:
```
note [1-based index of target task] [task notes]
```

:exclamation: Adding notes to a task will overwrite the existing task notes, if any!

Examples:
* Assuming there is a task at index 1 (1-based indexing), `note 1 previously postponed`
will add a task note `previously postponed` to the task
* Assuming there is a task at index 2 (1-based indexing) that has existing task notes,
`note 2 at school` will overwrite the existing task notes and replace it with `at school`

## Finding Tasks: `find`

Have too many tasks but you only want tasks with a specific substring? Use the `find`
command to quickly search for it!

```
find [substring of target task description]
```

:exclamation: The indices shown by the `find` keyword does not return the indices of the
tasks in the full list, but instead the order in the lists of matching tasks.
For example, if the command `find project` displays the following output:
```
     Waaa! Here are the matching tasks in your list:"
      1.[T][ ] prepare for project meeting (my part is part A)"
      2.[D][ ] project submission (by: Oct 10 2024)"
      3.[E][ ] project meeting (from: today 9pm to: 10pm)"
```
It does not necessarily mean that the task `prepare for project meeting` is also at `index 1`
in the full list of tasks! 
Use the `find` keyword to obtain the full list of tasks.

## Mark and Unmark Tasks: `mark`, `unmark`

Tasks can be marked as done or unmarked as undone, using the following syntax is as follows:
```
mark [1-based index of target task]
unmark [1-based index of target task]
```

## List Tasks: `list`

All existing tasks can be listed, with the following syntax:
```
list
```