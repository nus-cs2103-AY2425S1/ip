# Bob User Guide

Bob is a desktop chatBot that allows users to manage their tasks effectively.

This is simple tutorial guide on how to use it.

## Adding a Deadline Task

Add a deadline task to your task list.

Syntax:

```
deadline <task_description> /by <due_date>
```

Example: `deadline shopping trip /by 25/12/2024`

Expected output:
```
 Got it. I've added this task:
        [D][ ] shopping trip (by: 25 Dec 2024)
        Now you have 1 tasks in the list.
```

## Adding a Todo Task

Add a todo task to your task list.

Syntax:

```
todo <task_description> 
```

Example: `todo run`

Expected output:
```
 Got it. I've added this task:
        [T][ ] run
        Now you have 2 tasks in the list.

```

## Adding Event Task

Add an event task to your task list.

Syntax:

```
event <task_description> /from <day> <start_time> /by <end_time>
```

Example: `event birthday /from Mon 7pm /to 10pm`

Expected output:
```
 Got it. I've added this task:
        [E][ ] birthday (from: Mon 7pm to: 10pm)
        Now you have 3 tasks in the list.
```

## Listing all Tasks

List all tasks in the task list.

Syntax:

```
list
```

Example: `list`

Expected output:
```
Here are the tasks in your list:
        1.[D][ ] shopping trip (by: 25 Dec 2024)
        2.[T][ ] run
        3.[E][ ] birthday (from: Mon 7pm to: 10pm)
```

## Marking a Task

Marking a task in the task list a completed or not completed.

Syntax:

```
mark <index of task in task list>
```

```
unmark <index of task in task list>
```

Example: `mark 1`

Expected output:
```
 OK, I've marked this task as done:
        [D][X] shopping trip
```

Example: `mark 2`

Expected output:
```
 OK, I've marked this task as done:
        [T][X] run
```

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
        [D][ ] shopping trip
```

list command will display the following:

```
Here are the tasks in your list:
        1.[D][ ] shopping trip (by: 25 Dec 2024)
        2.[T][X] run
        3.[E][ ] birthday (from: Mon 7pm to: 10pm)

```

## Tagging a Task

Tagging a task in the task list with a hashtag, or untagging it.

Syntax:

```
tag <index of task in task list> <hashtag>
```
```
untag <index of task in task list>
```

Example: `tag 1 family`

Expected output:
```
Got it. I've tagged this task:
        [D][ ][#family] shopping trip (by: 25 Dec 2024)
```

Example: `tag 3 bestie`

Expected output:
```
Got it. I've tagged this task:
       [E][ ][#bestie] birthday (from: Mon 7pm to: 10pm)
```

Example: `untag 1`

Expected output:
```
Got it. I've untagged this task:
        [D][ ] shopping trip (by: 25 Dec 2024)
```

list command will display the following:

```
 Here are the tasks in your list:
        1.[D][ ] shopping trip (by: 25 Dec 2024)
        2.[T][X] run
        3.[E][ ][#bestie] birthday (from: Mon 7pm to: 10pm)
```

## Find all Tasks

Search for tasks with description matching a specified keyword.

Syntax:

```
find <keyword>
```

Example: `find run`

Expected output:
```
These are the matching tasks: 
        1.[T][X] run
```

## Delete Task

Deletes a task based on its task number in the task list.

Syntax:

```
delete <task_number>
```

Example: `delete 1`

Expected output:
```
Noted. I've removed this task:
        [D][ ] shopping trip (by: 25 Dec 2024)
        Now you have 2 tasks in the list.
```

## Close the chatBot

Closes the chatBot program.

Syntax:

```
bye
```

Example: `bye`
