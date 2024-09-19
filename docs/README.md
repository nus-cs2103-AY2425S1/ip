# Echo User Guide

<img src="Ui.png" width="300">

Echo is a chat bot who will help you manage your tasks!

## Adding todos: `Todo`

Adds a task with a description.

Format: `Todo` `[Description]`

Example: `Todo` `Go for a run`

```
Got it. I've added this task:
[T] [] Go for a run
Now you have 1 task in the list.
```

## Adding deadlines: `Deadline`

Adds a task with a deadline.

Format: `Deadline` `[Description]` `/by [date]`
- Date can be in any date format including date, month & year.
e.g. 24-9-2024, 4 Jun 2024

Example: `Deadline` `Assignment` `/by 24/9/2024`

```
Got it. I've added this task:
[D] [] Assignment (by: Sep 24 2024)
Now you have 1 task in the list.
```

## Adding events: `Event`

Adds a task with a start and end date/time.

Format: `Event` `Description` `/from [start date/time]` `/to [start date/time]`
- Start date/time can be anything.

Example: `Event` `My birthday` `/from 2pm` `/to 3pm`

```
Got it. I've added this task:
[E] [] My birthday (from: 2pm to: 3pm)
Now you have 1 task in the list.
```

## Marking tasks: `Mark`

Mark tasks to complete them.

Format: `Mark` `[List index]`

Example: `Mark` `1`

```
Nice! I've marked this task as done:
[E] [X] My birthday (from: 2pm to: 3pm)
```

## Unmarking tasks: `Unmark`

Unmark tasks to mark them as not completed.

Format: `Unmark` `[List index]`

Example: `Unmark` `1`

```
Ok, I've marked this task as not done yet:
[E] [] My birthday (from: 2pm to: 3pm)
```

## Deleting tasks: `Delete`

Delete added tasks

Format: `Delete` `[List index]`

Example: `Delete` `1`

```
Noted. I've removed this task:
[E] [] My birthday (from: 2pm to: 3pm)
```

## Finding tasks: `Find`

Find added tasks with a keyword.

Format: `Find` `[Keyword]`

Example: `Find` `Birthday   `

```
Here are the matching tasks in your list:
1. [E] [] My birthday (from: 2pm to: 3pm)
```

## Listing tasks: `List`

List all added tasks.

Format: `List`

Example: `List`

```
Here are the tasks in your list:
1. [T] [] Go for a walk
2. [E] [X] My birthday (from: 2pm to: 3pm)
3. [D] [] Asisgnment (by: Sep 29 2024)
```

## Updating tasks: `Update`

Update added tasks.

Format: `Update` `[List index]` `[Updated parameter]`
- Chatbot will automatically query user on certain null inputs. 
  User should hit `Enter` key to keep the parameter the same.
- Example:
```
Update 1 /from 2pm

Enter task description:

[user hits enter key]
```

Example: `Update` `1` `/by 30/9/2024`

```
Nice! I've edited this task:
[D] [] My birthday (by: Sep 30 2024)
```

## Quitting chatbot: `Bye`

Quits Echo chatbot and saves added tasks to file.

Format: `Bye`

Example: `Bye`

```
Bye. Hope to see you again soon!
```