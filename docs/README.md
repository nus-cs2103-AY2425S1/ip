# LBot User Guide
Your personal dedicated hyper assistant
![Image of LBot](Ui.png)

# Features
## Adding a `ToDo` task: `todo`, `td`
Creates a new `Todo` task.

Format: `todo/td description`

#### Examples

- `todo Pull the moon closer to earth`
- `td Destroy the sun`

## Adding an Event task: `event`, `e`
Creates a new `Event` task.

Format: `event/e description /from: yyyy-MM-dd HHmm /to: yyyy-MM-dd HHmm`

#### Examples

- `event conference for sun destruction /from: 2024-12-31 0900 /to: 2025-01-01 1800`
- `e friend meetup /from: 2024-09-18 1800 /to: 2024-09-18 2100`

## Adding a Deadline task: `deadline`, `d`
Creates a new `Deadline` task.

Format: `deadline/d description /by: yyyy-MM-dd HHmm`

#### Examples

- `deadline Moon gotta go /by: 2024-12-31 0000`
- `d submit application for moon destruction /by: 2024-12-01 1200`

## Marking a task: `mark`, `m`
Marks a task as complete or incomplete (depending on its current status).

Format: `mark/m index`

Note that index is the id of the task when you use the `list` command.
#### Examples

- `mark 1`
- `m 5`

## Deleting a task: `delete`, `del`

Format: `delete/del index`

Note that index is the id of the task when you use the `list` command.

#### Examples

- `delete 2`
- `del 1`

## List all tasks: `list`, `l`
Lists all current tasks in the system.

Format: `list/l`

## Find task with search term: `find`, `f`
Filters through the task list and returns tasks fitting the search term.

Format: `find/f term`

#### Examples

- `find moon`
- `f sun`

## Bye
Sends a special bye message <3

Format: `bye`, `bb`