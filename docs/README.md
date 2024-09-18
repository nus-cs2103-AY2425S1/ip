# Krona User Guide

![img.png](Ui.png)

Krona is a powerful task management chatbot designed to help you stay on top of your tasks efficiently. Whether you need to track daily to-dos, set deadlines, or manage events, Krona is here to make sure you never miss a beat. With its user-friendly commands and interactive interface, Krona provides seamless task management through an intuitive GUI. When you first start up Krona, use the `help` command to see all available commands
## Adding Tasks
Krona allows you to track Tasks that you have and have it readily available for you to view at any time. There are 3 different subtasks that you can add as shown below:
### ToDo Task
Add a ToDo task by specifying the task description as follows:

`todo [task description]`

Example:
```
todo buy stationery
```

Expected Outcome:

```
Got it! I've added this task:
[T][] buy stationery
Now you have 1 tasks in the list.
```
### Deadline Task
Add a task with a deadline by specifying both the task description and the deadline in a specific format:

`deadline [task description] /by [due date in d/M/yyyy HHmm format]`

Example: 
```
deadline return book /by 15/09/2024 1800
```

Expected Outcome:

```
Got it! I've added this task:
[D][] return book (by: Sep 15 2024, 6:00 pm)
Now you have 2 tasks in the list.
```
### Event Task
Add an Event with a start time and end time in the following format:

`event [task description] /from [date in d/M/yyyy HHmm] /to [date in d/M/yyyy HHmm]`

Example:
```
event spin class /from 20/09/2024 1800 /to 20/09/2024 2000
```

Expected Outcome:

```
Got it! I've added this task:
[E][] spin class (from: Sep 20 2024, 6:00 pm to: Sep 20 2024, 8:00 pm)
Now you have 3 tasks in the list.
```
## Listing Tasks
Use the `list` command to view all existing tasks

Sample list:
```
Here are the taks in your list:
1.[T][] buy stationery
2.[D][] return book (by: Sep 15 2024, 6:00 pm)
3.[E][] spin class (from: Sep 20 2024, 6:00 pm to: Sep 20 2024, 8:00 pm)
```


## Marking and Unmarking Tasks
The `mark` and `unmark` command can be used to mark tasks done or mark as not done. This is represented by the [] beside the task identifier [T], [D] and [E]. The format is mark/unmark followed by the task number in the list as shown:

`mark 1` and `unmark 1`

Expected Outcome based on the list above:
```
Nice! I've marked this task as done:
[T][X] buy stationery
```

```
OK, I've marked this task as not done yet:
[T][] buy stationery
```

## Deleting Tasks
Use `delete [task number]` to easily remove any tasks in your list.

Example from previous list:

```delete 1```

Expected outcome:

```
Noted. I've removed this task:
[T][] buy stationery
Now you have 2 tasks in the list.
```

## Finding Tasks
Easily find tasks using keywords using the command `find [keyword]` and Krona will display all the tasks that contain the keyword.

Example:
```
find book
```

Expected Outcome:
```
Here are the matching tasks in your list:
1.[D][] return book (by: Sep 15 2024, 6:00 pm)
```

## Exiting Krona
Use the command `bye` to exit the chatbot. Thank you for using Krona!