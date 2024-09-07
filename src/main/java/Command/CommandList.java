package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzEmptyTaskListException;
import exception.BlitzException;

/**
 * Represents a "list" command in the Blitz application.
 */
public class CommandList extends Command {
    /**
     * Constructs a new CommandList object with specified command String.
     *
     * @param command Command String to be associated with this Command object.
     */
    public CommandList(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to get all the existing Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to be used if required.
     * @return Execution result of the command as String.
     * @throws BlitzException If TaskList is empty.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        if (list.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        return ui.getStringForAllTasks(list);
    }
}
