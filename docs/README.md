# Jard User Guide

The title of the product is named Jard.

https://wujinhan1.github.io/ip/

![Jard](Ui.png)

Jard is a reminder chatbot with three different types of things to remind. Todo, deadline, and event.

Three types of actions, todo, deadline, and event. The outcome is that it creates three types of things to remind you for. Todo has takes in no dates, deadline takes in one, and event takes in two.

## Adding todo

An example of usage can be todo Homework, which adds an event called homework that you need to do.

Example: `todo Homework`


```
Got it. I've added this task:
[T][] Homework
Now you have 1 tasks in the list.
```

## Adding deadline

An example of usage can be deadline iP /by 2024-09-20 2359, which adds an event called iP that you need to complete by 9/20/2024 11:59pm

Example: `deadline iP /by 2024-09-20 2359`


```
Got it. I've added this task:
[D][] iP (by: Sep 20 2024, 11:59pm)
Now you have 2 tasks in the list.
```

## Adding event

An example of usage can be event meeting /from 2024-09-20 2100 /to 2024-09-20 2200, which adds an event called meeting from 9/20/2024 9pm to 9/20/2024 10pm.

Example: `event meeting /from 2024-09-20 2100 /to 2024-09-20 2200`


```
Got it. I've added this task:
[E][] meeting (from: Sep 20 2024, 9:00pm to: Sep 20 2024, 10:00pm)
Now you have 3 tasks in the list.
```

## Feature `list`
Gives you the list of events that you have put in.

Example: `list`

```
Here are the tasks in your list:
1.[T][] Homework
```

## Feature `mark and unmark`
Marks and unmarks events in your list.

Example: `mark 1`
```
Nice! I've marked this task as done:
[T][X] Homework
```

Example: `unmark 1`
```
OK, I've marked this task as not done yet:
[T][] Homework
```

## Feature `delete`
Deletes events in your list.

Example: `delete 1`
```
Noted. I've removed this task:
[T][X] Homework
Now you have 0 tasks in this list.
```

## Feature `find`
Finds event in your list by putting in characters in your event.

Example: `find Homework`
```
Here are the matching tasks in you list:
1.[T][X] Homework
```