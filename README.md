# Meeks Chatbot

This is a project of a chatbot. It's named after the a pokemon Mudkip. Given below are instructions on how to use it.

# Meeks User Guide

Meeks allows you to focus on the things that are in front of you. It's,

- text-based
- easy to pick up
- extremely fast to use!

### Quickstart
1. Ensure that you have Java 17 installed on your local machine.
2. Download the jar file from [here](../build/libs/meeks.jar)
3. Double click to run it! It is as simple as 1, 2 3!

Below are the commands Meeks understands! Refer to the summary table at the bottom!


## Adding tasks

Adds a task to the chatbot.

Example: `todo CS2103T IP`

This will create a task with the description `CS2103T IP`.

Expected Output:
```
Got it. I've added this task:
T | 0 | CS2103T IP 
Now you have X tasks in the list.
```

## Adding tasks with deadlines

Adds a task with a deadline to the chatbot.

Example: `deadline CS2103T IP /by 2024-09-20 2359`

This will create a deadline task with the description `CS2103T IP` and a deadline `Sep 20 2024 11:59 PM`.

Expected Output:
```
Got it. I've added this task:
D | 0 | CS2103T IP | Sep 20 2024 11:59 PM
Now you have X tasks in the list.
```

## Adding events

Adds a task with a start and end time to the chatbot.

Example: `event CS2103T IP /from 2024-08-15 0000 /to 2024-09-20 2359`

This will create an event task with description `CS2103T IP`, start time `2024-08-15 0000` and end time `2024-09-20 2359`

Expected output:
```
Got it! I've added this task:
E | 0 | CS2103T IP | from Aug 15 2024 12:00 AM to Sep 20 2024 11:59 PM
Now you have X tasks in the list.
```

## Deleting Task

Deletes the task from the list of tasks given by the index.

Example: `delete 1`

This will delete task number 1 from the list of tasks. Note that you can only delete items from 1 to the last item.

Expected output:
```
Noted. I've removed this task:
<Task details>
Now you have X tasks in the list.
```


## Filter Tasks

Filters the task list based on the given input.

Example: `find books`

If there are no tasks with description that contains `books` you will see the message
```No items in the list yet!```

Otherwise, you will see a list of tasks with description matching `books`

Example output:
```
Here are the tasks in your list: 

1. T | 0 | borrow books
2. D | 0 | return books | Sep 20 2024 6:00 PM
```

## Viewing list of tasks

Shows the list of tasks and whether they are completed or not. Ordered by when they were added to the list.

Example: `list`

Prints the tasks in the list.

Example output:
```
Here are the tasks in your list: 

1. T | 0 | borrow books
2. D | 1 | return books | Sep 20 2024 6:00 PM
3. E | 0 | intern | from May 15 2024 12:00 AM to Aug 10 2024 12:00 AM
```

## Marking a task as completed

Marks the task as done.

Example: `mark 1`

Marks the first task in the list as completed.

Example list:
```
1. T | 0 | return books
```
Expected output:
```
Nice I've marked this task as done:
T | 1 | return books
```

## Marking a task as uncompleted

Unmark the task as done.

Example: `unmark 2`

Unmarks the second task in the list.

Example list:
```
1. T | 0 | return books
2. D | 1 | party | Sep 20 2024 6:00 PM
```
Expected output:
```
OK, I've marked this task as not done yet:
D | 0 | party | Sep 20 2024 6:00 PM
```

## Update task

Update a specific field of the task.

Example: `update 1 description go to church`

Updates the description of the first task in the list to `go to church`.

Example list:
```
1. T | 0 | return books
2. D | 1 | party | Sep 20 2024 6:00 PM
```

New list:
```
1. T | 0 | go to church
2. D | 1 | party | Sep 20 2024 6:00 PM
```

Expected output: `Successfully updated task 1`

## Save

Saves the current list to the database.

Example: `save`

Ensures that any changes to the list is saved and updated to the database.

## Close the application

Make sure the list is saved before you close the application

Example: `bye`

## Command Summary

| Action        | Format                                                            | Examples                                                     |
|---------------|:------------------------------------------------------------------|--------------------------------------------------------------|
| Add Task      | `todo description`                                                | `todo CS2103T IP`                                            |
| Deadline Task | `deadline DESCRIPTION /by [YYYY-MM-DD HHMM]`                      | `deadline CS2103T IP /by 2024-09-20 2359`                    |
| Event Task    | `event DESCRIPTION /from [YYYY-MM-DD HHMM] /to [YYYY-MM-DD HHMM]` | `event CS2103T IP /from 2024-08-15 0000 /to 2024-09-20 2359` |
| Delete Task   | `delete INDEX`                                                    | `delete 1`                                                   |
| Find          | `find KEYWORD`                                                    | `find books`                                                 |
| List          | `list`                                                            | `list`                                                       |
| Mark          | `mark INDEX`                                                      | `mark 1`                                                     |
| Unmark        | `unmark INDEX`                                                    | `unmark 2`                                                   |
| Update        | `update INDEX FIELD NEWVALUE`                                     | `update 1 description go to church`                          |
| Save          | `save`                                                            | `save`                                                       |
| Exit          | `bye`                                                             | `bye`                                                        |
    

