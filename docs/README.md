# Primo User Guide

![Ui.png](Ui.png)

**El Primo is here!** Introducing a text based chat with yours truly, 
El Primo himself! El Primo will help you to _manage your tasks_, so that you 
will no longer miss deadlines, and be an organised good person :)

> El Primo, is here.


## Add a task
Add a todo task!

`todo (description)`

*Add a deadline task that has a deadline!*

`deadline (description) /by YYYY-MM-DD`

*Add a event task that has a duration!*

`event (description) /from YYYY-MM-DD /to YYYY-MM-DD`

## Mark and unmark tasks
*Mark and unmark tasks as done!*

`mark 2 / unmark 2`

Result:
```
Task PUNCHED! For pain and for glory!
[D][X] (description) (by: MMM dd YYYY)
Note: -
```

## Adding notes
*You can add notes to the tasks, simply by adding /n at the back of the command*

`todo nothing /n Sleep more`
```
New todo task! Grrr
[T][ ] nothing
Note: Sleep more

Now you have 4 tasks to PUNCH!
```

## Delete a task
*You can delete a task by its index*

`delete 4`


## Find a task
*You can find a task by its relevant part or detail*

`find Sleep`

## List everything!
*Get a list of all your tasks!*

`list`