---
layout: page
title: Asta Bot 
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

![Asta Ui](./Ui.png "Asta Ui")

## Product Introduction: Asta Task Manager

Asta is an intuitive and intelligent task management application designed to
help users stay organized and manage their daily, recurring, and project-related
tasks efficiently. The app is particularly suited for individuals and teams
looking to streamline their workflow and keep track of deadlines, events, and
to-dos with minimal effort.

## Key Features:

1. Task Tracking: Easily create tasks, set deadlines, and manage your workload.
2. Recurring Tasks: Manage recurring events, meetings, or deadlines with
   customizable intervals (daily, weekly, monthly).
3. Smart Input Parsing: Asta interprets natural language commands like repeat
   deadline submit report /by 12/09/2024 1800 /weekly, making task creation
   seamless and user-friendly.
4. Task Management: Mark tasks as complete, unmark them if needed, and delete
   tasks once they’re done. All with simple commands.
5. Task Search: Quickly find tasks based on keywords to stay on top of what
   matters most.
6. Persistent Storage: Tasks are saved automatically, ensuring your to-dos and
   deadlines are always accessible, even after restarting the app.

## Why Use Asta?

- Efficiency: Create tasks quickly with natural language input and let Asta
  handle the rest.
- Recurrence Support: Perfect for managing recurring tasks like weekly
  meetings or monthly reports without needing to re-enter them each time.
- Ease of Use: Simple commands, combined with robust task management features,
  make Asta a breeze to use for both individuals and teams.

## Command Documentation

| No. |     Command Description     |                                   Format                                    |                           examples                            |
|:---:|:---------------------------:|:---------------------------------------------------------------------------:|:-------------------------------------------------------------:|
|  1  |        Add Todo task        |                            `todo <description>`                             |                    `todo read more books`                     |
|  2  |      Add Deadline task      |               `deadline <description> /by <DD/MM/YYYY HHmm>`                |           `deadline read book /by 17/09/2024 2359`            |
|  3  |       Add Event task        |     `event <description> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm>`     | `event return book /from 17/09/2024 2359 /to 18/09/2024 2359` |
|  4  |         Delete task         |                               `delete <task>`                               |                          `delete 1`                           |
|  5  |        Find task(s)         |                              `find <keyword>`                               |                          `find book`                          |
|  6  |         List tasks          |                                   `list`                                    |                            `list`                             |
| 10  |         Mark a task         |                                `mark <task>`                                |                           `mark 1`                            |
| 11  |        Unmark a task        |                               `unmark <task>`                               |                          `unmark 1`                           |
| 12  | Add Recurring Deadline Task | `repeat deadline <description> /by 17/09/2024 2359 /<daily/weekly/monthly>` |   repeat deadline submit report /by 12/09/2024 1800 /weekly   |
