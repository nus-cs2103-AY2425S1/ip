# Lict

![Product screenshot](./Ui.png)

**Lict**  is a chatbot that helps users organize and manage tasks effectively.
These tasks are stored locally, allowing users to maintain their task lists even after restarting the application.

## Setting up
**Prerequisites**: Ensure you have **Java 17** or above installed in your computer.

To check your Java version, open your terminal or command prompt and run:

```bash
java --version
```

1. Download the `Lict.jar` file from [here](https://github.com/yockcheng/ip/releases/tag/A-Release).
2. Copy the file to the folder you want to use as the home folder for your Lict chatbot.
3. Open a command terminal, `cd` into the folder you put the jar file in, 
and use the `java -jar Lict.jar` command to run the application. 
A GUI similar to the picture shown above should appear in a few seconds. 

## Features

### 1. Tasks

Lict supports three types of tasks:

- **Todo**: Simple tasks with just a description.
- **Deadline**: Tasks with a specific due date or time.
- **Event**: Tasks with both a start time and an end time.

#### 1.1. Todo

The todo command allows users to add a basic task with a simple description.

```
todo <task description>
```

Example: 
```
todo read book
```

Expected output:

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

#### 1.2. Deadline

The deadline command allows users to add a task with a due date or time.

```
deadline <task description> /by <due date>
```

Example:
```
deadline submit assignment /by 2024-09-20
```

Expected output:

```
Got it. I've added this task:
  [D][ ] submit assignment (by: Sep 20 2024)
Now you have 2 tasks in the list.
```

#### 1.3. Event

The event command allows users to add an event with both a start and end date or time.

```
event <event description> /from <start date and time> /to <end date and time>
```

Example:
```
event staycation /from 2024-09-20 1400 /to 2024-09-22 1200
```

Expected output:

```
Got it. I've added this task:
  [E][ ] staycation (from: Sep 20 2024 2:00pm to: Sep 22 2024 12:00pm)
Now you have 3 tasks in the list.
```
## 2. Task Commands

Lict supports four types of task commands:

- **Mark**: Sets a specific task's completion status as done.
- **Unmark**: Resets a specific task's completion status to not done.
- **Snooze**: Reschedules a deadline or event by setting a new due date or start/end time.
- **Delete**: Remove a specific task from the task list permanently.

#### 2.1. Mark

The mark command allows users to mark a task at a specified index as completed.

```
mark <task number>
```

Example:
```
mark 1
```

Expected output:

```
Nice! I've marked this task as done:
    [T][X] read book
```

#### 2.2. Unmark

The unmark command allows users to unmark a task previously marked as done.

```
unmark <task number>
```

Example:
```
unmark 1
```

Expected output:

```
OK, I've marked this task as not done yet:
    [T][ ] read book
```

#### 2.3. Snooze

The snooze command allows users to postpone a deadline or event by setting a new due date
or new start/end times.

For deadline tasks:
```
snooze <task number> /by <new due date>  
```

For event tasks:
```
snooze <task number> /from <new start time> /to <new end time>  
```

Example:
```
snooze 2 /by 2024-10-01
```

Expected output:

```
Got it. I've snoozed the task:
    [D][ ] submit assignment (by: Oct 1 2024)
```

#### 2.4. Delete

The delete command allows users to remove a specified task from the task list.

```
delete <task number>
```

Example:
```
delete 2
```

Expected output:

```
Noted. I've removed this task:
    [D][ ] submit assignment (by: Oct 1 2024)
Now you have 2 tasks in the list.
```

## 3. General Commands

This section includes commands that help users interact with the Lict chatbot, 
including listing all the current tasks and finding tasks using specific keywords.

#### 3.1. Hello

The hello command starts the conversation with the Lict chatbot.

```
hello
```

Expected output:

```
Hello! I'm Lict!
What can I do for you?
```

#### 3.2. Find

The find command allows users to search for tasks containing a specific keyword.

```
find <keyword>
```

Example:
```
find book
```

Expected output:

```
Here are the matching tasks in your list:
    1. [T][ ] read book
```

#### 3.3. List

The list command displays all the tasks in the task list currently.

```
list
```

Expected output:

```
Here are the tasks in your list:
    1. [T][ ] read book
    2. [E][ ] staycation (from: Sep 20 2024 2:00pm to: Sep 22 2024 12:00pm)
```

#### 3.4. Bye

The bye command ends the conversation with the Lict chatbot.

```
bye
```

Expected output:

```
Bye. Hope to see you again soon!
```

## 4. Saving / Loading
Lict automatically saves the data in a .txt file after any commands that changes the data, 
such as adding, deleting or modifying tasks. The saved data is then loaded from the file when
the chatbot is started, and any corrupted data will be discarded.

The file is saved in the following location:

```
/data/LictData.txt
```

If the file or directory does not exist, Lict will create them automatically.