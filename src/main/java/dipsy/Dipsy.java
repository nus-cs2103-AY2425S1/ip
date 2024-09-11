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
 * It initializes the user interface, task list, and runs the command loop to process user input.
 * This class manages the overall flow of the application, from startup to termination.
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
     *  Returns the welcome message to be displayed when the application starts.
     * <p>This method retrieves the welcome message from the {@code Ui} class, which is displayed to
     * the user upon starting the application.</p>
     *  @return The welcome message to be shown when the application starts.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Exits the application by calling a method in the {@link MainWindow} to gracefully close the
     * JavaFX application.
     */
    public void exit() {
        if (mainWindow != null) {
            mainWindow.closeApplicationWithDelay(2000);
        }
    }

    /**
     * Generates a response based on the user's input in the chat.
     *
     * <p>This method processes the user's input by first attempting to parse it into a command.
     * If the input is invalid or unrecognized, appropriate error messages are returned.
     * If the input is valid, the command is executed, and the result is returned as the response.
     * If the command is an exit command, the application will close after processing the response.</p>
     *
     * @param input The user's input message to be processed.
     * @return A response message based on the executed command, or an error message if the input is invalid.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null";

        Command command;
        String response;

        // Attempt to parse the input to obtain command
        try {
            command = Parser.parseCommand(input, taskList, ui);
            assert command != null : "Parsed command should not be null";
        } catch (UnknownCommandException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage("An unexpected error occured: " + e.getMessage());
        }

        // Attempt to execute command
        try {
            response = command.execute();
            assert response != null : "Response from command execution should not be null";
        } catch (InvalidCommandException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidDateException e) {
            return ui.getErrorMessage(e.getMessage());
        }

        if (command.isExit()) {
            exit();
        }

        return response;
    }
}
