Neko User Guide
---
### Neko - Your Friendly Task Management Cat Chatbot
Neko is a cute, friendly task management chatbot designed to help you manage your daily tasks efficiently. Neko can add, delete, mark tasks as done, and even list all tasks in your schedule. Whether you're organizing deadlines, events, or simple to-do lists, Neko is here to help you keep everything on track with ease.
## Features
### 1. Adding To-do Tasks
You can add simple to-do tasks to your task list using the `add todo` command. This is perfect for quick tasks that don't have specific deadlines.
- Command format:
```
add todo [description]
```
- Example input:
```
add todo Buy groceries
```
- Expected output:
```
Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ
[T][ ] Buy groceries
```

### 2. Adding Deadlines
   You can add tasks with a specific deadline using the add deadline command. This feature allows you to keep track of tasks that must be completed by a certain date and time.

- Command format: 
```
add deadline [description] /by [yyyyMMddTHH:mm]
```
- Example input:
```
add deadline Submit report /by 20240930T18:00
```
- Expected output:
```
Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ
  [D][ ] Submit report (by: Mon, 30 Sep 2024 6:00pm)
```

### 3. Adding Events
   Neko can also help manage events that span over a period of time. Use the add event command to log events that have both a start and end date/time.
- Command format:
```
add event [description] /from [yyyyMMddTHH:mm] /to [yyyyMMddTHH:mm]
```
- Example input:
```
add event CS2103T project presentation /from 20240929T14:00 /to 20240929T16:00
```
- Expected output:
```
Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ
[E][ ] CS2103T project presentation (from: Sun, 29 Sep 2024 2:00pm to: Sun, 29 Sep 2024 4:00pm)
```

### 4. Listing Tasks
   You can easily view all your tasks by using the list command. Neko will show you all the tasks you have added, whether they are to-do items, deadlines, or events.
- Command:
```
list
```
- Expected output:
```
Here are the tasks in your list meow:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: Mon, 30 Sep 2024 6:00pm)
3. [E][ ] CS2103T project presentation (from: Sun, 29 Sep 2024 2:00pm to: Sun, 29 Sep 2024 4:00pm)
```

### 5. Marking Tasks as Done
Neko allows you to mark tasks as done once you have completed them. You can also mark multiple tasks at once by providing their indices separated by spaces.
- Command format:
```
mark [task number 1] [task number 2] [task number 3] ...
```
- Example input:
```
mark 1 2
```
- Expected output:
```
Nice meow! I've marked these tasks as done:
[T][X] Buy groceries
[D][X] Submit report (by: Mon, 30 Sep 2024 6:00pm)
```

### 6. Unmarking Tasks
   If you mistakenly mark a task as done, you can easily unmark it.

- Command format:
```
unmark [task number 1] [task number 2] [task number 3] ...
```
Example input:
```
unmark 1 2
```
- Expected output:
```
Ok meow, I've marked these tasks as not done yet:
[T][ ] Buy groceries
[D][ ] Submit report (by: Mon, 30 Sep 2024 6:00pm)
```

### 7. Deleting Tasks
If you no longer need a task, you can delete it using the delete command. You can also delete multiple tasks at once by providing their indices separated by spaces.

- Command format:
```
delete [task number 1] [task number 2] [task number 3] ...
```
- Example input:
```
delete 1 2 3
```
- Expected output:
```
Noted meow. I've removed the tasks:
[T][ ] Buy groceries
[D][ ] Submit report (by: Mon, 30 Sep 2024 6:00pm)
```

### 8. Finding Tasks
   Need to find a task quickly? Use the find command to search for tasks based on keywords.

- Command format:
```
find [keyword]
```
- Example input:
```
find presentation
```
- Expected output:
```
Here are the matching tasks in your list meow:
1. [E][ ] CS2103T project presentation (from: Sun, 29 Sep 2024 2:00pm to: Sun, 29 Sep 2024 4:00pm)
```

### 9. Viewing Tasks on a Specific Date
   You can view all tasks scheduled on a specific date using the view [date] command.
- Command format:
```
view [yyyyMMdd]
```
- Example input:
```
view 20240930
```
- Expected output:
```
Meow! Here is your schedule on Mon, 30 Sep 2024:
[D][ ] Submit report (by: Mon, 30 Sep 2024 6:00pm)
```

### 10. Help Command
If you're ever lost and need to know what commands Neko can handle, just type help.
- Command:
```
help
```
- Expected output:

```
Here are the available commands meow!

1. list - Displays all the tasks in the list
2. add todo [description] - Adds a new Todo task
3. add deadline [description] /by [deadline] - Adds a new Deadline task
4. add event [description] /from [start time] /to [end time] - Adds a new Event task
5. mark [task number] - Marks the task at the given index as done
6. unmark [task number] - Marks the task at the given index as not done
7. delete [task number] - Deletes the task at the given index
8. find [keyword] - Finds tasks that match the given keyword
9. view [date] - Views the tasks scheduled on the given date
10. help - Shows this list of commands
```

### 11. Exit Command 
To exit the Neko chatbot, use the bye command. This will stop the chatbot and close the application.
- Command:
```
bye
```
- Expected output:
```
Bye! Hope to see you again soon meow ฅ ฅ
```
---
## Summary

| Command                                | Description                                                             |
|----------------------------------------|-------------------------------------------------------------------------|
| `add todo [description]`               | Adds a new To-do task to the list                                        |
| `add deadline [description] /by [yyyyMMddTHH:mm]` | Adds a new Deadline task with a specific date and time              |
| `add event [description] /from [yyyyMMddTHH:mm] /to [yyyyMMddTHH:mm]` | Adds a new Event task with a start and end date/time          |
| `list`                                 | Displays all the tasks in the list                                       |
| `mark [task number]`                   | Marks a task as done                                                     |
| `unmark [task number]`                 | Marks a task as not done                                                 |
| `delete [task number]`                 | Deletes a task from the list                                             |
| `find [keyword]`                       | Finds tasks that match the given keyword                                 |
| `view [yyyyMMdd]`                      | Displays tasks scheduled on the given date                               |
| `help`                                 | Displays the list of available commands                                  |
| `bye`                                 | Exit the Neko chatbot                                                    |


## Conclusion
Neko is designed to make task management as easy and fun as possible with its interactive and intuitive command-line interface. Whether you're managing deadlines or long events, Neko has you covered with just a few simple commands. Keep your tasks organized and let Neko handle the details.