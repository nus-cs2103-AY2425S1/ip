# Tesla User Guide

![Ui.png](Ui.png)

>    “A little planning goes a long way.” – Anonymous

Ah, my dear friends, let Tesla be your guiding light, releasing your intellect from the
tiresome encumbrance of memorizing every tasks that demand your attention.
This remarkable tool is:
- elegantly text-based, allowing for seamless communication with your thoughts
- astonishingly simple to grasp, requiring but a flicker of your mind’s energy to master
- operates at a level of rapidity that you may dare to call INCREDIBLE

Embrace this invention, and free your intellect to ponder the wonders of the universe!

## Quick Start

To embark upon your journey with Tesla, heed these brief instructions:

1. Ensure that Java 17 or higher is present on your machine.
2. Acquire the latest .jar file from [here]().
3. Transfer this file to the folder you shall designate as the realm (folder) for your Tesla chatbot. 
4. Invoke your command terminal, navigate to this folder, and execute the command java -jar tesla.jar.

In mere moments, a graphical interface replete with sample data shall manifest before you.

And it is **FREE**!

Features:

- [x] Managing tasks
- [x] Managing deadlines
- [x] Managing events 


## Adding Todos

Adds tasks that are without any deadlines or time periods, and are thus generic by nature.

Format: `todo <task description>`

Example:

`todo go Sungei Buloh for bird watching sometime`

The chatbot notifies the user when the task to be done is successfully added to the list and specifies the total number of current tasks.

```
Got it. I've added this task:
[T][ ] go Sungei Buloh for bird watching sometime
Now you have 14 tasks in the list.
```

## Adding deadlines

Adds a deadline to the chatbot, to be viewed later when needed.

Format: `deadline <task description> /by <yyyy-mm-dd>`

Example:

`deadline Finish software architecture project /by 2024-11-23`

The chatbot notifies the user when the deadline is successfully added to the list and specifies the total number of current tasks.

```
Got it. I've added this task:
[D][ ] Finish software architecture project (by: Nov 23 2024)
Now you have 14 tasks in the list.
```

## Adding Events

Adds tasks that have a specific period that they occur in i.e. events.

Format: `event <task description> /from <start day/date and time> /to <start day/date and time>`

Example:

`event project meeting /from Mon 2pm /to Mon 4pm`

The chatbot notifies the user when the event is successfully added to the list and specifies the total number of current tasks.

```
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: Mon 4pm)
Now you have 14 tasks in the list.
```

## Deleting Tasks

Deletes tasks from the list of tasks currently in the program.

Format: `delete <index>`

Example:

`delete 14`

The chatbot notifies the user if the event is successfully deleted and specifies the total number of current tasks.

```
Got it. I've deleted this task:
[T][ ] go Sungei Buloh for bird watching sometime
Now you have 13 tasks in the list.
```

## Marking tasks

Marks the specified task from the list of tasks currently in the program.

Format: `mark <index>`

Example:

`mark 14`

The chatbot notifies the user if the task is successfully marked as done in the system.

```
Nice! I've marked this task as done:
[D][X] Finish feature specification (by: Sep 22 2024)
```

## Unmarking tasks

Unmarks the specified task from the list of tasks currently in the program.

Format: `unmark <index>`

Example:

`unmark 14`

The chatbot notifies the user if the task is successfully unmarked in the system.

```
OK, I've marked this task as not done yet:
[D][ ] Finish feature specification (by: Sep 22 2024)
```

## Finding tasks

Finds tasks that match the keywords given in the command. Note that the functions finds words individually,
rather than searching for the whole string.

Format: `find <keyword>`

Example: `find book`

The chatbot finds all existing entries that contain the word 'book' and displays them to the user.

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Jun 6 2012)
3.[T][X] read book again
4.[T][ ] borrow book
```

## Snoozing deadlines

Lets the user delay a task with a deadline to a new date.

Format: `snooze <index> /by <date as yyyy-mm-dd>`

Example: `snooze 2 /by 2012-06-07`

The chatbot notifies the user if the deadline is successfully snoozed in the system and specifies the total number of
current tasks.

```
Got it. I've snoozed this task:
[D][ ] return book (by: Jun 7 2012)
Now you have 13 tasks in the list.
```

