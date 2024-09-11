# Hoodini User Guide 🐕:

Update: I changed the background of the chatbot but everything else is the same 🐕

![Screenshot 2024-09-11 at 10 43 49 AM](https://github.com/user-attachments/assets/b38c673b-9655-4a8d-aa63-42d73ac16fca)


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

![Add Todo Task](https://github.com/user-attachments/assets/955cf695-f781-408c-a700-d049cd807e75)

## Adding Deadline tasks

To add a deadline task to your list, use the `deadline` command followed by the task description and `/by` before the deadline.


Example: `deadline tutorial /by 2024-09-07`

Expected output:

```
Noted. I have added this task:
[D][] tutorial (by: Sep 7 2024)
You have 5 tasks in the list.
```
![Screenshot 2024-09-07 at 2 40 53 PM](https://github.com/user-attachments/assets/2c25b31a-dcdd-467f-b57a-19eedce424ab)


## Adding Event tasks

To add an event task to your list, use the `event` command followed by the task description and `/from` before the start of the event and `/to` before the end of the event.

Example: `event meeting /from 2024-09-07 /to 2024-09-08`

Expected output:

```
Noted. I have added this task:
[E][] meeting (from: 2024-09-07 to 2024-09-08)
You have 6 tasks in the list.
```


![Screenshot 2024-09-07 at 2 41 11 PM](https://github.com/user-attachments/assets/f49cf5ea-2411-4432-93b2-6867a769d640)



## Listing tasks in the list

To view all the tasks in your list, use the `list` command.

Example: `list`

Expected output:

![Screenshot 2024-09-07 at 2 41 38 PM](https://github.com/user-attachments/assets/7fc4be54-bf84-4dbf-8b54-5cfac45c755c)


## Marking tasks in the list

To mark a task as done, use the `mark` command followed by the task number.

Example: `mark 1`

Expected output:

```
I have marked the task as done:
[D] [X] tutorial (by: Sep 8 2024)
```
![Screenshot 2024-09-07 at 2 42 16 PM](https://github.com/user-attachments/assets/16a1c29b-4bb1-4fa1-8961-d8c9bc3e4c18)


## Unmarking tasks in the list

To unmark a task as done, use the `unmark` command followed by the task number.

Example: `unmark 1`

Expected output:

```
I have marked the task as undone:
[D] [] homework (by: Sep 11 2024)
```
![Screenshot 2024-09-07 at 2 42 38 PM](https://github.com/user-attachments/assets/0ecea390-1aa5-4703-902e-e011be5356ca)



## Deleting tasks in the list

To delete a task from your list, use the `delete` command followed by the task number.

Example: `delete 1`

Expected output:

```
Noted. I have deleted this task:
[D] [X] tutorial (by: Sep 7 2024)
You have 4 tasks in the list.
```
![Screenshot 2024-09-07 at 2 43 00 PM](https://github.com/user-attachments/assets/fb459602-3294-4919-9f68-9069b247666a)


## Finding tasks in the list

To find tasks in your list that contain a specific keyword, use the `find` command followed by the keyword.

Example `find home`

Expected output:

```
Here are the list of tasks found:
1.[D] [] homework (by: Sep 11 2024)
```

![Screenshot 2024-09-07 at 2 43 26 PM](https://github.com/user-attachments/assets/dc97a258-a378-480a-aa01-5788733c0337)


## Sorting tasks in the list

To sort the tasks in your list by their type, use the `sort` command.

Example: `sort`

Expected output:

![Screenshot 2024-09-07 at 2 43 43 PM](https://github.com/user-attachments/assets/36083fd8-ad2d-4b5e-ac77-68cb72f9fe26)



## Ending the conversation

To end the conversation with Hoodini, use the `bye` command. It will write all the tasks you have added to a file called `hoodini.txt` in your desktop folder.

Example: `bye`

Expected output:

```
Bye! Come back to Hoodini soon!
```
![Screenshot 2024-09-07 at 2 43 55 PM](https://github.com/user-attachments/assets/f6967dc8-f4c3-4774-8ae2-95fb3b9ba3db)

Thank you for reading :dog:

