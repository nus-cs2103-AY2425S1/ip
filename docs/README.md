# Barcus User Guide 🐱

Feeling overwhelmed with tasks? Fear not, Barcus is here.
Barcus is a cute task management c(h)atbot meant to keep track of
your tasks, deadlines and events. Simply type in all your tasks, and let
Barcus take care of you. 🐈

![Barcus Product Screenshot](Ui.png)

****
## Setup guide
1. Ensure that you have Java `17` or above installed in your computer.
2. Download the latest `.jar` file from [here](https://github.com/skyl3-r/ip/releases).
3. Copy the file to the folder you'll use as the home folder for Barcus.
4. Open a command terminal, `cd` into the folder the jar file is in, and use
the command `java -jar barcus.jar` to run the application.

****
## Features
> Note: Variables wrapped in `<>` are parameters supplied by the user. 
> 
> `<date>` has to be in the format dd/MM/yyyy HH:mm
> 
> `<indexn>` has to be an integer
> 
> `<#tag>` has to be a single word
> 
> `<description>` can be a string of any length

| Name | Format                                        | Description                   |
|------|-----------------------------------------------|-------------------------------|
| todo | `todo <description>`                          | Adds a `Todo` task            |
| deadline | `deadline <description> /by <date>`           | Adds a `Deadline` task        |
| event | `event <description> /from <date> /to <date>` | Adds a `Event` task           |
| mark | `mark <index1> ... <indexn>`                  | Marks task at indices as done |
|unmark | `unmark <index1> ... <indexn>` | Unmarks task at indices as done |
| list | `list` | Displays the tasks |
| delete | `delete <index1> ... <indexn>` | Deletes tasks at indices |
| find | `find <description>` | Finds tasks with that description or tag |
| tag | `tag <index> <#tag>` | Tags task at index with `#tag` |
| untag | `untag <index> <#tag>` | Untags task at index with `#tag` |
| bye | `bye` | Saves tasks and exits Barcus |

****
### Adding Todo Task
Adds a `Todo` task.

**Format:** `todo <description>`

**Example:** `todo feed cat`

**Expected Outcome:**
```dtd
Added task: [T][ ] feed cat []
There are 1 task(s) in the list.
```
****
### Adding Deadline Task
Adds a `Deadline` task.

**Format:** `deadline <description> /by <date>`

**Example:** `deadline buy cat food /by 29/09/2024 11:00`

**Expected Outcome:**
```dtd
Added task: [D][ ] buy cat food [] (by: Sep 29 2024 11:00)
There are 2 task(s) in the list.
```
****
### Adding Event Task
Adds a `Event` task.

**Format:** `event <description> /from <date> /to <date>`

**Example:** `event meet cat /from 20/09/2024 10:00 /to 20/09/2024 12:00`

**Expected Outcome:**
```dtd
Added task: [E][ ] meet cat [] (from: Sep 20 2024 10:00 to: Sep 20 2024 12:00)
There are 3 task(s) in the list.
```
****
### Marking Tasks
Marks task at indices as done.

**Format:** `mark <index1> ... <indexn>`

**Example:** `mark 1 3 2`

**Expected Outcome:**
```dtd
Good job! Have marked as done:
[T][X] feed cat []
[E][X] meet cat [] (from: Sep 20 2024 10:00 to: Sep 20 2024 12:00)
[D][X] buy cat food [] (by: Sep 29 2024 11:00)
```
****
### Unmarking Tasks
Unmarks task at indices as done.

**Format:** `unmark <index1> ... <indexn>`

**Example:** `unmark 2 3`

**Expected Outcome:**
```dtd
No prob, have marked as undone:
[D][ ] buy cat food [] (by: Sep 29 2024 11:00)
[E][ ] meet cat [] (from: Sep 20 2024 10:00 to: Sep 20 2024 12:00)
```
****
### Listing Tasks
Displays the tasks.

**Format:** `list`

**Example:** `list`

**Expected Outcome:**
```dtd
Okie, here are your tasks!
1. [T][X] feed cat []
2. [D][ ] buy cat food [] (by: Sep 29 2024 11:00)
3. [E][ ] meet cat [] (from: Sep 20 2024 10:00 to: Sep 20 2024 12:00)
```
****
### Deleting Tasks
Deletes tasks at indices.

**Format:** `delete <index1> ... <indexn>`

**Example:** `delete 1`

**Expected Outcome:**
```dtd
Removed task: 
[T][X] feed cat []
There are 2 task(s) in the list.
```
****
### Finding Tasks
Finds tasks with that description or tag.

**Format:** `find <description>`

**Example:** `find buy`

**Expected Outcome:**
```dtd
Here are the matching tasks!
1. [D][ ] buy cat food [] (by: Sep 29 2024 11:00)
```
****
### Tagging Task
Tags task at index with `#tag`.

**Format:** `tag <index> <#tag>`

**Example:** `tag 1 #shopping`

**Expected Outcome:**
```dtd
Cool beans, have tagged task:
[D][ ] buy cat food [#shopping] (by: Sep 29 2024 11:00)
```
****
### Untagging Task
Untags task at index with `#tag`.

**Format:** `untag <index> <#tag>`

**Example:** `untag 1 #shopping`

**Expected Outcome:**
```dtd
Sureee, have untagged task:
[D][ ] buy cat food [] (by: Sep 29 2024 11:00)
```
****
### Exiting Barcus
Saves tasks and exits Barcus.

**Format:** `bye`

**Example:** `bye`

**Expected Outcome:**
```dtd
Alright, good talk!
```