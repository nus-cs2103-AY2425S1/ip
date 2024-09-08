package jade.command;

import jade.exception.JadeException;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Abstract class representing a command in the Jade application.
 */
public abstract class Command {
    protected TaskManager taskManager;

    public Command() {

    }

    /**
     * Constructs a Command object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to interact with.
     */
    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the command.
     *
     * @return A string message representing the result of the command execution.
     * @throws JadeException if the command execution fails.
     */
    public abstract String run() throws JadeException;

    protected String displayMessage(String message) {
        return Ui.TOP_LINE + message + Ui.BOT_LINE;
    }

    protected String displayErrorMessage(String errorMessage) {
        return Ui.TOP_LINE + Ui.INDENT + errorMessage + Ui.BOT_LINE;
    }
}
