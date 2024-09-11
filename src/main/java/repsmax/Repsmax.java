package repsmax;

import javafx.application.Application;

/**
 * Represents the main class of the Repsmax application.
 * <p>
 * This class initializes the core components of the application, including
 * storage, task management, user interface, and command parsing.
 * It also manages the overall flow of the application by handling user inputs
 * and interacting with various components.
 * </p>
 */
public class Repsmax {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a {@code Repsmax} object with the specified file path.
     * <p>
     * This constructor initializes the user interface, storage system,
     * parser, and task list. It also attempts to load tasks from the file
     * specified by {@code filePath}. If loading tasks fails, an error message
     * is displayed.
     * </p>
     *
     * @param filePath The path to the file where task data is stored.
     *                 It cannot be null or empty.
     */
    public Repsmax(String filePath) {
        ui = new Ui();  // Initializes the user interface component.
        storage = new Storage(filePath);  // Initializes the storage component with the specified file path.
        parser = new Parser();  // Initializes the parser for processing user inputs.
        tasks = new TaskList();  // Initializes the task list to manage tasks.
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        try {
            storage.load(tasks);  // Attempts to load tasks from the file into the task list.
        } catch (Exception e) {
            ui.showLoadingError();  // Displays an error message if loading fails.
        }
    }

    /**
     * Processes user input and generates a response.
     * <p>
     * If the user input is "bye", the application saves the current tasks
     * to the storage and returns a goodbye message. For other inputs,
     * it delegates the parsing to the {@code Parser} and returns the
     * result of the parsing.
     * </p>
     *
     * @param userInput The input provided by the user.
     * @return A response based on the user input, either a goodbye message
     *         or the result of parsing the input.
     */
    public String getResponse(String userInput) {
        assert userInput != null : "User input cannot be null";
        if (userInput.equals("bye")) {
            storage.save(tasks);  // Saves the current tasks to the storage before exiting.
            return ui.showGoodbye();  // Returns a goodbye message.
        } else {
            return parser.parse(userInput, tasks, ui, storage);  // Processes other inputs and returns the result.
        }
    }

    /**
     * Parses user input and performs the corresponding actions.
     * <p>
     * This method delegates the parsing to the {@code Parser}, which processes
     * the input and updates the task list or user interface as needed.
     * </p>
     *
     * @param userInput The input provided by the user to be parsed.
     */
    public void parse(String userInput) {
        assert userInput != null : "User input cannot be null";
        parser.parse(userInput, tasks, ui, storage);  // Delegates input parsing to the parser.
    }
}
