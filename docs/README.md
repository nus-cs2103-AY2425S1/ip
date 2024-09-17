# Tars ChatBot User Guide

![Ui.png](Ui.png)

This is a greenfield Java chatbot project that allows users to add three different kinds of tasks: `ToDo`, `Deadline` and `Event` tasks. It is optimised for users who prefer to type commands rather using a GUI to achieve the same result. 

However, to display the chat history in a more user-friendly manner, the chatbot comes with a GUI solely for the purpose of displaying the user inputs as well as the chatbot's responses.

# Commands
> [!NOTE]
> For all the commands, arguments for the command are specified between curly brackets `{}` and therefore, the curly brackets not meant to be typed in the command itself. See the example usage for clearer understanding. 

## Adding Tasks

### ToDo
A ToDo is a simple task that has no start date and no deadline.

Command format: `/todo {task name}`

Command example: `/todo fetch laundry` will create a ToDo task to fetch laundry

Expected output:
```
Added yet another task
    [T][] fetch laundry
You now have 1 tasks. Are you gonna do any of them?
```

### Deadline

A Deadline, as the name suggests, is a task with a fixed deadline. 

The date specified in the command needs to be in the `dd-MM-yy` format. For example 22nd of September 2024 will be represented as `22-09-24`

Command format: `/deadline {task name} /by {deadline date}`

Command example: `/deadline submit CS2103T IP /by 22-09-24`

Expected Output:
```
Added yet another task
    [D][] submit CS2103T IP  (by: Sep 22 2024)
You now have 2 tasks. Are you gonna do any of them?
```
### Event
An event is a task with a start date and an end date. 

Similar to creating a Deadline task, the dates specified in the command needs to be in the `dd-MM-yy` format

Command format: `/event {task name} /from {start date} /to {end date}`

Command example: `/event train for IPPT /from 17-09-24 /to 21-09-24`

Expected Output:
```
Added yet another task
    [E][] train for IPPT  (from: Sep 17 2024 to: Sep 21 2024)
You now have 2 tasks. Are you gonna do any of them?
```
## Showing tasks
To print all the tasks that have been created simply type the command `list`

Command format: `list`

Expected output:
```
Here are your tasks, champ. Let's see how many you can actually cross off.
1. [E] [ ] train for IPPT (from: Sep 17 2024 to: Sep 21 2024)
2. [D] [ ] submit CS2103T IP (by: Sep 22 2024)
3. [T] [ ] fetch laundry
```
## Marking tasks
Marking tasks allows users to mark tasks as done.

Command format: `mark {task number}`

Command example: `mark 1`

Expected output:
```
Task complete. If I had arms, I might give you a pat on the back.
[E] [X] train for IPPT (from: Sep 17 2024 to: Sep 21 2024)
```

Similarly, unmarking tasks allows users to unmark tasks. 

Command format: `unmark {task number}`

Command example: `unmark 1`

Expected output:
```
Task undone. No worries, I won't judge... much.
[E] [ ] train for IPPT (from: Sep 17 2024 to: Sep 21 2024)
```

## Deleting tasks
Users can also delete tasks with the `delete` keyword.

Command format: `delete {task number}`

Command example: `delete 1`

Expected output:
```
Wow you're freeing yourself up
   [E] [X] train for IPPT (from: Sep 17 2024 to: Sep 21 2024)
You now have 2 tasks left
```

##  Find Tasks
Users can quickly find tasks by searching for a word/letter in the task name. All the tasks that contain the search word/letter will be shown in the order in which they appear in the list along with the respective task number.

Command format: `find {search word}`

Command example: `find laundry`

Expected output:
```
Here are the tasks found.
2. [T] [ ] fetch laundry
```

## Exit
Users can exit the chatbot with the `bye` command. After 3.5 seconds, the app will automatically close.

Command format: `bye`

Expected output:
```
Well, that's a wrap! If you need anything else, just holler.
But let’s be honest, you’re probably better off asking someone else.
```
# Features

## Sorting of tasks
Whenever tasks are added they are automatically sorted in chronological order by the end date of the task. Hence, tasks with the earliest end date being shown at the top of the list. As such `ToDo` tasks have the lowest priority and will be shown at the bottom of the list whereas for `Deadline` and `Event` tasks, the deadline date/end date will be compared to determine which tasks takes precedence over the other.

This is done to ensure that the tasks that are due soon have the most visibility and can be clearly seen at the top of the list.

## Automatic exiting of chatbot

After typing the `bye` command, the program will pause for 4 seconds to allow for users to read the exit message before automatically exiting the app. 

This is a quality of life feature to ensure that users to not need to use additional inputs, such as a mouse click, to close the app and instead have it automatically done for the user. 

Additionally, this aligns with the original goal of this greenfield Java project to make a chatbot that is optimised for users who prefer to type commands to add tasks rather than use a GUI to do the same

