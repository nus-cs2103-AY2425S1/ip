
# Diego Task Manager - User Guide

Welcome to **Diego**, a task manager chatbot designed to help you organize your tasks efficiently. This guide will walk you through all the important features of Diego, so you can start using it effectively right away.

---

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
    - [Adding To-Do Tasks](#adding-to-do-tasks)
    - [Adding Deadlines](#adding-deadlines)
    - [Adding Events](#adding-events)
    - [Listing Tasks](#listing-tasks)
    - [Marking and Unmarking Tasks](#marking-and-unmarking-tasks)
    - [Deleting Tasks](#deleting-tasks)
    - [Finding Tasks](#finding-tasks)
    - [Reminders](#reminders)
4. [Command Summary](#command-summary)
5. [Error Messages](#error-messages)
6. [FAQs](#faqs)

---

## Introduction
Diego is a chatbot that helps you manage your tasks through simple text commands. Whether you're managing to-do tasks, deadlines, or events, Diego ensures you can stay organized with minimal effort.

---

## Quick Start
1. **Launch Diego** and wait for the welcome message.
2. **Start adding tasks** using the commands described in the next section.
3. Use `list` to see all your tasks, `mark` to mark them as done, and `delete` to remove any task.
4. Type `bye` when you’re done, and your tasks will be saved automatically for the next session.

---

## Features

### Adding To-Do Tasks
- Add a task that doesn't have a specific deadline.
- **Command**: `todo <description>`
- **Example**:
  ```
  Input: todo Buy groceries
  Output: Got it. I've added this task:
           [T][ ] Buy groceries
  ```

### Adding Deadlines
- Add a task with a specific deadline or due date.
- **Command**: `deadline <description> /by <due date>`
- **Example**:
  ```
  Input: deadline Submit report /by 2024-09-30
  Output: Got it. I've added this task:
           [D][ ] Submit report (by: Sep 30 2024)
  ```

### Adding Events
- Add an event with a start time and an end time.
- **Command**: `event <description> /from <start time> /to <end time>`
- **Example**:
  ```
  Input: event Project meeting /from 2024-09-25 1400 /to 2024-09-25 1600
  Output: Got it. I've added this task:
           [E][ ] Project meeting (from: Sep 25 2024, 2:00 PM to: Sep 25 2024, 4:00 PM)
  ```

### Listing Tasks
- Show a list of all tasks you’ve added.
- **Command**: `list`
- **Example**:
  ```
  Input: list
  Output: Here are the tasks in your list:
           1. [T][ ] Buy groceries
           2. [D][ ] Submit report (by: Sep 30 2024)
           3. [E][ ] Project meeting (from: Sep 25 2024, 2:00 PM to: Sep 25 2024, 4:00 PM)
  ```

### Marking and Unmarking Tasks
- Mark a task as done.
- **Command**: `mark <task number>`
- **Example**:
  ```
  Input: mark 1
  Output: Nice! I've marked this task as done:
           [T][X] Buy groceries
  ```
- Unmark a task to indicate it’s not done.
- **Command**: `unmark <task number>`
- **Example**:
  ```
  Input: unmark 1
  Output: Ok! I've marked this task as not done:
           [T][ ] Buy groceries
  ```

### Deleting Tasks
- Delete a task by its index.
- **Command**: `delete <task number>`
- **Example**:
  ```
  Input: delete 1
  Output: Noted. I've removed this task:
           [T][ ] Buy groceries
  ```

### Finding Tasks
- Find tasks that contain a specific keyword.
- **Command**: `find <keyword>`
- **Example**:
  ```
  Input: find report
  Output: Here are the matching tasks in your list:
           1. [D][ ] Submit report (by: Sep 30 2024)
  ```

### Reminders
- Get a reminder for tasks due within the next specified number of days.
- **Command**: `remind <days>`
- **Example**:
  ```
  Input: remind 7
  Output: Here are the tasks due within the next 7 days:
           1. [D][ ] Submit report (by: Sep 30 2024)
  ```

---

## Command Summary
Here’s a quick reference for all the commands you can use in Diego:

| Command                | Description                                         |
|------------------------|-----------------------------------------------------|
| `list`                 | List all tasks                                      |
| `todo <description>`    | Add a new to-do task                                |
| `deadline <description> /by <due date>` | Add a new deadline task             |
| `event <description> /from <start time> /to <end time>` | Add a new event    |
| `mark <task number>`    | Mark a task as done                                 |
| `unmark <task number>`  | Unmark a task as not done                           |
| `delete <task number>`  | Delete a task                                       |
| `find <keyword>`        | Find tasks containing a specific keyword            |
| `remind <days>`         | Show tasks due within the specified number of days  |
| `help`                 | Show available commands                             |
| `bye`                  | Exit Diego                                          |

---

## Error Messages
If you enter a command incorrectly or use an invalid input, Diego will show an appropriate error message. Below are some common errors and how to resolve them:

- **Unknown Command**: If you enter an invalid command.
    - Example:
      ```
      Input: invalidCommand
      Output: The command you entered is not recognized.
              Please use one of the following formats...
      ```
- **No Description**: If you forget to include a task description.
    - Example:
      ```
      Input: todo
      Output: Please enter a valid task description.
      ```
- **No Index**: If you forget to include the task index for commands like `mark` or `delete`.
    - Example:
      ```
      Input: mark
      Output: Please provide the index of the task.
      ```

---

## FAQs

**Q: Can I edit a task after I’ve added it?**
A: No, you currently need to delete the task and re-add it if you want to make changes.

**Q: What date formats are supported for deadlines?**
A: Diego supports the following date formats:
- `yyyy-MM-dd HHmm`
- `d/M/yyyy HHmm`
- `d MMM yyyy HHmm`
- `MMM d yyyy HHmm`

**Q: How are tasks saved?**
A: Tasks are automatically saved to a file so that they persist across sessions.

**Q: Can I add reminders for specific tasks?**
A: Diego allows you to get reminders for tasks due within a specified number of days using the `remind` command.

---


Thank you for using Diego! We hope this guide helps you get the most out of the chatbot.
