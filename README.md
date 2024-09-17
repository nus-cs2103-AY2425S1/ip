# PoChat User Guide

Welcome to PoChat, the little chatbot in your pocket that is lightweight but definitely pulls its
weight when it comes to keeping track of your life :)

Getting started
--------

When using PoChat for the first time, ensure that you have the file `chat_data.txt` set up
in the `main` directory. This can be an empty `.txt` file to begin with.

Once the file is set up, do a gradle build to ensure that the files are properly set up

```
./gradlew clean build
```

Now, start the bot

```
./gradlew start
```


Using the bot
--------

PoChat comes packed with a host of features for a smooth and seamless use of the chatbot.
These features include

## Add tasks

Add a todo, a task without a fixed timeline:

`todo some_task`

```
Got it. I've added this task:
[T][ ] some_task
Now you have 1 tasks in the list
```

Add a deadline, a task with a fixed deadline:

`deadline some_deadline /by 09/09/2024 1800 `

```
Got it. I've added this task:
[D][ ] some_deadline (by: 2024-09-09 1800)
Now you have 2 tasks in the list
```

Add an event, a task with a start and end time:

`todo some_event /from 09/09/2024 1800 to /to 09/09/2024 2100`

```
Got it. I've added this task:
[E][ ] some_event (from: 2024-09-09 1800 to: 2024-09-09 2100)
Now you have 3 tasks in the list
```

## List down all tasks

Once all tasks are added, you want to take a look at what you have coming up:

`list`

```
Here are the tasks in your list:
1. [T][ ] some_task
2. [D][ ] some_deadline (by: 2024-09-09 1800)
3. [E][ ] some_event (from: 2024-09-09 1800 to: 2024-09-09 2100)

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

## Exiting the bot

Done with PoChat? Well, all good things come to an end :( At this point simply enter:

`bye`

And the window will `clear`. Feel free to start using PoChat again anytime you would like~

## Closing

And now you have it! A chatbot made just for you to get everything in order :)


