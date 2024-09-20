package main;

import exception.KukiShinobuException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import command.Command;

/**
 * The main class for the Kuki Shinobu application, responsible for managing backend logic.
 * <p>
 * The {@code KukiShinobu} class handles user commands, manages the task list, and interacts
 * with storage. It processes commands entered by the user and updates the task list and storage
 * accordingly. The class continues to operate until an exit command is received.
 * </p>
 * <p>
 * This class does not handle the user interface setup but focuses on the core functionality
 * of parsing commands, executing them, and maintaining the application's state.
 * </p>
 */
public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    // IMPORTANT: Relative Filepath Specified must always be relative to root directory of the entire project
    private static final String FILE_PATH = "./data/database.txt";

    private boolean isExit = false;

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a {@code KukiShinobu} instance with the specified file path for storage.
     * <p>
     * Initializes the task list and sets up storage for task data. If an error occurs during
     * the loading of tasks, a default empty task list is created, and an error message is displayed.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public KukiShinobu(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KukiShinobuException e) {
            //TODO: Show more informative error message depending on the error that was thrown
            tasks = new TaskList();
        }
    }

    public KukiShinobu() {
        this(KukiShinobu.FILE_PATH);
    }

    /**
     * Checks if the application should exit.
     *
     * @return True if the exit condition is met, otherwise false.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Generates a welcome message from the bot.
     *
     * @return A welcome message.
     */
    public String generateWelcomeMessage() {
        return "Hey Traveller! I'm Kuki Shinobu, deputy leader of the Arataki Gang."
                + System.lineSeparator()
                + "Just let me know if you ever find yourself in a pinch. I can help you out.";
    }

    /**
     * Processes user input and generates a response.
     * <p>
     * Parses the user input to create a command, executes the command, and updates the task list
     * and storage. If an exception is thrown during parsing or execution, the error message is returned.
     * </p>
     *
     * @param input The user input to process.
     * @return The response generated based on the command execution or error message.
     */
    public String getResponse(String input) {
        String response;
        try {
            // Parser can throw a KukiShinobuException whose error message will be returned as the response
            Command command = Parser.parse(input);
            this.isExit = command.isExit();
            response = command.execute(tasks, storage);
        } catch (KukiShinobuException e) {
            response = e.getMessage();
        } finally {
            this.storage.write(this.tasks.getTasks());
        }
        return response;
    }
}
