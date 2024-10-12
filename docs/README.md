# Julie User Guide

![A screenshot of the Julie Chatbot in use.](../docs/Ui.png)

_Hi! I'm Julie! A personal Chatbot who can 
help you keep track of your tasks!_

## Adding Tasks
Julie supports 3 types of tasks:

### Todos: Tasks with no time constraints.

Example: `todo taskName`

```
Nice! I've added it to the list! 
T[ ] taskName
```

### Deadlines: Tasks with a deadline.

Example: `deadline taskName /by yyyy-mm-dd`

```
Nice! I've added it to the list! 
D[ ] taskName (by: mmm dd yy)
```

### Events: Tasks with a start and end time.

Example: `event taskName /from yyyy-mm-dd 
/to yyyy-mm-dd`

```
Nice! I've added it to the list! 
D[ ] taskName (from: mmm dd yy to: mmm dd yy)
```

## Marking Tasks:
A task can either be marked as completed, or incomplete.

### Marking Completion:
Example: `mark taskNumber`

```
Ooh, this task is done!
T[X] taskName //the task that corresponds with the number
```

### Un-marking Completion:
Example: `unmark taskNumber`

```
Oop, this task is not yet done
T[ ] taskName //the task that corresponds with the number
```

## List
`list` will return the list of tasks currently recorded by Julie

## Delete
`delete taskNumber` will delete the corresponding task

## Tag
`tag taskNumber tagString` will add a Tag with the tagString to the corresponding task.

## Find
`find searchString` will return a list of all tasks with the string in the name.