# ProYapper User Guide

![Screenshot of ProYapper](Ui.png)

**Pro Yapper** is an intuitive and efficient chatbot designed to streamline task management and enhance productivity. Whether you’re keeping track of daily to-dos, managing deadlines, or planning events, Pro Yapper makes it easy to stay organized with its user-friendly interface. Powered by JavaFX, the chatbot combines smart task handling features with a responsive GUI, offering a seamless experience for users.

Pro Yapper allows you to add, delete, and mark tasks as complete, while also supporting filtering by priority. Its unique feature set includes the ability to assign priorities to tasks (low, medium, high) and displays tasks in a visually appealing format using emoji-based indicators. The bot ensures that users can quickly view their most urgent tasks at a glance, helping them focus on what matters most.

Designed for simplicity, Pro Yapper allows users to interact naturally with commands like `/todo`, `/deadline`, and `/event`. Each task is stored and retrieved seamlessly, even after closing the app, ensuring that your progress is never lost.

Whether you're a student managing assignments or a professional balancing multiple deadlines, **Pro Yapper** is the reliable virtual assistant that keeps you on track with just a few simple commands!


## Adding deadlines

Adding a deadline creates a task with a specified due date. The deadline is tracked, and Pro Yapper will display it in your list of tasks along with the due date.

Usage: `deadline <task description> /by <yyyy-MM-dd HHmm> /priority <priority level>`

Example: `deadline IP Submission /by 2024-09-20 2359 /priority high`

Outcome: Creates a task with a deadline and the specified priority. The task will be added to the list of tasks, and a confirmation message will be shown.

```
Got it. I've added this task:
🔴 📆 |  | IP Submission (by: Sep 20 2024, 11:59 PM)
Now you have 1 tasks in the list.
```

## Adding to-do items

A to-do task is a basic task with no specific time attached to it. It allows you to create general tasks that need to be completed.

Usage: `todo <task description> /priority <priority level>`

Example: `todo sleep /priority medium`

Outcome: Creates a general to-do task with the given description and priority.

```
Got it. I've added this task:
🟡 📋 |  | sleep
Now you have 2 tasks in the list.
```

## Adding events

An event task is used to represent tasks with a specific start and end time. It allows you to define a period for an event.

Usage: `event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> /priority <priority level>`

Example: `event Meeting with TP group /from 2024-09-19 1500 /to 2024-09-19 1700 /priority high`

Outcome: Creates an event task with a start and end time, as well as the priority level.

```
Got it. I've added this task:
🔴 🎉 |  | Meeting with team (from: Sep 19 2024, 3:00 PM to: Sep 19 2024, 5:00 PM)
Now you have 3 tasks in the list.
```

## Listing tasks

This command lists all tasks currently in your task list. It will show the task type, status (done/not done), description, and any relevant times (for deadlines and events).

Example: `list`

Outcome: Displays all the tasks currently in the task list.

```
Here are the tasks in your list:
1. 🔴 📆 |  | IP Submission (by: Sep 20 2024, 11:59 PM)
2. 🟡 📋 |  | sleep
3. 🔴 🎉 |  | Meeting with team (from: Sep 19 2024, 3:00 PM to: Sep 19 2024, 5:00 PM)
Now you have 3 tasks in the list.
```

## Filtering tasks by priority

This command filters tasks based on their priority (high, medium, low). You can view only the tasks of the specified priority level.

Usage: `priority <priority level>`

Example: `priority high`

Outcome: Lists all tasks with the specified priority.

```
Here are the matching tasks in your list:
1. 🔴 📆 |  | IP Submission (by: Sep 20 2024, 11:59 PM)
2. 🔴 🎉 |  | Meeting with team (from: Sep 19 2024, 3:00 PM to: Sep 19 2024, 5:00 PM)
```

## Find certain tasks

This command allows you to search for tasks by keyword. The bot will return a list of tasks whose descriptions contain the keyword.

Usage: 'find <keyword>'

Example: `find submission`

Outcome: Lists all tasks that match the keyword.

```
Here are the matching tasks in your list:
1. 🔴 📆 |  | IP Submission (by: Sep 20 2024, 11:59 PM)
```

## Mark task as done

Marks a task as completed. Once marked as done, the task will show a completed status (✔️).

Usage: `mark <task number>`

Example: `mark 2`

Outcome: Marks the task as done and updates the task list.

```
Nice! I've marked this task as done:
🟡 📋 |✔| sleep
```

## Mark task as not done

Marks a task as not completed. This can be used to revert the status of a task previously marked as done.

Usage: `unmark <task number>`

Example: `unmark 2`

Outcome: Marks the task as not done and updates the task list.

```
OK, I've marked this task as not done yet:
🟡 📋 |  | sleep
```

## Delete task

Deleting a task removes it from your list of tasks. Once deleted, the task is permanently removed and cannot be retrieved.

Usage: `delete <task number>`

Example: `delete 2`

Outcome: The specified task is removed from the task list, and a confirmation message is displayed.

```
Noted. I've removed this task:
  🟡 📋 |  | sleep
  
Now you have 2 tasks in the list.
```