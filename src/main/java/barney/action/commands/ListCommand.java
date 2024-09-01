package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

/**
 * Represents a command to list all tasks. Extends the {@link Command} class.
 */
public class ListCommand extends Command {

    /**
     * Represents a command for listing items.
     *
     * @param argumentMap A HashMap containing the arguments for the command.
     */
    public ListCommand(HashMap<String, String> argumentMap) {
        super("list", argumentMap);
    }

    /**
     * Executes the ListCommand, which prints the list of tasks to the user
     * interface.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui    The Ui object for displaying the list of tasks.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the command arguments are invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        ui.printList(tasks);
        return true;
    }
}
