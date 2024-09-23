# Kafka User Guide

![Ui](https://github.com/user-attachments/assets/bf6a41fe-f86b-486c-9a75-bfea9c576cbc)




Kafka is a simple, interactive chatbot with personality designed to help users manage tasks. With Kafka, users can create, manage, and track their tasks, set deadlines, mark tasks as completed, and more. It provides an intuitive command-based interface that helps you keep track of your to-do list efficiently.





## Adding deadlines



Kafka allows users to add tasks with deadlines, helping them stay on top of their important events.



Usage: To add a deadline, use the following command format:



Command Format: `deadline <task description> /by <date/time>`



Example: `deadline submit assignment /by 2024-09-20 1800`



Expected Outcome: The task with a deadline will be added to the list, and Kafka will confirm the addition by displaying the task and its corresponding deadline.



```

Got it. I've added this task:

  [D][ ] submit assignment (by: September 20 2024 1800)

Now you have 1 task(s) in the list.

```



## Adding To-Dos



To-dos are basic tasks without any specific deadline. Dave allows users to add and manage these tasks.



Usage: To add a to-do, use the following command format:



Command Format: `todo <task description>`



Example: `todo read book`



Expected Outcome: A to-do task will be added to the list, and Kafka will confirm it by displaying the task.



```

Got it. I've added this task:

  [T][ ] read book

Now you have 4 task(s) in the list.

```



## Adding Events



For tasks that are tied to specific events with start and end times, you can use Kafka's event feature.



Usage: To add an event, use the following command format:



Command Format: `event <task description> /at <date/time>`



Example: `event attend meeting /from 2024-09-21 1000 /to 2024-09-21 1200`



Expected Outcome: An event will be added to the task list with a specified date and time, and Kafka will confirm the addition.



```

Got it. I've added this task:

  [E][ ] Attend meeting (from: September 21 2024 1000 to: September 21 2024 1200)

Now you have 5 task(s) in the list.

```

## Marking Tasks as Done



When a task is completed, you can mark it as done to keep your task list up-to-date.



Usage: To mark a task as done, use the following command format:



Command Format: `mark <task number>`



Example: `mark 1`



Expected Outcome: Kafka will mark the specified task as done and update the task list accordingly.



```

Good work on this task. Want a prize?:

  [T][X] buy groceries

```



## Unmarking Tasks



If you need to unmark a task that was previously marked as done, you can easily undo this action.



Usage: To unmark a task, use the following command format:



Command Format: `unmark <task number>`



Example: `unmark 1`



Expected Outcome: Kafka will unmark the specified task and return it to the list of pending tasks.



```

Hurry up. This task is necessary for Elio's script:

  [T][ ] buy groceries

```



## Deleting Tasks



If a task is no longer needed, you can delete it from the task list.



Usage: To delete a task, use the following command format:



Command Format: `delete <task number>`



Example: `delete 2`



Expected Outcome: The task will be removed from the list, and Kafka will confirm the deletion by displaying the updated task list.



```

I've removed this task:

  [D][ ] Submit assignment (by: September 20 2024 1800)

Now you have 4 task(s) in the list.

```



## Finding Tasks



To quickly search for tasks containing a specific keyword, Kafka offers a find command.



Usage: To find tasks, use the following command format:



Command Format: `find <keyword>`



Example: `find meeting`



Expected Outcome: Kafka will display a list of tasks that contain the specified keyword.





```

Here you go, these are the matching tasks in your list:

  1.[D][ ] submit assignment (by: September 21 2024 1000)

```



## Listing All Tasks



To view all tasks that are currently tracked by Kafka, use the list command.



Usage: To display the full list of saved tasks, use the following command format:



Command Format: `list`



Example: `list`



Expected Outcome: Kafka will display the full list of tasks, including their status (whether they are marked as done or pending).





```

Here are the tasks in your list:

  1. [T][ ] Buy groceries

  2. [D][ ] Submit assignment (by: September 21 2024 1000)

  3. [T][ ] Complete project report

```









## Snooze tasks



Kafka allows users to snooze tasks by changing their deadline and event dates.


Usage: To snooze a task, use one of the following command formats:



Command Format:



`snooze <taskNumber> /by <Datetime>` - Snooze deadline tasks.



`snooze <taskNumber> /from <Datetime>` - Snooze event tasks and the duration stays the same.



`snooze <taskNumber> /from <Datetime> /to <Datetime>` - Snooze event tasks and the date and time can be freely changed.



Example:



`snooze 3 /by 2024-01-01 1800`



`snooze 3 /from 2024-01-01 1800`



`snooze 3 /from 2024-01-01 1800 /to 2024-01-01 2000`



Expected Outcome:



When you use the snooze deadline command, Kafka is able to freely change the deadline task date and time. 


When you use the snooze event command with /from, Kafka is able to freely change the event task date and time, however the /to date and time is automatically updated with the duration staying the same.


When you use the snooze deadline command with /from and /to, Kafka is able to freely change the event task date and time.



For tasks:



```

Oh, the task is delayed? Do you need any help?

  [D][ ] submit assignment (by: January 01 2024 1800)

```
