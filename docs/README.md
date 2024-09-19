# BLACKNUT

Blacknut is a personal task manager chatbot that helps you manage your tasks, deadlines, and events efficiently. It's designed to be simple and intuitive to use for users of all levels.

## Setting up in IntelliJ

**Prerequisites**:
- JDK 17
- Latest version of IntelliJ IDEA

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close any existing project).
2. Open the project into IntelliJ as follows:
    1. Click `Open`.
    2. Select the Blacknut project directory, and click `OK`.
    3. If there are any prompts, accept the defaults.
3. Configure the project to use **JDK 17** as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk). In the same dialog, set the **Project language level** to `SDK default`.
4. Locate the `src/main/java/Blacknut.java` file, right-click it, and choose `Run Blacknut.main()`.

If the setup is correct, you should see an output similar to the following:


## Features

### 1. Add Tasks
You can add different types of tasks:
- **Todo**: A simple task with no deadline.
- Example: `todo Read a book`
- **Deadline**: A task with a specific deadline.
- Example: `deadline Submit assignment /by 2023-12-01 18:00`
- **Event**: A task with a start and end time.
- Example: `event Team meeting /from 2023-12-01 09:00 /to 2023-12-01 11:00`

### 2. View Tasks
List all tasks:
- Command: `list`

### 3. Mark Tasks
Mark a task as done:
- Command: `mark 1` (Marks the first task in the list as done)
- Command: `unmark 1` (Unmarks the first task in the list)

### 4. Delete Tasks
Delete a task from the list:
- Command: `delete 1`

### 5. Find Tasks
Find tasks that contain a specific keyword:
- Command: `find book`

### 6. Reminders
Blacknut reminds you of deadlines and events due today.
- Command: `remind`

## Exiting the Program
To exit the chatbot:
- Command: `bye`

## Saving Data
Blacknut saves your tasks automatically in a local file after every change, so your progress is never lost.

## Usage Example
Below is an example of a task list being managed in Blacknut:

todo Read a book

Got it. I've added this task: [T][ ] Read a book

deadline Submit assignment /by 2023-12-01 18:00

Got it. I've added this task: [D][ ] Submit assignment (by: Dec 01 2023, 18:00)

event Team meeting /from 2023-12-01 09:00 /to 2023-12-01 11:00

Got it. I've added this task: [E][ ] Team meeting (from: Dec 01 2023, 09:00 to: Dec 01 2023, 11:00)

list Here are the tasks in your list:

[T][ ] Read a book
[D][ ] Submit assignment (by: Dec 01 2023, 18:00)
[E][ ] Team meeting (from: Dec 01 2023, 09:00 to: Dec 01 2023, 11:00)
mark 1 Nice! I've marked this task as done: [T][X] Read a book

bye Bye. Hope to see you again soon!



