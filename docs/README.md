# Tudee User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
![Screenshot of a typical user conversation with Tudee](/docs/Ui.png)

// Product intro goes here
Ever wanted a personal assistant to help you **keep track of your daily tasks**? Fret not! Tudee chatbot is here!
Follow this README document to know the basics of using Tudee.

## Adding deadline tasks

// Describe the action and its outcome.
To add a deadline task to the list of tasks, type: deadline (task description) /by (date in **YYYY-MM-DD**)

// Give examples of usage

Example: `deadline finish poster design /by 2024-09-20`

// A description of the expected outcome goes here
Tudee will reply you with this message:
```
Got it. I've added this task:
 [D][ ] finish poster design (by: Sep 20 2024)
Now you have 1 tasks in the list.
```

## Adding todo tasks

To add a todo task to the list of tasks, type: todo (task description)

Example: `todo complete CS2100 quiz`

Tudee will reply you with this message:
```
Got it. I've added this task:
 [T][ ] complete CS2100 quiz
Now you have 2 tasks in the list.
```

## Adding event tasks

To add a event task to the list of tasks, type: event (task description) /from (start date) /to (end date)

Example: `event hackathon /from 2024-09-10 /to 2024-09-11`

Tudee will reply you with this message:
```
Got it. I've added this task:
 [E][ ] hackathon (from: Sep 10 2024 to: Sep 11 2024)
Now you have 3 tasks in the list.
```

## Show list of tasks

To display the current list of tasks, type: list

Example: `list`

Tudee will reply you with this message:
```
1. [D][ ] finish poster design (by: Sep 20 2024)
2. [T][ ] complete CS2100 quiz
3. [E][ ] hackathon (from: Sep 10 2024 to: Sep 11 2024)
```

## Mark tasks as completed

To mark tasks as completed, type: mark (position of task in list)

Example: `mark 1`

Tudee will reply you with this message:
```
Nice! I've marked this task as done:
[D][X] finish poster design (by: Sep 20 2024)
```

## Unmark tasks

To mark tasks that were completed as incomplete, type: unmark (position of task in list)

Example: `unmark 1`

Tudee will reply you with this message:
```
Ok, I've marked this task as not done yet:
[D][ ] finish poster design (by: Sep 20 2024)

```

## Delete tasks

To delete tasks from the list of tasks, type: delete (position of task in list)

Example: `delete 2`

Tudee will reply you with this message:
```
Noted. I've removed this task:
 [T][ ] complete CS2100 quiz
Now you have 2 tasks in the list.
```

## Find tasks by date

To find tasks at a specific date, type: date (date  in YYYY-MM-DD format)

Example: `date 2024-09-10`

Tudee will reply you with this message:
```
2. [E][ ] hackathon (from: Sep 10 2024 to: Sep 11 2024)
```

## Find tasks by keyword

To find tasks which descriptions' has a specific keyword, type find (keyword)

Example `find poster`

Tudee will reply you with this message:
```
1. [D][ ] finish poster design (by: Sep 20 2024)
```