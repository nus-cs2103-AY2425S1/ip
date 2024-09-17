# Henry User Guide

![Screenshot of the app when user adds in a deadline, enters wrong input and marks the task](Ui.png)

## Introduction
Henry is a personal assistant chatbot designed to help you manage and track tasks efficiently. It supports task input, deadlines, events, and even task searches. Henry helps you stay organized by providing simple commands to mark, unmark, delete, and find tasks.

## Adding Todos
The `todo` keyword allows you to add general tasks that don't have a specific date or time associated with them.

### Example:
`todo read book`

### Expected Output:
```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the list.
```

## Adding Deadlines
Use the `deadline` keyword to input a task that has a deadline. You will have to include a `/` before writing the deadline. The deadline should be written in the following format: **YYYY-MM-DD HHmm**

### Example:
`deadline return book /by 2019-12-01 1900`

### Expected Output:
```
Got it. I've added this task:
[D][ ] return book (by: Dec 01 2019 07.00 PM)
Now you have 2 tasks in the list.
```

## Adding Events
The `event` keyword allows you to schedule events with a start and end time. You will have to include a `/` before the start and end time.

### Example:
`event project meeting /from Mon 2pm /to 4pm`

### Expected Output:
```
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
```

## Feature: Viewing All Tasks
Henry can show you all the tasks you’ve added so far.

### Example:
`list`

### Expected Output:
```
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Dec 01 2019 07.00 PM)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

## Feature: Marking Tasks
Marking a task tells Henry that it has been completed. This is done by stating the task index.

### Example:
`mark 2`

### Expected Output:
```
Nice! I've marked this task as done:
[D][X] return book (by: Dec 01 2019 07.00 PM)
```

## Feature: Unmarking Tasks
Unmarking a task indicates that it is no longer completed. This is done by stating the task index.

### Example:
`unmark 2`

### Expected Output:
```
OK, I've marked this task as not done yet:
[D][ ] return book (by: Dec 01 2019 07.00 PM)
```

## Feature: Finding Tasks By Keyword
You can use the `find` feature to find specific tasks by searching for keywords.

### Example:
`find book`

### Expected Output:
```
Here are the matching tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Dec 01 2019 07.00 PM)
```

## Feature: Deleting Tasks
You can delete tasks that are no longer relevant by stating the task index or `all` to delete all the tasks.

### Example 1:
`delete 2`

### Expected Output:
```
Noted. I've removed this task:
[D][ ] return book (by: Dec 01 2019 07.00 PM)
Now you have 2 tasks in the list.
```

### Example 2:
`delete all`

### Expected Output:
```
Noted. I've removed all the tasks:
Now you have 0 task in the list.
```

## Feature: Exiting the Program
Typing `bye` will close the Henry application after a short delay.

### Example:
`bye`

### Expected Output:
```
Bye. Hope to see you again soon!
```
