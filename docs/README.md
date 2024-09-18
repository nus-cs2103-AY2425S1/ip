# Derrick User Guide
![Description of the screenshot](Ui.png)

Welcome to the Derrick Chatbot! Derrick is designed to help you manage your tasks efficiently. 
You can add new tasks, mark tasks as complete as well as find tasks through easy-to-use chat commands.

## Adding todos
Adds a Todo Task to the current TaskList.
The chatbot will inform you that the Todo has been added successfully and displays the
Todo description.

Example: `todo read book`


```
Got it. I have added this todo.
[T][] read book
You have 4 items in your list.
```

## Adding deadlines

Adds a Deadline Task to the current TaskList.
The chatbot will inform you that the Deadline has been added successfully and displays the
Deadline description and due date.


Example: `deadline return books /by 12pm`


```
Got it. I have added this event
[D][] return books (by: 12pm)
You have 4 items in your list.
```

## Adding events

Adds a Event Task to the current TaskList.
The chatbot will inform you that the Event has been added successfully and displays the
Event description, start date and end date.


Example: `event party /from 6pm /to 10pm`


```
Got it. I have added this event.
[E][] party (from: 6pm to: 10pm)
You have 6 items in your list.
```
## Displaying the TaskList

Shows the list of all the tasks currently stored in the TaskList.

Example: `list`

```
Here are the items in your list:
1. [T][X] Read book
2. [D][] Meeting (by: 12pm)
3. [E][X] party (from: 6pm to: 10pm)
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

## Deleting Tasks

To delete an item from the current TaskList, simply execute the following command: `delete POSITION`

Sample List:
- [x] meeting
- [ ] borrow books
- [ ] return books

Example: `delete 2`

```
I have removed this task: 
[T][] borrow books
You have 2 items in your list.
```

Resulting Sample List:
- [x] meeting
- [ ] return books

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

## Exiting the program
Exits the program when executing the command : `bye`

Example: `bye`

```
GoodBye!
```

The chatbot will close after a short period of time (around 3 seconds).
