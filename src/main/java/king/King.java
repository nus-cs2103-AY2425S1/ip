package king;

import king.commands.Command;
import king.ui.Ui;

/**
 * The main class for the King application. It handles initialization, task storage, and user interaction.
 */
public class King {
    private static final String DATA_FILE_PATH = "data/tasks.json"; // Updated to match Storage class
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the King application with the specified file path for storing tasks.
     * Loads tasks from the file, if available. If there is an issue loading the tasks,
     * initializes an empty task list.
     */
    public King() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KingException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the King application, displaying a welcome message and processing user commands
     * until the user exits the program. If any errors occur while processing a command,
     * an error message is displayed.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KingException e) {
                ui.showErrorAsString(e);
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            // Parse the input and create a command
            Command command = Parser.parse(input);

            // Execute the command and return the result as a string
            return command.execute(tasks, ui, storage);

        } catch (KingException e) {
            // If an error occurs, return the error message
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    public static void main(String[] args) {
        new King().run(); // Calls the default constructor
    }
}
