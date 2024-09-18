# Delta User Guide

#### Delta ChatBot is an app to assist you with your task management needs!

- [Showing List](#showing-list)
- [Adding Todos](#adding-todos)
- [Adding Deadlines](#adding-deadlines)
- [Adding Events](#adding-events)
- [Marking Tasks](#marking-tasks)
- [Unmarking Tasks](#unmarking-tasks)
- [Deleting Tasks](#deleting-tasks)
- [Finding Tasks](#finding-tasks)
- [Editing Tasks](#editing-tasks)
- [Exiting Delta ChatBot](#exiting-delta-chatbot)

![image](./Ui.png)

## Showing List

Shows your list of tasks.

Format: `list`

Example: `list`

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Dec 12 2024 6PM)
3. [E][ ] project meeting (from: Dec 12 2024 2PM to: Dec 12 2024 4PM)
```

## Adding Todos

Adds a Todo task in your list.

Format: `todo DESCRIPTION`

Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

## Adding Deadlines

Adds a Deadline task in your list.

Format: `deadline DESCRIPTION /by DATE/TIME`

Example: `deadline return book /by 2024-12-12 1800`

```
Got it. I've added this task:
  [D][ ] return book (by: Dec 12 2024 6PM) 
Now you have 2 tasks in the list.
```

## Adding Events

Adds a Event task in your list.

Format: `event DESCRIPTION /from DATE/TIME /to DATE/TIME`

Example: `event project meeting /from 2024-12-12 1400 /to 2024-12-12 1600`

```
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 12 2024 2PM to: Dec 12 2024 4PM) 
Now you have 3 tasks in the list.
```

## Marking Tasks

Marks a task in your list as done.

Format: `mark INDEX_OF_TASK`

Example: `mark 1`

```
Nice! I've marked this task as done!
  [T][X] read book 
```

## Unmarking Tasks

Unmarks a task in your list (i.e. mark the task as not done yet).

Format: `unmark INDEX_OF_TASK`

Example: `unmark 2`

```
Nice! I've marked this task as not done yet!
  [D][ ] return book (by: Dec 12 2024 6PM) 
```

## Deleting Tasks

Deletes a task from the list.

Format: `delete INDEX_OF_TASK`

Example `delete 1`

```
Noted. I've removed this task:
  [T][X] read book
Now you have 2 tasks in the list.
```

## Finding Tasks

Finds a task in the list using keywords.

Format: `find DESCRIPTION`

Example `find book`

```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Dec 12 2024 6PM)
```

## Editing Tasks

Edits a task in your list.

Format: `edit INDEX_OF_TASK TASK_ATTRIBUTE NEW_VALUE`

Possible task attributes for each task:
- Todo: `/desc`
- Deadline: `/desc` `/by`
- Event: `/desc` `/from` `/to`

Example: `edit 1 /desc read 2 books`

```
Nice! I've edited this task:
  [T][X] read 2 books
```

Example: `edit 3 /from 2024-12-12 1300`

```
Nice! I've edited this task:
  [E][ ] project meeting (from: Dec 12 2024 1PM to: Dec 12 2024 4PM)
```

## Exiting Delta ChatBot

Exits the chatbot after you are done. Tasks will be kept in memory and rebooted upon start up.

Format: `bye`

Example: `bye`

```
Bye. Hope to see you again soon!
```