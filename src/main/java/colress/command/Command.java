package colress.command;

import colress.TaskList;
import colress.Ui;

public abstract class Command {
    private final String executeSuccessMessage;

    public Command(String executeSuccessMessage) {
        this.executeSuccessMessage = executeSuccessMessage;
    }

    public String getExecuteSuccessMessage() {
        return executeSuccessMessage;
    }
    public abstract void execute(Ui ui, TaskList taskList);
}
