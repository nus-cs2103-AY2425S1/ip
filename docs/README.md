# KieTwoForOne User Guide

![Screenshot of KieTwoForOne chatbot UI](Ui.png)

KieTwoForOne is your all-encompassing task tracking chatbot. From marking tasks as complete or incomplete to filtering 
by date, KieTwoForOne does it all! It's features are listed below

## Features

### Adding tasks

Users can make use of KieTwoForOne to add three types of tasks, namely Todos, Deadline and Events. Once the task is 
added successfully, KieTwoForOne will display the task added to the user and inform the user on the total number of
tasks present in the list.

#### Todo Tasks

Todo tasks are tasks with no specified deadline. Users can add Todo tasks to KieTwoForOne by inputting the following
command: 

> todo (task)

For example if the user had inputted the following command:

> todo task

The chatbot will provide the following response if it has successfully added the task to the task list.

> Got it. I've added this task:
> 
>   [T] [ ] task 
> 
> Now you have 2 tasks in the list.

#### Deadline Tasks

Deadline tasks are tasks with one specified deadline. Users can add Deadline tasks to KieTwoForOne by inputting the 
following command:

> deadline (task) /by (date in YYYY-MM-DD) (time in 24hr clock)

For example if the user had inputted the following command:

> deadline task /by 2024-12-05 1800

The chatbot will provide the following response if it has successfully added the task to the task list.

> Got it. I've added this task:
>
>  [D] [ ] task (by: Dec 5 2024 1800)
>
> Now you have 2 tasks in the list.

#### Event Tasks

Event tasks are tasks with a specified start and end time. Users can add Event tasks to KieTwoForOne by inputting the
following command:

>  event (task) /from (date in YYYY-MM-DD) (time in 24hr clock) /to (date in YYYY-MM-DD) (time in 24hr clock)

For example if the user had inputted the following command: 

> event task /from 2024-12-05 1800 /to 2024-12-06 1800
> 
The chatbot will provide the following response if it has successfully added the task to the task list.

> Got it. I've added this task:
>
>  [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)
>
> Now you have 3 tasks in the list.

### Listing Tasks

Users can make use of KieTwoForOne to list the tasks that they added to the chatbot.

This is done by inputting the following command>

> list

Using the previous inputs from the adding tasks section calling this command would display the following

> Here are the tasks in your list:
> 1. [T] [ ] task
> 2. [D] [ ] task (by: Dec 5 2024 1800)
> 3. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)

### Marking and Unmarking tasks

Users can make use of KieTwoForOne to mark certain tasks as complete or incomplete. This would mark an X at the Task to 
indicate that the task has been completed and leave it empty if it is incomplete.

#### Marking tasks

Users can make use of KieTwoForOne to mark tasks as complete by inputting the following command:

> mark (index)

Index refers to the position of the task in the task list. For example calling the following input:

> mark 1

Would yield the following response from the chatbot.

> Nice! I've marked this task as done:
> 
> [T] [X] task

#### Unmarking tasks

Users can also unmark tasks that are incomplete by inputting the following command:

> unmark (index)

Index again refers to the position of the task in the task list. For example calling the following input on the same
task list.

> unmark 1

Would yield the following response from the chatbot.

> OK. I've marked this task as incomplete:
> 
> [T] [ ] task

### Deleting tasks

Users can also delete tasks from the task list by inputting the following command:

> delete (index)

Index refers to the position of the task in the task list. For example inputting the following command on the previous
task list:

> delete 1

Would yield the following response from the chatbot:

>Noted. I've removed the task:
> 
>[T] [ ] task
> 
>Now you have 2 tasks in the list.

The chatbot will inform you of the number of tasks remaining in the task list. Inputting the following:

> list

Would yield the following output:

>Here are the tasks in your list:
> 
> 1. [D] [ ] task (by: Dec 5 2024 1800)
> 2. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)

We can see that the task Todo task has been deleted and there are 2 tasks left.

### Filer tasks by date

Users can filter the tasks by date by inputting the following command:

> date (date in YYYY-MM-DD)

Deadline tasks are only kept if the deadline lies on the date inputted while event tasks are kept if the date lies 
between the start and end date inclusive

For example using the above task list, inputting the following:

> date 2024-12-05

Would yield the following output:

> Here are the tasks occurring on this date:
> 1. [D] [ ] task (by: Dec 5 2024 1800)
> 2. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)

Since the date inputted lies wihtin both the deadline and event duration.

On the other hand inputting the following:

> date 2024-12-06

Would yield the following output:

> Here are the tasks occurring on this date:
> 1. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)

Since the date does not lie within the deadline but lies within the event duration.

### Filter tasks by word

Users can make use of the find functionality in our chatbot to filter tasks by words using the following input

> find (word)

Given the following task list:

> Here are the tasks in your list:
> 1. [D] [ ] task (by: Dec 5 2024 1800)
> 2. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)
> 3. [T] [ ] do work
> 4. [T] [ ] do work tomorrow
> 5. [T] [ ] do poetry

Users can input a word from the sentence such as:

> find do

And yield the following output:

> Here are the matching tasks in your list:
> 1. [T] [ ] do work
> 2. [T] [ ] do work tomorrow
> 3. [T] [ ] do poetry

They may also input more than one word:

> find do work

And yield the following output:

> Here are the matching tasks in your list:
> 1. [T] [ ] do work
> 2. [T] [ ] do work tomorrow

They may even also input part of a word:

> find ta

And yield the following output:

> Here are the matching tasks in your list:
> 1. [D] [ ] task (by: Dec 5 2024 1800)
> 2. [E] [ ] task (from: Dec 5 2024 1800 to: Dec 6 2024 1800)

### Tagging tasks

Users can also use the tag function to tag certain tasks as they see fit using the following command

> tag (index) /(tag)

Index refers to the position of the task in the task list and the tag being the tag u would like to use.

For example inputting the following on the previous task list:

> tag 5 /fun

Will yield the following output:

> OK. I've tagged the following task:
> 
> [T] [ ] do poetry #fun

### Exiting the application

User can input the following command to exit and close the application

> bye
