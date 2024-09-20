# Majimabot
>"He's alright." - Ono Michio

## What is Majimabot?
This portable instance of the Mad Dog of Shimano can't fight for you, but what he lacks in combat skills, he makes up for in clerical tasks!

What can he do, you ask?

1. Keep track of various tasks
    * 'Todo', tasks which are either done or not
    * 'Deadline' tasks where you can specify a date and time to accomplish it by
    * 'Event' tasks which take place during two points in time
2. Mark tasks as completed or not
3. Find specific tasks you have listed

... and more to come! <sub>(If I feel like it.)</sub>

## How _do_ I use Majimabot? 🤔

Majima tracks three types of tasks, as stated above.
Typing 'todo' in the console, followed by a short description, stores a task with the corresponding description.
Entering 'deadline', followed by a description, seperated by a '/by' and a valid date-time format of "dd-MM-YYYY HHmm" creates a task with a deadline that Majima will keep track of.
An event has a description, a /from and /to time which does not need to follow a date-time format.

To know what tasks Majima is currently keeping track of, simply tell Majima
`list`
and he'll show you what tasks he's currently remembering.

Furthermore, tasks can be marked or unmarked to keep track of their completion. In a list like such,
```
1. [T][ ] Beat up thugs
2. [T][ ] Random sidequest
3. [T][ ] Throw the trash
```
executing `mark 3` would mark the task like so:
```
1. [T][ ] Beat up thugs
2. [T][ ] Random sidequest
3. [T][X] Throw the trash
```

Need a quick reminder? Just tell Majimabot `help` and he'll remind ya all about what he can do!

## So, what are you waiting for!
Majimabot remembers tasks in between runs, so download the .jar [here](https://github.com/Meowloid/ip/releases/tag/A-Jar), put him in a directory of your choice, and get started! And it's all for **FREE**!