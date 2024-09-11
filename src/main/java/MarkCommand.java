/**
 * Represents the command that marks tasks in the taskList.
 *
 * @author Aaron
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked as completed.
     */
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as completed.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the task at the specified index as completed.
     *
     * @param tasks The task list to update.
     * @param ui The Ui instance, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.markTask(taskIndex);
    }
}
