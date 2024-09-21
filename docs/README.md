# NotGPT User Guide
<p align="center">
    <img alt="Screenshot of notGPT Gui interactions" src="Ui.png"
        width="60%"
    >
</p>

NotGPT is a **desktop app for task management**. 
It's a little relectuctant but it'll get the job done...\
Also includes a data file which stores Tasks between sessions and automatically saves after every commamnd,\
you can edit it if you want but all invalid lines of data will be ignored

 - [Quick start](#quick-start)
 - [Commands](#commands)
 - [Command Summary](#command-summary)
  - [Adding Tasks](#adding-tasks)
    - [Adding a TODO](#adding-todos-todo)`todo`
    - [Adding a DEADLINE ](#adding-deadlines-deadline)`deadline`
    - [Adding an EVENT](#adding-events-event) `event`
  - [Date Formats](#date-formats)
  - [Additional Commands](#additional-commands)
    - [Listing all Tasks](#listing-all-tasks)`list`
    - [Marking a Task](#marking-a-task)`mark`
    - [Unmarking a Task ](#unmarking-a-task)`unmark`
    - [Deleting a Task](#deleting-a-task)`delete`
    - [Finding a Task using keyword](#finding-a-task-using-keyword)`find`
    - [Clearing all Tasks](#clearing-all-tasks)`clear`
    - [Exiting the program](#exiting-the-program)`bye`
  - [Image Sources](#image-sources) 


# Quick Start

1. Ensure you have a Java Runtime `17` or above installed in your computer.
2. Download the latest `.jar` file in the repository.
3. Copy the file to the directory you want to use as the root directory for your NotGPT chatbot.
4. Open a command terminal, `cd` into the directory/folder with the .jar file, 
and use the `java -jar notGPT.jar` command to run the application.
5. or just click on which also works...


# Commands
Commmands are not case sensitive so eg. "todo" and "ToDo" are both valid

Most commands follow the format `[command] <additional info>` without any of the brackets

Note that commands are read as the first string of letters before a white space

## Command Summary

| Command  | Format, Example                                                                                                               |
|----------|-|
| List     | `list` |
| Todo     | `todo DESCRIPTION` <br/>e.g., `todo read book`|
| Deadline | `deadline DESCRIPTION /by STRING` <br/>e.g., `deadline submit assignment /by 2024-11-11` |
| Event    | `event DESCRIPTION /from STRING /to STRING` <br/>e.g., `event Party /from 2024-09-29 /to 2024-11-27` |
| Mark     | `mark INDEX` <br/>e.g., `mark 2`|
| Unmark   | `unmark INDEX` <br/>e.g., `unmark 1`  |
| Delete   | `delete INDEX` <br/>e.g., `delete 3` |
| Find     | `find STRING` <br/>e.g., `find assignment`|
| Bye      | `bye` |


## Adding tasks
#### Adding TODOs: `todo`

Use this command to help you add a TODO item to the task list.

Command: `todo <name_of_TODO_item>`

Example: `todo Finish 2100 Lab 3`

Expected output:

```
Added "Finish 2100 Lab 3" as a new task
I guess you have X tasks now
```

#### Adding DEADLINEs: `deadline`

Use this command to help you add a DEADLINE to the task list.

Command: `deadline <name_of_DEADLINE> /by <Additional info>`

Example: `deadline finish README /by Tomorrow`

Expected output:

```
lol "finish README /by Tomorrow" is a new deadline,
better finish it quick... you have X tasks now
```

#### Adding EVENTs: `event`

Use this command to help you add an EVENT to the task list.\
/to and /from can be used in either order

Command: `event <name_of_EVENT> /from <Additional info> /to <Additional info>`

Example: `event recess week /from Now /to Forever` or `event recess week /to Now /from Forever`

Expected output:

```
Wow "recess week /from Now /to Forever"
is an Event in your life huh? you have X tasks now
```

## Date formats
For `Event` and `Deadline` there are a few Acceptable input formats which you can use for the additional info that will automatically be recognised and converted into 
```LocalDates``` dates in the task itself instead of being stored as a String.

You can use any of the following: yyyy-MM-dd, yyyy.MM.dd, dd-MM-yyyy, dd.MM.yyyy \
Or you can simply input the day itself (e. thursday) or it's shorthand (eg. mon) which will be recognised as the next valid date which that day occurs (if you tyhpe today's day of the week it will  return today).


Example: Let's say today is 20th Sep 2024 then
`Event Carnival /from tues /to 2024-09-27`\
would return the Event `[E][ ] Carnival (From:  24 Sep 2024 to: 27 Sept 2024)`

## Additional Commands
### Listing all tasks

Use this command to view the current task list. Tasks are sorted by order in which they were added into the list

Command: `list`

Example: `list`

Expected output:

```
1. [T][ ] Finish 2100 Lab 3
2. [D][ ] finish README (by: Tomorrow)
3. [E][ ] recess week (from: Now to: Forever)
```

### Marking a task

Use this command to mark a task as done.

Command: `mark <Task index number>`

Example: `mark 1`

Expected output:

```
Marked 1 as completed
use "list" to see changes
```

### Unmarking a task

Use this command to unmark the task back to the status of not done yet.

Command: `unmark <Task index number>`

Example: `unmark 1`

Expected output:

```
marked 1 as uncompleted
use "list" to see changes
```

### Deleting a task

Use this command to delete a task from the task list.

Command: `delete <Task index number>`

Example: `delete 2`

Expected output:

```
Deleted 2
use "list" to see changes
```

### Finding a task using keyword

Use this command to search for all tasks whose description contains the input <String> as a substring

Command: `find <String>`

Example: `find recess`

Expected output:

```
1. [E][ ] recess week (from: Now to: Forever)
```

### Clearing all Tasks

Use this command to add or update tag to a task.

Command: `clear`

Example: `clear`

Expected output: 

```
I removed everything from your task list,
hope you don't regret it...
```


### Exiting the program

ALternate way to close the program from within the Chatbox itself, comes with a goodbye message! (lol)

Command: `bye`

Example: `bye`

Expected output:

```
It's finally over... *yawn*
I'm heading to bed
```

# Image Sources
[Bot profile](https://www.youtube.com/watch?app=desktop&v=fKtJslkLnMw) (Frieren: Beyond Journey's End) \
[User profile picture](https://wysi.fandom.com/wiki/Giga_Chad?file=Giga.jpg) (Giga Chad meme)\
Application Icon was drawn inside powerpoint
