# Kuki Shinobu 🍪🥷
> “When your heart is set on something, you get closer to your goal with each passing day.” – Keqing

![Product Screenshot](Ui.png)

In Genshin Impact, the grind never stops. Whether you’re farming primo gems to pull for your favorite characters, gathering resources for ascension, or completing story quests to unravel the lore, it’s easy to get overwhelmed by the multitude of tasks and goals. Missing a single domain run or forgetting to gather crucial materials can set you back days, leaving you frustrated and scrambling to catch up.

But what if you had someone reliable by your side to ensure you stay organized and on track? That’s where Kuki Shinobu, your trusted companion from the Arataki Gang, comes in. She's here not only to hold you accountable but to ensure you complete all your tasks and hit your goals on time. With Kuki Shinobu as your task manager, you’ll never have to worry about missing out on primo gems, artifacts, or that perfect character build ever again!

She offers:
- **A minimalistic UI** that keeps distractions to a minimum, allowing you to focus on what matters most. 👍
- **Intuitive commands** that make it easy to add, manage, and view your tasks with simple input. 💻
- **Fun gamification elements** that turn task management into a rewarding experience, making you feel more motivated and productive. 🎮

## Setup Guide

1. Download the `KukiShinobu.jar` file from [here](https://github.com/apollo-tan/ip/releases/latest).
2. Move the downloaded file to your preferred directory.
3. Open your terminal in this directory.
4. Execute the command:
   ```bash
   java -jar KukiShinobu.jar
   ```

## Features

| Feature               | Command                                                     | Description                                                      |
| --------------------- | ----------------------------------------------------------- | ---------------------------------------------------------------- |
| [todo](#todo)         | `todo <description> [ #<tag>... ]`                          | Writes a simple reminder note, with optional tags                |
| [deadline](#deadline) | `deadline <description> /by <date> [ #<tag>... ]`           | Writes a deadline note with due date, with optional tags         |
| [event](#event)       | `event <description> /from <date> /to <date> [ #<tag>... ]` | Writes an event note with start and end date, with optional tags |
| [mark](#mark)         | `mark <serial>`                                             | Ticks task of corresponding serial number                        |
| [unmark](#unmark)     | `unmark <serial>`                                           | Unticks task of corresponding serial number                      |
| [delete](#delete)     | `delete <serial>`                                           | Removes task of corresponding serial number                      |
| [find](#find)         | `find <keyword>`                                            | Finds tasks whose description sub-matches the given expression   |
| [list](#list)         | `list`                                                      | Lists all tasks                                                  |
| [bye](#bye)           | `bye`                                                       | Program exits                                                    |


---

### todo

**Command**: `todo <description> [ #<tag>... ]`  
**Feature**: Writes a simple reminder note, with optional tags

**Example**:
- **Input**: `todo Meet Ningguang for business #Liyue #business`
- **Output**:
  ```
  Got it. I've added this task:
  [T][ ] Meet Ningguang for business [#Liyue #business]
  Now you have 1 task in the list.
  ```
---

### deadline

**Command**: `deadline <description> /by <date> [ #<tag>... ]`  
**Feature**: Writes a deadline note with due date, with optional tags

**Example**:
- **Input**: `deadline Complete Mondstadt exploration /by 2024-10-01 #exploration #Mondstadt`
- **Output**:
  ```
  Got it. I've added this task:
  [D][ ] Complete Mondstadt exploration (by: Oct 1 2024) [#exploration #Mondstadt]
  Now you have 2 tasks in the list.
  ```

- **Input**: `deadline Complete Mondstadt exploration /by 2024/10/01`
- **Output**: `Deadline date is in an incorrect format! Please use yyyy-MM-dd.`

---
### event

**Command**: `event <description> /from <date> /to <date> [ #<tag>... ]`  
**Feature**: Writes an event note with start and end date, with optional tags

**Example**:
- **Input**: `event Participate in Ludi Harpastum /from 2024-10-10 /to 2024-10-12 #festival #Mondstadt`
- **Output**: Got it. I've added this task:
  ```
  [E][ ] Participate in Ludi Harpastum (from: Oct 10 2024 to: Oct 12 2024) [#festival #Mondstadt]
  Now you have 3 tasks in the list.
  ```

---

### mark

**Command**: `mark <serial>`  
**Feature**: Ticks task of corresponding serial number

**Example**:
- **Input**: `mark 3`
- **Output**: Nice! I've marked this task as done:
  ```
  [E][X] Participate in Ludi Harpastum (from: Oct 10 2024 to: Oct 12 2024) [#festival #Mondstadt]
  ```

---

### unmark

**Command**: `unmark <serial>`  
**Feature**: Unticks task of corresponding serial number

**Example**:
- **Input**: `unmark 2`
- **Output**: OK, I've marked this task as not done yet:
  ```
  [D][ ] Complete Mondstadt exploration (by: Oct 1 2024) [#exploration #Mondstadt]
  ```

---

### delete

**Command**: `delete <serial>`  
**Feature**: Removes task of corresponding serial number

**Example**:
- **Input**: `delete 1`
- **Output**: Noted. I've removed this task:
  ```
  [T][ ] Meet Ningguang for business
  Now you have 2 tasks in the list.
  ```

---

### find

**Command**: `find <keyword>`  
**Feature**: Finds tasks whose description sub-matches the given expression

**Example**:
- **Input**: `find Diluc`
- **Output**: There are no tasks that contain the keyword!

- **Input**: `find Mondstadt`
- **Output**: Here are the matching tasks in your list:
  ```
  1.[D][ ] Complete Mondstadt exploration (by: Oct 1 2024) [#exploration #Mondstadt]
  ```

---

### list

**Command**: `list`  
**Feature**: Lists all tasks

**Example**:
- **Input**: `list`
- **Output**:
  ```
  1.[D][ ] Complete Mondstadt exploration (by: Oct 1 2024) [#exploration #Mondstadt]
  2.[E][X] Participate in Ludi Harpastum (from: Oct 10 2024 to: Oct 12 2024) [#festival #Mondstadt]
  ```

---

### bye

**Command**: `bye`  
**Feature**: Program exits

**Example**:
- **Input**: `bye`
- **Output**: N.A.

--- 
## Roadmap
- [x] Add, find, and delete tasks.
- [x] Create tasks with deadlines or start/end dates.
- [x] Add tags to tasks to keep organised
- [ ] Modify existing tasks (coming soon).
- [ ] Enhanced UI (coming soon).
- [ ] Ability to interact with other Genshin Impact characters (coming soon).  
