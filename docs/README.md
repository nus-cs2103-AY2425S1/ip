# Socchat User Guide

## Features
> 📝 Note
> - Words in `<>`are are the parameter should be supplied from user.
>   
>   e.g. `todo <task_description>`, `description` is the parameter which can be used as `todo swimming`.
> - Items in square brackets are optional. 
>   
>   e.g. `todo <task_description> [-tag <your_tag>]` can be used as `todo swimming -tag sport` or `todo swimming`
> - Parameters are in order. Follow the order in format.
> - Extraneous parameters for commands that do not take in parameters (such as `exit`, `list`) will be ignored.
>  e.g. `list 1` will be treated as `list`

## Add Todo Task: `todo`

Adds a task to Socchat.

**Format:** `todo <task_description> [-tag <your_tag>]`

**Example:** 
- todo assignment 1 
- todo swimming -tag sport

Example Outcome:

```
Meow~ added: [T][] swimming<tag: sport>
Now you have 3 task(s).
```

## Add Deadline Task: `deadline`

Add a deadline type of task to Socchat.

**Format:** `deadline <task_description> /by <deadline> [-tag <your_tag>]`

**Example:**
- deadline assignment 1 /by 2024-09-20
- deadline assignment 1 /by 2024-09-20 -tag important

Example Outcome:

```
Meow~ added: [D][] assignment 1 (by: 2024-09-20)<tag: important>
Now you have 4 task(s).
```

## Add Event Task: `event`

Add a event type of task to Socchat.

**Format:** `event <task_description> /from <start-date> /to <end-date> [-tag <your_tag>]`

**Example:**
- event party /from 2024-09-20 /to 2024-09-21
- event party /from 2024-09-20 /to 2024-09-21 -tag entertainment

Example Outcome:

```
Meow~ added: [E][] party (from: 2024-09-20, to: 2024-09-21)<tag: entertainment>
Now you have 5 task(s).
```
## List all tasks: `list`
List all the tasks in Socchat.

**Format**: `list`

## Find tasks: `find`
Find a task with a keyword.

**Format**: `find <your_keyword>`

> 💡 Tips to `find`
> - Search can be partially matched e.g. `p` can match `ip` and `party`.
> - Search is not case-sensitive e.g. `P` cannot match `ip`.

**Example:**

`find p` return all my tasks containing `p`.

<img src="./find.png" alt="find example" height="300" width="400"/>


## Delete a task: `delete`
Delete a task.

**Format**: `delete <task_number>`

**Example:**
- delete 6

Example Outcome:
```
Deleted "[T][] dance<tag: null>"
Now you have 5 task(s). Meow~
```

## Mark a task: `mark`
Mark a task as done.

**Format**: `mark <task_number>`

**Example:**
- mark 6

Example Outcome:
```
Meow~ Marked "dance" as done
[T][X] dance<tag: null>
```

## Unmark a task: `unmark`

Unmark a task as not done.

**Format**: `unmark <task_number>`

**Example:**
- unmark 6

Example Outcome:
```
Meow~ Marked "dance" as not done
[T][] dance<tag: null>
```

## Exit the program: `bye`

Exits the program. Program will automatically closed after a few seconds.

**Format**: `bye`

## Saving data

Socchat tasks are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.