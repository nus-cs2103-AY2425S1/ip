# Chatsy

![Screenshot of UI](Ui.png)

Chatsy is a personalised task management chatbot.

## Quick start

1. Ensure you have Java 11 or above installed in your computer
2. Download the latest `chatsy.jar` 
3. Copy the file to the folder you want to use as the home folder for the application
4. Open a command terminal, `cd` to the folder you put the `jar` file in, then use `java -jar chatsy.jar` command to run the application. You should see the GUI of the application and a `data` folder being created in the application's folder
5. Try interacting with the chat bot using the commands [below](#features)!



## Features

- [create tasks](#create-tasks---todo-deadline-event)
- [list](#list-tasks---list)
- [mark as done or undone](#mark-tasks---mark-unmark)
- [delete](#delete-tasks---delete)
- [find](#find-tasks---find)

### Create tasks - `todo`, `deadline`, `event`

You can create three different types of tasks, specifying task description, and optionally, task deadline or period.

**Format:**

`todo <description>`

`deadline <description> /by <deadline>`

`event <description> /from <from> /to <to>`

**Example usage:**

`event Project meeting /from 2024-09-25 14:00 /to 2024-09-25 15:30
`

**Expected output:**

```
Got it. I've added this task.
Now you have 4 tasks in the list
```



### List tasks - `list`

You can list everything in your task list.

**Format:**

`list`

**Expected output:**

```
1.[T][ ] Buy groceries
2.[D][ ] Submit Assignment (by: Sep 30 2024)
3.[E][ ] Project meeting (from: Sep 25 2024 14:00 to: Sep 25 2024 15:30)
```



### Mark tasks - `mark`, `unmark`

You can mark your tasks as done or undone.

**Format:**

`mark <index>`

`unmark <index>`

**Example usage:**

`mark 2`

**Expected output:**

```
Nice! I've marked this task as done:
[D][X] Submit Assignment (by: Sep 30 2024)
```



### Delete tasks - `delete`

Unwanted tasks can be removed from the task list.

**Format:**

`delete <index>`

**Example usage:**

`delete 2`

**Expected output:**

```
Noted. I've removed this task:
Now you have 2 tasks in the list.
```



### Find tasks - `find`

You can search your tasks based on task description.

**Format**:

`find <search string>`

**Example usage:**

`find Buy`

**Expected output:**

```
Here are the matching tasks in your list:
1.[T][ ] Buy groceries
```


## Usage

### Run the GUI application in command terminal:

`java -jar chatsy.jar`