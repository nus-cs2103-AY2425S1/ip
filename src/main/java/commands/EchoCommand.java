package commands;

import skibidi.Ui;
import skibidi.Command;
import storage.TaskStorage;

/**
 * Represents a command to echo a message.
 */
public class EchoCommand extends Command {
    private String message;

    /**
     * Creates a new EchoCommand.
     *
     * @param message The message to be echoed.
     */
    public EchoCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command to echo a message.
     *
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        ui.printMessage(message);
        return true;
    }
}