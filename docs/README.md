# Gale User Guide

![Gale GUI screenshot](Ui.png)

## Installation
1. Download the jar from ![github releases](https://github.com/kaikquah/ip/releases)
2. Download Java SDK Version 17
3. Open up a command line interface and run the command 
```bash
java -jar gale.jar
```

## Adding a ToDo task: `todo`
Adds a to-do task with a specific description to your task list.

Format:
```
todo [description]
```

Example:
```
todo go to the gym
```
A message with the to-do task just added will be displayed.
```
Whoosh! Task "[T][] go to the gym" added to my windy memory.
Now you have 1 task in the air.
Anything else?
```

## Adding a Deadline task: `deadline`
Adds a deadline task with a specific description and the date and time that the deadline is due to your task list.

Format:
```
deadline [description] /by [d/M/yyyy HH:mm]
```

Example:
```
deadline finish my homework /by 18/9/2024 14:00
```
A message with the deadline task just added will be displayed.
```
Whoosh! Task "[D][] finish my homework (by: Sep 18 2024 14:00)" added to my windy memory.
Now you have 2 tasks in the air.
Anything else?
```

## Adding an Event task: `event`
Adds an event task with a specific description, and the start and end date and time of the event, to your task list.

Format:
```
event [description] /from [d/M/yyyy HH:mm] /to [d/M/yyyy HH:mm]
```

Example:
```
event go to the birthday party /from 20/9/2024 18:00 /to 20/9/2024 22:00
```
A message with the event task just added will be displayed.
```
Whoosh! Task "[E][] go to the birthday party (from: 20 Sep 2024 18:00 to: 20 Sep 2024 22:00)" added to my windy memory.
Now you have 3 tasks in the air.
Anything else?
```

## Listing the tasks in your task list: `list`
Lists all tasks in your task list in the order that you added them.

Format:
```
list
```

Example:
```
list
```
A list of all tasks in your task list will be displayed.
```
You are breezy with these tasks:
 1. [T][] go to the gym
 2. [D][] finish my homework (by: Sep 18 2024 14:00)
 3. [E][] go to the birthday party (from: 20 Sep 2024 18:00 to: 20 Sep 2024 22:00)
```

## Deleting a task from your task list: `delete`
Deletes the specific task specified by the task index number from your task list.

Format:
```
delete [task index number]
```

Example:
```
delete 3
```
A message showing the deletion of the task will be displayed, and the task will be deleted.
```
Poof! The wind has blown away this task:
 [E][] go to the birthday party (from: 20 Sep 2024 18:00 to: 20 Sep 2024 22:00)
Now you have 2 tasks in your windy list.
```

## Marking a task as done: `mark`
Marks the specific task specified by the task index number as done.

Format:
```
mark [task index number]
```

Example:
```
mark 1
```
A message showing the marked task will be displayed.
```
Good work! You breezed through this task!
 [T][] go to the gym
```

## Un-marking a task: `unmark`
Unmarks the specific task specified by the task index number, making it not done.

Format:
```
unmark [task index number]
```

Example:
```
unmark 1
```
A message showing the unmarked task will be displayed.
```
Tough, this task is back in the windy realm.
 [T][] go to the gym
```

## Finding tasks using a keyword: `find`
Finds a list of tasks that contain the specified keyword.

Format:
```
find [keyword]
```

Example:
```
find homework
```
A message showing all the tasks that contain the keyword will be displayed.
```
Whoosh! Here are your matching tasks!
 1. [D][] finish my homework (by: Sep 18 2024 14:00)
```

## Adding a priority to a task: 
Adds a priority (low, medium, high, or none) to a task. This can only be added while declaring new tasks.

Format:
```
[task type] [priority] [description]
```

Example:
```
deadline high finish the canvas quiz /by 25/9/2024 23:59
```
A message containing the task with added priority will be displayed.
```
Whoosh! Task "[D][] finish the canvas quiz (by: 25 Sep 2024 23:59) [high]" added to my windy memory.
Now you have 3 tasks in the air.
Anything else?
```

To declare a task without a specific priority, omit the priority level when you declare the task.
Example:
```
todo wash the dishes
```

## Exiting the task manager: `bye`
Format:
```
bye
```
A goodbye message will be shown by Gale and the application exits.
```
Aw, it's time for you to go huh?
Catch you on the next gust!
```

## Acknowledgements

1. I have referenced the JavaFX tutorial on the course website.