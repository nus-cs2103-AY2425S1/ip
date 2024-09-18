# Johnson Chatbot User Guide

## Adding a ToDo: `todo`
Adds a ToDo task to the task list.

**Format:** `todo TASK_DESCRIPTION /#TAG1…​`

💡 **Tip:** A ToDo task can have any number of tags (including 0).

**Examples:**
- `todo read book /#leisure`
- `todo buy groceries`

## Adding a Deadline: `deadline`
Adds a Deadline task to the task list.

**Format:** `deadline TASK_DESCRIPTION /by DATE TIME /#TAG1…​`

💡 **Tip:** A Deadline task can have any number of tags (including 0).

💡 **Tip:** The time is also optional.

**Examples:**
- `deadline submit report /by 2024-12-12 12:00 /#work #urgent`
- `deadline finish project /by 2024-11-30 23:59`

## Adding an Event: `event`
Adds an Event task to the task list.

**Format:** `event TASK_DESCRIPTION /from START_DATE START_TIME /#TAG1…​`

💡 **Tip:** An Event task can have any number of tags (including 0).

💡 **Tip:** The time is also optional.
**Examples:**
- `event team meeting /from 2024-12-01 10:00 /#work`
- `event birthday party /from 2024-11-20`

## Marking a Task as Done: `mark`
Marks a task as completed.

**Format:** `mark INDEX`

💡 **Tip:** The index refers to the position of the task in the task list.

**Examples:**
- `mark 1`
- `mark 3`

## Unmarking a Task: `unmark`
Marks a task as not completed.

**Format:** `unmark INDEX`

💡 **Tip:** The index refers to the position of the task in the task list.

**Examples:**
- `unmark 2`
- `unmark 4`

## Deleting a Task: `delete`
Deletes a task from the task list.

**Format:** `delete INDEX`

💡 **Tip:** The index refers to the position of the task in the task list.

**Examples:**
- `delete 1`
- `delete 5`

## Finding Tasks: `find`
Finds tasks that contain a specific keyword.

**Format:** `find KEYWORD`

💡 **Tip:** The search is case-sensitive.

**Examples:**
- `find report`
- `find meeting`

## Finding Tasks by Tag: `findtag`
Finds tasks that contain a specific tag.

**Format:** `findtag TAG`

💡 **Tip:** The search is case-sensitive.

**Examples:**
- `findtag work`
- `findtag urgent`

## Listing All Tasks: `list`
Lists all tasks in the task list.

**Format:** `list`

**Example:**
- `list`

## Exiting the Program: `quit`
Exits the chatbot.

**Format:** `quit`

**Example:**
- `quit`