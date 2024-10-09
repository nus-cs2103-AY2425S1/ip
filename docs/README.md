# Jar User Guide

_Jar frees your mind from having to remember things you need to do._ 

**Features**
1. Text-based
2. Easy to learn
3. FAST SUPER FAST to use

## All you need to do is:

1. You can download the latest release of Jar Bot [here](https://github.com/jarrellc1/ip/releases).
2. Double-click it.
3. Run ```java -jar {filename}.jar```
4. Add your tasks.
5. Let it manage your tasks for you 😄.


## Adding DeadLines

Tasks that need to be done before a specific date/time

Command: ```deadline {task description} /by {d/mm/yyyy HH:MM}```

Sample input: ```deadline return book /by 2/12/2019 1800```

Expected output:
```
Added:[D][] return book (by: Dec 02 2019, 18:00)
Now you have 3 tasks in list.
```

## Adding ToDos
Tasks without any date/time

Command: ```todo {task description}```

Sample input: ```todo readbook```

Expected output:
```
Added:[T][] readbook
Now you have 3 tasks in list.
```

## Adding Events

Tasks that has a duration

Command: ```event {task description} /from {dd/mm/yyyy} /to {dd/mm/yyyy}```

Sample input: ``` event readbook /from 10/10/2024 /to 11/12/2024```

Expected output:
```
Added: [E][ ] readbook (from: 10/10/2024 to: 11/12/2024)
Now you have 11 tasks in the list.
```

## List

Display the list of tasks

Command/sample input: ```list```

Expected output:
```
Here are the tasks in your list:
1. [E][ ] work (from: 12/8/24 to: 12/9/24)
2. [T][X] readbook
```

## Find

Find specific task with same name

Command: ```find {task description}```

Sample input: ```find readbook```

Expected output:
```
Here are the matching tasks in your list:
1. [T][X] readbook
```

## Delete

Delete task with matching number

Command: ```delete {task number}```

Sample input: ```delete 1```

Expected output:
```
Noted. I've removed this task:
[T][X] readbook
Now you have 10 tasks in the list.
```

## Mark

Mark task with matching number

Command: ```mark {task number}```

Sample input: ```mark 1```

Expected output:
```
Nice! I've marked this task as done:
[T][X] readbook
```

## Unmark

Unmark task with matching number

Command: ```unmark {task number}```

Sample input: ```unmark 1```

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] readbook
```

## Exit

Exit bot and save file

Command: ```bye```

Sample input: ```bye```

Expected output: JarBot window will automatically close and exit. No action is required.

Data is automatically saved as txt file ```[JAR file location]/data/jar.txt```

## User Acknowledgement
- [ ] I know how to use the bot now
- [ ] I am aware of the risks associated to the bot

