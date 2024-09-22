# Echo User Guide



![Ui.png](Ui.png)

Echo is a task management application that helps you organize your tasks, deadlines, and events. It supports multiple
task types and features like adding, marking, and deleting tasks, with a clean, easy-to-use interface.




## Quick Start Guide
1. Ensure Java 17 is installed on your device.
2. Download the JAR file from this repository.
3. Place the JAR file in a directory and run it by double-clicking.
4. Type the desired command and click enter or the send button to interact with echo.


## Features


### Adding Todos
Adds a todo task that has no deadline

enter: "todo [task name]"

Example: `todo homework`

```
Got it. I've added this task:
[D] [] homework (by: 2024-09-27)
Now you have 2 tasks in the list.
```


### Adding Deadlines

Adds a deadline task that has a deadline

enter: "deadline [task name] /by [date in YYYY-MM-DD]"

Example: `deadline IP /by 2024-09-23`

```
Got it. I've added this task:
[D] [] IP (by: 2024-09-23)
Now you have 3 tasks in the list.
```

### Adding Events

Adds a event that has a start date and an end date

enter: "event [event name] /from [date in YYYY-MM-DD] /to [date in YYYY-MM-DD]"

Example: `event reccess week /from 2024-09-23 /to 2024-09-27`

```
Got it. I've added this task:
[E] [] reccess week (from: 2024-09-23 to: 2024-09-27)
Now you have 4 tasks in the list.
```


## Feature XYZ

// Feature details