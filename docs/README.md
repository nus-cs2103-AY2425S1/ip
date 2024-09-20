# Bob User Guide

// Product screenshot goes here


Welcome to Bob, your personal assistant to stay on top of all your tasks and assignments.

## Adding task

You can add 3 different types of task to your to do list with each task having its own format and purpose.

### ToDo `todo`
A simple task that has no start or end deadline but you want to track it anyways

**Format**<br>
`todo <TASK>`

**Example**<br>
`todo Read Harry Potter`

### Deadline `deadline`
A task which has a deadline you want to finish by

**Format**<br>
`deadline <TASK> /by DD/MM/YYYY HHMM` or<br>
`deadline <TASK> /by DD/MM/YYYY`

**Example**<br>
`deadline Return book /by 23/09/2024 1800`<br>
`deadline Return book /by 23/09/2024`

### Event `event`
An event that occurs over a stipulated period of time (usually with a start date and end date)

**Format**<br>
`event <TASK> /from DD/MM/YYYY HHMM /to /DD/MM/YYYY HHMM` or<br>
`event <TASK> /from DD/MM/YYYY HHMM /to /DD/MM/YYYY` or<br>
`event <TASK> /from DD/MM/YYYY /to /DD/MM/YYYY HHMM` or<br>
`event <TASK> /from DD/MM/YYYY /to /DD/MM/YYYY`

**Example**<br>
`event Attend Meeting /from 25/09/2024 1400 /to 25/09/2024 1600`<br>
`event Nights out with da bois /from 25/09/2024 1800 /to 25/09/2024`<br>
`event Some funny event /from 25/09/2024 /to 25/09/2024 1900`<br>
`event Family Visit /from 25/09/2024 /to 25/09/2024`<br>

## List tasks `list`
List all the tasks that you have

**Format**<br>
`list`

## Mark Task `mark`
Mark your completed tasks so that you can keep track of your progress

**Format**<br>
`mark <TASK_NUMBER>`

**Example**<br>
`mark 1`

## Unmark Task `unmark`
Accidentally marked a task? or you thought you completed a task but in reality you didn't.
Worry not just go and unmark it

**Format**<br>
`unmark <TASK_NUMBER>`

**Example**<br>
`unmark 1`

## Find Task `find`
Filter your tasks based on given keyword(s)

**Format**<br>
`find <KEYWORD>`

**Example**<br>
`find book`

## Delete Task `delete`
Delete a task that you don't need

**Format**<br>
`delete <TASK_NUMBER>`

**Example**<br>
`delete 1`

## Saved Data
Data is saved after every command so freight not