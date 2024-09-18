# Zero User Guide

Zero is a task management chatbot that helps users manage their to-do lists efficiently through a command-line interface or a GUI.
It allows users to add, delete, mark tasks as done, snooze deadlines, and more.

## Command List

| **Feature**                | **Command**                                                  | **Example Usage**                              |
|----------------------------|--------------------------------------------------------------|------------------------------------------------|
| **Adding Deadlines**        | `deadline <description> /by <date and time>`                 | `deadline return book /by 2024-09-01 1800`     |
| **Adding Events**           | `event <description> /from <start date and time> /to <end date and time>` | `event project meeting /from 2024-09-01 1400 /to 2024-09-01 1600` |
| **Adding Todos**            | `todo <description>`                                         | `todo read book`                               |
| **Marking Tasks as Done**   | `mark <task number>`                                         | `mark 1`                                       |
| **Unmarking Tasks**         | `unmark <task number>`                                       | `unmark 1`                                     |
| **Snoozing Deadlines**      | `snooze <task number>`                                       | `snooze 2`                                     |
| **Deleting Tasks**          | `delete <task number>`                                       | `delete 3`                                     |
| **Listing All Tasks**       | `list`                                                       | `list`                                         |
| **Finding Tasks by Keyword**| `find <keyword>`                                             | `find book`                                    |
| **Exiting the Application** | `bye`                                                        | `bye`                                          |

---

