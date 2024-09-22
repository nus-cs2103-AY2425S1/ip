# Puke 🤮 Task Manager User Guide

![Puke Interface](Ui.png)

## Introduction

Meet Puke 🤮, your loyal yet slightly tipsy sidekick dedicated to managing tasks. Whether you're slightly inebriated or fully sober, Puke ensures you never forget the important stuff—like keeping the party going or remembering to pay your dues. Puke is characterized by its:

- **Text-based interface:** Simple commands, ideal even when your vision is a bit fuzzy.
- **User-friendliness:** Easy enough to use even on your rowdiest nights.
- **Rapid responses:** Quicker than a bartender's pour!

## Getting Started

Just let Puke handle your task management while you manage the fun. And remember, it's **completely free!**

### Quick Start

1. **Ensure Java 17 or higher is installed on your computer.**
2. **Download the latest .jar file from [here](https://example.com/latest).**
3. **Place the file in your desired location.**
4. **Open a terminal, navigate to the folder containing the .jar file, and run:**

   ```
   java -jar puke.jar
   ```

   The GUI will pop up faster than you can say, "Bottoms up!"

## Features

### When You Start the App

Upon launching, Puke greets you with its unique, slightly slurred style:

```
HELLO???!!! I'm Puke and I'm Puk *uwaeh* ing... .-.
*uwaeh*
```

### Adding a To-do Task

Forget about forgetting—let Puke remember your errands.

**Usage:**

```
todo <description>
```

**Example:**

```
todo buy more beer
```

**Expected Outcome:**

```
Roger that! I've added this task:
[T][ ] buy more beer
Now you have 1 task in the list ~ !
*uwaeh*
```

### Adding a Deadline

Never miss a deadline, even if it’s for clearing your bar tab.

**Usage:**

```
deadline <description> /by <date>
```

**Example:**

```
deadline pay bar tab /by 12/12/2024 1222
```

**Expected Outcome:**

```
Roger that! I've added this task:
[D][ ] pay bar tab (by: Dec 12 2024, 12:22)
Now you have 2 tasks in the list ~ !
*uwaeh*
```

### Adding an Event

Keep track of your events, whether it’s a poker night or a weekend bash.

**Usage:**

```
event <description> /from <start> /to <end>
```

**Example:**

```
event poker night /from 24/12/2024 1900 /to 25/12/2024 0100
```

**Expected Outcome:**

```
Roger that! I've added this task:
[E][ ] poker night (from: Dec 24 2024, 19:00 to: Dec 25 2024, 01:00)
Now you have 3 tasks in the list ~ !
*uwaeh*
```

### Updating a Task

Change the description or timing of any task easily.

**Usage:**

```
update <index> /name <new_description> 
```
```
update <index> /by <new_time> 
```
```
update <index> /from <new_start_time> 
```
```
update <index> /to <new_end_time>
```

**Examples:**

```
update 1 /name pay the pub tab update 2 /by 15/12/2024 1800 update 3 /from 23/12/2024 2000 /to 24/12/2024 0200
```

**Expected Outcomes:**

```
Task updated successfully: [D][X] pay the pub tab (by: Dec 12 2024, 12:22)
```

### Deleting a Task

Sometimes, it's necessary to clear out tasks that are no longer relevant.

**Usage:**

```
delete <index>
```

**Example:**

```
delete 1
```

**Expected Outcome:**

```
Noted. I've removed this task:
[T][ ] buy more beer
Now you have 2 tasks in the list.
*uwaeh*
```

### Marking a Task as Done

Celebrate completing a task as you would a successful night out.

**Usage:**

```
mark <index>
```

**Example:**

```
mark 1
```

**Expected Outcome:**

```
Yippee~ *uweah* I've marked this task as done:
[D][X] pay bar tab (by: Dec 12 2024, 12:22)
*uwaeh*
```

### Unmarking a Task

Correct a premature completion mark—mistakes happen!

**Usage:**

```
unmark <index>
```

**Example:**

```
unmark 1
```

**Expected Outcome:**

```
LOL I've marked this task as not done yet:
[D][ ] pay bar tab (by: Dec 12 2024, 12:22)
*uwaeh*
```

### Listing Tasks

View all your tasks at once to keep track of what’s been done and what’s pending.

**Usage:**

```
list
```

**Expected Outcome:**

```
Here are the tasks in your list:
1. [D][X] pay bar tab (by: Dec 12 2024, 12:22)
2. [E][ ] poker night (from: Dec 24 2024, 19:00 to: Dec 25 2024, 01:00)

*uwaeh*
```

### Exiting the Program

When it’s time to say goodbye, whether to the app or your guests.

**Usage:**

```
bye
```

**Expected Outcome:**

```
BYE!!! *UWAGHhhHH* !!! see Ya *uWahghgh*
*uwaeh*
```

### Command Summary

| Action        | Format and Examples                                          |
|---------------|--------------------------------------------------------------|
| Add Todo      | `todo <description>` e.g., `todo buy more beer`              |
| Add Deadline  | `deadline <description> /by <date>` e.g., `deadline pay bar tab /by 12/12/2024 1222` |
| Add Event     | `event <description> /from <start> /to <end>` e.g., `event poker night /from 24/12/2024 1900 /to 25/12/2024 0100` |
| Delete        | `delete <index>` e.g., `delete 1`                            |
| Mark          | `mark <index>` e.g., `mark 1`                                |
| Unmark        | `unmark <index>` e.g., `unmark 1`                            |
| List          | `list`                                                      |
| Exit          | `bye`                                                        |

Stay organized with Puke 🤮, your reliable, albeit somewhat disoriented, task management companion. Drink and manage tasks responsibly!