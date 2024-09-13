# Lolo Chatbot 🤖

Welcome to **Lolo**, your friendly Java-based chatbot! Lolo helps you manage tasks with ease, offering functionalities to add, list, mark, unmark, and delete tasks. With a sleek JavaFX interface, Lolo is designed to make task management efficient and enjoyable.

## Features 🌟

- **Add Tasks**: Easily add ToDos, Deadlines, and Events.
- **Mark Tasks**: Track progress by marking tasks as done or not done.
- **Delete Tasks**: Remove tasks that are no longer needed.
- **View Tasks**: List tasks by type and view them by date.
- **Graphical User Interface**: Enjoy a modern GUI with JavaFX.

## Getting Started 🚀

Follow these steps to get Lolo up and running on your local machine:

### 1. Open the Project in IntelliJ IDEA 🛠️

1. **Open IntelliJ IDEA**: If you are not on the welcome screen, click `File > Close Project` to close any existing project.
2. **Open the Project**: Click `Open` and select the project directory, then click `OK`.
3. **Configure JDK**: Ensure the project is configured to use JDK 17. In the dialog, set the `Project language level` field to the SDK default option.
4. **Accept Defaults**: If prompted, accept the default settings to complete the setup.

### 2. Clone the Repository 🖨️

Open a terminal and clone the repository using the following command:

```bash
git clone https://github.com/chenle228/ip.git
```
Replace your-username with your GitHub username.

### 3. Run the Application 🌐
Navigate to the project directory and run the application with Gradle:

```bash
cd ip
./gradlew run
```
## User Guide 🛠️

### 1. **Add a ToDo Task** 📝
To add a new ToDo task, use the following format:
```bash
todo [task description]
```
**Example:**
```bash
todo Read Chapter 5
```
This command adds a task with the description "Read Chapter 5."

### 2. **Add a Deadline Task** ⏰
To add a new Deadline task, use the following format:
```bash
deadline [task description] /by [yyyy-MM-dd HHmm]
```
**Example:**
```bash
deadline Submit Assignment /by 2024-09-10 2359
```
This command adds a deadline task to "Submit Assignment" by September 10, 2024, 23:59.

### 3. **Add an Event Task** 📅
To add a new Event task, use the following format:
```bash
event [event description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]
```
**Example:**
```bash
event Team Meeting /from 2024-09-15 1400 /to 2024-09-15 1600
```
This command schedules an event "Team Meeting" from September 15, 2024, 14:00 to September 15, 2024, 16:00.

### 4. **List All Tasks** 📋
To view all tasks, simply use the command:
```bash
list
```
This will display all your current tasks with their details.

### 5. **Mark a Task as Done** ✅
To mark a task as done, use the following format:
```bash
mark [task number]
```
**Example:**
```bash
mark 1
```
This command marks the task with number 1 as done.

### 6. **Unmark a Task** ❌
To unmark a task, use the following format:
```bash
unmark [task number]
```
**Example:**
```bash
unmark 1
```
This command changes the status of the task with number 1 to not done.

### 7. **Delete a Task** 🗑️
To delete a task, use the following format:
```bash
delete [task number]
```
**Example:**
```bash
delete 1
```
This command deletes the task with number 1 from your list.

### 8. **Tag a Task** 
To tag a task, use the following format:
```bash
tag [task_number] <tag_name>
```
**Example:**
```bash
tag 1 important
```
This command tags task 1 with the tag "important".

### 9. **Exit the Application** 👋
To exit Lolo, use the command:
```bash
bye
```
This will close the application.

Have fun trying out Lolo! 🙌
