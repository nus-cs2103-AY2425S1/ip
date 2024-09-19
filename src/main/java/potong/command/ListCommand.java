package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

/**
 * Represent the command for showing the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Initialise the command.
     *
     * @param command Empty String.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Retrieves the String representation of the list of tasks.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of the list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null;
        return tasks.toString();
    }
}
