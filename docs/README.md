# Fanny

![Screenshot of UI](Ui.png)

Fanny is a personalised task management chatbot.

Below is a list of supported commands:
```
Note:
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo DESCRIPTION, DESCRIPTION is a parameter which can be used as todo eat.
```

## Adding todo tasks

Create a todo task using the `todo` command.

Input: `todo DESCRIPTION`

Example: `todo Sleep`

Expected outcome:
```
Roger that. I've added this task:
[T][ ] Sleep
Now you have 1 tasks in the list.
```
## Adding deadlines

Create a task with deadline using the `deadline` command.

Input: `deadline DESCRIPTION /by YYYY-MM-DD HH:MM`

Example: `deadline Homework /by 2024-09-18 23:59`

Expected outcome:
```
Roger that. I've added this task:
[D][ ] Homework  (by: Sep 18, 2024 23:59)
Now you have 2 tasks in the list.
```
## Adding events

Create a event task using the `event` command.

Input: `event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM`

Example: `event Celebration /from 2024-09-20 20:00 /to 2024-09-20 23:00`

Expected outcome:
```
Roger that. I've added this task:
[E][ ] Celebration  (from: Sep 20, 2024 20:00 to: Sep 20, 2024 23:00)
Now you have 3 tasks in the list.
```
## Viewing all tasks

View all tasks using the `list` command.

Input: `list`

Example `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][ ] Sleep
2.[D][ ] Homework  (by: Sep 18, 2024 23:59)
3.[E][ ] Celebration  (from: Sep 20, 2024 20:00 to: Sep 20, 2024 23:00)
```
## Marking a task as completed

Mark a task as completed using the `mark` command.

Input: `mark INDEX`

Example: `mark 1`

Expected outcome:
```
Awesome! I've marked this task as done:
[T][X] Sleep
```
## Marking a task as not completed

Mark a task as not completed using the `unmark` command.

Input: `unmark INDEX`

Example: `unmark 1`

Expected outcome:
```
Sadly, I've marked this task as not done yet:
[T][ ] Sleep
```
## Deleting a task

Delete a task using the `delete` command.

Input: `delete INDEX`

Example: `delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][ ] Sleep
Now you have 2 tasks in the list.
```
## Finding a task

Find a task with a keyword using the `find` command.

Input: `find KEYWORD`

Example: `find Homework`

Expected outcome:
```
Fanny:
Here are the matching tasks in your list:
1.[D][ ] Homework (by: Sep 18, 2024 23:59)
```
If there are no matching tasks:
```
No matching tasks found.
```
## Reminding the user

Upon launching the app, Fanny will remind users of upcoming deadlines
(due within the next 24 hours).

Users can also use the `remind` command to get reminders.

Input: `remind`

Example: `remind`

Expected outcome:
```
Please complete these tasks soon:
[D][ ] Homework 1  (by: Sep 18, 2024 16:00)
```
If there are no deadlines due in the next 24 hours:
```
Congrats!!! You have no upcoming deadlines!
```
## Exiting the application

Exit the chatbot using the `bye` command.

Input: `bye`

Example: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```

### Sources:
```
Credits to Freepik for the avatar design.
Fanny: Avatar icon. Freepik. (n.d.-a). https://www.freepik.com/icon/avatar_168734
User: Avatar icon. Freepik. (n.d.-a). https://www.freepik.com/icon/avatar_168720 
``` 