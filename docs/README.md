# Atlas User Guide

![Ui](Ui.png "A screenshot of the Ui")

Atlas is a simple and intuitive task management chatbot designed to help users manage their tasks, deadlines, and events efficiently. It allows users to add, mark, delete, and search for tasks through a conversational interface. This guide will walk you through the main features of Atlas and how to interact with it.

## Adding deadlines

To add a Deadline task, you can specify the task description and the date by which the task is due.

```
deadline [task description] /by [YYYY-MM-DD]
```

Example: `deadline Submit assignment /by 2024-09-30`

Expected output:
```
Added: [D][ ] Submit assignment (by: 30 Sep 2024)
```

## Listing tasks (sort: optional)

To list tasks, you can specify the optional argument "sort"

```
list (sort)
```

## Marking Tasks

```
mark [task number]
```

Example: `mark 2`

Expected output:
```
Nice! I've marked this task as done:
[X] Submit assignment (by: 30 Sep 2024)
```

## Adding Events

```
event [event description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]
```

## Adding Todos

```
todo [todo description]
```