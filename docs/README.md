# meerkatbot User Guide

![my ui](/docs/Ui.png)

This is meeerkatbot! It can log tasks for you, display what you have on your tasklist,
and you can remove them as and when you like! 

## Quick Start
1. Ensure that you have Java 17 or above installed on your device. 
2. Download the jar file here 
3. Run the jar file straight away, or from the terminal by typing
```
java -jar meerkatbot.jar
```

## Greeting
Meerkatbot loves saying hello to you! Whenever you click on anywhere on the app,
meerkat will say hi to you! Just don't click too many times....

## Creating Todo Tasks

The bot can create and log todo tasks. Todo tasks are the most basic of tasks,
it represents things you intend to do. After the task has been created, you can also
choose to mark it as done, or unmark it, or even delete the task if it's not necessary
anymore.

Here are some examples of how to utilise this function

Example: `todo buy groceries`

The tasklist will be updated with the task of type "todo" as denoted by [T], and it will be placed at 
the end of the tasklist at the last index.
```
expected output 
__________________________________________________
Got it. I've added this task:
[T][ ] buy groceries
Now you have 1 task in the list
__________________________________________________
```

## Creating Deadline Tasks

Apart from basic todo tasks, the bot can also create and log deadline tasks. Deadline tasks represents
the things you intend to do by a certain deadline. The CLI accepts a duedate in a 
YYYY-MM-DD 24hr format, and parses that into a LocalDateTime. After the task has been created,
you can also choose to mark it as done, or unmark it, or even delete the task if it's not necessary
anymore. The task will be displayed in a different date and time format, of MMM DD YYYY 12hr(am/pm).

Here are some examples of how to utilise this function

Example: `deadline study for lecture /by 2024-09-29 1900`

The tasklist will be updated with the task of type "deadline" as denoted by [D], and it will be placed at
the end of the tasklist at the last index.
```
expected output 
__________________________________________________
Got it. I've added this task:
[D][ ] study for lecture (by: Sep 29 2024 07:00pm)
Now you have 2 tasks in the list
__________________________________________________
```

## Creating Event Tasks

Apart from todo and deadline tasks, the bot can also create and log event tasks. Event tasks represent
the activities that will span a duration. The CLI accepts an event name, the start as well as end period.
After the task has been created, you can also choose to mark it as done, or unmark it, or even delete
the task if it's not necessary anymore.

Here are some examples of how to utilise this function

Example: `event go JB /from tomorrow /to next fri`

The tasklist will be updated with the task of type "event" as denoted by [E], and it will be placed at
the end of the tasklist at the last index.
```
expected output 
__________________________________________________
Got it. I've added this task:
[E][ ] go JB (from: tomorrow to: next fri)
Now you have 3 tasks in the list
__________________________________________________
```

## Listing your tasks

By using the "list" keyword, you can instruct the chatbot to display the list of all the tasks
that have been input so far, across the 3 different types of tasks

Here are some examples of how to utilise this function

Example: `list`

The tasklist will be displayed in chronological order of its input.
```
expected output 
__________________________________________________
Here are the tasks in your list
1. [T][ ] buy groceries
2. [D][ ] study for lecture (by: Sep 29 2024 07:00pm)
3. [E][ ] go JB (from: tomorrow to: next fri)
__________________________________________________
```

## Marking a task

By using the "mark" keyword, you can instruct the chatbot to mark a task as done, the same instruction
for all 3 types of task. The task is specified using its index in the tasklist.

Here are some examples of how to utilise this function

Example: `mark 1`

The changed task will be reflected in the output.
```
expected output 
__________________________________________________
Nice! I've marked this task as done:
[T][X] buy groceries
__________________________________________________
```

## Unmarking a task

Similar to the mark function, by using the "unmark" keyword, you can instruct the chatbot to mark a task
as done, the same instruction for all 3 types of task. The task is specified using its index in the tasklist.

Here are some examples of how to utilise this function

Example: `unmark 1`

The changed task will be reflected in the output.
```
expected output 
__________________________________________________
OK, I've marked this task as not done yet:
[T][ ] buy groceries
__________________________________________________
```

## Deleting a task

Like other list edit commands, by using the "delete" keyword, you can instruct the
chatbot to delete a task from the tasklist, the same instruction for all 3 types of
task. The task is specified using its index in the tasklist.

Here are some examples of how to utilise this function

Example: `delete 1`

The deleted task will be reflected in the output.
```
expected output 
__________________________________________________
roger that sir, I've removed this task:
[T][ ] buy groceries
__________________________________________________
```

## Finding a task

Like other list commands, by using the "find" keyword, you can instruct the
chatbot to search for a task in the tasklist, based on the search parameters received.
Any tasks with names that contain that keyword will be displayed in a list.

Here are some examples of how to utilise this function

Example: `find lecture`

The matching tasks will be reflected in the output.
```
expected output 
__________________________________________________
Here are the matching tasks in your list:
1. [D][ ] study for lecture (by: Sep 29 2024 07:00pm)
__________________________________________________
```

## Detecting Duplicates in TaskList

When creating a task to be added to the tasklist, the chatbot will inform you if there
is already an identical task present in the list. Tasks are identical if:
- it shares the same name (todo task)
- it shares the same name and due date (deadline task)
- it shares the same name, start and end period (event task)

Here are some examples of how to utilise this function

Example: `event go JB /from tommorrow /to next fri`

The chatbot will reject the creation of the identical task.
```
expected output 
__________________________________________________
stop trolling, the task is already present
__________________________________________________
```

## Bye bye!
When you're done with your managing of the tasklist, just type bye to let
meerkat know you're done! And the application will close.

Example: `bye`
```
expected output
__________________________________________________
bye bye! was nice meeting you!
__________________________________________________
```