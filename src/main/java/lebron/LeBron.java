package lebron;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for the LeBron application. This class handles the
 * initialization of the application, including loading tasks, handling user
 * commands, and controlling the application's flow.
 */
public class LeBron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a LeBron application instance with the specified file path for
     * storage. Initializes the storage, user interface, and parser. Attempts to
     * load the task list from the specified file, or initializes an empty task
     * list if loading fails.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public LeBron(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the LeBron application, handling the main loop that processes user
     * input and executes commands until the application is instructed to exit.
     */
    public String getResponse(String input) {

        assert input != null : "Input should not be null";
        
        StringBuilder response = new StringBuilder();
        try {
            // Parse the input and execute the command
            Command command = parser.parse(input);  // Assume parser is initialized elsewhere
            response.append(command.execute(taskList, ui, storage));  // Append the command output to response

            // Check if the command is an exit command
            if (command.isExit()) {
                response.append(ui.showGoodbyeMessage());
            }

        } catch (LeBronException e) {
            // Capture error messages into the response instead of printing
            response.append(e.getMessage()).append("\n");
        }

        return response.toString();
    }

}
