# Hue User Guide


![UI Screenshot](Ui.png)


## Introduction
Hue is a task management chatbot that helps you keep track of your tasks efficiently.


## Features
- **Add Tasks**: You can add tasks with the `todo`, `deadline`, and `event` commands.
- **List Tasks**: View all your tasks using the `list` command.
- **Mark/Unmark Tasks**: Use `mark` and `unmark` to change the status of tasks.
- **Delete Tasks**: Remove tasks from your list using the `delete` command.
- **Find Tasks**: Search for tasks using keywords with the `find` command.
- **Reschedule Tasks**: Postpone tasks with the `reschedule` command.

## Commands

### Valid Date Formats 
- `YYYY-MM-DD` (e.g. 2021-01-01)
- `DD/MM/YYYY HHmm` (e.g. 01/01/2021 1800)


### Adding Tasks
- **To Do** 
  * Input: `todo [task description]` 
  * Output: `[T][ ] [task description]`
- **Deadline** 
  * Input: `deadline [task description] /by [date]` 
  * Output: `[D][ ] [task description] (by: [date])`
- **Event** 
  * Input: `event [task description] /from [start date] /to [end date]` 
  * Output: `[E][ ] [task description] (from: [start date] to: [end date])`
### Marking/Unmarking Tasks
- **Mark**
  * Input: `mark [task number]` 
  * Output: `[T/D/E][X]`
- **Unmark**
  * Input `unmark [task number]` 
  * Output: `[T/D/E][ ]`

### Deleting Tasks
- **Delete** 
  * Input `delete [task number]` 
  * Output `Deleted: [task description]`

### Finding Tasks
- **Find**
  * Input: `find [keyword]` 
  * Output: `Here are the tasks in your list that match the keyword [keyword]:`

### Rescheduling Tasks
- **Reschedule** 
  * Input: Deadline - `reschedule [task number] /by [new date]` or Event - `reschedule [task number] /from [new start date] /to [new end date]`
  * Output: `Task Rescheduled: [task description] [new start date] ([new end date])`

## Error Handling
- Invalid command format
- Date parsing issues
- Invalid Task Range (Delete and Mark/Unmark)
- Marking a task that is already marked
- Unmarking a task that has not been marked yet

## Additional Information
For further assistance, please contact hughsoongks@gmail.com.