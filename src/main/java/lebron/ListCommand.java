package lebron;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the task list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to show the task list.
     * @param storage The storage (not used in this command).
     * @throws LeBronException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        ui.showTaskList(taskList);
    }
    
}
