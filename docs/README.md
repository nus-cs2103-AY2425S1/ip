# Fred User Guide

<img width="402" alt="Ui" src="https://github.com/user-attachments/assets/264e72c3-59dc-495c-b229-294d637741c5">
// Product screenshot goes here

Fred is a chatbot that helps you to:
- Add and delete tasks
- Mark tasks as done or not done
- Find tasks based on their descriptions
- Add tags to your tasks

# Features
> [!TIP]
> When inputting dates and times, the date and time should be in the form yyyy-MM-dd HH:mm.
> For example, to input the date and time 1st January 2024 8am, the input should be 2024-01-01 08:00.

## Add a todo task

Adds a todo task to the list of tasks.
A todo task is a task that only has a description.

Example: `todo return book`

```
Got it. I've added this task:
  [T][] return book
Now you have 2 tasks in the list.
```

## Add a deadline task
Adds a deadline task to the list of tasks.
Apart from a description, a deadline task also has an end time.

Example: `deadline return book /by 2024-01-01 12:00`

```
Got it. I've added this task:
   [D][ ] return book  (by: Jan 01 2024 12:00)
Now you have 6 tasks in the list.
```


## Add an event task

Adds an event task to the list of tasks.
Apart from a description, an event task has a start and end time.

Example: `event orientation week /from 2024-02-02 13:00 /to 2024-02-03 14:20`

```
Got it. I've added this task:
   [E][ ] orientation week  (from: Feb 02 2024 13:00 to: Feb 03 2024 14:20)
Now you have 7 tasks in the list.
```

## List all tasks

Shows all tasks in the list of tasks.

Format: `list`

## Delete a task

Deletes a task to the list of tasks.

Example: `delete 1`

```
Noted. I've removed this task:
   [T][X] read book 
```

## Find tasks that contain keywords

Finds all tasks whose description contain the specified keywords.

Example: `find book`

```
Here are the matching tasks in your list:
[D][ ] return book  (by: Jan 01 2024 12:00)
[T][ ] return book 
```

## Tag a task

Add a tag to a task.

Example: `tag 1 urgent`

```
OK, I've added the tag to this task:
   [D][ ] return book (urgent) (by: Jan 01 2024 12:00)
```

## Mark a task as done

Marks a task as done.

Example: `mark 3`

```
Nice! I've marked this task as done:
   [T][X] return book 
```

## Mark a task as not done

Marks a task as not done.

Example: `unmark 3`

```
OK, I've marked this task as not done yet:
   [T][ ] return book 
```

## Close the chatbot

Closes the chatbot.

Format: `bye`
