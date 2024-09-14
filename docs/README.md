# Derrick User Guide
![Description of the screenshot](Ui.png)

Welcome to the Derrick Chatbot! Derrick is designed to help you manage your tasks efficiently. 
You can add new tasks, mark tasks as complete as well as find tasks through easy-to-use chat commands.

## Adding deadlines

Adds a Deadline Task to the current TaskList.
The chatbot will inform you that the Deadline has been added successfully and displays the
Deadline description and due date.


Example: `deadline return books /by 12pm`


```
Got it. I have added this event
[D][] return books (by: 12pm)
```

## Marking/Unmarking an Item

To mark an item as done, simply execute the following command: `mark POSITION`

Example: `mark 1`

```
I have marked this task as done!
[D][X] return books (by: 12pm)
```

To mark an item as not done, simply execute the following command: `unmark POSITION`

Example: `unmark 1`

```
I have marked this task as not done yet!
[D][] return books (by: 12pm)
```


## Finding Tasks

`find KEYWORD` command allows you to find tasks in the current TaskList which contains the specific keyword.

Sample List:
- [x] meeting
- [ ] borrow books
- [ ] return books

Example: `find borrow`

```
Here are the items that matches your keyword:
[D][] borrow books
```

