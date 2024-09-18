# PoChat User Guide

Welcome to PoChat, the little chatbot in your pocket that is lightweight but definitely pulls its
weight when it comes to keeping track of your life :) 

![Ui.png](Ui.png)

Getting started
--------

Ensure that you are running java 17 on your machine. You may check your java version by entering
`java -version` in the command line.

Copy the jar file into your terminal and run it with the following command:

```
java -jar PoChat.java
```


Using the bot
--------

PoChat comes packed with a host of features for a smooth and seamless use of the chatbot. 
These features include

## Add tasks

Add a todo, a task **without** a fixed timeline: 

`todo some_task`

```
Got it. I've added this task:
[T][ ] some_task
Now you have 1 tasks in the list
```

Add a deadline, a task **with** a fixed deadline: 

`deadline some_deadline /by 19/09/2024 1800 `
```
Got it. I've added this task:
[D][ ] some_deadline (by: 2024-09-19 1800)
Now you have 2 tasks in the list
```

Add an event, a task **with** a start and end time: 

`event some_event /from 19/09/2024 1800 /to 19/09/2024 2100`
```
Got it. I've added this task:
[E][ ] some_event (from: 2024-09-19 1800 to: 2024-09-19 2100)
Now you have 3 tasks in the list
```

## List down all tasks

Once all tasks are added, you want to take a look at what you have coming up: 

`list`

```
Here are the tasks in your list:
1. [T][ ] some_task
2. [D][ ] some_deadline (by: 2024-09-19 1800)
3. [E][ ] some_event (from: 2024-09-19 1800 to: 2024-09-19 2100)
```

## Mark tasks as done and undone

Of course, you would eventually complete your tasks, and at that point you want to mark them as done :) 
Let's say I have completed `some_event` and now want to mark it as done:

`mark 3`

```
Nice! I've marked this task as done:
[E][X] some_event (from: 2024-09-09 1800 to: 2024-09-09 2100)
```

(notice how the X is now marked on the task to indicate that it is done)

Oops! Actually, you realised that there was some part that you left out and would want to get back to later. 
No worries here, because you can do the following to unmark it as done

`unmark 3`

```
Ok, I've marked this task as not done yet:
[E][ ] some_event (from: 2024-09-09 1800 to: 2024-09-09 2100)
```

## Deleting tasks

It's been a long time, and while not done, `some_event` is no longer relevant and you want to remove it. At this 
point, do the following:

`delete 3`

```
Noted. I've removed this task:
[E][ ] some_event (from: 2024-09-09 1800 to: 2024-09-09 2100)
Now you have 2 tasks in the list
```

## Searching for tasks based on keyword

At some point you have.. too many things to do :O and would want to quickly search for tasks based on
certain keywords. If the keyword you are using is `some`, you can do:

`find some`

```
Here are the matching tasks in your list:
1. [T][ ] some_task
2. [D][ ] some_deadline (by: 2024-09-09 1800)
```

## Duplicate handling 

Don't worry if you lose track! That's what PoChat is here for :) If you add a task you already have, well, 
PoChat will let you know :P

`todo some_task`

```
Sorry! You already have this task in your list. Please try adding another task
```

## Error handling

Forgot something? Like.. maybe a description for the todo you are working on? No worries, PoChat has got you
covered as well!

`todo`

```
Task description cannot be empty!! Please try again
```

## Exiting the bot

Done with PoChat? Well, all good things come to an end :( At this point simply enter:

`bye`

And the window will clear. Feel free to start using PoChat again anytime you would like~

## Closing

And now you have it! A chatbot made just for you to get everything in order :)

## Acknowledgements

Photos for the user and PoChat are generated with Meta AI. All rights reserved.


