# Hoodini User Guide


![Hoodini](images/Hoodini.png)

Hoodini is a productivity tool that helps you manage your tasks and deadlines. It is a command line application that allows you to add, delete, and mark tasks as done. It also allows you to set deadlines for your tasks.

## Adding Todo tasks

To add a todo task to your list, use the `todo` command followed by the task description.

Example: `todo read book`

Expected output:
```
Noted. I have added this task:
[T][] read book
You have 4 tasks in the list.
```
![Screenshot 2024-09-07 at 2.05.17 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_UgmsP9%2FScreenshot%202024-09-07%20at%202.05.17%E2%80%AFPM.png)

## Adding Deadline tasks

To add a deadline task to your list, use the `deadline` command followed by the task description and `/by` before the deadline.


Example: `deadline tutorial /by 2024-09-07`

Expected output:

```
Noted. I have added this task:
[D][] tutorial (by: Sep 7 2024)
You have 5 tasks in the list.
```
![Screenshot 2024-09-07 at 2.08.11 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_o1ZNcc%2FScreenshot%202024-09-07%20at%202.08.11%E2%80%AFPM.png)

## Adding Event tasks

To add an event task to your list, use the `event` command followed by the task description and `/from` before the start of the event and `/to` before the end of the event.

Example: `event meeting /from 2024-09-07 /to 2024-09-08`

Expected output:

```
Noted. I have added this task:
[E][] meeting (from: 2024-09-07 to 2024-09-08)
You have 6 tasks in the list.
```

![Screenshot 2024-09-07 at 2.11.23 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_4hTfRG%2FScreenshot%202024-09-07%20at%202.11.23%E2%80%AFPM.png)



## Listing tasks in the list

To view all the tasks in your list, use the `list` command.

Example: `list`

Expected output:

![Screenshot 2024-09-07 at 2.13.01 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_YTa87z%2FScreenshot%202024-09-07%20at%202.13.01%E2%80%AFPM.png)

## Marking tasks in the list

To mark a task as done, use the `mark` command followed by the task number.

Example: `mark 1`

Expected output:

```
I have marked the task as done:
[D] [X] tutorial (by: Sep 8 2024)
```
![Screenshot 2024-09-07 at 2.14.36 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_24Z4pS%2FScreenshot%202024-09-07%20at%202.14.36%E2%80%AFPM.png)

## Unmarking tasks in the list

To unmark a task as done, use the `unmark` command followed by the task number.

Example: `unmark 1`

Expected output:

```
I have marked the task as undone:
[D] [] homework (by: Sep 11 2024)
```
![Screenshot 2024-09-07 at 2.22.15 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_gxgGwV%2FScreenshot%202024-09-07%20at%202.22.15%E2%80%AFPM.png)


## Deleting tasks in the list

To delete a task from your list, use the `delete` command followed by the task number.

Example: `delete 1`

Expected output:

```
Noted. I have deleted this task:
[D] [X] tutorial (by: Sep 7 2024)
You have 4 tasks in the list.
```
![Screenshot 2024-09-07 at 2.17.37 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_0SHpiU%2FScreenshot%202024-09-07%20at%202.17.37%E2%80%AFPM.png)

## Finding tasks in the list

To find tasks in your list that contain a specific keyword, use the `find` command followed by the keyword.

Example `find home`

Expected output:

```
Here are the list of tasks found:
1.[D] [] homework (by: Sep 11 2024)
```

![Screenshot 2024-09-07 at 2.19.20 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_gOaksY%2FScreenshot%202024-09-07%20at%202.19.20%E2%80%AFPM.png)

## Sorting tasks in the list

To sort the tasks in your list by their type, use the `sort` command.

Example: `sort`

Expected output:


![Screenshot 2024-09-07 at 2.20.23 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_j6WRW3%2FScreenshot%202024-09-07%20at%202.20.23%E2%80%AFPM.png)

## Ending the conversation

To end the conversation with Hoodini, use the `bye` command. It will write all the tasks you have added to a file called `hoodini.txt` in your desktop folder.

Example: `bye`

Expected output:

```
Bye! Come back to Hoodini soon!
```
![Screenshot 2024-09-07 at 2.25.02 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fvc%2Frcqx2qtj5r5g9g5jglx9qctr0000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_GMrI4Z%2FScreenshot%202024-09-07%20at%202.25.02%E2%80%AFPM.png)
