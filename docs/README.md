# Bean User Guide


Bean is the up and coming new task manager that will help you keep track of your tasks. It is a command line 
application that is easy to use and will help you keep track of your tasks.

## Adding deadlines

Be able to add tasks which have deadlines.

Example: `deadline Get milk /by Tomorrow`

A deadline task is added to your list

```
Got it. I've added this task:
  [D][] Get milk (by: Tomorrow)
```

## Add to-dos

Be able to add standard tasks.

Example: `todo Buy groceries`

A deadline task is added to your list

```
Got it. I've added this task:
  [T][] Buy groceries
```


## Add Events

Be able to add tasks which have start and end dates

Example: `event Attend meetings /from 2021-09-01 /to 2021-09-02`

A deadline task is added to your list

```
Got it. I've added this task:
  [E][] Attend meetings (from: 2021-09-01 to: 2021-09-02)
```

## Edit Tasks

Be able to edit Event or Deadline tasks dates

Example: `edit 1 Following day`

A deadline task is added to your list

```
Noted. Task have been updated to the following::
  [D][] Get milk (by: Following day)
```