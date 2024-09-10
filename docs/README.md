# Tina User Guide

![Tina Screenshot](./Ui.png)

Tina frees your mind of having to remember things you need to do. It's,
- **EASY** to learn
- ~~FAST~~ SUPER FAST to use

## How to start
1. Ensure you have Java 17 or above installed in your machine.
2. Download jar file from [here](https://github.com/Justincjr/ip/releases/tag/A-Release).
3. Run `java -jar "{filename}.jar"`

## Adding Deadlines

Tasks that need to be done before a specific date/time

Command: ```deadline {description} {dd/mm/yyyy HHMM}```

Sample input: ```deadline return book /by 2/12/2019 1800```

Expected output:
```
Got it. I've added this task:
  [D][ ] return book (by: Dec 02 2019 18:00)
Now you have 1 tasks in the list.
```

## Adding Todos

Tasks without any date/time attached to it

Command: ```todo {description}```

Sample input: ```todo read book```

Expected output:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```
## Adding Events

Tasks that start at a specific date/time and ends at a specific date/time

Command: ```event {description} {dd/mm/yyyy HHMM} /to {dd/mm/yyyy HHMM}```

Sample input: ```event project meeting /from 2/12/2019 1800 /to 2/12/2019 1900```

Expected output:
```
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 02 2019 19:00)
Now you have 3 tasks in the list.
```

## List

Display the list of tasks

Command: ```list```

Expected output:
```
Here are the tasks in your list:
1. [D][ ] return book (by: Dec 02 2019 18:00)
2. [T][ ] read book
3. [E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 02 2019 19:00)
```

## Find

Finds tasks which description contain any of the given keywords

Command: ```find {keywords}```

Sample input: ```find project```

Expected output:
```
Here are the matching tasks in your list:
1. [E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 02 2019 19:00)
```

## Mark as done

Mark tasks as done or change the status back to not done

Command: ```mark {index}```

Sample input: ```mark 1```

Expected output:
```
Nice! I've marked this task as done:
  [D][X] return book (by: Dec 02 2019 18:00)
```

Command: ```unmark {index}```

Sample input: ```unmark 1```

Expected output:
```
OK, I've marked this task as not done yet:
[D][ ] return book (by: Dec 02 2019 18:00)
```

## Delete

Delete tasks from the list.

Command: ```delete {index}```

Sample input: ```delete 2```

Expected output:
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

## Exit

To exit, just use the command ```bye```

## Editing the data file

Data is saved automatically as a txt file ```[JAR file location]/data/tina.txt```