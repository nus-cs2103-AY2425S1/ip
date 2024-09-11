package jade.command;

import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Abstract class representing a command in the Jade application.
 */
public abstract class Command {
    protected static final boolean FOR_GUI = true;
    protected static final boolean FOR_TEXT_UI = false;

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
     * Executes the command and returns the result formatted for the GUI.
     *
     * @return A string message formatted for display in the GUI.
     */
    public abstract String runForGui();

    /**
     * Executes the command for the text-based UI.
     *
     * @return A string message representing the result of the command execution.
     */
    public abstract String run();

    /**
     * Appends indentation to the message if the command is being executed for text-based UI.
     *
     * @param forGui Indicates if the execution is for the GUI.
     * @param message The StringBuilder to append the indentation to.
     */
    protected void indentIfNotGui(boolean forGui, StringBuilder message) {
        if (!forGui) {
            message.append(Ui.INDENT);
        }
    }

    /**
     * Displays a formatted message for the text-based UI
     * with a top and bottom line for decoration.
     *
     * @param message The message to display for the text-based UI.
     * @return A formatted string with the message encapsulated by a top and bottom line.
     */
    protected String displayMessage(boolean forGui, String message) {
        if (forGui) {
            return message;
        } else {
            return Ui.formatTextUiMessage(message);
        }
    }
}
