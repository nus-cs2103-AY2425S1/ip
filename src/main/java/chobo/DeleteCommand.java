package chobo;

public class DeleteCommand extends Command{
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task removedTask = taskList.getTask(taskId);
        taskList.deleteTask(taskId);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskDeleted(removedTask, taskList.getTotalTask());
    }
}
