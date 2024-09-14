# Mortal Reminder User Guide

![Screenshot of Ui](Ui.png)

Mortal Reminder is a funny little app used to manage your **todos, deadlines and upcoming events** in a pretty graphical user interface which makes everything easier to see.

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer. 
2. Download the latest .jar file from (to be updated after release).
3. Double click to open and it should immediately open the app for you to use.

## Features and their examples

### Todo
Example:
```html
todo read book
```

A confirmation message should be output and the todo should be added to the list of tracked tasks. The confirmation message example is below:

```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[T][] read book
Now you have [however many task(s)] in your list.
```
### Deadlines
A deadline is a time-constrained task which must be done by a specific date and time. Make sure the date and time follows the exact format as shown below and that you have included the `/by` keyword. This should add a deadline to the list and output a message as shown in the examples below:

Example Input:
```
deadline return book /by 19-09-2024 1800
```
Example output:
```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[D][] return book (by: 19 Sep 2024 6:00pm)
Now you have [however many task(s)] in your list.
```

### Events
An Event is a type of task which happens specifically within a constrained duration. Make sure that the date and time specified for the start and end follow exactly as seen in the Example Input:

Example Input:
```
event project meeting /from 19-09-2024 1400 /to 19-09-2024 1500
```
Example Output:
```
You know what? Your life’s already hell so I am gonna leave you be. I've added this task:
[E][] project meeting (from: 19 Sep 2024 2:00pm to: 19 Sep 2024 3:00pm)
Now you have [however many task(s)] in your list.
```

### Marking and Unmarking
You can mark or unmark items to show if they have been completed or not. Simply do the following with the item index shown in the list. 
Example Input:
```
mark 1
```
Example Output:
```
"I've marked this task as done:
[specified task based on the item number]
But are you really Ok?"
```
