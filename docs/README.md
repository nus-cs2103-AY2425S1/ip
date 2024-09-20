# Milo User Guide

Milo is a task and client management tool designed to streamline your workflow, optimized for use through a Command Line Interface (CLI). It allows users to efficiently manage tasks and client information using simple text-based commands. Whether you're adding new clients, scheduling tasks, or tracking deadlines, Milo ensures quick and easy access to essential features with minimal effort.

- Quick Start
- Features
   1. [List All Clients](#1-list-all-clients)
   2. [Add a New Client](#2-add-a-new-client)
   3. [Delete a Client](#3-delete-a-client)
   4. [List All Tasks](#4-list-all-tasks)
   5. [Find a Task](#5-find-a-task)
   6. [Mark a Task as Complete](#6-mark-a-task-as-complete)
   7. [Mark a Task as Incomplete](#7-mark-a-task-as-incomplete)
   8. [Delete a Task](#8-delete-a-task)
   9. [Add a Todo Task](#9-add-a-todo-task)
   10. [Add a Deadline Task](#10-add-a-deadline-task)
   11. [Add an Event Task](#11-add-an-event-task)
   12. [Say Goodbye](#12-say-goodbye)
---
### Quick Start

1. Ensure you have Java `17` or above installed on your Computer.
2. Download the latest `.jar` file from [here](https://github.com/JumpyJay/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Milo bot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Milo.jar` command to run the application. A GUI similar to the below should appear in a few seconds.

![image](https://github.com/user-attachments/assets/ee27ac5d-b282-4e00-a03e-c924a3edaae9)

5. Type the command in the text box and press Enter to execute it.

   Some examples you can try:
   - `list_task`: List all tasks.
   - `list_client`: List all clients.
   - `delete_task [index]`: Delete task at specified index.
   - `bye`: Exits the app.


---

## **Client Management Commands**

### 1. **List All Clients**
- **Command:** `list_client`
- **Description:** Displays a list of all registered clients in the system.
- **Usage Example:**
```
list_client
```
- **Expected Output:** A list of all clients, showing their names and IDs.

---

### 2. **Add a New Client**
- **Command:** `client [name] [condition]`
- **Description:** Adds a new client to the system with relevant information.
- **Usage Example:**
```
client john lumbago
```
- **Expected Output:**
  ```
  Oh got it! I've added this client:
   john (condition: lumbago)
  Now you have <x> clients in your contacts
  ```

---

### 3. **Delete a Client**
- **Command:** `delete_client [index]`
- **Description:** Deletes an existing client by ID.
- **Usage Example:**
```
delete_client 2
```
- **Expected Output:**
  ```
  Okay~ I've removed this client:
  john (condition: lumbago)
  Now you have <x> clients in your contacts
  ```

---

## **Task Management Commands**

### 4. **List All Tasks**
- **Command:** `list_task`
- **Description:** Displays a list of all tasks.
- **Usage Example:**
```
list_task
```
- **Expected Output:** A list of all tasks, showing their descriptions and statuses (completed or not).

---

### 5. **Find a Task**
- **Command:** `find_task`
- **Description:** Searches for a task using a keyword.
- **Usage Example:**
```
find_task report
```
- **Expected Output:** A list of tasks containing the keyword "report".

---

### 6. **Mark a Task as Complete**
- **Command:** `mark`
- **Description:** Marks a task as complete using its ID.
- **Usage Example:**
```
mark 3
```
- **Expected Output:**
  ```
  Nice! I've marked this task as done:
  <task_details>
  ```
---

### 7. **Mark a Task as Incomplete**
- **Command:** `unmark`
- **Description:** Marks a task as incomplete using its ID.
- **Usage Example:**
```
unmark 3
```
- **Expected Output:**
  ```
  Sure! I've marked this as not done yet:
  <task_details>
  ```

---

### 8. **Delete a Task**
- **Command:** `delete_task`
- **Description:** Deletes a task from the task list by ID.
- **Usage Example:**
```
delete_task 5
```
- **Expected Output:**
- ```
  Alrighty~ I've removed this task:
  <task_details>
  Now you have <x> tasks in the list
  ```

---

## **Task Creation Commands**

### 9. **Add a Todo Task**
- **Command:** `todo [description]`
- **Description:** Adds a new Todo task.
- **Usage Example:**
```
todo Buy groceries
```
- **Expected Output:** 
  ```
  Got it! I've added this task:
   [T][] Buy groceries
  Now you have <x> task in the list
  ```

---

### 10. **Add a Deadline Task**
- **Command:** `deadline [description] /by [end date in format (YYYY-MM-DD)]`
- **Description:** Adds a new task with a deadline.
- **Usage Example:**
```
deadline Submit report /by 2024-09-30
```
- **Expected Output:**
  ```
  Got it! I've added this task:
   [D][] Submit report (by:2024-09-30)
  Now you have <x> task in the list
  ```

---

### 11. **Add an Event Task**
- **Command:** `event [description] /from [start date in format (YYYY-MM-DD)] /to [end date in format (YYYY-MM-DD)]`
- **Description:** Adds a new event task with a date and time.
- **Usage Example:**
```
event Team meeting /from 2024-09-20 /to 2024-09-21
```
- **Expected Output:**
  ```
   Got it! I've added this task:
   [E][] Team meeting (from:2024-09-20 to: 2024-09-21)
  Now you have <x> task in the list
  ```

---

## **Greeting Commands**

### 12. **Say Goodbye**
- **Command:** `bye`
- **Description:** Exits the application with a goodbye message.
- **Usage Example:**
```
bye
```
- **Expected Output:**
  ```
  Bye. Hope to see you again soon!
                   ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
  ```

---
