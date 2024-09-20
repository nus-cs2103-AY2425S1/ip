# Papagu User Guide


![Ui.png](Ui.png)

Papagu is a chatbot designed to help you manage your tasks. It is operated via a command line interface with a GUI to show you some nice images :D
There are three types of tasks that Papagu can help you manage: todos, deadlines, and events.

## Adding todos
Todos are tasks which have no deadline or time attached to them.
Todos have to be added in the following format:
```
todo <description>
```
eg. `todo cook lunch`

output:
```
Got it. I've added this task:
[T][] cook lunch
```

## Adding deadlines
Deadlines are tasks which have a deadline attached to them.
Deadlines have to be added in the following format:
```
deadline <description> /by <date> <time>
```

eg. `deadline finish project /by 3/10/2024 1800`

output:
```
Got it. I've added this task:
[D][ ] finish project (by: Oct-03-2024 18:00)
```

## Adding events 

Events are tasks which have a start and end time attached to them
Events have to be added in the following format:
```
event <description> /from <date> <time> /to <time>
```

eg. `event F1 Grand Prix /from 22/9/2024 2000 /to 1000`

output:
```
Got it. I've added this task:
[E][ ] F1 Grand Prix (from: Sept-22-2024 20:00 to: 10:00)
```

## Help function

If you are unsure about what functions there are you can key in `help` to get a list of commands


##  Listing tasks
You can view the list of tasks that you have by keying in the command `list`

## Exiting the program
You can exit the program by keying in the command `bye`

## Deleting tasks
You can delete tasks by keying in the command `delete <task number>`

eg. `delete 1`

output: 
```
Noted. I've removed this task:
[T][X] join sports club
```

## Marking tasks as done
You can mark tasks as done by keying in the command `mark <task number>`

## Marking tasks as undone
You can mark tasks as undone by keying in the command `unmark <task number>`

## Finding tasks
You can find tasks by keying in the command `find <keyword>`

eg. `find F1`

output:
```
Here are the matching tasks in your list:
1.[E][X] F1 Grand Prix (from: Sept-22-2024 20:00 to: 10:00)
```
