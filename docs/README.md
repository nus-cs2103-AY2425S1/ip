# Meow chatbot user guide
![alt text](image.png)

Are you feeling lonely? Love cat girls? Hate being disorganised?
Here is where the meow chatbot comes in !!! It helps you organise
all your tasks from todo to events incoming !!!

## Adding deadlines
You can add deadline task via the deadline command
it requires an additinal input

Example: `deadline <taskname> /by <time>`

Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
```

## Adding Events

You can add Events task via the event command
it requires an additinal input

Example: `event <eventName> /from <time> /to`

Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
```


## Update feature

You can update specfic details for existing tasks. Via the update command

For Todo tasks: `update <taskname> /new <data>`
For Other tasks: `update <taskname> /<characteristic to change> /new <new data>`

For example: `update Do CS1231S HW /name /new Do CS1231S Assignment`

Do make sure that time is formatted correctly as
`YYYY-MM-DD HHmm`

Expected outcome is that a new deadline task is added and meow informs you

```
expected output
Task Do CS1231S HW is now changed to Do CS1231S Assignment
```