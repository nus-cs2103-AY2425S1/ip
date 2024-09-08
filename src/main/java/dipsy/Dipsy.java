package dipsy;

import dipsy.command.Command;
import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.exception.UnknownCommandException;
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

    /**
     * Constructs a new {@code Dipsy} object, initializing the user interface and task list.
     */
    public Dipsy() {
        this.ui = new Ui();
        this.taskList = Storage.load(); // Load tasks from local disk when the application starts
    }


    /**
     * Runs the main loop of the program, printing the welcome message and processing commands until
     * the exit command is given.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public void exit() {
        System.exit(0);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command;
        String response;

        // Attempt to parse the input to obtain command
        try {
            command = Parser.parseCommand(input, taskList, ui);
        } catch (UnknownCommandException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage("An unexpected error occured: " + e.getMessage());
        }

        // Attempt to execute command
        try {
            response = command.execute();
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
