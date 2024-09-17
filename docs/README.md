# BitBot User Guide

## Product Overview

![Image of BitBot GUI][./docs/Ui.png]

BitBot is a task management application designed to help the users to 
organise the tasks and keep track of the remaining tasks that needs to be done.
There are three main tasks that can be added. 

1. Todo
   - Add a simple task with only a description.
2. Deadline
   - Add a task with a description with a deadline by.
3. Event
   - Add a task with a description with a starting time and an ending time.

_Good news!!! The deadlines can be written in three formats as well which boosts usability._
- Date and time format (e.g. 21-09-2024 18:00)
- Date format (e.g. 21-09-2024)
- Time format (e.g. 18:00)

Once these tasks have been created, the user can use one of the following to interact with the application bot.
- mark
- unmark
- delete
- find
- tag
- untag
- list
- bye

Enjoy using the application bot and hope BitBot makes your ever-increasing workload
and to-do lists easier to manage and control so that you can take a step back
and worry less and enjoy life!
## Adding deadlines

You can add a deadline to your task list by typing the following command:

Example: `deadline return book /by 21-09-2024 18:00`

The command will add a deadline task with the specified description and the due date and time.
```
________________________________
Okay, got it. I've added this task:
   [D][] return book (by: Sep 21 2024 18:00)
Now you have 1 task in the list.
________________________________
```

## Tagging feature

BitBot allows the users to tag their tasks respectively. Albeit filtering and other commands are not implemented yet,
tagging allows the users to identify the tasks they would like to tag.

Example: `tag 1 #URGENT`

The command will tag the corresponding task that is mentioned as the index.
```
________________________________
Nice! I've tagged this task:
   [D][#URGENT][] return book (by: Sep 21 2024 18:00)
with #URGENT
________________________________
```