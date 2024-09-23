# REI User Guide
> “Don't put off until tomorrow what you can do today.” - Benjamin Franklin
<p align="center">
    <img src="Ui.png" width="500">
</p>

**REI** is a simple yet promising chatbot, inspired by the charm and creativity of a member from the South Korean girl group [IVE :cupid:](https://kprofiles.com/ive-members-profile/). Though currently in its early stages, **REI** is designed to grow and evolve into a highly useful and intelligent chat companion.

## Tasks
**REI** helps users by efficiently storing tasks. There are three types of task **REI** can handle
1. *ToDo*: A simple, regular task without any specific time constraints. Great for quick reminders or ongoing activities.
2. *Deadline* : A task that needs to be completed by a specific date or time. Perfect for time-sensitive assignments or projects.
3. *Event* : A task with a defined start and finish time, making it ideal for meetings, appointments, or scheduled activities.

**REI** brings a fresh K-pop-inspired twist to task management, making everyday tasks a bit more fun!

## Features
- Create a task of a specific type (*ToDo*/*Deadline*/*Event*) | `todo`, `deadline`, `event`
- Delete a ~task~ | `delete`
- Mark a task as done | `mark`
- Unmark a task as done | `unmark`
- Display the list of tasks | `list`
- Tag a specific task | `tag`
- Delete a tag from a specific task | `untag`
- Find a specific task using keywords or tags | `find`
- Quit | `annyeong`



Example: `unmark 5`


```
Okay! I've marked this task as not done yet:
[D][] project submission (by: Mon, Dec 2 2019 12:00:00)
```

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/rei/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). 