
---

# **Agave User Guide**

---

## **Introduction**

**Agave** is a task management chatbot designed to help you stay on top of your tasks effortlessly.

---

## **Adding Deadline Task**

The `deadline` command allows you to add tasks with a specific deadline.

- **Action**: Adds a deadline task with the provided description and due date.
- **Outcome**: The task will be saved in the list and displayed with the [D] label, showing the date it's due.

**Format**: `deadline <description> /by <yyyy/MM/dd HHmm>`

### Example:
```bash
deadline Finish assg1 /by 2024/09/22 2359
```

**Expected Output**:
```
Got it. I've added this task:
 [D] [ ] Finish assg1 (by: Sep 22 2024, 11:59 PM)
```

The task will now be saved.

---

## **Adding ToDo Tasks**

The `todo` command allows you to add simple tasks without a deadline.

- **Action**: Adds a new ToDo task.
- **Outcome**: The task will be saved and displayed with the [T] label.

**Format**: `todo <description>`

### Example:
```bash
todo Sell kidney
```

**Expected Output**:
```
Got it. I've added this task:
 [T] [ ] Sell kidney
```

---

## **Adding Event Tasks**

The `event` command allows you to add simple tasks without a deadline.

- **Action**: Adds a new Event task.
- **Outcome**: The task will be saved and displayed with the [E] label, showing the time the event starts and ends.

**Format**: `event <description> /from <yyyy/MM/dd HHmm> /to <yyyy/MM/dd HHmm>`

### Example:
```bash
event meeting /from 2024/09/22 1500 /to 2024/09/22 1700
```

**Expected Output**:
```
Got it. I've added this task:
 [E] [ ] Group meeting (from: Sep 22 2024, 03:00 PM to: Sep 22 2024, 05:00 PM)
```

---

## **Marking a Task as Complete**

The `mark` command allows you to mark tasks as completed.

- **Action**: Marks the task at the given number as done.
- **Outcome**: The task will now be marked with an [X] to show it's completed.

**Format**: `mark <taskNumber>`

### Example:
```bash
mark 1
```

**Expected Output**:
```
Nice! I've marked this task as done:
 [T] [X] Take a nap
```

---

## **Listing All Tasks**

The `list` command displays all tasks.

- **Action**: Shows the full list of tasks.
- **Outcome**: All tasks, including deadlines, todos, and events, and listed with their status.

**Format**: `list`

### Example:
```bash
list
```

**Expected Output**:
```
Here are the tasks in your list:
1. [T] [ ] Sell kidney
2. [D] [ ] Finish assg1 (by: Sep 22 2024, 11:59 PM)
```

---

## **Finding Tasks**

The `find` command helps you locate tasks that contain a particular keyword.

- **Action**: Searches for tasks with the given keyword in the description.
- **Outcome**: Tasks matching the search criteria are shown.

**Format**: `find <keyword>`

### Example:
```bash
find assg1
```

**Expected Output**:
```
Here are the matching tasks in your list:
1. [D] [ ] Finish assg1 (by: Sep 22 2024, 11:59 PM)
```

---

## **Deleting a Task**

The `delete` command allows you to remove a task from the list.

- **Action**: Deletes the task at the specified index.
- **Outcome**: The task is permanently removed from the task list.

**Format**: `delete <taskNumber>`

### Example:
```bash
delete 2
```

**Expected Output**:
```
Task deleted:
 [D] [ ] Finish assg1 (by: Sep 22 2024, 11:59 PM)
```

---

## **Sorting Tasks by Deadline**

The `sort` command sorts all tasks by their deadline.

- **Action**: Reorders tasks chronologically based on their due date.
- **Outcome**: Tasks with deadlines are shown in chronological order.

**Format**: `sort`

### Example:
```bash
sort
```

**Expected Output**:
```
Tasks sorted by deadline.
```

---

## **Exiting the Program**

The `bye` command is used to exit the program.

- **Action**: Closes the application.
- **Outcome**: The chatbot will terminate.

**Format**: `bye`

### Example:
```bash
bye
```

**Expected Output**:
```
Bye. Hope to see you again soon!
```
And the window closes.

---
