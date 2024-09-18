# **Hamyo** Chatbot User Guide 🤖

<img src="Ui.png" alt="UI Screenshot" width="200"/>

> "Strive not to be a success, but rather to be of value." - Albert Einstein (1879-1955) [[Read More]](https://en.wikipedia.org/wiki/Albert_Einstein)

### Too many tasks to keep track of? **Hamyo** allows you to manage your
- 📝 ToDo
- 🕙 Deadline
- 💼 Event

### Functionalities supported by **Hamyo**
1. Add and Delete Tasks
2. Mark and Unmark Tasks
3. List all Tasks or filter by Date/ Keyword
4Mass Operation on Tasks (Mark, Unmark, Delete)

## Adding Tasks `todo` `deadline` `event`
Tasks that can be added include ToDo (a normal task), Deadline (a task that has to be completed before a specified date) and Event (a task that occurs between two specified dates/time).

Example 1: `todo Study for CS2103T Finals` Adds a ToDo task with the description "Study for CS2103T Finals" to the list of tasks.

```
Got it. I've added this task:
[T] [ ] Study for CS2103T Finals
There are 1 tasks in the list now.
```

Example 2: `deadline CS2103T Software Engineering Finals /by 2024-11-26 17:00` Adds a Deadline task with the description "CS2103T Software Engineering Finals" and specified deadline Nov 26 2024 17:00HRS to the list of tasks.
```
Got it. I've added this task:
[D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
There are 2 tasks in the list now.
```

Example 3: `event AY24/25 Semester 1 /from 2024-08-12 /to 2024-12-07` Adds a Event task with the description "AY24/25 Semester 1" and specified start date Aug 12 2024 and specified end date Dec 7 2024 to the list of tasks.
```
Got it. I've added this task:
[E] [ ] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
There are 3 tasks in the list now.
```

## Deleting Tasks `delete`
Tasks that are no longer relevant can be deleted by specifying the index.

Example: `delete 1` Deletes the task of index 1.
```
Noted. I've removed this task:
[T] [ ] Study for CS2103T Finals
There are 2 tasks in the list now.
```
> Note: This task would no longer be included in subsequent examples.

## Marking Tasks `mark`
Tasks that are completed can be marked by specifying the index.

Example: `mark 1` Marks the task of index 1.
```
Yay! This task has been marked as completed.
[D] [X] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
```

## Unmarking Tasks `unmark`
Tasks that are no longer completed can be unmarked by specifying the index.

Example: `unmark 1` Unmarks the task of index 1.
```
Oki! This task has been marked as incomplete.
[D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
```

## List all Tasks `list`
All tasks can be listed out for easy view and reference.

Example: `list` Lists all tasks.
```
These are your tasks:
1. [D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
2. [E] [ ] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
```

## List all Tasks filtered by Date `listDate`
Tasks occurring on a specified date can be listed out for easy view and reference.

Example: `listDate 2024-11-26` Lists all Deadlines and Events that fall on specified date Nov 24 2024.
```
These are your tasks on Nov 26 2024.
1. [D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
2. [E] [ ] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
```

## List all Tasks filtered by Keyword `find`
Tasks containing a specified keyword can be listed out for easy view and reference.

Example: `find finals` Lists all tasks containing the specified keyword.
```
Here are the matching tasks in your list for FINALS.
1. [D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
```

## Mass Operations `mark ...` `unmark ...` `delete ...`
Mass Operations on tasks can be executed for the commands `mark`, `unmark` and `delete`.

Example 1: `mark 1 2` Marks the tasks of index 1 and 2.
```
Aight Bet! I am marking 2 tasks!

Yay! This task has been marked as completed.
[D] [X] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)

Yay! This task has been marked as completed.
[E] [X] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
```

Example 2: `unmark 1 2` Unmarks the tasks of index 1 and 2.
```
Aight Bet! I am unmarking 2 tasks!

Oki! This task has been marked as incomplete.
[D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)

Oki! This task has been marked as incomplete.
[E] [ ] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
```

Example 3: `delete 1 2` Deletes the tasks of index 1 and 2.
```
Aight Bet! I am deleting 2 tasks!

Noted. I've removed this task:
[E] [ ] AY24/25 Semester 1 (from: Aug 12 2024 to: Dec 7 2024)
There are 1 tasks in the list now.

Noted. I've removed this task:
[D] [ ] CS2103T Software Engineering Finals (by: Nov 26 2024 17:00HRS)
There are 0 tasks in the list now.
```

## You have reached the end of the User Guide! 🤓
```java
public class Hamyo {
    public static void main(String[] args) {
        System.out.println("USE HAMYO TODAY!");
    }
}
```