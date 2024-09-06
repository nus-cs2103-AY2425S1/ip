package lolo;

import lolo.command.Command;
import lolo.command.Parser;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents the main class of the Lolo chatbot application.
 * Responsible for initializing the application, handling user commands,
 * and managing the flow of the program.
 */
public class Lolo {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Lolo object with the specified file path.
     * Initializes the storage and task list. If the task list
     * cannot be loaded from the specified file, a new empty task list is created.
     *
     * @param filePath The file path to load tasks from.
     */
    public Lolo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LoloException e) {
            tasks = new TaskList(); // Create an empty task list if loading fails
        }
    }

    /**
     * Returns a response to the user input.
     * Processes the input, executes the corresponding command, and returns feedback.
     *
     * @param input The user input to be processed.
     * @return The response from Lolo based on the command executed.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);  // Execute and return the response
        } catch (LoloException e) {
            return "Error: " + e.getMessage();  // Return error message
        }
    }

    /**
     * The main entry point of the Lolo application.
     * Creates a new Lolo object and starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Lolo("./data/lolo.txt");  // Initialize Lolo
    }
}



