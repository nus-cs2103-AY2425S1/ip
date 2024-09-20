# ChaCha User Guide

![Screenshot of Ui.png](./docs/Ui.png)

Procrastinating? Forgetting tasks? Not to worry, ChaCha is here to help you!

ChaCha is a chatbot that can help you keep track of your tasks, including todo, deadlines and events. 

Explore productivity with ChaCha the ChatBot!

## Viewing help: `help`

If you are new and don't know where to start, access overall help menu with `help`.

Format: `help`

If you need help for a specific command, include the command.

Format: `help <keyword>`

Example: `help todo`

Expected outcome:
`help todo` -- 

```
Want to add a To Do -- todo

This is how you should input the command: todo <description>
```
## Adding deadlines: `deadline`

Add a deadline task to your list, specifying the date.

Format: `deadline <description> /by <YYYY-MM-DD>`

Example: `deadline iP submission /by 2024-09-20`

Expected output:
```
Got it. I've added this task:
  [D][] ip submission (by: Sep 20 2024)
Now you have 1 tasks in the list.
```

## Adding events: `event`

Add an event to your list, specifying the date, start time and end time.

Format: `event <description> /<YYYY-MM-DD> /from <start time> /to <end time>`, where the time should 
be in the format of `HH.MMam` or `HH.MMpm`.

Example: `event CS2103T lecture /2024-09-20 /from 4pm /to 5.30pm`

Expected output:
```
Got it. I've added this task:
  [E][] cs2103t lecture (Sep 20 2024 from: 4.00PM to: 5.30PM)
Now you have 2 tasks in the list.
```

## Adding todos: `todo`

Add your todo tasks in your list.

Format: `todo <description>`

Example: `todo add javadoc`

Expected output:
```
Got it. I've added this task:
  [T][] add javadocs
Now you have 3 tasks in the list.
```

## Listing all tasks: `list`

List all the tasks you have in the list.

Example: `list`

Expected output:
```
Here are the tasks in your list:
1. [D][] ip submission (by: Sep 20 2024)
2. [E][] cs2103t lecture (Sep 20 2024 from: 4.00PM to: 5.30PM)
3. [T][] add javadocs
```

## Mark tasks as done: `mark`

Mark your tasks as done.

Format: `mark <index of task>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
  [D][X] ip submission (by: Sep 20 2024)
```


## Mark tasks as undone: `unmark`

Mark your tasks as undone.

Format: `unmark <index of task>`

Example: `unmark 1`

Expected output:
```
Okay! I've marked this task as not done yet:
  [D][] ip submission (by: Sep 20 2024)
```


## Find tasks: `find`

Find a task you want with a keyword in the description.

Format: `find <keyword>`

Example: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] return book
2. [T][] colour book
```


## Delete tasks: `delete`

Delete tasks that you do not need anymore.

Format: `delete <index of task>`

Example: `delete 1`

Expected outcome:
```
Okay! I've removed this task:
  [D][] ip submission (by: Sep 20 2024)
Now you have 4 tasks in the list.
```

## Close the application: `bye`

Close the application once you are done.

Example: `bye`

Expected outcome:
```
Bye! Hope to talk to you again soon!
```

## Command Summary

| Action   | Format                                               | Example          |
|----------|------------------------------------------------------|------------------|
| `help`   | `help`                                               | `help`           |
|    | `help<command>`                                      | `help todo`      |
| `deadline` | `deadline <description> /by <YYYY-MM-DD>`            |           `deadline iP submission /by 2024-09-20`       |
| `event`    | `event <description> /<YYYY-MM-DD> /from <start time> /to <end time>` | `event CS2103T lecture /2024-09-20 /from 4pm /to 5.30pm`            |
| `todo`     | `todo <description>`                                 | `todo add javadoc` |
| `list`     | `list`                                               | `list`             |
| `mark`     |  `mark <index of task>`                              | `mark 2`           |
| `unmark`   |     `unmark <index of task>`                           | `unmark 1`         |
| `find`     |     `find <keyword>`                                 | `find book`        |
| `delete`   |      `delete <index of task>`                          | `delete 1`         |
| `bye`      | `bye`                                                | `bye`              |
