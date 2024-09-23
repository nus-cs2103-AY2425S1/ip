# Diomon User Guide

Want a simple bloatware free task manager? Diomon is a lightweight command-line task management application to help you organise your tasks. With just a few clicks, Diomon is ready to be at ur service!
![Main view of application](/Ui.png)

****
## Table of Contents
- [Setup Guide](#setup-guide)
- [Commands](#commands)
  -[Todo](#adding-todo-task-todo)
  -[Deadline](#adding-deadlines-deadline)
  -[Event](#adding-events-event)
  -[List](#listing-tasks-list)
  -[Mark](#marking-tasks-mark)
  -[Unmark](#unmarking-tasks-unmark)
  -[Bye](#exit-bye)
  -[Help](#help-help)
  -[Find](#finding-tasks-find)
  -[Delete](#deleting-tasks-delete)

****
## Setup Guide
1. Ensure that you have Java `17` or above installed in your computer
2. Download the latest `.jar` [file](https://github.com/EyuGongYi/ip/releases/tag/A-Release). 
3. Open up your terminal, `cd` into the folder and run `java -jar diomon.jar` to run the application
 - `cd path/to/the/folder/that/contain/the/file`
 - `java -jar diomon.jar`

****
## Commands
> Note: Values wrapped in `[]` are parameters that the user need to supply.
> 
> `[Date]` should be in dd-MM-yyyy format. Do include 0 infront for single digit days or month
> 
> `[Index]` should only include whole numbers, can have multiple numbers separated by space.
> 
> `[description]` is the description of your tasks
> 
> `[functions]` name of the commands 

| Commands | Format                                        | Description                                                            | Example                                                     |
|----------|-----------------------------------------------|------------------------------------------------------------------------|-------------------------------------------------------------|
| Todo     | `todo [description]`                          | Adds a Todo task                                                       | `todo buy milk`                                             |
| Deadline | `deadline [description] /by [Date]`           | Adds a Deadline Task                                                   | `deadline buy milk /by 11-09-2001`                          |
| Event    | `event [description] /from [Date] /to [Date]` | Adds a Event Task                                                      | `event flight to manhattan /from 10-09-2001 /to 11-09-2001` |
| List     | `list`                                        | List out all your Tasks                                                | `list`                                                      |
| Mark     | `mark [Index]`                                | Marks task at index as done                                            | `mark 1 2`                                                  |
| UnMark   | `ummark [Index]`                              | Marks task at index as incomplete                                      | `umark 1`                                                   |
| Delete   | `delete [Index]`                              | Delete task at index                                                   | `delete 2`                                                  |
| Find     | `find [description]`                          | Find task with description containing the [description] provided       | `find fold`                                                 |
| Bye      | `bye`                                         | Saves your tasks and closes the application                            | `bye`                                                       |
| Help     | `help [functions]`                            | Provides you with more information regarding the [functions] provided  | `help delete`                                               |

***
## Adding Todo Task: `todo`
Adds a Todo Task

**Format** 
`todo [description]`

**Example** 
- `todo buy milk`
- `todo fold clothes`

**Result:**
```dtd
Task: ( buy milk ) has been added.
Task: ( fold clothes ) has been added.
```

***
## Adding Deadlines: `deadline`
Adds a Todo Task

**Format** 
`deadline [description] /by [Date]`

**Example**
- `deadline buy milk /by 11-09-2001`
- `deadline fold clothes /by 11-09-2001`

**Result:**
```dtd
Task: ( buy milk (by: 11-09-2001 (TUESDAY))) has been added.
Task: ( fold clothes (by: 11-09-2001 (TUESDAY))) has been added.
```

***
## Adding events: `event`
Adds a Todo Task

**Format** 
`event [description] /from [Date] /to [Date]`

**Example**
- `event flight to manhattan /from 10-09-2001 /to 11-09-2001`

**Result:**
```dtd
Task: ( flight to manhattan (From: 10-09-2001 To: 11-09-2001) ) has been added.
```

***
## Listing Tasks: `list`
Lists all the task

**Format**
`list`

**Example**
`list`

**Result:**
```dtd
Here ya go!
1-> [D][ ] buy milk (by: 11-09-2001 (TUESDAY))
2-> [E][ ] flight to manhattan (From: 10-09-2001 To: 11-09-2001)
3-> [T][ ] fold clothes
```

***
## Marking Tasks: `mark`
Marks task as complete

**Format** 
`mark [Index]`

**Example**
- `mark 1 2`

**Result:**
```dtd
(buy milk (by: 11-09-2001 (TUESDAY))) completed!!!
(flight to manhattan (From: 10-09-2001 To: 11-09-2001)) completed!!!
```

***
## Unmarking Tasks: `unmark`
Marks task as incomplete

**Format** 
`unmark [Index]`

**Example**
- `umark 1`

**Result:**
```dtd
( buy milk (by: 11-09-2001 (TUESDAY)) ) has been unmarked
Ya did a little oopies, just like your mom
```

***
## Deleting Tasks: `delete`
Delete task 

**Format** 
`Delete [Index]`

**Example**
- `delete 2`

**Result:**
```dtd
Task ( flight to manhattan (From: 10-09-2001 To: 11-09-2001) ) has been thanosed
```

***
## Finding Tasks: `find`
find tasks containing the prompt given

**Format** 
`find [description]`

**Example**
- `find fold`

**Result:**
```dtd
Remember to keep your search history clean!
2-> [T][ ] fold clothes
```

***
## Help: `help`
Gives more information on how to use the command

**Format** 
`help` / `help [functions]`

**Example**
- `help delete`

**Result:**
```dtd
Type `delete [index]` to delete the task as incomplete.
(E.g `delete 1 3` will remove the first and third item from the task list)
```

***
## Exit: `bye`
Saves and closes the application

**Format** 
`bye`

**Example** 
`bye`

**Expected Outcome:**
the application closes. what else is there?