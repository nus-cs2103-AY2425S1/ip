package commands;

import skibidi.Command;
import skibidi.Ui;
import storage.TaskStorage;

/**
 * Represents a command to echo a message.
 */
public class EchoCommand extends Command {
    private final String message;

    /**
     * Creates a new EchoCommand.
     *
     * @param message The message to be echoed.
     */
    public EchoCommand(String message) {
        this.message = message.split(" ", 2)[1];
    }

    /**
     * Executes the command to echo a message.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        return ui.outputMessage(message);
    }
}
