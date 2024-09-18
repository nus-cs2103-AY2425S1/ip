# Reminderebot User Guide

Reminderebot is a graphical user interface (GUI) desktop app that helps you track your upcoming tasks.
To start using Reminderebot, you can:

1. Ensure you have Java 17 or above installed in your computer
2. Download the .jar file under Releases
3. Navigate to the directory where the jar file is downloaded.
4. Run `reminderebot.jar` by clicking on the icon. Easy peasy!

## Features
Reminderebot is packed with various features:
- Viewing help: `help`
- Listing all tasks: `list`
- Addition of tasks
  - Todos: `todo`
  - Deadlines: `deadline`
  - Events: `event`
- Deletion of tasks: `delete`
- Mark tasks as done: `mark`
- Unmark tasks: `unmark`
- Search for task using keyword: `find`
- Exiting the programme: `bye`

## `help`
Shows the exact format for commands from within Reminderebot.

Usage:

```
 > help
 Welcome to the help section! Please enter a command below: 
 help
 bye
 list
 mark <int>
 unmark <int>
 find <keyword>
 todo <taskname>
 deadline <taskname> /by <duedate>
 event <name> /from <datetime> /to <datetime>
```

## `list`
Shows a list of your tasks.

Usage:

```dtd
> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
3. [E][ ] CS2103T practical (from 15 Nov 2024 16:00 to 15 Nov 2024 18:00)
```

## `todo`
Create a Todo.

Usage

`todo <todo name>`

```dtd
> todo CS2101 slides
Got it. I've added this task:
[T][ ] CS2101 slides
Now you have 1 tasks in the list.

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
```

## `deadline`
Create a Deadline.

Usage

`deadline <deadline name> /by <datetime>`

`<datetime> should be dd/MM/yy HH:mm`.

```dtd
> deadline CS2109S quiz /by 19/09/24 2359
Got it. I've added this task:
[D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
Now you have 2 tasks in the list.

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
```

## `event`
Create an Event.

Usage

`event <event name> /from <start time> /to <end time>`

`<start time> and <end time> should be dd/MM/yy HH:mm`.

```dtd
> event CS2103T practical /from 15/11/24 1600 /to 15/11/24 1800
Got it. I've added this task:
[E][ ] CS2103T practical (from: Nov 15 2024 0400pm to: Nov 15 2024 0600pm)
Now you have 3 tasks in the list.

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
3. [E][ ] CS2103T practical (from 15 Nov 2024 16:00 to 15 Nov 2024 18:00)
```

## `delete`
Delete a specific task.

Usage

`delete <index>`

```dtd
> delete 3
OK, I've removed this task:
[E][ ] CS2103T practical (from: Nov 15 2024 0400pm to: Nov 15 2024 0600pm)
Now you have 2 tasks in the list.

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
```

## `mark`
Mark a task as completed.

Usage

`mark <index>`

```dtd
> mark 1
Nice! I've marked this task as done:
[T][X] CS2101 slides

> list
Here are the tasks in your list:
1. [T][X] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
```

## `unmark`
Unmark a task as incomplete.

Usage

`unmark <index>`

```dtd
> unmark 1
OK, I've marked this task as not done yet:
[T][ ] CS2101 slides

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)
```

## `find`
Search a task based on keyword provided.

Usage

`find <keyword>`

```dtd
> find CS
Here are the matching tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2109S quiz (by 19 Sep 2024 23:59)

> find tasknotfound
Here are the matching tasks in your list:
```

## `bye`
Exits Reminderebot GUI and stores tasks.

Usage

```dtd
> bye
Bye. Hope to see you again soon!
> exits
```

## Screenshots
![Reminderbot in action](docs/Ui.png?raw=true "Title")