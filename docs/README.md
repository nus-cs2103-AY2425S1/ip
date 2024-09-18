# Chatterbox 

![Chatterbox](docs/Ui.png)
Your personal assistant to navigating this troubled world

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding Todo](#adding-todo)
  - [Adding Deadline](#adding-deadline)
  - [Adding Event](#adding-event) 
  - [Listing Tasks](#listing-tasks)
  - [Marking Task as Done](#marking-task-as-done)
  - [Marking Task as Undone](#marking-task-as-undone)
  - [Deleting Task](#deleting-task)
  - [Finding Task](#finding-task)
  - [Tagging Task](#tagging-task)
  - [Untagging Task](#untagging-task)
  - [Listing all tags](#listing-all-tags)
  - [Searching for all tasks tagged with a tag](#searching-for-all-tasks-tagged-with-a-tag)
  - [Exiting the program](#exiting-the-program)

### Quick Start
1. Ensure you have Java 17 installed.
2. Download the latest Chatterbox.jar from the releases page
3. Double-click the downloaded jar file to launch, if that doesn't work, open a terminal and run `java -jar Chatterbox.jar`

### Features

##### Adding Todo
To add a simple todo, type `todo <description>`
Example of usage:
```todo buy groceries```

##### Adding Deadline
To add a deadline, type `deadline <description> /by <date>`
`<date>` can be in String format or of the format 
<br>
* `dd-MM-yyyy HH:mm`
* `dd-MM-yyyy` 
* `dd/MM/yyyy HH:mm`, 
* `dd/MM/yyyy`


Example of usage
`deadline submit assignment /by 12-12-2021 23:59`

##### Adding Event
To add an event type `event <description> /from <date> /to <date>`
<br> `<date>` can be in String format or of the format
- `dd-MM-yyyy HH:mm`
- `dd-MM-yyyy`
- `dd/MM/yyyy HH:mm`,
- `dd/MM/yyyy`

#### Listing Tasks
To view all current tasks, type `list`.
<br>Example of usage
```list```
<br> 
Chatterbox will display
```
[T][ ] task 1 /tags: tag1 
[D][ ] dead 1 ( by Aug 29 2002, 14:21 )
[E][ ] event 1 ( from Apr 02 2003, 21:12 to Feb 01 2024, 12:00 )
```
#### Marking Task as Done
To mark a task as done, type `mark <task number>`
<br>Where `<task number>` is the index of the task in the list

Example of usage
```mark 1```

#### Marking Task as undone
To mark a task as undone, type `unmark <task number>`
<br>Where `<task number>` is the index of the task in the list
Example of usage
```unmark 1```

#### Deleting Task
To delete a task, type `delete <task number>`
<br>Where `<task number>` is the index of the task in the list
Example of usage
```delete 1```

#### Finding Task
To find a task, type `find <keyword>`
Example of usage
```find assignment```

#### Tagging Task
To tag a task, type `tag /i <task number> /t <tag>`
<br>Where `<task number>` is the index of the task in the list
<br>Where `<tag>` is the tag you want to add to the task
`<tag>` is not case-sensitive (will be converted to lowercase) and should not contain whitespace
<br>Example of usage
```tag /i 1 /t tag1```

#### Untagging Task
To untag a task, type `untag /i <task number> /t <tag>`
<br>Where `<task number>` is the index of the task in the list
<br>Where `<tag>` is the tag you want to remove from the task

Example of usage
```untag /i 1 /t tag1```

#### Listing all tags
To view all tags, type `alltags`
Example of usage
```alltags```

#### Searching for all tasks tagged with a tag
To view all tasks tagged with a tag, type `findtag <tag>`
Example of usage
```findtag tag1```

#### Exiting the program
To exit the program, type `bye`
Example of usage
```bye```


### Command Summary

| Action              | Format                                        | Examples                                          |
|---------------------|-----------------------------------------------|---------------------------------------------------|
| Add Todo            | `todo <description>`                          | `todo buy groceries`                              |
| Add Deadline        | `deadline <description> /by <date>`           | `deadline submit assignment /by 12-12-2021 23:59` |
| Add Event           | `event <description> /from <date> /to <date>` | `event project /from 12-12-2021 /to 12-12-2021`   |
| List                | `list`                                        | `list`                                            |
| Mark as Done        | `mark <task number>`                          | `mark 1`                                          |
| Mark as Undone      | `unmark <task number>`                        | `unmark 1`                                        |
| Delete              | `delete <task number>`                        | `delete 1`                                        |
| Find                | `find <keyword>`                              | `find assignment`                                 |
| Tag                 | `tag /i <task number> /t <tag>`               | `tag /i 1 /t tag1`                                |
| Untag               | `untag /i <task number> /t <tag>`             | `untag /i 1 /t tag1`                              |
| List all tags       | `alltags`                                     | `alltags`                                         |
| Find tasks with tag | `findtag <tag>`                               | `findtag tag1`                                    |
| Exit                | `bye`                                         | `bye`                                             |






  
