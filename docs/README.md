# Alisa User Guide

Alisa is a friendly chatbot that helps track and manage your tasks using a Command Line Interface (CLI).

Here are some of the features Alisa can do:

- Add different types of tasks (todo, event, deadline)
- Mark tasks as complete or incomplete
- Delete tasks from the list
- Edit specific tasks in the list
- Search for tasks based on keywords
- List all tasks in the list

## Adding Tasks

### Todo

Format: `todo TASK DESCRIPTION`

Example: `todo grocery shopping`

### Deadline

Format: `deadline TASK DESCRIPTION /by yyyy-mm-dd hh:mm`

Example: `deadline finish assignment /by 2024-09-20 12:00`


### Event

Format: `event TASK DESCRIPTION /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm`

Example: `event meeting /from 2024-09-20 12:00 /to 2024-09-20 14:00`

## Marking/Unmarking Tasks

Format: `mark INDEX` or `unmark INDEX`

Example: `mark 1` or `unmark 1`

## Deleting Tasks

Format: `delete INDEX`

Example: `delete 1`

## Editing Tasks

Format: `edit INDEX FEATURE_TO_EDIT EDITED_CONTENT`

Example: `edit 1 description finish assignment 2`

## Searching for Tasks

Format: `find KEYWORD`

Example: `find homework`

## Listing Tasks

Format: `list`




