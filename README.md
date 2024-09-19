# Crack Chatbot

Crack is an interactive task management chatbot that allows users to manage their to-do lists, deadlines, and events easily. It supports various task types and commands, making it an efficient personal assistant for managing your tasks.

## To run:

```bash
code ./gradlew clean build
code ./gradlew run
```

## Features

### 1. Task Management
- Add `Todo`, `Deadline`, and `Event` tasks.
- List all tasks in your task list.
- Mark or unmark tasks as done.

### 2. Date Support
- Add deadlines and events with specific dates.
- The chatbot understands dates in `yyyy-mm-dd` format and displays them in `MMM dd yyyy` format.

### 3. Task Postponement (Snooze)
- Snooze deadlines and events by updating their dates.
- Use the `snooze` command to postpone a task to a new date.

### 4. Task Search
- Find tasks containing a specific keyword with the `find` command.

## Usage

### 1. Adding Tasks

- **Todo:** Adds a to-do task with a description.
    ```
    todo <description>
    ```
    Example: `todo Read book`

- **Deadline:** Adds a task with a deadline.
    ```
    deadline <description> /by <yyyy-mm-dd>
    ```
    Example: `deadline Submit report /by 2024-09-30`

- **Event:** Adds an event with start and end dates.
    ```
    event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
    ```
    Example: `event Conference /from 2024-10-10 /to 2024-10-12`

### 2. Managing Tasks

- **List Tasks:** Lists all tasks.
    ```
    list
    ```

- **Mark Task:** Marks a task as done.
    ```
    mark <task_number>
    ```

- **Unmark Task:** Unmarks a task (mark as not done).
    ```
    unmark <task_number>
    ```

- **Delete Task:** Deletes a task from the list.
    ```
    delete <task_number>
    ```

### 3. Snoozing Tasks

- **Snooze:** Postpone a deadline or event by providing a new date.
    ```
    snooze <task_number> /to <new_date>
    ```
    Example: `snooze 2 /to 2024-10-05`

### 4. Finding Tasks

- **Find Tasks:** Search for tasks that contain a specific keyword.
    ```
    find <keyword>
    ```
    Example: `find book`

### 5. Exit the Program

- **Bye:** Exits the chatbot.
    ```
    bye
    ```

## Error Handling

Crack handles errors and invalid inputs gracefully. If an invalid command or incorrect format is provided, Crack will show a helpful error message through the GUI.

## Running the Project

1. **Build the Project**:
   - Use Gradle to build the project by running `gradle build` in the project directory.

2. **Run the Chatbot**:
   - After building, run the chatbot using the command:
     ```
     java -jar build/libs/Crack.jar
     ```

## Future Enhancements

- Support for recurring tasks.
- Enhanced reminders for upcoming deadlines.
- Integration with calendar applications.

---

Thank you for using Crack Chatbot! 🎉




# Credits
- [ChatGPT] used in this project to increase productivity and code quality.