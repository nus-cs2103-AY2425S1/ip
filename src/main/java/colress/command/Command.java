package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * An abstract class that encapsulates behaviour of a command.
 */
public abstract class Command {
    private final String EXECUTE_SUCCESS_MESSAGE;

    public Command(String executeSuccessMessage) {
        this.EXECUTE_SUCCESS_MESSAGE = executeSuccessMessage;
    }

    public String getExecuteSuccessMessage() {
        return EXECUTE_SUCCESS_MESSAGE;
    }

    public abstract void execute(Ui ui, TaskList taskList);
}
