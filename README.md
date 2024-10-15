# Meow chatbot user guide
![alt text](image.png)

Are you feeling lonely? Love cat girls? Hate being disorganised?
Here is where the meow chatbot comes in !!! It helps you organise
all your tasks from todo to events incoming !!!

## Mark Tasks
You can mark existing tasks as completed via the mark command
Command syntax: `mark <Task Index>`
Example: `mark 1`
```
expected output
task 1 has been marked
```

## Unmark Tasks
You can mark existing tasks as completed via the mark command
Command syntax: `unmark <Task Index>`
Example: `unmark 1`
```
expected output
task 1 has been unmarked
```

## List Tasks
You can use this command to list out all current available tasks
Command syntax: `list`
Example: `list`

## Find Tasks
You can use this command to find specfic task names which contain the word
Command syntax: `find <keyword>`
Example: `find meow`

```
expected outcome
all tasks name which contains meow will be listed
todo meow with cat
todo meow with martin
```

## Adding deadlines
You can add deadline task via the deadline command
Command syntax: `deadline <taskname> /by <time>`
it requires an additional input

Example: `deadline sleep /by 2024-09-24 2200`

Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
Meow has added this task hehe:
    [D][] sleep (by: 2200 Sep 24 2024)
    neow you have x tasks in the list
```

## Adding Events

You can add Events task via the event command
Command syntax: `event <eventName> /from 2024-09-24 1200 /to 2024-09-24 1400`
Example: `event sleep> /from <time> /to <time>`

Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
Meow has added this task hehe:
    [E][] sleep (at: 1200 Sep 24 2024 to: 1400 Sep 24 2024)
    neow you have x tasks in the list
```


## Update feature
You can update specfic details for existing tasks. Via the update command

Command syntax for Todo tasks: `update todo <taskname> /new <data>`
Command syntax for Other tasks: `update <tasktype> <taskname> /<characteristic to change> /new <new data>`

Todo Task Excample: `update todo meet friends /new meet parents`
Deadline Task Example: `update deadline CS1231S HW /name /new Do CS1231S Assignment`


Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
Task Todo meet friends is now changed to meet parents
Task Deadline Do CS1231S HW is now changed to Do CS1231S Assignment
```

## Delete Tasks
This command deletes the specific task based on its index in the list.
Command syntax: `delete <index>`
For example: `delete 2`

```
expected outcome
deletes and removes task 2 from the list
```
