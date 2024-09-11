/**
 * Represents the command that lists the tasks in the taskList.
 *
 * @author Aaron
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by listing all the tasks in the taskList.
     *
     * @param tasks The task list, which remains unchanged by this command.
     * @param ui The Ui instance that will display the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.listTasks();
    }
}
