package chobo;

/**
 * Represents the command that marks tasks as done
 */
public class MarkCommand extends Command {
    private int taskId;

    /**
     * Instantiates a new Mark command.
     *
     * @param taskId The task id (shown by list)
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task task = taskList.getTask(taskId);
        task.mark();
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskMarked(task);
    }
}
