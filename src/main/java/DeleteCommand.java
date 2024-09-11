/**
 * Represents the command that deletes tasks in the taskList.
 *
 * @author Aaron
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete the task at the specified index.
     *
     * @param tasks The task list to update.
     * @param ui The Ui instance, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.deleteTask(taskIndex);
    }
}
