# SirPotato - Task Management Chatbot

![Product Screenshot](Ui.png);
SirPotato is your all-in-one task management chatbot. It will handle and manage 
your tasks so that you may **enjoy your day stress-free.** It is optimized for use via a 
Command Line Interface (CLI) while still maintaining an aesthetic Graphical User Interface(GUI). 

## Table of contents

- [Quick Start Guide](#quick-start-guide)
- [Features](#features)
- [Usage](#usage)
  - [Adding Tasks: `add`](#adding-tasks)
  - [Marking Tasks as Done: `mark`](#marking-tasks-as-done)
  - [Unmarking Tasks: `unmark`](#unmarking-tasks)
  - [Deleting Tasks: `delete`](#deleting-tasks)
  - [Listing Task: `list`](#listing-tasks)
  - [Sorting Tasks: `sort`](#sorting-tasks)
  - [Finding a task: `find`](#finding-a-task)
- [Saving Data: `bye`](#saving-data)
- [Known Issues](#known-issues)
- [FAQS](#faqs)
- [Command Summary](#command-summary)

## Quick start guide

1. Ensure you have Java 17 installed on your computer. You can check this by using the command `java -version`
2. Download the latest jar file
3. Copy the file to the folder you want to use as the *home folder*
4. Open your terminal, `cd` into the folder you put the jar file in, and use the following command:
5. `java -jar SirPotato.jar`
6. The app does not contain any sample data, and will initialise a data folder and file for you
7. Refer to the [Features](#features) below for further details on how to use the bot


## Features 

1. **Task Management**: Add, delete, mark, unmark, list, and sort tasks.
2. **Three Types of Tasks**:
   - **Todo**: Tasks without any date/time.
   - **Deadline**: Tasks that need to be completed by a certain date(in dd-mm-yyyy format).
   - **Event**: Tasks that start and end at specific dates/times(also in dd-mm-yyyy format).
3. **Sort Tasks**: You can sort tasks by description or deadline.

## Usage 

### Adding Tasks

You may add three types of tasks, as mentioned in the features.

#### 1. **Todo**
Format:
``` 
todo <description>
```
Example:
``` todo read book ```

#### 2. **Deadline**
Format:
```
deadline <description> /by <date>
```
Remember that the date must be in dd-mm-yyyy format or else it will throw an error
Example:
```deadline submit homework /by 20-10-2024```

#### 3. **Event**
Format:
```
event <description> /from <start-date> /to <end-date>
```
Remember that the date must be in dd-mm-yyyy format or else it will throw an error

Example:
```event annual event /from 10-10-2024 /to 11-10-2024```

### Marking Tasks As Done

To mark a task as completed, use the following command:
```
mark <task_number>
```

Example:
```mark 1```

### Unmarking tasks

To unmark a task as unfinished, use the command:
```
unmark <task_number>
```

Example:
```unmark 1```

### Deleting Tasks

To delete a task, ensure that you refer to the first item as one and that the task is a valid task number
i.e it is not bigger than the size of your task list
Use the following command:
```
delete <task_number>
```
Example:
```delete 4```

### Listing Tasks

To list all your tasks, simply use the following command with no additional information:

```
list
```

### Sorting tasks

You may sort your tasks either by deadline or by description. Note that using this command won't modify your current list, but it will just show you the list in the sorted order you described in ascending order.

Use the following command:
```
sort <category>
```

Example:
```sort description```

The above example command will sort your tasks by description in ascending order

### Finding a task

You may search for a task by searching for a keyword that matches the description of the task. Use the following command

```
find <search_string>
```
example:
```
find book
```
The above command will return any tasks which have a description that contains 'book'.

## Saving Data

Once you are finished with the chatbot, simply type ```bye```. SirPotato will then automatically save your data to the file it loaded earlier. Nothing else is needed from you and you may then close the application by hitting the x in the top left corner of the window. 

## Known issues 

1. **Forgetting to use `bye` when finished.** If you forget to use the `bye` command when exiting, it will not save your data to the file.

## FAQs

**Q:** How do I transfer my data to another Computer?

**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

## Command summary

| Command                        | Description      |Example                               |
|--------------------------------|------------------|----------------------------------------------------------|
| `todo <description>`           | Adds a todo task | `todo Borrow a book`                                      |
| `deadline <description> /by <date>`| Adds a deadline task| `deadline Submit assignment /by 15-09-2014`               |
| `event <description> /from <start_date> /to <end_date>`| Adds an event task                         | `event Team meeting /from 15-10-2024 /to 16-10-2024`      |
| `list`                             | Lists all tasks                                            | `list`                                                   |
| `mark <task_number>`               | Marks a task as done                                       | `mark 1`                                                 |
| `unmark <task_number>`             | Unmarks a task as not done                                 | `unmark 1`                                               |
| `delete <task_number>`             | Deletes a task                                             | `delete 2`                                               |
| `sort description`                 | Sorts tasks by description                                 | `sort description`                                       |
| `sort deadline`                    | Sorts tasks by deadline                                    | `sort deadline` 
| `find <search_string>`       |  Finds tasks matching the search_string | `search book`
| `bye` | Saves your data | `bye`



