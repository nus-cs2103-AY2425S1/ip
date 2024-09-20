# NotGPT User Guide
<picture>
    <img alt="Screenshot of notGPT Gui interactions" src="Ui.png"
        width="60%"
    >
</picture>

notGPT is a **desktop app for task management**. 
Whether it is a simple TODO, a DEADLINE or an EVENT, 
notGPT is here to ensure that you don't lose sight of what is important.

 - **Quick start**
  - **Adding tasks** 
    - [Adding a TODO](#adding-todos-todo)`todo`
     - [Date formats](#date-formats) 
    - [Adding a DEADLINE ](#adding-deadlines-deadline)`deadline`
    - [Adding an EVENT](#adding-events-event) `event`
  - [Listing all tasks](#listing-all-tasks)`list`
  - [Marking a task](#marking-a-task)`mark`
  - [Unmarking a task ](#unmarking-a-task)`unmark`
  - [Deleting a task](#deleting-a-task)`delete`
  - [Finding a task using keyword](#finding-a-task-using-keyword)`find`
  - [Clearing the list](#clearing-the-list)`clear`
  - [Exiting the program](#exiting-the-program)`bye`


## Quick Start

1. Ensure you have a Java `17` or above installed in your computer.
2. Download the latest `.jar` file in the repository.
3. Copy the file to the folder you want to use as the _home folder_ for your notGPT chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, 
and use the `java -jar notGPT.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. The command lists are as below.


## Commands
Commmands are not case sensitive so eg. "todo" and "ToDo" are both valid

### Adding tasks
#### Adding TODOs: `todo`

Use this command to help you add a TODO item to the task list.
Once added, it can be seen when you list out the tasks.

Command: `todo <name_of_TODO_item> [#<tag_name>]`

Example: `todo submit 2106 lab`

Expected output:

```
Okie, I added it into the list:
  [T][ ] submit 2106 lab
Now you have 1 tasks in the list.
```
##### Date formats
stuff
#### Adding DEADLINEs: `deadline`

Use this command to help you add a DEADLINE to the task list.
Once added, it can be seen when you list out the tasks.

Command: `deadline <name_of_DEADLINE> /by yyyy/mm/dd HH:MM [#<tag_name>]`

Example: `deadline 2103 iP user guide /by 2024/09/20 23:59`

Expected output:

```
Okie, I added it into the list:
  [D][ ] 2103 iP user guide (by: 2024 Sep 20  23:59)
Now you have 2 tasks in the list.
```

#### Adding EVENTs: `event`

Use this command to help you add an EVENT to the task list.
Once added, it can be seen when you list out the tasks.

Command: `event <name_of_EVENT> /from yyyy/mm/dd HH:MM /to yyyy/mm/dd HH:MM [#<tag_name>]`

Example: `event recess week /from 2024/09/21 07:00 /to 2024/09/29 23:00`

Expected output:

```
Okie, I added it into the list:
  [E][ ] recess week (from: 2024 Sep 21  07:00 to: 2024 Sep 29  23:00)
Now you have 3 tasks in the list.
```

### Listing all tasks

Use this command to view the current task list.

Command: `list`

Example: `list`

Expected output:

```
Meow~ Here you are!
1.[T][ ] submit 2106 lab
2.[D][ ] 2103 iP user guide (by: 2024 Sep 20  23:59)
3.[E][ ] recess week (from: 2024 Sep 21  07:00 to: 2024 Sep 29  23:00)
```

### Marking a task

Use this command to mark a task as done.

Command: `mark <number_of_the_item_in_the_list>`

Example: `mark 1`

Expected output:

```
Well done! You have completed this task!
 [T][X] submit 2106 lab
```

### Unmarking a task

Use this command to unmark the task back to the status of not done yet.

Command: `unmark <number_of_the_item_in_the_list>`

Example: `unmark 1`

Expected output:

```
Meow~ Okay we can continue this task!
  [T][ ] submit 2106 lab
```

### Deleting a task

Use this command to delete a task from the task list.

Command: `delete <number_of_the_item_in_the_list>`

Example: `delete 2`

Expected output:

```
I have removed it from the list :)
  [D][ ] 2103 iP user guide (by: 2024 Sep 20  23:59)
Now you have 2 tasks in the list.
```

### Finding a task using keyword

Use this command to search for a task with the keyword.

Command: `find <keyword>`

Example: `find recess`

Expected output:

```
Meow~ Here you are!
[E][ ] recess week (from: 2024 Sep 21  07:00 to: 2024 Sep 29  23:00)
```

### Clearing the list

Use this command to add or update tag to a task.

Command: `tag <number_of_the_item_in_the_list> <tag_name>`

Example: `tag 1 important`

Expected output: 

```
I have tagged this task:)
[T][ ] submit 2106 lab #important
```


### Exiting the program

Say goodbye to the chatbot if you like before you close it.

Command: `bye`

Example: `bye`

Expected output:

```
Bye. Hope I can see you again soon!
Next time bring me some cat food please!!!
```
