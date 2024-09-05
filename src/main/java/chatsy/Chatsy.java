package chatsy;

import chatsy.exceptions.ChatsyException;
import chatsy.exceptions.LocalFileException;

/**
 * The main class for the Chatsy application.
 * It initializes the necessary components and interacts with the GUI.
 */
public class Chatsy {
    private static final String LOCAL_DIRECTORY_PATH = "./data";
    private static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatsy.txt";
    private TaskManager taskManager;
    private Storage storage;
    private CommandHandler commandHandler;

    /**
     * Constructs a Chatsy instance.
     */
    public Chatsy() {
        this.storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
        this.taskManager = new TaskManager();
        this.commandHandler = new CommandHandler(taskManager); // GUI will handle output now

        // Load tasks when starting the application
        try {
            taskManager.setTasks(storage.loadTasks());
        } catch (LocalFileException e) {
            // Handle error in the GUI
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    /**
     * Processes the user command and returns the appropriate response for the GUI to display.
     *
     * @param input The user input command.
     * @return The response message after handling the command.
     * @throws ChatsyException If any error occurs while handling the command.
     */
    public String handleCommand(String input) throws ChatsyException {
        return commandHandler.handle(input);
    }

    /**
     * Returns a greeting message for the user when the application starts.
     *
     * @return A greeting string.
     */
    public String greet() {
        return "Hello! I'm Chatsy. How can I assist you today?";
    }

    /**
     * Exits the Chatsy application by saving tasks and returning a goodbye message.
     *
     * @return A goodbye message to display in the GUI.
     */
    public String exit() {
        try {
            storage.saveTasks(taskManager.getTasks());
        } catch (LocalFileException e) {
            return "Failed to save tasks: " + e.getMessage();
        }
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Initializes the task list by loading tasks from storage (if any).
     *
     * @return A message indicating success or failure in loading tasks.
     */
    public String initTaskList() {
        try {
            taskManager.setTasks(storage.loadTasks());
            return "Task list initialized successfully!";
        } catch (LocalFileException e) {
            return "Failed to load tasks: " + e.getMessage();
        }
    }

    // Getter methods for accessing task manager, storage, etc., if needed by GUI
    public TaskManager getTaskManager() {
        return taskManager;
    }

    public Storage getStorage() {
        return storage;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
