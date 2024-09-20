package chobo;

public class UnmarkCommand extends Command{
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task task = taskList.getTask(taskId);
        task.unmark();
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskUnmarked(task);
    }
}
