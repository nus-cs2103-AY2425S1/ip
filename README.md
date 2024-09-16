# ChatBaby, a java based task management assistant

This is an individual greenfield Java project for course CS2103. ChatBaby is a simple Java-based chatbot with a graphical user interface (GUI) built using JavaFX. It helps users manage tasks, deadlines, and events through a conversational interface.

## Features

- **Task Management**: Add, list, mark, and unmark tasks.
- **Deadline Tracking**: Keep track of tasks with deadlines.
- **Event Tracking**: Manage tasks that have specific start and end times.
- **Graphical User Interface**: Interact with the chatbot through an intuitive JavaFX-based GUI.
- **Persistence**: Automatically saves tasks to disk and loads them on startup.

## Getting Started

### Prerequisites

- Java 17 or higher
- JavaFX 17 or higher

### Building the Project

To build the project, you'll need to use Gradle as the build tool. Ensure that JavaFX is correctly configured in your `build.gradle` file.

1. Clone the repository:
   ```bash
   git clone https://github.com/jian7490/ip
   cd chatbaby
   ```
2. Build the project using gradle:
   ```bash
   ./gradlew build
   ```
### Running the application: 
   ```bash
   ./gradlew run
   ```
Alternatively, you can run the `Launcher` class directly from your IDE.

### Using the Application

Once the application starts, interact with ChatBaby by entering commands into the text field and pressing the "Send" button. ChatBaby will respond accordingly.

#### Available Commands:

- **Add a task**: `todo <task description>`
- **Add a deadline**: `deadline <task description> /by <yyyy-MM-dd HH:mm>`
- **Add an event**: `event <task description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>`
- **List all tasks**: `list`
- **List tasks that end on a date** (either deadline or event): `listEndingOn <yyyy-mm-dd>`
- **Mark a task as done**: `mark <task number>`
- **Unmark a task**: `unmark <task number>`
- **Delete a task**: `delete <task number>`
- **Find a task**: `find <task description>`
- **Exit the application**: `bye`

### Saving and Loading Data

ChatBaby automatically saves your task list to a file and loads it when you restart the application. The data is stored in `./data/chatBaby.txt`.

### Troubleshooting

- **JavaFX Configuration Error**: Ensure that your `build.gradle` file includes the necessary JavaFX dependencies and VM options for your environment.

## Project Structure

- **Launcher.java**: Entry point of the application, used to launch the JavaFX application and work around classpath issues.
- **Main.java**: The main JavaFX application class, responsible for initializing the GUI and handling application logic.
- **MainWindow.java**: The controller class for the main GUI, handling user input and interaction with `ChatBaby`.
- **DialogBox.java**: Represents a dialog box in the chat interface.
- **ChatBaby.java**: Contains the main logic of the chatbot, including task management and responses.

## Acknowledgements

This project is based on the Duke chatbot framework and extended to include a graphical user interface using JavaFX.



