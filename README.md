# Timo User Guide

Welcome to Timo, your personal assistant bot! Timo is designed with the intention
to aid you in **managing** daily tasks.
#####
- Have an interview to take note?
#####
- Have an event coming up?
#####
- Have a deadline that you must remember to hit?
#####
Use **Timo** to take note of them! With features like add, delete, mark, unmark, undo etc...
### let Timo change your life!

#


## Adding todo

Adds a todo task to your task list

#### prints added to list, as well as size of task list, otherwise returns error
####

``` 
### How to use

todo <task>
```

Example: `todo return book`

//Example output
```
-------------------
Got it. I've added this task:
[T][ ] return book
You now have 2 tasks in your list
-------------------
```

## Adding deadline

Adds a deadline task to your task list


#### prints added to list, as well as size of task list, otherwise returns error
####

``` 
### How to use

deadline <task> /by <date and time>
(for date and time, follow format yyyy-MM-dd HHmm)
```

Example: `deadline return book /by 2020-12-12 1300`

//Example output
```
-------------------
Got it. I've added this task:
[D][ ] return book (by Dec 12 2020 1300)
You now have 2 tasks in your list
-------------------
```


## Adding event

Adds an event task to your task list


#### prints added to list, as well as size of task list, otherwise returns error
####

``` 
### How to use

event <task> /from <date and time> /to <date and time>
(for date and time, follow format yyyy-MM-dd HHmm)
```

Example: `event CTF /from 2020-12-12 1300 /to 2021-01-01 1400`

//Example output
```
-------------------
Got it. I've added this task:
[E][ ] CTF (from Dec 12 2020 1300 to: Jan 01 2021 1400)
You now have 3 tasks in your list
-------------------
```

## Show task list

Displays all the tasks in your task list

#### prints out the task in task list, and error if used wrongly
####

``` 
### How to use

list
```

Example: `list`

//Example output
```
-------------------
Here are the tasks in your list:
1. [T][ ] return book
-------------------
```

## Marking a task

Marks a task(todo/deadline/event) in your task list


#### prints that task has been marked, and error if number is negative or does not exist
####

``` 
### How to use

mark <number>
```

Example: `mark 1 (say 1 is a todo task)`

//Example output
```
-------------------
Nice! I've marked this task as done:
[T][X] return book
-------------------
```

## Unmarking a task

Unmarks a task(todo/deadline/event) in your task list


#### prints that task has been unmarked, and error if number is negative or does not exist
####

``` 
### How to use

unmark <number>
```

Example: `unmark 1 (say 1 is a todo task)`

//Example output
```
-------------------
Nice! I've marked this task as undone:
[T][ ] return book
-------------------
```


## Deleting a task

Deletes a task(todo/deadline/event) in your task list


#### prints that task has been removed, as well as the number of task in your list. If invalid input, prints out error
####

``` 
### How to use

delete <number>
```

Example: `delete 1 (say 1 is a todo task)`

//Example output
```
-------------------
Got it. I've removed this task:
[T][ ] return book
You now have 0 tasks in your list
-------------------
```

## Undo previous command

Undo your previous command

#### prints the command to undo, undo the command and print output, if invalid input prints error
####

``` 
### How to use

undo
```

Example: `undo`

//Example output
```
-------------------
undo command: todo return book
-------------------
Got it. I've removed this task:
[T][ ] return book
You now have 0 tasks in your list
-------------------
```

## find tasks containng specified phrases

helps you to find tasks that contains the phrases you specified

#### prints out the tasks which contain the phrase you specified, if invalid usage will print out error
####

``` 
### How to use

find <phrase>
```

Example: `find return`

//Example output
```
-------------------
Here are the tasks in your list:
1. [T][ ] return book
2. [T][ ] return earring
3. [T][ ] return pen
-------------------
```

## exiting the program

saves the program and exits

#### saves task list inside list.txt file, greets and exit program, if invalid usage will print out error
####

``` 
### How to use

bye
```

Example: `bye`

//Example output
```
-------------------
Bye! Hope to see you again soon!
-------------------
```

## Have fun using the application!
