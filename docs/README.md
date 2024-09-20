# Shnoop User Guide

![Screenshot of an application ChatBot showing two dogs conversing in English](/Ui.png)

> You could travel the world, but nothing comes close to the Shnoop chatbot <3

#### Shnoop is a simple ChatBot coded in Java and IntelliJ. It is designed as a simple to-do-list application that can fulfil your California dreams. 

## Features

> [!CAUTION]
> Avoid including excess or unwanted whitespaces in your commands.

### Viewing available commands

Shows a message explaining all available commands.

Just begin by typing anything! You will be provided with instructions if your input is invalid.

Example: `hello my dawg!`

An example that Shnoop will respond with:

```
Try typing 'todo', 'event' or 'deadline' followed by stating the task description.
```
___
### Adding a todo-task: ```todo``` / ```deadline``` / ```event```

#### Add a todo task to the list
Format: ```todo [task_details]```

Examples:
```todo Research on adopting dogs```

Resultant task added:
```
[T] [ ] Research on adopting dogs
```
___
#### Add a deadline task to the list
Format: ```deadline [task_details] [/by {task_deadline}]```

Examples:
```deadline Contact Tom on Dog Things /by Monday```

Resultant task added:
```
[D] [ ] Contact Tom on Dog Things (by: Monday)
```

> [!TIP]
> Dates in the YYYY-MM-DD format will be automatically converted to text

Examples:
```deadline Contact Tom on Dog Things /by 2024-06-06```

Resultant task added:
```
[D] [ ] Contact Tom on Dog Things (by: Jun 6 2024)
```
___
#### Add an event task to the list
Format: ```event [task_details] [/from {task_start}] [/to {task_end}]```

Examples:

```event Dog Adoption Party /from Aug 8 2024 /to Aug 10 2024```

```event Dog Adoption Party /from 2024-08-08 /to 2024-08-10```

Resultant task added:
```
[E] [ ] Dog Adoption Party (from: Aug 8 2024 to: Aug 10 2024)
```
___
### Listing all tasks: ```list```

Shows a list of all tasks in the todo-list.

Format: ```list```

> [!TIP]
> If you type ```list 1```, it will sort the list alphabetically
___
### Deleting tasks: ```delete```

Deletes a specified task. Specify task number based on the numbering seen in ```list```.

Format: ```delete [task_index]```

Examples:
```delete 3```

> [!WARNING]
> The task number is based on the number the task is associated with when the ```list``` command is run, do not base the number on the sorted variant of the list command (i.e ```list 1```)
___
### Mark a task: ```mark```

Marks a specified task. Specify task number based on the numbering seen in
```list```.

Format: ```mark [task_index]```

Examples:
```mark 3```

Example of a marked task:
```
[E] [X] Dog Adoption Party (from: Aug 8 2024 to: Aug 10 2024)
```
___
### Unmark a task: ```unmark```

Unmarks a specified task. Specify task number based on the numbering seen in
```list```.

Format: ```unmark [task_index]```

Examples:
```unmark 3```

Example of an umarked task:
```
[E] [ ] Dog Adoption Party (from: Aug 8 2024 to: Aug 10 2024)
```
___
### List all tasks with given phrase: ```find```

Lists all tasks whose description contain the given keyword / phrase.

Format: ```find [insert any word or phrase]```

Examples:
```find dog```

An example of a resultant listing:
```
[T] [ ] Research on adopting dogs
[E] [X] Dog Adoption Party (from: Aug 8 2024 to: Aug 10 2024)
```
___
### Saving all changes: ```bye```

Saves all changes made to the task list.

Format: ```bye```

> [!IMPORTANT]
> If you do not wish to save the tasks you have added or delete, you can use this aspect of the app as an undo feature. Hence, if you wish to undo / not save progress, do not type ```bye```, otherwise, remember to use this command to save all progress.
___