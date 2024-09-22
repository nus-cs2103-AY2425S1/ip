# meowmeow.meowmeow User Guide

## MeowMeow Chatbot 🐱

![screenshot of MeowMeow chatbot](/docs/Ui.png)

MeowMeow is a cute kitty chatbot who is eager to help you keep track of all sorts of tasks!
## Features:

## Adding ToDo tasks

Create a task without any date associated with it.

Example: todo borrow book

MeowMeow will add your todo task to the list of tasks.

```
   meow! :3 i've added this task: <your todo task>
   now you have <total number of tasks> tasks in the list.
```

## Adding Deadline tasks

Create a task which has to be done by a certain date.

Example: deadline submit quiz /by 2024-09-20

MeowMeow will add your deadline task to the list of tasks.

```
   meow! :3 i've added this task: <your deadline task>
   now you have <total number of tasks> tasks in the list.
```

## Adding Event tasks

Create a task which has start date and end date.

Example: event japan trip /from 2024-12-03 /to 2024-12-19

MeowMeow will add your event task to the list of tasks.

```
   meow! :3 i've added this task: <your event task>
   now you have <total number of tasks> tasks in the list.
```

## Adding DoWithin tasks

Create a task which has to be done within a certain period.

Example: doWithin finish math quiz /between 2024-09-16 /to 2024-09-20

MeowMeow will add your event task to the list of tasks.

```
   meow! :3 i've added this task: <your DoWithin task>
   now you have <total number of tasks> tasks in the list.
```

## List all tasks

Shows a list of all tasks currently in the tasklist.

Example: list

MeowMeow will list all tasks that are currently in the tasklist.

```
   here are the tasks in your list:
   1. [T][X] create meowmeow application
   2. [T][ ] borrow book
   3. [D][ ] submit quiz (by: Sep 20 2024)
   4. [E][ ] japan trip (from: Dec 03 2024 to: Dec 19 2024)
   5. [W][ ] finish math quiz (between: Sep 16 2024 and: Sep 20 2024)
```

## Marking a task as done

Marks a task as done to indicate that you have completed the task.
The task is marked via the index it has in the tasklist

Example: mark 3

MeowMeow will mark the task at the specified index as done.

```
   nice!! :3 i've marked this task as done:
   [D][X] submit quiz (by: Sep 20 2024)
```

## Unmarking a task as done

Unmarks a done task to indicate that you have not completed the task
in case you erraneously marked it as done.
The task is unmarked via the index it has in the tasklist

Example: unmark 3

MeowMeow will unmark the task at the specified index as not done.

```
   meow!! i've marked this task as not done yet:
   [D][ ] submit quiz (by: Sep 20 2024)
```

## Find tasks

Finds tasks which have a specified keyword

Example: find quiz

MeowMeow will list all tasks which have the keyword quiz in their task description.

```
   here are the matching tasks in your list:
   1. [D][ ] submit quiz (by: Sep 20 2024)
   2. [W][ ] finish math quiz (between: Sep 16 2024 and: Sep 20 2024)
```

## Delete a task

Deletes a specified tasks.
The task is specified via it's index in the task list.

Example: delete 3

MeowMeow will delete the task which is at the specified index,
removing it from the task list permanently.

```
   Noted. >:( I've removed this task:
   [D][ ] submit quiz (by: Sep 20 2024)
   Now you have 4 tasks in the list.
```

## Exit the application

Ends the conversation with MeowMeow and closes the application.

Example: bye

MeowMeow will say goodbye and close the application.

```
   bye!! hope to see you again soon! :3
```
