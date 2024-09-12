package dipsy;

import dipsy.command.Command;
import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.exception.UnknownCommandException;
import dipsy.javafx.MainWindow;
import dipsy.parser.Parser;
import dipsy.storage.Storage;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * The {@code Dipsy} class is the main entry point for the program.
 * It initializes the user interface, task list, and manages user commands.
 */
public class Dipsy {

    /** The user interface for interacting with the user. */
    private final Ui ui;

    /** The list of tasks managed by the application. */
    private final TaskList taskList;

    /** A reference to the {@link MainWindow} for interacting with the JavaFX UI. */
    private MainWindow mainWindow;

    /**
     * Constructs a new {@code Dipsy} object, initializing the user interface and task list.
     */
    public Dipsy() {
        this.ui = new Ui();
        this.taskList = Storage.load(); // Load tasks from local disk when the application starts
        assert this.taskList != null : "TaskList should not be null after loading from storage";
    }

    /**
     * Sets the {@link MainWindow} reference, allowing {@code Dipsy} to interact with the JavaFX UI.
     *
     * @param mainWindow The {@code MainWindow} instance controlling the UI.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Returns the welcome message to be displayed when the application starts.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Closes the application by invoking {@link MainWindow#closeApplicationWithDelay}.
     */
    public void exit() {
        if (mainWindow != null) {
            mainWindow.closeApplicationWithDelay(2000);
        }
    }

    /**
     * Generates a response based on the user's input in the chat.
     *
     * <p>This method processes the user's input by parsing it into a command, executing the command, and returning the result.</p>
     *
     * @param input The user's input message to be processed.
     * @return A response message based on the executed command, or an error message if the input is invalid.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null";

        Command command;
        String response;
        try {
            command = parseUserCommand(input); // Refactored parsing logic
            response = executeCommand(command); // Refactored command execution
        } catch (InvalidCommandException | InvalidDateException | UnknownCommandException e) {
            return ui.getErrorMessage(e.getMessage()); // Handle errors consistently
        } catch (Exception e) {
            return ui.getErrorMessage("An unexpected error occurred: " + e.getMessage());
        }

        if (command.isExit()) {
            exit();
        }
        return response;
    }


    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The parsed {@link Command}.
     * @throws UnknownCommandException If the command cannot be recognized.
     */
    private Command parseUserCommand(String input) throws UnknownCommandException {
        Command command = Parser.parseCommand(input, taskList, ui);
        assert command != null : "Parsed command should not be null";
        return command;
    }

    /**
     * Executes the given command and returns the result as a string.
     *
     * @param command The command to execute.
     * @return The result of the command execution.
     * @throws InvalidCommandException If the command is invalid.
     * @throws InvalidDateException If a date-related error occurs.
     */
    private String executeCommand(Command command) throws InvalidCommandException, InvalidDateException {
        String response = command.execute();
        assert response != null : "Response from command execution should not be null";
        return response;
    }
}
