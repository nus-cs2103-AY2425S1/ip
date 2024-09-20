# Meeju User Guide


![UI for Meeju](./Ui.png)

**Meeju, Your Personal assistant to track your tasks**

This chatbot is,
- Text-based which is targeted towards **FAST** typers ⚡⚡
- Easy to learn and very easy to use ✨



> Who is Meeju? 🐈
> 
> ![Meeju Picture](./meeju.png)
> 
> Meeju is the name of my lovely pet cat.
> She resides in my hometown in India. 
> Meeju is a playful and affectionate cat with whom I spend 
> a significant amount of time when I am in India.
> 
> Meeju is actually a stray cat who once found herself in our house.
> It all started with feeding her a bowl of milk. 
> Ever since that day, Meeju would always come and stay around our house.
> Although we never officially domesticated her, would often come for food as well as to play. 
> 
> As days passed the bond between us and Meeju grew stronger as she would
> regularly visit us. She would rub herself on us, lie on our laps and 
> enjoys being petted. And she responds when we call her. 
> From a distance when we call "MEEJU", she would acknowledge with a "MEOW"
> and walk towards us.
> 
> Her iconic "MEOW" is reflected in every message sent out by this chatbot - Meeju.

## Adding Todo Tasks

A todo task is a generic task which does not have any timeline attached with it.

A Todo task can be created using the following syntax
`todo TASK_DESCRTIPTION`


Example: `todo Plan Vacation`

```
expected output

Meow! I've added this task:
    [T][] Plan Vaccation
Now you have n tasks in the list.
```

> n refers to the total number of tasks

## Adding Deadline Tasks

A deadline task is a task which has a specific end date and time to it.

A Deadline task can be created using the following syntax
`deadline TASK_DESCRTIPTION /by DD/MM/YYYY HHMM`

> Date and Time must be given in the specified format!

Example: `deadline Submit Project /by 20/09/2024 2359`

```
expected output

Meow! I've added this task:
    [D][] Submit Project (by: Sep 20 2024 23:59HRS)
Now you have n tasks in the list.
```

## Adding Event Tasks

An event task is a task which spans across a certain duration. It has a start date/time and end date/time.

An Event task can be created using the following syntax
`event TASK_DESCRTIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`

> Date and Time must be given in the specified format!

Example: `event TP Meeting /from 22/09/2024 1400 /to 22/09/2024 1500`

```
expected output

Meow! I've added this task:
    [E][] TP Meeting (from: Sep 22 2024 14:00HRS to: Sep 22 2024 15:00HRS)
Now you have n tasks in the list.
```

## Viewing all Tasks

All the tasks created and which is in the list could be listed out by using the command
`list`

Example: `list`

```
expected output

1. [T][] Plan Vacation
2. [D][ ] Submit Project (by: Sep 20 2024 23:59HRS)
3. [E][ ] TP Meeting (from: Sep 22 2024 14:00HRS to: Sep 22 2024 15:00HRS)
```

## Reminders

All the tasks which has an upcoming deadline or an upcoming event could be viewed.

To get reminders, use the following command
`reminders`

Example: `reminders`

```
expected output

Deadlines
1. [D][ ] Submit Project (by: Sep 20 2024 23:59HRS)

Events
1. [E][ ] TP Meeting (from: Sep 22 2024 14:00HRS to: Sep 22 2024 15:00HRS)
```
> Those tasks which are marked as completed will NOT be listed!

## Mark a task as completed

This is used to mark out a task as completed which is done using the following command
`mark TASK_NUMBER`

> You can use 'list' function to view task number


Example: `mark 1`

```
expected output

Meow! I've marked this task as done:
    [T][X] Plan Vaccation
```

## Unmark a task as completed

This is the undo action of marking a task as done. It is done using the following command:
`unmark TASK_NUMBER`

> You can use 'list' function to view task number


Example: `unmark 1`

```
expected output

Meow! I've marked this task as not done yet:
    [T][] Plan Vaccation
```

## find

This is used to find any particular task using search keywords
`find KEYWORDS`

Example: `fine Meeting`

```
expected output

1. [E][ ] TP Meeting (from: Sep 22 2024 14:00HRS to: Sep 22 2024 15:00HRS)
```

## delete

This is used to permanently delete a task and remove it from the list.
`delete TASK_NUMBER`

> You can use 'list' function to view task number

Example: `delete 3`

```
expected output

Meow! I've removed this task:
   [E][ ] TP Meeting (from: Sep 22 2024 14:00HRS to: Sep 22 2024 15:00HRS)
Now you have 2 tasks in the list.l
```
