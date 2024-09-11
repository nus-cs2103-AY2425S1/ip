/**
 * Represents the command that unmarks tasks in the taskList.
 *
 * @author Aaron
 */
public class UnmarkCommand extends Command {
    /**
     * The index of the task to be unmarked.
     */
    private int taskIndex;

    /**
     * Constructs a UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark the task at the specified index.
     *
     * @param tasks The task list to update.
     * @param ui The Ui instance, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.unmarkTask(taskIndex);
    }
}
