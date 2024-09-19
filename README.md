# Maxine User Guide


![A screenshot of MaxineBot]([Ui.png](https://github.com/gabriellegtw/ip/blob/master/docs/Ui.png?raw=true))


This is Maxine, your personal chatbot assistant to help you keep track of your tasks!
Using Maxine, you can add different types of tasks (todo tasks, deadline tasks and event tasks).
You can also choose to mark (or unmark) your tasks to indicate your tasks completion status.
Maxine remembers your tasks even from the previous session.

## Downloading the JAR file

To use Maxine:

1. Download the JAR file
2. In your terminal, navigate to the directory containing the JAR file
3. type in the command "java -jar "maxine.jar""

NOTE: When you first use Maxine, it is expected that the command line
may print some error messages in the command line
(ie. "Oh no! I can't seem to find the file :(" will print twice).
However, this can be ignored as Maxine is just creating a maxine.txt file to
store data.

## Adding Tasks with no deadlines

To add a "Todo" task (ie. a task with no deadline), use the command:

todo [task] (eg. "todo homework")

## Adding deadlines

To add a "Deadline" task (ie. a task with a deadline), use the command:

deadline [task] /by [deadline] (eg. "deadline homework /by 20-09-2024")

It is also possible to put a date as a deadline that does not follow the normal format of a date.

eg. you can use the command "deadline homework /by tomorrow"

## Adding events

To add an "Event" task (ie. a task with a start and end date), use the command:

event [task] /from [start time] /to [end time] (eg. "event discussion /from today /to tomorrow")

Similar to the deadline task, you can use both a string which follows date format (or not)
to fill in the start and end times of your event

## View list

Want to refer back to your task list? 
No worries! Just type in the command "list" (without the inverted commas) to view your tasks

## Mark your tasks when they are completed

To mark you tasks as done, you can put in the command:

"mark [number]", where the number corresponds to the number on of the task on the task list
(eg. "mark 1" to mark the first task on the list)

## Unmark your tasks

Accidentally marked a task as complete?

Don't fret! Similar to marking a task, you can unmark a task by typing the command "unmark [number]"

## Deleting a task

If you would like to delete a task by using the command:

"delete [number]", where the number corresponds to the task number in the task list.

## Clearing the list (Extension BCD)

To delete everything in the list, use the command: "delete all"

## Finding specific tasks in your list

If your list is too long and you would like to search for a specific task, just use the command:

"find [keyword]", where keyword is the name of the task you would like to find
