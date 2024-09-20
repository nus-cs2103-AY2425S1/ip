# Dave User Guide

![User Interface](Ui.png)

Dave is a simple, interactive chatbot designed to help users manage tasks. With Dave, users can create, manage, and track their tasks, set deadlines, mark tasks as completed, and more. It provides an intuitive command-based interface that helps you keep track of your to-do list efficiently.


## Adding deadlines

Dave allows users to add tasks with deadlines, helping them stay on top of their important events.

Usage: To add a deadline, use the following command format:

Command Format: `deadline <task description> /by <date/time>`

Example: `deadline Submit assignment /by 2024-09-20`

Expected Outcome: The task with a deadline will be added to the list, and Dave will confirm the addition by displaying the task and its corresponding deadline.

```
Got it. I've added this task:
  [D][ ] Submit assignment (by: Sep 20 2024)
Now you have 3 tasks in the list.
```

## Adding To-Dos

To-dos are basic tasks without any specific deadline. Dave allows users to add and manage these tasks.

Usage: To add a to-do, use the following command format:

Command Format: `todo <task description>`

Example: `todo Buy groceries`

Expected Outcome: A to-do task will be added to the list, and Dave will confirm it by displaying the task.

```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 4 tasks in the list.
```

## Adding Events

For tasks that are tied to specific events with start and end times, you can use Dave's event feature.

Usage: To add an event, use the following command format:

Command Format: `event <task description> /at <date/time>`

Example: `event Attend meeting /at 2024-09-21 10:00`

Expected Outcome: An event will be added to the task list with a specified date and time, and Dave will confirm the addition.

```
Got it. I've added this task:
  [E][ ] Attend meeting (at: Sep 21 2024 10:00 AM)
Now you have 5 tasks in the list.
```
## Marking Tasks as Done

When a task is completed, you can mark it as done to keep your task list up-to-date.

Usage: To mark a task as done, use the following command format:

Command Format: `mark <task number>`

Example: `mark 1`

Expected Outcome: Dave will mark the specified task as done and update the task list accordingly.

```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

## Unmarking Tasks

If you need to unmark a task that was previously marked as done, you can easily undo this action.

Usage: To unmark a task, use the following command format:

Command Format: `unmark <task number>`

Example: `unmark 1`

Expected Outcome: Dave will unmark the specified task and return it to the list of pending tasks.

```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
```

## Deleting Tasks

If a task is no longer needed, you can delete it from the task list.

Usage: To delete a task, use the following command format:

Command Format: `delete <task number>`

Example: `delete 2`

Expected Outcome: The task will be removed from the list, and Dave will confirm the deletion by displaying the updated task list.

```
Noted. I've removed this task:
  [D][ ] Submit assignment (by: Sep 20 2024)
Now you have 4 tasks in the list.
```

## Finding Tasks

To quickly search for tasks containing a specific keyword, Dave offers a find command.

Usage: To find tasks, use the following command format:

Command Format: `find <keyword>`

Example: `find meeting`

Expected Outcome: Dave will display a list of tasks that contain the specified keyword.


```
Here are the matching tasks in your list:
  1. [E][ ] Attend meeting (at: Sep 21 2024 10:00 AM)
```

## Listing All Tasks

To view all tasks that are currently tracked by Dave, use the list command.

Usage: To display the full list of saved tasks, use the following command format:

Command Format: `list`

Example: `list`

Expected Outcome: Dave will display the full list of tasks, including their status (whether they are marked as done or pending).


```
Here are the tasks in your list:
  1. [T][ ] Buy groceries
  2. [E][ ] Attend meeting (at: Sep 21 2024 10:00 AM)
  3. [T][ ] Complete project report
```

## Exiting Dave

When you're done, you can exit the application by using the exit command.

Usage: To exit, use the following command format:

Command Format: `bye`

Example: `bye`

Expected Outcome: Dave will exit.


```
Bye. Hope to see you again soon!

```

## Adding Reminders

Dave allows users to receive reminders for either all undone tasks or overdue tasks. This feature helps ensure that you stay on top of your pending tasks.

Usage: To call a reminder, use one of the following command formats:

Command Format:

`reminder all` - Shows all undone tasks.

`reminder overdue` - Shows only overdue tasks.

Example: 

`reminder all`

`reminder overdue`

Expected Outcome: 

When you use the reminder all command, Dave will display a list of all tasks that are not yet completed. This list includes any tasks, to-dos, deadlines, or events that you haven’t marked as done, regardless of their due date. Dave will provide this list as a gentle reminder to keep track of all ongoing tasks.

When you use the reminder overdue command, Dave will display only those tasks whose deadlines have already passed but are still marked as incomplete. This allows you to prioritize tasks that need immediate attention. It’s a prompt to focus on any missed deadlines and take action before it’s too late.

For all undone tasks:

```
Hmmmm. Time is ticking. You still have the following tasks that haven't been completed:
  [D][ ] Submit assignment (by: Sep 20 2024)
  [T][ ] Buy groceries

```
For overdue undone tasks:
```
Can you get to work? You still have the following tasks that haven't been completed, and they're overdue:
  [D][ ] Submit assignment (by: Sep 15 2024)

```