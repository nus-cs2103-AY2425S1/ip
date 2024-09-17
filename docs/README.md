# Blitz User Guide
// Product screenshot goes here

Blitz is a **desktop app for managing tasks** via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Adding a todo task

Adds a todo task to the application.<br/><br/>

Syntax: `todo [description]`<br/><br/>

Example: `todo this is my first todo task!`

You should see this message in the application:
```
Got it. I've added this task:
[T][ ] this is my first todo task!
Now you have 1 tasks in the list.
```

## Adding a deadline task

Adds a deadline task to the application.<br/><br/>

Syntax: `deadline [description] /by [yyyy-mm-dd hhmm]`<br/><br/>

Example: `deadline this is my first deadline task! /by 2024-07-19 1200`

You should see this message in the application:
```
Got it. I've added this task:
[D][ ] this is my first deadline task! (by: 19 Jul 2024 12:00)
Now you have 1 tasks in the list.
```

## Adding a event task

Adds a event task to the application.<br/><br/>

Syntax: `event [description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]`<br/><br/>

Example: `event this is my first event task! /from 2024-07-19 1200 /to 2024-07-20 1200`

You should see this message in the application:
```
Got it. I've added this task:
[E][ ] this is my first event task! (from: 19 Jul 2024 12:00 to：20 Jul 2024 12:00)
Now you have 1 tasks in the list.
```

## Listing existing tasks

Lists all the task that have been added to the application.<br/><br/>

Syntax: `list`<br/><br/>

Example 1: suppose you have not add any task yet and you run `list`

You should see this message in the application:
```
There is nothing in the list!
```
<br/>

Example 2: suppose you run `todo this is my first todo task!` before and now you run `list`

You should see this message in the application:
```
Here are the tasks in your list:
1. [T][ ] this is my first todo task!
```

## Marking a task as done

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Unmarking a task to undone

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Finding a task

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Deleteing a task

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Exiting the application

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
