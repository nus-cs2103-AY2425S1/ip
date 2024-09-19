# zAIbot User Guide

![](Ui.png)

> The to-do app that reminds you what to do,
now with upgraded sarcasm.

To use the application:
1. Download the latest release
2. Copy it into a folder
3. Run this command in the folder
```bash
java -jar [nameOfFile]
```
## Adding tasks

There are 3 kinds of tasks.
1. todo (has no extra options)
2. deadline (has an extra option `by` to state deadline)
3. event (has two extra options `from` and `to` to state event dates)

### Adding todos

```
todo NAME
```

This command adds a `todo` task with the name.

---
**Example:**
```
todo Do something
```
adds a task with the name "Do something".

### Adding deadlines

```
deadline NAME /by YYYY-MM-DD HH:MM
```

This command adds a `deadline` task with the name and
a deadline.

> Dates must be formatted in YYYY-MM-DD HH:MM.
>
> If it is not entered this way, the command will not show in the interface.

---
**Example:**
```
deadline Do assignment /by 2024-01-01 12:00
```
adds a deadline task "Do assignment" with the deadline
by 1 Jan 24, 12pm.

### Adding events

```
event NAME /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM
```

This command adds a `event` task with the name and
a start date marked `from` and an end date marked `to`.

> Dates must be formatted in YYYY-MM-DD HH:MM.
>
> If it is not entered this way, the command will not show in the interface.

---
**Example:**
```
event Exam /from 2024-01-01 12:00 /to 2024-01-01 14:00
```
adds a event "Exam" from 1 Jan 24 12pm to 1 Jan 24 2pm.

# Listing events

```
list
```

Gets all the events.

## Filtering for specific events

```angular2html
find TEXT
```

where TEXT is a part of the name inside the events.

---
Example:

```angular2html
find Exam
```

gets all the tasks with "Exam" inside the name

# Updating tasks

## Marking tasks

```
mark NUMBER
```
where `NUMBER` is the index of the tasks as seen
in the `list` command.

---

```mark 1```
would mark the first task in the list.

## Unmark tasks

```
unmark NUMBER
```
where `NUMBER` is the index of the tasks as seen
in the `list command`

---

```unmark 1```
would unmark the first task in the list.

## Delete tasks

```
delete NUMBER
```
where `NUMBER` is the index of the tasks as seen
in the `list` command

---

```delete 1```
would delete the first task in the list.

# Closing application

```angular2html
bye
```

Exits the application after 1 second.