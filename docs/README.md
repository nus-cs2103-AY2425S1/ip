# User Guide to QuirkBot - Your Friendly Assistant
// Update the title above to match the actual product name

// Product screenshot goes here
![Ui.png](Ui.png)

// Product intro goes here
Welcome to QuirkBot - Your Friendly Assistant. QuirkBot helps you
to efficiently manage all your tasks in a convenient location. 
QuirkBot ensures that task management becomes a breeze by
keeping track of all your tasks.

## Show list of commands
// Feature details
Shows all available commands to use.

Example: `command`
Enter the keyword `command` to access all available commands.

Expected Outcome:
`List of Keyword Commands:`

## Add deadline tasks
// Feature details
Helps you to add a deadline task.

Example: `deadline math assignment /by 22092024 2359`
Enter the keyword `deadline` followed by `task_description`.
After `task_description`, enter the deadline in the
following format `/by dd/MM/YYYY HHmm`.

Expected Outcome:
`Great! I've added this task: [D][] math assignment /by 22092024
You now have X tasks in your list.`
`X` represents the current number of tasks in your task list.

## Add todo tasks
// Feature details
Helps you to add a todo task.

Example: `todo python programming`
Enter the keyword `todo` followed by `task_description`.

Expected Outcome:
`Great! I've added this task: [T][] python programming
You now have X tasks in your list`
`X` represents the current number of tasks in your task list.

## Add event tasks
// Feature details
Helps you to add an event task.

Example: `event music concert /from 21/09/2024 1900 /to 21/09/2024 2200`
Enter the keyword `event` followed by `task_description`.
After `task_description`, enter start timing in the following 
format `/from dd/MM/YYYY HHmm` and end timing in the following 
format `/to dd/MM/YYYY HHmm`.

Expected Outcome:
`Great! I've added this task: [E][] music concert /from 21/09/2024 1900
/to 21/09/2024 2200. You now have X tasks in your list`
`X` represents the current number of tasks in your task list.

## Mark a task
// Feature details
Helps you to mark a task which you have already completed.

Example: `mark X`
Enter the keyword `mark` followed by the task number from the list

Expected Outcome:
`Hooray! I've marked this task as done`

## Unmark a task
// Feature details
Helps you to unmark a task which is still pending completion.

Example: `unmark X`
Enter the keyword `unmark` followed by the task number from the list

Expected Outcome:
`No worries! I've marked this task as not done yet`

## Delete a task
// Feature details
Helps you to delete a task which you have completed a long time ago.

Example: `delete X`
Enter the keyword `delete` followed by the task number from the list

Expected Outcome:
`Done! I've removed this task [T][X] eat lunch
Now you have X tasks left`
`X` represents the current number of tasks in your task list

## Find a task
// Feature details
Finds a task from your current task list using a specific keyword.

Example: `find python`
Enter the keyword `find` followed by the search keyword `python`

Expected Outcome:
`Here are the search results for you:
   [T][] python programming`

## Show Task List
// Feature details
Shows your current task list of all your tasks.

Example: `list`
Enter the keyword `list` for QuirkBot to present your
current task list.

Expected Outcome:
`Here are the fabulous tasks in your list:
    task 1
    task 2
    task 3
    so on`
task 1, task 2, task 3 and so on will be inside your
fabulous task list.

## Exit Program
// Feature details
Exits from the QuirkBot program.

Example: `bye`
Enter the keyword `bye` to exit from QuirkBot.

Expected Outcome:
`Farewell! I'll be here when you need me.`