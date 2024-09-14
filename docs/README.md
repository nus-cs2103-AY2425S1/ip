# Gravitas User Guide

![Ui.png](Ui.png)

## View All Tasks

You can view all tasks in the list.

Format of command: `list`

Usage: `list`

Expected Output:
```
Here are the tasks in your list:
    1. [T][X] borrow book
    2. [D][ ] return book (by: Feb 12 2024 1200 am)
    3. [E][ ] Badminton (from: Jan 1 2024 1200 am to: Jan 1 2024 1159 pm)
```

## Add New Tasks
You can add new tasks to the list. There are 3 types of tasks: Todo, Deadline, Event.
_Note that the date and time must be in the format `yyyy-MM-dd HHmm`_.

Format of command:
1. Todo: `todo <description>`
2. Deadline: `deadline <description> /by <DEADLINE_DATE> <DEADLINE_TIME>`
3. Event: `event <description> /from <START_DATE> <START_TIME> /to <END_DATE> <END_TIME>`

Usage:
1. Todo: `todo borrow book`
2. Deadline: `deadline return book /by 12/02/2024 0000`
3. Event: `event Badminton /from 12/02/2024 0000 /to 12/02/2024 2359`


Expected Output:
```
Got it. I've added this task:
    [T][ ] borrow book
Now you have 1 tasks in the list.
```
```
Got it. I've added this task:
    [D][ ] return book (by: Feb 12 2024 1200 am)
Now you have 2 tasks in the list.
```
```
Got it. I've added this task:
    [E][ ] Badminton (from: Jan 1 2024 1200 am to: Jan 1 2024 1159 pm)
Now you have 3 tasks in the list.
```

## Help
Gravitas provides a help command to guide you on the commands available.

Format of command: `help`

Usage: `help`

Expected Output:
```
Here are the list of commands you can use:
    1. todo <task>: Adds a todo task to the task list.
    2. deadline <task> /by <date> <time>: Adds a deadline task to the task list.
    3. event <task> /from <start_date> <start_time> /to <end_date> <end_time>: Adds an event task to the task list.
    4. list: Displays the list of tasks in the task list.
    5. mark <task_number>: Marks the task as done.
    6. unmark <task_number>: Marks the task as not done.
    7. delete <task_number>: Deletes the task from the task list.
    8. find <keyword>: Finds tasks with the keyword in the task list.
    9. help: Displays the list of commands available.
    10. bye: Exits the Program.
    
    Note: <date> must be in the format dd/MM/yyyy and <time> in 24-hour format.
```

## Mark/Unmark Task
You can mark a task as done or unmark a task as not done.

Format of command:
1. Mark: `mark <task_number>`
2. Unmark: `unmark <task_number>`

Usage:
1. Mark: `mark 1`
2. Unmark: `unmark 1`

Expected Output:
```
Nice! I've marked this task as done:
    [T][X] borrow book
```
```
Nice! I've marked this task as not done:
    [T][ ] borrow book
```

## Delete Task
You can delete a task from the list.

Format of command: `delete <task_number>`

Usage: `delete 1`

Expected Output:
```
Noted. I've removed this task:
    [T][X] borrow book
```

## Find Task
You can find tasks with a keyword in the list.

Format of command: `find <keyword>`

Usage: `find book`

Expected Output:
```
Here are the matching tasks in your list:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: Feb 12 2024 1200 am)
```
_Note: empty keyword field will return all tasks._

## Save and Load Tasks
Gravitas automatically saves your tasks to a file and loads them when you start the program.

## Exit Program
You can exit the program by typing `bye`.

Format of command: `bye`

Usage: `bye`

_Note: The program will close immediately after typing `bye`._