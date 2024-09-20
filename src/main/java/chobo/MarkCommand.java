package chobo;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {
    private int taskId;

    /**
     * Instantiates a new Mark command.
     *
     * @param taskId the task id
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
