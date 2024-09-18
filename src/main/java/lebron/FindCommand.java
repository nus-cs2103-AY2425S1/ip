package lebron;

/**
 * Represents a command to find tasks that match a given keyword.
 * This command searches through the task list for tasks that contain
 * the specified keyword and returns the results.
 */
public class FindCommand extends Command {

    /** The keyword to search for in the task list. */
    public final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks in the provided task list
     * that match the keyword. The matching tasks are then displayed using the
     * provided UI.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The UI object used to display the matching tasks.
     * @param storage The storage object used to save the tasks (not used in this command).
     * @return A string representation of the tasks that match the keyword.
     * @throws LeBronException If there is an error during execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        return ui.showMatchingTasks(taskList, keyword);
    }
}

