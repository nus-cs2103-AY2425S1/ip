package Gary;

import java.io.FileNotFoundException;

import Gary.command.Command;

/**
 * The {@code Gary} class represents the main program that runs the task management application.
 * It handles the initialization of the necessary components and manages the main program loop.
 */
public class Gary {
    // Storage object for saving and loading tasks
    protected Storage storage;
    // TaskList object for managing tasks
    protected TaskList taskList;
    // Ui object for interacting with the user
    protected Ui ui;

    /**
     * Constructs a {@code Gary} object with the specified file path for storing tasks.
     * Initializes the {@code Ui}, {@code Storage}, and {@code TaskList} objects.
     *
     * @param filePath The file path where tasks are stored and loaded from.
     */
    public Gary(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.loadTaskList());
        } catch (FileNotFoundException e) {
            this.taskList = new TaskList();
        }

        // Assertion: Ensure that taskList and storage are initialized correctly
        assert this.taskList != null : "TaskList initialization failed!";
        assert this.storage != null : "Storage initialization failed!";
        assert this.ui != null : "UI initialization failed!";
    }

    /**
     * Starts the main program loop. This method greets the user, reads user input,
     * processes commands, and handles any exceptions that occur during execution.
     */
    public void start() {
        boolean isBye = false;
        this.ui.greet();

        // Assertion: Ensure that greet method has been called
        assert this.ui != null : "UI object should be initialized before starting.";

        while (!isBye) {
            try {
                String commandInput = this.ui.read();
                Command command = Parser.parse(commandInput);

                // Assertion: Parser should return a valid command
                assert command != null : "Parser failed to return a valid command!";

                command.execute(this.taskList, this.ui, this.storage);
                isBye = command.isBye();

                // Assertion: If isBye is true, the program will exit after this loop
                assert isBye == command.isBye() : "isBye should match command.isBye()";

            } catch (GaryException garyException) {
                this.ui.showError(garyException.getMessage());
            }
        }
    }

    /**
     * Returns the response for a given user input.
     *
     * @param input The user input.
     * @return The response from the application.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);

            // Assertion: Parser should return a valid command
            assert c != null : "Parser failed to return a valid command!";

            return c.execute(taskList, ui, storage);
        } catch (GaryException e) {
            return e.getMessage();
        }
    }
}
