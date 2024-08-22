/**
 * Represent the command to display the task list.
 * This command implements the Command interface and is for
 * displaying the tasks currently in the task list.
 */
public class ListCommand implements Command {
    /**
     * @inheritDoc
     *
     * Displays the tasks in the task list.
     *
     * @param tasks The TaskList which the tasks will be retrieved from.
     * @param ui The Ui object used to display the tasks in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayTaskList(tasks);
    }
}
