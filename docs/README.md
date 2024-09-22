# SecondMind User Guide
<img width="801" alt="Product Screenshot" src="UI.png">

SecondMind is a chatbot designed to assist you by keeping track of your to-do tasks, deadlines, and events. 

With its intuitive interface, SecondMind helps you stay organized and productive. 

Whether it’s managing your schedule or setting reminders, SecondMind ensures you never miss a deadline or event again.

## Adding basic ToDo tasks

Adds a ToDo task to the current task list.

Format: `todo {TASK_DESCRIPTION}`

Examples: 
- `todo Buy groceries`
- `todo Go to the gym`

Output:
```
Got it. I have added the following task:
  [T][] Buy groceries
You have a grand total of 1 task(s)
```

## Adding tasks with a deadline

Adds a task with a deadline specified by the user to the current task list.

Format: `deadline {TASK_DESCRIPTION} /by {TASK_DEADLINE}`
> [!WARNING]
> Dates must be of the format: yyyy-MM-ddTHH:mm:ss!

Examples:
- `deadline Return Software Engineering textbook to CLB /by 2024-11-28T18:00:00`
- `deadline Complete CS2109S Problem Set 3 /by 2024-10-15T23:59:59`

Output:
```
Got it. I have added the following task:
  [D][] Return Software Engineering textbook to CLB (by: 28 Nov 2024 18:00:00pm)
You have a grand total of 2 task(s)
```

## Adding events

Adds an event with a start time and end time, both specified by the user.

Format: `event {EVENT_DESCRIPTION} /from {EVENT_START_TIME} /to {EVENT_END_TIME}`

Examples:
- `event CS2103T Team Meeting /from 2024-10-28T18:00:00 /to 2024-10-28T21:00:00`
- `event Night Cycling at East Coast Park /from 2024-11-05T20:00:00 /to 2024-11-06T06:00:00`

Output:
```
Got it. I have added the following task:
  [E][] CS2103T Team Meeting (from: 28 Oct 2024 18:00:00pm to: 28 Oct 2024 21:00:00pm)
You have a grand total of 3 task(s)
```

## Viewing all tasks in task list

Displays completion status and description of all tasks in the task list.

Format: `list`

Output:
```
1. [T][] Buy groceries
2. [D][] Return Software Engineering textbook to CLB (by: 28 Nov 2024 18:00:00pm)
3. [E][] CS2103T Team Meeting (from: 28 Oct 2024 18:00:00pm to: 28 Oct 2024 21:00:00pm)
```

## Marking tasks as complete

Changes the completion status of a task to complete.

Format: `mark {TASK_NUMBER}`
> [!Note]
> Task numbers are 1-indexed.

Examples:
- `mark 1`

Output:
```
Well done! You have completed the following task:
  [T][X] Buy groceries
```

## Marking tasks as incomplete

Changes the completion status of a task to incomplete.

Format: `unmark {TASK_NUMBER}`

Examples:
- `unmark 1`

Output:
```
I've marked the following task as incomplete:
  [T][] Buy groceries
```

## Deleting tasks

Remove tasks from the task list.

Format: `delete {TASK_NUMBER}`

Examples:
- `delete 1`

Output:
```
I've removed the following task:
  [T][] Buy groceries
You have a grand total of 2 task(s)
```

## Finding tasks with keyword

Displays all tasks that contains the keyword specified by user.

Format: `find {KEYWORD}`
> [!Note]
> If multiple words are specified, they will be treated as a single keyword.

Examples:
- `find CS2103T`

Output:
```
1. [E][] CS2103T Team Meeting (from: 28 Oct 2024 18:00:00pm to: 28 Oct 2024 21:00:00pm)
```

## Exiting the program

Exit the SecondMind chatbot application.

Formats: `bye`
