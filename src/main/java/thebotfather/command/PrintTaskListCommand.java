package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * A command to print the current list of tasks.
 */
public class PrintTaskListCommand extends Command {

    /**
     * Executes the command to display the current task list.
     * <p>
     * Retrieves the task descriptions from the {@code taskList} and returns
     * them as a formatted string to be displayed via the {@code Ui}.
     *
     * @param taskList The list of tasks to be printed.
     * @param ui The user interface to display the task list.
     * @param storage The storage system (not used by this command).
     * @return A string representing the current task list.
     * @throws TheBotFatherException If an error occurs while retrieving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        return taskList.getListDesc();
    }
}
