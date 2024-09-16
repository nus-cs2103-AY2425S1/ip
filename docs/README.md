# ShoAI User Guide

![alt text](Ui.png)

## Introduction

ShoAI is a task and client management application designed to help you keep track of tasks and manage clients efficiently. The following guide will walk you through the various commands you can use to interact with the application, including task management and client operations.

## Commands

### Task Management

1. **Show List of Tasks**: `list`

   - **Example Input**: `list`
   - **Example Output**:
     ```
     Behold the mighty list of tasks! 📝
     1.[T][ ] Buy groceries
     2.[D][ ] Finish homework (by: 2024-09-30 17:00)
     3.[E][ ] Team meeting (from: 2024-09-20 09:00 to: 2024-09-20 11:00)
     ```

2. **Mark Task as Done**: `mark [task number]`

   - **Example Input**: `mark 1`
   - **Example Output**:
     ```
     Task marked complete, like a pro! ✅
     [T][X] Buy groceries
     ```

3. **Unmark Task**: `unmark [task number]`

   - **Example Input**: `unmark 1`
   - **Example Output**:
     ```
     Oopsie! Task is back on the to-do list. 🙈
     [T][ ] Buy groceries
     ```

4. **Add To-Do Task**: `todo [description]`

   - **Example Input**: `todo Buy groceries`
   - **Example Output**:
     ```
     A new task has joined the squad! 🎉
     [T][ ] Buy groceries
     Now you have 1 task in the list.
     ```

5. **Add Deadline Task**: `deadline [description] /by [datetime]`

   - **Example Input**: `deadline Finish homework /by 2024-09-30 17:00`
   - **Example Output**:
     ```
     Deadline set, time’s ticking! ⏰
     [D][ ] Finish homework (by: 2024-09-30 17:00)
     Now you have 1 task in the list.
     ```

6. **Add Event Task**: `event [description] /from [start datetime] /to [end datetime]`

   - **Example Input**: `event Team meeting /from 2024-09-20 09:00 /to 2024-09-20 11:00`
   - **Example Output**:
     ```
     Event logged, let the countdown begin! 📆
     [E][ ] Team meeting (from: 2024-09-20 09:00 to: 2024-09-20 11:00)
     Now you have 1 task in the list.
     ```

7. **Delete Task**: `delete [task number]`

   - **Example Input**: `delete 1`
   - **Example Output**:
     ```
     Task deleted, like magic! ✨
     [T][ ] Buy groceries
     Now you have 0 tasks in the list.
     ```

8. **Find Tasks**: `find [keyword]`
   - **Example Input**: `find groceries`
   - **Example Output**:
     ```
     Here’s what I’ve unearthed!
     These are the matching tasks in your list:
     1.[T][ ] Buy groceries
     ```

### Client Management

1. **Add Client**: `addclient [name] /email [email] /phone [phone]`

   - **Example Input**: `addclient John Doe /email john@example.com /phone 1234567890`
   - **Example Output**:
     ```
     Client added:
     Name: John Doe
     Email: john@example.com
     Phone: 1234567890
     ```

2. **Remove Client**: `removeclient [client number]`

   - **Example Input**: `removeclient 1`
   - **Example Output**:
     ```
     Client removed:
     Name: John Doe
     Email: john@example.com
     Phone: 1234567890
     ```

3. **Show List of Clients**: `listclients`
   - **Example Input**: `listclients`
   - **Example Output**:
     ```
     Here’s the client roster! 📝
     1.Name: John Doe
       Email: john@example.com
       Phone: 1234567890
     ```
