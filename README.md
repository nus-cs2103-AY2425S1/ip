# User Guide for Rudolf: Your Christmas Task Companion 🎄

![image](https://github.com/user-attachments/assets/8a95d379-bd75-49cb-a594-c950347a3a6c)

## Product Overview

> “Christmas isn't a season. It's a feeling.” – Edna Ferber

**Rudolf** is a Java-based chatbot designed to help you manage your Christmas season tasks with a festive twist. With Rudolf, you can manage tasks like `ToDos`, `Deadlines`, and `Events`, all while enjoying Christmas-themed interactions. This task companion ensures that you can focus on the joy of the holidays without worrying about forgetting any important to-do.

## Key Features

- **Manage Tasks**: Easily add, list, delete, and find tasks.
- **Mark Tasks as Done**: Track your progress as you complete tasks.
- **Task Types**: Rudolf supports `ToDos`, `Deadlines`, and `Events` for all kinds of holiday planning.
- **Festive Responses**: Enjoy delightful Christmas-themed messages while organizing your tasks.
  
---

## Command Summary 🎅

| Command                            | Description                                            |
|------------------------------------|--------------------------------------------------------|
| `todo <description>`               | Adds a new ToDo task.                                  |
| `deadline <description> /by <date>`| Adds a new Deadline task with a specific due date.      |
| `event <description> /from <date> /to <date>` | Adds a new Event task with start and end times. |
| `list`                             | Lists all current tasks.                               |
| `mark <task number>`               | Marks the task as completed.                           |
| `unmark <task number>`             | Unmarks the task as incomplete.                        |
| `delete <task number>`             | Deletes a task by its number.                          |
| `find <keyword>`                   | Searches tasks that match the given keyword.           |
| `list sorted by date`              | Lists tasks sorted by date.                            |
| `list sorted by description`       | Lists tasks sorted alphabetically by description.      |
| `bye`                              | Exits the application.                                 |

---

## Examples of Commands

### Add a Task

To add a new task, simply type:
```bash
todo Wrap gifts
```
Rudolf will confirm the task with a festive message:
```bash
Gotcha. I've added this task:
  [T][ ] Wrap gifts
Now you have 1 task in the list. Feliz Navidad!
```

### List Tasks

To view all tasks, use the `list` command:
```bash
list
```
Rudolf will show the tasks in your list:
```bash
Ho Ho Ho! Here are the tasks in your Christmas list:
1. [T][ ] Wrap gifts
2. [D][ ] Buy turkey (by: Dec 24)
3. [E][ ] Christmas Party (from: Dec 24, 6PM to: Dec 24, 11PM)
```

### Mark a Task as Done

Mark tasks as completed using the `mark` command followed by the task number:
```bash
mark 1
```
Rudolf will celebrate your progress:
```bash
Sleigh! I've marked this task as done:
  [T][X] Wrap gifts
```

### Unmark a Task

To unmark a task that was previously marked as done, use the `unmark` command:
```bash
unmark 1
```
Rudolf will update the task's status:
```bash
Alright-o, I've marked this task as not done yet:
  [T][ ] Wrap gifts
```

### Delete a Task

If you want to delete a task, use the `delete` command followed by the task number:
```bash
delete 2
```
Rudolf will confirm the deletion with a festive note:
```bash
Aww okay. I've removed this task:
  [D][ ] Buy turkey (by: Dec 24)
Now you have 2 tasks in the list. Let it snow!
```

### Find a Task

To search for tasks by a specific keyword, use the `find` command:
```bash
find party
```
Rudolf will search through the tasks and show you the matching ones:
```bash
Hooray! The elves found these matching tasks in your list:
1. [E][ ] Christmas Party (from: Dec 24, 6PM to: Dec 24, 11PM)
```

---

## Festive Farewell 🎁

When you're ready to leave, say `bye` and Rudolf will send you off with some holiday cheer:
```bash
bye
```
```bash
Bye~ Hope to see you again!
Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •
•。★Christmas★ 。* 。
° 。 ° ˛˚˛ * Π__。˚
˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛
˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚
And a happy new year!
```

---

Let Rudolf guide you through a stress-free Christmas season so you can enjoy the holidays without a hitch! 🎄✨
```
Whether it's wrapping presents, sending out cards, or hosting a Christmas dinner, let Rudolf and the helpful elves keep track of everything so you can enjoy the magic of Christmas without a hitch. 🎅
