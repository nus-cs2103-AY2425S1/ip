package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * An abstract class that encapsulates behaviour of a command.
 */
public abstract class Command {
    private final String successfulExecutionMessage;

    public Command(String successfulExecutionMessage) {
        this.successfulExecutionMessage = successfulExecutionMessage;
    }

    public String getSuccessfulExecutionMessage() {
        return successfulExecutionMessage;
    }

    public abstract void execute(Ui ui, TaskList taskList);
}
