Shrimp User Guide
==
![Shrimp UI](./Ui.png)

## Introduction
Welcome to the Shrimp chatbot! This guide will help you navigate the commands available to manage your tasks effectively. Shrimp is designed to assist you with managing your to-do list, setting deadlines, scheduling events, and more.

> Manage Tasks, Free Your Mind

Shrimp is a fast and lightweight task manager designed to keep you organized without getting in your way. Whether you need to keep track of tasks, manage deadlines, or get reminders, Shrimp has you covered.

```javascript
                   .__          .__               
              _____|  |_________|__| _____ ______ 
             /  ___/  |  \_  __ \  |/     \\____ \
             \___ \|   Y  \  | \/  |  Y Y  \  |_> >
            /____  >___|  /__|  |__|__|_|  /   __/
                 \/     \/               \/|__|    
```

## How to Use:

1. **Ensure Java 17 or Above**: Verify that Java is installed and added to your path by running `java --version` in your terminal.
2. **Download**: Obtain the latest release of Shrimp from [GitHub Releases](https://github.com/RadieonAjax/ip/releases).
3. **Run Shrimp**:
    - Open your terminal.
    - Navigate to the folder containing `shrimp.jar`.
    - Execute the following command:
      ```bash
      java -jar shrimp.jar
      ```
4. **Explore Commands**: Use the [commands overview](#commands-overview) to familiarize yourself with available commands.

## Commands Overview
> [!NOTE]
> The format is given as it is. You should replace the text in `<...>` with the parameters you intend to use.

| **Function**            | **Command** | **Format**                                                        | **Example**                                                      |
|-------------------------|-------------|-------------------------------------------------------------------|------------------------------------------------------------------|
| **Exit the bot**        | `bye`       | `bye`                                                             | `bye`                                                            |
| **List tasks**          | `list`      | `list`                                                            | `list`                                                           |
| **Mark task as done**   | `mark`      | `mark <task_number>`                                              | `mark 1`                                                         |
| **Unmark task as done** | `unmark`    | `unmark <task_number>`                                            | `unmark 2`                                                       |
| **Add a Todo**          | `todo`      | `todo <description>`                                              | `todo Buy groceries`                                             |
| **Add a Deadline**      | `deadline`  | `deadline <description> /by <date_time>`                          | `deadline Finish homework /by 2024-09-20 15:00`                  |
| **Add an Event**        | `event`     | `event <description> /from <start_date_time> /to <end_date_time>` | `event Team meeting /from 2024-09-21 09:00 /to 2024-09-21 11:00` |
| **Delete a task**       | `delete`    | `delete <task_number>`                                            | `delete 3`                                                       |
| **Clear all tasks**     | `clear`     | `clear`                                                           | `clear`                                                          |
| **Find tasks**          | `find`      | `find <keyword>`                                                  | `find project`                                                   |

## Detailed Command Descriptions
### Exit the bot
- **Command**: `bye`
- **Description**: Exits the chatbot application.

### List tasks
- **Command**: `list`
- **Description**: Lists all tasks currently in the task list.

### Add a Todo
- **Command**: `todo <description>`
- **Description**: Adds a new Todo task with the given description.
- **Example**: `todo Buy groceries`

### Add a Deadline
- **Command**: `deadline <description> /by <date_time>`
- **Description**: Adds a new Deadline task with the given description and due date/time.
- **Example**: `deadline Finish homework /by 2024-09-20T15:00`

### Add an Event
- **Command**: `event <description> /from <start_date_time> /to <end_date_time>`
- **Description**: Adds a new Event task with the given description, start date/time, and end date/time.
- **Example**: `event Team meeting /from 2024-09-21T09:00 /to 2024-09-21T11:00`

### Mark task as done
- **Command**: `mark <task_number>`
- **Description**: Marks the specified task as done.
- **Example**: `mark 1`

### Unmark task as done
- **Command**: `unmark <task_number>`
- **Description**: Unmarks the specified task, marking it as not done.
- **Example**: `unmark 2`

### Delete a task
- **Command**: `delete <task_number>`
- **Description**: Deletes the specified task from the task list.
- **Example**: `delete 3`

### Clear all tasks
- **Command**: `clear`
- **Description**: Clears all tasks from the task list.

### Find tasks
- **Command**: `find <keyword>`
- **Description**: Finds and lists tasks containing the specified keyword.
- **Example**: `find project`

## Error Handling

The Shrimp chatbot handles several common errors:
- **Invalid command format**: Missing or incorrect parameters, special characters.
- **Data integrity issues**: Invalid date/time formats, etc.
- **Environment issues**: Missing files, etc.

## Conclusion

If you have any questions or need further assistance, feel free to reach out. Enjoy using Shrimp!
