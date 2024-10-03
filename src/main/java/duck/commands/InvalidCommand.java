package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command that handles invalid instructions given by the user.
 * When executed, it informs the user that the instruction is not recognized.
 */
public class InvalidCommand extends Command {

    private static final String MESSAGE_INVALID_COMMAND =
            "Quack, that's not a valid instruction! Duck don't know how to respond to that.\n";

    /**
     * Constructs an InvalidCommand with the specified message.
     *
     * @param message The unrecognized command or instruction provided by the user.
     */
    public InvalidCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by notifying the user that the instruction is invalid.
     * This method prints a message to the console.
     *
     * @param tasks The list of tasks.
     * @param storage The storage system.
     * @param ui The user interface.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);
        throw new DuckException(MESSAGE_INVALID_COMMAND);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the InvalidCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
