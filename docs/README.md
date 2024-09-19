# Juno User Guide

## Product User Interface
<p style="text-align:center;"><img src="Ui.png" alt="Juno" width="400"/></p>

## Product Introduction

**Juno** is a chatbot designed to assist with task management. It helps users create, track, and manage tasks more efficiently. Key features include adding events, setting deadlines, and searching tasks by keywords, all through a conversational interface. It also supports date/time handling for deadline tracking.

### Technical Overview
- Language: Java
- Dependencies:
  - GSON for storing user tasks
  - JUnit for testing various use cases
  - JavaFX for GUI design

## Getting Started 

To get started with Juno, follow these steps:
1. Ensure you have Java 17 or above installed in your Computer.
2. Under GitHub Releases, download the latest version of the jar file of the Juno chatbot (named `juno.jar`).
3. Open a terminal.
4. From your home directory, navigate to the `Downloads` folder using the `cd` command in the terminal (e.g. `cd Downloads`).
5. Run the command `java -jar juno.jar`. A GUI similar to the one provided above under **Product User Interface** should appear.
6. You can now use Juno! Full list of features os Juno are included below.


## Features
> [!Notes about command format]
> - Words in `UPPER_CASE` and `CURLY BRACKETS` are the parameters to be supplied by the user.
> 
>   e.g. In `add todo {TASK_DESCRIPTION}`, `{TASK_DESCRIPTION}` refers to a parameter.
> 
> - For commands which takes in multiple parameters, e.g.`add deadline`, separate the parameters with a `/` when typing it in the Juno chatbot user interface.
> 
> - For commands that do not take in any parameters, e.g. `list`, please ensure that the command is used exactly as shown in the feature list.

### 1. List Tasks: `list`
Displays all the tasks in the task list.

Format: `list`

Example: `list`

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/494790b4-dfe5-4071-bd64-8705944cd886" alt="ListDemo" width="450"/></p>

The above shows the expected output of `list` if the task list currently has a `deadline` task and an `event` task.

### 2. Add Todo Tasks: `add todo`
Adds a Todo task into the task list.

Format: `add todo {TASK_DESCRIPTION}`

- When adding a Todo task, Juno will check the `{TASK_DESCRIPTION}` with the current `{TASK_DESCRIPTION}` of the tasks in the list. 
- If the `{TASK_DESCRIPTION}`is the same as other `{TASK_DESCRIPTION}` in the list, then the task will NOT be added to the task list.
- This is also case-insensitive. e.g. `Buy dinner` will match `BUY DINNER`.

Example: `add todo Buy dinner`, `add todo Do homework`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/4fb32eb9-0530-4384-9460-0d057c88cd32" alt="TodoDemo" width="450"/></p>

The above shows the expected output of `add todo Buy dinner` if the task list currently has 2 other tasks.

### 3. Add Deadline Tasks: `add deadline`
Adds a Deadline task into the task list, with an end time.

Format: `add deadline {TASK_DESCRIPTION}/{END_TIME}`

- When adding an Event task, Juno will check the `{TASK_DESCRIPTION}` with the current `{TASK_DESCRIPTION}` of the tasks in the list.
- If the `{TASK_DESCRIPTION}`is the same as other `{TASK_DESCRIPTION}` in the list, then the task will NOT be added to the task list.
- This is also case-insensitive. e.g. `Buy dinner` will match `BUY DINNER`.
- For `{END_TIME}`, please indicate it in the following format: `yyyy MM dd hh.mma`, e.g. `2024 12 16 12.16PM`, `2024 09 03 10.16AM`, `2024 06 08 03.16AM`, if not **Juno** will **NOT** add the task to the task list as it can't process the time.

Example: `add deadline Wash shoes/2024 09 20 03.44PM`, `add deadline Submit Assignment/2024 09 20 09.00AM`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/b4468168-2ab7-4da4-b604-12e08faaf848" alt="DeadlineDemo" width="450"/></p>

The above shows the expected output of `add deadline Wash shoes/2024 09 20 03.44PM` if the task list currently has 3 other tasks.

### 4. Add Event Tasks: `add event`
Adds an Event task into the task list, with a start time and an end time.

Format: `add event {TASK_DESCRIPTION}/{START_TIME}/{END_TIME}`

- When adding an Event task, Juno will check the `{TASK_DESCRIPTION}` with the current `{TASK_DESCRIPTION}` of the tasks in the list.
- If the `{TASK_DESCRIPTION}`is the same as other `{TASK_DESCRIPTION}` in the list, then the task will NOT be added to the task list.
- This is also case-insensitive. e.g. `Buy dinner` will match `BUY DINNER`.
- For `{START_TIME}` and `{END_TIME}`, please indicate it in the following format: `yyyy MM dd hh.mma`, e.g. `2024 12 16 12.16PM`, `2024 09 03 10.16AM`, `2024 06 08 03.16AM`, if not **Juno** will **NOT** add the task to the task list as it can't process the time correctly.

