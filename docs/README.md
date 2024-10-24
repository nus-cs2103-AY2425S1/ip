# Talky User Guide

Talky Screenshot
![Talky Screenshot](/Ui.png)
// Product intro goes here

## Adding deadlines
Add deadlines by 

Example: `deadline A /by  19/09/2024 1700`


It'll add the deadline to saved tasks.
```
expected output
Added A
```
```
output with list
Deadline: []A (by: 19-09-2024 17:00)
```

## Feature Help
Shows available commands

Example: `help`

It'll list all the commands with usage and format
```
expected output
Here are the available commands

To show all tasks: list

To add ToDo: todo [task name]

To add Deadline: event [task name] /by [date] [time]

To add Event: event [task name] /from [date] [time] /to [date] [time]

To delete task: delete [task index]

To find keyword in task: find [keyword]

To mark task use: mark [task index]

To unmark task use: mark [task index]
```
