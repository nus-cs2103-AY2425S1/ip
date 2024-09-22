# Spongebob User Guide

![alt text](Ui.png "Spongebob ui")

Spongebob is a chatbot for managing your tasks,
optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Spongebob can track your tasks faster than 
traditional GUI apps.
---
## Setup Guide

1. Download a version of Spongebob [here.](https://github.com/Jaynon/ip/releases)

2. The jar file should be in a folder (ie folder_name/spongebob.jar)

3. Then, run the included jar file in any terminal with the line below.
```cmd
java -jar spongebob.jar
```

---

---
## Adding Todos

Adds a Todo task to your list, such as watching a lecture video.

Example: `todo watch CS2103T lecture`

The todo task 'watch CS2103T lecture' will be added to the task list.

```
Got it! I've added this task to your list - keep up the great work!

[T][] watch CS2103T lecture

Now you have 1 in the list! 
```
---
## Adding Deadlines

Adds a deadline task to your list, such as an assignment due on 13 October

Example: `deadline CS103T Assignment /by 13/10/2024`

A deadline task 'CS103T Assignment' with a due date of 13 October 
will be added to the task list

```
Got it! I've added this task to your list - keep up the great work!

[D][] CS103T Assignment (By: 13 Oct 2024)

Now you have 2 in the list! 
```

---

## Adding Events

Adds an Event task to your list, such as a party from 13 October to 14 October

Example: `event party at my house /from 13/10/2024 /to 14/10/2024`

An event task 'party at my house' starting from 
13 October 2024 to 14 October 2024 will be added to the task list

```
Got it! I've added this task to your list - keep up the great work!

[E][] party at my house (From: 13 Oct 2024 To: 14 Oct 2024)

Now you have 3 in the list! 
```

---

## Display task list

Display current tasks to the user

Example: `list`

A list of tasks will be displayed

```
Alrighty, buddy! Here are the tasks in your list!

1.[T][] watch CS2103T lecture
2.[D][] CS103T Assignment (By: 13 Oct 2024)
3.[E][] party at my house (From: 13 Oct 2024 To: 14 Oct 2024)

```

---
## Marking Tasks as Done

Marks a task via index as shown in the list as done. 

Example: `mark 3`

The task at index 3 will be marked as done.

```
Woohoo! I've marked this task as done - great Job!

[E][X] party at my house (From: 13 Oct 2024 To: 14 Oct 2024)
```

---
## Unmark a Task as Done

Unmarks a task via index as shown in the list 
by marking it as not done.

Example: `unmark 1`

The task at index 1 will be marked as not done.

```
Alrighty, I've put the task back to 'not done yet' Keep at it-you've got this!

[T][] watch CS2103T lecture
```

---

## Delete a task

Deletes a task via index as shown in the list.

Example: `delete 1`

The task at index 1 will be deleted

```
Okay-dokey! I've removed this task:

[T][] watch CS2103T lecture

Now you have 2 tasks in the list.
```

---

## Find a task

Filters the list based on the input given to show only tasks
whose description fits.

Example: `find Assignment`

A list of tasks whose description includes the word 'Assignment' will be shown

```
Okay-dokey! here are the matching tasks!

1.[D][] CS103T Assignment (By: 13 Oct 2024)
```

---
## Tag a task

Gives or updates a task a tag

Example: `tag 2 IMPORTANT`

The task at index 2 will be tagged with #IMPORTANT

```
Okay-dokey! I have added a tag to the task!

2.[D][] CS103T Assignment (By: 13 Oct 2024) #IMPORTANT
```

---

