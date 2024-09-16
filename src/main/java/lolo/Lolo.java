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
        // Assert that the filePath is not null
        assert filePath != null : "File path should not be null";

        storage = new Storage(filePath);

        try {
            // Load tasks from storage
            tasks = new TaskList(storage.load());

            // Assert that the tasks list is not null after loading
            assert tasks != null : "Task list should not be null after loading";
        } catch (LoloException e) {
            tasks = new TaskList(); // Create an empty task list if loading fails
        }

        // Assert that tasks is properly initialized (either from load or new list)
        assert tasks != null : "Task list should be initialized";
    }

    /**
     * Returns a response to the user input.
     * Processes the input, executes the corresponding command, and returns feedback.
     *
     * @param input The user input to be processed.
     * @return The response from Lolo based on the command executed.
     */
    public String getResponse(String input) {
        // Assert that input is not null
        assert input != null : "Input should not be null";

        try {
            // Parse the user input into a command
            Command command = Parser.parse(input);

            // Assert that the command is valid (not null)
            assert command != null : "Parsed command should not be null";

            // Execute the command and get the response
            String response = command.execute(tasks, storage);

            // Assert that the response is valid (not null)
            assert response != null : "Response from command execution should not be null";

            return response;
        } catch (LoloException e) {
            return "Error: " + e.getMessage();  // Return error message
        }
    }

    /**
     * The main entry point of the Lolo application.
     * Initializes and runs the Lolo application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialize Lolo with a file path
        assert args.length > 0 : "File path should be provided as an argument";

        new Lolo(args[0]);  // Pass the file path to the Lolo constructor
    }
}
