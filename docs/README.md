# ChadGPT User Guide

![Ui.png](Ui.png)

Don't be a lowly minion, be a chad! Let ChadGPT help you track all your minion tasks.

### Adding tasks
Adds a new todo to the task list
```
todo <task>
```
Example:
```
todo eat banana
```
This adds a new todo `eat banana` to the task list

### Adding deadlines
```
deadline <task> /by <date>
```
Example:
```
deadline eat banana /by 2024-11-11
```
This adds a new task `eat banana` with the deadline `2024-11-11`

### Adding events
```
event <task> /from <date> /to <date>
```
Example:
```
event eat banana /from 2024-01-01 /to 2024-12-31
```
This adds a new event `eat banana` that lasts from `2024-01-01` to `2024-12-31`

### Listing all tasks
```
list
```

### Task marking
To mark a task as completed:
```
mark <index of task>
```

To unmark a task: 
```
unmark <index of task>
```

### Deleting tasks
```
delete <task index>
```

### Searching for tasks
```
find <query>
```
Example:
```
find banana
```
This will return a list of all tasks that contain the word `banana`.

### Undo command
Reverts the last executed command
```
undo
```