# IpMan User Guide
<img src="./Ui.png" alt="image of window with some commands that have been run in the chatbot" style="max-width: 500px;" />

IpMan is a chatbot which helps you to manage tasks and their deadlines. It saves
the tasks locally into a file and loads it later when needed. This chatbot
supports 3 types of tasks: to-dos, deadlines and events.

- To-dos: Simply keeps track of something that needs to be done
- Deadlines: Something that needs to be done by a certain date
- Events: A task that starts and ends on some dates.

## Adding to-dos
The following command adds a to-do to the chatbot's list of tasks.
```
todo <name>
```

Example commands:
```
todo math homework assignment 1
todo plan for JB trip
```

After adding these to-dos, we can see that new to-dos have been added to our
chatbot by using the `list` command:

User:
```
list
```

Chatbot response:
```
1. [T][ ] math homework assignment 1
2. [T][ ] plan for JB trip
```

## Adding deadlines
The following command adds a deadline to the chatbot's list of tasks.
```
deadline <name> /by <date in YYYY-MM-DD>
```
Note that the date should be in the format of `<YEAR>-<MONTH>-<DAY>` and should
have 4, 2 and 2 numbers respectively for each part of the date.

Example commands:
```
deadline math homework assignment 1 /by 2024-09-03
deadline submit proposal for JB trip /by 2024-12-19
```

After adding these deadlines, we can see that new deadlines have been added to
our chatbot by using the `list` command:

User:
```
list
```

Chatbot response:
```
1. [D][ ] math homework assignment 1 (by: 03/09/2024)
2. [D][ ] submit proposal for JB trip (by: 19/12/2024)
```

## Adding events
The following command adds an event to the chatbot's list of tasks.
```
event <name> /from <date in YYYY-MM-DD> /to <date in YYYY-MM-DD>
```
Note that the dates should be in the format of `<YEAR>-<MONTH>-<DAY>` and should
have 4, 2 and 2 numbers respectively for each part of the date.

Example commands:
```
event holiday trip to Singapore /from 2024-10-19 /to 2024-11-01
event camping trip /from 2024-09-21 /to 2024-09-30
```

After adding these events, we can see that new events have been added to
our chatbot by using the `list` command:

User:
```
list
```

Chatbot response:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [E][ ] camping trip (from: 21/09/2024 to: 30/09/2024)
```

## Listing all tasks
The following command shows all the tasks that have been added:
```
list
```

All tasks (to-dos, deadlines and events) will be shown in a list. There are two
groups of square brackets at the start of the list which indicate the type of
task and whether the task has been marked as done.

In particular, the task types can be read as follows:
- `[T]` the task is a to-do
- `[D]` the task is a deadline
- `[E]` the task is an event

For example:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [D][X] submit proposal for JB trip (by: 19/12/2024)
```

The first task is an event (`E`) which has not been marked as done as the square
brackets next to the task type are empty (i.e. `[ ]`).

The second task is a deadline (`D`) which has been marked as done as the square
brackets next to the task type have an X (i.e. `[X]`).

## Finding a task
The following command searches for tasks whose name contains the search term:
```
find <search term>
```

Example comamnds:
```
find trip
find holiday
```

For example, consider a chatbot which has the following list of tasks:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [D][X] submit proposal for JB trip (by: 19/12/2024)
3. [T][ ] math homework assignment 1
4. [E][X] camping trip (from: 21/09/2024 to: 30/09/2024)
5. [T][ ] plan for JB trip
```

Then, running the command:
```
find trip
```
Will give the following output:
```
1. [D][X] submit proposal for JB trip (by: 19/12/2024)
2. [E][X] camping trip (from: 21/09/2024 to: 30/09/2024)
3. [T][ ] plan for JB trip
```

## Deleting a task
The following comamnd deletes a task based on the numbering given by `list`:
```
delete <index number>
```

Example comamnds:
```
delete 1
delete 4
```

For example, consider a chatbot which has the following list of tasks:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [D][X] submit proposal for JB trip (by: 19/12/2024)
3. [T][ ] math homework assignment 1
4. [E][X] camping trip (from: 21/09/2024 to: 30/09/2024)
5. [T][ ] plan for JB trip
```

Then, running the command:
```
delete 3
```
Will delete the third item of the list. And the chatbot will reply:
```
Noted. I've removed this task:
[T][ ] math homework assignment 1
Now you have 4 tasks in the list.
```

## Marking a task as done
The following command will mark a task as complete
```
mark <index number>
```

For example, consider a chatbot which has the following list of tasks:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [D][X] submit proposal for JB trip (by: 19/12/2024)
3. [T][ ] math homework assignment 1
4. [E][X] camping trip (from: 21/09/2024 to: 30/09/2024)
5. [T][ ] plan for JB trip
```

Then, running the command:
```
mark 3
```
Will mark the third item of the list as completed. And the chatbot will reply:
```
Marked this task as done:
[T][X] math homework assignment 1
```

## Unmarking a task as done
The following command will unmark a task as complete
```
unmark <index number>
```

For example, consider a chatbot which has the following list of tasks:
```
1. [E][ ] holiday trip to Singapore (from: 19/10/2024 to: 01/11/2024)
2. [D][X] submit proposal for JB trip (by: 19/12/2024)
3. [T][ ] math homework assignment 1
4. [E][X] camping trip (from: 21/09/2024 to: 30/09/2024)
5. [T][ ] plan for JB trip
```

Then, running the command:
```
unmark 2
```
Will mark the third item of the list as completed. And the chatbot will reply:
```
Marked this task as not yet done:
[D][ ] submit proposal for JB trip (by: 19/12/2024)
```

## Exiting the program
The following command closes the program:
```
bye
```

## Command Shorthands
Every command has a alternative that may be used. Following is a list of all the
commands and their alternatives:

- `bye`: `exit`
- `list`: `l`
- `mark`: `m`
- `unmark`: `um`
- `todo`: `t`
- `deadline`: `d`
- `event`: `e`
- `delete`: `del`
- `find`: `f`

For example, instead of typing:
```
event camping trip /from 2024-09-21 /to 2024-09-30
```
The user may use:
```
e camping trip /from 2024-09-21 /to 2024-09-30
```