Example: `add event Networking/2024 09 20 03.00PM/2024 09 20 06.00PM`, `add event Do Assignment/2024 09 10 07.00PM/2024 09 10 10.00PM`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/e3984f35-6a74-413c-8ede-6e7aa07056d1" alt="EventDemo" width="450"/></p>

The above shows the expected output of `add event Networking/2024 09 20 03.00PM/2024 09 20 06.00PM` if the task list currently has 3 other tasks.

### 5. Mark Task: `mark`
Marks a task from the task list as completed.

Format: `mark {INDEX}`

- Marks a task at a specified `{INDEX}`.
- The index refers to the index number shown in the displayed task list (through using the `list` command).
- The index must be a positive integer 1, 2, 3,...
- If the `{INDEX}` inputted is not in the task list, then no task will be marked.
- e.g. Trying `mark 5` would not work if the task list only has 3 tasks, and there is no task that is the "task number 5" to be marked.
- Attempting to mark an already marked task will not work.

Example: `mark 1`, `mark 3`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/94150c2d-ff50-4eb4-88b3-855484ce1907" alt="MarkDemo" width="450"/></p>

The above shows the expected output of `mark 1`, given the first task in the list is a `Deadline` task with a `{TASK_DESCRIPTION}` of "CS2103T Project".


### 6. Unmark Task: `unmark`
Unmark a task from the task list as uncompleted.

Format: `unmark {INDEX}`

- This command is the opposite of `mark` command above.
- Unmarks a task at a specified `{INDEX}`.
- The index refers to the index number shown in the displayed task list (through using the `list` command).
- The index must be a positive integer 1, 2, 3,...
- If the `{INDEX}` inputted is not in the task list, then no task will be unmarked.
- e.g. Trying `unmark 5` would not work if the task list only has 3 tasks, and there is no task that is the "task number 5" to be unmarked.
- Attempting to unmark an already unmarked task will not work.

Example: `unmark 1`, `unmark 3`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/0b8db9f7-6016-4ff8-864a-186936f26c87" alt="UnmarkDemo" width="450"/></p>

The above shows the expected output of `unmark 1`, given the first task in the list is a `Deadline` task with a `{TASK_DESCRIPTION}` of "CS2103T Project".

### 7. Delete Task: `delete`
Deletes a task from the task list.

Format: `delete {INDEX}`

- Deletes a task at a specified `{INDEX}`.
- The index refers to the index number shown in the displayed task list (through using the `list` command).
- The index must be a positive integer 1, 2, 3,...
- If the `{INDEX}` inputted is not in the task list, then it would not delete successfully.
- e.g. Trying `delete 5` would not work if the task list only has 3 tasks, and there is no task that is the "task number 5" to be deleted.

Example: `delete 1`, `delete 3`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/5d5c6b65-ac2c-4a92-9da1-22b0f33ed35b" alt="DeleteDemo" width="450"/></p>

The above shows the expected output of `delete 2`, where the second task in the list is a `Todo` task.


### 8. Find Task: `find`
Finds a task from the task list by task description or its task type.

Format: `find {KEYWORD}` 

- Finds a task based on the task description (e.g. `homework`) and the task type (e.g. `deadline`, `event`, `todo`).
- The search is case-insensitive. e.g `homework` will match `HOMEWORK`.
- Uses exact case-insensitive string matching, e.g. if a task `homework` is in the list, using `find homeworkk` will not work.

Example: `find homework`, `find deadline`.

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/62fe2f07-53ec-4efd-9ab6-2ab6d4084e82" alt="FindDemo" width="450"/></p>

The above shows the expected output of `find deadline`, and 2 deadline tasks that is in the task list is being returned.

### 9. Remind Tasks: `remind`
Reminds the user of upcoming task.

Format: `remind`

- Reminds the user of task that is going to be due in 12 hours and **NOT** yet being marked.
- Also reminds the user of overdue tasks that are not yet being marked.
- Only work on task types with a `{END_TIME}`, i.e. only for `event` and `deadline` tasks.
- E.g. `add deadline Wash shoes/2024 09 20 03.44PM` followed by`remind` will only send a reminder to users on the GUI if the current time is after `2024 09 20 03.44AM` **AND** if the task is not being marked.

Example: `remind`

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/6f17e0de-724f-4664-8fc7-0ec233d44797" alt="RemindDemo" width="450"/></p>

The above shows the expected output of `remind`, given the task list currently has a `deadline` task that is not yet marked and due within 12 hours.

### 10. Exit GUI: `bye`
Exits the Juno chatbot interface.

Format: `bye`

- When `bye` command is executed, it will wait for 3 seconds before closing the GUI, to simulate the behaviour of "shutting down" the system.

Example: `bye`

Expected output:
<p style="text-align:center;"><img src="https://github.com/user-attachments/assets/15f5dfff-6572-40fe-8625-4f259b8e7a92" alt="ListDemo" width="450"/></p>


The above shows the expected output of `bye`.


## Conclusion

Thank you for using Juno! 
