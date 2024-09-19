# Snowy User Guide
![Screenshot of Snowy Chatbot GUI](https://snowstopxt.github.io/ip/Ui.png)

Snowy is a **graphical user interface (GUI)** desktop app that helps you track your upcoming tasks. To start using Snowy, you can:
- Download the .jar file under Releases,
- run java -jar duke.jar in the terminal to start

Snowy is packed with various features, such as:

* Addition and deletion of tasks
	* Todos
	* Events
	* Deadlines
* Marking tasks as done
* Searching tasks with keywords
* Tagging tasks


## Features
### - `list`

See a list of your tasks.

#### Usage

`list`

```
> list
Here are the tasks in your list:
	1. [T][ ] CS2101 homework
	2. [D][ ] CS2100 quiz (by 20 Sep 2024 11:59pm)
	3. [E][ ] meeting (from 20 Sep 2024, 10:00pm to 20 Sep 2024, 11:00pm)
```

### - `todo`

Create a todo.

#### Usage

`todo <task name>`

```
> todo CS2101 homework
added:
	[T][ ] CS2101 homework
You have 1 task(s) in the list.
```

### - `event`

Create an event.

#### Usage

`event <event name> /from <start time> /to <end time>`

`<start time>` and `<end time>` should be `YYYY-MM-DD HHmm`.

```
> event meeting /from 2024-09-20 2200 /to 2024-09-20 2300
[E][ ] meeting (from 20 Sep 2024, 10:00pm to 20 Sep 2024, 11:00pm)
Now you have 3 task(s) in the list.

Added an event to your list of tasks
```

### - `deadline`

Create a deadline.

#### Usage

`deadline <deadline name> /by <time>`

`<time>` should be in the form of `YYYY-MM-DD HHmm`.

```
> deadline CS2100 quiz /by 2024-09-20 2359
added:
	[D][ ] CS2100 quiz (by 20 Sep 2024, 11:59pm )
You have 2 task(s) in the list.
```

### - `mark`
Mark a task as completed.
`mark 1`

#### Usage

`mark <task number>`

```
> mark 1
1. [T][X] CS2101 homework
Marked task as done at index 1
```

### - `unmark`
Unmark a task as uncompleted.
`mark 1`

#### Usage

`unmark <task number>`

```
> unmark 1
1. [T][] CS2101 homework
Unmarked task at index 1
```

### - `delete`

Delete a specific task.

#### Usage

`delete <task number>`

```
> delete 1
Removed task:
	[T][ ] CS2101 homework
Now you have 2 task(s) in the list.

Deleted task at index 1
```

### - `find`

Search a task based on the keyword provided.

#### Usage

`find <keyword>`

```
> find cs
	1. [T][ ] CS2101 homework
	2. [D][ ] CS2100 quiz (by 20 Sep 2024 11:59pm)

  2 task(s) listed!
```

### - `tag`

Tag a task.

#### Usage

`tag <task number> <tag name>`

```
> tag 1 important
	1. [T][ ] CS2101 homework # important

  Tagged task at index 1
```
