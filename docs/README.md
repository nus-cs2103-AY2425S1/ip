# Lemon User Guide

Welcome to lemon!\
A lil guy that will help you keep track of tasks!

![alt text](https://github.com/Dino-Nuggies/ip/docs/Ui.png?raw=true)
>Open lemon using the following command in cmd
>``` java
>$ java -jar lemon.jar
>```

Here is a general list of things lemon can help you with

    1. Adding and managing of Tasks
        1.1. Updating its statuses
        1.2. Deletion of Tasks
    2. Listing Tasks lemon remembers
    3. Finding Tasks using Keywords
---

## Features

### Help!
It can be pretty confusing to use lemon for the first time.\
Therefore simply ask lemon what features it has by asking it with `help`
### Adding Tasks
Lemon is able to help you keep track of variety types of tasks.\
Currently it supports:
* `todo (description)`
* `deadline (description) /by (date)`
* `event (description) /from (date) /to (date)`

#### To do
Using `todo (description)`\
A simple task for lemon to keep track of for you. :D

>Example:
>```
>todo Buy a cup of coffee
>```

#### Deadline
Using `deadline (description) /by (date)`\
A task that has to be done by a specified date.\
Do make sure `date` is in the format of `dd-mm-yyyy`
> Example:
>```
>deadline Complete homework /by 22-10-2024
>```

#### Event
Using `event (description) /from (date) /to (date)`\
A task that will occur within the specified dates.\
Do make sure `date` is in the format of `dd-mm-yyyy`
> Example:
>```
>deadline Attend university open house /from 01-09-2024 /to 05-09-2024
>```

### Updating Tasks Statuses
Using `mark (task index)` or `unmark (task index)`
* `mark (task index)` to mark the completion of a task
* `unmark (task index)` unmarks a completed task

### Deleting Tasks
Using `delete (task index)`\
Removes a task that lemon remembers based on its index

### Listing Tasks
With `list` lemon will display all the task in its saved order.\
This can also be helpful if you have forgotten what tasks you have gave to lemon and which index each task are under.

### Finding Tasks
Whenever you need to filter by a specific keyword from a longgg list of tasks\
Using `find (keyword)` and lemon will display all tasks that description with the matching keyword

### Exiting lemon
Say `bye` to lemon once you are satisfied and wishes for lemon to go back to its ethereal point of existence.\
Dont worry, lemon will store all tasks you gave to it.