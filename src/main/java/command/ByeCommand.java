package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;

/**
 * Represents a "bye" command in the Blitz application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a new CommandBye object with specified command String.
     *
     * @param command Command String to be associated with this Command object.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to be used if required.
     * @param ui Ui to be used if required.
     * @param storage Storage to be used if required.
     * @return Execution result of the command as String.
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        return "bye";
    }

}
