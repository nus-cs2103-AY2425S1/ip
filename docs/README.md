# Arts User Guide

![Product Screenshot](./Ui.png)
Welcome to the **Arts** User Guide. This guide will help you navigate and utilize all the features of the Arts product efficiently.

## Adding Deadlines

To add a deadline, use the following command format:

Example: `deadline <deadline-name> /by <date>`

- **Date Format**: The date must be in one of the following formats:
    - `yyyy-MM-dd HHmm` (e.g., `2023-12-31 2359`)
    - `d/M/yyyy HHmm` (e.g., `31/12/2023 2359`)

- **Outcome**: This action will create a new deadline with the specified name and date, provided that the date is valid and the task does not already exist in the task list.

- **Expected Output**:
  ```
  Yatta! 🎉 I've successfully added this task to your list:
  ✨ <deadline-name> (by: <formatted-date>) ✨
  Now your quest has <number-of-tasks> task(s) to conquer! Ganbatte! 💪
  ```

### Notes
- Ensure that the task description and deadline are separated by `/by`.
- The task description cannot be empty.
- If the task already exists with the same description and deadline, an error will be raised.
- If the date format is incorrect, the following error message will be displayed:
  ```
  Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.
  ```

## Adding Events

To add an event, use the following command format:

Example: `event <event-name> /from <start-date> /to <end-date>`

- **Date Format**: Both the start and end dates must be in one of the following formats:
    - `yyyy-MM-dd HHmm` (e.g., `2023-12-31 1400`)
    - `d/M/yyyy HHmm` (e.g., `31/12/2023 1400`)

- **Outcome**: This action will create a new event with the specified name and date range, provided that the dates are valid, the start date is before the end date, and the event does not already exist in the task list.

- **Expected Output**:
  ```
  Sugoi! 🌟 I've added this epic event to your adventure:
  🎉 <event-name> (from: <formatted-start-date> to: <formatted-end-date>) 🎉
  Now your journey includes <number-of-tasks> task(s) to tackle! Keep up the great work, hero! 💪
  ```

### Notes
- Ensure that the event description, start date, and end date are separated by `/from` and `/to`.
- The event description cannot be empty.
- If the event already exists with the same description and date range, an error will be raised.
- If the start date is not before the end date, the following error message will be displayed:
  ```
  Event start date must be before end date.
  ```
- If the date format is incorrect, the following error message will be displayed:
  ```
  Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.
  ```
- If the event details are incomplete or incorrectly formatted, the following error message will be displayed:
  ```
  The event must have /from and /to times.
  ```

## Adding Todo Tasks

To add a todo task, use the following command format:

Example: `add-todo <todo-description>`

- **Description**: The description of the todo task must not be empty and should clearly specify the task to be added.

- **Outcome**: This action will create a new todo task with the specified description, provided that the description is valid and the task does not already exist in the task list.

- **Expected Output**:
  ```
  Hooray! 🎊 A new adventure awaits with this task:
  ✨ <todo-description> ✨
  Your quest now has <number-of-tasks> task(s) to conquer! Keep shining, champion! 🌟
  ```

### Notes
- The todo description cannot be empty. If it is, the following error message will be displayed:
  ```
  The description of a todo cannot be empty.
  ```
- If the todo task already exists with the same description, an error will be raised:
  ```
  A todo with the same description already exists.
  ```
- If any other input parameter is invalid, the following error message will be displayed:
  ```
  Invalid input parameters
  ```

## Deleting Tasks

To delete a task, use the following command format:

Example: `delete <task-index>`

- **Task Index**: The index of the task to be deleted must be a valid number corresponding to the task's position in the task list (starting from 1).

- **Outcome**: This action will remove the specified task from the task list, update the storage, and display a confirmation message.

- **Expected Output**:
  ```
  Farewell, brave task! 🌸 You've been removed from the quest:
  💔 <task-description> 💔
  The journey continues with <number-of-tasks> task(s) left. Keep going, warrior! 🗡️
  ```

### Notes
- The task index must be a number. If it is not, the following error message will be displayed:
  ```
  Task index must be a number.
  ```
- The task index must be within the bounds of the task list. If it is not, the following error message will be displayed:
  ```
  Task index is out of bounds.
  ```
- If there is an error saving the updated task list to storage, the following error message will be displayed:
  ```
  Failed to save tasks to storage.
  ```

## Finding Tasks

