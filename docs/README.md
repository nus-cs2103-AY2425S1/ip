# Talkie 👽

![Screenshot of a snippet of Talkie](https://jereeemyyyy.github.io/ip/Ui.png)

## Quick Start 🤖
Talkie is a graphical user interface (GUI) desktop app that helps you
track your upcoming tasks. To start using Talkie, you can:

* Download the .jar file under Releases,
* Navigate to the directory containing the .jar file in terminal,
* run java -jar talkie.jar, and voila!


## Features 🚀
Talkie is packed with various features, such as:

* Addition and deletion of tasks
* Todos
* Events
* Deadlines
* Marking tasks as done
* Unmarking tasks as not done
* Searching tasks with keywords
* Sorting tasks alphabetically

### Adding a Todo: `todo`
Adds a Todo Task into the task list.

#### Format: `todo <TASK_DESCRIPTION>`

#### Example: `todo CS2103T Week 6 Assignment`
A message of acknowledgement that the 
todo task has been added will be shown
```
// Expected Output

Got it. I've added this task:
   [T][ ] CS2103T Week 6 Assignment
You have 1 tasks in the list.
```

### Adding a Deadline: `deadline`
Adds a Deadline Task into the task list.

#### Format: `deadline <TASK_DESCRIPTION> /by <TIME>`
* `<TIME>` should be in the form of `yyyy-MM-dd HHmm`

#### Example: `deadline CS2101 Slides /by 2024-09-17 2359`
A message of acknowledgement that the
deadline task has been added will be shown
```
// Expected Output

Got it. I've added this task:
   [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
You have 2 tasks in the list.
```

### Adding an Event: `event`
Adds a Event Task into the task list.

#### Format: `event <TASK_DESCRIPTION> /from <START_TIME> /to <END_TIME>`
* `<START_TIME>` & `<END TIME>` should be in the form of `yyyy-MM-dd HHmm`

#### Example: `event John's Birthday Party /from 2024-09-19 1600 /to 2024-09-19 2200`
A message of acknowledgement that the
event task has been added will be shown
```
// Expected Output

Got it. I've added this task:
   [E][ ] John's Birthday Party (from: Sep 17 2024 16:00 to: Sep 17 2024 2200)
You have 3 tasks in the list.
```

### Listing all tasks: `list`
Shows a list of all tasks

#### Format: `list`

#### Example: 
```
// Expected Output

Here are the tasks in your list:
1. [T][ ] CS2103T Week 6 Assignment
2. [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
3. [E][ ] John's Birthday Party (from: Sep 17 2024 16:00 to: Sep 17 2024 2200)
```

### Deleting a task: `delete`
Deletes an existing task from the task list

#### Format: `delete <INDEX>`
* Deletes the task at the specified `<INDEX>`. The index refers to the
index number shown in the displayed task list. 
The index **must be a positive integer** 1,2,3,...

#### Example: `delete 2`
```
// Expected Output

Noted! I've removed this task:
  [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
Now you have 2 tasks in the list.
```

### Locating tasks by description: `find`
Finds task whose description contain any of the given keywords. 

#### Format: `find <KEYWORD>`
* The search is case-insensitive. eg. `ate` will match `Ate`
* The order of the keywords does not matter. 
eg. `Han Solo` will match `Solo Han`
* Only the description is searched

#### Example: `find CS2101`
```
// Expected Output

Here are the matching tasks in your list:
1. [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
```

### Marking a task: `mark`
Marks an existing task from the task list

#### Format: `mark <INDEX>`
* Marks the task at the specified `<INDEX>`. The index refers to the
index number shown in the displayed task list.
The index **must be a positive integer** 1,2,3,...

#### Example: `mark 2`
```
// Expected Output

Nice! I've marked this task as done:
 [D][X] CS2101 Slides (by: Sep 17 2024 23:59)
```

### Unmarking a task: `unmark`
Marks an existing task from the task list.

#### Format: `unmark <INDEX>`
* Unmarks the task at the specified `<INDEX>`. The index refers to the
  index number shown in the displayed task list.
  The index **must be a positive integer** 1,2,3,...

#### Example: `unmark 2`
```
// Expected Output

Nice! I've marked this task as done:
 [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
```

### Sorting a task list: `sort`
Sorts the task list in alphanumerical order.

#### Format: `sort`
#### Example:
```
// Expected Output

Your task list after sorting:
1. [T][ ] CS2103T Week 6 Assignment
2. [D][ ] CS2101 Slides (by: Sep 17 2024 23:59)
3. [E][ ] John's Birthday Party (from: Sep 17 2024 16:00 to: Sep 17 2024 2200)
```

### Exiting the program: `bye`
Exits the program and saves the changes.
#### Format: `bye`













