# Duck User Guide

![Product Screenshot](./Ui.png)

Duck is a simple task management application that helps you keep track of your tasks effortlessly.

## Adding a Todo Task

To add a todo task, use the command:

```
todo <description>
```

This action adds a task without a specific deadline.

### Example: `todo CS2100 quiz`

**Expected Outcome:**

```
Got it. I've added this task: [T][ ] CS2100 quiz
```

## Adding deadlines

To add a deadline task, use the command:

```
deadline <description> /by <yyyy-MM-dd>
```
This action allows you to add a task with a specific deadline.

Example: `deadline finish ip /by 2024-09-21`

Expected outcome:
```
Got it. I've added this task: [D][] Finish project report (by: Sep 21 2024)
```

## Adding an Event Task

To add an event task, use the command:

```
event <description> /from <date/time> /to <date/time>
```
This action adds a task with a start and end date.

Example: `event hackathon /from Sep 15 2024 6pm /to 10pm`

Expected outcome:
```
Got it. I've added this task: [E][] hackathon (from: Sep 15 2024 6pm to: 10pm)
```


## Listing All Tasks

To view all your tasks, use the command:

```
list
```
This action displays all tasks currently in your task list.

Example: `list`

Expected outcome:
```
Here are your tasks:
1. [D][] Finish ip (by: Sep 20 2024)
2. [T][] CS2100 quiz
3. [E][] hackathon (from: Sep 15 2024 6pm to: 10pm)
```

## Marking a Task as Done

To mark a task as completed, use the command:

```
done <task_number>
```
This action marks the specified task as done.

Example: `done 1`

Expected outcome:
```
Nice! I've marked this task as done: [D][X] Finish ip (by: Sep 20 2024)
```

## Deleting a Task

To delete a task, use the command:

```
delete <task_number>
```
This action removes the specified task from your task list.

Example: `delete 2`

Expected outcome:
```
Noted. I've removed this task: [T][] do chores
```

## Finding Tasks by Keyword

To search for tasks containing a specific keyword, use the command:

```
find <keyword>
```
This action searches for tasks that include the keyword in their descriptions.

Example: `find CS2100`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] CS2100 homework
2. [E][] CS2100 exam (from: Sep 19 2024 2pm to: 3pm)
```

## Reminder

To display deadlines due within 3 days, use the command:

```
remind
```
This action shows all upcoming deadlines within the next 3 days.

Example: `remind`

Expected outcome:
```
Here are your upcoming deadlines:
1. [D][] Finish project report (by: Sep 22 2024)
```

## Exiting the Application

To exit the application, use the command:

```
bye
```
This action exits the program.

Example: `bye`

Expected outcome:
```
Bye! Hope to see you again soon!
```