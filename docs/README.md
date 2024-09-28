# Oyster Chatbot User Guide

![Ui.png](Ui.png)

Oyster chatbot is a productivity app to help you manage your upcoming tasks and events.

## Adding Tasks

Tasks can be added to a list, for you to keep track of things to do.

### Usage

`todo (details)`

### Expected Output

A success message informing of the task added is returned.
```
Alright, the task has been added!
```

## Adding Deadlines

Tasks with deadlines can be added to a list, for you to keep track of things with deadlines.

### Usage

`deadline (details) /by (date DD/MM/YYYY)`

### Expected Output

A success message informing of the task added is returned.
```
Alright, the deadline task has been added!
```

## Adding Events

Events in a period of time can be added to a list, for you to keep track of events.

### Usage

`event (details) /from (date DD/MM/YYYY) /by (date DD/MM/YYYY)`

### Expected Output

A success message informing of the task added is returned.
```
Alright, the event has been added!
```

## Displaying Items

Request for the chatbot to display all added items, to keep track of things.

### Usage

`list`

### Expected Output

A list of the items currently added.
```
Here is your current list!
1. ...
2. ...
```

## Deleting Items

Delete items from the list, to clean up unwanted items.

### Usage

`delete (index of item)`

### Expected Output

A success message showing the deletion of the item.
```
I have deleted the task!
```

## Closing the App

Terminate the app.

### Usage

`bye`

### Expected Output

A goodbye message.
```
See you again!
```

## Viewing Reminders

Displays a list of items that are starting soon, or due soon, or overdue.

### Usage

`remind`

### Expected Output

A list of items that require attention soon.
```
2 ongoing events:
• ...
• ...

2 overdue items:
• ...
• ...

2 tasks due soon:
• ...
• ...
```

## Finding Items

Finds items based on a keyword given, to find specific tasks easily.

### Usage

`find (keyword)`

### Expected Output

A list of items that include the keyword in its details.
```
Tasks matching 'keyword':
1. ...
2. ...
3. ...
```

## Mark Items

Complete an item by marking it as done, to manage things to do.

### Usage

`mark (index of item)`

### Expected Output

A success message informing of the marking of the item.
```
Well done on completing the task!
```

## Unmark Items

Label an item as incomplete, to manage things to do.

### Usage

`unmark (index of item)`

### Expected Output

A success message informing of the marking of the item.
```
I have unmarked the task!
```