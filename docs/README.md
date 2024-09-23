# Yap User Guide

![image](https://github.com/user-attachments/assets/d37c3060-0062-4960-9bd6-3af1e1ad53b4)


Yap is a lightweight and user-friendly task management app designed to help you stay organized. With Yap, you can easily add, delete, and track tasks to keep your to-do list up to date. Mark tasks as done when completed, and focus on what’s left. Yap’s minimalistic design ensures that managing your tasks is quick and hassle-free, allowing you to focus on getting things done. Stay productive, stay on track – Yap has you covered.

## Feature List and Commands

### Yap has the following list of features, which you can use with the respective commands.
- **Get help**

  Command: help
- **List tasks**

  Command: list
- **Add a To-Do Task**

  Command: todo <task_description>

  Example: todo 
- **Add a Task with a deadline**

  Command: deadline <task_description> /by <yyyy-mm-dd>
- **Add an Event**

  An event has a starting time and a ending time.
  Command: event <task_description> /from <yyyy-mm-dd> to <yyyy-mm-dd>
- **Add a Task that has a fixed duration**

  Command: fixedduration <task_description> /duration <duration_in_hours>
- **Find a task that matches the description provided**

  Command: find <task_description>
- **Mark a task as completed**
  
  Command: mark <task_number>
  **Note:** The task number is the task number provided by the list command.
- **Mark a task as uncompleted**

  Command: unmark <task_number>
  **Note:** Similar to unmark task, the task number is the one provided by the list command.
- **Delete a task from the current list**

  Command: delete <task_number>
  **Note:** Similar to the previous commands, the task number is the one provided by the list command.
