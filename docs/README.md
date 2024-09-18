# Delta User Guide

#### Delta ChatBot is an app to assist you with your task management needs!

![image](./Ui.png)

## Adding Todos

You can add a Todo task in your list.

Format: `todo` `[description of task]`

Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

## Adding Deadlines

You can add a Deadline task in your list.

Format: `deadline` `[description of task]` `/by` `[date/time]`

Example: `deadline return book /by 2024-12-12 1800`

```
Got it. I've added this task:
  [D][ ] return book (by: Dec 12 2024 6PM) 
Now you have 2 tasks in the list.
```

## Adding Events

You can add a Event task in your list.

Format: `event` `[description of task]` `/from` `[date/time]` `/to` `[date/time]`

Example: `event project meeting /from 2024-12-12 1400 /to 2024-12-12 1600`

```
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 12 2024 2PM to: Dec 12 2024 4PM) 
Now you have 3 tasks in the list.
```

## Marking Tasks

You can mark a task in your list as done.

Format: `mark` `[index of task]`

Example: `mark 1`

```
Nice! I've marked this task as done!
  [T][X] read book 
```

## Unmarking Tasks

You can unmark a task in your list (i.e. mark the task as not done yet).

Format: `unmark` `[index of task]`

Example: `unmark 2`

```
Nice! I've marked this task as not done yet!
  [D][ ] return book (by: Dec 12 2024 6PM) 
```

## Deleting Tasks

You can delete a task from the list.

Format: `delete` `[index of task]`

Example `delete 1`

```
Noted. I've removed this task:
  [T][X] read book
Now you have 2 tasks in the list.
```

## Finding Tasks

You can find a task in the list using keywords.

Format: `find` `[description of task]`

Example `find book`

```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Dec 12 2024 6PM)
```

## Showing List

You can show your list of tasks.

Format: `list`

Example: `list`

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Dec 12 2024 6PM)
3. [E][ ] project meeting (from: Dec 12 2024 2PM to: Dec 12 2024 4PM)
```

## Editing Tasks

You can edit a task in your list.

Format: `edit` `[index of task]` `[task attribute to edit]` `[new value for attribute]`

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

You can exit the chatbot after you are done. Tasks will be kept in memory and rebooted upon start up.

Format: `bye`

Example: `bye`

```
Bye. Hope to see you again soon!
```