To find tasks containing a specific keyword, use the following command format:

Example: `find <keyword>`

- **Keyword**: The keyword to search for in the task descriptions. It must not be null, empty, or consist solely of special characters.

- **Outcome**: This action will search through the task list and return tasks that contain the specified keyword in their descriptions.

- **Expected Output**:
    - If tasks are found:
      ```
      Eureka! 🎉 Here are the tasks that matched your quest for '<keyword>':
      1. <task-description-1>
      2. <task-description-2>
      ...
      Keep up the great work, hero! 💪✨
      ```
    - If no tasks are found:
      ```
      Oh no! 😱 No tasks matched your search. Keep your spirits high, the right task will appear! 🌈
      ```

### Notes
- The keyword must contain at least one alphanumeric character. If it doesn't, the following error message will be displayed:
  ```
  Keyword cannot be null, empty, or contain only special characters.
  ```

## Marking Tasks as Done

To mark a task as done, use the following command format:

Example: `mark <task-index>`

- **Task Index**: The index of the task to be marked as done must be a valid number corresponding to the task's position in the task list (starting from 1).

- **Outcome**: This action will mark the specified task as done, update the storage, and display a confirmation message.

- **Expected Output**:
  ```
  Victory! 🌟 I've marked this task as complete:
  🎉 <task-description> 🎉
  You've leveled up, champion! Keep conquering those tasks! 🚀
  ```

### Notes
- The task index must be a number. If it is not, the following error message will be displayed:
  ```
  Task index must be a number.
  ```
- The task index must be within the bounds of the task list. If it is not, the following error message will be displayed:
  ```
  Task index is out of bounds.
  ```
- If there is an error saving the updated task list to storage, the following error message will be displayed:
  ```
  Failed to save tasks to storage.
  ```
- If the task at the given index does not exist, an error will be thrown:
  ```
  Task at the given index does not exist.
  ```


## Unmarking Tasks as Not Done

To unmark a task (mark it as not done), use the following command format:

Example: `unmark <task-index>`

- **Task Index**: The index of the task to be marked as not done must be a valid number corresponding to the task's position in the task list (starting from 1).

- **Outcome**: This action will mark the specified task as not done, update the storage, and display a confirmation message.

- **Expected Output**:
  ```
  🎌 Fear not, for this task has been unmarked! 🗒️
  Continue your quest with renewed vigor, valiant warrior! 🌟
  <task-description>
  ```

### Notes
- The task index must be a number. If it is not, the following error message will be displayed:
  ```
  Task index must be a number.
  ```
- The task index must be within the bounds of the task list. If it is not, the following error message will be displayed:
  ```
  Task index is out of bounds.
  ```
- If there is an error saving the updated task list to storage, the following error message will be displayed:
  ```
  Failed to save tasks to storage.
  ```
- If the task at the given index does not exist, an error will be thrown:
  ```
  Task at the given index does not exist.
  ```

## Sorting Deadline Tasks Chronologically

To sort deadline tasks chronologically, use the following command:

Example: `sort_deadlines`

- **Outcome**: This action will sort all tasks of type `Deadline` in chronological order based on their due dates. The sorted list will then be saved to storage, and a confirmation message will be displayed.

- **Expected Output**:
  ```
  ✨ Behold! The deadlines have been aligned in perfect harmony! 📅✨
  Your journey through time is now clearer, brave adventurer! 🌟
  ```

### Notes
- The command will only affect tasks of type `Deadline`. Other tasks will remain in their original order.
- The sorted deadlines will be placed at the beginning of the task list, followed by other types of tasks.
- If there is an error saving the sorted task list to storage, an `ArtsException` will be thrown with an appropriate error message.

## Sorting Event Tasks by Start Date

To sort event tasks by their start date, use the following command:

Example: `sort_events`

- **Outcome**: This action will sort all tasks of type `Event` by their start date. The sorted list will then be saved to storage, and a confirmation message will be displayed.

- **Expected Output**:
  ```
  ✨ The stars have aligned, and your events are now sorted by time! ⏰✨
  Embark on your epic journey with clarity and purpose, noble hero! 🌟
  ```

### Notes
- The command will only affect tasks of type `Event`. Other tasks will remain in their original order.
- The sorted events will be placed at the beginning of the task list, followed by other types of tasks.
- If there is an error saving the sorted task list to storage, an `ArtsException` will be thrown with an appropriate error message.