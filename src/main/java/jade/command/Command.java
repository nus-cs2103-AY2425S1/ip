package jade.command;

import jade.exception.JadeException;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Abstract class representing a command in the Jade application.
 */
public abstract class Command {
    protected TaskManager taskManager;

    /**
     * Default constructor for the Command class.
     */
    public Command() {}

    /**
     * Constructs a Command object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to interact with.
     */
    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the command for the text-based UI.
     *
     * @return A string message representing the result of the command execution.
     * @throws JadeException if the command execution fails.
     */
    public abstract String run();

    /**
     * Executes the command and returns the result formatted for the GUI.
     *
     * @return A string message formatted for display in the GUI.
     * @throws JadeException if the command execution fails.
     */
    public abstract String runForGui();

    /**
     * Displays a formatted message for the text-based UI
     * with a top and bottom line for decoration.
     *
     * @param message The message to display for the text-based UI.
     * @return A formatted string with the message encapsulated by a top and bottom line.
     */
    protected String displayMessage(String message) {
        return Ui.TOP_LINE + message + Ui.BOT_LINE;
    }

    /**
     * Displays a formatted error message for the text-based UI
     * with indentation and a top and bottom line for decoration.
     *
     * @param errorMessage The error message to display for the text-based UI.
     * @return A formatted string with the error message encapsulated by a top and bottom line.
     */
    protected String displayErrorMessage(String errorMessage) {
        return Ui.TOP_LINE + Ui.INDENT + errorMessage + Ui.BOT_LINE;
    }
}
