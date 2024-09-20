# SecondMind User Guide
// Product screenshot goes here

SecondMind is a chatbot designed to assist you by keeping track of your to-do tasks, deadlines, and events. 

With its intuitive interface, SecondMind helps you stay organized and productive. 

Whether it’s managing your schedule or setting reminders, SecondMind ensures you never miss a deadline or event again.

## Adding basic ToDo tasks

Adds a ToDo task to the current task list.

Format: `todo {TASK_DESCRIPTION}`

Examples: 
- `todo Buy groceries`
- `todo Go to the gym`

## Adding tasks with a deadline

Adds a task with a deadline specified by the user to the current task list.

Format: `deadline {TASK_DESCRIPTION} /by {TASK_DEADLINE}`
> [!WARNING]
> Dates must be of the format: yyyy-MM-ddTHH:mm:ss!

Examples:
- `deadline Return Software Engineering textbook to CLB /by 2024-11-28T18:00:00`
- `deadline Complete CS2109S Problem Set 2 /by 2024-10-15T23:59:59`

## Adding events

Adds an event with a start time and end time, both specified by the user.

Format: `event {EVENT_DESCRIPTION} /from {EVENT_START_TIME} /to {EVENT_END_TIME}`

Examples:
- `event CS2013T Team Meeting /from 2024-10-28T18:00:00 /to 2024-10-28T21:00:00`
- `event Night Cycling at East Coast Park /from 2024-11-05T20:00:00 /to 2024-11-06T06:00:00`

## Viewing all tasks in task list

Displays completion status and description of all tasks in the task list.

Format: `list`

## Marking tasks as complete

Changes the completion status of a task to complete.

Format: `mark {TASK_NUMBER}`
> [!Note]
> Task numbers are 1-indexed.

Examples:
- `mark 1`

## Marking tasks as incomplete

Changes the completion status of a task to incomplete.

Format: `unmark {TASK_NUMBER}`

Examples:
- `unmark 1`

## Deleting tasks

Remove tasks from the task list.

Format: `delete {TASK_NUMBER}`

Examples:
- `delete 2`

## Finding tasks with keyword

Displays all tasks that contains the keyword specified by user.

Format: `find {KEYWORD}`
> [!Note]
> If multiple words are specified, they will be treated as a single keyword.

Examples:
- `find book`

## Exiting the program

Exit the SecondMind chatbot application.

Formats: `bye`
