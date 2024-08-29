package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private final String message;

    /**
     * Constructor for InvalidCommand.
     *
     * @param message Error message to be displayed.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(message);
    }
}