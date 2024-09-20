package chatsy;

import chatsy.exceptions.ChatsyException;
import chatsy.exceptions.InvalidTaskStringException;
import chatsy.exceptions.LocalFileException;
import chatsy.parser.Parser;

/**
 * The main class for the Chatsy application.
 * It initializes the necessary components and interacts with the GUI.
 */
public class Chatsy {

    private static final String LOCAL_DIRECTORY_PATH = "./data";
    private static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatsy.txt";

    private final TaskManager taskManager;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructs a Chatsy instance.
     */
    public Chatsy() {
        this.storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
        this.taskManager = new TaskManager();
        this.parser = new Parser(taskManager);

        // Initialize tasks from the storage upon starting the application
        loadTasks();
    }

    /**
     * Loads tasks from storage and sets them into the task manager.
     */
    private void loadTasks() {
        try {
            taskManager.setTasks(storage.loadTasks());
        } catch (LocalFileException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        } catch (InvalidTaskStringException e) {
            System.out.println("Failed to set tasks: Invalid task data.");
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
        return parser.parse(input).execute(taskManager);
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
            return "Bye. Hope to see you again soon!";
        } catch (LocalFileException e) {
            return "Failed to save tasks: " + e.getMessage();
        }
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
        } catch (InvalidTaskStringException e) {
            return "Failed to set tasks: Invalid task data.";
        }
    }

    // Getter methods for accessing task manager, storage, etc., if needed by GUI

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public Storage getStorage() {
        return storage;
    }

    public Parser getParser() {
        return parser;
    }
}
