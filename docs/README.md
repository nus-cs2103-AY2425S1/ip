# ArsenBot User Guide

![UI sample image](Ui.png)

ArsenBot is an interactive chatbot designed to assist users with task management and provide a friendly conversational experience.

## Features

- **Add Tasks**: Easily add tasks of different types.
- **Mark Tasks as Done**: Keep track of completed tasks.
- **List Tasks**: View all your tasks in one place.
- **Delete Tasks**: Remove tasks you no longer need.
- **Find Tasks**: Search for specific tasks in your list.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ArtillerySun/ip.git
   ```
2. **Navigate to the project directory**:
   ```bash
   cd ip
   ```
3. **Build the project**:
   ```bash
   ./gradlew build
   ```

### Running the Bot

To run the ArsenBot, use the following command:

```bash
java -jar build/libs/ArsenBot-all.jar
 ```

## Usage

After starting the application, you can interact with ArsenBot through the graphical user interface. Here are some commands you can use:

### Adding a Task

You can add three types of tasks:

- **ToDo:**
    - Command: `todo [task description]`
    - Example: `todo Finish project report`

- **Deadline:**
    - Command: `deadline [task description] /by [date]`
    - Example: `deadline Submit assignment /by 2024-09-30`

- **Event:**
    - Command: `event [task description] /from [start time] /to [end time]`
    - Example: `event Team meeting /from 2024-09-25 1000 /to 2024-09-25 1100`

ArsenBot will confirm the addition and let you know the current number of tasks.

### Deleting Tasks

To delete a task, type the command `delete [task number]`. For example:

```
delete 1
```

ArsenBot will inform you that the task has been removed.

### Viewing Tasks

To see all your tasks, simply type:

```
list
```

ArsenBot will display a numbered list of your tasks.

### Exiting the Application

To exit, just type:

```
bye
```

ArsenBot will say goodbye and close the application.

## Error Handling

If you enter an incorrect command or incomplete information, ArsenBot will provide user-friendly error messages, guiding you on what is required. For example:

- If you forget to include a description for a ToDo task, ArsenBot will respond with:
  ```
  Error: The todo command requires a description.
  ```