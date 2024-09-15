# Tohru User Guide

Tohru is your personal task assistant.

![Gallery](./Ui.png)

## Installation:

1. Download the jar from [releases](https://github.com/rollingpencil/ip/releases/).
2. Copy the Java archive to a folder of your choice.
3. Open terminal and run:

```bash
java -jar Tohru-<version>.jar
```

## Feature:
Available commands are:

`todo`, `deadline`, `event`, `list`, `find`, `delete`, `mark`, `unmark`, `bye`

### Adding todos

Adds a todo with the specified description to the todo list.

Format:

```text
todo <description>
```
Example:
```text
todo Buy groceries
```

Returns a response stating the details of the todo you just entered.

```
Added todo entry:
[T] [ ] Buy groceries
There are now 1 total entries
```

### Adding deadlines

Adds a deadline with the specified description and datetime to the todo list.

Format:

```text
deadline <description> /by <datetime>
```

The `datetime` should be in the format `DD/MM/YYYY HHMM` where the time is in 24h format.

Example:

```text
deadline Buy tickets /by 23/09/2024 1200
```

Returns a response stating the details of the deadline you just entered

```
Added deadline entry:
[D] [ ] Buy tickets (by: Mon 23 Sept 2024 1200)
There are now 2 total entries
```

### Adding events

Adds an event with the specified description, starting time and ending time to the todo list.

Format:

```text
event <description> /from <from-datetime> /to <to-datetime>
```

The `from-datetime` and `to-datetime` should be in the format `DD/MM/YYYY HHMM` where the time is in 24h format.

`to-datetime` should be later than `from-datetime` chronologically

Example:

```text
event Attend convention /from 24/09/2024 1200 /to 24/09/2024 1800
```

Returns a response stating the details of the event you just entered

```
Added event entry:
[E] [ ] Attend convention (from: Tue 24 Sept 2024 1200 to Tue 24 Sept 2024 1800)
There are now 3 total entries
```
### Listing all todo/deadline/events

List all tasks have entered into the todo list.

Format:

```text
list
```

Returns a response displaying all your todo items

```
These are 3 entries on your todo:
1. [T] [ ] Buy groceries
2. [D] [ ] Buy tickets (by: Mon 23 Sept 2024 1200)
3. [E] [ ] Attend convention (from: Tue 24 Sept 2024 1200 to Tue 24 Sept 2024 1800)
```

### Deleting todo/deadline/event

Removes a todo/deadline/events item from the todo list.

Format:

```text
delete <index>
```

The `index` should be the number corresponding to the entry in the `list` command.

Example:

```text
delete 4
```

Returns a response stating the details of the todo/deadline/event you just removed.

```
Alright! I have removed this task from list:
[T] [ ] Task to be deleted
```

### Mark todo/deadline/event as complete

Mark a todo/deadline/events item from the todo list as complete.

Format:

```text
mark <index>
```

The `index` should be the number corresponding to the entry in the `list` command.

Example:

```text
mark 1
```

Returns a response stating the details of the todo/deadline/event you just marked as complete.

```
Alright! I have set this task as done:
[T] [X] Buy groceries
```

### Mark todo/deadline/event as incomplete

Mark a todo/deadline/events item from the todo list as incomplete.

Format:

```text
unmark <index>
```

The `index` should be the number corresponding to the entry in the `list` command.

Example:

```text
unmark 1
```

Returns a response stating the details of the todo/deadline/event you just marked as incomplete.

```
Alright! I have set this task as not done:
[T] [ ] Buy groceries
```

### Finding todo/deadline/event

Find all tasks containing a specified keyword.

Format:

```text
find <keyword>
```
Example:

```text
find Buy
```

Returns a response displaying todo items that matches the keyword.

```
Here are the 2 matching tasks in your list:
1. [T] [ ] Buy groceries
2. [D] [ ] Buy tickets (by: Mon 23 Sept 2024 1200)
```

### Exiting

Exits the program.

Format:

```text
bye
```

Returns a response displaying the goodbye message.

```
Bye. Hope to see you again soon!
```

## Acknowledgements
- Close a stage after a certain amount of time JavaFX [Source](https://stackoverflow.com/a/27334614)