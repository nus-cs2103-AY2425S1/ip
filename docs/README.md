# Jade User Guide

![Product Screenshot](/images/Ui.png)

Welcome to **Jade**, a task management application designed to help you keep track of your tasks efficiently. 
You can add, list, mark, delete, and find tasks, as well as sort them based on different criteria.


## Adding a Todo task

To add a todo task, use the following command: `todo <task>`.

Example: `todo read book`
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

## Adding a Deadline task

To add a deadline task, use the following command: `deadline <task> /by <time>`.
The format for time is as follows: `yyyy-MM-dd HHmm`.

Example: `deadline return book /by 2024-09-30 1600`
```
Got it. I've added this task:
  [D][ ] return book (by: 4:00PM 30 Sep 2024)
Now you have 2 tasks in the list.
```

## Adding an Event task

To add an event task, use the following command: `event <task> /from <time> /to <time>`.
The format for time is as follows: `yyyy-MM-dd HHmm`.

Example: `event project meeting /from 2024-09-25 1100 /to 2024-09-25 1230`
```
Got it. I've added this task:
  [E][ ] project meeting (from: 11:00AM 25 Sep 2024 to: 12:30PM 25 Sep 2024)
Now you have 3 tasks in the list.
```

## Listing all tasks
To list all the tasks, use the following command: `list`.

Example:
```
Here are the task(s) in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 4:00PM 30 Sep 2024)
3. [E][ ] project meeting (from: 11:00AM 25 Sep 2024 to: 12:30PM 25 Sep 2024)
```

## Marking a task as done
To mark a task as done, use the following command: `mark <index>`.

Example: `mark 1`
```
Nice! I've marked this task as done:
  [T][X] read book
```

## Marking a task as not done yet
To mark a task as not done yet, use the following command: `unmark <index>`.

Example: `unmark 1`
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

## Deleting a task
To delete a task, use the following command: `delete <index>`.

Example: `delete 3`
```
Noted. I've removed this task:
  [E][ ] project meeting (from: 11:00AM 25 Sep 2024 to: 12:30PM 25 Sep 2024)
Now you have 2 tasks in the list.
```

## Finding a task
To find a task, use the following command: `find <keyword>`.

Example: `find book`
```
Here are the matching task(s) in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 4:00PM 30 Sep 2024)
```

## Sorting the tasks
To sort your tasks, use the following command: `sort by <type>`
Types of sort includes: `alphabet`, `task type`, `deadline`, `event`.

Example: `sort by alphabet`
```
Here are the sorted task(s) in your list:
1. [D][X] assignment 1 (by: 1:00PM 16 Sep 2024)
2. [T][ ] clean room
3. [D][ ] iP submission (by: 11:59PM 20 Sep 2024)
4. [E][ ] midterm (from: 4:15PM 10 Oct 2024 to: 5:15PM 10 Oct 2024)
5. [E][ ] volunteer (from: 2:00PM 18 Sep 2024 to: 5:00PM 18 Sep 2024)
```

Example: `sort by event`
```
Here are the sorted task(s) in your list:
1. [E][ ] volunteer (from: 2:00PM 18 Sep 2024 to: 5:00PM 18 Sep 2024)
2. [E][ ] midterm (from: 4:15PM 10 Oct 2024 to: 5:15PM 10 Oct 2024)
3. [D][X] assignment 1 (by: 1:00PM 16 Sep 2024)
4. [T][ ] clean room
5. [D][ ] iP submission (by: 11:59PM 20 Sep 2024)
```

Example: `sort by task type`
```
Here are the sorted task(s) in your list:
1. [T][ ] clean room
2. [D][X] assignment 1 (by: 1:00PM 16 Sep 2024)
3. [D][ ] iP submission (by: 11:59PM 20 Sep 2024)
4. [E][ ] volunteer (from: 2:00PM 18 Sep 2024 to: 5:00PM 18 Sep 2024)
5. [E][ ] midterm (from: 4:15PM 10 Oct 2024 to: 5:15PM 10 Oct 2024)
```

## Exiting the application
To exit the application, use the following command: `bye`.
