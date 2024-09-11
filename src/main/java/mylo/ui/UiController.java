package mylo.ui;

import mylo.command.Command;
import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Controller for managing the interaction between the GUI, TUI, and back-end logic of the Mylo application.
 * <p></p>
 * <p>
 * The UiController acts as the mediator between GUI and TUI, and the
 * underlying task management system. It handles command execution and updates the user interfaces accordingly.
 * </p>
 */
public class UiController {
    private TaskList taskList;
    private Gui gui;
    private Tui tui;

    /**
     * Constructs a UiController with the specified GUI, TUI, and TaskList.
     * <p>
     * This constructor initializes the controller and displays a welcome message to both the GUI and TUI.
     * </p>
     *
     * @param gui The graphical user interface.
     * @param tui The text-based user interface.
     * @param taskList The list of tasks managed by the application.
     */
    public UiController(Gui gui, Tui tui, TaskList taskList) {
        this.gui = gui;
        this.tui = tui;
        this.taskList = taskList;

        String name = "Mylo";
        String greet = "Hello! Thanks for using " + name + ".\n";
        String openingQuery = "What can I help you?";
        gui.showWelcomeMessage(greet + openingQuery);
        tui.showWelcomeMessage(greet + openingQuery);
    }

    /**
     * Executes the given command and returns the response.
     * <p>
     * This method processes the command, updating the task list and returning the response.
     * </p>
     *
     * @param command The command to be executed.
     * @return The result of executing the command.
     * @throws InsufficientInfoException If there is insufficient information to execute the command.
     * @throws IllegalValueException If the command contains illegal values.
     * @throws StorageOperationException If there is an error during storage operations.
     */
    public String execute(Command command) throws InsufficientInfoException, IllegalValueException,
            StorageOperationException {
        return command.execute(taskList, tui);
    }

    /**
     * Notifies the TUI about an input or response message.
     * <p>
     * This method updates the TUI with either the user's input or the system's response.
     * </p>
     *
     * @param message The message to be sent to the TUI.
     * @param isInput A boolean flag indicating whether the message is user input (true) or a system response (false).
     */
    public void notifyTui(String message, boolean isInput) {
        if (isInput) {
            tui.updateInput(message);
        } else {
            tui.updateResponse(message);
        }
    }
}
