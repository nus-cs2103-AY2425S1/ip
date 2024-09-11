package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents an abstract command that can be executed in the application.
 * Specific command types should extend this class and implement the {@link #execute} method.
 */
public abstract class Command {
    // description of the command
    private String command;
    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(Task task, Ui ui, Storage storage);

    public abstract String getString();

    public abstract boolean isExit();
}
