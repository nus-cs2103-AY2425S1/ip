# EKuD 🙃 User Guide
~~Duke~~Ekud is out for _**REVENGE**_ against CS2103T students who keep messing with his code,
and **HE... IS... FURIOUS.**
<div style="text-align: center;">
    <img alt="EKuD GUI" src="./Ui.png"/>  
    <p>GUI Interface</p>
</div>

* [Quick Start](#quick-start)
* [Features](#features)
  * [Listing all tasks: `list`](#list)
  * [Adding a regular task: `todo`](#todo)
  * [Adding a task with a deadline: `deadline`](#deadline)
  * [Adding an event: `event`](#event)
  * [Deleting a task: `delete`](#delete)
  * [Finding tasks: `find`](#find)
  * [Marking a task as complete: `mark`](#mark)
  * [Un-marking a completed task: `unmark`](#unmark)
  * [Setting tasks as low or high priority: `set`](#set)
  * [Sorting the tasks: `sort`](#sort)
  * [Saving the task data](#save)
  * [Exiting the program: `exit`](#exit)
* [FAQ](#faq)
* [Command summary](#summary)

<hr>

<h2 id="quick-start">Quick Start</h2>

EKuD invites you to try him out ~~(or else)~~!
1. Ensure that you have Java `17` or above installed on your Computer.
2. Download the latest `EKuD.jar` file from [here](https://github.com/uniqly/ip/releases).
3. Open a command terminal, `cd` into the folder where `EKuD.jar` is located, and start EKuD 
by running the following command:
```
java -jar EKuD.jar
```
4. To run EKuD in the CLI mode, run:
```
java -jar EKuD.jar --text
```

<hr>

<h2 id="features">Features</h2>

> ### ℹ️ Note: Parameters 
> * Words in `{lower-case}` are the parameters supplied by the user.  
> e.g. in `todo {description}`, `{descsription}` is a parameter and can be used as `todo task name`
> * Items in square brackets are optional.  
> e.g. in `sort /by {category} [/order {order}`, can be used as `sort /by description /order ascending` 
> or `sort /by description`.
> * Parameters can be in any order.  
> e.g. if the command specifies `event /from {from} /to {to}`, `event /to {to} /from {from}` is also acceptable.
> * _Commands_ are not case-sensitive, but **_fields_ are case-sensitive**.  
> e.g. `todo`, `Todo`, `TODO`, `ToDo`, etc. are all the same command. But `/from` and `/From` are not the same 
> and only `/lower_case` parameters are recognized.
> * Extraneous parameters for commands will be ignored.  
> e.g. the command `todo task /by 1/4/2024 1200` will be interpreted as `todo task`.

<h3 id="list"></h3>

### Listing all tasks: `list`
Displays all the tasks currently loaded by EKuD and/or added by the user.  
Format: `list`  

<h3 id="todo"></h3>

### Adding a regular task: `todo`
Adds a `todo` task to the task list.  
Format: `todo {description}`
* A `todo` task is one that only has a description.
* `{description}` **cannot be empty or only whitespaces**.
 
Example:
* `todo homework`

<h3 id="deadline"></h3>
### Adding a task with a deadline: `deadline`
Adds a `deadline` task to the task list.  
Format: `deadline {description} /by {deadline}`
* A `deadline` task has both a description and a deadline.
* `{description}` **cannot be empty or only whitespaces**.
* `{deadline}` is a [date input](#date-input).

Example:
* `deadline homework /by 1/4/2024 1200` Adds a task with _"homework"_ that is due on
_1 April 2024, 12:00 PM_ to the task list.

<h3 id="event"></h3>
### Adding an event: `event`
Adds a `event` task to the task list.  
Format: `event {description} /from {start-date} /to {end-date}`
* An `event` has a description and a start and end date.
* `{description}` **cannot be empty or only whitespaces**.
* `{start-date}` and `{end-date}` are both [date inputs](#date-input) and `{start-date}`
must be chronologically before `{end-date}`.

Example:
* `event recess week /from 21/9/2024 0000 /to 29/9/2024 2359` Adds an event _"recess week"_ that lasts from
_21 September 2024, 12:00 AM_ to _29 September 2024, 11:59 PM_ to the task list.

<h3 id="delete"></h3>
### Deleting a task: `delete`
Deletes the specified task from the task list.  
Format: `delete {index}`
* Deletes the task at the specified `{index}`.
* `{index}` refers to the index number shown when running the `list` command.
* `{index}` **must be a positive number and within the range of the list** 1, 2, 3, ..., N.

Example:
* `delete 1` Deletes the 1st task as displayed when running the `list` command.

<h3 id="find"></h3>
### Finding tasks: `find`
Searches for all tasks which `description` contains an **exact** keyword and displays them.
Format: `find {keyword}`
* `{keyword}` is **case-sensitive**, meaning that the task's `description` must contain the exact `{keyword}`.  
e.g. `find task` will not match with `todo Task` but it will match with `todo ttask`.
* `{keyword}` **cannot be empty or only whitespaces**.

Example:
* `find read` Finds all the tasks which `description` contains the word _"read"_ **exactly** and displays them.

<h3 id="mark"></h3>
### Marking a task as complete: `mark`
Marks a specified incomplete task as completed.  
Format: `mark {index}`
* Marks the task at the specified `{index}` as complete.
* The specified task **must not be complete** already.
* `{index}` refers to the index number shown when running the `list` command.
* `{index}` **must be a positive number and within the range of the list** 1, 2, 3, ..., N.

Example:
* `mark 2` Marks the 2nd task in the task list as shown by `list` as completed.

<h3 id="unmark"></h3>
### Un-marking a completed task: `unmark`
Un-marks a specified completed task, making it incomplete. The [opposite](#mark) of mark.  
Format: `unmark {index}`
* Same format as `mark`, except that the specified task **must not be incomplete**.

Example:
* `unmark 2` Un-marks the 2nd task in the task list as shown by `list`, making them incomplete.

<h3 id="set"></h3>
### Setting tasks as low or high priority: `set`
Sets the priority of a specified task as `low` or `high`.  
Format: `set {index} /priority {priority}`
* Sets the priority of the task the specified `{index}`.
* `{index}` refers to the index number shown when running the `list` command.
* `{index}` **must be a positive number and within the range of the list** 1, 2, 3, ..., N.
* `{priority}` must be either `high` or `low` and is not case-sensitive.  
e.g. `high`, `High`, `HIGH`, etc. are equivalent.

<h3 id="sort"></h3>
### Sorting the tasks: `sort`
Sorts the task list by a [specified category](#sorting-category) and displays them.  
Format: `sort /by {category} [/order {order}]`
* Sorts the tasks in the list based on the `{category}` given in the `{order}` specified.
* Sorting changes the actual order in which the tasks are saved, meaning that `list` will display the new
sorted order after sorting.
* `{category}` must be one of `deadline`, `description`, `priority` or `completion`, but it is not case-sensitive.  
e.g. `deadline`, `Deadline`, `DEADLINE`, etc. are equivalent.
* `{order}` is an optional parameter and must be either `ascending` or `descending`, but it is not case-sensitive.  
e.g. `ascending`, `Ascending`, `ASCENDING`, etc. are equivalent.
* If `/order {order}` is **omitted**, `{order}` is assumed to be `ascending`. 

Example:
* `sort /by deadline` Sorts the tasks by ascending `deadline`.   
If a task has no deadline/end-date it is assumed to have a `deadline` after tasks that have a deadline/end-date.
* `sort /by description /order descending` Sorts the tasks by descending `description`.  
i.e. Tasks are sorted in reverse alphabetical order in terms of `description` name.

<h3 id="save"></h3>
### Saving the task data
Task data is automatically saved to the user's local storage after any command that changes the task list.  
i.e. `todo`, `deadline`, `event`, `delete`, `mark`, `unmark`, `set`, and `sort`.
Tasks are read from and written to `[JAR file location]/data/tasks.txt`.
* EKuD automatically detects if the file exists, and creates if it does not.

> ### ⚠️ Caution: Editing
> * Advanced users may directly access the contents of `[JAR file location]/data/tasks.txt` to edit/backup them.
> * If a task is edited to an [invalid format](#save-format), that task will be deleted from the save file upon
> opening the app. Therefore, users are advised to back up their tasks before editing them.

<h3 id="exit"></h3>
### Exiting the program: `exit`
Exits the program.  
Format: `exit`  
Aliases: `bye`

<hr>

<h2 id="faq">FAQ</h2>

<h4 id="date-input"></h4>
### Dates
**Q**: What is a valid date input for _parameters_ that take a in date input.  
**A**: Date inputs must follow the `d/M/yyyy HHmm` format. e.g. If you want to specify _1 April 2024, 01:00 PM_,
you must put `1/4/2024 1300` or `01/04/2024 1300`. If you put other formats such as `1 April 2024, 01:00 PM` 
or `1/4/2024 13:00`, `1/4/24 13000`, etc., an error will be thrown.  
Moreover, the date must be valid (i.e. cannot put _31 April 2024_ even if its formatted `d/M/yyyy HHmm`).

<h4 id="sorting-category"></h4>
### Sorting
**Q**: How does sorting the different categories work?  
**A**: Sorting is done only 1 category at a time; The final ordering is solely determined by comparison function
that compares 2 adjacent tasks by the specified category.  
**Q**: What does `ascending` and `descending` order mean?  
**A**: `ascending` is the default ordering based on the comparison function (which determines which of the two
adjacent tasks should be first based on the category).  
`descending` is sorting but the output of the comparison function is inverted.

<h4 id="save-format"></h4>
### Saving
**Q**: What is the format for task in the save file?  
**A**: The format is given by `{task-code} | {completion} | {priority} | {description}[ | {other-args}...]`
* `{task-code}` is one character code given to each type of task, it is the same one as shown when `list` is called.
* `{complete}` is either `1` or `0` where `1` means completed (and `0` is incomplete).
* `{priority}` is the priority of the task, either `LOW` or `HIGH`.
* `{description}` is the description of the task.
* `{other-args...}` refers to the other parameters for the various types of tasks.
e.g. `event` would have `{start-date} | {end-date}` in-place of `{other-args}...` (**note the delimiter**).
And since `todo` has no other parameters, `{other-args}` will be the empty string (**note the lack of delimiter**)
and thus the optional part `[ | {other-args}...]` is omitted.

<hr>

<h2 id="summary">Command Summary</h2>

| Command  | Format, Examples                                                                                                             |
|----------|------------------------------------------------------------------------------------------------------------------------------|  
| List     | `list`                                                                                                                       |
| Todo     | `todo {description}`<br>e.g.,`todo homework`                                                                                 |
| Deadline | `deadline {description} /by {deadline}`<br>e.g., `deadline homework /by 1/4/2024 1200`                                       |
| Event    | `event {description} /from {start-date} /to {end-date}`<br>e.g, `{event recess week /from 21/9/2024 0000 /to 29/9/2024 2359` |
| Delete   | `delete {index}`<br>e.g., `delete 1`                                                                                         |
| Find     | `find {keyword}`<br>e.g., `find read`                                                                                        |
| Mark     | `mark {index}`<br>e.g., `mark 2`                                                                                             |
| Un-mark  | `unmark {index}`<br>e.g, `unmark 2`                                                                                          |
| Set      | `set {index} /priority {priority}`<br>e.g, `set 1 /priority high`                                                            |
| Sort     | `sort /by {category} [/order {order}]`<br>e.g., `sort /by completion /order descending`                                      |
| Exit     | `exit` or `bye`                                                                                                               |