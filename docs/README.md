# Megamind User Guide
![Ui.png](Ui.png)

Megamind is a task tracker that hopes to improve your productivity

## Viewing help : help
Shows a message displaying all command
<br/>
Format: `help`

## Listing tasks : list
Shows a message displaying all tasks
<br/>
Format: `list`


## Adding a todo task: todo
Adds a todo task to the list including a description
<br/>

Format: `todo <description>`
<br/>

Example: `todo Revise for CS2100`
<br/>

```
Got it. I've added this task:
[T][ ] Revise for CS2100
```


## Adding deadlines: deadline
Adds a deadline task to the list including a description
<br/>

Format: `deadline <description> /by <time>`
<br/>

Example: `deadline Revise for CS2100 /by 31/09/2024 2359`
<br/>

```
Got it. I've added this task:
[D][ ] Revise for CS2100 (BY: 31 Sep 2024, 11:59:00 pm)
```

## Deleting tasks: delete
Deletes a task based on its task number
<br/>

Format: `delete <task number>`
<br/>

Example: `delete 1`
<br/>

```
Task has been deleted sucessfully:
[T][ ] Revise for CS2100
```

## Exiting Megamind : bye
Saves all changes and closes GUI
<br/>
Format: `bye`