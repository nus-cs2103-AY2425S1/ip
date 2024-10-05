# Kuki Shinobu
The **Kuki Shinobu Bot** is a Java-based application designed to help users manage tasks such as to-do items, deadlines, and events. Built with Object-Oriented Programming (OOP) principles, it draws inspiration from **Kuki Shinobu**, a character from the game *Genshin Impact*.

This project originated from the greenfield Java template, inspired by the Duke chatbot, and has been reimagined as **Kuki Shinobu**, an enhanced bot for task management. This README provides technical documentation on the bot's architecture, setup instructions, and other key information for developers.

## User Guide
For a detailed user guide, refer to the [User Guide](https://apollo-tan.github.io/ip/).

## Technical Overview
Kuki Shinobu is a task management bot written in Java. It helps users create, track, and manage various types of tasks including to-dos, deadlines, and events. The bot processes user commands via a text-based interface and stores tasks with optional tagging for better organization.

### Key Features:
1. **Task Management:**
   - Supports three types of tasks: `Todo`, `Deadline`, and `Event`.
   - Each task can be marked as done or deleted.
   - Users can assign tags to tasks for better categorization.

2. **Tagging System:**
   - Tags are stored in a `HashSet` for fast look-up.
   - Tags are serialized into the database format as `"[tag1,tag2]"`.
   - Parsing functions ensure proper handling of empty or missing tags.

3. **Command Parsing:**
   - A robust command parser that handles a variety of commands such as adding tasks, marking them as done, and finding tasks by keyword.
   - Error handling is achieved via custom exceptions, like `IndexOutOfBoundsKukiShinobuException`, to ensure meaningful error messages and proper task indexing (1-based index).

4. **File Storage:**
   - The bot persists task data in a text file using a database-like format.
   - On startup, the bot parses the stored data to reconstruct tasks with their tags and states.

5. **User Interface (GUI):**
   - A GUI was created using JavaFX, which enables a more interactive experience for users.
   - The application follows the Model-View-Controller (MVC) architecture to separate logic, UI, and data handling.

### System Requirements:
- **JDK Version**: The bot is developed and tested on **Java 17** to ensure compatibility. We recommend using Java 17 to avoid version compatibility issues.
- **JavaFX**: The GUI requires JavaFX components to be properly installed and configured. Follow the setup guide linked below for more details.

### Project Structure:
- **`src/main/java`**: Contains the main Java classes, including `KukiShinobu.java`, the main driver class.
- **Commands**: Each user command (e.g., add, delete, list) is implemented as its own class under the `command` package.
- **Tasks**: Task types (`Todo`, `Deadline`, `Event`) and related logic are located in the `task` package.
- **Exceptions**: Custom exceptions for the bot are located in the `exception` package.
- **Tags**: Tag handling and related functionality can be found in the `tag` package.

## Setting up Kuki Shinobu in IntelliJ

Prerequisites:
- **JDK 17**
- **JavaFX SDK** (if developing or running the GUI)

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first).
2. Open the project into IntelliJ:
   - Click `Open`.
   - Select the project directory, and click `OK`.
   - If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. Follow the [JavaFX setup guide](https://se-education.org/guides/tutorials/javaFx.html) if you plan to work with the GUI.

Once set up, locate the `src/main/java/KukiShinobu.java` file, right-click it, and choose `Run KukiShinobu.main()`. If the setup is correct, you should see the bot running in the terminal or through the GUI.

## Build & Deployment

### Creating the JAR File

To create a cross-platform JAR file that works on any system with Java 17 installed:

1. Open a terminal in the project root.
2. Run the following command to generate a fat JAR file:
   ```bash
   ./gradlew clean shadowJar
   ```
3. Perform smoke tests to ensure the JAR file works across platforms:
   - Move the JAR file to an empty folder.
   - Run the JAR file in the terminal:
     ```bash
     java -jar KukiShinobu.jar
     ```
   - Test on different operating systems, or share the JAR file with others to verify cross-platform functionality.

### Version Control & Releases

To release a new version of the bot:

1. Create a new GitHub release (e.g., `v1.0`).
2. Upload the JAR file.
3. Ensure that only one JAR file is uploaded to avoid confusion during grading or usage.

## Acknowledgements

### Use of Generative AI
Generative AI tools, including ChatGPT, were heavily used for creating detailed Javadocs, commit messages, test suite creation and occasional code refactoring throughout the development of Kuki Shinobu.

### JavaFX Tutorial
The GUI for this project was created with guidance from the [JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html).
